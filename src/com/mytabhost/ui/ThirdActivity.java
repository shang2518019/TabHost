package com.mytabhost.ui;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mytabhost.tools.Sqlite_handle;
import com.mytabhost.tools.UIHelper;
import com.mytabhost.widget.MySideBar;
import com.mytabhost.widget.MySideBar.OnTouchingLetterChangedListener;

@SuppressLint({ "ShowToast", "ShowToast" })
public class ThirdActivity extends Activity  {
	private static int num = 0;
	private TextView header_title;
	private MyCheckBox myCheckBox;
	private ListView listView;
	private ArrayList<String> child = new ArrayList<String>();
	private Sqlite_handle sqlite_handle;
	private ArrayList<String> arrayList;
	private EditText search_products_seditText;
	private MySideBar mySideBar;
	
	ArrayList<String> temp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_third);
		header_title = (TextView) this.findViewById(R.id.titleText);
		myCheckBox = (MyCheckBox) this.findViewById(R.id.myCheckBox);
		listView = (ListView) this.findViewById(R.id.over_listView);
		mySideBar = (MySideBar) this.findViewById(R.id.mySideBar1);
		search_products_seditText = (EditText) this
				.findViewById(R.id.search_products_editText);
		mySideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {			
			@Override
			public void onTouchingLetterChanged(String s) {
				// TODO Auto-generated method stub
				Toast toast = Toast.makeText(ThirdActivity.this, s, 1000);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				if (temp == null) {
					temp = new ArrayList<String>();
				}
				temp.clear();
//				System.out.println("s====" + s.toString());
				temp = getListView(s.toString());
				if (temp.size() != 0) {
					listView.setAdapter(new ArrayAdapter<String>(
							ThirdActivity.this, R.layout.list_item,
							R.id.product_name, temp));
				}
			}
		});
		sqlite_handle = new Sqlite_handle(this);
		arrayList = new ArrayList<String>();		
		search_products_seditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (temp == null) {
					temp = new ArrayList<String>();
				}
				temp.clear();
				System.out.println("s====" + s.toString());
				temp = getListView(s.toString());
				if (temp.size() != 0) {
					listView.setAdapter(new ArrayAdapter<String>(
							ThirdActivity.this, R.layout.list_item,
							R.id.product_name, temp));
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		for (int i = 0; i < MySideBar.b.length; i++) {
			arrayList.add(MySideBar.b[i]);
		}
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
									1000).show();
							myCheckBox.setmValue("我是：" + num++);
						}
					}
				});
		listView.setAdapter(new MyAdapter(this));

	}

	/***
	 * 自定义Adapter
	 * 
	 * @author zhangjia
	 * 
	 */
	class MyAdapter extends BaseAdapter {
		private Context context;
		private ViewHolder holder;

		public MyAdapter(Context context) {
			super();
			this.context = context;
		}

		@Override
		public int getCount() {
			return MySideBar.b.length;
			// return MySideBar.b.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView textView;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = (LinearLayout) LayoutInflater.from(context)
						.inflate(R.layout.item, null);
				holder.textView = (TextView) convertView
						.findViewById(R.id.tv_item);
				holder.listView = (ListView) convertView
						.findViewById(R.id.lv_item);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			final String textValue = MySideBar.b[position];// A...B...C
			// 显示该控件
			holder.textView.setVisibility(View.VISIBLE);
			holder.listView.setVisibility(View.VISIBLE);
			// if (position == 0) {
			// holder.textView.setText("当前城市");
			// } else {
			holder.textView.setText(textValue);
			// }
			child = getListView(textValue);
			System.out.println("daxiao===========" + child.size());
			holder.listView.setAdapter(new ArrayAdapter<String>(context,
					R.layout.list_item, R.id.product_name, child));
			// 设置样式（宽度）
			setListViewHeightBasedOnChildren(holder.listView);

			if (child.size() == 0) {
				// 没有项的让该控件消失
				holder.textView.setVisibility(View.GONE);
				holder.listView.setVisibility(View.GONE);
			}

			holder.listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// parent.getItemAtPosition(position).toString();//根据item提示,这样万无一失
					Toast.makeText(ThirdActivity.this,
							parent.getItemAtPosition(position).toString(), 1000)
							.show();
				}
			});
			return convertView;
		}

		private class ViewHolder {
			private TextView textView;
			private ListView listView;
		}

	}

	/***
	 * 动态设置listview的高度
	 * 
	 * @param listView
	 */
	public void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		// params.height += 5;// if without this statement,the listview will be
		// a
		// little short
		// listView.getDividerHeight()获取子项间分隔符占用的高度
		// params.height最后得到整个ListView完整显示需要的高度
		listView.setLayoutParams(params);
	}

	public ArrayList<String> getListView(String textValue) {

		return sqlite_handle.queryCity(textValue);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}

	
	
}
