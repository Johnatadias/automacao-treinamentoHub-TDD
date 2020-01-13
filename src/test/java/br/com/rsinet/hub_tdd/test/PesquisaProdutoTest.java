package br.com.rsinet.hub_tdd.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import br.com.rsinet.hub_tdd.page.CategoriaPage;
import br.com.rsinet.hub_tdd.page.HomePage;
import br.com.rsinet.hub_tdd.page.ProdutoDescricaoPage;
import br.com.rsinet.hub_tdd.suport.ExcelUtils;
import br.com.rsinet.hub_tdd.suport.Screenshot;
import br.com.rsinet.hub_tdd.suport.Web;

public class PesquisaProdutoTest {

	private WebDriver driver;	
	
	@Before
	public void inicializa() {
		driver = Web.createChromer();
	}
	
	@Test
	public void procurarProdutoPelaHomePage() throws Exception {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		CategoriaPage categoriaPage = PageFactory.initElements(driver, CategoriaPage.class);
		ProdutoDescricaoPage produtoDescPage = PageFactory.initElements(driver, ProdutoDescricaoPage.class);
		
		ExcelUtils.setExcelFile("target/massaDeDados/procuraProduto.xlsx","Plan1");
		
		//##################################################################
		String categoriaDoProduto = ExcelUtils.getCellData(2, 0);
		String produto = ExcelUtils.getCellData(2, 1);
		String assertProduto = ExcelUtils.getCellData(2, 2);
		
		homePage.buscaCategoria(driver, categoriaDoProduto);
		categoriaPage.escolherProdutoDaCategoria(driver, produto);

		//Validando se produto foi escolhido corretamente
		assertEquals(assertProduto, produtoDescPage.validandoProdutoEscolhido.getText());
		Screenshot.gerarScreenShot(driver);
	}
	
	@After
	public void finaliza() {
		Web.killChromer(driver);
	}
}
