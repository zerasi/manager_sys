package cn.sys.entity;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2020-03-20
 */
public class User {
    private Integer id;

    private String username;

    private String adres;

    private String link_person;

    private String realname;

    private String accNbr;

    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres == null ? null : adres.trim();
    }

    public String getLink_person() {
        return link_person;
    }

    public void setLink_person(String link_person) {
        this.link_person = link_person == null ? null : link_person.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getAccNbr() {
        return accNbr;
    }

    public void setAccNbr(String accNbr) {
        this.accNbr = accNbr == null ? null : accNbr.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}