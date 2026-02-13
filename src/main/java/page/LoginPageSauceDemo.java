package page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class LoginPageSauceDemo extends BasePage {
    private static final String LOGIN_URL = "https://www.saucedemo.com/";
    private final By username = By.xpath("//input[@id='user-name']");
    private final By password = By.xpath("//input[@id='password']");
    private final By loginButton = By.xpath("//input[@id='login-button']");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");

    public LoginPageSauceDemo(String browser){
        super(browser);
        openPage(LOGIN_URL);
    }

    public LoginPageSauceDemo login(String username, String password){
        setValueOnElement(this.username,username);
        setValueOnElement(this.password,password);
        click(loginButton);
        return this;

    }

    public String getErrorMessage() throws TimeoutException{
        try {
            return getText(errorMessage);

        } catch (TimeoutException e) {
            return "";

        }
    }


}
