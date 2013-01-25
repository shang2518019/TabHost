package com.mytabhost.ui;

import com.mytabhost.tools.UIHelper;
import com.mytabhost.ui.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {
	private TextView header_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_second);
		header_title = (TextView) this.findViewById(R.id.titleText);
		UIHelper.init_header(this, header_title,R.drawable.frame_logo_post, R.string.answers);
	}


}
