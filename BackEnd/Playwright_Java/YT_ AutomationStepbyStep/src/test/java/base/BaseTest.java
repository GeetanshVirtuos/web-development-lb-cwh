package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ExtentManager;
import java.lang.reflect.Method;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected ExtentReports extent;
    protected ExtentTest test;

    //This annotation comes from TestNG and it means this is a "Before" method. So, this method will run before running any test
    //with @Test annotation.
    @BeforeMethod
    public void setUp(Method method){
        // Reporting
        extent = ExtentManager.getInstance();
        test = extent.createTest(method.getName());

        // Playwright setup
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        page = browser.newPage();
        page.setDefaultTimeout(4000); // Ideally it should be 30 secs but I am keeping things fast for
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        // Reporting
        if(result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
        } else if(result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        } else {
            test.skip("Test Skipped");
        }

        extent.flush(); // This will finally put everything in the "test-output/ExtentReport.html" file

        // Browser cleanup
        if(browser != null) browser.close();
        if(playwright != null) playwright.close();
    }
}


