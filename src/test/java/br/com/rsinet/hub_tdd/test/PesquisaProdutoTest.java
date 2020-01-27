package br.com.rsinet.hub_tdd.test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import br.com.rsinet.hub_tdd.pageFactory.CategoriaPage;
import br.com.rsinet.hub_tdd.pageFactory.HomePage;
import br.com.rsinet.hub_tdd.pageFactory.ProdutoDescricaoPage;
import br.com.rsinet.hub_tdd.suport.DriverFactory;
import br.com.rsinet.hub_tdd.suport.ExcelUtils;
import br.com.rsinet.hub_tdd.suport.Report;
import br.com.rsinet.hub_tdd.utils.ExcelMassaDeDados;

public class PesquisaProdutoTest {

	private WebDriver driver;
	private HomePage homePage;
	private CategoriaPage categoriaPage;
	private ProdutoDescricaoPage produtoDescPage;
	private ExtentTest test;
	private ExcelMassaDeDados excel;

	@BeforeTest
	public void setConfigReport() {
		/*setando o reporte e enviando a string definindo o nome do arquivo report deste teste*/
		Report.setReport();
	}

	@BeforeMethod
	public void inicializa() throws Exception {
		/*setando chromedriver*/
		driver = DriverFactory.createChromer();

		/*definindo as PageFactory usada neste teste*/
		homePage = new HomePage(driver);
		categoriaPage = new CategoriaPage(driver);
		produtoDescPage = new ProdutoDescricaoPage(driver);

		/*setando as configurações da classe excel responsavel pela leitura da massa de dados*/
		ExcelUtils.setExcelFile("Produtos");
		
		/*instanciando a class responsavel por armazenar a massa de dados do excel*/
		excel = new ExcelMassaDeDados();
	}

	@Test
	public void deveProcurarProdutoPorHomePage() throws Exception {
		/*definindo teste para o report*/
		test = Report.createTest("deveProcurarProdutoPorHomePage");

		/*ações*/
		homePage.buscaCategoria(excel.getCategoriaDoProdutoHomePage());
		categoriaPage.escolherProdutoDaCategoria(excel.getProdutoEscolhido());

		/*Validando se produto foi escolhido corretamente*/
		assertEquals(excel.getAssertProdutoEscolhido(), produtoDescPage.validandoProdutoEscolhido());
	}

	@Test
	public void naoDeveAddMaisDezProdutoNoCarrinhoDeCompras() throws Exception {
		/*definindo teste para o report*/
		test = Report.createTest("naoDeveAddMaisDezProdutoNoCarrinhoDeCompras");

		/*ações*/
		homePage.buscaCategoria(excel.getCategoriaDoProdutoParaAdd());
		categoriaPage.escolherProdutoDaCategoria(excel.getProdutoEscolhidoParaAdd());
		produtoDescPage.inserindoQtd(excel.getQuantidadeProduto());
		produtoDescPage.clicarBtnAddProduto();

		/*Validando se produto foi escolhido corretamente*/
		assertEquals(excel.getAssertMensagem(), produtoDescPage.validandoMensagemError());
	}

	@AfterMethod
	public void finaliza(ITestResult result) throws IOException {
		/*condição para definir o status do teste no report*/
		Report.statusReported(test, result, driver);

		/*fechando*/
		DriverFactory.quitChrome(driver);
	}
	
	@AfterTest
	public void finalizaReport() {
		Report.quitExtent();
	}
}
