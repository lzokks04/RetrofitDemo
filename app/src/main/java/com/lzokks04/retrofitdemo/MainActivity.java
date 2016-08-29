package com.lzokks04.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private ListView mListView;
    private ArrayAdapter<String> adapter;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listview);
        getCity();
    }

    private void getCity() {
        //api网址
        String baseURL = "https://api.heweather.com/";
        //对应接口的getType
        String cityType = "allchina";
        //对应apikey
        String apiKey = "8028909595314ababe7f34f3ecfac303";
        //初始化Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)//URL
                .addConverterFactory(GsonConverterFactory.create())//添加转换器,这里添加了gson转换，也可以自定义转换器
                .build();

        CityService cityService = retrofit.create(CityService.class);//调用接口
        //初始化call
        Call<CityEntity> call = cityService.getCity(cityType, apiKey);
        //回调
        call.enqueue(new Callback<CityEntity>() {
            @Override
            public void onResponse(Call<CityEntity> call, Response<CityEntity> response) {
                list = getCityList(response.body());
                adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
                mListView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<CityEntity> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }

    /**
     * 提取javabean中的城市信息
     *
     * @param bean
     * @return
     */
    private List<String> getCityList(CityEntity bean) {
        List<String> cityList = new ArrayList<String>();
        for (int i = 0; i < bean.getCity_info().size(); i++) {
            cityList.add(bean.getCity_info().get(i).getCity());
        }
        return cityList;
    }
}
