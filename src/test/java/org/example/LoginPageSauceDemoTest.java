package org.example;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.LoginPageSauceDemo;

public class LoginPageSauceDemoTest {
    private static final Logger logger = LoggerFactory.getLogger(LoginPageSauceDemoTest.class);

    private String browser;
    private LoginPageSauceDemo loginPage;

    @Before
     public void setUp(){
        logger.info("Setting browser and creating the connection with the page");
        browser = "edge";
        loginPage = new LoginPageSauceDemo(browser); //the page is opened in loginPageSauceDemo constructor
        logger.info("The initial settings were done successfully");

    }

    @Test
    public void loginWithEmptyCredentials(){
        logger.info("Test case UC-1 Test Login form with empty credentials:");
        String errMessage = loginPage.login("","")
                .getErrorMessage();

        Assert.assertEquals("Epic sadface: Username is required",errMessage);
        logger.info("Success test case");
    }

    @After
    public void closePage(){
        logger.info("Disconnecting to the browser...");
        loginPage.close();
        logger.info("Browser closed");
    }





}
