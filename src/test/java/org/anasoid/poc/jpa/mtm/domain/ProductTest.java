package org.anasoid.poc.jpa.mtm.domain;

import static org.anasoid.poc.jpa.mtm.domain.CategoryTestSamples.*;
import static org.anasoid.poc.jpa.mtm.domain.ProductTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.anasoid.poc.jpa.mtm.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Product.class);
        Product product1 = getProductSample1();
        Product product2 = new Product();
        assertThat(product1).isNotEqualTo(product2);

        product2.setId(product1.getId());
        assertThat(product1).isEqualTo(product2);

        product2 = getProductSample2();
        assertThat(product1).isNotEqualTo(product2);
    }

    @Test
    void catgoriesTest() {
        Product product = getProductRandomSampleGenerator();
        Category categoryBack = getCategoryRandomSampleGenerator();

        product.addCatgories(categoryBack);
        assertThat(product.getCatgories()).containsOnly(categoryBack);

        product.removeCatgories(categoryBack);
        assertThat(product.getCatgories()).doesNotContain(categoryBack);

        product.catgories(new HashSet<>(Set.of(categoryBack)));
        assertThat(product.getCatgories()).containsOnly(categoryBack);

        product.setCatgories(new HashSet<>());
        assertThat(product.getCatgories()).doesNotContain(categoryBack);
    }
}
