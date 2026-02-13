package org.example;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;
import utils.FileCsvScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import page.LoginPageSauceDemo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;
import java.util.Random;

public class LoginPageSauceDemoTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginPageSauceDemoTest.class);
    private static final String VALID_PASSWORD = "secret_sauce";

    //private WebDriver driver;
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
        //if (driver != null) {
            //driver.quit();
        //}
        if (loginPage != null){
            loginPage.close();
        }
    }

    // PARAMETRIZED TESTS

    @ParameterizedTest
    @ValueSource(strings = {"edge", "firefox"})
    public void loginWithEmptyCredentials(String browser) {

         //driver = DriverManager.getDriver(browser);
        //loginPage = new LoginPageSauceDemo(driver); //the page is opened in loginPageSauceDemo constructor
        loginPage = new LoginPageSauceDemo(browser);

        String errMessage = loginPage.login("", "")
                .getErrorMessage();

        int indexOfErrorMessage = errMessage.indexOf("Username");
        String errMessageToValidate = errMessage.substring(indexOfErrorMessage);

        Assertions.assertEquals("Username is required", errMessageToValidate);
    }


    @ParameterizedTest
    @ValueSource(strings = {"edge", "firefox"})
    public void loginWithCredentialsByPassingUsername(String browser) {

        //driver = DriverManager.getDriver(browser);
        //loginPage = new LoginPageSauceDemo(driver);
        loginPage = new LoginPageSauceDemo(browser);

        String errorMessage = loginPage.login(randomValidUsername, "")
                .getErrorMessage();

        int indexOfErrorMessage = errorMessage.indexOf("Password");
        String errorMessageToValidate = errorMessage.substring(indexOfErrorMessage);

        Assertions.assertEquals("Password is required", errorMessageToValidate);
    }


    @ParameterizedTest
    @ValueSource(strings = {"edge", "firefox"})
    public void loginWithAcceptedCredentials(String browser) {

        //driver = DriverManager.getDriver(browser);
        //loginPage = new LoginPageSauceDemo(driver);
        loginPage = new LoginPageSauceDemo(browser);

        String title = loginPage.login(randomValidUsername, VALID_PASSWORD)
                .getTitle();

        Assertions.assertEquals("Swag Labs", title);
    }
}
