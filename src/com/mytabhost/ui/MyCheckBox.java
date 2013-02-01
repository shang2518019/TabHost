package com.mytabhost.ui;

import com.mytabhost.ui.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.CheckBox;

/**
 * 自定义一个带有value的checkbox
 * 
 * @author Administrator
 * 
 */
public class MyCheckBox extends CheckBox {

	private String mValue;


	public String getmValue() {
		return mValue;
	}

	public void setmValue(String mValue) {
		this.mValue = mValue;
	}


	public MyCheckBox(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyCheckBox(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MyCheckBox(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub		
		TypedArray typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.Mycheckbox);
		this.mValue = typedArray.getString(R.styleable.Mycheckbox_value);
//		System.out.println(mValue);
		/**
		 * .TypedArray 通常最后调用 .recycle() 方法，为了保持以后使用该属性一致性！
		 */
		typedArray.recycle();
	}

}
