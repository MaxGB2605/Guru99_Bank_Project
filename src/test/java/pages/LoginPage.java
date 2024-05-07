package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class LoginPage extends CommonMethods {

    @FindBy(xpath = "/html/body/form/table/tbody/tr[1]/td[2]/input")
    public WebElement userIDField;

    @FindBy(xpath = "/html/body/form/table/tbody/tr[2]/td[2]/input")
    public WebElement userPasswordField;

    @FindBy(xpath = "/html/body/form/table/tbody/tr[3]/td[2]/input[1]")
    public WebElement loginButton;
    @FindBy(xpath = "/html/body/form/table/tbody/tr[3]/td[2]/input[2]")
    public WebElement resetButton;

    @FindBy(xpath = "//*[@id=\"message23\"]")
    public WebElement IDFieldErrorMsg;


    public LoginPage(){
        PageFactory.initElements(driver, this);
    }
}
