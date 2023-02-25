package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KitchenProducts {
    WebDriver driver;
    public KitchenProducts(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@placeholder=\"Search by name...\"]")
    public WebElement SearchBox;

    @FindBy(xpath ="//div[text()=\"Prestige Stove\"]")
    public WebElement PrestigeStove;

    @FindBy(xpath = "//*[text()='14500.00']")
    public WebElement PrestigeStovePrice;

    @FindBy(xpath = "//div[text()=\"PrestigeKitchenSet\"]")
    public WebElement PrestigeKitchenSet;
}
