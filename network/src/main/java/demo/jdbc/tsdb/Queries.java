package demo.jdbc.tsdb;

/**
 * Created by Administrator on 2017/4/5.
 */
public class Queries {

    private String aggregator;

    private String metric;

    private String downsample;

    public String getAggregator() {
        return aggregator;
    }

    public void setAggregator(String aggregator) {
        this.aggregator = aggregator;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getDownsample() {
        return downsample;
    }

    public void setDownsample(String downsample) {
        this.downsample = downsample;
    }
}
