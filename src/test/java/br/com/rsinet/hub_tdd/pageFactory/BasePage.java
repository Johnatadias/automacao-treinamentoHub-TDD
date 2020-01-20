package br.com.rsinet.hub_tdd.pageFactory;

import org.openqa.selenium.WebDriver;

public class BasePage {

	WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
}
