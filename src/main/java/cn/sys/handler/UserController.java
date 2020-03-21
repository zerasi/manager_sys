package cn.sys.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.sys.entity.User;
import cn.sys.entity.UserExample;
import cn.sys.service.UserService;
import cn.sys.utils.PageResult;
import cn.sys.utils.Result;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService UserService;
	
	
	@RequestMapping("updatelogin")
	public Result updatelogin(HttpSession session,User User){
		try {
			this.UserService.update(User);
			session.setAttribute("User", User);
			return new Result(true, "成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "失败");
		}
	}
	
	@RequestMapping("zhanshi")
	public User updatelogin(HttpSession session){
		User existUser = (User) session.getAttribute("User");
		if(existUser!=null){
			return existUser;
		}
		return null;
	}
	
	@RequestMapping("findAll")
	public List<User> findALl(User User){
		UserExample example = new UserExample();
		return UserService.findAll(example);
	}
	
	@RequestMapping("add")
	public Result add(User User){
		try {
			if(User.getId()==null){
				this.UserService.add(User);
			}else{
				this.UserService.update(User);
			}
			return new Result(true, "成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "失败");
		}
	}
	
	@RequestMapping("findPage")
	public PageResult findPage(User User,Integer page,Integer rows){
		UserExample example = new UserExample();
		return this.UserService.findPage(example,page,rows);
	}
	
	@RequestMapping("findOne")
	public User findOne(Integer id){
		User User = this.UserService.findOne(id);
		return User;
	}
	
	@RequestMapping("update")
	public Result update( User User){
		try {
			this.UserService.update(User);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}
	
	@RequestMapping(value="delete")
	public Result delete(String ids){
		
		try {
			for (String id : ids.split(",")) {
				this.UserService.delete(Integer.parseInt(id));
			}
			return new Result(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "操作失败");
		}
	} 
	
	
}
