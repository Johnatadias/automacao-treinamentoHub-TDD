package br.com.rsinet.hub_tdd.test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import br.com.rsinet.hub_tdd.page.HomePage;
import br.com.rsinet.hub_tdd.page.ProdutoDescricaoPage;
import br.com.rsinet.hub_tdd.page.ResultadoPesquisaPage;
import br.com.rsinet.hub_tdd.suport.ExcelUtils;
import br.com.rsinet.hub_tdd.suport.Report;
import br.com.rsinet.hub_tdd.suport.Web;

public class FiltraProdutoTest {

	private WebDriver driver;
	private HomePage homePage;
	private ResultadoPesquisaPage resultadoPesquisaPage;
	private ProdutoDescricaoPage produtoDescPage;
	private ExtentTest test;
	private ExtentReports extent;

	@BeforeTest
	public void setConfigReport() {
		extent = Report.setReport("filtraProduto_report");
	}

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
		test = Report.createTest("procuraProdutoExistentePelaLupaDePesquisa");

		String categoriaDoProduto = ExcelUtils.getCellData(1, 0);
		String produto = ExcelUtils.getCellData(1, 1);
		String assertProduto = ExcelUtils.getCellData(1, 2);

		homePage.inserirNomeCategoria.sendKeys(categoriaDoProduto + Keys.ENTER);
		resultadoPesquisaPage.escolherProduto(driver, produto);

		// Validandp se produto foi escolhido corretamente
		assertEquals(assertProduto, produtoDescPage.validandoProdutoEscolhido.getText());
	}

	@Test
	public void procuraProdutoInexistentePelaLupaDePesquisa() throws Exception {
		test = Report.createTest("procuraProdutoInexistentePelaLupaDePesquisa");

		String categoriaDoProduto = ExcelUtils.getCellData(9, 0);
		String assertProduto = ExcelUtils.getCellData(9, 1);

		homePage.inserirNomeCategoria.sendKeys(categoriaDoProduto + Keys.ENTER);

		// Validandp se produto não foi encontrado
		assertEquals(assertProduto, resultadoPesquisaPage.validandoResult.getText());

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 2000);");
	}

	@AfterMethod
	public void finaliza(ITestResult result) throws IOException {
		Report.statusReported(test, result, driver);

		Report.killExtent(extent);
		Web.killChromer(driver);
	}
}
