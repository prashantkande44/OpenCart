package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

	@Test(groups = {"Master","Regression"})
	public void verify_account_registration() {

		logger.info("***** Starting TC001_AccountRegistrationTest  ****");
		logger.debug("This is a debug log message");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Click on MyAccount");
			hp.clickRegister();
			logger.info("Click on Register");

			// Initialize account registration page
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

			logger.info("Fill registration details");
			regpage.setFirstName(randomString().toUpperCase());
			regpage.setLastName(randomString().toUpperCase());
			regpage.setEmail(randomString() + "@gmail.com");
			regpage.setTelephone(randomNumeric());
			String password = randomAlphaNumeric();
			regpage.setPassword(password);
			regpage.setConfirmPassword(password);
			regpage.setPrivacyPolicy();
			regpage.clickContinue();

			logger.info("Verify the confirmation message");
			String confmsg = regpage.getConfirmationMsg();
			if (confmsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
			logger.info("Test passed");

		} catch (Exception e) {
			logger.error("Test failed");
			Assert.fail();
			
		} finally {
			logger.info("Finished TC001_AccountRegistrationTest");
		}
	}

}
