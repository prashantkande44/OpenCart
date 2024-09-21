package testBase;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups = {"Sanity","Regression","DataDriver","Master"})
	@Parameters({"os","browser"})
    public void setUp(String os,String br) throws IOException {
		
		//loading properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		
		//loading log4j file
		logger=LogManager.getLogger(this.getClass());
		
		switch(br.toLowerCase())
		{
		case "chrome" :driver = new ChromeDriver();break;
		case "edge" :driver = new EdgeDriver();break;
		case "firefox" :driver = new FirefoxDriver();break;
		default : System.out.println("Invalied Browser......");
		return;
		
		}
		
		WebDriverManager.chromedriver().setup();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("appURL2"));  //Read From Properties File
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNumeric() {
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;

	}

	public String randomAlphaNumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(4);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return (generatedString+"@"+generatedNumber);

	}
	
	//Screensdhot Method
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}
	
	
}
