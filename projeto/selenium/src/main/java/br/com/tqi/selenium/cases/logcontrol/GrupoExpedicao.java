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

public class GrupoExpedicao {
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
		FileUtils.copyFile(scrFile, new File("c:\\Users\\isaias.goulart\\Desktop\\Logocontrol_Auto\\Grupo_Expedicao\\Criar_Grupo_Expedicao\\01_Login_ok.png"));

		// Acessar Grupo de Expedição
		driver.findElement(By.linkText("Cadastros")).click();
		driver.findElement(By.xpath("//li[@id='menu_register']/ul/li[2]/a/span")).click();// Gurpo expedição no menu
		//Screenshot
		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\Users\\isaias.goulart\\Desktop\\Logocontrol_Auto\\Grupo_Expedicao\\Criar_Grupo_Expedicao\\02_Grupo_Expedição.png"));

		// Aba Informações Básicas
		driver.findElement(By.id("j_idt73:j_idt78")).click();// Botão "Novo"
		driver.findElement(By.id("expedition-group-tab:client-select_input")).sendKeys("Unibanco");
		driver.findElement(By.xpath("//div[@id='expedition-group-tab:client-select_panel']/ul/li[1]")).click();// Cliente
		driver.findElement(By.id("expedition-group-tab:state-select_label")).click();// Estado
		driver.findElement(By.id("expedition-group-tab:state-select_14")).click();// Estado
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='expedition-group-tab:office-select']/div[3]/span")).click();// Agência
		driver.findElement(By.id("expedition-group-tab:office-select_1104")).click();// Agência
		driver.findElement(By.id("expedition-group-tab:name-input")).sendKeys("Teste-001");

		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\Users\\isaias.goulart\\Desktop\\Logocontrol_Auto\\Grupo_Expedicao\\Criar_Grupo_Expedicao\\03_Informações_Básicas.png"));

		// Aba Remetente
		driver.findElement(By.linkText("Remetente")).click();
		driver.findElement(By.id("expedition-group-tab:sender-name-input")).sendKeys("Rementente-Isaias");
		driver.findElement(By.id("expedition-group-tab:sender-email-input")).sendKeys("isaias.goulart@tqi.com.br");
		driver.findElement(By.id("expedition-group-tab:sender-zipcode-input")).clear();
		driver.findElement(By.id("expedition-group-tab:sender-zipcode-input")).sendKeys("38408188");
		driver.findElement(By.id("expedition-group-tab:sender-zipcode-input")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		driver.findElement(By.id("expedition-group-tab:sender-address-number-input")).clear();
		driver.findElement(By.id("expedition-group-tab:sender-address-number-input")).sendKeys("971");
		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\Users\\isaias.goulart\\Desktop\\Logocontrol_Auto\\Grupo_Expedicao\\Criar_Grupo_Expedicao\\04_Aba_Remetente.png"));

		driver.findElement(By.id("expedition-group-tab:j_idt183")).click();
		Thread.sleep(2000);
		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\Users\\isaias.goulart\\Desktop\\Logocontrol_Auto\\Grupo_Expedicao\\Criar_Grupo_Expedicao\\05_Remetente_Adicionado.png"));

		// Aba Aviso de Recebimento
		driver.findElement(By.linkText("Aviso de Recebimento")).click();
		//driver.findElement(By.xpath("//a[@href='#expedition-group-tab:return-receipt']")).click();
		//driver.findElement(By.xpath("//div[@id='expedition-group-tab']/ul/li[3]")).click();// Cliente
		driver.findElement(By.id("expedition-group-tab:return-receipt-name-input")).sendKeys("Rementente-Isaias");
		driver.findElement(By.id("expedition-group-tab:return-receipt-email-input")).sendKeys("isaias.goulart@tqi.com.br");
		driver.findElement(By.id("expedition-group-tab:return-receipt-zipcode-input")).clear();
		driver.findElement(By.id("expedition-group-tab:return-receipt-zipcode-input")).sendKeys("38408188");
		driver.findElement(By.id("expedition-group-tab:return-receipt-zipcode-input")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		driver.findElement(By.id("expedition-group-tab:return-receipt-address-number-input")).clear();
		driver.findElement(By.id("expedition-group-tab:return-receipt-address-number-input")).sendKeys("971");
		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\Users\\isaias.goulart\\Desktop\\Logocontrol_Auto\\Grupo_Expedicao\\Criar_Grupo_Expedicao\\06_Aba_Aviso_De_Recebimento.png"));

		driver.findElement(By.id("expedition-group-tab:j_idt280")).click();
		Thread.sleep(2000);
		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\Users\\isaias.goulart\\Desktop\\Logocontrol_Auto\\Grupo_Expedicao\\Criar_Grupo_Expedicao\\07_Aviso_Recebimento_Adicionado.png"));

		// Aba Logística Reversa
		driver.findElement(By.linkText("Logística Reversa")).click();
		driver.findElement(By.id("expedition-group-tab:reverse-logistics-name-input")).sendKeys("Rementente-Isaias");
		driver.findElement(By.id("expedition-group-tab:reverse-logistics-email-input")).sendKeys("isaias.goulart@tqi.com.br");
		driver.findElement(By.id("expedition-group-tab:reverse-logistics-zipcode-input")).clear();
		driver.findElement(By.id("expedition-group-tab:reverse-logistics-zipcode-input")).sendKeys("38408188");
		driver.findElement(By.id("expedition-group-tab:reverse-logistics-zipcode-input")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		driver.findElement(By.id("expedition-group-tab:reverse-logistics-address-number-input")).clear();
		driver.findElement(By.id("expedition-group-tab:reverse-logistics-address-number-input")).sendKeys("971");
		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\Users\\isaias.goulart\\Desktop\\Logocontrol_Auto\\Grupo_Expedicao\\Criar_Grupo_Expedicao\\08_Aba_Logística_Reversa.png"));

		driver.findElement(By.id("expedition-group-tab:j_idt376")).click();
		Thread.sleep(2000);
		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\Users\\isaias.goulart\\Desktop\\Logocontrol_Auto\\Grupo_Expedicao\\Criar_Grupo_Expedicao\\09_Logística_Reversa_Adicionado.png"));

		// Aba Campos Personalizados
		driver.findElement(By.linkText("Campos Personalizados")).click();
	    driver.findElement(By.id("expedition-group-tab:custom-field-name-input")).sendKeys("Teste-0001");
	    driver.findElement(By.xpath("//div[@id='expedition-group-tab:custom-field-type-select']/div[3]/span")).click();
	    driver.findElement(By.id("expedition-group-tab:custom-field-type-select_9")).click();
		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\Users\\isaias.goulart\\Desktop\\Logocontrol_Auto\\Grupo_Expedicao\\Criar_Grupo_Expedicao\\10_Aba_Campos_Personalizados.png"));

  	    driver.findElement(By.id("expedition-group-tab:j_idt443")).click();
		Thread.sleep(2000);
		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\Users\\isaias.goulart\\Desktop\\Logocontrol_Auto\\Grupo_Expedicao\\Criar_Grupo_Expedicao\\11_Campo_Personalizado_Adicionado.png"));

		driver.findElement(By.id("btnSave")).click();
		Thread.sleep(3000);

		//Tela de Confirmação
	    assertEquals("Registro salvo com sucesso.",driver.findElement(By.cssSelector("span.ui-messages-info-summary")).getText());
		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("c:\\Users\\isaias.goulart\\Desktop\\Logocontrol_Auto\\Grupo_Expedicao\\Criar_Grupo_Expedicao\\12_Salvo_com_sucesso.png"));
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