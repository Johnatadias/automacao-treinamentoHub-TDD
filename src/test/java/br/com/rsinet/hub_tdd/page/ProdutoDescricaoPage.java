package br.com.rsinet.hub_tdd.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProdutoDescricaoPage {

	@FindBy(how = How.XPATH, using = "//*[@id=\"Description\"]/h1")
	public WebElement validandoProdutoEscolhido;

}