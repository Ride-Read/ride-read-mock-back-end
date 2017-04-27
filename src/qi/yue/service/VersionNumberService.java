package qi.yue.service;

import qi.yue.dto.VersionNumberDTO;
import qi.yue.entity.VersionNumber;

public interface VersionNumberService {
	public VersionNumberDTO find(Integer id);

	public int save(VersionNumber versionNumber);

	VersionNumberDTO findLastVersion(Integer versionType);
}
