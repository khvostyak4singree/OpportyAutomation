package TestCase;

import Pages.LoginPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest extends ChromeConfig {

    private  LoginPage loginPage;

    @BeforeTest
    protected void initPages(){
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1)
    protected void loginTest() {
        loginPage.openLoginPage();
        loginPage.loginUser();
        loginPage.verifyLogin();

    }

    @Test(priority = 3)
    protected void loginFB() {
        loginPage.openLoginPageFB();
        loginPage.loginUserFB();
        loginPage.verifyUserFB();
    }

    @Test(priority = 2)
    protected void loginLinkedIn() {
        loginPage.openLoginPageIN();
        loginPage.loginUserIN();
        loginPage.verifyUserIN();
    }
}


