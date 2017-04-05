package qi.yue.dto;

/**
 * @Title ResponseDTO
 * @author WUSINAN
 * @date 2017年3月30日 Description: 接口返回参数DTO
 */
public class ResponseDTO {
	private String status;
	private Object data;
	private String msg;
	private Long timestamp;

	public ResponseDTO() {
		super();
	}

	public ResponseDTO(String status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}

	public ResponseDTO(String status, Object data, String msg) {
		super();
		this.status = status;
		this.data = data;
		this.msg = msg;
	}

	public ResponseDTO(String status, String msg, Long timestamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timestamp = timestamp;
	}

	public ResponseDTO(String status, Object data, String msg, Long timestamp) {
		super();
		this.status = status;
		this.data = data;
		this.msg = msg;
		this.timestamp = timestamp;
	}

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

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}
