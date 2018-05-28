package com.abhishekd.restapi.repositories;

import com.abhishekd.restapi.domain.Category;
import io.swagger.annotations.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Category repository class for database interaction
 */
@Repository
@Api(tags = "Category Entity")
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Getting category based on the provided category name
     * @param categoryName
     * @return
     */
    Category findByCategoryName(String categoryName);
}
