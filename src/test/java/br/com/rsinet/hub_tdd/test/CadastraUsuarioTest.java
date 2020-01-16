package br.com.rsinet.hub_tdd.test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import br.com.rsinet.hub_tdd.page.FormCadastraUsuarioPage;
import br.com.rsinet.hub_tdd.page.HomePage;
import br.com.rsinet.hub_tdd.suport.ExcelUtils;
import br.com.rsinet.hub_tdd.suport.Report;
import br.com.rsinet.hub_tdd.suport.Web;

public class CadastraUsuarioTest {

	private WebDriver driver;
	private HomePage homePage;
	private FormCadastraUsuarioPage cadastraUsuario;
	private ExtentTest test;
	private ExtentReports extent;

	@BeforeTest
	public void setConfigReport() {
		/*setando o reporte e enviando a string definindo o nome do arquivo report deste teste*/
		extent = Report.setReport("cadastraUsuario_report");
	}
	
	@BeforeMethod
	public void inicializa() throws Exception {
		/*setando chromedriver*/
		driver = Web.createChromer();

		/*definindo as PageFactory usada neste teste*/
		ExcelUtils.setExcelFile("target/dadosParaTest/massaDeDadosTestes.xlsx", "Cadastro");
		homePage = PageFactory.initElements(driver, HomePage.class);
		cadastraUsuario = PageFactory.initElements(driver, FormCadastraUsuarioPage.class);

		/*setando as configurações da classe excel responsavel pela leitura da massa de dados*/
		homePage.clicaIconeUser();
		homePage.clicaCreateNewAccount();
	}

	@Test
	public void deveCadastrarUsuarioNovoTest() throws Exception {
		/*definindo teste para o report*/
		test = Report.createTest("deveCadastrarUsuarioNovoTest");
		
		/*massa para o teste*/
		String userName = ExcelUtils.getCellData(1, 1);
		String email = ExcelUtils.getCellData(2, 1);
		String password = ExcelUtils.getCellData(3, 1);
		String confirmPassword = ExcelUtils.getCellData(4, 1);
		String firstName = ExcelUtils.getCellData(5, 1);
		String lastName = ExcelUtils.getCellData(6, 1);
		String phoneNumber = ExcelUtils.getCellData(7, 1);
		String country = ExcelUtils.getCellData(8, 1);
		String city = ExcelUtils.getCellData(9, 1);
		String address = ExcelUtils.getCellData(10, 1);
		String state = ExcelUtils.getCellData(11, 1);
		String postalCode = ExcelUtils.getCellData(12, 1);

		/*ações*/
		cadastraUsuario.cadastrandoUsuario(userName, email, password, confirmPassword, firstName ,
				lastName, phoneNumber, country, city, address, state, postalCode);
		cadastraUsuario.clicaBtnRegistrar();

		/*setando um tempo para realizar o assertEquals*/
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 1000);");
		
		/*Validando usuario se foi criado com sucesso, recenbendo massa de dados pelo excel*/
		String assertAccount = ExcelUtils.getCellData(13, 1);
		assertEquals(assertAccount, homePage.validandoUsuarioCriado());
	}

	@Test
	public void deveValidarCamposObrigatorioDoCadastroTest() throws Exception {
		/*definindo teste para o report*/
		test = Report.createTest("deveValidarCamposObrigatorioDoCadastroTest");
		
		/*massa para o teste*/
		String userName = "";
		String email = "";
		String password = "";
		String confirmPassword = "";
		String firstName = ExcelUtils.getCellData(5, 1);
		String lastName = ExcelUtils.getCellData(6, 1);
		String phoneNumber = ExcelUtils.getCellData(7, 1);
		String country = ExcelUtils.getCellData(8, 1);
		String city = ExcelUtils.getCellData(9, 1);
		String address = ExcelUtils.getCellData(10, 1);
		String state = ExcelUtils.getCellData(11, 1);
		String postalCode = ExcelUtils.getCellData(12, 1);

		/*ações*/
		cadastraUsuario.cadastrandoUsuario(userName, email, password, confirmPassword, firstName ,
				lastName, phoneNumber, country, city, address, state, postalCode);
		
		/*setando um tempo para realizar o assertEquals*/
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scrollBy(0,-350)", "");

		/*Validando campos obrigatorio para cadastro, recenbendo massa de dados pelo excel*/
		String assertUserName = ExcelUtils.getCellData(1, 3);
		String assertEmail = ExcelUtils.getCellData(2, 3);
		String assertPassword = ExcelUtils.getCellData(3, 3);
		String assertConfirmPassword = ExcelUtils.getCellData(4, 3);

		assertEquals(assertUserName, cadastraUsuario.validandoCampoUserName());
		assertEquals(assertEmail, cadastraUsuario.validandoCampoEmail());
		assertEquals(assertPassword, cadastraUsuario.validandoCampoPass());
		assertEquals(assertConfirmPassword, cadastraUsuario.validandoCampoConfirmPass());
	}

	@AfterMethod
	public void finaliza(ITestResult result) throws IOException {
		/*condição para definir o status do teste no report*/
		Report.statusReported(test, result, driver);

		/*fechando*/
		Report.quitExtent(extent);
		Web.quitChrome(driver);
	}
}
