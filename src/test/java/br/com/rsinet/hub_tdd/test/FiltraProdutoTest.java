package br.com.rsinet.hub_tdd.test;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import br.com.rsinet.hub_tdd.page.HomePage;
import br.com.rsinet.hub_tdd.page.ProdutoDescricaoPage;
import br.com.rsinet.hub_tdd.page.ResultadoPesquisaPage;
import br.com.rsinet.hub_tdd.suport.ExcelUtils;
import br.com.rsinet.hub_tdd.suport.Screenshot;
import br.com.rsinet.hub_tdd.suport.Web;

public class FiltraProdutoTest {

	private WebDriver driver;
	private HomePage homePage;
	private ResultadoPesquisaPage resultadoPesquisaPage;
	private ProdutoDescricaoPage produtoDescPage;

	@BeforeMethod
	public void inicializa() throws Exception {
		driver = Web.createChromer();

		homePage = PageFactory.initElements(driver, HomePage.class);
		resultadoPesquisaPage = PageFactory.initElements(driver, ResultadoPesquisaPage.class);
		produtoDescPage = PageFactory.initElements(driver, ProdutoDescricaoPage.class);

		ExcelUtils.setExcelFile("target/dadosParaTest/massaDeDadosTestes.xlsx", "Produtos");

		homePage.clicaLupaPesquisa.click();
	}

	@Test
	public void procuraProdutoExistentePelaLupaDePesquisa() throws Exception {

		String categoriaDoProduto = ExcelUtils.getCellData(1, 0);
		String produto = ExcelUtils.getCellData(1, 1);
		String assertProduto = ExcelUtils.getCellData(1, 2);

		homePage.inserirNomeCategoria.sendKeys(categoriaDoProduto + Keys.ENTER);
		resultadoPesquisaPage.escolherProduto(driver, produto);

		// Validandp se produto foi escolhido corretamente
		assertEquals(assertProduto, produtoDescPage.validandoProdutoEscolhido.getText());
		
		Screenshot.gerarScreenShot(driver, "procuraProdutoExistentePelaLupaDePesquisa");
	}

	@Test
	public void procuraProdutoInexistentePelaLupaDePesquisa() throws Exception {

		String categoriaDoProduto = ExcelUtils.getCellData(9, 0);
		String assertProduto = ExcelUtils.getCellData(9, 1);

		homePage.inserirNomeCategoria.sendKeys(categoriaDoProduto + Keys.ENTER);

		// Validandp se produto não foi encontrado
		assertEquals(assertProduto, resultadoPesquisaPage.validandoResult.getText());
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 1000);");
		
		Screenshot.gerarScreenShot(driver, "procuraProdutoInexistentePelaLupaDePesquisa");
	}

	@AfterMethod
	public void finaliza(){
		Web.killChromer(driver);
	}
}
