package TestCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class ChromeConfig {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeTest
    protected void startChrome() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @AfterTest
    public void closeBrowser() {
        driver.quit();
    }
}


