package br.com.rsinet.hub_tdd.test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import br.com.rsinet.hub_tdd.pageFactory.CategoriaPage;
import br.com.rsinet.hub_tdd.pageFactory.HomePage;
import br.com.rsinet.hub_tdd.pageFactory.ProdutoDescricaoPage;
import br.com.rsinet.hub_tdd.suport.ExcelUtils;
import br.com.rsinet.hub_tdd.suport.Report;
import br.com.rsinet.hub_tdd.suport.WebFactory;

public class PesquisaProdutoTest {

	private WebDriver driver;
	private HomePage homePage;
	private CategoriaPage categoriaPage;
	private ProdutoDescricaoPage produtoDescPage;
	private ExtentTest test;
	private ExtentReports extent;

	@BeforeTest
	public void setConfigReport() {
		/*setando o reporte e enviando a string definindo o nome do arquivo report deste teste*/
		extent = Report.setReport("pesquisaProduto_report");
	}

	@BeforeMethod
	public void inicializa() throws Exception {
		/*setando chromedriver*/
		driver = WebFactory.createChromer();

		/*definindo as PageFactory usada neste teste*/
		homePage = new HomePage(driver);
		categoriaPage = new CategoriaPage(driver);
		produtoDescPage = new ProdutoDescricaoPage(driver);

		/*setando as configurações da classe excel responsavel pela leitura da massa de dados*/
		ExcelUtils.setExcelFile("Produtos");
	}

	@Test
	public void deveProcurarProdutoPorHomePage() throws Exception {
		/*definindo teste para o report*/
		test = Report.createTest("deveProcurarProdutoPorHomePage");

		/*massa para o teste*/
		String categoriaDoProduto = ExcelUtils.getCellData(2, 0);
		String produto = ExcelUtils.getCellData(2, 1);
		String assertProduto = ExcelUtils.getCellData(2, 2);

		/*ações*/
		homePage.buscaCategoria(categoriaDoProduto);
		categoriaPage.escolherProdutoDaCategoria(produto);

		/*Validando se produto foi escolhido corretamente*/
		assertEquals(assertProduto, produtoDescPage.validandoProdutoEscolhido());
	}

	@Test
	public void naoDeveAddMaisDezProdutoNoCarrinhoDeCompras() throws Exception {
		/*definindo teste para o report*/
		test = Report.createTest("naoDeveAddMaisDezProdutoNoCarrinhoDeCompras");

		/*massa para o teste*/
		String categoriaDoProduto = ExcelUtils.getCellData(2, 0);
		String produto = ExcelUtils.getCellData(2, 1);
		String quantidadeProduto = ExcelUtils.getCellData(4, 3);
		String assertMensagem = ExcelUtils.getCellData(4, 2);

		/*ações*/
		homePage.buscaCategoria(categoriaDoProduto);
		categoriaPage.escolherProdutoDaCategoria(produto);
		produtoDescPage.inserindoQtd(quantidadeProduto);
		produtoDescPage.clicarBtnAddProduto();

		/*Validando se produto foi escolhido corretamente*/
		assertEquals(assertMensagem, produtoDescPage.validandoMensagemError());
	}

	@AfterMethod
	public void finaliza(ITestResult result) throws IOException {
		/*condição para definir o status do teste no report*/
		Report.statusReported(test, result, driver);

		/*fechando*/
		Report.quitExtent(extent);
		WebFactory.quitChrome(driver);
	}
}
