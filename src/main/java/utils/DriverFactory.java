package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Map;
import java.util.function.Supplier;

public class DriverFactory {

    public static WebDriver setBrowser(String browser) {

        if (browser == null){
            throw new IllegalArgumentException("Browser cannot be null!");
        }

        browser = browser.toLowerCase();
        Map<String, Supplier<WebDriver>> browserMap = Map.of(
                "edge", EdgeDriver::new,
                "firefox", FirefoxDriver::new
        );

        if (!browserMap.containsKey(browser)) {
            throw new IllegalArgumentException("The navegator especified {} is not supported: " + browser);
        }


        return browserMap.get(browser).get();
    }
}
