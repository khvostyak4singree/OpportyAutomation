package Pages;

        import Utils.Tools;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;
        import org.openqa.selenium.support.PageFactory;
        import org.openqa.selenium.support.ui.ExpectedConditions;

        import static org.testng.Assert.assertEquals;
        import static org.testng.Assert.assertTrue;

public class LoginPage extends Tools {

        public LoginPage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver,this);
            System.out.println("LoginPage elements are initialized");

    }

    //Login user
    private String HOME_PAGE_URL="https://testing.opporty.com";
    private String USER_EMAIL = "khvostyak+25@singree.com";
    private String USER_PASSWORD = "111111";

    //FB user
    public static final String FB_EMAIL ="boikiy@mail.ru";
    public static final String FB_PASS ="123scarys123";
    public static final String FB_FIRST_NAME="Михаил";
    public static final String FB_PAGE_TITLE="Log into Facebook | Facebook";

    //IN user
    public static final String IN_EMAIL ="khvostyak@singree.com";
    public static final String IN_PASS = "123scarys123";
    public static final String IN_PAGE_TITLE="Authorize | LinkedIn";


    //Login user
    @FindBy(css = "input[name='email']")
    private  WebElement emailField;
    @FindBy(css = "input[name='password']")
    private  WebElement passwordField;
    @FindBy(css = "button[class='btn-react btn btn-default']")
    private WebElement submitButton;
    @FindBy(css = "#basic-nav-dropdown")
    private WebElement accountDropDown;
    @FindBy(css = "i[class='fa fa-sign-out']")
    private  WebElement logoutButton;
    @FindBy(css = "button[class='logIn']")
    private WebElement signInButton;

    //FB User
    @FindBy(css = "div[class='social-facebook']") WebElement buttonFB;
    @FindBy(id = "email") WebElement emailFieldFB;
    @FindBy(id = "pass") WebElement passFieldFB;
    @FindBy(id = "loginbutton") WebElement loginButtonFB;

    //IN user
    @FindBy(css = "div[class='social-linkedin']") WebElement buttonIN;
    @FindBy(css = "input[type='text']") WebElement emailFieldIN;
    @FindBy(css = "input[type='password']") WebElement passFieldIN;
    @FindBy(css = "input[type='submit']") WebElement loginButtonIN;

    //User

   // @Step("Open Page")
   public void openLoginPage() throws InterruptedException {
       driver.navigate().to(HOME_PAGE_URL);
       Thread.sleep(5000);
       waitForElementIsClickable(signInButton);
       signInButton.click();
    }

   // @Step(" Login user")
    public  void loginUser() {
        emailField.sendKeys(USER_EMAIL);
        passwordField.sendKeys(USER_PASSWORD);
        waitForElementIsClickable(submitButton);
        submitButton.click();
    }

   // @Step("Verify Login")
    public  void verifyLogin(){
            assertVisibility( accountDropDown);
            waitForElementIsClickable(accountDropDown);
            accountDropDown.click();
            assertVisibility(logoutButton);
            logoutButton.isDisplayed() ;
            logoutButton.click();
            assertVisibility(signInButton);
            assertTrue(signInButton.isDisplayed());
        }



    //User FB

   // @Step("Open Page FB")
    public  void openLoginPageFB() throws InterruptedException {
        driver.navigate().to(HOME_PAGE_URL);
        Thread.sleep(3000);
        waitForElementIsClickable(signInButton);
        signInButton.click();
        waitForElementIsClickable(buttonFB);
        buttonFB.click();
        wait.until(ExpectedConditions.titleIs(FB_PAGE_TITLE));
        getCurrentTitle(FB_PAGE_TITLE);
    }

  //  @Step("Login User FB")
    public  void loginUserFB() throws InterruptedException{
            emailFieldFB.sendKeys(FB_EMAIL);
            passFieldFB.sendKeys(FB_PASS);
            loginButtonFB.click();
            assertEquals(accountDropDown.getText(), FB_FIRST_NAME);

    }

  //  @Step("Verify User FB")
    public  void verifyUserFB()throws InterruptedException {
            assertVisibility(accountDropDown);
            accountDropDown.click();
            assertVisibility(logoutButton);
            logoutButton.isDisplayed();
            logoutButton.click();
            assertVisibility(signInButton);
            assertTrue(signInButton.isDisplayed());
        }



    //User IN

   // @Step("Open Page IN")
    public  void openLoginPageIN() throws InterruptedException {
        driver.navigate().to(HOME_PAGE_URL);
        Thread.sleep(3000);
        waitForElementIsClickable(signInButton);
        signInButton.click();
        waitForElementIsClickable(buttonIN);
        buttonIN.click();
        wait.until(ExpectedConditions.titleIs(IN_PAGE_TITLE));
        getCurrentTitle(IN_PAGE_TITLE);

    }

   // @Step("Login User IN")
    public  void loginUserIN() throws InterruptedException{
            emailFieldIN.sendKeys(IN_EMAIL);
            passFieldIN.sendKeys(IN_PASS);
            loginButtonIN.click();
    }

  //  @Step("Verify User IN")
    public  void verifyUserIN() throws InterruptedException {
            Thread.sleep(1000);
            assertVisibility(accountDropDown);
            accountDropDown.click();
            logoutButton.isDisplayed();
            logoutButton.click();
            assertVisibility(signInButton);
            assertTrue(signInButton.isDisplayed());
        }
    }
