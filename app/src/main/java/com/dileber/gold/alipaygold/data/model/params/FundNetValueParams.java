package com.dileber.gold.alipaygold.data.model.params;

import com.dileber.gold.alipaygold.data.model.base.BaseParams;
import com.drcosu.ndileber.mvp.data.model.SModel;

import java.util.HashMap;

/**
 * Created by hyy on 2018/1/18.
 */

public class FundNetValueParams extends BaseParams {

    public String netValueType ;
    public String fundCode ;
    public String startDate;
    public String endDate;
    public static class Builder {

        FundNetValueParams params;
        public Builder(){
            params = new FundNetValueParams();
        }

        public FundNetValueParams.Builder setData(String netValueType,String fundCode,String startDate,String endDate){
            params.startDate = startDate;
            params.endDate = endDate;
            params.netValueType = netValueType ;
            params.fundCode = fundCode ;
            return this;
        }

        public FundNetValueParams build() {
            return params;
        }

    }
}
