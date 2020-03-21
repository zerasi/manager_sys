package cn.sys.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.sys.entity.Category;
import cn.sys.entity.CategoryExample;
import cn.sys.entity.Category;
import cn.sys.service.CategoryService;
import cn.sys.utils.PageResult;
import cn.sys.utils.Result;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("findAllCategory")
	public List<Category> findALl(){
		return categoryService.findAll();
	}
	
	@RequestMapping("category_save")
	public Result add(Category category){
		try {
			if(category.getId()==null){
				this.categoryService.add(category);
			}else{
				this.categoryService.update(category);
			}
			return new Result(true, "成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "失败");
		}
	}
	
	@RequestMapping("CategoryfindPage")
	public PageResult findPage(Category category,Integer page,Integer rows){
		CategoryExample example = new CategoryExample();
		return this.categoryService.findPage(example,page,rows);
	}
	
	@RequestMapping("CategoryfindOne")
	public Category findOne(Integer id){
		Category Category = this.categoryService.findOne(id);
		return Category;
	}
	
	@RequestMapping("Categoryupdate")
	public Result update( Category Category){
		try {
			this.categoryService.update(Category);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}
	
	@RequestMapping(value="delete_category")
	public Result delete(String ids,Integer a){
		
		try {
			for (String id : ids.split(",")) {
				this.categoryService.delete(Integer.parseInt(id));
			}
			return new Result(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "操作失败");
		}
	} 
	
}
