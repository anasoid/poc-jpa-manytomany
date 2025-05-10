package org.anasoid.poc.jpa.mtm.service;

import org.anasoid.poc.jpa.mtm.IntegrationTest;
import org.anasoid.poc.jpa.mtm.domain.Category;
import org.anasoid.poc.jpa.mtm.domain.Product;
import org.anasoid.poc.jpa.mtm.repository.CategoryRepository;
import org.anasoid.poc.jpa.mtm.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * Copyright 2023-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * @author : anasoid
 * Date :   5/10/25
 */
@IntegrationTest
@Transactional
class ProductServiceTestIT {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Transactional
    void assertDateUpdateNotChangeManyToManyChange() {
        Category cat1 = fakeCategory("cat1");
        Category cat2 = fakeCategory("cat2");
        Category cat3 = fakeCategory("cat3");
        categoryRepository.saveAllAndFlush(Arrays.asList(cat1, cat2, cat3));
        List<String> catCodes = Arrays.asList(cat1.getCode(), cat2.getCode(), cat3.getCode());
        assertThat(categoryRepository.findAll().stream().map(Category::getCode).toList()).containsExactlyElementsOf(catCodes);
        assertThat(cat1.getCreatedDate()).isNotNull();
        assertThat(cat1.getModifiedDate()).isNull();
        Product p1 = fakeProduct("p1");
        //new
        productRepository.saveAndFlush(p1);
        Product p1Result = productRepository.findById(p1.getId()).orElseThrow();
        assertThat(p1Result.getCode()).isEqualTo("p1");
        assertThat(p1Result.getCreatedDate()).isNotNull();
        assertThat(p1Result.getModifiedDate()).isNull();
        //Add category
        p1Result.addCatgories(cat1);
        productRepository.saveAndFlush(p1Result);
        p1Result = productRepository.findById(p1.getId()).orElseThrow();
        assertThat(p1Result.getModifiedDate()).isNotNull();
    }

    private Category fakeCategory(String code) {
        Category category = new Category();
        category.setCode(code);
        return category;
    }

    private Product fakeProduct(String code) {
        Product product = new Product();
        product.setCode(code);
        return product;
    }


}
