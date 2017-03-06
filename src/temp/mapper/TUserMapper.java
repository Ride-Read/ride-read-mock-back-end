package temp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import qi.yue.entity.User;
import temp.po.TUserExample;

public interface TUserMapper {
    int countByExample(TUserExample example);

    int deleteByExample(TUserExample example);

    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(TUserExample example);

    User selectByPrimaryKey(Integer uid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") TUserExample example);

    int updateByExample(@Param("record") User record, @Param("example") TUserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}