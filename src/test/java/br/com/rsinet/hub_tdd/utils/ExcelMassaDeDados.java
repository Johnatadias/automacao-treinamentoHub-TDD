package br.com.rsinet.hub_tdd.utils;

import br.com.rsinet.hub_tdd.suport.ExcelUtils;

public class ExcelMassaDeDados {

	/*#############################################################
	* cenario validar novo usuario */
	
	public String getUserName() throws Exception {
		return ExcelUtils.getCellData(1, 1);
	}

	public String getEmail() throws Exception {
		return ExcelUtils.getCellData(2, 1);
	}

	public String getPassword() throws Exception {
		return ExcelUtils.getCellData(3, 1);
	}

	public String getConfirmPassword() throws Exception {
		return ExcelUtils.getCellData(4, 1);
	}

	public String getFirstName() throws Exception {
		return ExcelUtils.getCellData(5, 1);
	}

	public String getLastName() throws Exception {
		return ExcelUtils.getCellData(6, 1);
	}

	public String getPhoneNumber() throws Exception {
		return ExcelUtils.getCellData(7, 1);
	}

	public String getCountry() throws Exception {
		return ExcelUtils.getCellData(8, 1);
	}

	public String getCity() throws Exception {
		return ExcelUtils.getCellData(9, 1);
	}

	public String getAddress() throws Exception {
		return ExcelUtils.getCellData(10, 1);
	}

	public String getState() throws Exception {
		return ExcelUtils.getCellData(11, 1);
	}

	public String getPostalCode() throws Exception {
		return ExcelUtils.getCellData(12, 1);
	}

	public String getAssertNovoUsuario() throws Exception {
		return ExcelUtils.getCellData(13, 1);
	}

	/*#############################################################
	* cenario validar campos obrigatorios */
	
	public String getUserNameVazio() {
		return "";
	}

	public String getEmailVazio() {
		return "";
	}

	public String getPassaWordVazio() {
		return "";
	}

	public String getConfirmPasswordVazio() {
		return "";
	}

	public String getAssertUserName() throws Exception {
		return ExcelUtils.getCellData(1, 3);
	}

	public String getAssertEmail() throws Exception {
		return ExcelUtils.getCellData(2, 3);
	}

	public String getAssertPassword() throws Exception {
		return ExcelUtils.getCellData(3, 3);
	}

	public String getAssertConfirmPassword() throws Exception {
		return ExcelUtils.getCellData(4, 3);
	}
	
	/*#############################################################
	* cenario filtra Produto Existente Pela Lupa De Pesquisa*/

	public String getCategoriaExistente() throws Exception {
		return ExcelUtils.getCellData(1, 0);
	}

	public String getProdutoExistente() throws Exception {
		return ExcelUtils.getCellData(1, 1);
	}

	public String getAssertProdutoExistente() throws Exception {
		return ExcelUtils.getCellData(1, 2);
	}
	
	/*#############################################################
	* cenario procura Produto Inexistente Pela Lupa De Pesquisa*/

	public String getProdutoInexistente() throws Exception {
		return ExcelUtils.getCellData(9, 0);
	}

	public String getAssertProdutoInexistente() throws Exception {
		return ExcelUtils.getCellData(9, 1);
	}
	
	/*#############################################################
	* cenario deve Procurar Produto Por Home Page*/

	public String getCategoriaDoProdutoHomePage() throws Exception {
		return ExcelUtils.getCellData(2, 0);
	}

	public String getProdutoEscolhido() throws Exception {
		return ExcelUtils.getCellData(2, 1);
	}

	public String getAssertProdutoEscolhido() throws Exception {
		return ExcelUtils.getCellData(2, 2);
	}
	
	/*#############################################################
	* cenario nao Deve Add Mais Dez Produto No Carrinho De Compras*/
	
	public String getCategoriaDoProdutoParaAdd() throws Exception {
		return ExcelUtils.getCellData(2, 0);
	}

	public String getProdutoEscolhidoParaAdd() throws Exception {
		return ExcelUtils.getCellData(2, 1);
	}

	public String getQuantidadeProduto() throws Exception {
		return ExcelUtils.getCellData(4, 3);
	}

	public String getAssertMensagem() throws Exception {
		return ExcelUtils.getCellData(4, 2);
	}
}
