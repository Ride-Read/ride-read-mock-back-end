package qi.yue.dto;

/**
 * @Title ResponseDTO
 * @author WUSINAN
 * @date 2017年3月30日
 * Description: 接口返回参数DTO
 */
public class ResponseDTO {
	private String status;
	private Object data;
	private String msg;
	

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
