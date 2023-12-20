package ModulePackage;

import static org.testng.Assert.assertTrue;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import BasePackage.Utility;

public class PageRepo {
	WebDriver driver;

	static final By allowcookis = By.xpath("//span[contains(text(),'Allow Cookie')]");
	static final By pageload1 = By.xpath("//div[@id='zsiq_float']");
	static final By pageload = By.xpath("//div[@id='zsiq_float']//img");
	static final By seeallint = By.xpath("//a[contains(text(),'See All Integrations')]//img");
	static final By codelessauto = By.xpath("//a[contains(text(),'Codeless Automation')]");
	static final By codeless = By.xpath("//h2[contains(text(),'Codeless Automation')]");

	static final By linktext = By.xpath("//a[contains(text(),'Integrate Testing Whiz with LambdaTest')]");

	static final By linkcomunity = By.linkText("Community");
	Utility ut = new Utility();

	public PageRepo(WebDriver driver) {
		this.driver = driver;
	}

	public void ScrollToIntegration() {
		try {

			ut.ExplicitWaitCondition(driver, pageload);

			WebElement ele = driver.findElement(seeallint);

			Actions action = new Actions(driver);
			action.moveToElement(ele).click().build().perform();

			System.out.println(driver.getCurrentUrl() + "********" + driver.getTitle());

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void switchTabsUsingPartOfUrl() {
		String currentHandle = null;
		String platform = "https://www.lambdatest.com/integrations";
		String title = "TestingWhiz Integration | LambdaTest";

		final Set<String> handles = driver.getWindowHandles();
		if (handles.size() > 1) {
			currentHandle = driver.getWindowHandle();
			for (final String handle : handles) {
				driver.switchTo().window(handle);

				if (driver.getCurrentUrl().contains(platform) && !currentHandle.equals(handle)) {
					System.out.println("Expected URL Passed with Child Window Opens");

					WebElement allow = driver.findElement(allowcookis);
					allow.click();

					ut.ExplicitWaitCondition(driver, pageload1);

					WebElement ele = driver.findElement(codeless);
					Actions action = new Actions(driver);
					action.scrollToElement(ele).perform();

					ut.ExplicitWaitCondition(driver, pageload);

					WebElement element = driver.findElement(linktext);
					Actions actions = new Actions(driver);
					actions.moveToElement(element).click().build().perform();

					String tit = driver.getTitle();

					System.out.println(title);
					System.out.println(tit);
					
					boolean status = false;
					if (title.equals(tit)) {
						System.out.println("Title Assertion Pass");
						status = true;
					}
					assertTrue(status, "Title Assertion Fails");
					System.out.println("Handles Count: " + handles.size());
				}
			}
		} else {
			System.out.println("No Child Window: URL For Main Window *** " + driver.getCurrentUrl());
			if (driver.getCurrentUrl().contains(platform)) {
				System.out.println("Expected URL Passed but No Child Window Opens");
				for (final String handle : handles) {
					driver.switchTo().window(handle);
					if (driver.getCurrentUrl().contains(platform)) {

						WebElement allow = driver.findElement(allowcookis);
						allow.click();

						ut.ExplicitWaitCondition(driver, pageload1);

						WebElement ele = driver.findElement(codeless);
						Actions action = new Actions(driver);
						action.scrollToElement(ele).perform();

						ut.ExplicitWaitCondition(driver, pageload);

						WebElement element = driver.findElement(linktext);
						Actions actions = new Actions(driver);
						actions.moveToElement(element).click().build().perform();

						String tit = driver.getTitle();

						System.out.println(title);
						System.out.println(tit);

						boolean status = false;
						if (title.equals(tit)) {
							System.out.println("Title Assertion Pass");
							status = true;

						}
						assertTrue(status, "Title Assertion Failed");
						System.out.println("Handles Count: " + handles.size());

					}
				}

			}

		}

	}

	public void CommunityClick() {
		String urlc = "https://community.lambdatest.com/";
		driver.get("https://www.lambdatest.com/blog");

		WebElement cele = driver.findElement(linkcomunity);
		cele.click();

		String urlcr = driver.getCurrentUrl();

		if (urlc.equals(urlcr)) {
			System.out.println("Community URL Matches");
		} else
			System.out.println("Community URL Not Match");

	}

}
