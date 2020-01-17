package br.com.rsinet.hub_tdd.pageFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultadoPesquisaPage {

	//##########################################################
	//metodo de test procuraProdutoExistentePelaLupaDePesquisa
	public void escolherProduto(WebDriver driver, String produto) {
		/*foi instanciando essa page para poder usar o metodo que serve para outros testes*/
		new CategoriaPage().escolherProdutoDaCategoria(driver, produto);
	}
	
	//##########################################################
	/*metodo de test procuraProdutoInexistentePelaLupaDePesquis*/
	@FindBy(xpath = "//*[@id=\"searchPage\"]/div[3]/div/label/span")
	private WebElement resultPesquisaProduto;
	
	public String validandoResult(WebDriver driver) {
		/*setando um tempo para poder gerar a screenshot por completa*/
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 2000);");
		
		return resultPesquisaProduto.getText();
	}
}
