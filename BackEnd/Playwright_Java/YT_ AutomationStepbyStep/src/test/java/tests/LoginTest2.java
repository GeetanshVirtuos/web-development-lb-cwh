// This file uses POM

package tests;

import base.BaseTest;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.SkipException;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest2 extends BaseTest {

    @Test
    void loginTest1() {
        LoginPage loginPage = new LoginPage(page);
        HomePage homePage = new HomePage(page);

        test.info("Navigating to Login Page");
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        test.info("Adding Username");
        loginPage.addUsername("Admin");

        test.info("Adding Password");
        loginPage.addPassword("admin123");

        test.info("Clicking login button");
        loginPage.clickLoginButton();

        test.info("checking homepage");
        homePage.clickTimeLink();

        test.info("All steps completed");
    }

//    @Test (enabled = false) //This line does NOT mean skip, this means TestNG would completely ignore this test. So it's like this test does not exist at all.
    @Test
    public void loginTest2() {
        test.skip("Skipping this test");
        throw new SkipException("Skipping this test");
    }

    // This Test is designed to fail to trigger a screenshot in the report
    @Test
    void loginTest3() {
        LoginPage loginPage = new LoginPage(page);
        HomePage homePage = new HomePage(page);

        test.info("Navigating to Login Page");
        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        test.info("Adding Username");
        loginPage.addUsername("Admin");

        test.info("Adding Password");
        loginPage.addPassword("WRONG PASSWORD"); // This will trigger the FAIL

        test.info("Clicking login button");
        loginPage.clickLoginButton();

        test.info("checking homepage");
        homePage.clickTimeLink();

        test.info("All steps completed");
    }
}
