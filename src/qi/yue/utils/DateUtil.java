package qi.yue.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @ClassName: DateUtil
 * @Description: 时间工具类
 * @author Caokai
 * @date 2016年01月08日 下午4:02:50
 */
public class DateUtil {

	/**
	 * 获取当前时间戳（String）
	 * 
	 * @return
	 */
	public static String getTimestamp() {
		Date date = new Date();
		return StringUtil.dateToString(date, "YYYY-MM-DD HH:mm:ss");
	}

	/**
	 * String对象转Data方法
	 * 
	 * @param dateStr
	 * @param strFormat
	 * @return
	 * @throws ParseException
	 */
	public static Date strToDate(String dateStr, String strFormat) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		return sdf.parse(dateStr);
	}

	/**
	 * 获取年龄
	 * 
	 * @param birthday(YYYY-MM-DD)
	 * @return
	 */
	public static Integer getAge(String birthday) {
		/* 获取生日年月日 */
		Integer birthday_y = Integer.parseInt(birthday.substring(0, 4));
		Integer birthday_m = Integer.parseInt(birthday.substring(5, 7));
		Integer birthday_d = Integer.parseInt(birthday.substring(8, 10));

		/* 获取当前时间并得到年月日 */
		Calendar cal = Calendar.getInstance();
		int current_y = cal.get(Calendar.YEAR);// 获取年份
		int current_m = cal.get(Calendar.MONTH) + 1;// 获取月份
		int current_d = cal.get(Calendar.DATE);// 获取日

		Integer age = current_y - birthday_y;
		if (birthday_m > current_m) {
			age--;
		}
		if (birthday_m == current_m) {
			if (birthday_d > current_d) {
				age--;
			}
		}

		return age;
	}

	/**
	 * 获取年龄
	 * 
	 * @param birthday(YYYY-MM-DD)
	 * @return
	 */
	public static Integer getWorkage(Integer startyear) {
		/* 获取当前时间并得到年月日 */
		Calendar cal = Calendar.getInstance();
		int current_y = cal.get(Calendar.YEAR);// 获取年份

		Integer workage = current_y - startyear;

		return workage;
	}
}
