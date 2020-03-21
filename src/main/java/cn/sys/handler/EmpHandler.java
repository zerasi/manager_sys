package cn.sys.handler;

import cn.sys.entity.Emp;
import cn.sys.service.EmpService;
import cn.sys.utils.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.org.apache.regexp.internal.RE;

@Controller
public class EmpHandler
{
  @Autowired
  private EmpService service;
  
  @RequestMapping({"findAllemp"})
  @ResponseBody
  public List<Emp> findAllemp()
  {
    try
    {
      return this.service.findAllemp();
    }
    catch (Exception localException) {}
    return null;
  }
  
  @RequestMapping({"emp_add"})
  @ResponseBody
  public Map empAdd(Emp emp, String roleIds)
  {
    try
    {
      this.service.empAdd(emp, roleIds);
      return ajaxReturn(true, "操作成功");
    }
    catch (Exception localException) {}
    return ajaxReturn(false, "操作失败");
  }
  
  @RequestMapping({"deleteEmpById"})
  @ResponseBody
  public Map deleteEmpById(String ids)
  {
    try
    {
      this.service.deleteEmpById(ids);
      return ajaxReturn(true, "操作成功");
    }
    catch (Exception localException) {}
    return ajaxReturn(false, "操作失败");
  }
  
  @RequestMapping({"findByEmpId"})
  @ResponseBody
  public Emp findByEmpId(String id)
  {
    try
    {
      return this.service.findByEmpId(Integer.parseInt(id));
    }
    catch (Exception localException) {}
    return null;
  }
  
  public Map ajaxReturn(boolean b, Object obj)
  {
    Map<String, Object> map = new HashMap();
    map.put("success", Boolean.valueOf(b));
    map.put("message", obj);
    return map;
  }
  
  @RequestMapping("empLogin")
  public @ResponseBody Result empLogin(HttpSession session,Emp emp){
	  List<Emp> eemp=service.empLogin(emp);
	  if(eemp.size()>0){
		  session.setAttribute("emp", eemp.get(0));
		  return new Result(true, "");
	  }else{
		  return new Result(false, "");
		  
	  }
  }
  
  @RequestMapping("empname")
  public @ResponseBody String empname(HttpSession session){
	  Emp emp=(Emp)session.getAttribute("emp");
	  if(emp!=null){
		  
		  return emp.getUsername();
	  }
	  return null;
  }
}
