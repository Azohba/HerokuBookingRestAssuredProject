import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/features"},
        plugin = {"json:target/reports/Cucumber.json"},
        glue = {"features/steps"},
        publish = true
)
public class TestRunner {
    @AfterClass
    public static void finalSequence() {

    }

}

