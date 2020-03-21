package cn.sys.dao;

import cn.sys.entity.Rein;
import cn.sys.entity.ReinExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReinMapper {
    int countByExample(ReinExample example);

    int deleteByExample(ReinExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Rein record);

    int insertSelective(Rein record);

    List<Rein> selectByExample(ReinExample example);

    Rein selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Rein record, @Param("example") ReinExample example);

    int updateByExample(@Param("record") Rein record, @Param("example") ReinExample example);

    int updateByPrimaryKeySelective(Rein record);

    int updateByPrimaryKey(Rein record);
}