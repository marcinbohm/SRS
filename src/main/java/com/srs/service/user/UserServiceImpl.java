package com.srs.service.user;

import com.srs.OperationStatus;
import com.srs.dict.DictOperationName;
import com.srs.dto.UserDTO;
import com.srs.dto.UserShiftDTO;
import com.srs.entity.Cell;
import com.srs.entity.Sentence;
import com.srs.entity.User;
import com.srs.entity.Shift;
import com.srs.mapper.SmartMapper;
import com.srs.repository.ShiftRepository;
import com.srs.repository.UserRepository;
import com.srs.repository.filters.UserFilter;
import com.srs.repository.specs.ShiftSpecs;
import com.srs.security.auth.AuthenticationFacade;
import com.srs.util.FormatUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Validated
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationFacade authenticationFacade;

    private final ShiftRepository shiftRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthenticationFacade authenticationFacade, ShiftRepository shiftRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationFacade = authenticationFacade;
        this.shiftRepository = shiftRepository;
    }

    @Override
    public Optional<User> findUserById(Integer userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public Optional<User> findUserByLogin(String username) {
        return userRepository.findByLogin(username);
    }

    @Override
    public Optional<User> findUserByIdAndRefreshTicket(Integer userId, String refreshToken) {
        return userRepository.findByUserIdAndUserSessionsList_RefreshTicket(userId, refreshToken);
    }

    @Override
    public <S extends User> S save(S user) {
        return userRepository.save(user);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        return userRepository.findById(userId)
                .map(user -> this.modelMapper.map(user, UserDTO.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> this.modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getUsersByFilter(UserFilter userFilter) {
        return null;
    }

    @Override
    public OperationStatus upsert(User user) {
        boolean adding = (user.getUserId() == null);

        User userExisting =
                (adding ? new User() : userRepository.findById(user.getUserId())
                        .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono obiektu z Id: " + user.getUserId())));

        if (user.getPassword() != null
                && !passwordEncoder.matches(user.getPassword(), userExisting.getPassword()))
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        else
            user.setPassword(null);
        OperationStatus opStatus =
                new OperationStatus(User.class.getSimpleName(),
                        adding ? DictOperationName.ADD.getCode()
                                : DictOperationName.UPDATE.getCode());
        SmartMapper.transferData(user, userExisting);

        User userSaved =
                userRepository.save(userExisting);
        Integer id = userSaved.getUserId();
        opStatus.setRecordId(id).setSuccess(id != null);
        return opStatus;
    }
    
    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public OperationStatus delete(Integer userId) {

        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(EntityNotFoundException::new);

        OperationStatus opStatus =
                new OperationStatus(
                        Shift.class.getSimpleName(),
                        DictOperationName.DELETE.getCode()
                );

        opStatus.setRecordId(userId);
        userRepository.delete(user);

        return opStatus.setSuccess(!userRepository.existsById(userId));
    }

    @Override
    public List<UserShiftDTO> getLoggedUserShifts() {

        User loggedUser = userRepository.findByUserId(authenticationFacade.getCurrentUser().getId())
                .orElseThrow(EntityNotFoundException::new);

        Specification<Shift> shiftSpec = Specification
                .where(ShiftSpecs.userIdIn(Collections.singletonList(loggedUser.getUserId())));
        List<Shift> shifts = shiftRepository.findAll(shiftSpec);

        return mapLoggedUserShifts(loggedUser, shifts);
    }

    private List<UserShiftDTO> mapLoggedUserShifts(User loggedUser, List<Shift> shifts) {
        List<UserShiftDTO> userShiftDTOList = new ArrayList<>();

        shifts.forEach(shift -> {
            UserShiftDTO userShiftDTO = new UserShiftDTO();
            userShiftDTO.setStartDate(shift.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            userShiftDTO.setEndDate(shift.getEndDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            userShiftDTO.setFirstname(loggedUser.getFirstname());
            userShiftDTO.setLastname(loggedUser.getLastname());
            userShiftDTOList.add(userShiftDTO);
        });

        return userShiftDTOList;
    }

    @Override
    public OperationStatus setUserShift(Integer userId, Integer shiftId) {
        User user = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);

        Shift shift = shiftRepository.findById(shiftId)
                .orElseThrow(EntityNotFoundException::new);

        OperationStatus opStatus =
                new OperationStatus(
                        Sentence.class.getSimpleName(),
                        DictOperationName.UPDATE.getCode()
                );

        user.getUserShiftSet().add(shift);
        opStatus.setRecordId(userId);
        userRepository.save(user);

        return opStatus.setSuccess(true);
    }
}
