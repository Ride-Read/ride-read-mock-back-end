package qi.yue.dto;

public class ResponseDTO {
	private Integer status;
	private Object data;
	private Long timestamp;

	public ResponseDTO() {
		super();
	}

	public ResponseDTO(Integer status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}

	public ResponseDTO(Integer status, Object data, Long timestamp) {
		super();
		this.status = status;
		this.data = data;
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}
