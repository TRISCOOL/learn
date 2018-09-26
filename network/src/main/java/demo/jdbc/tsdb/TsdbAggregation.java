package demo.jdbc.tsdb;

/**
 * Created by Administrator on 2017/4/7.
 */
public enum TsdbAggregation {

    MIN("min"),
    MAX("max"),
    SUM("sum"),
    AVG("avg"),
    DEV("dev"),
    ZIM_SUM("zimsum"),
    MIM_MAX("mimmax"),
    MIM_MIN("mimmin");

    private String name;

    TsdbAggregation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
