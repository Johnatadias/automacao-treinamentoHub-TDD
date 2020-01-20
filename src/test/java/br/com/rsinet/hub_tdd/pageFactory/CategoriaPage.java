package br.com.rsinet.hub_tdd.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CategoriaPage extends BasePage{

	public CategoriaPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public ProdutoDescricaoPage escolherProdutoDaCategoria(String produto) {
		driver.findElement(By.linkText(produto)).click();
		
		return new ProdutoDescricaoPage(driver);
	}
}
