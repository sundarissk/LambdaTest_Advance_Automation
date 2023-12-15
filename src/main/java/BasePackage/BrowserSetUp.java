package BasePackage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BrowserSetUp {
	// public WebDriver driver;

	/*
	 * driver = new ChromeDriver(); driver.manage().window().maximize();
	 * driver.get("https://www.lambdatest.com/");
	 */

	public static String username = "sundariksen";
	public static String accesskey = "hC8ef7bSe7tUTJ32lZYF8Xdk1np7iHbaLOmZDFO4Hx14SnVwrK";
	public static RemoteWebDriver driver = null;
	public static String gridURL = "@hub.lambdatest.com/wd/hub";
	public static DesiredCapabilities browserOptions = new DesiredCapabilities();

	@BeforeMethod
	@Parameters(value = { "browser", "version", "platform" })
	public void SetUp(String browser, String version, String platform) {
		if (browser.equalsIgnoreCase("chrome")) {

			HashMap<String, Object> ltOptions = new HashMap<String, Object>();
			ltOptions.put("browserName", browser);
			ltOptions.put("version", version);
			ltOptions.put("platform", platform);

			ltOptions.put("build", "AdvanceAutomationTesting");
			ltOptions.put("project", "AdvanceAutomationTesting");
			ltOptions.put("name", "AdvanceAutomationTestingChrome");

			ltOptions.put("idleTimeout", "20");

			ltOptions.put("visual", true);
			ltOptions.put("video", true);
			ltOptions.put("network", true);
			ltOptions.put("w3c", true);
			ltOptions.put("console", "true");

			browserOptions.setCapability("LT:Options", ltOptions);
		} else if (browser.equalsIgnoreCase("Microsoft Edge")) {

			HashMap<String, Object> ltOptions = new HashMap<String, Object>();
			ltOptions.put("browserName", browser);
			ltOptions.put("version", version);
			ltOptions.put("platform", platform);

			ltOptions.put("build", "AdvanceAutomationTesting");
			ltOptions.put("project", "AdvanceAutomationTesting");
			ltOptions.put("name", "AdvanceAutomationTestingEdge");

			ltOptions.put("idleTimeout", "20");

			ltOptions.put("visual", true);
			ltOptions.put("video", true);
			ltOptions.put("network", true);
			ltOptions.put("w3c", true);
			ltOptions.put("console", "true");

			browserOptions.setCapability("LT:Options", ltOptions);
		}
		try {
			driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), browserOptions);

		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		driver.manage().window().maximize();
		driver.get(" https://www.lambdatest.com/");

	}

	@AfterMethod
	public void TearDown() {
		driver.close();
		driver.quit();
	}
}
