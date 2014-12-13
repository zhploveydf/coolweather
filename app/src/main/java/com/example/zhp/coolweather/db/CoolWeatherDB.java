package com.example.zhp.coolweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.zhp.coolweather.model.City;
import com.example.zhp.coolweather.model.County;
import com.example.zhp.coolweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhp on 2014/12/13 0013.
 */
public class CoolWeatherDB {

	public static final String DB_NAME = "cool_weather";

	public static final int VERSION = 1;

	private static CoolWeatherDB coolWeatherDB;

	private SQLiteDatabase db;

	public CoolWeatherDB(Context context) {//私有化构造方法
		CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}

	public synchronized static CoolWeatherDB getInstance(Context context) {//获取CoolWeatherDB的实例
		if (coolWeatherDB == null) {
			coolWeatherDB = new CoolWeatherDB(context);
		}

		return coolWeatherDB;
	}

	public void saveProvince(Province province) {//将Province实例存储到数据库
		if (province != null) {
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code",province.getProvinceCode());
			db.insert("Province",null,values);
		}
	}
	
	public List<Province> loadProvince(){//从数据库中读取Province
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db.query("Province",null,null,null,null,null,null);
		
		if(cursor.moveToFirst()){
			do {
				Province province = new Province();
				province.setId(cursor.getColumnIndex("id"));
				province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				list.add(province);
			}while (cursor.moveToNext());
		}
		
		return list;
	}
	
	public void saveCity(City city){//存储City到数据库
		if(city!=null){
			ContentValues values = new ContentValues();
			values.put("city_name",city.getCityName());
			values.put("city_code",city.getCityCode());
			values.put("province_id",city.getProvinceId());
			db.insert("City",null,values);
		}
	}
	
	public List<City> loadCities(int provinceId){//从数据库中读取City
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query("City",null,"province_id = ?",new String[]{String.valueOf(provinceId)},null,null,null);
		if(cursor.moveToFirst()){
			do {
				City city = new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
				city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
				city.setProvinceId(provinceId);
				list.add(city);
			}while (cursor.moveToNext());
		}
		
		return list;
	}
	
	public void saveCounty(County county){//将County存储到数据库中
		if(county!=null){
			ContentValues values = new ContentValues();
			values.put("county_name",county.getCountyName());
			values.put("county_code",county.getCountyCode());
			values.put("city_id",county.getCityId());
			db.insert("County",null,values);
		}
	}
	
	public List<County> loadCounties(int cityId){//从数据库中读取County
		List<County> list = new ArrayList<County>();
		Cursor cursor = db.query("County",null,"city_id = ?",new String[]{String.valueOf(cityId)},null,null,null);
		
		if(cursor.moveToFirst()){
			do {
				County county = new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
				county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
				county.setCityId(cityId);
				list.add(county);
			}while (cursor.moveToNext());
		}
		
		return list;
	}
	
	
}
