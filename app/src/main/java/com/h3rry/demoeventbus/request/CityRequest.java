package com.h3rry.demoeventbus.request;

import android.util.Log;

import com.h3rry.demoeventbus.event.MyEvent;
import com.h3rry.demoeventbus.model.City;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by herry on 15/03/2016.
 */
public class CityRequest {

    private final String url = "https://sites.google.com/site/demoh3rry/json/jakarta.json";

    public void run() {
        Request request = new Request.Builder().url(url).build();
        OkHttpClient client = new OkHttpClient();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(Response response) {
                try {
                    String str = response.body().string();
                    JSONObject jsonObject = new JSONObject(str);
                    City city = new City();
                    city.setName(jsonObject.getString("city"));
                    city.setLatitude(jsonObject.getDouble("lat"));
                    city.setLongitude(jsonObject.getDouble("long"));
                    Log.d("demo.eventbus", city.toString());
                    EventBus.getDefault().post(new MyEvent("Success", city));
                    Log.d("demo.eventbus", "send eventbus done");
                } catch (JSONException e) {
                    Log.e("demo.eventbus", e.getMessage());
                    EventBus.getDefault().post(new MyEvent("JSONException " + e.getMessage(), null));
                } catch (IOException e) {
                    EventBus.getDefault().post(new MyEvent("IOException " + e.getMessage(), null));
                    Log.e("demo.eventbus", e.getMessage());
                }
            }
        });

    }

}
