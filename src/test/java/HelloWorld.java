import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().version("87.0.4280.88").setup();
      WebDriver  driver = new ChromeDriver();
        driver.get("https://chromedriver.chromium.org/downloads");

	}

}
