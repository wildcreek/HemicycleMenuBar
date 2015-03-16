package com.enzo.menubar;

import android.os.Build;
import android.os.Bundle;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnClickListener {

	private RelativeLayout rl_level1;
	private RelativeLayout rl_level2;
	private RelativeLayout rl_level3;
	private ImageButton ib_menu;
	private ImageButton ib_home;
	private boolean isLevel1Visible  =true;
	private boolean isLevel2Visible  =true;
	private boolean isLevel3Visible  =true;
	private float pivotX;
	private float pivotY;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		rl_level1 = (RelativeLayout) findViewById(R.id.rl_level1);
		rl_level2 = (RelativeLayout) findViewById(R.id.rl_level2);
		rl_level3 = (RelativeLayout) findViewById(R.id.rl_level3);
		ib_home = (ImageButton) findViewById(R.id.ib_home);
		ib_menu = (ImageButton) findViewById(R.id.ib_menu);
		ib_home.setOnClickListener(this);
		ib_menu.setOnClickListener(this);
	}

 

	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_home://第一层 
			if(isLevel3Visible){//先隐藏第三层，再隐藏第二层
				ObjectAnimator animator = hideRotationAnimation(rl_level3);
				animator.start();
				isLevel3Visible = false;
				animator.addListener(new AnimatorListener() {
					
					@Override
					public void onAnimationStart(Animator animation) {
						
					}
					
					@Override
					public void onAnimationRepeat(Animator animation) {
						
					}
					
					@Override
					public void onAnimationEnd(Animator animation) {
						ObjectAnimator animator = hideRotationAnimation(rl_level2);
						animator.start();
						isLevel2Visible = false;
					}
					
					@Override
					public void onAnimationCancel(Animator animation) {
						
					}
				});
			}else if(isLevel2Visible){//隐藏第二层
				ObjectAnimator animator = hideRotationAnimation(rl_level2);
				animator.start();
				isLevel2Visible = false;
			}else{//显示第二层
				ObjectAnimator animator = showRotationAnimation(rl_level2);
				animator.start();
				isLevel2Visible =true;
			}
			
			break;
		case R.id.ib_menu:
			if(isLevel3Visible){//隐藏第三层
				ObjectAnimator animator = hideRotationAnimation(rl_level3);
				animator.start();
				isLevel3Visible = false;
			}else if(isLevel2Visible){//显示第三层
				ObjectAnimator animator = showRotationAnimation(rl_level3);
				animator.start();
				isLevel3Visible =true;
			}
			break;

		default:
			break;
		}
	}
	@SuppressLint("NewApi")
	public ObjectAnimator hideRotationAnimation(View target){
 
		target.setPivotX(target.getWidth()/2);
		target.setPivotY(target.getHeight());
		ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", 0,-180);
		animator.setDuration(500);
		return animator;
	}
	@SuppressLint("NewApi")
	public ObjectAnimator showRotationAnimation(View target){
		target.setPivotX(target.getWidth()/2);
		target.setPivotY(target.getHeight());
		ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", -180,0);
		animator.setDuration(500);
		return animator;
	}
	@SuppressLint("NewApi")
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_MENU){//菜单键
			if(isLevel3Visible){//隐藏第三层 第二层 第一层
				ObjectAnimator animator = hideRotationAnimation(rl_level3);
				animator.start();
				isLevel3Visible = false;
				animator.addListener(new AnimatorListener() {
					
					@Override
					public void onAnimationStart(Animator animation) {
						
					}
					
					@Override
					public void onAnimationRepeat(Animator animation) {
						
					}
					
					@TargetApi(Build.VERSION_CODES.HONEYCOMB)
					@SuppressLint("NewApi")
					@Override
					public void onAnimationEnd(Animator animation) {
						ObjectAnimator animator = hideRotationAnimation(rl_level2);
						animator.start();
						isLevel2Visible = false;
						animator.addListener(new AnimatorListener() {
							
							@Override
							public void onAnimationStart(Animator animation) {
								
							}
							
							@Override
							public void onAnimationRepeat(Animator animation) {
							}
							
							@SuppressLint("NewApi")
							@Override
							public void onAnimationEnd(Animator animation) {
								ObjectAnimator animator = hideRotationAnimation(rl_level1);
								animator.start();
								isLevel1Visible = false;
							}
							
							@Override
							public void onAnimationCancel(Animator animation) {
								
							}
						});
					}
					
					@Override
					public void onAnimationCancel(Animator animation) {
						
					}
				});
			}else if(isLevel2Visible){//隐藏第二层 第一层
				ObjectAnimator animator = hideRotationAnimation(rl_level2);
				animator.start();
				isLevel2Visible = false;
				animator.addListener(new AnimatorListener() {
					
					@Override
					public void onAnimationStart(Animator animation) {
						
					}
					
					@Override
					public void onAnimationRepeat(Animator animation) {
						
					}
					
					@Override
					public void onAnimationEnd(Animator animation) {
						ObjectAnimator animator = hideRotationAnimation(rl_level1);
						animator.start();
						isLevel1Visible = false;
					}
					
					@Override
					public void onAnimationCancel(Animator animation) {
						
					}
				});
			}else if(isLevel1Visible){//隐藏第一层
				ObjectAnimator animator = hideRotationAnimation(rl_level1);
				animator.start();
				isLevel1Visible = false;
			}else{//显示第一层
				ObjectAnimator animator = showRotationAnimation(rl_level1);
				animator.start();
				isLevel1Visible = true;
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
