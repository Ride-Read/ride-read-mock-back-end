package temp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import temp.po.TFollower;
import temp.po.TFollowerExample;

public interface TFollowerMapper {
    int countByExample(TFollowerExample example);

    int deleteByExample(TFollowerExample example);

    int deleteByPrimaryKey(Integer fid);

    int insert(TFollower record);

    int insertSelective(TFollower record);

    List<TFollower> selectByExample(TFollowerExample example);

    TFollower selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(@Param("record") TFollower record, @Param("example") TFollowerExample example);

    int updateByExample(@Param("record") TFollower record, @Param("example") TFollowerExample example);

    int updateByPrimaryKeySelective(TFollower record);

    int updateByPrimaryKey(TFollower record);
}