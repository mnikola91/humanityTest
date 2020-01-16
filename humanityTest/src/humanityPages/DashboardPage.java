package humanityPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage extends BasicPage {

	private By navLinks = By.cssSelector("ul#topMenu li");
	private By arrowMenu = By.id("wrap_us_menu");
	private By userMenuLinks = By.cssSelector(".userm>a");
	public DashboardPage(WebDriver driver) {
		super(driver);
	}

	public List<WebElement> getNavLinks() {
		return this.driver.findElements(navLinks);
	}
	
	public WebElement getArrowMenu() {
		return this.driver.findElement(arrowMenu);
	}
	public WebElement getProfileLink() {
		return this.driver.findElements(userMenuLinks).get(0);
	}
	
	public void goToProfile() {
		this.getArrowMenu().click();
		this.getProfileLink().click();
	}
}
