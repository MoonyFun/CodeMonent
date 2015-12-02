package moony.codemonent.model;

import java.util.List;

/**
 * @author: MOONY
 * @data: 15/12/1
 * @Description: ${todo}<Joke Model>
 */
public class Joke {

    private int error_code;
    private String reason;
    private ResultEntity result;

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public String getReason() {
        return reason;
    }

    public ResultEntity getResult() {
        return result;
    }

    public static class ResultEntity {

        private List<DataEntity> data;

        public void setData(List<DataEntity> data) {
            this.data = data;
        }

        public List<DataEntity> getData() {
            return data;
        }

        public static class DataEntity {
            private String content;
            private String hashId;
            private int unixtime;
            private String updatetime;

            public void setContent(String content) {
                this.content = content;
            }

            public void setHashId(String hashId) {
                this.hashId = hashId;
            }

            public void setUnixtime(int unixtime) {
                this.unixtime = unixtime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }

            public String getContent() {
                return content;
            }

            public String getHashId() {
                return hashId;
            }

            public int getUnixtime() {
                return unixtime;
            }

            public String getUpdatetime() {
                return updatetime;
            }
        }
    }
}
