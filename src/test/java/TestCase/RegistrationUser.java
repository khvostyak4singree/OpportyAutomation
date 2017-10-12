package TestCase;

import Pages.RegistrationPage;
import Utils.ActivationUser;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class RegistrationUser extends ChromeConfig{

    private RegistrationPage registrationPage;
    private ActivationUser activationUser;

    @BeforeTest
    protected void initPages(){
        registrationPage = new RegistrationPage(driver);
        activationUser = new ActivationUser(driver);
    }

    @Test(priority = 1)
    public void registrationUser() throws InterruptedException {
        registrationPage.openRegistrationPage();
        registrationPage.inputRegistrationData();
    }

    @Test(priority = 2)
    public void activationUser() throws Exception {
        activationUser.openActivationLink();
    }

    @Test(priority = 3)
    public void deleteActivationMail() throws Exception {
        activationUser.deleteActivationMessage();

    }
}
