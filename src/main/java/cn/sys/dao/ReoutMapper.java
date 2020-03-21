package cn.sys.dao;

import cn.sys.entity.Reout;
import cn.sys.entity.ReoutExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReoutMapper {
    int countByExample(ReoutExample example);

    int deleteByExample(ReoutExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Reout record);

    int insertSelective(Reout record);

    List<Reout> selectByExample(ReoutExample example);

    Reout selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Reout record, @Param("example") ReoutExample example);

    int updateByExample(@Param("record") Reout record, @Param("example") ReoutExample example);

    int updateByPrimaryKeySelective(Reout record);

    int updateByPrimaryKey(Reout record);
}