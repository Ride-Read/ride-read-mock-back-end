package qi.yue.utils;

import qi.yue.common.MessageCommon;
import qi.yue.dto.ResponseDTO;

/**
 * @Title ResponseUtil
 * @author WUSINAN
 * @date 2017年3月30日 Description:封装ResponseDTO的工具类
 */
public class ResponseUtil {
	private ResponseUtil() {
	}

	public static ResponseDTO ConvertToSuccessResponse() {
		ResponseDTO result = new ResponseDTO(MessageCommon.STATUS_SUCCESS, MessageCommon.SUCCESS_MESSAGE);
		return result;
	}

	public static ResponseDTO ConvertToSuccessResponse(Long timestamp) {
		ResponseDTO result = new ResponseDTO(MessageCommon.STATUS_SUCCESS, MessageCommon.SUCCESS_MESSAGE, timestamp);
		return result;
	}

	public static ResponseDTO ConvertToSuccessResponse(Object data) {
		ResponseDTO result = new ResponseDTO(MessageCommon.STATUS_SUCCESS, data, MessageCommon.SUCCESS_MESSAGE);
		return result;
	}

	public static ResponseDTO ConvertToSuccessResponse(Object data, Long timestamp) {
		ResponseDTO result = new ResponseDTO(MessageCommon.STATUS_SUCCESS, data, MessageCommon.SUCCESS_MESSAGE,
				timestamp);
		return result;
	}

	public static ResponseDTO ConvertToFailResponse() {
		ResponseDTO result = new ResponseDTO(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		return result;
	}

	public static ResponseDTO ConvertToFailResponse(Long timestamp) {
		ResponseDTO result = new ResponseDTO(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE, timestamp);
		return result;
	}

	public static ResponseDTO ConvertToFailResponse(String status) {
		ResponseDTO result = new ResponseDTO(status, MessageCommon.FAIL_MESSAGE);
		return result;
	}

	public static ResponseDTO ConvertToFailResponse(String status, String msg) {
		ResponseDTO result = new ResponseDTO(status, msg);
		return result;
	}

	public static ResponseDTO ConvertToFailResponse(String status, String msg, Long timestamp) {
		ResponseDTO result = new ResponseDTO(status, msg, timestamp);
		return result;
	}
}
