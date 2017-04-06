package qi.yue.dao.mapper;

import java.util.List;
import qi.yue.dto.SmsCodeDTO;
import qi.yue.entity.SmsCode;

public interface SmsCodeMapper {

	int insert(SmsCode smsCode);

	SmsCodeDTO find(Integer id);

	List<SmsCodeDTO> findByPhonenumber(String phonenumber);

	List<SmsCodeDTO> findDayRecordByPhonenumber(String phonenumber);

}