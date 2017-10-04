package Pages;

import Utils.Tools;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginPage extends Tools {

        public LoginPage(WebDriver driver) {
            this.driver = driver;
            wait = new WebDriverWait(driver, 10);
            PageFactory.initElements(driver,this);
            System.out.println("LoginPage elements are initialized");

    }

    //Login user
    private String HOME_PAGE_URL="https://testing.opporty.com/";
    private String USER_EMAIL = "khvostyak+25@singree.com";
    private String USER_PASSWORD = "111111";

    //FB user
    public static final String FB_EMAIL ="boikiy@mail.ru";
    public static final String FB_PASS ="123scarys123";
    public static final String FB_FIRST_NAME="Михаил";

    //IN user
    public static final String IN_EMAIL ="khvostyak@singree.com";
    public static final String IN_PASS = "123scarys123";


    //Login user
    @FindBy(css = "input[name='email']")
    private  WebElement emailField;
    @FindBy(css = "input[name='password']")
    private  WebElement passwordField;
    @FindBy(css = "button[class='btn-react btn btn-default']")
    private WebElement submitButton;
    @FindBy(css = "i[class='fa fa-sign-out']")
    private  WebElement logoutButton;
    @FindBy(css = ".logIn")
    private WebElement signInButton;
    @FindBy(xpath = "//*[@id=\"basic-nav-dropdown\"]")
    private WebElement homePageDropDown;

    //FB User
    @FindBy(css = "div[class='social-facebook']")
    private WebElement buttonFB;
    @FindBy(id = "email")
    private WebElement emailFieldFB;
    @FindBy(id = "pass")
    private WebElement passFieldFB;
    @FindBy(id = "loginbutton")
    private WebElement loginButtonFB;

    //IN user
    @FindBy(css = "div[class='social-linkedin']")
    private WebElement buttonIN;
    @FindBy(css = "input[type='text']")
    private WebElement emailFieldIN;
    @FindBy(css = "input[type='password']")
    private WebElement passFieldIN;
    @FindBy(css = "input[type='submit']")
    private WebElement loginButtonIN;

    //User

   public void openLoginPage() {
       openPage(HOME_PAGE_URL);
       signInButton.click();
    }

    public  void loginUser() {
        emailField.sendKeys(USER_EMAIL);
        passwordField.sendKeys(USER_PASSWORD);
        waitForElementIsClickable(submitButton);
        submitButton.click();
        sleep(1);
    }

    public  void verifyLogin() {
            waitForElementIsClickable(homePageDropDown);
            homePageDropDown.click();
            assertVisibility(logoutButton);
            logoutButton.isDisplayed() ;
            logoutButton.click();
            assertVisibility(signInButton);
            assertTrue(signInButton.isDisplayed());
            sleep(1);
        }

    //User FB

    public  void openLoginPageFB() {
        openPage(HOME_PAGE_URL);
        signInButton.click();
        waitForElementIsClickable(buttonFB);
        buttonFB.click();
    }

    public  void loginUserFB() {
            emailFieldFB.sendKeys(FB_EMAIL);
            passFieldFB.sendKeys(FB_PASS);
            loginButtonFB.click();
            assertEquals(homePageDropDown.getText(), FB_FIRST_NAME);

    }

    public  void verifyUserFB() {
        assertVisibility(homePageDropDown);
        homePageDropDown.click();
        logoutButton.isDisplayed();
        logoutButton.click();
        assertVisibility(signInButton);
        assertTrue(signInButton.isDisplayed());
        }

    //User IN

    public  void openLoginPageIN(){
        openPage(HOME_PAGE_URL);
        signInButton.click();
        waitForElementIsClickable(buttonIN);
        buttonIN.click();

    }

    public  void loginUserIN(){
            emailFieldIN.sendKeys(IN_EMAIL);
            passFieldIN.sendKeys(IN_PASS);
            loginButtonIN.click();
    }

    public  void verifyUserIN() {
            assertVisibility(homePageDropDown);
            homePageDropDown.click();
            logoutButton.isDisplayed();
            logoutButton.click();
            assertVisibility(signInButton);
            assertTrue(signInButton.isDisplayed());
        }
    }
