package temp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import temp.po.TFollowing;
import temp.po.TFollowingExample;

public interface TFollowingMapper {
    int countByExample(TFollowingExample example);

    int deleteByExample(TFollowingExample example);

    int deleteByPrimaryKey(Integer fid);

    int insert(TFollowing record);

    int insertSelective(TFollowing record);

    List<TFollowing> selectByExample(TFollowingExample example);

    TFollowing selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(@Param("record") TFollowing record, @Param("example") TFollowingExample example);

    int updateByExample(@Param("record") TFollowing record, @Param("example") TFollowingExample example);

    int updateByPrimaryKeySelective(TFollowing record);

    int updateByPrimaryKey(TFollowing record);
}