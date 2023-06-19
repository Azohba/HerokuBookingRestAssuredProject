import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/features"},
        plugin = {"pretty", "json:target/reports/Cucumber.json", "html:target/reports/Cucumber.html"},
        glue = {"features/steps"},
        publish = true,
        monochrome = true
)
public class TestRunner {


}

