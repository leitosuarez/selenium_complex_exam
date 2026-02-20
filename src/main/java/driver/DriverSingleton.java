package driver;

import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

import java.time.Duration;

public class DriverSingleton {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverSingleton() {}

    public static void getDriver(String browser) {

        if (driver.get() == null) {
            WebDriver newDriver = DriverFactory.setBrowser(browser);
            driver.set(newDriver);

        }
        driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get();
    }

    public static WebDriver getDriver() {
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
