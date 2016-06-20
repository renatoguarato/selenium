package br.com.tqi.selenium.cases;

import org.junit.Test;
import org.openqa.selenium.By;

import br.com.tqi.selenium.BaseSelenium;
import br.com.tqi.selenium.util.Browser;

public class Exemplo extends BaseSelenium {

	@Test
	public void teste() throws Exception {
		configureDriver(Browser.FIREFOX);
		// configureDriver(Browser.CHROME_MAC);
		// configureDriver(Browser.CHROME_LINUX32);
		// configureDriver(Browser.CHROME_LINUX64);
		// configureDriver(Browser.CHROME_WINDOWS);
		// configureDriver(Browser.IE);

		accessPage("http://www.tqi.com.br");
		clickObject(By.id("testes"));
		clickObject(By.xpath("//a[@title='Extranet TQI']"));
	}
}
