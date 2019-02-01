import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws IOException {

        String name = "НИОКР";
        String maxSum = "300000000";
        String minSum = "100";
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/geckodriver");
        WebDriver driver = new FirefoxDriver();
        WindowsTab windowsTab = new WindowsTab();
        windowsTab.setMainWindowHandle(driver);
        driver.get("http://zakupki.gov.ru/");
        driver.findElement(By.cssSelector("button.search__btn")).click();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5000, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5000, TimeUnit.SECONDS);

        driver.findElement(By.cssSelector("a.extendedSearchLink.floatRight")).click();


        WebElement minSumField = driver.findElement(By.cssSelector("#priceFromGeneral"));
        for (int i = 0; i < minSum.length(); i++) {
            minSumField.sendKeys(String.valueOf(minSum.charAt(i)));
        }
        WebElement maxSumField = driver.findElement(By.cssSelector("#priceToGeneral"));
        for (int i = 0; i < maxSum.length(); i++) {
            maxSumField.sendKeys(String.valueOf(maxSum.charAt(i)));
        }

        WebElement mainField = driver.findElement(By.cssSelector("#searchString.autocompleteOrder.hint.clearValue.withoutAutocomplete"));
        mainField.clear();
        for (int i = 0; i < name.length(); i++) {
            mainField.sendKeys(String.valueOf(name.charAt(i)));
        }
        driver.findElement(By.xpath("//li[@id='placingWaysTag']/div/div/span")).click();


        ((JavascriptExecutor) driver).executeScript("document.getElementById('placingWay_ZKKD44').click()");
        ((JavascriptExecutor) driver).executeScript("document.getElementById('placingWay_KESMBO').click()");
        ((JavascriptExecutor) driver).executeScript("document.getElementById('placingWay_OKD504').click()");
        ((JavascriptExecutor) driver).executeScript("document.getElementById('placingWay_ZKKU44').click()");
        ((JavascriptExecutor) driver).executeScript("document.getElementById('placingWay_ZKK44').click()");
        ((JavascriptExecutor) driver).executeScript("document.getElementById('placingWay_OK').click()");
        ((JavascriptExecutor) driver).executeScript("document.getElementById('placingWay_OK44').click()");
        ((JavascriptExecutor) driver).executeScript("document.getElementById('placingWay_OKD44').click()");
        ((JavascriptExecutor) driver).executeScript("document.getElementById('placingWay_OKU44').click()");
        ((JavascriptExecutor) driver).executeScript("document.getElementById('placingWay_OK504').click()");
        ((JavascriptExecutor) driver).executeScript("document.getElementById('placingWay_SZ').click()");
        ((JavascriptExecutor) driver).executeScript("document.getElementById('placingWaysSelectBtn').click()");
//        driver.findElement(By.xpath("//li[@id='orderStages']/div/div/span")).click();
//        ((JavascriptExecutor)driver).executeScript("document.getElementById('ca').click()");
//        ((JavascriptExecutor)driver).executeScript("document.getElementById('pc').click()");
//        ((JavascriptExecutor)driver).executeScript("document.getElementById('pa').click()");
//        ((JavascriptExecutor)driver).executeScript("document.getElementById('orderStagesSelectBtn')" +
//                ".getElementsByClassName('btnBtn blueBtn')[0].click()");
        driver.findElement(By.cssSelector("div.pseudoSelect.greyText")).click();

        ((JavascriptExecutor) driver).executeScript("document.getElementById('1').click()");

        driver.findElement(By.cssSelector("span.bigOrangeBtn.searchBtn")).click();
        Set<WebElement> webElementHashSet = new HashSet<WebElement>();
        LinkedList<WebElement> linkList;

        driver.findElement(By.cssSelector("li.pageSelect")).click();
        ((JavascriptExecutor) driver).executeScript("document.getElementById('_10').click()");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int pageNumber = driver.findElements(By.cssSelector("body > div.parametrs.margBtm10 " +
                "> div.paginator.greyBox.extendedVariant.margBtm20 " +
                "> div.paginator.greyBox > ul.pages > li.page")).size();
        String numberOfPages = driver.findElement(By.cssSelector("body > div.parametrs.margBtm10 " +
                "> div.paginator.greyBox.extendedVariant.margBtm20 " +
                "> div.paginator.greyBox > ul.pages > li:nth-child(" + pageNumber + ") > a")).getAttribute("data-pagenumber");
        System.out.println(pageNumber);
        System.out.println(numberOfPages);
        int pageNumber2 = Integer.parseInt(numberOfPages);
        System.out.println(pageNumber2);
        try (FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/notes.txt")) {
            int i = 1;
            while (i <= pageNumber2) {
                if (i == 3) i = 4;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                driver.findElement(By.cssSelector("body > div.parametrs.margBtm10 " +
                        "> div.paginator.greyBox.extendedVariant.margBtm20 " +
                        "> div.paginator.greyBox > ul > li:nth-child(" + i + ")")).click();
//                    linkList.addAll(driver.findElements(By.cssSelector("dd > strong")));
//            webElementHashSet.addAll(driver.findElements(By.cssSelector("dd > strong")));
                i++;
                WebElement sum;
                WebElement info;
                for (int j = 0; j < driver.findElements(By.cssSelector("dd > strong")).size(); j++) {
                    sum = driver.findElements(By.cssSelector("dd > strong")).get(j);

                    windowsTab.setOldWindowsSet(driver);
                    driver.findElement(By.cssSelector("div:nth-child(" + (j + 3) + ") > div.reportBox > ul > ul > li:nth-child(1) > a")).click();
                    driver.switchTo().window(windowsTab.getNewWindowHandle(driver));
                    String nameOfPurchase = driver.findElement(By.cssSelector("body > div.cardWrapper > div > div > div.cardWrapper > div > div > div.contentTabBoxBlock > div.noticeTabBox.padBtm20 > div:nth-child(2) > table > tbody > tr:nth-child(4) > td:nth-child(2)\n")).getText();
                    driver.switchTo().window(windowsTab.getMainWindowHandle());

                    byte[] buffer = (sum.getText() + System.lineSeparator() + nameOfPurchase + System.lineSeparator()).getBytes();
                    fos.write(buffer, 0, buffer.length);

//                    System.out.println(sum.getText());
                }
//                for (WebElement w : driver.findElements(By.cssSelector("dd > strong"))) {
//                    byte[] buffer = (w.getText() + System.lineSeparator()).getBytes();
//
//                    fos.write(buffer, 0, buffer.length);
//                    System.out.println(w.getText());
//                }
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
//        for (int j = 0; j < linkList.size(); j++) {

//        linkList = (LinkedList<WebElement>) driver.findElements(By.cssSelector("dd > strong"));


//        }
//        }
//        dateField.sendKeys(date);
//        innField.submit();
//        List<WebElement> linkList = driver.findElements(By.cssSelector(".hide.information.warning"));
//        List<WebElement> linkList2 = driver.findElements(By.cssSelector(".hide.information.pnl-info"));
//        List<WebElement> linkList3 = driver.findElements(By.cssSelector(".field-errors"));
//        for (WebElement w : linkList) {
//            System.out.println(w.getText());
//        }
//        for (WebElement w : linkList2) {
//            System.out.println(w.getText());
//        }
//        for (WebElement w : linkList3) {
//            System.out.println(w.getText());
//        }
//        if (!isDigit(inn)) System.out.println("Недопустимые символы в ИНН");
//        if (!isDigit(kpp)) System.out.println("Недопустимые символы в КПП");
//        driver.close();
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        if (!s.equals("")) {
            try {
                Integer.parseInt(s);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } else
            return true;
    }


}
