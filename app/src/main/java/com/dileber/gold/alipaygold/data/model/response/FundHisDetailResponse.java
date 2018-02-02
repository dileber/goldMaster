package com.dileber.gold.alipaygold.data.model.response;

import com.dileber.gold.alipaygold.data.model.base.BaseResponse;
import com.drcosu.ndileber.mvp.data.model.SModel;

import java.util.List;

/**
 * Created by hyy on 2018/2/2.
 */
public class FundHisDetailResponse extends BaseResponse {

    /**
     * data : {"paginator":{"total":7,"page":1},"curType":"156","resultList":[{"fundCode":"000930","date":"20180201","netValuePer":"2.7244","totalNetValue":"1.1371","rate":"-0.24","weekYield":"-1.97","monthYield":"0.12","thisYearYield":"0.12","totalYield":"13.77","yield":"0","yield30Days":"0","yieldThisYear":"0","yieldTotal":"3.779","threeMonthYield":"-.0012","sixMonthYield":"-.9","yearYield":"2.46","priceValue":"1.00","assetValue":"3,127,864,079.41","showAssetValue":false},{"fundCode":"000930","date":"20180131","netValuePer":"2.7309","totalNetValue":"1.1398","rate":"0.02","weekYield":"-1.37","monthYield":"0.36","thisYearYield":"0.36","totalYield":"14.04","yield":"0","yield30Days":"0","yieldThisYear":"0","yieldTotal":"3.853","threeMonthYield":"-.0007","sixMonthYield":"-.59","yearYield":"2.7","priceValue":"1.00","assetValue":"3,160,405,927.68","showAssetValue":false},{"fundCode":"000930","date":"20180130","netValuePer":"2.7304","totalNetValue":"1.1396","rate":"-0.72","weekYield":"-1.14","monthYield":"0.34","thisYearYield":"0.34","totalYield":"14.02","yield":"0","yield30Days":"0","yieldThisYear":"0","yieldTotal":"3.851","threeMonthYield":".0028","sixMonthYield":"-.31","yearYield":"2.68","priceValue":"1.00","assetValue":"3,209,622,197.87","showAssetValue":false},{"fundCode":"000930","date":"20180129","netValuePer":"2.7502","totalNetValue":"1.1478","rate":"-0.55","weekYield":"0.01","monthYield":"1.07","thisYearYield":"1.07","totalYield":"14.84","yield":"0","yield30Days":"0","yieldThisYear":"0","yieldTotal":"4.071","threeMonthYield":".0098","sixMonthYield":".41","yearYield":"3.42","priceValue":"1.00","assetValue":"3,279,938,198.86","showAssetValue":false},{"fundCode":"000930","date":"20180126","netValuePer":"2.7654","totalNetValue":"1.1542","rate":"-0.50","weekYield":"0.68","monthYield":"2.03","thisYearYield":"1.63","totalYield":"15.48","yield":"0","yield30Days":"0","yieldThisYear":"0","yieldTotal":"4.246","threeMonthYield":".0097","sixMonthYield":"1.7","yearYield":"4","priceValue":"1.00","assetValue":"3,390,500,981.18","showAssetValue":false},{"fundCode":"000930","date":"20180125","netValuePer":"2.7792","totalNetValue":"1.1599","rate":"0.37","weekYield":"0.98","monthYield":"2.67","thisYearYield":"2.14","totalYield":"16.05","yield":"0","yield30Days":"0","yieldThisYear":"0","yieldTotal":"4.4","threeMonthYield":".017","sixMonthYield":"1.62","yearYield":"3.8","priceValue":"1.00","assetValue":"3,509,677,867.80","showAssetValue":false},{"fundCode":"000930","date":"20180124","netValuePer":"2.7689","totalNetValue":"1.1556","rate":"0.25","weekYield":"0.07","monthYield":"2.68","thisYearYield":"1.76","totalYield":"15.62","yield":"0","yield30Days":"0","yieldThisYear":"0","yieldTotal":"4.291","threeMonthYield":".0092","sixMonthYield":"1.29","yearYield":"2.74","priceValue":"1.00","assetValue":"3,539,285,111.67","showAssetValue":false},{"fundCode":"000930","date":"20180123","netValuePer":"2.7619","totalNetValue":"1.1527","rate":"0.43","weekYield":"-0.60","monthYield":"2.42","thisYearYield":"1.50","totalYield":"15.33","yield":"0","yield30Days":"0","yieldThisYear":"0","yieldTotal":"4.219","threeMonthYield":".0098","sixMonthYield":"1.24","yearYield":"2.31","priceValue":"1.00","assetValue":"3,558,902,854.70","showAssetValue":false},{"fundCode":"000930","date":"20180122","netValuePer":"2.7500","totalNetValue":"1.1478","rate":"0.12","weekYield":"-1.30","monthYield":"1.98","thisYearYield":"1.06","totalYield":"14.83","yield":"0","yield30Days":"0","yieldThisYear":"0","yieldTotal":"4.092","threeMonthYield":".0027","sixMonthYield":".8","yearYield":"2.34","priceValue":"1.00","assetValue":"3,605,509,969.91","showAssetValue":false},{"fundCode":"000930","date":"20180119","netValuePer":"2.7467","totalNetValue":"1.1464","rate":"-0.20","weekYield":"-1.01","monthYield":"1.57","thisYearYield":"0.94","totalYield":"14.70","yield":"0","yield30Days":"0","yieldThisYear":"0","yieldTotal":"4.065","threeMonthYield":".0013","sixMonthYield":"1.35","yearYield":"2.61","priceValue":"1.00","assetValue":"3,729,682,963.40","showAssetValue":false}]}
     * errMsg : 成功
     * retCode : 0
     */

