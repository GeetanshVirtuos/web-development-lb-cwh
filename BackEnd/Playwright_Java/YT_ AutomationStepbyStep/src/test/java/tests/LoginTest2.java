// This file uses POM

package tests;

import base.BaseTest;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest2 extends BaseTest {

    @Test
    void test() {
        LoginPage loginPage = new LoginPage(page);
        HomePage homePage = new HomePage(page);

        page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        loginPage.addUsername("Admin");
        loginPage.addPassword("admin123");
        loginPage.clickLoginButton();
        homePage.clickTimeLink();
    }
}
