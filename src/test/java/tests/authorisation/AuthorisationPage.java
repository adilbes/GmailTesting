package tests.authorisation;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AuthorisationPage {


    //тест авторизации
    public final SelenideElement email_field = $x(".//input[@id='identifierId']");
    public final SelenideElement next_button = $x(".//div[@id='identifierNext']/div[@data-is-touch-wrapper='true']/button[@jsname='LgbsSe']");
    public final SelenideElement password_field = $x(".//input[@type='password']");
    public final SelenideElement next_password_button = $x(".//div[@id='passwordNext']/div/button");
    public final SelenideElement error_button = $x(".//a[@aria-label='Повторить попытку']");
    public final SelenideElement enter_again = $x(".//a[text()='Войти']");

    //тест отправки письма
    public final SelenideElement write_button = $x(".//span[@class='nU n1']/..");
    public final SelenideElement email_reciever = $x(".//textarea[@id=':as']");
    public final SelenideElement email_topic = $x(".//input[@id=':aa']");
    public final SelenideElement email_text = $x(".//textarea[@id=':bl']");
    public final SelenideElement email_send_button = $x(".//div[@id=':a0']");

    //тест проверки наличия письма в отправленных
    public final SelenideElement sent_messages = $x(".//div[@id=':62']/div");

    //тест создания подписи
    public final SelenideElement settings_window = $x(".//a[@aria-label='Настройки']");
    public final SelenideElement settings_button = $x(".//button[@aria-label='Все настройки']");
    public final SelenideElement signature_add_button = $x(".//button[@id=':a4']");
    public final SelenideElement signature_name = $x(".//input[@id=':j8.in']");
    public final SelenideElement signature_submit_button = $x(".//button[@name='ok']");
    public final SelenideElement signature_text = $x(".//div[@id=':bu']");
    public final SelenideElement signature_select = $x(".//select[@id=':cn']");
    public final SelenideElement settings_submit = $x(".//button[@id=':9e']");

}
