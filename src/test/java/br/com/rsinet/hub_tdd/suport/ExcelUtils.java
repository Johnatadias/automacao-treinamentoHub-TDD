package br.com.rsinet.hub_tdd.suport;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;

	/* Este metodo é para definir o caminho do arquivo e para abrir o arquivo do Excel, 
	 * passe o caminho do Excel e o nome da folha como argumentos para este metodo*/
	public static void setExcelFile(String SheetName) throws Exception {

		try {
			// Abrindo arquivo excel
			FileInputStream ExcelFile = new FileInputStream("target/dadosParaTest/massaDeDadosTestes.xlsx");

			// Acesse a planilha de dados de teste 
			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/* Este metodo e para ler os dados de teste da celula do Excel, neste estamos
	 * passando parametros como Row num e Col num*/
	public static String getCellData(int RowNum, int ColNum) throws Exception {

		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			String CellData = Cell.getStringCellValue();

			return CellData;

		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
