package com.mytabhost.ui;

import com.mytabhost.tools.UIHelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FourthActivity extends Activity {

	private TextView header_title;
	private ListView lv;
	private String[] mes = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
			"11" };
	private ArrayAdapter<String> adapter;
	public static final int EIGHT_ID = Menu.FIRST + 1;
	public static final int SIXTEEN_ID = Menu.FIRST + 2;
	public static final int TWENTY_FOUR_ID = Menu.FIRST + 3;
	public static final int TWO_ID = Menu.FIRST + 4;
	public static final int THIRTY_TWO_ID = Menu.FIRST + 5;
	public static final int FORTY_ID = Menu.FIRST + 6;
	public static final int ONE_ID = Menu.FIRST + 7;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_fourth);
		header_title = (TextView) this.findViewById(R.id.titleText);
		UIHelper.init_header(this, header_title, R.drawable.frame_logo_tweet,
				R.string.myZone);
		lv = (ListView) findViewById(R.id.listView1);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mes);
		lv.setAdapter(adapter);
		this.registerForContextMenu(lv);// 注册contentmenu
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle(R.string.titlename);
		menu.setHeaderIcon(R.drawable.contextmenu_collection);
		// 使用menu文件下的XML文件，也可以再代码中用add方法
		MenuInflater menuInflater = new MenuInflater(this);
		menuInflater.inflate(R.menu.contextmenu, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	/**
	 * 处理contextMenu的长按点击事件和listview item的onclick事件不一样
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		// 得到当前被选中的item信息
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		// 得到点击项在listview中的位置
		int listitem_id = (int) menuInfo.position;
		System.out.println("------" + listitem_id);
		// System.out.println(arrays.get(listitem_id));
		int id = item.getItemId();
		switch (id) {
		case R.id.cope:
			break;
		case R.id.delete:
			adapter.notifyDataSetChanged();
			 Toast.makeText(this,"点击了....", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
	
	
}
