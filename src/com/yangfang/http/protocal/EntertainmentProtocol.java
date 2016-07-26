package com.yangfang.http.protocal;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.yangfang.entiy.JsonResult;
import com.yangfang.entiy.News;

public class EntertainmentProtocol extends BaseProtocol<ArrayList<News>>{
	
	private ArrayList<News> Newslist;

	@Override
	public String getParams() {
		return "top";
	}

	@Override
	public ArrayList<News> parseJson(String result) {
		Gson gson = new Gson();
		JsonResult jsonResult = gson.fromJson(result, JsonResult.class);
		Newslist = jsonResult.result.data;
		
		return Newslist;
	}
	
	public ArrayList<News> getNewsist(){
		return Newslist;
	}

}
