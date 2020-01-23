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

import br.com.rsinet.hub_tdd.pageFactory.BasePage;
import br.com.rsinet.hub_tdd.pageFactory.FormCadastraUsuarioPage;
import br.com.rsinet.hub_tdd.pageFactory.HomePage;
import br.com.rsinet.hub_tdd.suport.ExcelUtils;
import br.com.rsinet.hub_tdd.suport.Report;
import br.com.rsinet.hub_tdd.utils.ExcelMassaDeDados;
import br.com.rsinet.hub_tdd.suport.DriverFactory;

public class CadastraUsuarioTest{

	private WebDriver driver;
	private HomePage homePage;
	private FormCadastraUsuarioPage cadastraUsuario;
	private ExtentTest test;
	private ExtentReports extent;
	private BasePage basePage;
	private ExcelMassaDeDados excel;

	@BeforeTest
	public void setConfigReport() {
		/*setando o reporte e enviando a string definindo o nome do arquivo report deste teste*/
		extent = Report.setReport("cadastraUsuario_report");
	}
	
	@BeforeMethod
	public void inicializa() throws Exception {
		/*setando chromedriver*/
		driver = DriverFactory.createChromer();
		basePage = new BasePage(driver);

		/*definindo as PageFactory usada neste teste*/
		homePage = new HomePage(driver);
		cadastraUsuario = new FormCadastraUsuarioPage(driver);

		/*setando as configurações da classe excel responsavel pela leitura da massa de dados*/
		ExcelUtils.setExcelFile("Cadastro");
		
		/*instanciando a class responsavel por armazenar a massa de dados do excel*/
		excel = new ExcelMassaDeDados();
		
		/*ação para iniciar ambos testes desta classe*/
		homePage.clicaIconeUser();
		homePage.clicaCreateNewAccount();
	}

	@Test
	public void deveCadastrarUsuarioNovoTest() throws Exception {
		/*definindo teste para o report*/
		test = Report.createTest("deveCadastrarUsuarioNovoTest");

		/*ações*/
		cadastraUsuario.cadastrandoUsuario(excel.getUserName(), excel.getEmail(), excel.getPassword(), excel.getConfirmPassword(), excel.getFirstName() ,
				excel.getLastName(), excel.getPhoneNumber(), excel.getCountry(), excel.getCity(), excel.getAddress(), excel.getState(), excel.getPostalCode());
		cadastraUsuario.clicaBtnRegistrar();
		
		/*Validando usuario se foi criado com sucesso, recenbendo massa de dados pelo excel*/
		assertEquals(excel.getAssertNovoUsuario(), homePage.validandoUsuarioCriado());
	}

	@Test
	public void deveValidarCamposObrigatorioDoCadastroTest() throws Exception {
		/*definindo teste para o report*/
		test = Report.createTest("deveValidarCamposObrigatorioDoCadastroTest");

		/*ações*/
		cadastraUsuario.cadastrandoUsuario(excel.getUserNameVazio(), excel.getEmailVazio(), excel.getPassaWordVazio(), excel.getConfirmPasswordVazio(), excel.getFirstName() ,
				excel.getLastName(), excel.getPhoneNumber(), excel.getCountry(), excel.getCity(), excel.getAddress(), excel.getState(), excel.getPostalCode());
		cadastraUsuario.clicaBtnRegistrar();
		
		/*Validando campos obrigatorio para cadastro, recenbendo massa de dados pelo excel*/
		assertEquals(excel.getAssertUserName(), cadastraUsuario.validandoCampoUserName());
		assertEquals(excel.getAssertEmail(), cadastraUsuario.validandoCampoEmail());
		assertEquals(excel.getAssertPassword(), cadastraUsuario.validandoCampoPass());
		assertEquals(excel.getAssertConfirmPassword(), cadastraUsuario.validandoCampoConfirmPass());
		
		/*scroll para cima para realizar o screenshot*/
		basePage.scrollUp();
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
