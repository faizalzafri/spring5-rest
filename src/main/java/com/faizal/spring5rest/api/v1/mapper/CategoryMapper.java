package com.faizal.spring5rest.api.v1.mapper;

import com.faizal.spring5rest.api.v1.model.CategoryDTO;
import com.faizal.spring5rest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mappings({@Mapping(source = "id", target = "id"), @Mapping(source = "name", target = "name")})
    CategoryDTO categoryToCategoryDTO(Category category);

    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}
