package qi.yue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yunpian.sdk.model.ResultDO;
import com.yunpian.sdk.model.SendSingleSmsInfo;
import com.yunpian.sdk.service.SmsOperator;
import com.yunpian.sdk.service.YunpianRestClient;

import net.sf.json.JSONObject;
import qi.yue.common.MessageCommon;
import qi.yue.dao.mapper.SmsCodeMapper;
import qi.yue.dto.ResponseDTO;
import qi.yue.dto.SmsCodeDTO;
import qi.yue.entity.SmsCode;
import qi.yue.service.SmsCodeService;
import qi.yue.utils.CommonUtil;
import qi.yue.utils.StringUtil;

@Service
public class SmsCodeServiceImpl implements SmsCodeService {
	@Resource
	private SmsCodeMapper smsCodeMapper;

	@Override
	public SmsCodeDTO find(Integer id) {
		return smsCodeMapper.find(id);
	}

	@Override
	public int save(SmsCode smsCode) {
		// TODO Auto-generated method stub
		return smsCodeMapper.insert(smsCode);
	}

	@Override
	public List<SmsCodeDTO> findByPhonenumber(String phonenumber) {
		return smsCodeMapper.findByPhonenumber(phonenumber);
	}

	@Override
	public ResponseDTO getYunPianCode(String phonenumber, Long timestamp) {
		ResponseDTO responseDTO = new ResponseDTO();
		if (CommonUtil.isNullOrEmpty(timestamp) || CommonUtil.isNullOrEmpty(phonenumber)) {
			responseDTO.setStatus(MessageCommon.STATUS_FAIL);
			responseDTO.setMsg(MessageCommon.FAIL_MESSAGE);
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
			smsCodeMapper.insert(smsCode);
			return responseDTO;
		} else {

		}
		return null;

	}

	@Override
	public List<SmsCodeDTO> findDayRecordByPhonenumber(String phonenumber) {
		return smsCodeMapper.findDayRecordByPhonenumber(phonenumber);
	}

}
