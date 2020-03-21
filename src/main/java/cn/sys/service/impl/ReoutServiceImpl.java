package cn.sys.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.sys.dao.CategoryMapper;
import cn.sys.dao.ReoutMapper;
import cn.sys.dao.ResourceMapper;
import cn.sys.dao.UserMapper;
import cn.sys.entity.Category;
import cn.sys.entity.Reout;
import cn.sys.entity.ReoutExample;
import cn.sys.entity.Resource;
import cn.sys.entity.ResourceExample;
import cn.sys.service.ReoutService;
import cn.sys.utils.PageResult;
import cn.sys.utils.Result;

@Service
@Transactional
public class ReoutServiceImpl implements ReoutService{
	
	@Autowired
	private ReoutMapper mapper;
	
	@Autowired
	private ResourceMapper remapper;
	
	@Autowired
	private UserMapper usermapper;
	
	@Autowired
	private CategoryMapper categoryMapper;

	
	public List<Reout> findAll() {
		List<Reout> list = mapper.selectByExample(new ReoutExample());
		for(Reout Reout:list){
			Resource resource = this.remapper.selectByPrimaryKey(Reout.getRid());
			resource.setCategory(this.categoryMapper.selectByPrimaryKey(resource.getCid()));
			Reout.setResource(resource);
			Reout.setUser(this.usermapper.selectByPrimaryKey(Reout.getUid()));
		}
		return list;
	}

	
	public Result add(Reout Reout){
		Resource resource = remapper.selectByPrimaryKey(Reout.getRid());
		Long surplus = resource.getSurplus()-Reout.getCount();
		if(resource.getSurplus()<0){
			return new Result(false, "商品剩余不足！");
		}
		resource.setSurplus(surplus);
		remapper.updateByPrimaryKeySelective(resource);
		Reout.setTotal(resource.getUnit_price().multiply(new BigDecimal(Reout.getCount())));
		mapper.insertSelective(Reout);
		return new Result(true, "操作成功");
	}

	
	public void update(Reout Reout) throws Exception {
		Resource resource = remapper.selectByPrimaryKey(Reout.getRid());
		/*Reout reoutyuan = mapper.selectByPrimaryKey(Reout.getId());
		Integer sub = Reout.getCount()-reoutyuan.getCount();
		resource.setTotal(resource.getTotal()-sub);
		if(resource.getTotal()<0){
			throw new Exception();
			
		}
		remapper.updateByPrimaryKeySelective(resource);*/
		mapper.updateByPrimaryKey(Reout);
		
	}

	
	public PageResult findPage(ReoutExample example, Integer page, Integer rows) {
		PageHelper.startPage(page,rows);
		Page<Reout> pages = (Page<Reout>)this.mapper.selectByExample(example);
		List<Reout> list = pages.getResult();
		for(Reout Reout:list){
			Reout.setResource(this.remapper.selectByPrimaryKey(Reout.getRid()));
			Reout.setUser(this.usermapper.selectByPrimaryKey(Reout.getUid()));
		}
		return new PageResult(pages.getTotal(), list);
	}

	
	public Reout findOne(Integer id) {
		Reout Reout =mapper.selectByPrimaryKey(id);
		Reout.setResource(this.remapper.selectByPrimaryKey(Reout.getRid()));
		Reout.setUser(this.usermapper.selectByPrimaryKey(Reout.getUid()));
		return Reout;
	}

	
	public void delete(int parseInt) {
		mapper.deleteByPrimaryKey(parseInt);
	}

	
	public List showChart() {
		List<List> ll = new ArrayList<List>();
		List<Resource> list = this.remapper.selectByExample(new ResourceExample());
		for(Resource resource : list){
			Long count = 0L;
			ReoutExample reoutExample = new ReoutExample();
			reoutExample.createCriteria().andRidEqualTo(resource.getId());
			List<Reout> reoutList = this.mapper.selectByExample(reoutExample);
			for(Reout reout : reoutList){
				count = count + reout.getCount();
			}
			List<Object> strList = new ArrayList<Object>();
			strList.add(resource.getName());
			strList.add(count);
			ll.add(strList);
		}
		return ll;
	}

}
