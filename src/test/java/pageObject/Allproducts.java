package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Allproducts {
    public static WebDriver driver;

    public Allproducts(WebDriver driver)
    {
        this.driver=driver;

        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@placeholder=\"Search by name...\"]")
    public WebElement searchproduct;

    @FindBy(xpath = "//div[text()=\"SG Bat\"]")
    public WebElement sgBat;

}
