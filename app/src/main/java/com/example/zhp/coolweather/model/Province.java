package com.example.zhp.coolweather.model;

/**
 * Created by zhp on 2014/12/12 0012.
 */
public class Province {
	
	private int id;
	
	private String provinceName;
	
	private String provinceCode;
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getProvinceName(){
		return provinceName;
	}
	
	public void setProvinceName(String provinceName){
		this.provinceName = provinceName;
	}
	
	public String getProvinceCode(){
		return provinceCode;
	}
	
	public void setProvinceCode(String provinceCode){
		this.provinceCode = provinceCode;
	}
}
