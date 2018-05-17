package trickyTripper.po;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.remote.RemoteWebElement;

/**
 *
 * @author andreendo
 */
public class ChangeLogPage extends BasePage {
    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"OK\")")
    RemoteWebElement okButton;

    public ChangeLogPage(AndroidDriver d) {
        super(d);
    }

    public InitialPage pressOK() {
        okButton.click();
        return new InitialPage(d);
    }
}
