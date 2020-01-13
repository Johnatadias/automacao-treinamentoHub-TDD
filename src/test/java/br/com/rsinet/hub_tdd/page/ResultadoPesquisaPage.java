package br.com.rsinet.hub_tdd.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ResultadoPesquisaPage {

	CategoriaPage categoriaPage = new CategoriaPage();

	public ProdutoDescricaoPage escolherProduto(WebDriver driver, String produto) {
		categoriaPage.escolherProdutoDaCategoria(driver, produto);
		return new ProdutoDescricaoPage();
	}
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"searchPage\"]/div[3]/div/label/span")
	public WebElement validandoResult;
}
