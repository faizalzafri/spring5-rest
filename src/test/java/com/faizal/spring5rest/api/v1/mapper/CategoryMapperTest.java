package com.faizal.spring5rest.api.v1.mapper;

import com.faizal.spring5rest.api.v1.model.CategoryDTO;
import com.faizal.spring5rest.domain.Category;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryMapperTest {

    public static final String NAME = "TEST_CATEGORY";
    public static final long ID = 1L;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() {

        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertEquals(Long.valueOf(ID), categoryDTO.getId());
        assertEquals(String.valueOf(NAME), categoryDTO.getName());

    }
}
