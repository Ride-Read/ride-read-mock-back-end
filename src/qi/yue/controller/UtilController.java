package qi.yue.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiniu.util.Auth;
import com.yunpian.sdk.model.ResultDO;
import com.yunpian.sdk.model.SendSingleSmsInfo;
import com.yunpian.sdk.service.SmsOperator;
import com.yunpian.sdk.service.YunpianRestClient;

import net.sf.json.JSONObject;
import qi.yue.common.MessageCommon;
import qi.yue.dto.SmsCodeDTO;
import qi.yue.entity.SmsCode;
import qi.yue.service.SmsCodeService;
import qi.yue.utils.CommonUtil;
import qi.yue.utils.ResponseUtil;
import qi.yue.utils.StringUtil;

@Controller
@RequestMapping("/util")
public class UtilController {
	@Resource
	private SmsCodeService smsCodeService;

	@RequestMapping(value = "/qiniu_token", method = RequestMethod.POST)
	public @ResponseBody Object qiniuToken(String token, Long timestamp, String filename, Integer uid) {
		if (CommonUtil.isNullOrEmpty(token) || CommonUtil.isNullOrEmpty(filename) || CommonUtil.isNull(timestamp)
				|| CommonUtil.isNull(uid)) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		}
		try {
			// String tokenTemp = EncryptionUtil.GetMD5Code(uid + timestamp +
			// MessageCommon.PUBLIC_KEY);
			// if (!tokenTemp.equals(token)) {
			// result.put("data", "");
			// result.put("status", MessageCommon.STATUS_FAIL);
			// } else {
			Auth auth = Auth.create(MessageCommon.QI_NIU_ACCESS_KEY, MessageCommon.QI_NIU_SECRE_KEY);
			String upToken = auth.uploadToken(MessageCommon.QI_NIU_BUCKET, filename);
			Map<String, String> map = new HashMap<>();
			map.put("up_token", upToken);
			return ResponseUtil.ConvertToSuccessResponse(map);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}
	}

	@RequestMapping(value = "/yun_pian_code", method = RequestMethod.POST)
	public @ResponseBody Object yunPianCode(Long timestamp, String phonenumber) {
		if (CommonUtil.isNullOrEmpty(timestamp) || CommonUtil.isNullOrEmpty(phonenumber)) {
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		}
		try {
			Long nowTimestamp = new Date().getTime();
			List<SmsCodeDTO> smsCodeDTOs = smsCodeService.findDayRecordByPhonenumber(phonenumber);
			if (smsCodeDTOs != null && smsCodeDTOs.size() >= 5) {
				return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_SMS_CODE_MAX_ERROR,
						MessageCommon.FAIL_MESSAGE_SMS_CODE_MAX_ERROR);
			}
			if (smsCodeDTOs != null && smsCodeDTOs.size() >= 1) {
				SmsCodeDTO smsCodeDTO = smsCodeDTOs.get(0);
				int interval = (int) ((nowTimestamp - smsCodeDTO.getCreated_at().getTime()) / 1000 / 60);
				if (interval <= 3) {
					return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_MIN_INTERVAL_ERROR,
							MessageCommon.FAIL_MESSAGE_MIN_INTERVAL_ERROR);
				}
			}
			String randCode = StringUtil.getRandomNumber(MessageCommon.RAND_LENGTH);
			String qiYueCode = StringUtil.getYunPianCode(randCode);
			YunpianRestClient client = new YunpianRestClient(MessageCommon.YUN_PIAN_APIKEY);// 用apikey生成client,可作为全局静态变量
			SmsOperator smsOperator = client.getSmsOperator();// 获取所需操作类
			ResultDO<SendSingleSmsInfo> yunPianResult = smsOperator.singleSend(phonenumber, qiYueCode);
			JSONObject json = JSONObject.fromObject(yunPianResult);
			JSONObject data = (JSONObject) json.get("data");
			String code = data.get("code").toString();
			if ("0".equals(code)) {
				SmsCode smsCode = new SmsCode();
				smsCode.setPhonenumber(phonenumber);
				smsCode.setRandCode(randCode);
				smsCode.setCreatedAt(new Date());
				smsCodeService.save(smsCode);
				Map<String, String> map = new HashMap<>();
				map.put("rand_code", randCode);
				return ResponseUtil.ConvertToSuccessResponse(map);
			} else {
				return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}
	}
}
