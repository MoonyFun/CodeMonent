package moony.codemonent.model;

import java.util.List;

/**
 * @author: MOONY
 * @data: 15/11/27
 * @Description: ${todo}<聚合news列表>
 */
public class News {

    private String reason;
    private int error_code;
    private List<String> result;

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public int getError_code() {
        return error_code;
    }

    public List<String> getResult() {
        return result;
    }
}
