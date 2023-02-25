package Scripts;
import Context.context;
import DataProvider.ConfigFileReader;
import DataProvider.ReadWriteExcel;
import extentReport.ExtentReport;
import objectManagerFolder.DriverManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pageObject.*;
import utils.Logging;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestCase {
    WebDriver driver;
    LoginPage loginPage;
    relevelHomePage RelevelHomePage;

    Allproducts allproducts;
    Electronics electronics;
    KitchenProducts kitchenProducts;
    Sprots sprots;
    ProductDetailPage productDetailPage;
    CartDetails cartDetails;

    ExtentReport extentReport;
    context Context;
    ReadWriteExcel readWriteExcel;
    SoftAssert softAssert;

    @BeforeSuite
    public void beforeSuitSetup() throws IOException {
        driver = DriverManager.getDriver();
        driver.get(ConfigFileReader.geturl());
        RelevelHomePage = new relevelHomePage(driver);
        electronics = new Electronics(driver);
        kitchenProducts = new KitchenProducts(driver);
        sprots = new Sprots(driver);
        productDetailPage = new ProductDetailPage(driver);
        cartDetails = new CartDetails(driver);
        allproducts = new Allproducts(driver);
        loginPage = new LoginPage(driver);
        extentReport = new ExtentReport();
        readWriteExcel = new ReadWriteExcel();
        Context = new context();
        softAssert = new SoftAssert();
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
    }

    @AfterSuite
    public void afterSuit() {
        softAssert.assertAll();
        extentReport.flush();
        driver.quit();
    }

    @BeforeMethod
    public void startTestCase() {
        Logging.info("Starting Test Case");
    }

    @AfterMethod
    public void endTestCase() {
        Logging.info("End of Test Case");
    }

    @Test(description = "TC-001 : This test case is to verify logIn with black credentials", enabled = true)
    public void LoginTestWithBlackCredentials() throws IOException {
        extentReport.createTest("TC-001 : This test case is to verify logIn with black credentials");
        RelevelHomePage.Loginlink.click();
        Logging.info("User clicked on Login button of Home Page");
        extentReport.info("user click on login button of login page");
        loginPage.loginbtn.click();
        Logging.info("user click on login button of login page");
        extentReport.info("user click on login button of login page");
        if ((driver.findElements(By.xpath("//div[text()='Username and Password are required!!']"))).size() > 0) {
            String errorMessage = loginPage.ErrorMsg.getText();
            extentReport.info("Username and Password are required!! message is displayed");
            Logging.info("Username and Password are required!! message is displayed");
            softAssert.assertEquals(errorMessage, "Username and Password are required!!");
            extentReport.pass("Error message is displayed when user login with blank credentials");
            extentReport.addScreenshot(driver);
            Logging.info("Error message is displayed when user login with blank credentials");
            Logging.endTestCase();
        } else {
            extentReport.fail("Username and Password are required!! message is not displayed ");
            extentReport.addScreenshot(driver);
            Logging.error("Username and Password are required!! message is not displayed");
        }

    }

    @Test(description = "TC002-This test verifies that user can login and logout with valid credentials", enabled = true)
    public void loginwithcorrectcredintials() throws IOException {
        XSSFSheet sheet = readWriteExcel.getXssfSheet("Sheet1");
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            extentReport.createTest("TC002-This test verifies that user can login and logout with valid credentials");
            RelevelHomePage.Loginlink.click();
            extentReport.info("user on login page");
            Logging.info("user on login page");
            loginPage.usernamebtn.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
            extentReport.info("user entered username");
            Logging.info("user entered username");
            loginPage.passwordbtn.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
            extentReport.info("user entered password");
            Logging.info("user entered password");
            loginPage.loginbtn.click();
            extentReport.info("user click on login");
            Logging.info("user click on login");

            if (RelevelHomePage.LogOutbtn.isDisplayed()) {
                extentReport.info("user login successfully and log out button displayed");
                Logging.info("user login successfully and log out button displayed");
                extentReport.addScreenshot(driver);
                extentReport.pass("user log in with correct credentials");
                Logging.info("user log in with correct credentials");
                RelevelHomePage.LogOutbtn.click();
                Logging.endTestCase();
            } else {
                extentReport.info("user login unsuccessfully and log out button not displayed");
                Logging.info("user login unsuccessfully and log out button not displayed");
                extentReport.addScreenshot(driver);
                extentReport.fail("user can not log in with correct credentials");
                Logging.error("user can not log in with correct credentials");
                Logging.endTestCase();
            }
        }
    }

    @Test(description = "TC003-This test verifies user can search for a product", enabled = true)
    public void SearchForProduct() throws IOException, InterruptedException {
        XSSFSheet sheet = readWriteExcel.getXssfSheet("Sheet3");
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            extentReport.createTest("TC003-This test verifies user can search for a product");
            switch (sheet.getRow(i).getCell(0).getStringCellValue()) {
                case "Electronics":
                    RelevelHomePage.electroniclink.click();
                    extentReport.info("user click on electronic button of home page");
                    Logging.info("user click on electronic button of home page");
                    electronics.SearchBox.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
                    extentReport.info("user search for electronic product");
                    Logging.info("user search for electronic product");
                    String actualProductName = electronics.s21.getText();
                    String expectProductName = sheet.getRow(i).getCell(1).getStringCellValue();
                    Double actualProductPrice = Double.parseDouble(electronics.s21Price.getText());
                    System.out.println(actualProductPrice);
                    double expectedProductPrice = (sheet.getRow(i).getCell(2).getNumericCellValue());
                    if (actualProductName.equals(expectProductName)) {
                        softAssert.assertEquals(actualProductPrice, expectedProductPrice, "prices are not equal as expected");
                        if (actualProductPrice == expectedProductPrice) {
                            extentReport.info("prices are equal as expected");
                            Logging.info("prices are equal as expected");
                            extentReport.pass("User able to search for electronic product");
                            extentReport.addScreenshot(driver);
                            Logging.info("User able to search for electronic product");
                        } else {
                            extentReport.info("prices are not equal as expected");
                            Logging.info("prices are not equal as expected");
                            extentReport.fail("User is not able to search for electronic product");
                            extentReport.addScreenshot(driver);
                            Logging.error("User is not able to search for electronic product");
                        }
                    } else {
                        extentReport.info("product name is not equal as expected");
                        Logging.info("product name is not equal as expected");
                        extentReport.fail("Product details does not equal as expected");
                        Logging.error("Product details does not equal as expected");
                        extentReport.addScreenshot(driver);
                    }
                    break;

                case "Kitchen Items":
                    RelevelHomePage.KitchenItemslink.click();
                    extentReport.info("user click on Kitchen Items button of Home page");
                    Logging.info("user click on Kitchen Items button of Home page");
                    kitchenProducts.SearchBox.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
                    extentReport.info("user search for a product in kitchen items");
                    Logging.info("user search for a product in kitchen items");
                    String actualProductName1 = kitchenProducts.PrestigeStove.getText();
                    String expectedProductName1 = sheet.getRow(i).getCell(1).getStringCellValue();
                    Double actualPrdocutPrice1 = Double.parseDouble(kitchenProducts.PrestigeStovePrice.getText());
                    double expectedProductPrice1 = sheet.getRow(i).getCell(2).getNumericCellValue();
                    if (actualProductName1.equals(expectedProductName1)) {
                        softAssert.assertEquals(actualPrdocutPrice1, expectedProductPrice1, "Prices of product is not as expected");
                        if (actualPrdocutPrice1 == expectedProductPrice1) {
                            extentReport.info("product prices are as expected");
                            Logging.info("product prices are as expected");
                            extentReport.pass("user is able to search for kitchen product");
                            extentReport.addScreenshot(driver);
                            Logging.info("user is able to search for kitchen product");
                        } else {
                            extentReport.info("product prices are not as expected");
                            Logging.info("product prices are not as expected");
                            extentReport.fail("user is unable to search for kitchen products");
                            extentReport.addScreenshot(driver);
                            Logging.error("user is unable to search for kitchen products");
                        }
                    } else {
                        extentReport.info("product name is not equal as expected");
                        Logging.info("product name is not equal as expected");
                        extentReport.fail("product details are not equal as expected");
                        Logging.error("product details are not equal as expected");
                        extentReport.addScreenshot(driver);
                    }
                    break;

                case "Sports":
                    RelevelHomePage.Sportslink.click();
                    extentReport.info("user click on sports button of Home page");
                    Logging.info("user click on sports button of Home page");
                    kitchenProducts.SearchBox.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
                    extentReport.info("user search for a product in sports");
                    Logging.info("user search for a product in sports");
                    String actualProductName2 = sprots.SGBat.getText();
                    String expectedProductName2 = sheet.getRow(i).getCell(1).getStringCellValue();
                    Double actualPrdocutPrice2 = Double.parseDouble(sprots.SGBatPrice.getText());
                    double expectedProductPrice2 = sheet.getRow(i).getCell(2).getNumericCellValue();
                    if (actualProductName2.equals(expectedProductName2)) {
                        softAssert.assertEquals(actualPrdocutPrice2, expectedProductPrice2, "Prices of product is not as expected");
                        if (actualPrdocutPrice2 == expectedProductPrice2) {
                            extentReport.info("product prices are as expected");
                            Logging.info("product prices are as expected");
                            extentReport.pass("user is able to search for sports product");
                            extentReport.addScreenshot(driver);
                            Logging.info("user is able to search for sports product");
                        } else {
                            extentReport.info("product prices are not as expected");
                            Logging.info("product prices are not as expected");
                            extentReport.fail("user is unable to search for sports products");
                            extentReport.addScreenshot(driver);
                            Logging.error("user is unable to search for sports products");
                        }
                    } else {
                        extentReport.info("product name is not equal as expected");
                        Logging.info("product name is not equal as expected");
                        extentReport.fail("product details are not equal as expected");
                        Logging.error("product details are not equal as expected");
                        extentReport.addScreenshot(driver);

                    }
                    break;

                default:
                    extentReport.info("No matching category is found");
                    Logging.info("No matching category is found");
                    extentReport.fail("matching category is not found , unable to search the product");
                    Logging.info("matching category is not found , unable to search the product");
                    extentReport.addScreenshot(driver);
            }
            driver.get(ConfigFileReader.geturl());
        }
        Logging.endTestCase();

    }

    @Test(description = "TC004-This test verifies user can remove product from cart", enabled = true)
    public void removeProductFromCart() throws IOException, InterruptedException {
        XSSFSheet sheet = readWriteExcel.getXssfSheet("Sheet1");
        extentReport.createTest("TC004-This test verifies user can remove product from cart");
        RelevelHomePage.Loginlink.click();
        extentReport.info("user click on login button of home page");
        Logging.info("user click on login button of home page");
        loginPage.usernamebtn.sendKeys(sheet.getRow(1).getCell(0).getStringCellValue());
        extentReport.info("user entered userid");
        Logging.info("user entered userid");
        loginPage.passwordbtn.sendKeys(sheet.getRow(1).getCell(1).getStringCellValue());
        extentReport.info("user entered password");
        Logging.info("user entered password");
        loginPage.loginbtn.click();
        extentReport.info("user click on login button of login page");
        Logging.info("user click on login button of login page");
        RelevelHomePage.electroniclink.click();
        extentReport.info("user click on electronic category");
        Logging.info("user click on electronic category");
        electronics.s21.click();
        extentReport.info("user click on s21 product");
        Logging.info("user click on s21 product");
        Thread.sleep(5000);
        productDetailPage.AddtoCart.click();
        extentReport.info("user click on add to cart button on product detail page" );
        Logging.info("user click on add to cart button on product detail page" );
        productDetailPage.GoToCart.click();
        extentReport.info("user click on go to cart button on product detail page");
        Logging.info("user click on go to cart button on product detail page");
        String ProductNameonCartPage=cartDetails.s21NameonCart.getText();
        String ProductPriceonCartPage = cartDetails.s21PriceonCart.getText();
        if(productDetailPage.s21NameonProductPage.equals(ProductNameonCartPage))
        {
            extentReport.info("Product name in cart is equal as product name on product detail page");
            Logging.info("Product name in cart is equal as product name on product detail page");
            if (productDetailPage.s21PriceonProductPage.equals(ProductPriceonCartPage))
            {
                extentReport.info("Product price is same as product price in product detail page");
                Logging.info("Product price is same as product price in product detail page");
            }
            else
            {
                extentReport.info("Product price is not same as shown in product detail page");
                Logging.info("Product price is not same as shown in product detail page");
            }
        }
        else
        {
            extentReport.info("Product name in cart is not equal as product name on product detail page");
            Logging.info("Product name in cart is not equal as product name on product detail page");
        }
        Thread.sleep(5000);
        cartDetails.removebtn.click();
        extentReport.info("user click on remove button on cart page");
        Logging.info("user click on remove button on cart page");
        if (cartDetails.emptycart.isDisplayed())
        {

                String emptyCartMessage = cartDetails.emptycart.getText();
                softAssert.assertEquals(emptyCartMessage,"Your cart is empty!","empty cart text is not same" );
                extentReport.info("cart is empty after removing the product");
                Logging.info("user is able to remove the item from cart");
                extentReport.pass("user is able to remove the item from cart");
                extentReport.addScreenshot(driver);
                Logging.endTestCase();
        }
        else
        {
                extentReport.info("cart is not empty after removing the product");
                extentReport.fail("user is not able to remove the item from cart");
                Logging.info("user is not able to remove the item from cart");
                extentReport.addScreenshot(driver);
        }

    }
    @Test(description = "TC005-This test verify user can order product",enabled = true)
    public void userCanBuyProduct() throws IOException, InterruptedException {
        XSSFSheet sheet= readWriteExcel.getXssfSheet("Sheet1");
        extentReport.createTest("TC005-This test verify user can order product");
        RelevelHomePage.Loginlink.click();
        extentReport.info("user on login page");
        Logging.info("user on login page");
        loginPage.usernamebtn.sendKeys(sheet.getRow(1).getCell(0).getStringCellValue());
        extentReport.info("user entered username on login page");
        Logging.info("user entered username on login page");
        loginPage.passwordbtn.sendKeys(sheet.getRow(1).getCell(1).getStringCellValue());
        extentReport.info("user entered password on login page");
        Logging.info("user entered password on login page");
        loginPage.loginbtn.click();
        extentReport.info("user click on login button on login page");
        Logging.info("user user click on login button  login page");

        RelevelHomePage.electroniclink.click();
        extentReport.info("user click on electronic");
        Logging.info("user click on electronic");
        electronics.iPhone15.click();
        extentReport.info("user click iphone product on electronic page");
        Logging.info("user click iphone product on electronic page");
        Thread.sleep(5000);
        productDetailPage.AddtoCart.click();
        extentReport.info("user click add to cart on product details page");
        Logging.info("user click add to cart on product details page");
        Thread.sleep(5000);
        productDetailPage.GoToCart.click();
        extentReport.info("user click on cart");
        Logging.info("user click on cart");
        cartDetails.checkOutbtn.click();
        extentReport.info("user click check out button on cart details page");
        Logging.info("user click on check out button on cart details page");
        Thread.sleep(5000);
        String expectedName=productDetailPage.iphoneName.getText();
        String expectedPrice=productDetailPage.iphonePrice.getText();

        String actualName=cartDetails.iphoneName.getText();
        String actualPrice=cartDetails.iphonePrice.getText();
        if(expectedName.equals(actualName))
        {
            extentReport.info("user get same name on both page");
            Logging.info("user get same name on both page");
            if (expectedPrice.equals(actualPrice))
            {
               extentReport.info("user get same price on both page");
               Logging.info("user get same price on both page");
            }
            else {
                extentReport.info("user get not same price on both page");
                Logging.info("user get not same price on both page");
                 }
            }
          else
          {
              extentReport.info("user get not same name on both page");
              Logging.info("user get not same name on both page");
          }
        cartDetails.confirmbtn.click();
        extentReport.info("user click on confirm button on cart details page");
        Logging.info("user click on confirm button on cart details page");

          if (cartDetails.orderconfirmsg.isDisplayed())
          {
              extentReport.info("user order confirm msg displayed");
              Logging.info("user order confirm msg displayed");
              extentReport.pass("user order product successfully");
              Logging.info("user order product successfully");
              extentReport.addScreenshot(driver);
              Logging.endTestCase();
          }
          else
          {
              extentReport.info("user order confirm msg not displayed");
              Logging.info("user order confirm msg not displayed");
              extentReport.fail("user order product unsuccessfully");
              Logging.error("user order product unsuccessfully");
              extentReport.addScreenshot(driver);
              Logging.endTestCase();
          }
    }
}


