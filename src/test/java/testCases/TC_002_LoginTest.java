package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

	@Test(groups = {"Sanity","Master"})

	public void verifyLogin() {
		logger.info("Starting TC_02_Login Test");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();

			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetpage = macc.isMyAccountPageExists();

			Assert.assertTrue(targetpage);

			logger.info("TC_02 Finished");
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("TC_02 Finished");
	}
}
