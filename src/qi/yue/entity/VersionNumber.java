package qi.yue.entity;

import java.util.Date;

import com.sun.jmx.snmp.Timestamp;

public class VersionNumber {
	private Integer id;
	private Integer versionType;
	private String version;
	private Date createAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersionType() {
		return versionType;
	}

	public void setVersionType(Integer versionType) {
		this.versionType = versionType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
}
