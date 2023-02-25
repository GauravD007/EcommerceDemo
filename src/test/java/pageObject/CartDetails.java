package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartDetails {
    public static WebDriver driver;
    public CartDetails(WebDriver driver)
    {
        this.driver=driver;

        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[text()=\"Remove\"]")
    public WebElement removebtn;

    @FindBy(xpath = "//div[text()=' Your cart is empty! ']")
    public WebElement emptycart;

    @FindBy(xpath = "//div[text()='s21']")
    public WebElement s21NameonCart;

    @FindBy(xpath = "//div[text()='65000.00']")
    public WebElement s21PriceonCart;

    @FindBy(xpath = "//a[text()=\"Checkout\"]")
    public WebElement checkOutbtn;

    @FindBy(xpath = "//div[text()=\"Confirm Payment\"]")
    public WebElement confirmbtn;

    @FindBy(xpath = "//div[text()=\"Order Confirmed\"]")
    public WebElement orderconfirmsg;

    @FindBy(xpath = "//div[text()=\"iPhone15\"]")
    public WebElement iphoneName;

    @FindBy(xpath = "//div[text()=\"89000\"]")
    public WebElement iphonePrice;

}
