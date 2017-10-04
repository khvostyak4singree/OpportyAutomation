package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Tools {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected void sleep(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
        }
    }


    public void openPage(String url, String title){
        try{
            driver.get(url);
            wait.until(ExpectedConditions.titleIs(title));
            assertEquals(driver.getCurrentUrl(), url);
            assertEquals(driver.getTitle(), title);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public  void getCurrentUrl(String element){
        try{
            assertTrue(driver.getCurrentUrl().contains(element));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public  void getCurrentTitle(String element){
        try{
            assertTrue(driver.getTitle().contains(element));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public  void waitForElementIsClickable(WebElement element){
        wait = new WebDriverWait(driver, 15);
        wait.until(elementToBeClickable(element));
    }

    public  void assertVisibility(WebElement element){
        try{
            assertTrue(element.isDisplayed());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}