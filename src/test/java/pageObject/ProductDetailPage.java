package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage {
    public static WebDriver driver;
    public ProductDetailPage(WebDriver driver)
    {
        this.driver=driver;

        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[text()=\"Add To Cart\"]")
    public WebElement AddtoCart;

    @FindBy(xpath = "//a[text()=\"Go To Cart\"]")
    public WebElement GoToCart;

    @FindBy(xpath = "//a[text()='Cart']")
    public WebElement CartBtn;

    @FindBy(xpath = "//div[text()='s21']")
    public WebElement s21NameonProductPage;

    @FindBy(xpath = "//div[text()='65000']")
    public WebElement s21PriceonProductPage;

    @FindBy(xpath = "//div[text()=\"iPhone15\"]")
    public WebElement iphoneName;

    @FindBy(xpath = "//div[text()=\"89000\"]")
    public WebElement iphonePrice;

    @FindBy(xpath = "//a[text()=' ECommerce']")
    public WebElement homePage;


}
