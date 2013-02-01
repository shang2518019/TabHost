package com.mytabhost.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mytabhost.tools.UIHelper;

public class ThirdActivity extends Activity {
	private static int num = 0;
	private TextView header_title;
	private MyCheckBox myCheckBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_third);
		header_title = (TextView) this.findViewById(R.id.titleText);
		myCheckBox = (MyCheckBox) this.findViewById(R.id.myCheckBox);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		UIHelper.init_header(this, header_title, R.drawable.frame_logo_active,
				R.string.action);
		myCheckBox
				.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton arg0, boolean b) {
						// TODO Auto-generated method stub
						if (b) {
							Toast.makeText(ThirdActivity.this,
									"选择了:" + myCheckBox.getmValue(),
									Toast.LENGTH_SHORT).show();
							myCheckBox.setmValue("我是：" + num++);
						}
					}
				});
	}
}
