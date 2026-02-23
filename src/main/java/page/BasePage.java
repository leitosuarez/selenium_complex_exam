package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage { //-> no se puede instanciar, solo heredan las clases hijas
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openPage(String url){
        driver.get(url);
    }

    public String getText(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public void setValueOnElement(By locator, String value){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(value);
    }

    public void click(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void clearInput(By element){
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        int inputLength = input.getAttribute("Value").length();
        for (int i = 0; i <= inputLength; i++) {
            input.sendKeys(Keys.BACK_SPACE);
        }
    }

    public String getTitle(){
        return driver.getTitle();
    }

}

