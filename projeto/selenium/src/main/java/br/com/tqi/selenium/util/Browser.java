package br.com.tqi.selenium.util;

public enum Browser {

	FIREFOX(""), //
	CHROME_MAC("chromedriver_mac"), //
	CHROME_LINUX32("chromedriver_linux32"), //
	CHROME_LINUX64("chromedriver_linux64"), //
	CHROME_WINDOWS("chromedriver.exe"), //
	IE("IEDriverServer.exe");

	private String driver;

	private Browser(String driver) {
		this.driver = driver;
	}

	public String getDriver() {
		return driver;
	}

}
