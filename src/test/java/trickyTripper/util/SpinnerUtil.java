package trickyTripper.util;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

/**
 *
 * @author andreendo
 */
public class SpinnerUtil {

    RemoteWebElement spinnerElement;
    AndroidDriver driver;

    public SpinnerUtil(RemoteWebElement spinnerElement, AndroidDriver driver) {
        this.spinnerElement = spinnerElement;
        this.driver = driver;
    }

    public boolean selectByText(String category) {
        spinnerElement.click();
        WebElement listView = driver.findElementByClassName("android.widget.ListView");

        //last = empty
        ArrayList<String> lastElements = new ArrayList<>();

        //collect current and check element
        ArrayList<String> currentElements = new ArrayList<>();
        List<WebElement> elements = listView.findElements(By.className("android.widget.TextView"));
        for (WebElement e : elements) {
            currentElements.add(e.getText());
            if (e.getText().equals(category)) {
                e.click();
                return true;
            }
        }
        //while(current != last)
        //  scrollDown
        //  last = current
        //  collect new current
        //  add all elements already in the list
        while (!currentElements.equals(lastElements)) {
            scrollDown();
            lastElements = currentElements;
            
            currentElements = new ArrayList<>();
            elements = listView.findElements(By.className("android.widget.TextView"));
            for (WebElement e : elements) {
                currentElements.add(e.getText());
                if (e.getText().equals(category)) {
                    e.click();
                    return true;
                }
            }
        }

        //do the same scroll up
        //last = empty
        lastElements = new ArrayList<>();

        //collect current and check element
        currentElements = new ArrayList<>();
        elements = listView.findElements(By.className("android.widget.TextView"));
        for (WebElement e : elements) {
            currentElements.add(e.getText());
            if (e.getText().equals(category)) {
                e.click();
                return true;
            }
        }        
        
        while (!currentElements.equals(lastElements)) {
            scrollUp();
            lastElements = currentElements;
            
            currentElements = new ArrayList<>();
            elements = listView.findElements(By.className("android.widget.TextView"));
            for (WebElement e : elements) {
                currentElements.add(e.getText());
                if (e.getText().equals(category)) {
                    e.click();
                    return true;
                }
            }
        }        
        
        /*
        scrollUp();
        scrollDown();
        */
        return false;
    }

    /*
    protected void swipe(int startx, int starty, int endx, int endy, int duration) {
        TouchAction action = new TouchAction((MobileDriver) driver);
        action.press(startx, starty).waitAction(Duration.ofMillis(duration)).moveTo(endx, endy).release().perform();
    }*/
    private void scrollUp() {
        int pressX = driver.manage().window().getSize().width / 2;
        int bottomY = driver.manage().window().getSize().height / 2;
        int topY = bottomY + driver.manage().window().getSize().height / 6;
        scroll(pressX, bottomY, pressX, topY);
    }

    private void scrollDown() {
        int pressX = driver.manage().window().getSize().width / 2;
        int bottomY = driver.manage().window().getSize().height / 2;
        int downY = bottomY - driver.manage().window().getSize().height / 6;
        scroll(pressX, bottomY, pressX, downY);
    }

    /*
     * don't forget that it's "natural scroll" where 
    * fromY is the point where you press the and toY where you release it
     */
    private void scroll(int fromX, int fromY, int toX, int toY) {
        //System.out.println("(" + fromX + ", " + fromY + ") -> (" + toX + "," + toY + ")");
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(fromX, fromY).waitAction(Duration.ofMillis(500)).moveTo(toX, toY).release().perform();
    }

}
