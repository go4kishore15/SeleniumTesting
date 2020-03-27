package com.example.demo.bdd;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features",
		glue = {"com.example.demo.bdd"},
		plugin = {"pretty", "json:target/cucumber-reports/Cucumber.json",
				        "junit:target/cucumber-reports/Cucumber.xml"}
		)

public class LoginUIRunner {

}
