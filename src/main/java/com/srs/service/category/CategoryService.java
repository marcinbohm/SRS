package com.srs.service.category;

import com.srs.OperationStatus;
import com.srs.dto.CategoryDTO;
import com.srs.entity.Category;
import com.srs.repository.filters.CategoryFilter;

import java.time.LocalDateTime;
import java.util.List;

public interface CategoryService {

    CategoryDTO getCategoryById(Integer categoryId);

    List<CategoryDTO> getAllCategories();

    List<CategoryDTO> getCategoriesByFilter(CategoryFilter categoryFilter);

    OperationStatus upsert(Category category);

    OperationStatus delete(Integer categoryId);
}
