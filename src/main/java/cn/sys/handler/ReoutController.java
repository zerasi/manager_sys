package cn.sys.handler;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import cn.sys.dao.UserMapper;
import cn.sys.entity.Reout;
import cn.sys.entity.ReoutExample;
import cn.sys.entity.User;
import cn.sys.entity.Reout;
import cn.sys.service.ReoutService;
import cn.sys.utils.DateUtils;
import cn.sys.utils.ExcelUtil;
import cn.sys.utils.PageResult;
import cn.sys.utils.Result;

@RestController
@RequestMapping("reout")
public class ReoutController {

	@Autowired
	private ReoutService ReoutService;
	
	
	
	@RequestMapping("findAll")
	public List<Reout> findALl(){
		return ReoutService.findAll();
	}
	
	@Autowired
	private UserMapper usermapper;
	
	@RequestMapping("save")
	public Result add(Reout Reout){
		try {
			Result result = this.ReoutService.add(Reout);
			if(result.getSuccess()){
				return new Result(true, "操作成功");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "失败");
		}
	}
	
	@RequestMapping("findPage")
	public PageResult findPage(Reout Reout,Integer page,Integer rows){
		ReoutExample example = new ReoutExample();
		return this.ReoutService.findPage(example,page,rows);
	}
	
	@RequestMapping("findOne")
	public Reout findOne(Integer id){
		Reout Reout = this.ReoutService.findOne(id);
		return Reout;
	}
	
	@RequestMapping("update")
	public Result update( Reout Reout){
		try {
			this.ReoutService.update(Reout);
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
				this.ReoutService.delete(Integer.parseInt(id));
			}
			return new Result(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "操作失败");
		}
	} 
	
	@RequestMapping("showChart")
	public List pingfenShowChart(){
		try {
			
			List<List> ll = this.ReoutService.showChart();
			System.out.println(JSON.toJSONString(ll));
			return ll;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	@RequestMapping("export")
    public void export(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//获取数据
		List<Reout> list = this.ReoutService.findAll();
		
		//excel标题
		String[] title = {"单据号","商品名称","商品分类","数量","应付金额","实际付款金额","购买会员","非会员购买","备注","出库时间"};
		
		//excel文件名
	    String fileName = "销售记录表"+System.currentTimeMillis()+".xls";
	    
	    //sheet名
	    String sheetName = "销售记录表";
	    
	    //String[] content[i] = new String[title.length];
	    String[][] content = new String[list.size()][title.length];
		for (int i = 0; i < list.size(); i++) {
	    	//content[i][] = new String[title.length];
			Reout obj = list.get(i);
            content[i][0] = obj.getOnum();
            content[i][1] = obj.getResource().getName();

            content[i][3] = obj.getCount()+obj.getResource().getUnit();
            content[i][4] = obj.getTotal().toString();
            content[i][5] = obj.getActual_total().toString();
            content[i][6] = obj.getUser().getUsername();
            content[i][7] = obj.getPurchaser();
            content[i][8] = obj.getRemark();
            content[i][9] = DateUtils.getNowDate(obj.getCreate_date());
		}
	    
	   //创建HSSFWorkbook 
		HSSFWorkbook wb = new HSSFWorkbook();
		wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, wb);
		
		try {
			this.setResponseHeader(response, fileName);
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	 public void setResponseHeader(HttpServletResponse response, String fileName) {
	        try {
	            try {
	                fileName = new String(fileName.getBytes(),"ISO8859-1");
	            } catch (UnsupportedEncodingException e) {
	                e.printStackTrace();
	            }
	            response.setContentType("application/octet-stream;charset=ISO8859-1");
	            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
	            response.addHeader("Pargam", "no-cache");
	            response.addHeader("Cache-Control", "no-cache");
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	
}
