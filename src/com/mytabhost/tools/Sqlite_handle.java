package com.mytabhost.tools;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Sqlite_handle {

	private DBOpenHelper dbOpenhelper;
	private Cursor cursor;
	private SQLiteDatabase sqlLiteDatabase;

	public Sqlite_handle(Context context) {
		// TODO Auto-generated constructor stub
		dbOpenhelper = new DBOpenHelper(context);
	}

	
	public ArrayList<String> queryCity(String s){
		
		ArrayList<String> list = new ArrayList<String>();
		sqlLiteDatabase = dbOpenhelper.getWritableDatabase();
		cursor = sqlLiteDatabase.query("city",new  String[]{"name"}, checkingRegex(s),
				null, null, null, null, null);
//			sqlLiteDatabase.q
		while(cursor.moveToNext()){
			list.add(cursor.getString(cursor.getColumnIndex("name")));
		}
		close_Cursor();
		close_SqliteDB();
		return list;
	}

	public ArrayList<String>  queryAllCity(){
		ArrayList<String> list = new ArrayList<String>();
		sqlLiteDatabase = dbOpenhelper.getWritableDatabase();
		cursor = sqlLiteDatabase.query("city",new  String[]{"name"}, null,
				null, null, null, null, null);
//			sqlLiteDatabase.q
		while(cursor.moveToNext()){
			String temp=cursor.getString(cursor.getColumnIndex("name"));
			System.out.println(temp);
			list.add(temp);
		}
		close_Cursor();
		close_SqliteDB();
		return list;
	}

	public void close_SqliteDB() {

		sqlLiteDatabase.close();
	}

	public void close_Cursor() {
		cursor.close();
	}
	
	/**
	 * 判断输入的是否是英文
	 * @return
	 */
	private String  checkingRegex(String s) {
		// TODO Auto-generated method stub
		Pattern pattern = Pattern.compile("[a-zA-z_]");
		Matcher matcher1 = pattern.matcher(s);
		if (matcher1.matches() ) {
			return "pinyin like '%" + s + "%'";
		} 
		return "name like '%" + s + "%'";
	}

}
