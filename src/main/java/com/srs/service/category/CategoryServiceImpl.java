package com.srs.service.category;

import com.srs.OperationStatus;
import com.srs.dict.DictOperationName;
import com.srs.dto.CategoryDTO;
import com.srs.entity.Application;
import com.srs.entity.Category;
import com.srs.entity.Category;
import com.srs.mapper.SmartMapper;
import com.srs.repository.CategoryRepository;
import com.srs.repository.filters.CategoryFilter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDTO getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId)
                .map(category -> this.modelMapper.map(category, CategoryDTO.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> this.modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getCategoriesByFilter(CategoryFilter categoryFilter) {
        return null;
    }

    @Override
    public OperationStatus upsert(Category category) {
        boolean adding = (category.getCategoryId() == null);

        Category categoryExisting =
                (adding ? new Category() : categoryRepository.findById(category.getCategoryId())
                        .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono obiektu z Id: " + category.getCategoryId())));

        OperationStatus opStatus =
                new OperationStatus(Category.class.getSimpleName(),
                        adding ? DictOperationName.ADD.getCode()
                                : DictOperationName.UPDATE.getCode());

        SmartMapper.transferData(category, categoryExisting);

        Category categorySaved =
                categoryRepository.save(category);
        Integer id = categorySaved.getCategoryId();
        opStatus.setRecordId(id).setSuccess(id != null);
        return opStatus;
    }

    @Override
    public OperationStatus delete(Integer categoryId) {
        
        Category category =
                categoryRepository
                        .findById(categoryId)
                        .orElseThrow(EntityNotFoundException::new);

        OperationStatus opStatus =
                new OperationStatus(
                        Category.class.getSimpleName(),
                        DictOperationName.DELETE.getCode()
                );

        opStatus.setRecordId(categoryId);
        categoryRepository.delete(category);

        return opStatus.setSuccess(!categoryRepository.existsById(categoryId));
    }
}
