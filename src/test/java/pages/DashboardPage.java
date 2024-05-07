package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class DashboardPage extends CommonMethods {
    @FindBy(xpath = "/html/body/table/tbody/tr/td/table/tbody/tr[3]/td")
    public WebElement welcomeMsg;

    public DashboardPage() {
        PageFactory.initElements(driver, this);
    }
}
