package qi.yue.dao.mapper;

import qi.yue.dto.VersionNumberDTO;
import qi.yue.entity.VersionNumber;

public interface VersionNumberMapper {
	int insert(VersionNumber versionNumber);

	VersionNumberDTO find(Integer id);

	VersionNumberDTO findLastVersion(Integer versionType);
}