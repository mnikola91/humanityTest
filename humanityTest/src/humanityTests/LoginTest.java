package humanityTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import humanityPages.DashboardPage;
import humanityPages.EmployeePage;
import humanityPages.HomePage;
import humanityPages.LoginPage;
import humanityPages.TitlePage;
import humanityUtils.ExcelUtils;

public class LoginTest extends BasicTest {

	private String baseUrl = "https://www.humanity.com/";

	@Test(priority = 0)
	public void basicLogin() throws InterruptedException {
		driver.get(baseUrl);
		Thread.sleep(2000);

		HomePage hp = new HomePage(driver);
		hp.getLoginBtn().click();
		Thread.sleep(2000);

		LoginPage lg = new LoginPage(driver);
		lg.singIn("bemin72323@jancloud.net", "NekaFirma");
		Thread.sleep(2000);

		TitlePage tp = new TitlePage(driver);

		Assert.assertTrue(tp.getPageTitle().contains("Dashboard"));
	}

	@Test(priority = 1)
	public void dashboardTest() throws InterruptedException {

		DashboardPage dp = new DashboardPage(driver);
		Assert.assertTrue(dp.getNavLinks().size() > 0);
	}

	@Test(priority = 20)
	public void addEmployeeTest() throws InterruptedException {
		driver.get("https://sdn1.humanity.com/app/dashboard/");
		Thread.sleep(2000);

		DashboardPage dp = new DashboardPage(driver);
		dp.getNavLinks().get(5).click();
		Thread.sleep(2000);

		EmployeePage ep = new EmployeePage(driver);
		ep.getAddEmployeeBtn().click();
		Thread.sleep(2000);

		ExcelUtils ex = new ExcelUtils();
		ex.setExcell("utils-files\\radnici.xlsx");
		ex.setWorkSheet(0);

		for (int i = 1; i < 4; i++) {
			ep.addEmployee(ex.getDataAt(i, 0), ex.getDataAt(i, 1), ex.getDataAt(i, 2)); 
		}
		ep.addEmployee("Petar", "Petrovic", "petarp@email.com");
		ep.addEmployee("Marko", "Markovic", "markom@email.com");
		ep.addEmployee("Milos", "Milosevic", "milosm@email.com");

		ep.getSaveBtn().click();
		Thread.sleep(2000);

		dp.getNavLinks().get(5).click(); 
		Thread.sleep(2000);

		for (int i = 0; i < 5; i++) {
			Assert.assertTrue(ep.isEmployeeAdded(ex.getDataAt(i, 2)));
		}

		ex.closeExcell();
	}
}
