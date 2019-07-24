import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.web.utils.ReportUtility;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

@KarateOptions(tags = "@Login")
public class TestRunner {

    @Test
    public void runTests()
    {
        Results results = Runner.parallel(getClass(), 5, "target/surefire-reports");
        ReportUtility.generateReport();
    }
}
