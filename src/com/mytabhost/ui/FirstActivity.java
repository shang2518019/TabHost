package com.mytabhost.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.mytabhost.tools.UIHelper;

public class FirstActivity extends Activity {
	
	/*头标签*/
	private TextView header_title;
	/*list中每项名称*/
	private String[] list_Items_title;
	/*listView*/
	private ListView listView;
	/*list中每项图标*/
	private int[] image_id = { R.drawable.classification_1,
			R.drawable.classification_2, R.drawable.classification_3 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_first);
		list_Items_title = getResources().getStringArray(R.array.firstActivity_list_title);
		
		header_title = (TextView) this.findViewById(R.id.titleText);
		UIHelper.init_header(this, header_title, R.drawable.frame_logo_news,
				R.string.news);
		listView = (ListView) this.findViewById(R.id.first_listView);
		inilistView();
	}

	
	private void inilistView() {
		// TODO Auto-generated method stub
		String[] from = { "image", "name" };
		int[] to = { R.id.item_image,R.id.item_name };
		listView.setAdapter(new SimpleAdapter(this,data(list_Items_title, image_id), R.layout.list_item_ui,from, to));
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				
				
			}			
		});
	}

	/**
	 *列表的数据
	 * @return
	 */
	private List<? extends Map<String, ?>> data(String[] list_Items_title,int[] image_id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int count = list_Items_title.length;
		for(int i=0;i<count;i++){
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("name",list_Items_title[i]);
			hashMap.put("image",image_id[i]);
			list.add(hashMap);
		}		
		return list;
	}


}
