package br.com.rsinet.hub_tdd.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FormCadastraUsuarioPage {
	
	@FindBy(how = How.NAME, using = "usernameRegisterPage")
	private WebElement userName;

	@FindBy(how = How.NAME, using = "emailRegisterPage")
	private WebElement email;

	@FindBy(how = How.NAME, using = "passwordRegisterPage")
	private WebElement password;

	@FindBy(how = How.NAME, using = "confirm_passwordRegisterPage")
	private WebElement confirmPassword;

	@FindBy(how = How.NAME, using = "first_nameRegisterPage")
	private WebElement firstName;

	@FindBy(how = How.NAME, using = "last_nameRegisterPage")
	private WebElement lastName;

	@FindBy(how = How.NAME, using = "phone_numberRegisterPage")
	private WebElement phoneNumber;

	@FindBy(how = How.NAME, using = "countryListboxRegisterPage")
	private WebElement countryList;

	@FindBy(how = How.NAME, using = "cityRegisterPage")
	private WebElement city;

	@FindBy(how = How.NAME, using = "addressRegisterPage")
	private WebElement address;

	@FindBy(how = How.NAME, using = "state_/_province_/_regionRegisterPage")
	private WebElement state;

	@FindBy(how = How.NAME, using = "postal_codeRegisterPage")
	private WebElement postalCode;

	@FindBy(how = How.NAME, using = "i_agree")
	private WebElement aceitarTermo;

	@FindBy(how = How.ID, using = "register_btnundefined")
	private WebElement botaoRegistrar;

	@FindBy(how = How.XPATH, using = "//*[@id=\"formCover\"]/div[1]/div[1]/sec-view[1]/div/label")
	private WebElement campoUserName;

	@FindBy(how = How.XPATH, using = "//*[@id=\"formCover\"]/div[1]/div[1]/sec-view[2]/div/label")
	private WebElement campoEmail;

	@FindBy(how = How.XPATH, using = "//*[@id=\"formCover\"]/div[1]/div[2]/sec-view[1]/div/label")
	private WebElement campoPass;

	@FindBy(how = How.XPATH, using = "//*[@id=\"formCover\"]/div[1]/div[2]/sec-view[2]/div/label")
	private WebElement campoConfirmPass;

	public void cadastrandoUsuario(String userName, String email, String password, String confirmPass, String firstName,
			String lastName, String phoneNumber, String country, String city, String address, String state, String postalCode) {
		
		this.userName.sendKeys(userName);
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
	}
	
	public String validandoCampoUserName() {
		return campoUserName.getText();
	}
	
	public String validandoCampoEmail() {
		return campoEmail.getText();
	}
	
	public String validandoCampoPass() {
		return campoPass.getText();
	}
	
	public String validandoCampoConfirmPass() {
		return campoConfirmPass.getText();
	}
	
	public void clicaBtnRegistrar() {
		botaoRegistrar.click();
	}
}
