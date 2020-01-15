package br.com.rsinet.hub_tdd.test;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import br.com.rsinet.hub_tdd.page.CategoriaPage;
import br.com.rsinet.hub_tdd.page.HomePage;
import br.com.rsinet.hub_tdd.page.ProdutoDescricaoPage;
import br.com.rsinet.hub_tdd.suport.ExcelUtils;
import br.com.rsinet.hub_tdd.suport.Screenshot;
import br.com.rsinet.hub_tdd.suport.Web;

public class PesquisaProdutoTest {

	private WebDriver driver;
	private HomePage homePage;
	private CategoriaPage categoriaPage;
	private ProdutoDescricaoPage produtoDescPage;

	@BeforeMethod
	public void inicializa() throws Exception {
		driver = Web.createChromer();

		homePage = PageFactory.initElements(driver, HomePage.class);
		categoriaPage = PageFactory.initElements(driver, CategoriaPage.class);
		produtoDescPage = PageFactory.initElements(driver, ProdutoDescricaoPage.class);

		ExcelUtils.setExcelFile("target/dadosParaTest/massaDeDadosTestes.xlsx", "Produtos");
	}

	@Test
	public void deveProcurarProdutoPorHomePage() throws Exception {

		String categoriaDoProduto = ExcelUtils.getCellData(2, 0);
		String produto = ExcelUtils.getCellData(2, 1);
		String assertProduto = ExcelUtils.getCellData(2, 2);

		homePage.buscaCategoria(driver, categoriaDoProduto);
		categoriaPage.escolherProdutoDaCategoria(driver, produto);

		// Validando se produto foi escolhido corretamente
		assertEquals(assertProduto, produtoDescPage.validandoProdutoEscolhido.getText());

		Screenshot.gerarScreenShot(driver, "deveProcurarProdutoPorHomePage");
	}
	
	@Test
	public void naoDeveAddMaisDezProdutoNoCarrinhoDeCompras() throws Exception {

		String categoriaDoProduto = ExcelUtils.getCellData(2, 0);
		String produto = ExcelUtils.getCellData(2, 1);
		String quantidadeProduto = "999";
		String assertMensagem = ExcelUtils.getCellData(4, 2);

		homePage.buscaCategoria(driver, categoriaDoProduto);
		categoriaPage.escolherProdutoDaCategoria(driver, produto);
		produtoDescPage.quantidadeProdutos.sendKeys(quantidadeProduto);
		produtoDescPage.btnAddProduto.click();

		// Validando se produto foi escolhido corretamente
		assertEquals(assertMensagem, produtoDescPage.validandoMensagemError.getText());
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scrollBy(0,200)", "");

		Screenshot.gerarScreenShot(driver, "naoDeveAddMaisDezProdutoNoCarrinhoDeCompras");
	}

	@AfterMethod
	public void finaliza() {
		Web.killChromer(driver);
	}
}
