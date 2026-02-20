package setups;

import driver.DriverSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;


public class Hooks {
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
    @Before
    public void setUp() {
        logger.info("Starting test execution...");
        String browser = System.getProperty("browser", "edge");
        DriverSingleton.getDriver(browser);
        logger.info("Browser selected: {}", browser);
    }

    @After
    public void tearDown() {
        logger.info("Closing browser...");
        DriverSingleton.closeDriver();
    }

}
