package qi.yue.dto;

import java.util.Date;

public class VersionNumberDTO {
	private Integer id;
	private Integer version_type;
	private String version;
	private String version_url;
	private Date create_at;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion_type() {
		return version_type;
	}

	public void setVersion_type(Integer version_type) {
		this.version_type = version_type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion_url() {
		return version_url;
	}

	public void setVersion_url(String version_url) {
		this.version_url = version_url;
	}

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}
}
