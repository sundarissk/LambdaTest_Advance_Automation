package TestCases;

import org.testng.annotations.Test;

import BasePackage.BrowserSetUp;
import ModulePackage.PageRepo;

public class AutomationTestCases extends BrowserSetUp {
	@Test
	public void test1() {

		PageRepo pr = new PageRepo(driver);

		pr.ScrollToIntegration();
		pr.switchTabsUsingPartOfUrl();
		pr.CommunityClick();

	}

}
