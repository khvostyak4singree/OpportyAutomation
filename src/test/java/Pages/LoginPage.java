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
    public static final String HOME_PAGE_URL="https://testing.opporty.com";
    public static final String HOME_PAGE_TITLE = "Requests for quotes, proposals, and offers of services and goods on Opporty.";
    public static final String USER_EMAIL = "khvostyak+25@singree.com";
    public static final String USER_PASSWORD = "111111";
    public static final String USER_ACCOUNT="https://testing.opporty.com/account";

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
    @FindBy(css = "input[name='email']") WebElement emailField;
    @FindBy(css = "input[name='password']")WebElement passwordField;
    @FindBy(css = "button[class='btn-react btn btn-default']") WebElement submitButton;
    @FindBy(css = "#basic-nav-dropdown") WebElement accountDropDown;
    @FindBy(css = "i[class='fa fa-sign-out']")WebElement logoutButton;
    @FindBy(css = ".logIn") WebElement signInButton;

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
    public  void openLoginPage(){
        openPage(HOME_PAGE_URL, HOME_PAGE_TITLE);
        signInButton.click();
    }

   // @Step(" Login user")
    public  void loginUser() throws InterruptedException {
        try {
            emailField.sendKeys(USER_EMAIL);
            passwordField.sendKeys(USER_PASSWORD);
            waitForElementIsClickable(submitButton);
            submitButton.click();
            Thread.sleep(1000);
            getCurrentUrl(USER_ACCOUNT);
            Thread.sleep(1000);
        } catch (Exception | AssertionError e) {
            e.printStackTrace();
        }
    }

   // @Step("Verify Login")
    public  void verifyLogin() throws InterruptedException {
        try {
            assertVisibility( accountDropDown);
            accountDropDown.click();
            assertVisibility(logoutButton);
        }catch (Exception | AssertionError e){
            e.printStackTrace();
        }finally {
            if (logoutButton.isDisplayed()) ;
            logoutButton.click();
            assertVisibility(signInButton);
            assertTrue(signInButton.isDisplayed());
        }
    }


    //User FB

   // @Step("Open Page FB")
    public  void openLoginPageFB() throws InterruptedException {
        openPage(HOME_PAGE_URL, HOME_PAGE_TITLE);
        signInButton.click();
        waitForElementIsClickable(buttonFB);
        buttonFB.click();
        wait.until(ExpectedConditions.titleIs(FB_PAGE_TITLE));
        getCurrentTitle(FB_PAGE_TITLE);
    }

  //  @Step("Login User FB")
    public  void loginUserFB() throws InterruptedException{
        try {
            emailFieldFB.sendKeys(FB_EMAIL);
            passFieldFB.sendKeys(FB_PASS);
            loginButtonFB.click();
            assertEquals(accountDropDown.getText(), FB_FIRST_NAME);
        } catch (Exception |AssertionError e){
            e.printStackTrace();
        }
    }

  //  @Step("Verify User FB")
    public  void verifyUserFB()throws InterruptedException {
        try {
            assertVisibility(accountDropDown);
            accountDropDown.click();
            assertVisibility(logoutButton);
        }catch (Exception | AssertionError e){
            e.printStackTrace();
        }finally {
            if (logoutButton.isDisplayed());
            logoutButton.click();
            assertVisibility(signInButton);
            assertTrue(signInButton.isDisplayed());
        }
    }


    //User IN

   // @Step("Open Page IN")
    public  void openLoginPageIN() throws InterruptedException {
        openPage(HOME_PAGE_URL, HOME_PAGE_TITLE);
        signInButton.click();
        waitForElementIsClickable(buttonIN);
        buttonIN.click();
        wait.until(ExpectedConditions.titleIs(IN_PAGE_TITLE));
        getCurrentTitle(IN_PAGE_TITLE);

    }

   // @Step("Login User IN")
    public  void loginUserIN() throws InterruptedException{
        try {
            emailFieldIN.sendKeys(IN_EMAIL);
            passFieldIN.sendKeys(IN_PASS);
            loginButtonIN.click();
        } catch (Exception |AssertionError e){
            e.printStackTrace();
        }
    }

  //  @Step("Verify User IN")
    public  void verifyUserIN()throws InterruptedException {
        try {
            Thread.sleep(1000);
            assertVisibility(accountDropDown);
            accountDropDown.click();
            assertVisibility(logoutButton);
        }catch (Exception | AssertionError e){
            e.printStackTrace();
        }finally {
            if (logoutButton.isDisplayed());
            logoutButton.click();
            assertVisibility(signInButton);
            assertTrue(signInButton.isDisplayed());
        }
    }
}