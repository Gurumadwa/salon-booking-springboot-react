package com.koushik.categoryservice.service.impl;

import com.koushik.categoryservice.model.Category;
import com.koushik.categoryservice.payload.dto.SalonDto;
import com.koushik.categoryservice.repository.CategoryRepository;
import com.koushik.categoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category, SalonDto salonDto) {
        Category newCategory = new Category();

        newCategory.setName(category.getName());
        newCategory.setImage(category.getImage());
        newCategory.setSalonId(category.getSalonId());

        return categoryRepository.save(newCategory);
    }

    @Override
    public Set<Category> getAllCategoryBySalon(Long id) {
        return categoryRepository.findBySalonId(id);
    }

    @Override
    public Category getCategoryById(Long id) throws Exception {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category == null){
            throw new Exception("category does not exist");
        }
        return category;
    }

    @Override
    public void deleteCategoryById(Long id, Long salonId) throws Exception {
        Category categoryById = getCategoryById(id);
        if(!categoryById.getSalonId().equals(salonId)){
            throw new Exception("Not Authorized to delete category");
        }

        categoryRepository.deleteById(id);
    }
}
