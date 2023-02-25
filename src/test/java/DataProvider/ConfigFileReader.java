package DataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private static Properties properties;
    private static final String ConfigFilePath="src/main/resources/config.properties";
    static
    {
        BufferedReader bufferedReader;
        try
        {
            bufferedReader= new BufferedReader(new FileReader(ConfigFilePath));
            properties=new Properties();
            try {
                properties.load(bufferedReader);
                bufferedReader.close();

            }
            catch (IOException ioException)
            {
                System.out.println("Exception while reading the file:"+ioException);
            }
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            System.out.println("file is not present at the defined location:"+fileNotFoundException);
        }
    }
    public static String geturl()
    {
        String siteUrl= properties.getProperty("url");
        if(siteUrl!=null)
            return siteUrl;
        else throw new RuntimeException("url is not defined in to config.properties file");
    }
    public static String getMode()
    {
        String headlessModeValue = properties.getProperty("headless");
        if(headlessModeValue!=null)
            return headlessModeValue;
        else throw new RuntimeException("Headless mode is not defined in to config.properties file");
    }
    public static String getBrowser()
    {
        String browser = properties.getProperty("browser");
        if(browser!=null)
            return browser;
        else throw new RuntimeException("browser value is not defined in to config.properties file");
    }
    public static String getImplicitWait()
    {
        String implicitWait = properties.getProperty("implicitWait");
        if(implicitWait!=null)
            return implicitWait;
        else throw new RuntimeException("implicitWait is not defined in to config.properties file");
    }
    public static String getPageLoadTimeout()
    {

        String pageLoadTimeout = properties.getProperty("pageLoadTimeout");
        if(pageLoadTimeout!=null)
            return pageLoadTimeout;
        else throw new RuntimeException("pageLoadTimeout is not defined in to config.properties file");
    }
    public static String getTestDataFilePath()
    {
        String testDataFilePath = properties.getProperty("testDatafile");
        if(testDataFilePath!=null)
            return testDataFilePath;
        else throw new RuntimeException("testDataFilePath is not defined in to config.properties file ");
    }
    public static String getUserName()
    {
        String userName = properties.getProperty("username");
        if (userName!=null)
            return userName;
        else throw new RuntimeException("username is not defined in to config.properties file");
    }
    public static String getPassword()
    {
        String password = properties.getProperty("password");
        if (password!=null)
            return password;
        else throw new RuntimeException("password is not defined in to config.properties file");
    }
}

