package demo.jdbc.tsdb;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/5.
 */
public class TsdbResult {

    private String metric;

    private Map dps;

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public Map getDps() {
        return dps;
    }

    public void setDps(Map dps) {
        this.dps = dps;
    }
}
