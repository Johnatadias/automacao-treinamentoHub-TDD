package br.com.rsinet.hub_tdd.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {
	
	//####################################################################
	//metodo de test cadastraUsuario
	@FindBy(how = How.ID, using = "menuUserLink")
	private WebElement iconeUser;
	
	@FindBy(how = How.LINK_TEXT, using = "CREATE NEW ACCOUNT")
	private WebElement createNewAccount;

	@FindBy(how = How.ID, using = "menuUserLink")
	private WebElement usuarioCriado;
	
	public void clicaIconeUser() {
		iconeUser.click();
	}
	
	public void clicaCreateNewAccount() {
		createNewAccount.sendKeys(Keys.ENTER);
	}
	
	public String validandoUsuarioCriado() {
		return usuarioCriado.getText();
	}
	
	//####################################################################
	//metodo de test procurarProdutoPelaHomePage	
	public void buscaCategoria(WebDriver driver, String categoria) {
		driver.findElement(By.id(categoria+"Img")).click();
	}

	//####################################################################
	//metodo de test procuraProdutoPelaLupaDePesquisa
	@FindBy(how = How.ID, using = "menuSearch")
	private WebElement lupaPesquisa;
	
	@FindBy(how = How.ID, using = "autoComplete")
	private WebElement nomeCategoria;
	
	public void inserirNomeCategoria(String categoria) {
		nomeCategoria.sendKeys(categoria + Keys.ENTER);
	}
	
	public void clicaLupaPesquisa() {
		lupaPesquisa.click();
	}
}
