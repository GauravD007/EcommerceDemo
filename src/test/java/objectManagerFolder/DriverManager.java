package objectManagerFolder;
import DataProvider.ConfigFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import utils.Logging;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
public class DriverManager {
    public static WebDriver driver;
    public static WebDriver getDriver()
    {
        if(driver==null)
            createDriver();
        return driver;
    }
    public static void createDriver()
    {
        switch (ConfigFileReader.getBrowser().toUpperCase())
        {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver();
                Logging.info("chrome driver created");
                break;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
                Logging.info("Firefox driver created ");
                break;
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                driver= new EdgeDriver();
                Logging.info("Edge Driver created");
                break;
            case "SAFARI":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                Logging.info("safari Driver setup is done");
                break;
            default:
                System.out.println("No Matching browser is found");

                Logging.info("No Matching browser is found , closing the test execution run");
                System.exit(0);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigFileReader.getImplicitWait())));
        Logging.info("waiting for page to load completely");

        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(ConfigFileReader.getPageLoadTimeout()),TimeUnit.SECONDS);
        Logging.info("page is loaded");
        driver.manage().window().maximize();
        Logging.info("Browser window is maximized");

    }
}