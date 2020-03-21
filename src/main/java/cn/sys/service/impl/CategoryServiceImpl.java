package cn.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.sys.dao.CategoryMapper;
import cn.sys.entity.Category;
import cn.sys.entity.CategoryExample;
import cn.sys.service.CategoryService;
import cn.sys.utils.PageResult;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryMapper mapper;

	
	public List<Category> findAll() {
		return mapper.selectByExample(new CategoryExample());
	}

	
	public void add(Category category) {
		mapper.insertSelective(category);
	}

	
	public void update(Category category) {
		mapper.updateByPrimaryKey(category);
		
	}

	
	public PageResult findPage(CategoryExample example, Integer page, Integer rows) {
		PageHelper.startPage(page,rows);
		Page<Category> pages = (Page<Category>)this.mapper.selectByExample(example);
		return new PageResult(pages.getTotal(), pages.getResult());
	}

	
	public Category findOne(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	
	public void delete(int parseInt) {
		mapper.deleteByPrimaryKey(parseInt);
	}

}
