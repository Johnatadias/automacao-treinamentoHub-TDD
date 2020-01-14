package br.com.rsinet.hub_tdd.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FormCadastraUsuarioPage {

	@FindBy(how = How.NAME, using = "usernameRegisterPage")
	public WebElement userName;

	@FindBy(how = How.NAME, using = "emailRegisterPage")
	public WebElement email;

	@FindBy(how = How.NAME, using = "passwordRegisterPage")
	public WebElement password;

	@FindBy(how = How.NAME, using = "confirm_passwordRegisterPage")
	public WebElement confirmPassword;

	@FindBy(how = How.NAME, using = "first_nameRegisterPage")
	public WebElement firstName;

	@FindBy(how = How.NAME, using = "last_nameRegisterPage")
	public WebElement lastName;

	@FindBy(how = How.NAME, using = "phone_numberRegisterPage")
	public WebElement phoneNumber;

	@FindBy(how = How.NAME, using = "countryListboxRegisterPage")
	public WebElement countryList;

	@FindBy(how = How.NAME, using = "cityRegisterPage")
	public WebElement city;

	@FindBy(how = How.NAME, using = "addressRegisterPage")
	public WebElement address;

	@FindBy(how = How.NAME, using = "state_/_province_/_regionRegisterPage")
	public WebElement state;

	@FindBy(how = How.NAME, using = "postal_codeRegisterPage")
	public WebElement postalCode;

	@FindBy(how = How.NAME, using = "i_agree")
	public WebElement aceitarTermo;

	@FindBy(how = How.ID, using = "register_btnundefined")
	public WebElement botaoRegistrar;

	@FindBy(how = How.ID, using = "menuUserLink")
	public WebElement validandoUsuarioCriado;

	@FindBy(how = How.XPATH, using = "//*[@id=\"registerPage\"]/article/sec-form/div[2]/label[1]")
	public WebElement validandoUsuarioExistente;

	@FindBy(how = How.XPATH, using = "//*[@id=\"formCover\"]/div[1]/div[1]/sec-view[1]/div/label")
	public WebElement validandoCampoUserName;

	@FindBy(how = How.XPATH, using = "//*[@id=\"formCover\"]/div[1]/div[1]/sec-view[2]/div/label")
	public WebElement validandoCampoEmail;

	@FindBy(how = How.XPATH, using = "//*[@id=\"formCover\"]/div[1]/div[2]/sec-view[1]/div/label")
	public WebElement validandoCampoPass;

	@FindBy(how = How.XPATH, using = "//*[@id=\"formCover\"]/div[1]/div[2]/sec-view[2]/div/label")
	public WebElement validandoCampoConfirmPass;

	public HomePage cadastrarUsuario(String userName, String email, String password, String confirmPass, 
			String firstName, String lastName, String phoneNumber, String country, 
			String city, String address, String state, String postalCode) {
		
		this.userName.sendKeys(userName);
		this.lastName.sendKeys(lastName);
		this.email.sendKeys(email);
		this.password.sendKeys(password);
		this.confirmPassword.sendKeys(confirmPass);
		this.firstName.sendKeys(firstName);
		this.lastName.sendKeys(lastName);
		this.phoneNumber.sendKeys(phoneNumber);
		this.countryList.sendKeys(country);
		this.city.sendKeys(city);
		this.address.sendKeys(address);
		this.state.sendKeys(state);
		this.postalCode.sendKeys(postalCode);
		
		this.aceitarTermo.click();
		this.botaoRegistrar.click();
		
		return new HomePage();
	}
}
