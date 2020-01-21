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

import br.com.rsinet.hub_tdd.pageFactory.HomePage;
import br.com.rsinet.hub_tdd.pageFactory.ProdutoDescricaoPage;
import br.com.rsinet.hub_tdd.pageFactory.ResultadoPesquisaPage;
import br.com.rsinet.hub_tdd.suport.ExcelUtils;
import br.com.rsinet.hub_tdd.suport.Report;
import br.com.rsinet.hub_tdd.suport.DriverFactory;

public class FiltraProdutoTest {

	private WebDriver driver;
	private HomePage homePage;
	private ResultadoPesquisaPage resultadoPesquisaPage;
	private ProdutoDescricaoPage produtoDescPage;
	private ExtentTest test;
	private ExtentReports extent;

	@BeforeTest
	public void setConfigReport() {
		/*setando o reporte e enviando a string definindo o nome do arquivo report deste teste*/
		extent = Report.setReport("filtraProduto_report");
	}

	@BeforeMethod
	public void inicializa() throws Exception {
		/*setando chromedriver*/
		driver = DriverFactory.createChromer();

		/*definindo as PageFactory usada neste teste*/
		homePage = new HomePage(driver);
		resultadoPesquisaPage = new ResultadoPesquisaPage(driver);
		produtoDescPage = new ProdutoDescricaoPage(driver);

		/*setando as configurações da classe excel responsavel pela leitura da massa de dados*/
		ExcelUtils.setExcelFile("Produtos");
		
		/*ação para iniciar ambos testes desta classe*/
		homePage.clicaLupaPesquisa();
	}

	@Test
	public void procuraProdutoExistentePelaLupaDePesquisa() throws Exception {
		/*definindo teste para o report*/
		test = Report.createTest("procuraProdutoExistentePelaLupaDePesquisa");

		/*massa para o teste*/
		String categoriaDoProduto = ExcelUtils.getCellData(1, 0);
		String produto = ExcelUtils.getCellData(1, 1);
		String assertProduto = ExcelUtils.getCellData(1, 2);

		/*ação*/
		homePage.inserirNomeCategoria(categoriaDoProduto);
		resultadoPesquisaPage.escolherProduto().escolherProdutoDaCategoria(produto);;

		/*Validandp se produto foi escolhido corretamente*/
		assertEquals(assertProduto, produtoDescPage.validandoProdutoEscolhido());
	}

	@Test
	public void procuraProdutoInexistentePelaLupaDePesquisa() throws Exception {
		/*definindo teste para o report*/
		test = Report.createTest("procuraProdutoInexistentePelaLupaDePesquisa");

		/*massa para o teste*/
		String categoriaDoProduto = ExcelUtils.getCellData(9, 0);
		String assertProduto = ExcelUtils.getCellData(9, 1);

		/*ação*/
		homePage.inserirNomeCategoria(categoriaDoProduto);

		/*Validandp se produto não foi encontrado*/
		assertEquals(assertProduto, resultadoPesquisaPage.validandoResult());
	}

	@AfterMethod
	public void finaliza(ITestResult result) throws IOException {
		/*condição para definir o status do teste no report*/
		Report.statusReported(test, result, driver);

		/*fechando*/
		Report.quitExtent(extent);
		DriverFactory.quitChrome(driver);
	}
}
