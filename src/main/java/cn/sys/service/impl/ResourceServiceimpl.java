package cn.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.sys.dao.CategoryMapper;
import cn.sys.dao.ResourceMapper;
import cn.sys.entity.Category;
import cn.sys.entity.Resource;
import cn.sys.entity.ResourceExample;
import cn.sys.service.ResourceService;
import cn.sys.utils.PageResult;

@Service
@Transactional
public class ResourceServiceimpl implements ResourceService{
	
	@Autowired
	private ResourceMapper mapper;
	@Autowired
	private CategoryMapper amapper;
	

	
	public List<Resource> findAll() {
		return mapper.selectByExample(new ResourceExample());
	}

	
	public void add(Resource resource) {
		mapper.insertSelective(resource);
	}

	
	public void update(Resource resource) {
		mapper.updateByPrimaryKeySelective(resource);
		
	}

	
	public Resource findOne(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	
	public PageResult findPage(ResourceExample example, Integer page, Integer rows) {
		PageHelper.startPage(page,rows);
		Page<Resource> pages = (Page<Resource>)this.mapper.selectByExample(example);
		List<Resource> list =  pages.getResult();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setCategory(amapper.selectByPrimaryKey(list.get(i).getCid()));
		}
		return new PageResult(pages.getTotal(), pages.getResult());
	}

	
	public void delete(int parseInt) {
		mapper.deleteByPrimaryKey(parseInt);
		
	}


	@Override
	public List<Resource> addOrUpdateBefore(Resource resource) {
		ResourceExample example = new ResourceExample();
		example.createCriteria().andBak1EqualTo(resource.getBak1());
		return this.mapper.selectByExample(example);
	}

}
