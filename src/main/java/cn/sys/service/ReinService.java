package cn.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import cn.sys.entity.Rein;
import cn.sys.entity.ReinExample;
import cn.sys.utils.PageResult;

public interface ReinService {

	List<Rein> findAll();

	void add(Rein Rein);

	void update(Rein Rein);

	PageResult findPage(ReinExample example, Integer page, Integer rows);

	Rein findOne(Integer id);

	void delete(int parseInt);

	void addByExcel(Rein rein);


}
