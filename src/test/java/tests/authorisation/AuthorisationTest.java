package tests.authorisation;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;
import java.time.Duration;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static constants.Constant.Urls.*;
import static com.codeborne.selenide.Selenide.$x;
import java.io.File;
import java.util.regex.Pattern;
import org.openqa.selenium.chrome.ChromeOptions;
import com.codeborne.selenide.Selenide;



public class AuthorisationTest extends BaseTest {
    
    AuthorisationPage authorisationPage = new AuthorisationPage();

    //фейкер для генерации данных письма и подписи
    Faker faker = new Faker();
    //    я буду использовать констант имейл чтобы тест прошел, но вот рандомный
//    public String randomEmail = faker.internet().emailAddress();
    public String randomTopic = faker.resolve("game_of_thrones.houses");
    public String randomText = faker.resolve("game_of_thrones.quotes");
    public String randomSignatureName = faker.resolve("game_of_thrones.characters");
    public String randomSignatureText = faker.resolve("game_of_thrones.quotes");


    public void Authorisation() throws InterruptedException {
        goToUrl(HOME_PAGE_URL);

//        Первый способ обойти защиту гугла (нерабочий)
//        ChromeOptions options = new ChromeOptions();
//        options.addExtensions(new File("C://Users//adyussekov//Desktop//extension//extension.crx"));
//        options.addArguments("--no-sandbox");
//        Второй способ (нерабочий)
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("user-data-dir=C://Users//adyussekov//AppData//Local//Google//Chrome//User Data");

        authorisationPage.email_field.shouldBe(Condition.visible, Duration.ofSeconds(10)).sendKeys(EMAIL);
        authorisationPage.next_button.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        authorisationPage.password_field.sendKeys(PASSWORD);
        authorisationPage.next_password_button.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        //попытка обойти защиту гугла
//        if ($x(".//input[@type='password']").isDisplayed()) {
//            authorisationPage.password_field.sendKeys(PASSWORD);
//            authorisationPage.next_password_button.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
//        }
//        else {
//            authorisationPage.error_button.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
//            authorisationPage.enter_again.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
//            authorisationPage.email_field.shouldBe(Condition.visible, Duration.ofSeconds(10)).sendKeys(EMAIL);
//            authorisationPage.next_button.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
//            Thread.sleep(10000);
//            authorisationPage.password_field.shouldBe(Condition.visible, Duration.ofSeconds(10)).sendKeys(PASSWORD);
//            authorisationPage.next_password_button.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
//        }
    }

    @Test
    @Order(1)
    public void AuthorisationCheck() throws InterruptedException {
        Authorisation();
    }

    @Test
    @Order(2)
    public void SendEmail() throws InterruptedException {
        Authorisation();
        authorisationPage.write_button.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        authorisationPage.email_reciever.shouldBe(Condition.visible, Duration.ofSeconds(10)).sendKeys(EMAIL_TO);
//        Дальше таймеры не нужны, т.к. окно ввода письма открывается целиком
        authorisationPage.email_topic.sendKeys(randomTopic);
        authorisationPage.email_text.sendKeys(randomText);
        authorisationPage.email_send_button.click();
    }

    @Test
    @Order(3)
    public void CheckEmail() throws InterruptedException {
        Authorisation();
        //делал поиск по топику в отправленных
        authorisationPage.sent_messages.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        $("#body").find(withText(randomTopic)).shouldBe(Condition.visible, Duration.ofSeconds(40));
    }

    @Test
    @Order(4)
    public void Signature() throws InterruptedException {
        Authorisation();
       // ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

        authorisationPage.settings_window.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        authorisationPage.settings_button.shouldBe(Condition.visible).click();
        authorisationPage.signature_add_button.scrollTo()
                .shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
        authorisationPage.signature_name.shouldBe(Condition.visible).sendKeys(randomSignatureName);
        authorisationPage.signature_submit_button.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        authorisationPage.signature_text.shouldBe(Condition.visible).sendKeys(randomSignatureText);
        authorisationPage.signature_select
                .shouldBe(Condition.visible)
                .selectOptionContainingText(randomSignatureName);



        authorisationPage.settings_submit.shouldBe(Condition.visible).click();

        //отправка нового письма
        authorisationPage.write_button.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        authorisationPage.email_reciever.shouldBe(Condition.visible, Duration.ofSeconds(10)).sendKeys(EMAIL_TO);
        authorisationPage.email_topic.sendKeys(randomTopic);
        authorisationPage.email_text.sendKeys(randomText);
        authorisationPage.email_send_button.click();
    }

    @Test
    @Order(5)
    public void SignatureSentEmail() throws InterruptedException {
        Authorisation();
        authorisationPage.sent_messages.shouldBe(Condition.visible).click();
        $("#body").find(withText(randomTopic)).shouldBe(Condition.visible, Duration.ofSeconds(40))
                .click();
        $("#body").find(withText(randomSignatureText)).shouldBe(Condition.visible, Duration.ofSeconds(40));
    }
}
