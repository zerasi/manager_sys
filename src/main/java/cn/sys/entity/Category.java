package cn.sys.entity;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2020-03-20
 */
public class Category {
    private Integer id;

    private String cate_no;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类详细
     */
    private String bak1;

    private String bak2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCate_no() {
        return cate_no;
    }

    public void setCate_no(String cate_no) {
        this.cate_no = cate_no == null ? null : cate_no.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBak1() {
        return bak1;
    }

    public void setBak1(String bak1) {
        this.bak1 = bak1 == null ? null : bak1.trim();
    }

    public String getBak2() {
        return bak2;
    }

    public void setBak2(String bak2) {
        this.bak2 = bak2 == null ? null : bak2.trim();
    }
}