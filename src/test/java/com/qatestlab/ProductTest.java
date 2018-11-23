package com.qatestlab;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.Random;

public class ProductTest {
     WebDriver driver;
     String product = generateString();
     Random random = new Random();
     String amount = String.valueOf(random.nextInt(100) + 1);
     String rawPrice = String.format("%.2f",(float)(0.1 + random.nextDouble() * (100.0 - 0.1)));
     String[] priceSplitted = rawPrice.split(",");
     String price = priceSplitted[0] + "," + priceSplitted[1];

    @Parameters("browser")
    @BeforeTest
    public void testSetUp(String browser) {
        driver = DriverManager.getConfiguredDriver(browser);
    }


    @Test(dataProvider = "LoginProvider")
    public void testCase1(String login, String password) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        WebElement emailInput = driver.findElement(By.id("email"));
        emailInput.sendKeys(login);
        // Filling password-field:
        WebElement passwordInput = driver.findElement(By.id("passwd"));
        passwordInput.sendKeys(password);
        // Submit form:
        WebElement submitButton = driver.findElement(By.name("submitLogin"));
        submitButton.click();
        // Clicking the buttons on the main menu:
        /*WebElement katalog = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.linkText("Каталог")));*/
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Каталог")));
        WebElement katalog = driver.findElement(By.linkText("Каталог"));
        WebElement categories = driver.findElement(By.id("subtab-AdminProducts"));
        Actions actions = new Actions(driver);
        actions.moveToElement(katalog).pause(Duration.ofSeconds(5)).click(categories)
                .build().perform();
        // Waiting for the page load:
        /*wait.until((ExpectedCondition<Boolean>) wdriver -> ((JavascriptExecutor) driver).executeScript(
                "return document.readyState"
        ).equals("complete"));*/
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("page-header-desc-configuration-add")));
        WebElement newProduct = driver.findElement(By.id("page-header-desc-configuration-add"));
        newProduct.click();
        // Waiting for the page load:
        /*wait.until((ExpectedCondition<Boolean>) wdriver -> ((JavascriptExecutor) driver).executeScript(
                "return document.readyState"
        ).equals("complete"));*/
        // Filling the forms (name, amount, price):
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("form_step1_name_1")));
        WebElement productName = driver.findElement(By.id("form_step1_name_1"));
        productName.sendKeys(product);
        System.out.println("form1 OK " + product);
        WebElement productAmount = driver.findElement(By.id("form_step1_qty_0_shortcut"));
        productAmount.clear();
        productAmount.sendKeys(amount);
        System.out.println("form2 OK " + amount);
        WebElement productPrice = driver.findElement(By.id("form_step1_price_shortcut"));
        productPrice.clear();
        productPrice.sendKeys(price);
        System.out.println("form3 OK " + price);
        //Clicking the checkbox
        driver.findElement(By.className("switch-input")).click();
        System.out.println("checkbox OK");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.growl-close")));
        WebElement popup1 = driver.findElement(By.cssSelector("div.growl-close"));
        popup1.click();
        System.out.println("popup1 OK");
        //wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        driver.findElement(By.id("submit")).click();
        System.out.println("clicked!");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.growl-close")));
        WebElement popup2 = driver.findElement(By.cssSelector("div.growl-close"));
        popup2.click();
        System.out.println("popup2 OK");


    }

    @Test(dependsOnMethods = {"testCase1"})
    public void testCase2() {
        driver.get("http://prestashop-automation.qatestlab.com.ua/");
        WebDriverWait wait = new WebDriverWait(driver, 30);
       /* wait.until((ExpectedCondition<Boolean>) wdriver -> ((JavascriptExecutor) driver).executeScript(
                "return document.readyState"
        ).equals("complete"));*/
        //#content > section > a
        // //*[@id="content"]/section/a
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#content > section > a")));
        driver.findElement(By.cssSelector("#content > section > a")).click();
        System.out.println("clicked again!");
        String xPath = "//a[text()[contains(.,'" + product + "')]]";
        // Soft-assertion:
        SoftAssert softAssertion = new SoftAssert();
        softAssertion.assertTrue(driver.findElement(By.xpath(xPath)).isDisplayed());
        System.out.println("FOUNDED");
        driver.findElement(By.xpath(xPath)).click();
        softAssertion.assertEquals(driver.findElement(By.className("h1")).getText(), product.toUpperCase(), "Unexpected product-name");
        String currPrice = driver.findElement(By.className("current-price")).getText();
        softAssertion.assertTrue(currPrice.startsWith(price));
        // //*[@id="product-details"]/div[1]/span
        softAssertion.assertTrue(driver.findElement(By.xpath("//*[@id=\"product-details\"]/div[1]/span")).
                getText().startsWith(amount));
        softAssertion.assertAll();
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "LoginProvider")
    public Object[][] getDataFromDataprovider() {
        return new Object[][]
                {
                        {"webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw"}
                };

    }
    private String generateString() {
        return RandomStringUtils.randomAlphabetic(10);
    }
}
