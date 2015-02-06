package com.example.util;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

public class LoadImage {

	public static void getImage(final String url,final IloadImage image){
		
		final Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				image.load(url, (Bitmap) msg.obj);
				super.handleMessage(msg);
			}
		};
		new Thread(){
			public void run() {
				
				InputStream stream = Utils.getConn(url);
				Bitmap bitmap = BitmapFactory.decodeStream(stream);
				Message message = new Message();
				message.obj = bitmap;
				handler.sendMessage(message);
			};
		}.start();
	}
	public interface IloadImage{
		void load(String url,Bitmap bitmap);
	}
}
