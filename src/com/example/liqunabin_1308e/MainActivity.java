package com.example.liqunabin_1308e;

import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.example.adapter.GoodAdapter;
import com.example.bean.Good;
import com.example.util.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.widget.GridView;

public class MainActivity extends Activity {

	private GridView gv;
	private Context context;
	private List<Good> list;
	private GoodAdapter adapter;
	private static final String url = "http://192.168.1.33:8080/page_a/data.json";
	protected static final int CHANGE_UI = 1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        context = this;
        gv = (GridView) findViewById(R.id.gv);
        
        new Thread(){
        	public void run() {
        		
        			InputStream stream = Utils.getConn(url);
        			HttpParams params = new BasicHttpParams();
        			HttpGet get = new HttpGet(url);
        			HttpConnectionParams.setConnectionTimeout(params, 5000);
        			HttpConnectionParams.setSoTimeout(params, 5000);
        			HttpClient client = new DefaultHttpClient();
        			try {
        				HttpResponse response = client.execute(get);
        				if(response.getStatusLine().getStatusCode()==200){
        					HttpEntity entity = response.getEntity();
        					String str = EntityUtils.toString(entity, "gbk");
        					list = Utils.parser(str);
        					Message message = new Message();
        					message.obj = list;
        					message.what = CHANGE_UI;
        					handler.sendMessage(message);
        				}
        			} catch (Exception e) {
        				e.printStackTrace();
        			} 
        	};
        }.start();
        
    }
    
    @SuppressLint("HandlerLeak")
	Handler handler = new Handler(){
    	@SuppressWarnings("unchecked")
		public void handleMessage(android.os.Message msg) {
    		
    		if(msg.what==CHANGE_UI){
    			list = (List<Good>) msg.obj;
    			adapter = new GoodAdapter(list, context);
    			gv.setAdapter(adapter);
    		}
    	};
    };
    
}
