package demo.jdbc.model;

import java.nio.ByteBuffer;

/**
 * Created by TR on 2016/11/9.
 */
public class PointMetric {
    private String modelName; // required
    private long ts; // required
    private double value; // required
    private ByteBuffer binVal; // optional

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public ByteBuffer getBinVal() {
        return binVal;
    }

    public void setBinVal(ByteBuffer binVal) {
        this.binVal = binVal;
    }
}
