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

import br.com.rsinet.hub_tdd.pageFactory.HomePage;
import br.com.rsinet.hub_tdd.pageFactory.ProdutoDescricaoPage;
import br.com.rsinet.hub_tdd.pageFactory.ResultadoPesquisaPage;
import br.com.rsinet.hub_tdd.suport.DriverFactory;
import br.com.rsinet.hub_tdd.suport.ExcelUtils;
import br.com.rsinet.hub_tdd.suport.Report;
import br.com.rsinet.hub_tdd.utils.ExcelMassaDeDados;

public class FiltraProdutoTest {

	private WebDriver driver;
	private HomePage homePage;
	private ResultadoPesquisaPage resultadoPesquisaPage;
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
		resultadoPesquisaPage = new ResultadoPesquisaPage(driver);
		produtoDescPage = new ProdutoDescricaoPage(driver);

		/*setando as configurações da classe excel responsavel pela leitura da massa de dados*/
		ExcelUtils.setExcelFile("Produtos");
		
		/*instanciando a class responsavel por armazenar a massa de dados do excel*/
		excel = new ExcelMassaDeDados();
		
		/*ação para iniciar ambos testes desta classe*/
		homePage.clicaLupaPesquisa();
	}

	@Test
	public void procuraProdutoExistentePelaLupaDePesquisa() throws Exception {
		/*definindo teste para o report*/
		test = Report.createTest("procuraProdutoExistentePelaLupaDePesquisa");

		/*ação*/
		homePage.inserirNomeCategoria(excel.getCategoriaExistente());
		resultadoPesquisaPage.escolherProduto().escolherProdutoDaCategoria(excel.getProdutoExistente());

		/*Validandp se produto foi escolhido corretamente*/
		assertEquals(excel.getAssertProdutoExistente(), produtoDescPage.validandoProdutoEscolhido());
	}

	@Test
	public void procuraProdutoInexistentePelaLupaDePesquisa() throws Exception {
		/*definindo teste para o report*/
		test = Report.createTest("procuraProdutoInexistentePelaLupaDePesquisa");

		/*ação*/
		homePage.inserirNomeCategoria(excel.getProdutoInexistente());

		/*Validandp se produto não foi encontrado*/
		assertEquals(excel.getAssertProdutoInexistente(), resultadoPesquisaPage.validandoResult());
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
