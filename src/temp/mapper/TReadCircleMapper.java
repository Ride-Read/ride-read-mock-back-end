package temp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import qi.yue.entity.ReadCircle;
import temp.po.TReadCircleExample;

public interface TReadCircleMapper {
    int countByExample(TReadCircleExample example);

    int deleteByExample(TReadCircleExample example);

    int deleteByPrimaryKey(Integer mid);

    int insert(ReadCircle record);

    int insertSelective(ReadCircle record);

    List<ReadCircle> selectByExample(TReadCircleExample example);

    ReadCircle selectByPrimaryKey(Integer mid);

    int updateByExampleSelective(@Param("record") ReadCircle record, @Param("example") TReadCircleExample example);

    int updateByExample(@Param("record") ReadCircle record, @Param("example") TReadCircleExample example);

    int updateByPrimaryKeySelective(ReadCircle record);

    int updateByPrimaryKey(ReadCircle record);
}