package com.lsyweather.app.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lsyweather.app.db.LsyWeatherOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class LsyWeatherDB {
    /**
     * 数据库名
     */
    public static final String DB_NAME = "lsy_weather";
    /**
     * 数据库版本
     */
    public static final int VERSION = 1;
    private static LsyWeatherDB lsyWeatherDB;
    private SQLiteDatabase db;
    /**
     * 将构造方法私有化
     */
    private LsyWeatherDB(Context context){
        LsyWeatherOpenHelper dbhelper = new LsyWeatherOpenHelper(context,DB_NAME,null,VERSION);
        db = dbhelper.getWritableDatabase();
    }
    /**
     * 获取LsyWeatherDB的实例
     */
    public synchronized static LsyWeatherDB getInstance(Context context){
        if(lsyWeatherDB == null){
            lsyWeatherDB = new LsyWeatherDB(context);
        }
        return lsyWeatherDB;
    }
    /**
     * 将Province实例存储到数据库
     */
    public void saveProvince(Province province){
        if(province != null){
            ContentValues values = new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province", null, values);
        }
    }
    /**
     * 从数据库获取全国所有的省份信息
     */
    public List<Province> loadProvinces(){
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }while (cursor.moveToNext());
        }
        if(cursor != null){
            cursor.close();
        }
        return list;
    }
    /**
     * 将City实例存储到数据库
     */
    public void saveCity(City city){
        if(city != null){
            ContentValues values = new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            values.put("province_id",city.getProvinceId());
            db.insert("City", null, values);
        }
    }
    /**
     *从数据库读取某省下所有城市信息
     */
    public List<City> loadCities (int provinceId){
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City",null,"province_id = ?",new String[] {String.valueOf(provinceId)},null,null,null);
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
        if(cursor != null){
            cursor.close();
        }
        return list;
    }
}
