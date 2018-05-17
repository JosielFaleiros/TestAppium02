package trickyTripper.po;

import io.appium.java_client.pagefactory.Widget;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author andreendo
 */

public class Traveler extends Widget {
    
    @FindBy(id = "de.koelle.christian.trickytripper:id/participantTabRowView_output_participantName")
    WebElement name;
    
    @FindBy(id = "de.koelle.christian.trickytripper:id/participant_tab_row_view_output_paid")
    WebElement paid;
    
    @FindBy(id = "de.koelle.christian.trickytripper:id/participant_tab_row_view_output_spent")
    WebElement spent;
    
    protected Traveler(WebElement element) {
        super(element);
    }
    
    public String getName() {
        return name.getText();
    }
    
    public String getPaid() {
        return paid.getText();
    }
    
    public String getSpent() {
        return spent.getText();
    }

    @Override
    public String toString() {
        return getName() + ";" + getPaid() + ";" + getSpent();
    }
    
    public void select() {
        name.click();
    }
}
