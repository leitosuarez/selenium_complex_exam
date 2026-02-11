package org.example;
import helpers.FileCsvScanner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.LoginPageSauceDemo;

import java.util.ArrayList;
import java.util.Random;

public class LoginPageSauceDemoTest {
    private static final Logger logger = LoggerFactory.getLogger(LoginPageSauceDemoTest.class);
    private static final String VALID_PASSWORD = "secret_sauce";
    private LoginPageSauceDemo loginPage;
    private String randomValidUsername;

    @Before
     public void setUp(){
        logger.info("Setting browser and creating the connection with the page");
        String browser = "edge";
        loginPage = new LoginPageSauceDemo(browser); //the page is opened in loginPageSauceDemo constructor

        logger.info("reading and saving username file values in a list");
        FileCsvScanner scannerUsers = new FileCsvScanner();
        ArrayList<String> validUsers = scannerUsers.validUsernameScanner();

        logger.info("selecting and adding random users for testing");
        Random random = new Random();
        int randomIndex = random.nextInt(validUsers.size());
        randomValidUsername = validUsers.get(randomIndex);

        logger.info("The initial settings were done successfully");

    }

    @Test
    public void loginWithEmptyCredentials(){
        logger.info("Test case UC-1 Test Login form with empty credentials:");
        String errMessage = loginPage.login("","")
                .getErrorMessage();

        int indexOfErrorMessage = errMessage.indexOf("Username");
        String errMessageToValidate = errMessage.substring(indexOfErrorMessage);

        Assert.assertEquals("Username is required",errMessageToValidate);
        logger.info("Success test case UC-1");
    }

    @Test
    public void loginWithCredentialsByPassingUsername(){
        logger.info("Test case UC-2 Test Login form with credentials by passing Username:");

        String errorMessage = loginPage.login(randomValidUsername,"")
                .getErrorMessage();

        int indexOfErrorMessage = errorMessage.indexOf("Password");
        String errorMessageToValidate = errorMessage.substring(indexOfErrorMessage);

        Assert.assertEquals("Password is required",errorMessageToValidate);
        logger.info("Success test case UC-2");


    }
    @Test
    public void loginWithAcceptedCredentialsByPassingUsernameAndPassword(){
        logger.info("Test case UC-3 Test Login form with credentials by passing Username & Password:\n" +
                "\n" + "Type credentials in username which are under Accepted username are sections.");

        String title = loginPage.login(randomValidUsername,VALID_PASSWORD)
                .getTitle();

        Assert.assertEquals("Swag Labs",title);
        logger.info("Success test case UC-3");


    }

    @After
    public void closePage(){
        logger.info("Disconnecting to the browser...");
        loginPage.close();
        logger.info("Browser closed");
    }



}
