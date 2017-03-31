package qi.yue.utils;

import qi.yue.common.MessageCommon;
import qi.yue.dto.ResponseDTO;

/**
 * @Title ResponseUtil
 * @author WUSINAN
 * @date 2017年3月30日
 * Description:封装ResponseDTO的工具类
 */
public class ResponseUtil {
	private ResponseUtil() {}
	
	public static ResponseDTO ConvertToSuccessResponse() {
		ResponseDTO result = new ResponseDTO();
		result.setMsg(MessageCommon.SUCCESS_MESSAGE);
		result.setStatus(MessageCommon.STATUS_SUCCESS);
		return result;
	}
	
	public static ResponseDTO ConvertToSuccessResponse(Object data) {
		ResponseDTO result = new ResponseDTO();
		result.setMsg(MessageCommon.SUCCESS_MESSAGE);
		result.setStatus(MessageCommon.STATUS_SUCCESS);
		result.setData(data);
		return result;
	}
	
	public static ResponseDTO ConvertToFailResponse() {
		ResponseDTO result = new ResponseDTO();
		result.setMsg(MessageCommon.FAIL_MESSAGE);
		result.setStatus(MessageCommon.STATUS_FAIL);
		return result;
	}
	
	public static ResponseDTO ConvertToFailResponse(String status) {
		ResponseDTO result = new ResponseDTO();
		result.setMsg(MessageCommon.FAIL_MESSAGE);
		result.setStatus(status);
		return result;
	}
	
	public static ResponseDTO ConvertToFailResponse(String status, String msg) {
		ResponseDTO result = new ResponseDTO();
		result.setMsg(msg);
		result.setStatus(status);
		return result;
	}
}
