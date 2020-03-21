package cn.sys.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.FutureTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.sys.entity.Rein;
import cn.sys.entity.ReinExample;
import cn.sys.entity.Rein;
import cn.sys.service.ReinService;
import cn.sys.utils.ImportExcelUtils;
import cn.sys.utils.PageResult;
import cn.sys.utils.Result;

@RestController
@RequestMapping("rein")
public class ReinController {

	@Autowired
	private ReinService ReinService;
	
	@RequestMapping("findAll")
	public List<Rein> findALl(){
		return ReinService.findAll();
	}
	
	@RequestMapping("save")
	public Result add(Rein Rein){
		try {
			/*if(Rein.getId()==null){
				this.ReinService.add(Rein);
			}else{
				this.ReinService.update(Rein);
			}*/
			this.ReinService.add(Rein);
			return new Result(true, "成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "失败");
		}
	}
	
	@RequestMapping("findPage")
	public PageResult findPage(Rein Rein,Integer page,Integer rows){
		ReinExample example = new ReinExample();
		return this.ReinService.findPage(example,page,rows);
	}
	
	@RequestMapping("findOne")
	public Rein findOne(Integer id){
		Rein Rein = this.ReinService.findOne(id);
		return Rein;
	}
	
	@RequestMapping("update")
	public Result update( Rein Rein){
		try {
			this.ReinService.update(Rein);
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
				this.ReinService.delete(Integer.parseInt(id));
			}
			return new Result(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "操作失败");
		}
	} 
	
	@RequestMapping(value = "importExcel",method = RequestMethod.POST)
	public Result importUsers(MultipartFile excelFile){
		
		try {
			
			return new Result(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "操作失败");
		}
	} 
	
	@RequestMapping(value = "/uploadApk")
	@ResponseBody
	public Object uploadApk(
	        @RequestParam(value = "apkFile") MultipartFile apkFile,
	        HttpServletRequest request, HttpServletResponse response) {
	    Map<String,Object> resMap = new HashMap<String,Object>();
	    if (apkFile != null) {
	        //获取保存的路径，
	        String realPath = request.getSession().getServletContext()
	                .getRealPath("/upload");
	        //System.out.println(realPath);
	        if (apkFile.isEmpty()) {
	            // 未选择文件
	            resMap.put("status", "empty");
	        } else{
	            // 文件原名称
	            String originFileName = apkFile.getOriginalFilename();
	            try {
	                //这里使用Apache的FileUtils方法来进行保存
	                /*FileUtils.copyInputStreamToFile(apkFile.getInputStream(),
	                        new File(realPath, originFileName));*/
	            	ImportExcelUtils excel = new ImportExcelUtils();
	            	List<Object[]> list = excel.importExcel03((FileInputStream) apkFile.getInputStream());
	          	      for(Object[] ss:list){ 
	          	    	Rein rein = new Rein();
	          	        rein.setOnum((String)ss[0]);
	          	        rein.setRe_no((String) ss[1]);
	          	        rein.setTotal(new BigDecimal((String) ss[3]));
	          	        rein.setCount(Long.parseLong((String) ss[2]));
	          	        rein.setPeople((String) ss[4]);
	          	        rein.setRemark((String) ss[5]);
	          	        rein.setCreate_date(new Date());
	          	        this.ReinService.addByExcel(rein);
	          	      } 
	            	
	                resMap.put("status","true");
	            } catch (Exception e) {
	                System.out.println("文件上传失败");
	                resMap.put("status", "false");
	                e.printStackTrace();
	            }
	        }
	 
	    }
	    return resMap;
	}
	
	
	/*@RequestMapping(value = "importExcel",method = RequestMethod.POST)
    public Map<String,Object> importUsers(MultipartFile excelFile){
        Map<String,Object> resultMap = new HashMap<>();
        if(excelFile == null || excelFile.isEmpty()){
            resultMap.put("success", false);
            resultMap.put("msg", "数据文件不存在");
            return resultMap;
        }
        //开启新的线程来处理导入，并使用callback接受结果
        FutureTask<Map<String,Object>> task = new FutureTask<>(()->this.ReinService.importExcel(excelFile));
      
        new Thread(task).start();
        try {
            resultMap = task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }*/
}
