package driver;

import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

public class DriverSingleton {
    private static WebDriver driver;
    //private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverSingleton(){}

    public static WebDriver getDriver(String browser){
        if (driver == null){
            //WebDriver newDriver = DriverFactory.setBrowser(browser);
            //driver.set(newDriver);
            driver = DriverFactory.setBrowser(browser);
        }
        //return driver.get();
        return driver;
    }

    public static void closeDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}
