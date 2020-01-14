package br.com.rsinet.hub_tdd.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import br.com.rsinet.hub_tdd.page.FormCadastraUsuarioPage;
import br.com.rsinet.hub_tdd.page.HomePage;
import br.com.rsinet.hub_tdd.suport.ExcelUtils;
import br.com.rsinet.hub_tdd.suport.Screenshot;
import br.com.rsinet.hub_tdd.suport.Web;

public class CadastraUsuarioTest {

	private WebDriver driver;
	private HomePage homePage;
	private FormCadastraUsuarioPage cadastraUsuario;
	@Rule
	public TestName testName = new TestName();

	@Before
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

		cadastraUsuario.userName.sendKeys(ExcelUtils.getCellData(1, 0));
		cadastraUsuario.email.sendKeys(ExcelUtils.getCellData(1, 1));
		cadastraUsuario.password.sendKeys(ExcelUtils.getCellData(1, 2));
		cadastraUsuario.confirmPassword.sendKeys(ExcelUtils.getCellData(1, 3));
		cadastraUsuario.firstName.sendKeys(ExcelUtils.getCellData(1, 4));
		cadastraUsuario.lastName.sendKeys(ExcelUtils.getCellData(1, 5));
		cadastraUsuario.phoneNumber.sendKeys(ExcelUtils.getCellData(1, 6));
		cadastraUsuario.countryList.sendKeys(ExcelUtils.getCellData(1, 7));
		cadastraUsuario.city.sendKeys(ExcelUtils.getCellData(1, 8));
		cadastraUsuario.address.sendKeys(ExcelUtils.getCellData(1, 9));
		cadastraUsuario.state.sendKeys(ExcelUtils.getCellData(1, 10));
		cadastraUsuario.postalCode.sendKeys(ExcelUtils.getCellData(1, 11));
		cadastraUsuario.aceitarTermo.click();
		cadastraUsuario.botaoRegistrar.click();

		// Validando usuario se foi criado com sucesso
		Thread.sleep(2000);

		String assertAccount = ExcelUtils.getCellData(1, 12);

		assertEquals(assertAccount, cadastraUsuario.validandoUsuarioCriado.getText());
	}

	@Test
	public void naoDeveCadastrarUsuarioExistenteTest() throws Exception {

		String userName = ExcelUtils.getCellData(1, 0);
		String email = ExcelUtils.getCellData(1, 1);
		String password = ExcelUtils.getCellData(1, 2);
		String confirmPassword = ExcelUtils.getCellData(1, 3);
		String firstName = ExcelUtils.getCellData(1, 4);
		String lastName = ExcelUtils.getCellData(1, 5);
		String phoneNumber = ExcelUtils.getCellData(1, 6);
		String country = ExcelUtils.getCellData(1, 7);
		String city = ExcelUtils.getCellData(1, 8);
		String address = ExcelUtils.getCellData(1, 9);
		String state = ExcelUtils.getCellData(1, 10);
		String postalCode = ExcelUtils.getCellData(1, 11);

		cadastraUsuario.cadastrarUsuario(userName, email, password, confirmPassword, firstName, lastName, phoneNumber,
				country, city, address, state, postalCode);

		// Validando se usuario não foi criado
		Thread.sleep(2000);

		String assertAccount = ExcelUtils.getCellData(2, 12);

		assertEquals(assertAccount, cadastraUsuario.validandoUsuarioExistente.getText());
	}

	@Test
	public void validandoCamposObrigatorioDoCadastroTest() throws Exception {

		String userName = "";
		String email = "";
		String password = "";
		String confirmPassword = "";
		String firstName = ExcelUtils.getCellData(1, 4);
		String lastName = ExcelUtils.getCellData(1, 5);
		String phoneNumber = ExcelUtils.getCellData(1, 6);
		String country = ExcelUtils.getCellData(1, 7);
		String city = ExcelUtils.getCellData(1, 8);
		String address = ExcelUtils.getCellData(1, 9);
		String state = ExcelUtils.getCellData(1, 10);
		String postalCode = ExcelUtils.getCellData(1, 11);

		cadastraUsuario.cadastrarUsuario(userName, email, password, confirmPassword, firstName, lastName, phoneNumber,
				country, city, address, state, postalCode);

		// Validando campos obrigatorio para cadastro
		String assertUserName = ExcelUtils.getCellData(3, 12);
		String assertEmail = ExcelUtils.getCellData(3, 13);
		String assertPassword = ExcelUtils.getCellData(3, 14);
		String assertConfirmPassword = ExcelUtils.getCellData(3, 15);

		assertEquals(assertUserName, cadastraUsuario.validandoCampoUserName.getText());
		assertEquals(assertEmail, cadastraUsuario.validandoCampoEmail.getText());
		assertEquals(assertPassword, cadastraUsuario.validandoCampoPass.getText());
		assertEquals(assertConfirmPassword, cadastraUsuario.validandoCampoConfirmPass.getText());
	}

	@After
	public void finaliza() {
		Screenshot.gerarScreenShot(driver, testName);
		Web.killChromer(driver);
	}
}
