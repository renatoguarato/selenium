package br.com.tqi.selenium.cases.logcontrol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EditaGrupoExpedicao {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://10.10.0.218:8080";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void testCategoria() throws Exception {
		// Login
		driver.get(baseUrl + "/logcontrol-web/");
		driver.findElement(By.id("loginUser")).clear();
		driver.findElement(By.id("loginUser")).sendKeys("zgoulart@gmail.com");
		driver.findElement(By.id("pswdUser")).clear();
		driver.findElement(By.id("pswdUser")).sendKeys("iniciar");
		driver.findElement(By.id("login")).click();
		//Screenshot
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\Users\\isaias.goulart\\Desktop\\Logocontrol_Auto\\Grupo_Expedicao\\Editar_Criar_Grupo_Expedicao\\01_Login_ok.png"));

		// Acessar Grupo de Expedição
		driver.findElement(By.linkText("Cadastros")).click();
		driver.findElement(By.xpath("//li[@id='menu_register']/ul/li[2]/a/span")).click();
		//Screenshot
		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\Users\\isaias.goulart\\Desktop\\Logocontrol_Auto\\Grupo_Expedicao\\Editar_Criar_Grupo_Expedicao\\02_Grupo_Expedição.png"));

		//Editar Registro (Alteração de endereço do remetente)
		driver.findElement(By.id("j_idt73:client-select_input")).sendKeys("Unibanco");
		driver.findElement(By.xpath("//div[@id='j_idt73:client-select_panel']/ul/li[1]")).click();
		driver.findElement(By.id("j_idt73:j_idt77")).click();
	    driver.findElement(By.id("datatable-form:j_idt84:3:j_idt88")).click();
		Thread.sleep(2000);
	    driver.findElement(By.linkText("Remetente")).click();
	    driver.findElement(By.id("expedition-group-tab:sender-datatable:0:j_idt191")).click();
		Thread.sleep(2000);
		//driver.findElement(By.id("expedition-group-tab:sender-address-number-input")).clear();
		driver.findElement(By.id("expedition-group-tab:sender-address-number-input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		driver.findElement(By.id("expedition-group-tab:sender-address-number-input")).sendKeys(Keys.DELETE);
		driver.findElement(By.id("expedition-group-tab:sender-address-number-input")).sendKeys("123");
		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\Users\\isaias.goulart\\Desktop\\Logocontrol_Auto\\Grupo_Expedicao\\Editar_Criar_Grupo_Expedicao\\03_Alteração_Endereço_Remetente.png"));
		driver.findElement(By.id("btnSave")).click();
		Thread.sleep(3000);

		//Tela de Confirmação
	    assertEquals("Registro salvo com sucesso.",driver.findElement(By.cssSelector("span.ui-messages-info-summary")).getText());
		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\Users\\isaias.goulart\\Desktop\\Logocontrol_Auto\\Grupo_Expedicao\\Editar_Criar_Grupo_Expedicao\\04_Salvo_com_sucesso.png"));
	}

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
	}