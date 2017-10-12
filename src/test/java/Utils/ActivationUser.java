package Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import javax.mail.*;
import java.util.Properties;

public class ActivationUser extends Tools{

    public ActivationUser(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver,this);
        System.out.println("LoginPage elements are initialized");

    }

    public static final String host = "pop.gmail.com";// change accordingly
    public static final String mailStoreType = "pop3s";
    public static final String username = "khvostyak@singree.com";// change accordingly
    public static final String password = "123scarys123";// change accordingly
    private static final Logger log = Logger.getLogger(ActivationUser.class);


    public static String getActivationLink() throws Exception {
        String reference = null;
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
            if (message.getSubject().contains("Registration successfull")) {
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());
                String messageBody = message.getContent().toString();
                Document document = Jsoup.parseBodyFragment(messageBody);
                Elements link = document.body().getElementsByAttributeValueContaining("href", "https://testing.opporty.com/activate/user/");
                System.out.println(link);
                reference = link.attr("href").toString();
                System.out.println(reference);
            }
        }
        return reference;
    }

    public void openActivationLink() throws Exception {
        String url = getActivationLink();
        driver.get(url);
        Thread.sleep(5000);
    }

    public void deleteActivationMessage() throws Exception {
        Properties properties = new Properties();
        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", "995");
        properties.put("mail.pop3.starttls.enable", "true");
        Session emailSession = Session.getDefaultInstance(properties);
        Store store = emailSession.getStore(mailStoreType);
        store.connect(host, username, password);
        Folder emailFolder = store.getFolder("INBOX");
        emailFolder.open(Folder.READ_WRITE);
        Message [] messages = emailFolder.getMessages();
        int i =0;
        for (Message message:messages){
            i++;
            System.out.println(message.getSubject());
            if (message.getSubject().contains("Registration successfull")){
                message.setFlag(Flags.Flag.DELETED, true);
                emailFolder.close(true);
                System.out.println("Message: Registration successfull - WAS DELETED!");
                log.info("Registration successfull - WAS DELETED!");
                break;

            }

        }

    }
}









