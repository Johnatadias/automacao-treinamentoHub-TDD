package br.com.rsinet.hub_tdd.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProdutoDescricaoPage {
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"Description\"]/h1")
	private WebElement produtoEscolhido;
	
	public String validandoProdutoEscolhido() {
		return produtoEscolhido.getText();
	}	
	
	//##########################################################
	//metodo de test naoDeveAddMaisDezProdutoNoCarrinhoDeCompras
	
	@FindBy(how = How.NAME, using = "quantity")
	private WebElement quantidadeProdutos;
	
	@FindBy(how = How.NAME, using = "save_to_cart")
	private WebElement btnAddProduto;
	
	public void inserindoQtd (String qtd) {
		quantidadeProdutos.sendKeys(qtd);
	}
	
	public void clicarBtnAddProduto() {
		btnAddProduto.click();
	}
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"productProperties\"]/label")
	private WebElement MensagemError;
	
	public String validandoMensagemError() {
		return MensagemError.getText();
	}
}