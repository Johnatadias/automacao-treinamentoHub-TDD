package br.com.rsinet.hub_tdd.pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormCadastraUsuarioPage {
	
	@FindBy(name = "usernameRegisterPage")
	private WebElement userName;

	@FindBy(name = "emailRegisterPage")
	private WebElement email;

	@FindBy(name = "passwordRegisterPage")
	private WebElement password;

	@FindBy(name = "confirm_passwordRegisterPage")
	private WebElement confirmPassword;

	@FindBy(name = "first_nameRegisterPage")
	private WebElement firstName;

	@FindBy(name = "last_nameRegisterPage")
	private WebElement lastName;

	@FindBy(name = "phone_numberRegisterPage")
	private WebElement phoneNumber;

	@FindBy(name = "countryListboxRegisterPage")
	private WebElement countryList;

	@FindBy(name = "cityRegisterPage")
	private WebElement city;

	@FindBy(name = "addressRegisterPage")
	private WebElement address;

	@FindBy(name = "state_/_province_/_regionRegisterPage")
	private WebElement state;

	@FindBy(name = "postal_codeRegisterPage")
	private WebElement postalCode;

	@FindBy(name = "i_agree")
	private WebElement aceitarTermo;

	@FindBy(id = "register_btnundefined")
	private WebElement botaoRegistrar;

	@FindBy(xpath = "//*[@id=\"formCover\"]/div[1]/div[1]/sec-view[1]/div/label")
	private WebElement campoUserName;

	@FindBy(xpath = "//*[@id=\"formCover\"]/div[1]/div[1]/sec-view[2]/div/label")
	private WebElement campoEmail;

	@FindBy(xpath = "//*[@id=\"formCover\"]/div[1]/div[2]/sec-view[1]/div/label")
	private WebElement campoPass;

	@FindBy(xpath = "//*[@id=\"formCover\"]/div[1]/div[2]/sec-view[2]/div/label")
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
