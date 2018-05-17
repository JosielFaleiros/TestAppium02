package trickyTripper;

import io.appium.java_client.MobileElement;
import trickyTripper.po.InitialPage;
import trickyTripper.po.AddTravelerPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import trickyTripper.po.ChangeLogPage;
import trickyTripper.po.CreatePaymentPage;

/**
 *
 * @author andreendo
 * 
 * appium in command line
 * 
 * LD_PRELOAD='/usr/lib/x86_64-linux-gnu/libstdc++.so.6' /home/utfpr/Android/Sdk/emulator/emulator -netdelay none -netspeed full -avd Nexus4-22
 * 
 * /home/utfpr/Android/Sdk/tools/bin/uiautomatorviewer
 * 
 */
public class TrickyTripperTest {
    private static String APKFILELOCATION = "./res/de.koelle.christian.trickytripper_21.apk";
    protected AndroidDriver d;

    @Before
    public void before() throws MalformedURLException {
        File apkFile = new File(APKFILELOCATION);
        
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus4-22");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.APP, apkFile.getAbsolutePath());

        d = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void after() {
        if(d != null)
            d.quit();
    }

    @Test
    public void testIncludeThreeTravelers() throws Exception {
        //pass initial page
        ChangeLogPage changeLogPage = new ChangeLogPage(d);
        InitialPage initialPage = changeLogPage.pressOK();
        
        //add travelers
        AddTravelerPage addTravelerPage = initialPage.pressAdd();
        addTravelerPage.insert("Mike");
        addTravelerPage.insert("Julien");
        addTravelerPage.insert("Richard");
        
        //check travelers added
        initialPage = addTravelerPage.pressBack();
        assertEquals(3, initialPage.getNumberOfTravelers());
        assertTrue( initialPage.hasTraveler("Mike") );
        assertTrue( initialPage.hasTraveler("Julien") );
        assertTrue( initialPage.hasTraveler("Richard") );
        
        //
        CreatePaymentPage paymentPage = initialPage.clickTraveler("Julien");
        initialPage = paymentPage.add("240.00", "Beverages");
        
        assertEquals("Julien;240.00;80.00", initialPage.getTravelerInfo("Julien"));
        assertEquals("Mike;0.00;80.00", initialPage.getTravelerInfo("Mike"));
        assertEquals("Richard;0.00;80.00", initialPage.getTravelerInfo("Richard"));
        
        Thread.sleep(5000);
    }
}
