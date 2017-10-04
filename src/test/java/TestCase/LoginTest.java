package TestCase;

import Pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends ChromeConfig{

    LoginPage loginPage = new LoginPage(driver);

    @Test
        public void loginTest() throws Exception{
        loginPage.openLoginPage();
        loginPage.loginUser();
        loginPage.verifyLogin();

        }
    }


