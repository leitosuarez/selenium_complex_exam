package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;

public class DriverFactory {
    private static final String[] avaiblesBrowsers = {"edge","firefox"};

    public static WebDriver setBrowser (String browser){
        browser = browser.toLowerCase();
        if (browser == null || browser.isEmpty() ){
            throw new IllegalArgumentException();
        }
        if (browser.equals(avaiblesBrowsers[0])){
            return new EdgeDriver();
        }
        if (browser.equals(avaiblesBrowsers[1])){
            return new FirefoxDriver();
        }
        return null;
    }
}
