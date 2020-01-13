package br.com.rsinet.hub_tdd.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import br.com.rsinet.hub_tdd.page.HomePage;
import br.com.rsinet.hub_tdd.page.ProdutoDescricaoPage;
import br.com.rsinet.hub_tdd.page.ResultadoPesquisaPage;
import br.com.rsinet.hub_tdd.suport.ExcelUtils;
import br.com.rsinet.hub_tdd.suport.Screenshot;
import br.com.rsinet.hub_tdd.suport.Web;

public class FiltraProdutoTest {

	private WebDriver driver;

	@Before
	public void inicializa() {
		driver = Web.createChromer();
	}

	@Test
	public void procuraProdutoExistentePelaLupaDePesquisa() throws Exception {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ResultadoPesquisaPage resultadoPesquisaPage = PageFactory.initElements(driver, ResultadoPesquisaPage.class);
		ProdutoDescricaoPage produtoDescPage = PageFactory.initElements(driver, ProdutoDescricaoPage.class);
		
		ExcelUtils.setExcelFile("target/massaDeDados/procuraProduto.xlsx", "Plan1");

		// ##################################################################
		String categoriaDoProduto = ExcelUtils.getCellData(3, 0);
		String produto = ExcelUtils.getCellData(3, 1);
		String assertProduto = ExcelUtils.getCellData(3, 2);

		homePage.clicaLupaPesquisa.click();
		homePage.inserirNomeCategoria.sendKeys(categoriaDoProduto + Keys.ENTER);
		resultadoPesquisaPage.escolherProduto(driver, produto);

		// Validandp se produto foi escolhido corretamente
		assertEquals(assertProduto, produtoDescPage.validandoProdutoEscolhido.getText());
		Screenshot.gerarScreenShot(driver);
	}
	
	@Test
	public void procuraProdutoInexistentePelaLupaDePesquisa() throws Exception {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		ResultadoPesquisaPage resultadoPesquisaPage = PageFactory.initElements(driver, ResultadoPesquisaPage.class);
		
		ExcelUtils.setExcelFile("target/massaDeDados/procuraProduto.xlsx", "Plan1");

		// ##################################################################
		String categoriaDoProduto = ExcelUtils.getCellData(8, 0);
		String assertProduto = ExcelUtils.getCellData(8, 1);

		homePage.clicaLupaPesquisa.click();
		homePage.inserirNomeCategoria.sendKeys(categoriaDoProduto + Keys.ENTER);

		// Validandp se produto foi escolhido corretamente
		assertEquals(assertProduto, resultadoPesquisaPage.validandoResult.getText());
		Screenshot.gerarScreenShot(driver);
	}

	@After
	public void finaliza() {
		Web.killChromer(driver);
	}
}
