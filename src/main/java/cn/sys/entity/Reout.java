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
public class Reout {
    private Integer id;

    /**
     * 单据号
     */
    private String onum;

    private Long count;

    /**
     * 应付金额
     */
    private BigDecimal total;

    /**
     * 实际付款金额
     */
    private BigDecimal actual_total;

    /**
     * 经办人
     */
    private String people;

    /**
     * 备注
     */
    private String remark;

    /**
     * 库存商品id
     */
    private Integer rid;

    /**
     * 出库单创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date create_date;

    /**
     * （若为会员购买填入会员信息，若非会员，填写采购对手信息）
     */
    private Integer uid;
    private User user;

    /**
     * 采购对手信息
     */
    private String purchaser;

    private String purchaser_bak;
    
    private Resource resource;

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

    public BigDecimal getActual_total() {
        return actual_total;
    }

    public void setActual_total(BigDecimal actual_total) {
        this.actual_total = actual_total;
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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser == null ? null : purchaser.trim();
    }

    public String getPurchaser_bak() {
        return purchaser_bak;
    }

    public void setPurchaser_bak(String purchaser_bak) {
        this.purchaser_bak = purchaser_bak == null ? null : purchaser_bak.trim();
    }
}