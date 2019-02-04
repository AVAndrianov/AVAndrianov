package Task2;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import settings.FfoxDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainClassTask2 {
    private WebDriver driver;
    private String name = "НИОКР";
    private String maxSum = "300000000";
    private String minSum = "100";
    private WindowsTab windowsTab;
    private SearchSettings searchSettings;
    private WebDriverWait wait;

    public MainClassTask2() {
        super();
        driver = new FfoxDriver().getDriver();
        searchSettings = new SearchSettings(driver);
        windowsTab = new WindowsTab();
        wait = new WebDriverWait(driver, 1);

        windowsTab.
                setMainWindowHandle(driver);
        driver.
                get("http://zakupki.gov.ru/");
        driver.
                manage().
                timeouts().
                implicitlyWait(10, TimeUnit.SECONDS);
        driver.
                manage().
                timeouts().
                pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.
                manage().
                timeouts().
                setScriptTimeout(10, TimeUnit.SECONDS);

        driver.
                findElement(By.cssSelector("button.search__btn")).
                click();
        driver.
                findElement(By.cssSelector("a.extendedSearchLink.floatRight")).
                click();
        //Заполнение поля максимальная сумма
        searchSettings.
                fillInputField(driver,
                        By.cssSelector("#priceToGeneral"), maxSum);
        //Заполнение поля минимальная сумма
        searchSettings.
                fillInputField(driver,
                        By.cssSelector("#priceFromGeneral"), minSum);
        //Заполнение поля Закупки
        searchSettings.
                fillInputField(driver,
                        By.cssSelector("#searchString.autocompleteOrder" +
                                ".hint.clearValue.withoutAutocomplete"), name);
        //Выбор валюты
        driver.findElement(By.cssSelector("div.pseudoSelect.greyText")).
                click();

        ((JavascriptExecutor) driver).
                executeScript("document.getElementById('1').click()");
        //Способ определения поставщика, подрядной организации
        driver.
                findElement(By.xpath("//li[@id='placingWaysTag']/div/div/span")).
                click();

        ((JavascriptExecutor) driver).
                executeScript("document.getElementById('placingWay_ZKKD44').click()");

        ((JavascriptExecutor) driver).
                executeScript("document.getElementById('placingWay_KESMBO').click()");

        ((JavascriptExecutor) driver).
                executeScript("document.getElementById('placingWay_OKD504').click()");

        ((JavascriptExecutor) driver).
                executeScript("document.getElementById('placingWay_ZKKU44').click()");

        ((JavascriptExecutor) driver).
                executeScript("document.getElementById('placingWay_ZKK44').click()");

        ((JavascriptExecutor) driver).
                executeScript("document.getElementById('placingWay_OK').click()");

        ((JavascriptExecutor) driver).
                executeScript("document.getElementById('placingWay_OK44').click()");

        ((JavascriptExecutor) driver).
                executeScript("document.getElementById('placingWay_OKD44').click()");

        ((JavascriptExecutor) driver).
                executeScript("document.getElementById('placingWay_OKU44').click()");

        ((JavascriptExecutor) driver).
                executeScript("document.getElementById('placingWay_OK504').click()");

        ((JavascriptExecutor) driver).
                executeScript("document.getElementById('placingWay_SZ').click()");

        ((JavascriptExecutor) driver).
                executeScript("document.getElementById('placingWaysSelectBtn').click()");

        //Этап закупки:
//        driver.findElement(By.xpath("//li[@id='orderStages']/div/div/span")).click();
//        ((JavascriptExecutor)driver).executeScript("document.getElementById('ca').click()");
//        ((JavascriptExecutor)driver).executeScript("document.getElementById('pc').click()");
//        ((JavascriptExecutor)driver).executeScript("document.getElementById('pa').click()");
//        ((JavascriptExecutor)driver).executeScript("document.getElementById('orderStagesSelectBtn')" +
//                ".getElementsByClassName('btnBtn blueBtn')[0].click()");

        //Кнопка Найти
        driver.findElement(By.cssSelector("span.bigOrangeBtn.searchBtn")).
                click();

        //Переключение количества выводимых позиций на одной странице
        driver.findElement(By.cssSelector("li.pageSelect")).
                click();
        ((JavascriptExecutor) driver).
                executeScript("document.getElementById('_20').click()");
        //Руки еще не дошли сделать нормальную проверку и ожидание
        try {
            Thread.sleep(2000);
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
        int pageNumber2;
        int pageNumber = 1;

        //Если количество страниц, равно 0,ожидаем таймаут поиска элемента и выствляю pageNumber = 0
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body > div.parametrs.margBtm10 " +
                    "> div.paginator.greyBox.extendedVariant.margBtm20 " +
                    "> div.paginator.greyBox > ul.pages > li.page")));
        } catch (Exception e) {
            pageNumber = 0;
        }

        //Опредеояю количество страниц
        if (pageNumber != 0) {
            pageNumber = driver.findElements(By.cssSelector("body > div.parametrs.margBtm10 " +
                    "> div.paginator.greyBox.extendedVariant.margBtm20 " +
                    "> div.paginator.greyBox > ul.pages > li.page")).
                    size();
            String numberOfPages = driver.findElement(By.cssSelector("body > div.parametrs.margBtm10 " +
                    "> div.paginator.greyBox.extendedVariant.margBtm20 " +
                    "> div.paginator.greyBox > ul.pages > li:nth-child(" + pageNumber + ") > a")).
                    getAttribute("data-pagenumber");
            pageNumber2 = Integer.parseInt(numberOfPages);
        } else {
            pageNumber2 = 1;
        }

        try (
                FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/notes.txt")) {
            int i = 1;
            while (i <= pageNumber2) {
                //При переходе на вторую страницу слева появляется элемент в виде стрелки и счет сдивигается на 1
                if (i == 3) i = 4;
                //Руки еще не дошли сделать нормальную проверку и ожидание
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (pageNumber2 != 1)
                    driver.findElement(By.cssSelector("body > div.parametrs.margBtm10 " +
                            "> div.paginator.greyBox.extendedVariant.margBtm20 " +
                            "> div.paginator.greyBox > ul > li:nth-child(" + i + ")")).click();
                i++;

                WebElement sum;
                String nameOfPurchase;
                String applicationDeadline;
                String contactPerson;
                String electronicSignature;
                for (int j = 0; j < driver.findElements(By.cssSelector("dd > strong")).size(); j++) {
                    sum = driver.findElements(By.cssSelector("dd > strong")).get(j);
                    windowsTab.setOldWindowsSet(driver);
                    driver.
                            findElement(By.cssSelector("div:nth-child(" + (j + 3) + ") " +
                                    "> div.reportBox > ul > ul > li:nth-child(1) > a")).click();
                    driver.switchTo().window(windowsTab.getNewWindowHandle(driver));
                    nameOfPurchase = driver.
                            findElement(By.cssSelector("body > div.cardWrapper " +
                                    "> div > div > div.cardWrapper > div > div > div.contentTabBoxBlock " +
                                    "> div.noticeTabBox.padBtm20 > div:nth-child(2) > table > tbody > tr:nth-child(4) " +
                                    "> td:nth-child(2)\n")).getText();
                    applicationDeadline = driver.
                            findElement(By.cssSelector("body > div.cardWrapper > div " +
                                    "> div > div.cardWrapper > div > div > div.contentTabBoxBlock " +
                                    "> div.noticeTabBox.padBtm20 > div:nth-child(10) > table > tbody " +
                                    "> tr:nth-child(1) > td:nth-child(2)")).getText();
                    contactPerson = driver.findElement(By.cssSelector("body > div.cardWrapper > div " +
                            "> div > div.cardWrapper > div > div > div.contentTabBoxBlock " +
                            "> div.noticeTabBox.padBtm20 > div:nth-child(6) > table > tbody " +
                            "> tr:nth-child(2) > td:nth-child(2)")).getText();
                    driver.close();
                    driver.
                            switchTo().
                            window(windowsTab.getMainWindowHandle());

                    String URL = driver.
                            findElement(By.cssSelector("div:nth-child(" + (j + 3) + ") > div.boxIcons " +
                                    "> a.cryptoSignLink.externalSignLink.pWidth_840.linkPopUp")).getAttribute("href");
                    windowsTab.setOldWindowsSet(driver);
                    ((JavascriptExecutor) driver).executeScript("window.open()");
                    driver.switchTo().window(windowsTab.getNewWindowHandle(driver));
                    driver.get(URL);
                    driver.
                            findElement(By.cssSelector("body > div.modal > div " +
                                    "> div.addingTbl.expandTbl.elSignBlock > table " +
                                    "> tbody > tr.rowExpand > td:nth-child(1) > span")).click();
                    electronicSignature = driver.
                            findElement(By.cssSelector("tr.toggleTr.expandRow > td > div")).getText();
                    driver.close();
                    driver.
                            switchTo().
                            window(windowsTab.getMainWindowHandle());
                    byte[] buffer = (nameOfPurchase
                            + System.lineSeparator()
                            + sum.getText()
                            + System.lineSeparator()
                            + applicationDeadline
                            + System.lineSeparator()
                            + contactPerson
                            + System.lineSeparator()
                            + electronicSignature
                            + System.lineSeparator()
                            + System.lineSeparator()).
                            getBytes();
                    fos.write(buffer, 0, buffer.length);
                }
            }
        } catch (
                IOException ex) {

            System.out.println(ex.getMessage());
        } finally {
            driver.close();
        }
    }
}
