package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Sprots {
    WebDriver driver;
    public Sprots(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@placeholder=\"Search by name...\"]")
    public WebElement SearchBox;

    @FindBy(xpath = "//div[text()=\"SG Bal\"]")
    public WebElement SGBall;

    @FindBy(xpath = "//div[text()=\"SG Bat\"]")
    public WebElement SGBat;

    @FindBy(xpath = "//*[text()='25500.00']")
    public WebElement SGBatPrice;

    @FindBy(xpath = "//div[text()=\"Cricket Batting Pad\"]")
    public WebElement CricketBattingPad;
}