    public Data data;
    public String errMsg;
    public String retCode;

    public static class Data {
        /**
         * paginator : {"total":7,"page":1}
         * curType : 156
         * resultList : [{"fundCode":"000930","date":"20180201","netValuePer":"2.7244","totalNetValue":"1.1371","rate":"-0.24","weekYield":"-1.97","monthYield":"0.12","thisYearYield":"0.12","totalYield":"13.77","yield":"0","yield30Days":"0","yieldThisYear":"0","yieldTotal":"3.779","threeMonthYield":"-.0012","sixMonthYield":"-.9","yearYield":"2.46","priceValue":"1.00","assetValue":"3,127,864,079.41","showAssetValue":false},{"fundCode":"000930","date":"20180131","netValuePer":"2.7309","totalNetValue":"1.1398","rate":"0.02","weekYield":"-1.37","monthYield":"0.36","thisYearYield":"0.36","totalYield":"14.04","yield":"0","yield30Days":"0","yieldThisYear":"0","yieldTotal":"3.853","threeMonthYield":"-.0007","sixMonthYield":"-.59","yearYield":"2.7","priceValue":"1.00","assetValue":"3,160,405,927.68","showAssetValue":false},{"fundCode":"000930","date":"20180130","netValuePer":"2.7304","totalNetValue":"1.1396","rate":"-0.72","weekYield":"-1.14","monthYield":"0.34","thisYearYield":"0.34","totalYield":"14.02","yield":"0","yield30Days":"0","yieldThisYear":"0","yieldTotal":"3.851","threeMonthYield":".0028","sixMonthYield":"-.31","yearYield":"2.68","priceValue":"1.00","assetValue":"3,209,622,197.87","showAssetValue":false},{"fundCode":"000930","date":"20180129","netValuePer":"2.7502","totalNetValue":"1.1478","rate":"-0.55","weekYield":"0.01","monthYield":"1.07","thisYearYield":"1.07","totalYield":"14.84","yield":"0","yield30Days":"0","yieldThisYear":"0","yieldTotal":"4.071","threeMonthYield":".0098","sixMonthYield":".41","yearYield":"3.42","priceValue":"1.00","assetValue":"3,279,938,198.86","showAssetValue":false},{"fundCode":"000930","date":"20180126","netValuePer":"2.7654","totalNetValue":"1.1542","rate":"-0.50","weekYield":"0.68","monthYield":"2.03","thisYearYield":"1.63","totalYield":"15.48","yield":"0","yield30Days":"0","yieldThisYear":"0","yieldTotal":"4.246","threeMonthYield":".0097","sixMonthYield":"1.7","yearYield":"4","priceValue":"1.00","assetValue":"3,390,500,981.18","showAssetValue":false},{"fundCode":"000930","date":"20180125","netValuePer":"2.7792","totalNetValue":"1.1599","rate":"0.37","weekYield":"0.98","monthYield":"2.67","thisYearYield":"2.14","totalYield":"16.05","yield":"0","yield30Days":"0","yieldThisYear":"0","yieldTotal":"4.4","threeMonthYield":".017","sixMonthYield":"1.62","yearYield":"3.8","priceValue":"1.00","assetValue":"3,509,677,867.80","showAssetValue":false},{"fundCode":"000930","date":"20180124","netValuePer":"2.7689","totalNetValue":"1.1556","rate":"0.25","weekYield":"0.07","monthYield":"2.68","thisYearYield":"1.76","totalYield":"15.62","yield":"0","yield30Days":"0","yieldThisYear":"0","yieldTotal":"4.291","threeMonthYield":".0092","sixMonthYield":"1.29","yearYield":"2.74","priceValue":"1.00","assetValue":"3,539,285,111.67","showAssetValue":false},{"fundCode":"000930","date":"20180123","netValuePer":"2.7619","totalNetValue":"1.1527","rate":"0.43","weekYield":"-0.60","monthYield":"2.42","thisYearYield":"1.50","totalYield":"15.33","yield":"0","yield30Days":"0","yieldThisYear":"0","yieldTotal":"4.219","threeMonthYield":".0098","sixMonthYield":"1.24","yearYield":"2.31","priceValue":"1.00","assetValue":"3,558,902,854.70","showAssetValue":false},{"fundCode":"000930","date":"20180122","netValuePer":"2.7500","totalNetValue":"1.1478","rate":"0.12","weekYield":"-1.30","monthYield":"1.98","thisYearYield":"1.06","totalYield":"14.83","yield":"0","yield30Days":"0","yieldThisYear":"0","yieldTotal":"4.092","threeMonthYield":".0027","sixMonthYield":".8","yearYield":"2.34","priceValue":"1.00","assetValue":"3,605,509,969.91","showAssetValue":false},{"fundCode":"000930","date":"20180119","netValuePer":"2.7467","totalNetValue":"1.1464","rate":"-0.20","weekYield":"-1.01","monthYield":"1.57","thisYearYield":"0.94","totalYield":"14.70","yield":"0","yield30Days":"0","yieldThisYear":"0","yieldTotal":"4.065","threeMonthYield":".0013","sixMonthYield":"1.35","yearYield":"2.61","priceValue":"1.00","assetValue":"3,729,682,963.40","showAssetValue":false}]
         */

