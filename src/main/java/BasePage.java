import helpers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Hello world!
 *
 */
public class BasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public BasePage(String browser){
        this.driver = DriverFactory.setBrowser(browser);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }



}

