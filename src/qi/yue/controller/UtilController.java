package qi.yue.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiniu.util.Auth;

import qi.yue.common.MessageCommon;
import qi.yue.dto.ResponseDTO;
import qi.yue.utils.CommonUtil;

@Controller
@RequestMapping("/util")
public class UtilController {
	@RequestMapping(value = "/qiniu_token", method = RequestMethod.POST)

	public @ResponseBody Object qiniuToken(String token, Long timestamp, String filename, Integer uid) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(filename) || CommonUtil.isNull(timestamp)
				|| CommonUtil.isNull(uid)) {
			result.put("data", "");
			result.put("status", MessageCommon.STATUS_FAIL);
		} else {
			// String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp +
			// MessageCommon.PUBLIC_KEY);
			// if (!tokenTemp.equals(token)) {
			// result.put("data", "");
			// result.put("status", MessageCommon.STATUS_FAIL);
			// } else {
			Auth auth = Auth.create(MessageCommon.QI_NIU_ACCESS_KEY, MessageCommon.QI_NIU_SECRE_KEY);
			String upToken = auth.uploadToken(MessageCommon.QI_NIU_BUCKET, filename);
			result.put("qiuniu_token", upToken);
			result.put("status", MessageCommon.STATUS_SUCCESS);
			// }
		}
		return result;
	}
	
	@RequestMapping(value = "/qiniu_vedio", method = RequestMethod.POST)
	public @ResponseBody Object saveVedios(String token, Long timestamp, String filename, Integer uid) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (CommonUtil.isNullOrEmpty(token) ||
			CommonUtil.isNullOrEmpty(filename) ||
			CommonUtil.isNull(timestamp) ||
			CommonUtil.isNull(uid)) {
			result.put("data", "");
			result.put("status", MessageCommon.STATUS_FAIL);
		}else {
			Auth auth = Auth.create(MessageCommon.QI_NIU_ACCESS_KEY, MessageCommon.QI_NIU_SECRE_KEY);
			String upToken = auth.uploadToken(MessageCommon.QI_NIU_BUCKET, filename);
		}
		
		
		return result;
	}
}
