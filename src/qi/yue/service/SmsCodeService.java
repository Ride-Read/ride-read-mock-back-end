package qi.yue.service;

import java.util.List;

import qi.yue.dto.ResponseDTO;
import qi.yue.dto.SmsCodeDTO;
import qi.yue.entity.SmsCode;

public interface SmsCodeService {
	SmsCodeDTO find(Integer id);

	int save(SmsCode smsCode);

	List<SmsCodeDTO> findByPhonenumber(String phonenumber);

	List<SmsCodeDTO> findDayRecordByPhonenumber(String phonenumber);

	ResponseDTO getYunPianCode(String phonenumber, Long timestamp);

}
