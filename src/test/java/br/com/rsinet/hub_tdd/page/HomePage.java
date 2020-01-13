package br.com.rsinet.hub_tdd.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {
	
	//####################################################################
	//metodo de test cadastraUsuario
	
	@FindBy(how = How.ID, using = "menuUserLink")
	public WebElement clica_IconeUser;
	
	@FindBy(how = How.LINK_TEXT, using = "CREATE NEW ACCOUNT")
	public WebElement clica_CreateNewAccount;

	
	//####################################################################
	//metodo de test procurarProdutoPelaHomePage
	
	public HomePage buscaCategoria(WebDriver driver, String categoria) {
		driver.findElement(By.id(categoria+"Img")).click();
		return this;
	}

	
	//####################################################################
	//metodo de test procuraProdutoPelaLupaDePesquisa
	
	@FindBy(how = How.ID, using = "menuSearch")
	public WebElement clicaLupaPesquisa;
	
	@FindBy(how = How.ID, using = "autoComplete")
	public WebElement inserirNomeCategoria;
}
