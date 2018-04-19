package com.example.chyeseol.lotto;

import com.google.firebase.perf.metrics.AddTrace;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class NLottoConn {
    // http://www.nlotto.co.kr/common.do?method=getLottoNumber&drwNo=797
//    private static final String SERVER_BASE_URL = "http://www.nlotto.co.kr/";
//    private static String baseUrl;

    /*public static void setBaseUrl(String baseUrl) {
        NLottoConn.baseUrl = baseUrl;
    }*/
    private static final String baseUrl = "http://www.nlotto.co.kr/";

    static class NLottoResponse {
        String returnValue;
        String drwNo;
        String drwNoDate;
        String drwtNo1;
        String drwtNo2;
        String drwtNo3;
        String drwtNo4;
        String drwtNo5;
        String drwtNo6;
        String firstPrzwnerCo;
        String firstAccumamnt;
        String firstWinamnt;
        String totSellamnt;

        public String toString() {
            return "returnValue: " + returnValue +
                    "\ndrwNo: " + drwNo +
                    "\ndrwNoDate: " + drwNoDate +
                    "\ndrwtNo1: " + drwtNo1 +
                    "\ndrwtNo2: " + drwtNo2 +
                    "\ndrwtNo3: " + drwtNo3 +
                    "\ndrwtNo4: " + drwtNo4 +
                    "\ndrwtNo5: " + drwtNo5 +
                    "\ndrwtNo6: " + drwtNo6 +
                    "\nfirstPrzwnerCo: " + firstPrzwnerCo +
                    "\nfirstAccumamnt: " + firstAccumamnt +
                    "\nfirstWinamnt: " + firstWinamnt +
                    "\ntotSellamnt: " + totSellamnt;
        }
    }

    interface LottoNumberRetriever {
        //        @Headers("x-api-key: ?????")
        @GET("common.do")
        Call<NLottoResponse> getLottoNumbers(@Query("method") String method,
                                             @Query("drwNo") String no);
    }

    @AddTrace(name = "getLottoNumbers")
    public static Call<NLottoResponse> getLottoNumbers(String dwrNo) {
        LottoNumberRetriever nLottoConn = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LottoNumberRetriever.class);

        return nLottoConn.getLottoNumbers("getLottoNumber", dwrNo);
    }

}
/*
{
  "bnusNo":41,
  "firstAccumamnt":18975543377,
  "firstWinamnt":2710791911,
  "returnValue":"success",
  "totSellamnt":77038759000,
  "drwtNo3":14,
  "drwtNo2":10,
  "drwtNo1":2,
  "drwtNo6":36,
  "drwtNo5":32,
  "drwtNo4":22,
  "drwNoDate":"2018-03-17",
  "drwNo":798,
  "firstPrzwnerCo":7
}
*/