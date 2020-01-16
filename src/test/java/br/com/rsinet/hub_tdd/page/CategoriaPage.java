package br.com.rsinet.hub_tdd.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoriaPage {
	
	public void escolherProdutoDaCategoria(WebDriver driver, String produto) {
		driver.findElement(By.linkText(produto)).click();
	}
}
