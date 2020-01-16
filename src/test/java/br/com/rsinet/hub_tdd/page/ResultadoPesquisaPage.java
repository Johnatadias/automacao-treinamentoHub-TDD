package br.com.rsinet.hub_tdd.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ResultadoPesquisaPage {

	CategoriaPage categoriaPage = new CategoriaPage();

	//##########################################################
	//metodo de test procuraProdutoExistentePelaLupaDePesquisa
	public void escolherProduto(WebDriver driver, String produto) {
		categoriaPage.escolherProdutoDaCategoria(driver, produto);
	}
	
	//##########################################################
	//metodo de test procuraProdutoInexistentePelaLupaDePesquis
	@FindBy(how = How.XPATH, using = "//*[@id=\"searchPage\"]/div[3]/div/label/span")
	private WebElement resultPesquisaProduto;
	
	public String validandoResult() {
		return resultPesquisaProduto.getText();
	}
}
