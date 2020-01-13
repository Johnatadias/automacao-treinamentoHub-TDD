package br.com.rsinet.hub_tdd.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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

	@Before
	public void inicializa() {
		driver = Web.createChromer();
	}

	@Test
	public void deveCadastrarUsuarioTest() throws Exception {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		FormCadastraUsuarioPage cadastraUsuario = PageFactory.initElements(driver, FormCadastraUsuarioPage.class);

		ExcelUtils.setExcelFile("target/massaDeDados/cadastroUsuario.xlsx", "Plan1");

		// ##################################################################
		homePage.clica_IconeUser.click();
		homePage.clica_CreateNewAccount.sendKeys(Keys.ENTER);

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

		assertEquals(assertAccount, cadastraUsuario.infoParaValidacao.getText());
		Screenshot.gerarScreenShot(driver);
	}

	@After
	public void finaliza() {
		Web.killChromer(driver);
	}
}
