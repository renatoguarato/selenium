package br.com.tqi.selenium;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.tqi.selenium.exception.MessageException;
import br.com.tqi.selenium.util.Browser;

public class BaseSelenium {

	public static Logger logger = Logger.getLogger(BaseSelenium.class);

	public static Browser browser;

	public static WebDriver driver;

	public static WebDriverWait wait;

	private static final Integer TIMEOUT = 5;

	/**
	 * Aguarda existência do objeto informado através de regex.
	 *
	 * @param expression
	 */
	public static void waitByXpath(String expression) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(expression)));
	}

	/**
	 * Aguarda existência do objeto informado através do ID.
	 *
	 * @param idObject
	 */
	public static void waitById(String idObject) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(idObject)));
	}

	/**
	 * Aguarda existência do objeto informado através do class.
	 *
	 * @param classObject
	 */
	public static void waitByClass(String classObject) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(classObject)));
	}

	/**
	 * Aguarda existência do objeto informado.
	 *
	 * @param object
	 */
	public static void waitObject(By object) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(object));
	}

	/**
	 * Obtém elemento select com o id informado.
	 *
	 * @param idObject
	 * @return
	 */
	public static Select selectElement(String idObject) {

		waitById(idObject);
		return new Select(driver.findElement(By.id(idObject)));
	}

	/**
	 * Abre a página informada.
	 *
	 * @param page
	 * @throws MessageException
	 */
	public static void accessPage(String page) throws MessageException {

		driver.get(page);
	}

	/**
	 * Realiza a ação de clique no objeto informado.
	 *
	 * @param object
	 * @throws MessageException
	 */
	public static void clickObject(By object) throws MessageException {

		waitObject(object);
		driver.findElement(object).click();
	}

	/**
	 * Realiza a ação de clique no objeto filho informado.
	 *
	 * @param object
	 * @param children
	 * @throws MessageException
	 */
	public static void clickChildrenObject(By object, By children) throws MessageException {

		waitObject(object);
		driver.findElement(object).findElement(children).click();
	}

	/**
	 * Realiza a ação de clique no objeto informado.
	 *
	 * @param object
	 * @throws MessageException
	 */
	public static void clickObjectAlert(By object) throws MessageException {

		waitObject(object);
		driver.findElement(object).click();
	}

	/**
	 * Fecha alert na tela de acordo com a ação informada. OK ou Cancel
	 *
	 * @param actionOK
	 * @return
	 */
	public static String closeAlertAndGetItsText(Boolean actionOK) {

		Alert alert = driver.switchTo().alert();
		String result = alert.getText();

		if (actionOK) {
			alert.accept();
		} else {
			alert.dismiss();
		}

		return result;
	}

	/**
	 * Realiza ação de drag and drop no objeto informado.
	 *
	 * @param object
	 */
	public static void dragAndDrop(By object) {

		Actions action = new Actions(driver);
		WebElement we = driver.findElement(object);
		action.moveToElement(we).build().perform();
	}

	/**
	 * Realiza ação de clicar, segurar e mover o objeto informado de acordo os
	 * eixos X e Y.
	 *
	 * @param object
	 * @param xAxis
	 * @param yAxis
	 */
	public void clickAndHold(By object, int xAxis, int yAxis) {

		WebElement slider = driver.findElement(object);
		Actions builder = new Actions(driver);
		Action action = builder.clickAndHold(slider).moveByOffset(xAxis, yAxis).release().build();
		action.perform();
	}

	/**
	 * Configura o driver utilizado para teste.
	 *
	 */
	public static void configureDriver(final Browser browser) {

		try {

			Assert.assertNotNull("Defina o navegador a ser utilizado", browser);

			logger.info("Configurando webDriver.");

			String PATH_DRIVER = "src/main/resources/drivers/%s";
			if (!browser.getDriver().equals("")) {
				System.setProperty("webdriver.chrome.driver", String.format(PATH_DRIVER, browser.getDriver()));
			}

			if (browser.name().startsWith("CHROME")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				driver = new ChromeDriver(options);
			}
			if (browser.name().startsWith("IE")) {
				driver = new InternetExplorerDriver();
				driver.manage().window().maximize();
			}
			if (browser.name().startsWith("FIREFOX")) {
				FirefoxProfile firefoxProfile = new FirefoxProfile();
				firefoxProfile.setPreference("browser.download.folderList", 0);
				firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "image/jpeg, text/csv");

				driver = new FirefoxDriver(firefoxProfile);
				driver.manage().window().maximize();
			}

			wait = new WebDriverWait(driver, TIMEOUT);
			driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		} catch (Exception e) {

			logger.error("Error configure driver: ", e);
		}
	}

	/**
	 * Realiza login no sistema Vericad.
	 *
	 * @throws Exception
	 */
	@Before
	public void setUp() {

		try {

			PropertyConfigurator.configure("src/main/resources/log4j.properties");

		} catch (Exception e) {

			logger.error("Error setup: ", e);
		}
	}

	/**
	 * Fecha browser após finalizar os testes.
	 *
	 */
	@After
	public void tearDown() {

		try {

			driver.close();
			driver.quit();
		} catch (Exception e) {

			logger.error("Error close driver: ", e);
		}
	}

	public boolean isAlertPresent() {

		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	/**
	 * Loga mensagem de erro e seta execução do método como fail.
	 *
	 * @param message
	 * @param error
	 */
	public static void assertMessageError(String message, Exception error) {

		logger.error(message, error);
		Assert.fail(error.getMessage());
	}

	/**
	 * Verifica a existência de algum WebElement.
	 *
	 * @param object
	 * @return <code>boolean</code>
	 */
	public boolean existsWebElement(By object) {

		WebElement element = null;
		try {
			element = driver.findElement(object);
		} catch (NoSuchElementException e) {
			logger.warn(e.getMessage(), e);
		}

		return element != null;
	}

}
