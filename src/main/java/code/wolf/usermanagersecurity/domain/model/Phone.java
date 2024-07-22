package code.wolf.usermanagersecurity.domain.model;

import lombok.Builder;

@Builder
public class Phone {
    private String number;
    private String cityCode;
    private String contryCode;

    public Phone() {
    }

    public Phone(String number, String cityCode, String countryCode) {
        this.number = number;
        this.cityCode = cityCode;
        this.contryCode = countryCode;
    }

    public String getNumber() {
        return number;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getContryCode() {
        return contryCode;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setContryCode(String countryCode) {
        this.contryCode = countryCode;
    }
}
