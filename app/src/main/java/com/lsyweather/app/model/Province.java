package com.lsyweather.app.model;

/**
 * Created by Administrator on 2016/5/25.
 */
public class Province {
    private int id;
    private String provinceName;
    private String provinceCode;

    public void setId(int id) {
        this.id = id;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getId() {
        return id;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }
}
