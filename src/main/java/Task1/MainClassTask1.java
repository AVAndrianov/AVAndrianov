package Task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import settings.FfoxDriver;

import java.util.concurrent.TimeUnit;

public class MainClassTask1 {
    private WebDriver driver;
    private String main_title;
    private String first_title;

    public MainClassTask1() {
        driver = new FfoxDriver().getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://gossluzhba.gov.ru/");
        main_title = "Госслужба";
        first_title = "Госслужба/Вакансии";

        checkPageCorrect(driver, main_title);

        if (waitElement(driver, By.cssSelector("body > div.container > div > div " +
                "> nav > ul.nav.nav-pills.main-menu " +
                "> li:nth-child(3) > a"), 10)) {

            driver.findElement(By.cssSelector("body > div.container > div > div " +
                    "> nav > ul.nav.nav-pills.main-menu " +
                    "> li:nth-child(3) > a")).click();

            checkPageCorrect(driver, first_title);

            if (waitElement(driver, By.cssSelector("body > div.container " +
                    "> div:nth-child(4) > div.col-xs-8 > a:nth-child(1) " +
                    "> div > p.title"), 10)) {
                driver.findElement(By.cssSelector("body > div.container " +
                        "> div:nth-child(4) > div.col-xs-8 > a:nth-child(1) " +
                        "> div > p.title")).click();
            }
        }
        driver.close();
    }

    private static Boolean waitElement(WebDriver driver, By by, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (Exception e) {
            System.out.println("Элемент '" + by + "' не доступен.");
            return false;
        }
    }

    private static void checkPageCorrect(WebDriver driver, String title) {
        System.out.print("Тест " + title + ": ");
        if ((driver.getTitle()).
                equals(title))
            System.out.println("пройден.");
        else
            System.out.println("провален.");
    }
}
