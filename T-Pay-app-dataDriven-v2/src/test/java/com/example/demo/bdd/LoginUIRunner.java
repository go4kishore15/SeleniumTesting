package com.example.demo.bdd;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", 
                                  glue = { "com.example.demo.bdd" }, 
                                  tags = {"@sanity" }, 
                                  plugin= {"pretty","html:target/cucumber"},
                                  monochrome = true
//                                  dryRun = true
)

public class LoginUIRunner {

}
