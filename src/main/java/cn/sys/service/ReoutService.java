package cn.sys.service;

import java.util.List;

import cn.sys.entity.Reout;
import cn.sys.entity.ReoutExample;
import cn.sys.utils.PageResult;
import cn.sys.utils.Result;

public interface ReoutService {

	List<Reout> findAll();

	Result add(Reout Reout) throws Exception;

	void update(Reout Reout) throws Exception ;

	PageResult findPage(ReoutExample example, Integer page, Integer rows);

	Reout findOne(Integer id);

	void delete(int parseInt);

	List showChart();


}
