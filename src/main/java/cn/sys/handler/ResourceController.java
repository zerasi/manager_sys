package cn.sys.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.sys.entity.Resource;
import cn.sys.entity.ResourceExample;
import cn.sys.service.ResourceService;
import cn.sys.utils.PageResult;
import cn.sys.utils.Result;

@RestController
public class ResourceController {

	@Autowired
	private ResourceService ResourceService;
	
	@RequestMapping("findAllResource")
	public List<Resource> findALl(){
		return ResourceService.findAll();
	}
	
	@RequestMapping("Resource_save")
	public Result add(Resource Resource){
		try {
			if(Resource.getId()==null){
				
				List<Resource> list = this.ResourceService.addOrUpdateBefore(Resource);
				if(list.size()>0){
					return new Result(true, "商品编号已存在");
				}
				this.ResourceService.add(Resource);
			}else{
				List<Resource> list = this.ResourceService.addOrUpdateBefore(Resource);
				if(list.size()>0){
					if(list.get(0).getId().intValue() != Resource.getId().intValue()){
						return new Result(true, "商品编号已存在");
					}
				}
				this.ResourceService.update(Resource);
			}
			return new Result(true, "成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "失败");
		}
	}
	
	@RequestMapping("findAllProduct")
	public PageResult findPage(Resource Resource,Integer page,Integer rows){
		ResourceExample example = new ResourceExample();
		return this.ResourceService.findPage(example,page,rows);
	}
	
	@RequestMapping("ResourcefindOne")
	public Resource findOne(Integer id){
		Resource Resource = this.ResourceService.findOne(id);
		return Resource;
	}
	
	@RequestMapping("Resourceupdate")
	public Result update( Resource Resource){
		try {
			this.ResourceService.update(Resource);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}
	
	@RequestMapping(value="delete_product")
	public Result delete(String ids,Integer a){
		
		try {
			for (String id : ids.split(",")) {
				this.ResourceService.delete(Integer.parseInt(id));
			}
			return new Result(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "操作失败");
		}
	} 
	
}
