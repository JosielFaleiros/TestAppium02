package trickyTripper.po;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author andreendo
 */
public class BasePage {
    protected AndroidDriver d;
    
    public BasePage(AndroidDriver d) {
        this.d = d;
        PageFactory.initElements(new AppiumFieldDecorator(d, 15, TimeUnit.SECONDS), this);
    }
}
