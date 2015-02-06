package com.example.liqunabin_1308e;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimActivity extends Activity {

	private Animation animation;
	private Handler handler = new Handler();
	private ImageView iv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anim);
		iv = (ImageView) findViewById(R.id.iv);
		animation = AnimationUtils.loadAnimation(AnimActivity.this, R.anim.scale_anim);
		iv.setAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				
				handler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						startActivity(new Intent(AnimActivity.this, MainActivity.class));
					}
				}, 5000);
				
			}
		});
	}

}