        public Paginator paginator;
        public String curType;
        public List<ResultList> resultList;

        public static class Paginator {
            /**
             * total : 7
             * page : 1
             */

            public int total;
            public int page;
        }

        public static class ResultList extends SModel {
            /**
             * fundCode : 000930
             * date : 20180201
             * netValuePer : 2.7244
             * totalNetValue : 1.1371
             * rate : -0.24
             * weekYield : -1.97
             * monthYield : 0.12
             * thisYearYield : 0.12
             * totalYield : 13.77
             * yield : 0
             * yield30Days : 0
             * yieldThisYear : 0
             * yieldTotal : 3.779
             * threeMonthYield : -.0012
             * sixMonthYield : -.9
             * yearYield : 2.46
             * priceValue : 1.00
             * assetValue : 3,127,864,079.41
             * showAssetValue : false
             */

            public String fundCode;
            public String date;
            public String netValuePer;
            public String totalNetValue;
            public String rate;
            public String weekYield;
            public String monthYield;
            public String thisYearYield;
            public String totalYield;
            public String yield;
            public String yield30Days;
            public String yieldThisYear;
            public String yieldTotal;
            public String threeMonthYield;
            public String sixMonthYield;
            public String yearYield;
            public String priceValue;
            public String assetValue;
            public boolean showAssetValue;
        }
    }
}
