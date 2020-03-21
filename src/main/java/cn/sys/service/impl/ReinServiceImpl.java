package cn.sys.service.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.sys.dao.ReinMapper;
import cn.sys.dao.ResourceMapper;
import cn.sys.entity.Rein;
import cn.sys.entity.ReinExample;
import cn.sys.entity.Resource;
import cn.sys.entity.ResourceExample;
import cn.sys.service.ReinService;
import cn.sys.utils.PageResult;

@Service
@Transactional
public class ReinServiceImpl implements ReinService{
	
	@Autowired
	private ReinMapper mapper;
	
	@Autowired
	private ResourceMapper remapper;

	
	public List<Rein> findAll() {
		return mapper.selectByExample(new ReinExample());
	}

	
	public void add(Rein Rein) {
		Resource resource = remapper.selectByPrimaryKey(Rein.getRid());
		resource.setSurplus(resource.getSurplus()+Rein.getCount());
		remapper.updateByPrimaryKeySelective(resource);
		mapper.insertSelective(Rein);
	}

	
	public void update(Rein Rein) {
		/*Rein reinyuan = mapper.selectByPrimaryKey(Rein.getId());
		Integer sub = Rein.getCount()-reinyuan.getCount();
		Resource resource = remapper.selectByPrimaryKey(Rein.getRid());
		resource.setTotal(resource.getTotal()+sub);
		remapper.updateByPrimaryKeySelective(resource);
		mapper.updateByPrimaryKey(Rein);*/
		
	}

	
	public PageResult findPage(ReinExample example, Integer page, Integer rows) {
		PageHelper.startPage(page,rows);
		Page<Rein> pages = (Page<Rein>)this.mapper.selectByExample(example);
		List<Rein> list = pages.getResult();
		for(Rein rein:list){
			rein.setResource(this.remapper.selectByPrimaryKey(rein.getRid()));
		}
		return new PageResult(pages.getTotal(), list);
	}

	
	public Rein findOne(Integer id) {
		Rein rein =mapper.selectByPrimaryKey(id);
		rein.setResource(this.remapper.selectByPrimaryKey(rein.getRid()));
		return rein;
	}

	
	public void delete(int parseInt) {
		mapper.deleteByPrimaryKey(parseInt);
	}


	@Override
	public void addByExcel(Rein rein) {
		ResourceExample resourceExample = new ResourceExample();
		resourceExample.createCriteria().andBak1EqualTo(rein.getRe_no());
		List<Resource> list = remapper.selectByExample(resourceExample);
		if(list.size() <= 0){
			throw new RuntimeException(rein.getRe_no()+"商品编号不存在");
		}
		Resource resource = list.get(0);
		resource.setSurplus(resource.getSurplus()+rein.getCount());
		rein.setRid(resource.getId());
		remapper.updateByPrimaryKeySelective(resource);
		mapper.insertSelective(rein);
		
	}


}
