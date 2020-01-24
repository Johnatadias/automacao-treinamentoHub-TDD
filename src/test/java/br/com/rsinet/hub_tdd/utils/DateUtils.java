package br.com.rsinet.hub_tdd.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	/*gerando data e hora atual para adicionar no nome da screenshot*/
	public static String getTimeStamp() {
		return new SimpleDateFormat("dd_MM_yyyy HH.mm.ss").format(new Date());
	}
}
