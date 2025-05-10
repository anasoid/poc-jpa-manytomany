package org.anasoid.poc.jpa.mtm.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.anasoid.poc.jpa.mtm.IntegrationTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@IntegrationTest
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
