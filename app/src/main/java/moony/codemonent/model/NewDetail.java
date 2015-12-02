package moony.codemonent.model;

import java.util.List;

/**
 * @author: MOONY
 * @data: 15/11/27
 * @Description: ${todo}<New详情>
 */
public class NewDetail {

    private String reason;
    private int error_code;
    /**
     * title : 美媒:美拟宣布10亿美元对台军售计划 为四年来首次
     * content : 美媒:<em>美</em>拟<em>宣布</em>10亿美元<em>对台军售</em>计划 为四年来首次  参考消息网11月27日报道 美媒称,美国拟<em>将宣布</em>价值十亿美元的<em>对台军售</em>计划。有官员表示,军售计划预计于12月中旬正式宣布。这将是四年来美国首次<em>对台军售</em>。台湾方面对此次的军售计划表示欢迎。  美国之音电台网站11月27日...
     * img_width : 358
     * full_title : 美媒:美拟宣布10亿美元对台军售计划 为四年来首次
     * pdate : 34分钟前
     * src : 国际在线
     * img_length : 500
     * img : http://p5.qhimg.com/t016b53d08c288a3d84.jpg
     * url : http://gb.cri.cn/48519/2015/11/27/6351s5180643.htm
     * pdate_src : 2015-11-27 14:58:15
     */

    private List<ResultEntity> result;

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public int getError_code() {
        return error_code;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public static class ResultEntity {
        private String title;
        private String content;
        private String img_width;
        private String full_title;
        private String pdate;
        private String src;
        private String img_length;
        private String img;
        private String url;
        private String pdate_src;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setImg_width(String img_width) {
            this.img_width = img_width;
        }

        public void setFull_title(String full_title) {
            this.full_title = full_title;
        }

        public void setPdate(String pdate) {
            this.pdate = pdate;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public void setImg_length(String img_length) {
            this.img_length = img_length;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setPdate_src(String pdate_src) {
            this.pdate_src = pdate_src;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        public String getImg_width() {
            return img_width;
        }

        public String getFull_title() {
            return full_title;
        }

        public String getPdate() {
            return pdate;
        }

        public String getSrc() {
            return src;
        }

        public String getImg_length() {
            return img_length;
        }

        public String getImg() {
            return img;
        }

        public String getUrl() {
            return url;
        }

        public String getPdate_src() {
            return pdate_src;
        }
    }
}
