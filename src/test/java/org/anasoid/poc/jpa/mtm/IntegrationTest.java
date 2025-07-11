package org.anasoid.poc.jpa.mtm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.anasoid.poc.jpa.mtm.config.AsyncSyncConfiguration;
import org.anasoid.poc.jpa.mtm.config.EmbeddedSQL;
import org.anasoid.poc.jpa.mtm.config.JacksonConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = { PocJpaManytomanyApp.class, JacksonConfiguration.class, AsyncSyncConfiguration.class })
@EmbeddedSQL
public @interface IntegrationTest {
}
