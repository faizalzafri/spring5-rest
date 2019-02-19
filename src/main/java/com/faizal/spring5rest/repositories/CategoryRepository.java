package com.faizal.spring5rest.repositories;

import com.faizal.spring5rest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
