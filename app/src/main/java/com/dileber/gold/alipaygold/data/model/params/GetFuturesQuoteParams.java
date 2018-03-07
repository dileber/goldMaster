package com.dileber.gold.alipaygold.data.model.params;

import com.dileber.gold.alipaygold.data.model.base.BaseParams;

/**
 * Created by hyy on 2018/3/1.
 */
public class GetFuturesQuoteParams  extends BaseParams {
//    commodityNo=GC&type=minute
    public String commodityNo ;
    public String type ;
    public static class Builder {

        GetFuturesQuoteParams params;
        public Builder(){
            params = new GetFuturesQuoteParams();
        }

        public GetFuturesQuoteParams.Builder setData(String commodityNo,String type){
            params.commodityNo = commodityNo;
            params.type = type;
            return this;
        }

        public GetFuturesQuoteParams build() {
            return params;
        }

    }
}
