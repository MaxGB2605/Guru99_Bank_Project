import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginFunctionalityTest {
    public static void main(String[] args) {

        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();

        driver.get("http://www.demo.guru99.com/V4/");

        WebElement userId = driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td[2]/input"));
        userId.sendKeys("mngr570101");

        WebElement password = driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td[2]/input"));
        password.sendKeys("nYhEjuq");

        WebElement loginButton = driver.findElement(By.xpath("/html/body/form/table/tbody/tr[3]/td[2]/input[1]"));
        loginButton.click();
    }
}
