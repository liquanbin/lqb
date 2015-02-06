package com.example.util;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.bean.Good;

public class Utils {

	public static List<Good> parser(String str){
		
		List<Good> list = new ArrayList<Good>();
		try {
			JSONArray jsonArray = new JSONArray(str);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object = (JSONObject) jsonArray.get(i);
				Good good = new Good();
				good.setTitle(object.getString("traveltitle"));
				good.setPrice(object.getString("travelprice"));
				good.setUrl(object.getString("travelurl"));
				good.setLevel(object.getString("level"));
				list.add(good);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	} 
	
	public static InputStream getConn(String url){
		HttpParams params = new BasicHttpParams();
		HttpGet get = new HttpGet(url);
		HttpConnectionParams.setConnectionTimeout(params, 5000);
		HttpConnectionParams.setSoTimeout(params, 5000);
		HttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(get);
			if(response.getStatusLine().getStatusCode()==200){
				HttpEntity entity = response.getEntity();
				InputStream stream = entity.getContent();
				return stream;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
}
