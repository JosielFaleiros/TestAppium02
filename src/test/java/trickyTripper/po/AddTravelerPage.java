package trickyTripper.po;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author andreendo
 */
public class AddTravelerPage extends BasePage {
    @FindBy(id = "Navigate up")
    RemoteWebElement backButton;
    
    @FindBy(id = "Add")
    RemoteWebElement addButton;

    @FindBy(id = "de.koelle.christian.trickytripper:id/editParticipantView_autocomplete_name")
    RemoteWebElement textField;

    
    public AddTravelerPage(AndroidDriver d) {
        super(d);
    }

    public void insert(String name) {
        textField.sendKeys(name);
        addButton.click();
    }
    
    public InitialPage pressBack() {
        backButton.click();
        return new InitialPage(d);
    }
}
