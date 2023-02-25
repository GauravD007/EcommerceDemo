package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class relevelHomePage {
    public static WebDriver driver;

   public relevelHomePage(WebDriver driver)
   {
       this.driver=driver;

       PageFactory.initElements(driver,this);
   }

   @FindBy(xpath = "//a[text()=\"Login\"]")
    public WebElement Loginlink;

    @FindBy(xpath = "//a[text()=\"Cart\"]")
    public WebElement Cartbtn;

    @FindBy(xpath = "//a[text()=\"All Products\"]")
    public WebElement allProductlink;

    @FindBy(xpath = "//a[text()=\"Kitchen Items\"]")
    public WebElement KitchenItemslink;

    @FindBy(xpath = "//a[text()=\"Electronics\"]")
    public WebElement electroniclink;

    @FindBy(xpath = "//a[text()=\"Sports\"]")
    public WebElement Sportslink;

    @FindBy(xpath = "//div[text()=\"Logout\"]")
    public WebElement LogOutbtn;


}
