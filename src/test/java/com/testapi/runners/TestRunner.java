package com.testapi.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.testapi.stepdefs",
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",         // for HTML report
                "json:target/cucumber-report.json"           // optional: for JSON
        },
        monochrome = true
)

public class TestRunner {
}
