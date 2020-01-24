package br.com.rsinet.hub_tdd.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	
	/*gerando screenshot*/
	public static String gerarScreenShot(WebDriver driver, String nome) {
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		/*definindo destino e nome da screenshot*/
		String destino = System.getProperty("user.dir") + "/target/reportScreenshot/"+ nome + "-" + DateUtils.getTimeStamp() + ".png";
		
		try {
			FileUtils.copyFile(file, new File(destino));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destino;
	}
}
