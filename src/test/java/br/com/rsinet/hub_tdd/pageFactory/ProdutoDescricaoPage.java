package br.com.rsinet.hub_tdd.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProdutoDescricaoPage extends BasePage{
	
	public ProdutoDescricaoPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id=\"Description\"]/h1")
	private WebElement produtoEscolhido;
	
	public String validandoProdutoEscolhido() {
		return produtoEscolhido.getText();
	}	
	
	/*##########################################################
	* metodo de test naoDeveAddMaisDezProdutoNoCarrinhoDeCompras*/
	
	@FindBy(name = "quantity")
	private WebElement quantidadeProdutos;
	
	@FindBy(name = "save_to_cart")
	private WebElement btnAddProduto;
	
	public void inserindoQtd (String qtd) {
		quantidadeProdutos.sendKeys(qtd);
	}
	
	public void clicarBtnAddProduto() {
		btnAddProduto.click();
	}
	
	@FindBy(xpath = "//*[@id=\"productProperties\"]/label")
	private WebElement MensagemError;
	
	public String validandoMensagemError() {
		/*usando o scroll para obter a mensagem de erro*/
		scrollDown();
		
		return MensagemError.getText();
	}
}