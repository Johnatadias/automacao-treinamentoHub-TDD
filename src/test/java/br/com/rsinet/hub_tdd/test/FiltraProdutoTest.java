package br.com.rsinet.hub_tdd.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
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
	private HomePage homePage;
	private ResultadoPesquisaPage resultadoPesquisaPage;
	private ProdutoDescricaoPage produtoDescPage;
	@Rule
	public TestName testName = new TestName();

	@Before
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

		String categoriaDoProduto = ExcelUtils.getCellData(3, 0);
		String produto = ExcelUtils.getCellData(3, 1);
		String assertProduto = ExcelUtils.getCellData(3, 2);

		homePage.inserirNomeCategoria.sendKeys(categoriaDoProduto + Keys.ENTER);
		resultadoPesquisaPage.escolherProduto(driver, produto);

		// Validandp se produto foi escolhido corretamente
		assertEquals(assertProduto, produtoDescPage.validandoProdutoEscolhido.getText());
	}

	@Test
	public void procuraProdutoInexistentePelaLupaDePesquisa() throws Exception {

		String categoriaDoProduto = ExcelUtils.getCellData(10, 0);
		String assertProduto = ExcelUtils.getCellData(10, 1);

		homePage.inserirNomeCategoria.sendKeys(categoriaDoProduto + Keys.ENTER);

		// Validandp se produto foi escolhido corretamente
		assertEquals(assertProduto, resultadoPesquisaPage.validandoResult.getText());
	}

	@After
	public void finaliza() throws IOException {
		Screenshot.gerarScreenShot(driver, testName);
		Web.killChromer(driver);
	}
}
