package com.dileber.gold.alipaygold.data.model.params;

import android.content.Intent;

import com.dileber.gold.alipaygold.data.model.base.BaseParams;

/**
 * Created by hyy on 2018/2/2.
 */
public class FundHisDetailParams extends BaseParams{


    public int pageNo ;
    public int pageSize ;
    public String fundCode ;
    public String startDate;
    public String endDate;
    public static class Builder {

        FundHisDetailParams params;
        public Builder(){
            params = new FundHisDetailParams();
        }

        public FundHisDetailParams.Builder setData(String fundCode,String startDate,String endDate){
            params.startDate = startDate;
            params.endDate = endDate;
            params.fundCode = fundCode ;
            return this;
        }

        public FundHisDetailParams.Builder pageInfo(int pageNo,int pageSize){
            params.pageNo = pageNo;
            params.pageSize = pageSize;
            return this;
        }

        public FundHisDetailParams build() {
            return params;
        }

    }
}
