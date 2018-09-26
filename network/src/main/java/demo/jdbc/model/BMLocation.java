package demo.jdbc.model;

/**
 * "formatted_address": "广东省深圳市福田区香梅路",
 "business": "香蜜湖,景田,下梅林",
 */
public class BMLocation {
    private String formatted_address;
    private String business;

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }
}
