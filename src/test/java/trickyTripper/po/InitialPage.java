package trickyTripper.po;

import io.appium.java_client.android.AndroidDriver;
import java.util.List;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author andreendo
 */
public class InitialPage extends BasePage {

    //id -> using content-desc
    @FindBy(id = "Add traveler to trip")
    RemoteWebElement addButton;

    @FindBy(id = "de.koelle.christian.trickytripper:id/participantTabRowView_output_participantName")
    List<RemoteWebElement> namesElements;

    @FindBy(className = "android.widget.RelativeLayout")
    List<Traveler> travelers;

    public InitialPage(AndroidDriver d) {
        super(d);
    }

    public AddTravelerPage pressAdd() {
        addButton.click();
        return new AddTravelerPage(d);
    }

    public boolean hasTraveler(String name) {
        return travelers.stream()
                .anyMatch( t -> t.getName().equals(name) );
    }

    public int getNumberOfTravelers() {
        return travelers.size();
    }

    public String getTravelerInfo(String name) {
        Traveler t = travelers.stream()
                .filter(x -> x.getName().equals(name))
                .findFirst()
                .orElse(null);
        
        return t.toString();
    }    
    
    public CreatePaymentPage clickTraveler(String name) {
        Traveler t = travelers.stream()
                .filter(x -> x.getName().equals(name))
                .findFirst()
                .orElse(null);
        
        t.select();
        
        return new CreatePaymentPage(d);
    }
}
