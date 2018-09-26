package demo.jdbc.tsdb;

import java.util.List;

/**
 * Created by Administrator on 2017/4/5.
 */
public class TsdbConditions {

    private String start;
    private String end;
    private List<Queries> queries;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public List<Queries> getQueries() {
        return queries;
    }

    public void setQueries(List<Queries> queries) {
        this.queries = queries;
    }
}
