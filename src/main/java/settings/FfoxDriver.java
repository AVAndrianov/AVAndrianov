package settings;

import org.openqa.selenium.WebDriver;

public class FfoxDriver {
    static WebDriver driver;

    public FfoxDriver() {
        super();

        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/geckodriver");
        driver = new org.openqa.selenium.firefox.FirefoxDriver();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
