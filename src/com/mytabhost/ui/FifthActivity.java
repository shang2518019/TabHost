package com.mytabhost.ui;

import com.mytabhost.tools.UIHelper;
import com.mytabhost.ui.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class FifthActivity extends Activity {

	private TextView header_title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_fifth);
		header_title = (TextView) this.findViewById(R.id.titleText);
		UIHelper.init_header(this, header_title,R.drawable.frame_logo_tweet, R.string.more);
	}	
}
