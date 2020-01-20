package br.com.rsinet.hub_tdd.pageFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class BasePage {

	protected WebDriver driver;
	private JavascriptExecutor js;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void waitTime() {
		js = (JavascriptExecutor) driver;
		js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 1000);");
	}
	
	public void scrollDown() {
		js = (JavascriptExecutor) driver;
		js.executeScript("scrollBy(0,200)", "");
	}
	
	public void scrollUp() {
		js = (JavascriptExecutor)driver;
		js.executeScript("scrollBy(0,-380)", "");
	}
}
