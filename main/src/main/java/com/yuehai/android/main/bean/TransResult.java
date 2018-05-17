package com.yuehai.android.main.bean;

import java.util.List;

public class TransResult extends BaseBean {
//    {"message":"success","result":[{"dst":"你好","srcIndex":"0","to":"cn"}],"code":"000000"}
    //    {"message":"success","result":[{"dst":"Hello there","srcIndex":"0","to":"en"}],"code":"000000"}
    private List<Result> result;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public static class Result {
        private String dst;
        private String srcIndex;
        private String to;

        public String getDst() {
            return dst;
        }

        public void setDst(String dst) {
            this.dst = dst;
        }

        public String getSrcIndex() {
            return srcIndex;
        }

        public void setSrcIndex(String srcIndex) {
            this.srcIndex = srcIndex;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }
    }
}
