package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.DashboardPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;
import utils.ExcelReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LoginSteps extends CommonMethods {

    @Given("user is navigated to web application")
    public void user_is_navigated_to_web_application() {
        openBrowserAndLaunchApplication();

    }

    @When("user enters valid userId and password")
    public void user_enters_valid_user_id_and_password() {

        sendText(ConfigReader.read("userName"), loginPage.userIDField);
        sendText(ConfigReader.read("password"), loginPage.userPasswordField);
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        click(loginPage.loginButton);
    }

    @Then("user is successfully logged in")
    public void user_is_successfully_logged_in() {
        Assert.assertTrue(dashboardPage.welcomeMsg.isDisplayed());
        String currentTitle = driver.getTitle();
        System.out.println(currentTitle);
    }

    //invalid credentials
    @When("user enters invalid credentials from Excel file")
    public void user_enters_invalid_credentials_from_excel_file() throws IOException, InterruptedException {
        List<Map<String, String>> loginData = ExcelReader.read();
        System.out.println("exel" + loginData);
        for (Map<String, String> invalidCredential : loginData) {
            sendText(invalidCredential.get("UserID"), loginPage.userIDField);
            sendText(invalidCredential.get("Password"),loginPage.userPasswordField);
            click(loginPage.loginButton);
            handleAlert(driver,"dismiss");
        }

    }

    @Then("user gets the error message")
    public void user_gets_the_error_message() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
