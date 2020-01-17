package br.com.rsinet.hub_tdd.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	
	//####################################################################
	//metodo de test cadastraUsuario
	@FindBy(id = "menuUserLink")
	private WebElement iconeUser;
	
	@FindBy(linkText = "CREATE NEW ACCOUNT")
	private WebElement createNewAccount;

	@FindBy(id = "menuUserLink")
	private WebElement usuarioCriado;
	
	public void clicaIconeUser() {
		iconeUser.click();
	}
	
	public void clicaCreateNewAccount() {
		createNewAccount.sendKeys(Keys.ENTER);
	}
	
	public String validandoUsuarioCriado(WebDriver driver) {
		/*setando um tempo para realizar o assertEquals*/
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 1000);");
		
		return usuarioCriado.getText();
	}
	
	//####################################################################
	//metodo de test procurarProdutoPelaHomePage	
	public void buscaCategoria(WebDriver driver, String categoria) {
		driver.findElement(By.id(categoria+"Img")).click();
	}

	//####################################################################
	//metodo de test procuraProdutoPelaLupaDePesquisa
	@FindBy(id = "menuSearch")
	private WebElement lupaPesquisa;
	
	@FindBy(id = "autoComplete")
	private WebElement nomeCategoria;
	
	public void inserirNomeCategoria(String categoria) {
		nomeCategoria.sendKeys(categoria + Keys.ENTER);
	}
	
	public void clicaLupaPesquisa() {
		lupaPesquisa.click();
	}
}
