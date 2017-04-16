package qi.yue.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import qi.yue.common.MessageCommon;
import qi.yue.dto.MomentDTO;
import qi.yue.dto.ResponseDTO;
import qi.yue.exception.BusinessException;
import qi.yue.exception.ParameterException;
import qi.yue.service.MomentService;
import qi.yue.utils.ResponseUtil;

@Controller
@RequestMapping("/map")
public class ReadMapController {
	@Resource
	MomentService momentService;

	@RequestMapping(value = "/show_user_map", method = RequestMethod.POST)
	public @ResponseBody Object showUserMap(Integer uid, Long timestamp, String token) {
		try {
			List<MomentDTO> data = momentService.findUserMap(uid, timestamp, token);
			ResponseDTO responseDTO = ResponseUtil.ConvertToSuccessResponse(data);
			return responseDTO;
		} catch (ParameterException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}
	}

	@RequestMapping(value = "/show_near_map", method = RequestMethod.POST)
	public @ResponseBody Object showNearMap(Integer uid, Long timestamp, String token, BigDecimal latitude,
			BigDecimal longitude) {
		try {
			List<MomentDTO> data = momentService.findNearMap(uid, timestamp, token, latitude, longitude);
			ResponseDTO responseDTO = ResponseUtil.ConvertToSuccessResponse(data);
			return responseDTO;
		} catch (ParameterException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_PARAMETER_WRONG,
					MessageCommon.FAIL_MESSAGE_PARAMETER);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtil.ConvertToFailResponse(MessageCommon.STATUS_FAIL, MessageCommon.FAIL_MESSAGE);
		}
	}
}
