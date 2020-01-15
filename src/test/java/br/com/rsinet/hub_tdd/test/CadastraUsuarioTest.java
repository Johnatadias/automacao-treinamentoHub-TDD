package br.com.rsinet.hub_tdd.test;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import br.com.rsinet.hub_tdd.page.FormCadastraUsuarioPage;
import br.com.rsinet.hub_tdd.page.HomePage;
import br.com.rsinet.hub_tdd.suport.ExcelUtils;
import br.com.rsinet.hub_tdd.suport.Screenshot;
import br.com.rsinet.hub_tdd.suport.Web;

public class CadastraUsuarioTest {

	private WebDriver driver;
	private HomePage homePage;
	private FormCadastraUsuarioPage cadastraUsuario;

	@BeforeMethod
	public void inicializa() throws Exception {
		driver = Web.createChromer();

		ExcelUtils.setExcelFile("target/dadosParaTest/massaDeDadosTestes.xlsx", "Cadastro");
		homePage = PageFactory.initElements(driver, HomePage.class);
		cadastraUsuario = PageFactory.initElements(driver, FormCadastraUsuarioPage.class);

		homePage.clica_IconeUser.click();
		homePage.clica_CreateNewAccount.sendKeys(Keys.ENTER);
	}

	@Test
	public void deveCadastrarUsuarioNovoTest() throws Exception {

		cadastraUsuario.userName.sendKeys(ExcelUtils.getCellData(1, 1));
		cadastraUsuario.email.sendKeys(ExcelUtils.getCellData(2, 1));
		cadastraUsuario.password.sendKeys(ExcelUtils.getCellData(3, 1));
		cadastraUsuario.confirmPassword.sendKeys(ExcelUtils.getCellData(4, 1));
		cadastraUsuario.firstName.sendKeys(ExcelUtils.getCellData(5, 1));
		cadastraUsuario.lastName.sendKeys(ExcelUtils.getCellData(6, 1));
		cadastraUsuario.phoneNumber.sendKeys(ExcelUtils.getCellData(7, 1));
		cadastraUsuario.countryList.sendKeys(ExcelUtils.getCellData(8, 1));
		cadastraUsuario.city.sendKeys(ExcelUtils.getCellData(9, 1));
		cadastraUsuario.address.sendKeys(ExcelUtils.getCellData(10, 1));
		cadastraUsuario.state.sendKeys(ExcelUtils.getCellData(11, 1));
		cadastraUsuario.postalCode.sendKeys(ExcelUtils.getCellData(12, 1));
		cadastraUsuario.aceitarTermo.click();
		cadastraUsuario.botaoRegistrar.click();

		// Validando usuario se foi criado com sucesso
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 1000);");

		String assertAccount = ExcelUtils.getCellData(13, 1);

		assertEquals(assertAccount, cadastraUsuario.validandoUsuarioCriado.getText());
		
		Screenshot.gerarScreenShot(driver, "deveCadastrarUsuarioNovoTest");
	}

	@Test
	public void deveValidarCamposObrigatorioDoCadastroTest() throws Exception {

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

		cadastraUsuario.validandoCamposObrigatorio(userName, email, password, confirmPassword, firstName ,
				lastName, phoneNumber, country, city, address, state, postalCode);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scrollBy(0,-350)", "");

		// Validando campos obrigatorio para cadastro
		String assertUserName = ExcelUtils.getCellData(1, 3);
		String assertEmail = ExcelUtils.getCellData(2, 3);
		String assertPassword = ExcelUtils.getCellData(3, 3);
		String assertConfirmPassword = ExcelUtils.getCellData(4, 3);

		assertEquals(assertUserName, cadastraUsuario.validandoCampoUserName.getText());
		assertEquals(assertEmail, cadastraUsuario.validandoCampoEmail.getText());
		assertEquals(assertPassword, cadastraUsuario.validandoCampoPass.getText());
		assertEquals(assertConfirmPassword, cadastraUsuario.validandoCampoConfirmPass.getText());
		
		Screenshot.gerarScreenShot(driver, "deveValidarCamposObrigatorioDoCadastroTest");
	}

	@AfterMethod
	public void finaliza() {
		Web.killChromer(driver);
	}
}
