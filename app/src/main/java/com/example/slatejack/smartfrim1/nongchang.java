package com.example.slatejack.smartfrim1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.slatejack.smartfrim1.data.Result;
import com.example.slatejack.smartfrim1.data.Sensor;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class nongchang extends AppCompatActivity {
    private Map<String, String> map;
    private List<Map<String, String>> list;
    private TextView tvcity;
    private TextView tvweather;
    private TextView tvtemp;
    private TextView tvwind;
    private TextView tvpm;
    private ImageView tvicon;
    private MqttManager mqttManager;
    private BaseAdapter baseAdapter;
    private TextView chuangan1_name1;
    private TextView chuangan1_name2;
    private TextView chuangan1_name3;
    private TextView chuangan2_name1;
    private TextView chuangan2_name2;
    private TextView chuangan2_name3;
    private TextView chuangan3_name1;
    private TextView chuangan3_name2;
    private TextView chuangan4_name1;
    private TextView chuangan4_name2;
    private TextView chuangan5_name1;
    private TextView chuangan5_name2;
    private TextView chuangan6_name1;
    private TextView chuangan6_name2;
    private TextView chuangan1;
    private TextView chuangan2;
    private TextView chuangan3;
    private TextView chuangan4;
    private TextView chuangan5;
    private TextView chuangan6;
    public static final String TAG = "MQTT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_nongchang );
        //初始化控件
        initView();
        //解析xml文件
        InputStream inputStream = getResources().openRawResource( R.raw.weather );
        try {
            List<weatherinfo> weatherinfos = weatherservice.getInfoFromXml( inputStream );
            list = new ArrayList<Map<String, String>>();
            for (weatherinfo info : weatherinfos) {
                map = new HashMap<>();
                map.put( "temp", info.getTemp() );
                map.put( "weather", info.getWeather() );
                map.put( "wind", info.getWind() );
                map.put( "pm", info.getPm() );
                map.put( "name", info.getName() );
                list.add( map );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //显示天气控件到文本框中
        getMap( 1, R.drawable.sun );
    }

    //天气信息
    private void getMap(int number, int iconnumber) {
        Map<String, String> citymap = list.get( number - 1 );
        String weather = citymap.get( "weather" );
        String name = citymap.get( "name" );
        String pm = citymap.get( "pm" );
        String wind = citymap.get( "wind" );
        String temp = citymap.get( "temp" );
        tvcity.setText( name );
        tvweather.setText( weather );
        tvwind.setText( wind );
        tvpm.setText( pm );
        tvicon.setImageResource( iconnumber );

    }


    private void updateView(String data) {
        Log.d( TAG, "主线程收到消息" + data );
        //chuangan1_name1.setText( data );
        Gson gson = new Gson();
        Result res = gson.fromJson( data, Result.class );
        if (res.getObj().equals( "sens" )) {
            for (Sensor sensor : res.getPayload()) {
                switch (sensor.getType()) {
                    case Sensor.SENSOR_LIGHT:
                        chuangan1.setText( "光照温湿度变送器" );
                        chuangan1_name1.setText("温度："+sensor.getData().get( 0 ).toString());
                        chuangan1_name2.setText("空气湿度："+sensor.getData().get( 1 ).toString());
                        chuangan1_name3.setText("光照："+sensor.getData().get( 2 ).toString());
                        break;
                    case Sensor.SENSOR_CO2:
                        chuangan2.setText( "co2温湿度变送器" );
                        chuangan2_name1.setText("温度："+sensor.getData().get( 0 ).toString());
                        chuangan2_name2.setText("空气湿度："+sensor.getData().get( 1 ).toString());
                        chuangan2_name3.setText("CO2浓度："+sensor.getData().get( 2 ).toString());
                        break;
                    case Sensor.SENSOR_WATER1:
                        chuangan3.setText( "土壤水分传感器1" );
                        chuangan3_name1.setText( "温度："+sensor.getData().get( 0 ).toString() );
                        chuangan3_name2.setText( "土壤湿度："+sensor.getData().get( 1 ).toString() );
                    case Sensor.SENSOR_WATER2:
                        chuangan4.setText( "土壤水分传感器2" );
                        chuangan4_name1.setText( "温度："+sensor.getData().get( 0 ).toString() );
                        chuangan4_name2.setText( "土壤湿度："+sensor.getData().get( 1 ).toString() );
                    case Sensor.SENSOR_WATER3:
                        chuangan5.setText( "土壤水分传感器3" );
                        chuangan5_name1.setText( "温度："+sensor.getData().get( 0 ).toString() );
                        chuangan5_name2.setText( "土壤湿度："+sensor.getData().get( 1 ).toString() );
                    case Sensor.SENSOR_SOIL:
                        chuangan6.setText( "土壤检测传感器" );
                        chuangan6_name1.setText( "电导度："+sensor.getData().get( 0 ).toString() );
                        chuangan6_name2.setText( "盐分："+sensor.getData().get( 1 ).toString() );
                    default:
                        break;
                }
            }
        }

    }

    private void initView() {
        tvcity = findViewById( R.id.city );
        tvweather = findViewById( R.id.weather );
        tvtemp = findViewById( R.id.temp );
        tvwind = findViewById( R.id.wind );
        tvpm = findViewById( R.id.pm );
        tvicon = findViewById( R.id.imageView2 );
        chuangan1_name1 = findViewById( R.id.chuangan1_name1 );
        chuangan1_name2 = findViewById( R.id.chuangan1_name2 );
        chuangan1_name3 = findViewById( R.id.chuangan1_name3 );
        chuangan2_name1 = findViewById( R.id.chuangan2_name1 );
        chuangan2_name2 = findViewById( R.id.chuangan2_name2 );
        chuangan2_name3 = findViewById( R.id.chuangan2_name3 );
        chuangan3_name1 = findViewById( R.id.chuangan3_name1 );
        chuangan3_name2 = findViewById( R.id.chuangan3_name2 );
        chuangan4_name1 = findViewById( R.id.chuangan4_name1 );
        chuangan4_name2 = findViewById( R.id.chuangan4_name2 );
        chuangan5_name1 = findViewById( R.id.chuangan5_name1 );
        chuangan5_name2 = findViewById( R.id.chuangan5_name2 );
        chuangan6_name1 = findViewById( R.id.chuangan6_name1 );
        chuangan6_name2 = findViewById( R.id.chuangan6_name2 );
        chuangan1=findViewById( R.id.chuangan1 );
        chuangan2=findViewById( R.id.chuangan2 );
        chuangan3=findViewById( R.id.chuangan3 );
        chuangan4=findViewById( R.id.chuangan4 );
        chuangan5=findViewById( R.id.chuangan5 );
        chuangan6=findViewById( R.id.chuangan6 );
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                updateView( msg.obj.toString() );
            }
        };
        //连接mqtt服务器
        mqttManager = new MqttManager( "tcp://118.24.19.135:1883", getApplicationContext(), handler );
        mqttManager.connect();
    }
}
