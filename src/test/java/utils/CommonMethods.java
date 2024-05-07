package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class CommonMethods extends PageInitializer {
    public static WebDriver driver;

    public void openBrowserAndLaunchApplication() {
        switch (ConfigReader.read("browser")) {
            case "Chrome":
                driver = new ChromeDriver();
                break;

            case "Firefox":
                driver = new FirefoxDriver();
                break;

            case "Edge":
                driver = new EdgeDriver();
                break;
            case "Safari":
                driver = new SafariDriver();
                break;
            default:
                throw new RuntimeException("Invalid Browser");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(ConfigReader.read("url"));
        initializePageObject();
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void sendText(String text, WebElement element) {
        element.clear();
        element.sendKeys(text);
    }

    public void clearTextField(WebElement element) {
        element.clear();
    }

    public WebDriverWait getWait() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        return wait;
    }

    public void waitForElementToBeClickable(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }

    public JavascriptExecutor getJSExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }


    public void jsClick(WebElement element) {
        getJSExecutor().executeScript("arguments[0].click()", element);
    }

    public String getTimeStamp(String pattern) {
        Date date = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public byte[] takeScreenshot(String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] picByte = ts.getScreenshotAs(OutputType.BYTES);
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(sourceFile, new File(Constants.SCREENSHOT_FILEPATH + fileName + " " + getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));

        }catch (IOException e){
            e.printStackTrace();
        }
        return picByte;
    }

    // to handle alerts
    public static void handleAlert(WebDriver driver, String action) {
        try {
            // Wait for the alert to be present
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            // Perform the specified action on the alert
            switch (action.toLowerCase()) {
                case "accept":
                    alert.accept();
                    break;
                case "dismiss":
                    alert.dismiss();
                    break;
                case "gettext":
                    String alertText = alert.getText();
                    System.out.println("Alert text: " + alertText);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid action: " + action);
            }
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present.");
        } catch (TimeoutException e) {
            System.out.println("Timed out waiting for alert to be present.");
        }
    }

}
