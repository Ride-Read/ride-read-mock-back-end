package qi.yue.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import qi.yue.dao.mapper.VersionNumberMapper;
import qi.yue.dto.VersionNumberDTO;
import qi.yue.entity.VersionNumber;
import qi.yue.service.VersionNumberService;

@Service
public class VersionNumberServiceImpl implements VersionNumberService {

	@Resource
	private VersionNumberMapper versionNumberMapper;

	@Override
	public VersionNumberDTO find(Integer id) {
		return versionNumberMapper.find(id);
	}

	@Override
	public int save(VersionNumber versionNumber) {
		return versionNumberMapper.insert(versionNumber);
	}

	@Override
	public VersionNumberDTO findLastVersion(Integer versionType) {
		return versionNumberMapper.findLastVersion(versionType);
	}

}
