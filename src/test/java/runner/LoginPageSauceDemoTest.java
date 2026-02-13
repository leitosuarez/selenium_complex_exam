package runner;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
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

@Execution(ExecutionMode.SAME_THREAD)
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
        //if (loginPage != null){
         //   loginPage.closePage();
        //}
        DriverSingleton.closeDriver();

    }


    @ParameterizedTest
    @ValueSource(strings = {"edge", "firefox"})
    public void loginWithEmptyCredentials(String browser) {

        //WebDriver driver = DriverSingleton.getDriver(browser);
        driver = DriverSingleton.getDriver(browser);
        loginPage = new LoginPageSauceDemo(driver); //the page is opened in loginPageSauceDemo constructor
        loginPage.openPage(LOGIN_URL);

        String errMessage = loginPage.login("", "")
                .getErrorMessage();

        boolean isMessageShown = errMessage.contains("Username is required");
        Assertions.assertTrue(isMessageShown);
    }


    @ParameterizedTest
    @ValueSource(strings = {"edge", "firefox"})
    public void loginWithCredentialsByPassingUsername(String browser) {

        //WebDriver driver = DriverSingleton.getDriver(browser);
        driver = DriverSingleton.getDriver(browser);
        loginPage = new LoginPageSauceDemo(driver);
        loginPage.openPage(LOGIN_URL);

        String errorMessage = loginPage.login(randomValidUsername, "")
                .getErrorMessage();


        boolean isMessageShown = errorMessage.contains("Password is required");
        Assertions.assertTrue(isMessageShown);
    }


    @ParameterizedTest
    @ValueSource(strings = {"edge", "firefox"})
    public void loginWithAcceptedCredentials(String browser) {

        //WebDriver driver = DriverSingleton.getDriver(browser);
        driver = DriverSingleton.getDriver(browser);
        loginPage = new LoginPageSauceDemo(driver);
        loginPage.openPage(LOGIN_URL);

        String title = loginPage.login(randomValidUsername, VALID_PASSWORD)
                .getTitle();

        Assertions.assertEquals("Swag Labs", title);
    }
}
