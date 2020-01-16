package humanityTests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import humanityPages.DashboardPage;
import humanityPages.HomePage;
import humanityPages.LoginPage;
import humanityPages.ProfilePage;

public class ProfileTest extends BasicTest {

	@BeforeTest
	public void setup() throws InterruptedException {
		super.setup();
		driver.get(this.baseUrl);
		Thread.sleep(2000);

		HomePage hp = new HomePage(driver);
		hp.getLoginBtn().click();
		Thread.sleep(2000);

		LoginPage lg = new LoginPage(driver);
		lg.singIn("bemin72323@jancloud.net", "NekaFirma");
		Thread.sleep(2000);
	}

	@Test(priority = 5)
	public void uploadRegularImage() throws InterruptedException {
		DashboardPage dp = new DashboardPage(driver);
		dp.goToProfile();
		Thread.sleep(3000);

		ProfilePage pp = new ProfilePage(driver);
		pp.goToEditSection();
		Thread.sleep(1000);
		pp.uploadImage("images\\imageSmallerThan4MB.png");
		Thread.sleep(2000);

		Assert.assertTrue(pp.isImageUploadedSuccessfuly());
	}

	@Test(priority = 10)
	public void uploadImageOver4Mb() throws InterruptedException {
		DashboardPage dp = new DashboardPage(driver);
		dp.goToProfile();
		Thread.sleep(2000);

		ProfilePage pp = new ProfilePage(driver);
		pp.goToEditSection();
		Thread.sleep(1000);
		pp.uploadImage("images\\Image5MB.jpg");
		Thread.sleep(2000);

		Assert.assertFalse(pp.isImageUploadedSuccessfuly());
	}
}
