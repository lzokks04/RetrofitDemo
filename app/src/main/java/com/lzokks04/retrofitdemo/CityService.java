package com.lzokks04.retrofitdemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 设置URL header参数
 * Created by Liu on 2016/8/22.
 */
public interface CityService {
    //此处注解意思为获取方式为get（通过注解的方式）
    //括号的字符串为网址后的路径
    //https://api.heweather.com/x3/citylist?search=allchina&key=8028909595314ababe7f34f3ecfac303
    //源网址的"x3/citylist?search=allchina&key=8028909595314ababe7f34f3ecfac303"在此对应
    //"?search=allchina&key=8028909595314ababe7f34f3ecfac303"
    //看看就知道了
    @GET("x3/citylist")
    /*
    search对应search=?,映射为String,getType，这里指的是搜索的城市类型，可以输入allchina
    key对应key=?,映射为String apiKey，这里指的是apikey，可从www.heweather.com中免费获取
    也可以直接什么都不填，或者在调用时直接填网址全名,接口处可以写成(假设情况)
    call<JavaBean> getXXX();
     */
    Call<CityEntity> getCity(@Query("search")String getType,@Query("key")String apiKey);
}
