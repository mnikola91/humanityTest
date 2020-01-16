package humanityPages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage extends BasicPage {
	
	private By imageUploadedSuccessfuly = By.id("fileupload_completedMessage");
	private By uploadInput = By.id("fileupload");
	private By sections = By.cssSelector(".EmployeeTop a");
	public ProfilePage(WebDriver driver) {
		super(driver);
	}
	
	public void goToEditSection() {
		this.driver.findElements(sections).get(1).click();
	}
	
	public WebElement getUploadInput() {
		return this.driver.findElement(uploadInput);
	}

	public void uploadImage(String path) {
		getUploadInput().sendKeys(new File(path).getAbsolutePath());
	}
	
	public boolean isImageUploadedSuccessfuly() {
		try {
			return this.driver.findElement(imageUploadedSuccessfuly) != null;
		} catch (Exception e) {
			return false;
		}
	}
}
