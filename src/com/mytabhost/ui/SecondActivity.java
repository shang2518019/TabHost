package com.mytabhost.ui;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.mytabhost.tools.UIHelper;
import com.mytabhost.widget.PullDownView;
import com.mytabhost.widget.PullDownView.OnPullDownListener;
import com.mytabhost.widget.ScrollOverListView;

public class SecondActivity extends Activity {
	private TextView header_title;

	private PullDownView pullDownView;
	private ScrollOverListView listView;
	private MyAdapter adapter;
	private List<String> arrays;
	private LayoutInflater inflater;
	private MenuInflater menuInflater;

	@SuppressLint("HandlerLeak")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_second);
		menuInflater = new MenuInflater(this);
		header_title = (TextView) this.findViewById(R.id.titleText);
		UIHelper.init_header(this, header_title, R.drawable.frame_logo_post,
				R.string.answers);

		inflater = getLayoutInflater();
		pullDownView = (PullDownView) findViewById(R.id.pullDownView1);
		pullDownView.enableAutoFetchMore(true, 0);
		listView = pullDownView.getListView();
		adapter = new MyAdapter();
		listView.setAdapter(adapter);

		initArrays(new Handler() {
			@SuppressWarnings("unchecked")
			@Override
			public void handleMessage(Message msg) {
				arrays = (List<String>) msg.obj;
				adapter.notifyDataSetChanged();
				pullDownView.notifyDidDataLoad(false);
			}
		});

		pullDownView.setOnPullDownListener(new OnPullDownListener() {

			@Override
			public void onRefresh() {
				getNewString(new Handler() {
					@Override
					public void handleMessage(Message msg) {
						arrays.add(0, (String) msg.obj);
						adapter.notifyDataSetChanged();
						pullDownView.notifyDidRefresh(arrays.isEmpty());
					}
				});
			}

			@Override
			public void onLoadMore() {
				getNewString(new Handler() {
					@Override
					public void handleMessage(Message msg) {
						arrays.add((String) msg.obj);
						adapter.notifyDataSetChanged();
						pullDownView.notifyDidLoadMore(arrays.isEmpty());
					}
				});
			}
		});
		/**
		 * 如果listview item点击事件有好多功能（删除，复制，保存....）就可以用contextmenu完成
		 * ，先要listview注册到contextMenu,再使用要override onCreateContextMenu方法，
		 */
		this.registerForContextMenu(listView);
	}

	@SuppressLint("HandlerLeak")
	private void initArrays(final Handler handler) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					Thread.interrupted();
					e.printStackTrace();
				}

				List<String> as = new ArrayList<String>();
				as.add("first");
				as.add("second");
				as.add("third");
				as.add("four");
				as.add("five");
				as.add("first");
				as.add("second");

				handler.obtainMessage(0, as).sendToTarget();
			}
		}).start();
	}

	private void getNewString(final Handler handler) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					Thread.interrupted();
					e.printStackTrace();
				}
				handler.obtainMessage(0,
						"New Text " + System.currentTimeMillis())
						.sendToTarget();
			}
		}).start();
	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return arrays == null ? 0 : arrays.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.item_list, null);
				holder.textView = (TextView) convertView
						.findViewById(R.id.text);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.textView.setText(arrays.get(position));

			return convertView;
		}
	}

	private static class ViewHolder {
		TextView textView;
	}

	/**
	 * 改变Menu背景
	 * @return view
	 */
	public View setMenuBackground() {
		this.getLayoutInflater().setFactory(
				new android.view.LayoutInflater.Factory() {

					@Override
					public View onCreateView(String name, Context context,
							AttributeSet attrs) {
						// 指定自定义inflate的对象
						if (name.equalsIgnoreCase("com.android.internal.view.menu.IconMenuItemView")) {

							try {
								LayoutInflater inflater = getLayoutInflater();
								final View view = inflater.createView(name,
										null, attrs);
								new Handler().post(new Runnable() {
									public void run() {
										// // 设置背景图片
										view.setBackgroundColor(getResources()
												.getColor(R.color.menuBG));
										
									}
								});

								return view;
							} catch (InflateException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

						return null;
					}
				});

		return null;
	}

	/**
	 * 创建MENU菜单
	 * @param menu
	 * @return
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 使用menu文件下的XML文件，也可以再代码中用add方法
		menuInflater.inflate(R.menu.menu, menu);
		// 改变contextMenu背景
		setMenuBackground();
		return true;
	}


}
