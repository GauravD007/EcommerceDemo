package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Electronics {
    WebDriver driver;
    public Electronics(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@placeholder=\"Search by name...\"]")
    public WebElement SearchBox;

    @FindBy(xpath = "//div[text()=\"iPhone15\"]")
    public WebElement iPhone15;

    @FindBy(xpath = "//div[text()=\"s21\"]")
    public WebElement s21;

    @FindBy(xpath = "//*[text()='65000.00']")
    public WebElement s21Price;

    @FindBy(xpath = "//div[text()=\"Mi12\"]")
    public WebElement MI12;
}
