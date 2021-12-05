package common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {

    private WebElement element;
    private JavascriptExecutor jsExecutor;
    private WebDriverWait explicitWait;

    public static final long LONG_TIMEOUT = 30;
    public void openPageUrl(WebDriver driver, String url) {
        driver.get(url);
        sleepInSecond(3);
    }

    public String getCurrentPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
        sleepInSecond(3);
    }

    public By getByXpath(String locator) {
        return By.xpath(locator);
    }

    public WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByXpath(locator));
    }

    public void clickToElement(WebDriver driver, String locator) {
        element = getElement(driver, locator);
        element.click();
        sleepInSecond(2);
    }

    public void sendkeysToElement(WebDriver driver, String locator, String value) {
        element = getElement(driver, locator);
        element.clear();
        element.sendKeys(value);
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getElementText(WebDriver driver, String locator) {
        element = getElement(driver, locator);
        return element.getText();
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        element = getElement(driver, locator);
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public void scrollToElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        element = getElement(driver, locator);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        sleepInSecond(2);
    }

    public void waitToElementVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }

    public void waitToElementClickable(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }

}
