package Pages;

import Utils.Tools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends Tools{


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver,this);
        System.out.println("LoginPage elements are initialized");

    }

    //Registration USER
    public static final String HOME_PAGE_URL="https://testing.opporty.com/";

    public static final String FIRST_NAME = "Auto";
    public static final String LAST_NAME = "Test";
    public static final String EMAIL = "khvostyak+38@singree.com";
    public static final String PASSWORD = "111111";
    public static final String CONFIRMATION_PASSWORD = "111111";

    //USER
    @FindBy(name = "firstname") WebElement firstNameField;
    @FindBy(name= "lastname") WebElement lastNameField;
    @FindBy(name = "email") WebElement emailField;
    @FindBy(name = "password") WebElement passField;
    @FindBy(name = "repassword") WebElement confPassField;
    @FindBy(xpath = "//a[contains(text(), 'Individual')]") WebElement individualButton;
    @FindBy(css = "button[class='signIn']") WebElement signUpButton;
    @FindBy(css = "div.row:nth-child(9) > div:nth-child(1) > button:nth-child(1)") WebElement createButton;
    @FindBy(xpath = "//button[contains(text(), 'Ok')]") WebElement agreeRegistrationButton;

    //User


    public void openRegistrationPage() throws InterruptedException {
            openPage(HOME_PAGE_URL);
            getCurrentUrl(HOME_PAGE_URL);
            signUpButton.click();

    }


    public void inputRegistrationData() throws  InterruptedException {
            individualButton.click();
            emailField.sendKeys(EMAIL);
            passField.sendKeys(PASSWORD);
            confPassField.sendKeys(CONFIRMATION_PASSWORD);
            firstNameField.sendKeys(FIRST_NAME);
            lastNameField.sendKeys(LAST_NAME);
            createButton.click();
            Thread.sleep(2000);
            assertVisibility(agreeRegistrationButton);
            agreeRegistrationButton.click();

        }

    }