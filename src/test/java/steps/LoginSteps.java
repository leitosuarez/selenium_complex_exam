package steps;

import driver.DriverSingleton;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private static final String LOGIN_URL = "https://www.saucedemo.com/";

    @Given("the user is on the login page")
    public void openLoginPage() {

        driver = DriverSingleton.getDriver(); // obtenés la instancia creada en Hooks
        loginPage = new LoginPage(driver);    // ahora sí lo pasás correctamente
        loginPage.openPage(LOGIN_URL);
    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsIn(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("the expected result should be {string}")
    public void iShouldSee(String expectedResult) {
        String title = driver.getTitle();

        if (driver.getCurrentUrl().contains("inventory")) {
            assertThat(title).isEqualTo(expectedResult);
        } else {
            String errorMessage = loginPage.getErrorMessage();
            assertThat(errorMessage).contains(expectedResult);
        }
    }
}
