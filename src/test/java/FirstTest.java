import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author andreendo
 */
public class FirstTest {
    
    public FirstTest() {
    }
    
    protected AndroidDriver d;

    @Before
    public void before() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus4-22");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        //capabilities.setCapability(MobileCapabilityType., "com.commonsware.android.arXiv");
        //capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.commonsware.android.arXiv.arXiv");

        d = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void after() {
        /*if(d != null)
            d.quit();*/
    }

    @Test
    public void test01() {
        d.resetApp();
        InitialPage initialPage = new InitialPage(d);
        
        initialPage.select("Computer Science");
        //d.closeApp();
    }
}
