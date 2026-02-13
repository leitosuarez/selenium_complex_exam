package driver;

import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

public class DriverSingleton {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverSingleton() {}

    public static WebDriver getDriver(String browser) {

        if (driver.get() == null) {
            WebDriver newDriver = DriverFactory.setBrowser(browser);
            driver.set(newDriver);
        }

        return driver.get();
    }

    public static void closeDriver() {

        WebDriver currentDriver = driver.get();

        if (currentDriver != null) {
            currentDriver.quit();
            driver.remove();
        }
    }
}
