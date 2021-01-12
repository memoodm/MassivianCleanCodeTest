package co.com.memoodm.roulette.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String dateToMassivianFormat(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
		return simpleDateFormat.format(date);
	}
	
}
