package qi.yue.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 字符串工具类
 * 
 * @ClassName: StringUtil
 * @Description: 字符串工具类
 * @author Caokai
 * @date 2015年11月17日 下午4:02:50
 */
public class StringUtil {

	public static boolean isNull(String str) {
		return str == null || str.length() == 0 || "".equals(str.trim());
	}

	public static int getPriceMu100(String price) {
		return new BigDecimal(price).multiply(new BigDecimal(100)).intValue();
	}

	/**
	 * Data对象转String方法
	 * 
	 * @param date
	 * @param strFormat
	 * @return
	 */
	public static String dateToString(Date date, String strFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		return sdf.format(date);
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
	 * 生成指定位数的随机数
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomNumber(Integer length) {
		long seed = System.currentTimeMillis();// 获得系统时间，作为生成随机数的种子
		StringBuffer sb = new StringBuffer();// 装载生成的随机数
		Random random = new Random(seed);// 调用种子生成随机数
		for (int i = 0; i < length; i++) {
			sb.append(random.nextInt(length));
		}
		return sb.toString();
	}

	/**
	 * 将数组转换成字符串
	 * 
	 * @param strs
	 * @param interval
	 * @return
	 */
	public static String arrayToString(String[] strs, String interval) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strs.length; i++) {
			sb.append(strs[i]);
			if (i != strs.length - 1) {
				sb.append(interval);
			}
		}
		return sb.toString();

	}

}
