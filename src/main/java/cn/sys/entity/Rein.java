package cn.sys.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2020-03-19
 */
public class Rein {
    private Integer id;

    /**
     * 单据号
     */
    private String onum;

    /**
     * 商品数量
     */
    private Long count;

    /**
     * 总金额
     */
    private BigDecimal total;

    /**
     * 经办人
     */
    private String people;

    /**
     * 备注
     */
    private String remark;

    /**
     * 库存商品
     */
    private Integer rid;

    /**
     * 入库单创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date create_date;
    
    private Resource resource;
    
    private String re_no;

    public String getRe_no() {
		return re_no;
	}

	public void setRe_no(String re_no) {
		this.re_no = re_no;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOnum() {
        return onum;
    }

    public void setOnum(String onum) {
        this.onum = onum == null ? null : onum.trim();
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people == null ? null : people.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}