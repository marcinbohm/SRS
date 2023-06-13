package com.srs.service.user;

import com.srs.OperationStatus;
import com.srs.dto.UserDTO;
import com.srs.dto.UserShiftDTO;
import com.srs.entity.User;
import com.srs.repository.filters.UserFilter;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserById(Integer userId);

    Optional<User> findUserByLogin(String username);

    Optional<User> findUserByIdAndRefreshTicket(Integer userId, String refreshToken);

    <S extends User> S save(S user);

    void delete(User user);

    UserDTO getUserById(Integer userId);

    OperationStatus delete(Integer userId);

    List<UserDTO> getAllUsers();

    List<UserDTO> getUsersByFilter(UserFilter userFilter);

    OperationStatus upsert(User user);

    List<UserShiftDTO> getLoggedUserShifts();

    OperationStatus setUserShift(Integer userId, Integer shiftId);
}
