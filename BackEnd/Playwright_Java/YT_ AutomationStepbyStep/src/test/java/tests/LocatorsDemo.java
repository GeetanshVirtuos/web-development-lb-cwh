package tests;

import base.BaseTest;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

public class LocatorsDemo extends BaseTest {

    @Test
    public void testAllLocators(){
        page.navigate("https://trytestingthis.netlify.app/");

        //  First name using ID attribute
        page.locator("#fname").fill("John");

        //  Last name using name attribute
        page.locator("input[name='lname']").fill("Smith");

        //  Male radio button using value
        page.locator("input[value='male']").check();
        // Nth Element - select second radio manually
        page.locator("input[type='radio']").nth(1).check(); // Female

        //  Dropdown using ID  page.locator("select#option").selectOption("option 3");
        page.locator("#option").selectOption("option 2");

        //  Option 1 checkbox   page.locator("input[type='checkbox'][value='Option 1']").check();
        page.locator("input[name='option1']").check();
        page.getByLabel("Option 1").check();

        //  Checkbox using name
        page.locator("input[name='option1']").check(); // Option 1
        page.locator("input[name='option2']").check(); // Option 2

        //  XPath (avoid if possible, but sometimes needed)
        page.locator("//input[@type='date']").fill("2023-12-25");

        //  Use visible text of buttons or links
        page.getByText("Click Me").click();

        //  Role Selector (for accessibility)
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
    }
}
