package com.h3rry.demoeventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.h3rry.demoeventbus.event.MyEvent;
import com.h3rry.demoeventbus.request.CityRequest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private TextView txtCity;
    private TextView txtLongitude;
    private TextView txtLatitude;
    private TextView txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCity = (TextView) findViewById(R.id.textCity);
        txtLongitude = (TextView) findViewById(R.id.textLng);
        txtLatitude = (TextView) findViewById(R.id.textLat);
        txtMessage = (TextView) findViewById(R.id.textMessage);

        findViewById(R.id.buttonGo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readStaticJSON();
            }
        });
    }

    private void readStaticJSON() {
        CityRequest request = new CityRequest();
        request.run();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MyEvent event) {
        Log.i("demo.eventbus", "Event received");
        txtMessage.setText("Status: " + event.message);
        if (event.message.equalsIgnoreCase("success")) {
            txtCity.setText("City: " + event.city.getName());
            txtLatitude.setText("Latitude: " + event.city.getLatitude());
            txtLongitude.setText("Longitude: " + event.city.getLongitude());
        } else {
            txtCity.setText("City: ");
            txtLatitude.setText("Latitude: ");
            txtLongitude.setText("Longitude: ");
        }
    }

}
