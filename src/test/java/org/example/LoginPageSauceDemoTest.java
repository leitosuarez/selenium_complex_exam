package org.example;
import helpers.FileCsvScanner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.LoginPageSauceDemo;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;

public class LoginPageSauceDemoTest {
    private static final Logger logger = LoggerFactory.getLogger(LoginPageSauceDemoTest.class);

    private String browser;
    private LoginPageSauceDemo loginPage;
    private ArrayList<String> validUsers;

    @Before
     public void setUp(){
        logger.info("Setting browser and creating the connection with the page");

        browser = "edge";
        loginPage = new LoginPageSauceDemo(browser); //the page is opened in loginPageSauceDemo constructor

        FileCsvScanner scannerUsers = new FileCsvScanner();
        validUsers = scannerUsers.validUsernameScanner();

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
        Random random = new Random();
        int randomIndex = random.nextInt(validUsers.size());
        String randomValidUsername = validUsers.get(randomIndex);

        String errorMessage = loginPage.login(randomValidUsername,"")
                .getErrorMessage();

        int indexOfErrorMessage = errorMessage.indexOf("Password");
        String errorMessageToValidate = errorMessage.substring(indexOfErrorMessage);

        Assert.assertEquals("Password is required",errorMessageToValidate);
        logger.info("Success test case UC-2");


    }

    @After
    public void closePage(){
        logger.info("Disconnecting to the browser...");
        loginPage.close();
        logger.info("Browser closed");
    }





}
