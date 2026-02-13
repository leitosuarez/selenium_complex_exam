package runner;
//import org.junit.jupiter.api.parallel.Execution;
//import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import driver.DriverSingleton;
import utils.FileCsvScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page.LoginPageSauceDemo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;
import java.util.Random;
import static org.assertj.core.api.Assertions.assertThat;

//@Execution(ExecutionMode.SAME_THREAD)
public class LoginPageSauceDemoTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginPageSauceDemoTest.class);
    private static final String VALID_PASSWORD = "secret_sauce";
    private static final String LOGIN_URL = "https://www.saucedemo.com/";

    private WebDriver driver;
    private LoginPageSauceDemo loginPage;
    private String randomValidUsername;

    @BeforeEach
    public void setUp() {

        logger.info("Reading and saving username file values in a list");

        FileCsvScanner scannerUsers = new FileCsvScanner();
        ArrayList<String> validUsers = scannerUsers.validUsernameScanner();

        Random random = new Random();
        int randomIndex = random.nextInt(validUsers.size());
        randomValidUsername = validUsers.get(randomIndex);

        logger.info("User selected successfully");
    }

    @AfterEach
    public void closePage() {
        DriverSingleton.closeDriver();
    }


    @ParameterizedTest
    @ValueSource(strings = {"edge", "firefox"})
    public void loginWithEmptyCredentials(String browser) {

        driver = DriverSingleton.getDriver(browser);
        loginPage = new LoginPageSauceDemo(driver); //the page is opened in loginPageSauceDemo constructor
        loginPage.openPage(LOGIN_URL);

        String errorMessage = loginPage.login("", "")
                .getErrorMessage();

        assertThat(errorMessage).contains("Username is required");
    }


    @ParameterizedTest
    @ValueSource(strings = {"edge", "firefox"})
    public void loginWithCredentialsByPassingUsername(String browser) {

        driver = DriverSingleton.getDriver(browser);
        loginPage = new LoginPageSauceDemo(driver);
        loginPage.openPage(LOGIN_URL);

        String errorMessage = loginPage.login(randomValidUsername, "")
                .getErrorMessage();


        assertThat(errorMessage).contains("Password is required");
    }


    @ParameterizedTest
    @ValueSource(strings = {"edge", "firefox"})
    public void loginWithAcceptedCredentials(String browser) {

        driver = DriverSingleton.getDriver(browser);
        loginPage = new LoginPageSauceDemo(driver);
        loginPage.openPage(LOGIN_URL);

        String title = loginPage.login(randomValidUsername, VALID_PASSWORD)
                .getTitle();

        assertThat(title).isEqualTo("Swag Labs");
    }
}
