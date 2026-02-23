package page;

import org.openqa.selenium.*;

public class LoginPage extends BasePage {
    private final By username = By.xpath("//input[@id='user-name']");
    private final By password = By.xpath("//input[@id='password']");
    private final By loginButton = By.xpath("//input[@id='login-button']");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void clearUsername(){
        clearInput(this.username);
    }

    public void clearPassword(){
        clearInput(this.password);
    }

    public void setUsername(String username){
        setValueOnElement(this.username,username);
    }

    public void setPassword(String password){
        setValueOnElement(this.password,password);
    }


    public LoginPage clickLogin(){
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
