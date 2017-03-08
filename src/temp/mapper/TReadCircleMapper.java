package temp.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import temp.po.TReadCircle;
import temp.po.TReadCircleExample;

public interface TReadCircleMapper {
    int countByExample(TReadCircleExample example);

    int deleteByExample(TReadCircleExample example);

    int deleteByPrimaryKey(Integer mid);

    int insert(TReadCircle record);

    int insertSelective(TReadCircle record);

    List<TReadCircle> selectByExample(TReadCircleExample example);

    TReadCircle selectByPrimaryKey(Integer mid);

    int updateByExampleSelective(@Param("record") TReadCircle record, @Param("example") TReadCircleExample example);

    int updateByExample(@Param("record") TReadCircle record, @Param("example") TReadCircleExample example);

    int updateByPrimaryKeySelective(TReadCircle record);

    int updateByPrimaryKey(TReadCircle record);
}