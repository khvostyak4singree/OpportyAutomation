package TestCase;

import Pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends ChromeConfig{

    @Test
        protected void loginTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        loginPage.loginUser();
        loginPage.verifyLogin();

        }
    }


