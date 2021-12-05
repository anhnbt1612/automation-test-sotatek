package test;

import java.util.*;

import common.AbstractPage;
import common.SessionData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GetPriceDef extends AbstractPage {
    WebDriver driver;
    List<Product> products = new ArrayList<>();

    public void getBrowserDriver() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @Given("^Open \"([^\"]*)\" website$")
    public void openWebsite(String web) {
        getBrowserDriver();
        openPageUrl(driver, web);
    }

    @When("^Search keyword$")
    public void searchKeyword(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String web = SessionData.getDataTbVal(dataTable, row, "Web");
            String keyword = SessionData.getDataTbVal(dataTable, row, "Keyword");

            if (web.equals("shopee")) {
                refreshPage(driver);
                refreshPage(driver);
                waitToElementVisible(driver, "//input[contains(@class,'searchbar')]");
                sendkeysToElement(driver, "//input[contains(@class,'searchbar')]", keyword);
                waitToElementClickable(driver, "//button[contains(@class,'search-button')]");
                clickToElement(driver, "//button[contains(@class,'search-button')]");
            } else {
                waitToElementVisible(driver, "//input[contains(@class,'search-box')]");
                sendkeysToElement(driver, "//input[contains(@class,'search-box')]", keyword);
                waitToElementClickable(driver, "//button[contains(@class,'search-box')]");
                clickToElement(driver, "//button[contains(@class,'search-box')]");
            }
        }
    }

    @And("^verify first product on result page$")
    public void verifyFirstProductOnResultPage(List<List<String>> dataTable) {
        for (int row : SessionData.getDataTbRowsNoHeader(dataTable).keySet()) {
            String web = SessionData.getDataTbVal(dataTable, row, "Web");
            String keyword = SessionData.getDataTbVal(dataTable, row, "Keyword");

            scrollToElement(driver, "(//a[contains(.,'" + keyword + "')])[1]");
            if (web.equals("shopee")) {
                clickToElementByJS(driver, "(//a[contains(.,'" + keyword + "')])[1]//img");
                waitToElementVisible(driver, "(//div[contains(@class,'product-briefing')]//span)[1]");
                String prodName = getElementText(driver, "(//div[contains(@class,'product-briefing')]//span)[1]");
                System.out.println(prodName);
                waitToElementVisible(driver, "//div[text()='30% giảm']//parent::div/div");
                String prodPrice = getElementText(driver, "//div[text()='30% giảm']//parent::div/div");
                System.out.println(prodPrice);
                String prodLink = getCurrentPageUrl(driver);
                System.out.println(prodLink);
                products.add(new Product(prodName, prodPrice, prodLink, Arrays.asList('₫','.')));
            } else {
                clickToElementByJS(driver, "(//a[contains(.,'" + keyword + "')])[1]");
                waitToElementVisible(driver, "//h1");
                String prodName = getElementText(driver, "//h1");
                System.out.println(prodName);
                waitToElementVisible(driver, "//div[contains(@class,'product-price')]//span");
                String prodPrice = getElementText(driver, "//div[contains(@class,'product-price')]//span");
                System.out.println(prodPrice);
                String prodLink = getCurrentPageUrl(driver);
                System.out.println(prodLink);
                products.add(new Product(prodName, prodPrice, prodLink, Arrays.asList('₫',',')));
            }
        }
    }

    @And("^Print and close browser$")
    public void closeApplication() {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                if (p1 == null) {
                    return -1;
                }
                if (p2 == null) {
                    return 1;
                }
                return p1.getPriceNumber() - p2.getPriceNumber();
            }
        });
        for (Product product : products) {
            System.out.println("--------------- Sort and print product information -------------- " + product);
        }
        products.clear();
        driver.quit();
    }
}
