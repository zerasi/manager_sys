package cn.sys.service;

import java.util.List;

import cn.sys.entity.Category;
import cn.sys.entity.CategoryExample;
import cn.sys.utils.PageResult;

public interface CategoryService {

	List<Category> findAll();

	void add(Category category);

	void update(Category category);

	PageResult findPage(CategoryExample example, Integer page, Integer rows);

	Category findOne(Integer id);

	void delete(int parseInt);

}
