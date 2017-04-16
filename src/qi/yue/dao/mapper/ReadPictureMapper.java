package qi.yue.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import temp.po.TReadPicture;
import temp.po.TReadPictureExample;

public interface ReadPictureMapper {
    int countByExample(TReadPictureExample example);

    int deleteByExample(TReadPictureExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TReadPicture record);

    int insertSelective(TReadPicture record);

    List<TReadPicture> selectByExampleWithBLOBs(TReadPictureExample example);

    List<TReadPicture> selectByExample(TReadPictureExample example);

    TReadPicture selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TReadPicture record, @Param("example") TReadPictureExample example);

    int updateByExampleWithBLOBs(@Param("record") TReadPicture record, @Param("example") TReadPictureExample example);

    int updateByExample(@Param("record") TReadPicture record, @Param("example") TReadPictureExample example);

    int updateByPrimaryKeySelective(TReadPicture record);

    int updateByPrimaryKeyWithBLOBs(TReadPicture record);

    int updateByPrimaryKey(TReadPicture record);
}