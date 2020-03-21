package cn.sys.service;

import java.util.List;

import cn.sys.entity.Resource;
import cn.sys.entity.ResourceExample;
import cn.sys.utils.PageResult;

public interface ResourceService {

	List<Resource> findAll();

	void add(Resource resource);

	void update(Resource resource);

	Resource findOne(Integer id);

	PageResult findPage(ResourceExample example, Integer page, Integer rows);

	void delete(int parseInt);

	List<Resource> addOrUpdateBefore(Resource resource);

}
