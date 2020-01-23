package br.com.rsinet.hub_tdd.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultadoPesquisaPage extends BasePage{

	public ResultadoPesquisaPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/*##########################################################
	* metodo de test procuraProdutoExistentePelaLupaDePesquisa*/
	public CategoriaPage escolherProduto() {
		/*foi instanciando essa page para poder usar o metodo que serve para outros testes*/
		return new CategoriaPage(driver);
	}
	
	/*##########################################################
	* metodo de test procuraProdutoInexistentePelaLupaDePesquis*/
	@FindBy(xpath = "//*[@id=\"searchPage\"]/div[3]/div/label/span")
	private WebElement resultPesquisaProduto;
	
	public String validandoResult() {
		/*setando um tempo para poder gerar a screenshot por completa*/
		waitTime();
		
		return resultPesquisaProduto.getText();
	}
}
