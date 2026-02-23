package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InventoryPage extends BasePage{
    private final By pageTitle = By.xpath("//div[@class='app_logo']");

    public InventoryPage(WebDriver driver){
        super(driver);
    }

    public String getTitle(){
        WebElement pageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(this.pageTitle));
        return pageTitle.getText();
    }
}
