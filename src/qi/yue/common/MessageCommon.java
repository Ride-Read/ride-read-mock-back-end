package qi.yue.common;

public class MessageCommon {

	public static final String STATUS_SUCCESS = "0";
	public static final String STATUS_FAIL = "1";
	public static final String STATUS_USER_IS_EXIST = "1000";
	public static final String STATUS_USER_NOT_EXIST = "1002";
	public static final String STATUS_PASSWORD_WRONG = "1003";
	public static final String STATUS_PHONENUMBER_EXIST = "1004";
	public static final String STATUS_PARAMETER_WRONG = "1005";
	public static final String STATUS_QUERY_FAIL = "1006";
	public static final String STATUS_UPDATE_FAIL = "1007";
	public static final String STATUS_SAVE_FAIL = "1008";
	public static final String STATUS_DELETE_FAIL = "1009";

	public static final String TYPE_ERROR = "1011";
	public static final String IMAGE_EMPTY = "1012";
	public static final String VIDEO_EMPTY = "1013";

	public static final String PUBLIC_KEY = "airing";
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String QI_NIU_ACCESS_KEY = "fbVYMBeuMglXqIDmW1H_tlOkb4CrxlLCIUPjGsRV";
	public static final String QI_NIU_SECRE_KEY = "UfLYZXK0ihHkaqTA2QQdzbn1FkDHH0G8oVCaRXMf";
	public static final String QI_NIU_BUCKET = "rideread";

	public static final String SUCCESS_MESSAGE = "返回成功";
	public static final String FAIL_MESSAGE = "返回失败";
	public static final String FAIL_MESSAGE_PARAMETER = "参数错误或者为空";
	public static final String FAIL_MESSAGE_USER_NOT_EXIST = "用户不存在";
	public static final String FAIL_MESSAGE_USER_IS_EXIST = "用户已存在";
	public static final String FAIL_MESSAGE_PASSWORD_WRONG = "密码错误";
	public static final String FAIL_MESSAGE_QUERY_FAIL = "查询出错";
	public static final String FAIL_MESSAGE_UPDATE_FAIL = "update语句执行失败";
	public static final String FAIL_MESSAGE_SAVE_FAIL = "插入失败";
	public static final String FAIL_MESSAGE_DELETE_FAIL = "删除数据失败";
	public static final String FAIL_MESSAGE_TYPE_ERROR = "类型错误";
	public static final String FAIL_MESSAGE_IMAGE_EMPTY = "图片为空";
	public static final String FAIL_MESSAGE_VIDEO_EMPTY = "视频为空";
	
	public static final int PAGE_SIZE = 20;
	
	private MessageCommon() {}
}
