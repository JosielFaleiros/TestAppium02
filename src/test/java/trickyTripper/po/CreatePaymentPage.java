package trickyTripper.po;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import trickyTripper.util.SpinnerUtil;

/**
 *
 * @author andreendo
 */
public class CreatePaymentPage extends BasePage {
    
    @FindBy(id = "Create")
    RemoteWebElement addButton;

    @FindBy(className = "android.widget.EditText")
    RemoteWebElement amountEdit;
    
    @FindBy(id = "de.koelle.christian.trickytripper:id/paymentView_spinnerPaymentCategory")
    RemoteWebElement categorySpinner;
    
    
    public CreatePaymentPage(AndroidDriver d) {
        super(d);
    }
    
    public InitialPage add(String amount, String category) {
        amountEdit.sendKeys(amount);
        
        SpinnerUtil spinnerUtil = new SpinnerUtil(categorySpinner, d);
        if(spinnerUtil.selectByText(category)) {
            addButton.click();
            return new InitialPage(d);
        }
        return null;
    }
}
