package Utils;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.mail.*;
import java.util.Properties;


public class ForgotPassword extends Tools {


    public ForgotPassword(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
        System.out.println("Reset Password elements are initialized");
    }

    public static final String host = "pop.gmail.com";// change accordingly
    public static final String mailStoreType = "pop3s";
    public static final String username = "khvostyak@singree.com";// change accordingly
    public static final String passworduser = "123scarys123";// change accordingly
    public static final String HOME_PAGE_URL="https://testing.opporty.com/";
    public static final String USER_EMAIL = "opportytestuser@gmail.com";

    @FindBy(css = ".forgot>a")
    private WebElement forgotPassButton;
    @FindBy(css = ".form-control")
    private WebElement emailField;
    @FindBy(css = ".btn-react.btn-forgot.btn.btn-default")
    private WebElement emailMeButton;
    @FindBy(css = ".btn-react.btn-ok.btn.btn-default")
    private WebElement okButton;
    @FindBy(css = "input[name='password']")
    private WebElement passField;
    @FindBy(css = "input[name='repassword']")
    private WebElement repassField;
    @FindBy(css = ".logIn")
    private WebElement signInButton;


    public static String getForgotePassword() throws Exception {
        String password = null;
        Properties properties = new Properties();
        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", "995");
        properties.put("mail.pop3.starttls.enable", "true");
        Session emailSession = Session.getDefaultInstance(properties);
        Store store = emailSession.getStore(mailStoreType);
        store.connect(host, username, password);
        Folder emailFolder = store.getFolder("INBOX");
        emailFolder.open(Folder.READ_ONLY);
        Message[] messages = emailFolder.getMessages();
        int i = 0;
        for (Message message : messages) {
            System.out.println("---------------------------------");
            i++;
            if (message.getSubject().contains("Password reset")) {
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());
                String messageBody = message.getContent().toString();
                Document document = Jsoup.parseBodyFragment(messageBody);
                Elements link = document.body().getElementsByAttributeValueContaining("href", "https://testing.opporty.com/reset-password/");
                System.out.println(link);
                password = link.attr("href").toString();
                System.out.println(password);
            }
        }
        return password;
    }


    public void enterPassword() throws Exception {
        String pass = getForgotePassword();
        driver.get(pass);
    }


    public void deletePasswordMessage() throws Exception {
        Properties properties = new Properties();
        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", "995");
        properties.put("mail.pop3.starttls.enable", "true");
        Session emailSession = Session.getDefaultInstance(properties);
        Store store = emailSession.getStore(mailStoreType);
        store.connect(host, username, passworduser);
        Folder emailFolder = store.getFolder("INBOX");
        emailFolder.open(Folder.READ_WRITE);
        Message[] messages = emailFolder.getMessages();
        int i = 0;
        for (Message message : messages) {
            i++;
            System.out.println(message.getSubject());
            if (message.getSubject().contains("Password reset")) {
                message.setFlag(Flags.Flag.DELETED, true);
                emailFolder.close(true);
                System.out.println("Message: Updated password - WAS DELETED!");
                break;

            }
        }
    }

    public void sendRequestToNewPassword () throws Exception{
        openPage(HOME_PAGE_URL);
        signInButton.click();
        assertVisibility(forgotPassButton);
        forgotPassButton.click();
        assertVisibility(emailField);
        emailField.sendKeys(USER_EMAIL);
        emailMeButton.click();
        okButton.click();
        Thread.sleep(2000);
    }

    public void loginWithNewPassword()throws Exception{
        openPage(HOME_PAGE_URL);
        Thread.sleep(2000);
        signInButton.click();
        emailField.sendKeys(USER_EMAIL);
        signInButton.click();
    }
}
