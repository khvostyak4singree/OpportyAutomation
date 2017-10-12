package TestCase;

import Utils.ForgotPassword;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ForgotePasswordTest extends ChromeConfig {

    private ForgotPassword forgotPassword;

    @BeforeTest
    protected void initPages(){
        forgotPassword = new ForgotPassword(driver);
    }

    @Test(priority = 1)
    protected void requestNewPassword() throws Exception {
        forgotPassword.sendRequestToNewPassword();
    }

    @Test(priority = 2)
    protected void getResetUrl() throws Exception {
        forgotPassword.getForgotePassword();
        forgotPassword.enterPassword();

    }

    @Test(priority = 3)
    protected void loginWithNewPass() throws Exception {
        forgotPassword.loginWithNewPassword();
        forgotPassword.deletePasswordMessage();

    }
}



