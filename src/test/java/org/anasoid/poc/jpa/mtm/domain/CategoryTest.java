package org.anasoid.poc.jpa.mtm.domain;

import static org.anasoid.poc.jpa.mtm.domain.CategoryTestSamples.*;
import static org.anasoid.poc.jpa.mtm.domain.ProductTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.anasoid.poc.jpa.mtm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CategoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Category.class);
        Category category1 = getCategorySample1();
        Category category2 = new Category();
        assertThat(category1).isNotEqualTo(category2);

        category2.setId(category1.getId());
        assertThat(category1).isEqualTo(category2);

        category2 = getCategorySample2();
        assertThat(category1).isNotEqualTo(category2);
    }

    @Test
    void productsTest() {
        Category category = getCategoryRandomSampleGenerator();
        Product productBack = getProductRandomSampleGenerator();

        category.addProducts(productBack);
        assertThat(category.getProducts()).containsOnly(productBack);
        assertThat(productBack.getCatgories()).containsOnly(category);

        category.removeProducts(productBack);
        assertThat(category.getProducts()).doesNotContain(productBack);
        assertThat(productBack.getCatgories()).doesNotContain(category);

        category.products(new HashSet<>(Set.of(productBack)));
        assertThat(category.getProducts()).containsOnly(productBack);
        assertThat(productBack.getCatgories()).containsOnly(category);

        category.setProducts(new HashSet<>());
        assertThat(category.getProducts()).doesNotContain(productBack);
        assertThat(productBack.getCatgories()).doesNotContain(category);
    }
}
