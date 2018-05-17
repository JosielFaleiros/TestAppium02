
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import static org.junit.Assert.*;

/**
 *
 * @author utfpr
 */
public class InitialPage {

    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\""+"Computer Science"+"\")")
    RemoteWebElement csItem;
    
    @AndroidFindBy(id = "android:id/text1")
    List<RemoteWebElement> items;
    
    public InitialPage(AndroidDriver d) {
        PageFactory.initElements(new AppiumFieldDecorator(d, 15, TimeUnit.SECONDS), this);
        
        //d.findElementByAndroidUIAutomator().click();
        //System.out.println( d.getPageSource().length() );
        
    }
    
    public void select(String label) {
        boolean clicked = false;
        for(RemoteWebElement e : items) {
            if(e.getText().contains(label)) {
                e.click();
                clicked = true;
                break;
            }
        }
        if(! clicked)
            fail();
    }
    
    public void selectComputerScience() {
        csItem.click();
    }
    
}
