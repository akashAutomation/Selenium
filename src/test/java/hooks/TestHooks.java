package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import support.BaseController;
import utilities.ReportManager;

public class TestHooks extends BaseController {
    @Before
    public void setUp() {
        ReportManager manager = new ReportManager();
        //initDriver();
    }

    @After
    public void cleanUp() {
        //tearDown();
    }

}
