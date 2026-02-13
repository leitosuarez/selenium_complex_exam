package utils;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static WebDriver driver;

    private DriverManager(){

    }

    public static WebDriver getDriver(String browser){
        if (driver == null){
            driver = DriverFactory.setBrowser(browser);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quiteDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}
