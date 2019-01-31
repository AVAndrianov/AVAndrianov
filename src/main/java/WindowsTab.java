import org.openqa.selenium.WebDriver;

import java.util.Set;

public class WindowsTab {

    private String newWindowHandle;
    private String mainWindowHandle;
    private Set<String> oldWindowsSet;
    private Set<String> newWindowsSet;

    public String getMainWindowHandle() {
        return mainWindowHandle;
    }

    public void setMainWindowHandle(WebDriver driver) {
        mainWindowHandle = driver.getWindowHandle();
    }

    public void setOldWindowsSet(WebDriver driver) {
        oldWindowsSet = driver.getWindowHandles();
    }

    public String getNewWindowHandle(WebDriver driver) {
        newWindowsSet = driver.getWindowHandles();
        newWindowsSet.removeAll(oldWindowsSet);
        newWindowHandle = newWindowsSet.iterator().next();
        return newWindowHandle;
    }
}
