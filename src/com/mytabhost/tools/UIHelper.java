package com.mytabhost.tools;

import com.mytabhost.ui.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.TextView;

public class UIHelper {

		
	/**
	 * 初始化每个子界面头信息
	 * @param context Activity对象
	 * @param title_TextView  标题
	 * @param DrawableId      标题图片ID
	 * @param textview_Content_Id  标题内容
	 */
	public static void init_header(Context context, TextView title_TextView,
			int DrawableId, int textview_Content_Id) {
		title_TextView.setCompoundDrawablesWithIntrinsicBounds(context.getResources()
				.getDrawable(DrawableId), null, null, null);
		title_TextView.setText(textview_Content_Id);
	}

	/**
	 * 退出程序
	 * 
	 * @param cont
	 */
	public static void Exit(final Context cont) {
		AlertDialog.Builder builder = new AlertDialog.Builder(cont);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle(R.string.app_menu_surelogout);
		builder.setPositiveButton(R.string.sure,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// 退出
						AppManager.getAppManager().AppExit(cont);
						dialog.dismiss();					
					}
				});
		builder.setNegativeButton(R.string.cancle,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		builder.show();
	}

}
