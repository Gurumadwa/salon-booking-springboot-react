package com.koushik.categoryservice.service;

import com.koushik.categoryservice.model.Category;
import com.koushik.categoryservice.payload.dto.SalonDto;

import java.util.Set;

public interface CategoryService {
    Category createCategory(Category category, SalonDto salonDto);
    Set<Category> getAllCategoryBySalon(Long id);
    Category getCategoryById(Long id) throws Exception;
    void deleteCategoryById(Long id, Long salonId) throws Exception;
}
