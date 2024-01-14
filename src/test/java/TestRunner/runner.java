package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.*;

@io.cucumber.testng.CucumberOptions(
        features = {"src/test/resources/Features"}
        , glue = {"StepDefs", "Pages", "api"}
        , tags = "@Regression"
        , monochrome = true
        , plugin = {"html:target/cucumber-reports/index.html"}
)
@Test
public class runner extends AbstractTestNGCucumberTests {

}
