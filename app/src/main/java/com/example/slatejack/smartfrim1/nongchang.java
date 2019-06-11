package com.example.slatejack.smartfrim1;

import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class nongchang extends AppCompatActivity {
    private Map<String,String> map;
    private List<Map<String,String>> list;
    private TextView tvcity;
    private TextView tvweather;
    private TextView tvtemp;
    private TextView tvwind;
    private TextView tvpm;
    private ImageView tvicon;
    private  MqttManager mqttManager ;
    private BaseAdapter baseAdapter;
    private TextView chuangan1_name1;
    private TextView chuangan1_name2;
    private TextView chuangan2_name1;
    private TextView chuangan2_name2;
    private TextView chuangan3_name1;
    private TextView chuangan3_name2;
    private TextView chuangan4_name1;
    private TextView chuangan4_name2;
    private TextView chuangan5_name1;
    private TextView chuangan5_name2;
    private TextView chuangan6_name1;
    private TextView chuangan6_name2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_nongchang );
        //初始化控件
        initView();
        //解析xml文件
        InputStream inputStream =getResources().openRawResource( R.raw.weather );
        try {
            List<weatherinfo> weatherinfos=weatherservice.getInfoFromXml( inputStream );
            list=new ArrayList<Map<String,String>>();
            for (weatherinfo info:weatherinfos){
                map=new HashMap<>();
                map.put( "temp",info.getTemp() );
                map.put( "weather",info.getWeather() );
                map.put( "wind",info.getWind() );
                map.put( "pm",info.getPm() );
                map.put( "name",info.getName() );
                list.add( map );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //连接mqtt服务器
        mqttManager=new MqttManager("tcp://118.24.19.135:1883",nongchang.this);
        mqttManager.subscribe( "jcsf/gh/iotdata",0 );
        mqttManager.connect();
        Toast.makeText(getApplicationContext(),"连接成功", Toast.LENGTH_SHORT).show();
        //显示天气控件到文本框中
        getMap(1,R.drawable.sun);
        getmessage();
    }

    //天气信息
    private void getMap(int number, int iconnumber) {
        Map<String,String> citymap=list.get( number-1 );
        String weather=citymap.get( "weather" );
        String name=citymap.get( "name" );
        String pm=citymap.get( "pm" );
        String wind=citymap.get( "wind" );
        String temp=citymap.get("temp");
        tvcity.setText( name );
        tvweather.setText( weather );
        tvtemp.setText( temp );
        tvwind.setText( wind );
        tvpm.setText( pm );
        tvicon.setImageResource( iconnumber );

    }

    private void getmessage(){
        String message1;
        message1= mqttManager.apply();
        chuangan1_name2.setText( message1 );
    }

    private void initView() {
      tvcity = findViewById( R.id.city );
      tvweather=findViewById( R.id.weather );
      tvtemp=findViewById( R.id.temp );
      tvwind=findViewById( R.id.wind );
      tvpm=findViewById( R.id.pm );
      tvicon=findViewById( R.id.imageView2 );
      chuangan1_name1=findViewById( R.id.chuangan1_name1 );
      chuangan1_name2=findViewById( R.id.chuangan1_name2 );
      chuangan2_name1=findViewById( R.id.chuangan2_name1 );
      chuangan2_name2=findViewById( R.id.chuangan2_name2 );
      chuangan3_name1=findViewById( R.id.chuangan3_name1 );
      chuangan3_name2=findViewById( R.id.chuangan3_name2 );
      chuangan4_name1=findViewById( R.id.chuangan4_name1 );
      chuangan4_name2=findViewById( R.id.chuangan4_name2 );
      chuangan5_name1=findViewById( R.id.chuangan5_name1 );
      chuangan5_name2=findViewById( R.id.chuangan5_name2 );
      chuangan6_name1=findViewById( R.id.chuangan6_name1 );
      chuangan6_name2=findViewById( R.id.chuangan6_name2 );
    }
}
