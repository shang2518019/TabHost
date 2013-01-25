package com.mytabhost.ui;

import com.mytabhost.tools.AppManager;
import com.mytabhost.tools.LogUtil;
import com.mytabhost.tools.UIHelper;
import com.mytabhost.ui.R;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends TabActivity implements OnTouchListener,
		OnGestureListener {

	private static final int FLING_MIN_DISTANCE = 20;
	private static final int FLING_MIN_VELOCITY = 0;
	private TabHost tabHost;
	private String[] titles;
	private GestureDetector mGestureDetector;
	private static final String LOGTAG = LogUtil.makeLogTag(MainActivity.class);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		AppManager.getAppManager().addActivity(getCurrentActivity());
		mGestureDetector = new GestureDetector(this);
		LinearLayout ll = (LinearLayout) findViewById(R.id.linew);
		ll.setOnTouchListener(this);
		ll.setLongClickable(true);
		titles = getResources().getStringArray(R.array.title);
		initTab();
	}

	private void initTab() {
		// TODO Auto-generated method stub
		tabHost = getTabHost();
		LinearLayout view = createView(0, R.drawable.main_navigation_news);
		tabHost.addTab(tabHost.newTabSpec("first").setIndicator(view)
				.setContent(new Intent(this, FirstActivity.class)));
		LinearLayout view1 = createView(1, R.drawable.main_navigation_answers);
		tabHost.addTab(tabHost.newTabSpec("second").setIndicator(view1)
				.setContent(new Intent(this, SecondActivity.class)));
		LinearLayout view2 = createView(2, R.drawable.main_navigation_action);
		tabHost.addTab(tabHost.newTabSpec("third").setIndicator(view2)
				.setContent(new Intent(this, ThirdActivity.class)));
		LinearLayout view3 = createView(3, R.drawable.main_navigation_myzone);
		tabHost.addTab(tabHost.newTabSpec("fourth").setIndicator(view3)
				.setContent(new Intent(this, FourthActivity.class)));
		LinearLayout view4 = createView(4, R.drawable.main_navigation_more);
		tabHost.addTab(tabHost.newTabSpec("fifth").setIndicator(view4)
				.setContent(new Intent(this, FifthActivity.class)));
		tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			public void onTabChanged(String arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private LinearLayout createView(int id, int draeble_id) {
		LinearLayout view = (LinearLayout) getLayoutInflater().inflate(
				R.layout.tab_wighet, null);
		ImageView tab_imageView = (ImageView) view
				.findViewById(R.id.tab_imageView);
		tab_imageView.setImageDrawable(getResources().getDrawable(draeble_id));
		TextView tab_TextView = (TextView) view.findViewById(R.id.tab_textView);
		tab_TextView.setText(titles[id]);
		return view;
	}

	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		int total = tabHost.getTabWidget().getChildCount();
		int current = tabHost.getCurrentTab();
		int index = 0;
		// TODO Auto-generated method stub
		if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
			// Fling left
			Toast.makeText(this, "向左手势", Toast.LENGTH_SHORT).show();

			index = current + 1 > total ? total - 1 : current + 1;
		} else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {
			// Fling right
			index = current - 1 < 0 ? 0 : current - 1;
			Toast.makeText(this, "向右手势", Toast.LENGTH_SHORT).show();
		}
		tabHost.setCurrentTab(index);
		return false;
	}

	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onTouch(View arg0, MotionEvent event) {
		// TODO Auto-generated method stub
		return mGestureDetector.onTouchEvent(event);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		int keyCode = event.getKeyCode();
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			UIHelper.Exit(MainActivity.this);
			Log.d(LOGTAG, "退出程序");
		}
		return super.dispatchKeyEvent(event);
	}

	@Override 
	public boolean dispatchTouchEvent(MotionEvent event) { 

	if(mGestureDetector.onTouchEvent(event)){ 
	event.setAction(MotionEvent.ACTION_CANCEL); 
	} 

	return super.dispatchTouchEvent(event); 

	} 
	
}
