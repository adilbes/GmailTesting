package tests.base;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.open;
import static common.Config.HOLD_BROWSER_OPEN;

public class BaseTest {
    public void goToUrl(String url) {
        open(url);
    }

    static {
        Configuration.holdBrowserOpen = HOLD_BROWSER_OPEN;
    }
}
