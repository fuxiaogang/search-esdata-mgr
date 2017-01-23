package com.wlw.wlsearch.entity;

import java.util.Date;

/**
 * @author fuxg
 * @create 2017-01-23 16:38
 */
public class SearchResult {
    private Integer id;
    private String ico;
    private String title;
    private String logo;
    private String address;
    private String content;
    private Date operatime;
    private Float price;
    private Float meter;
    private String url;
    private String cat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getOperatime() {
        return operatime;
    }

    public void setOperatime(Date operatime) {
        this.operatime = operatime;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getMeter() {
        return meter;
    }

    public void setMeter(Float meter) {
        this.meter = meter;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }
}
