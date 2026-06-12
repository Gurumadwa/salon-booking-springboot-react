package com.koushik.categoryservice.controller;


import com.koushik.categoryservice.model.Category;
import com.koushik.categoryservice.payload.dto.SalonDto;
import com.koushik.categoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories/salon-owner")
@RequiredArgsConstructor
public class SalonCategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        SalonDto salonDto = new SalonDto();
        salonDto.setId(1L);

        Category newCategory = categoryService.createCategory(category, salonDto);

        return ResponseEntity.ok(newCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId) throws Exception {
        SalonDto salonDto = new SalonDto();
        salonDto.setId(1L);

        categoryService.deleteCategoryById(categoryId, salonDto.getId());

        return ResponseEntity.ok("Category Deleted Successfully");
    }

}
