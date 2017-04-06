package qi.yue.dto;

import java.util.Date;

public class SmsCodeDTO {
	private Integer id;

	private String phonenumber;

	private String rand_code;

	private Integer count;

	private Date created_at;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getRand_code() {
		return rand_code;
	}

	public void setRand_code(String rand_code) {
		this.rand_code = rand_code;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

}