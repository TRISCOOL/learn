package demo.jdbc.model;

public class BMResponse {
    private String status;
    private BMLocation result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BMLocation getResult() {
        return result;
    }

    public void setResult(BMLocation result) {
        this.result = result;
    }
}
