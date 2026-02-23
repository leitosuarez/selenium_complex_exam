package steps;

import driver.DriverSingleton;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.assertj.core.api.Assertions.assertThat;
import org.openqa.selenium.WebDriver;
import page.LoginPage;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private static final String LOGIN_URL = "https://www.saucedemo.com/";

    @Given("The user enters on the login page")
    public void theUserEntersOnTheLoginPage() {
        driver = DriverSingleton.getDriver();
        loginPage = new LoginPage(driver);
        loginPage.openPage(LOGIN_URL);
    }

    @When("I enter {string} in the username field")
    public void iEnterInTheUsernameField(String arg0) {
        loginPage.setUsername(arg0);
    }

    @And("I enter {string} in the password field")
    public void iEnterInThePasswordField(String arg0) {
        loginPage.setPassword(arg0);
    }

    @And("I clear the username field")
    public void iClearTheUsernameField() {
       loginPage.clearUsername();
    }

    @And("I clear the password field")
    public void iClearThePasswordField() {
        loginPage.clearPassword();
    }

    @And("I click the login button")
    public void iClickTheLoginButton() {
        loginPage.clickLogin();
    }

    @Then("I see an error message {string}")
    public void iSeeAnErrorMessage(String arg0) {
        String errMessage = loginPage.getErrorMessage();
        assertThat(errMessage).isEqualTo(arg0);
    }


}
