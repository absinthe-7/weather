package model;

public class City {

    private int id;

    private String cityName;

    private String cityCode;

    private int provinceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCpde() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvunceId() {
        return provinceId;
    }

    public void setProvunceId(int provinceId) {
        this.provinceId = provinceId;
    }
}

