package com.faizal.spring5rest.services;

import com.faizal.spring5rest.api.v1.mapper.CategoryMapper;
import com.faizal.spring5rest.api.v1.model.CategoryDTO;
import com.faizal.spring5rest.domain.Category;
import com.faizal.spring5rest.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    private static final String NAME = "TEST_CATEGORY";
    private static final long ID = 1L;

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    public void getAllCategories() throws Exception {

        //given
        List<Category> categoryList = Arrays.asList(new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categoryList);

        //when
        List<CategoryDTO> returnList = categoryService.getAllCategories();

        //then
        assertEquals(categoryList.size(), returnList.size());
    }

    @Test
    public void getCategoryByName() throws Exception {

        //given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        //when
        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        //then
        assertEquals(Long.valueOf(ID), categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());

    }
}
