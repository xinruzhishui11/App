package com.yangfang.http.protocal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.util.Log;

import com.yangfang.http.HttpHelper;
import com.yangfang.http.HttpHelper.HttpResult;
import com.yangfang.utils.IOUtils;
import com.yangfang.utils.StringUtils;
import com.yangfang.utils.UIUtils;

public abstract class BaseProtocol<T> {


	/**
	 * 获取数据
	 * 
	 * @param index
	 *            分页请求数据的起始位�?
	 */
	public T getData() {
		// 先从本地缓存中读取数�?,如果�?,就直接返�?,如果没有,才从网络加载
		String result = getCache();
		if (result == null) {
			result = getDataFromNet();
		}

		if (result != null) {
			return parseJson(result);
		}

		return null;
	}

	/**
	 * 从本地缓存中读取数据
	 */
	private String getCache() {
		// 获取系统缓存目录
		File cacheDir = UIUtils.getContext().getCacheDir();
		// 以网络链接作为文件名�?,保证特定接口对应特定数据
		File cacheFile = new File(cacheDir, getParams());

		if (cacheFile.exists()) {// 缓存文件存在
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(cacheFile));
				String validTime = reader.readLine();// 读取第一行内�?,缓存截止时间
				if (System.currentTimeMillis() < Long.parseLong(validTime)) {// 当前时间小于缓存截止时间,说明缓存还在有效期范围内

					String line = null;
					StringBuffer sb = new StringBuffer();
					while ((line = reader.readLine()) != null) {
						sb.append(line);
					}

					return sb.toString();
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				IOUtils.close(reader);
			}
		}

		return null;
	}

	/**
	 * 向本地缓存写数据
	 */
	private void setCache(String result) {
		// 获取系统缓存目录
		File cacheDir = UIUtils.getContext().getCacheDir();
		// 以网络请求参数作为文件名，保证特定接口对应特定数�?
		File cacheFile = new File(cacheDir, getKey() + getParams());

		FileWriter writer = null;
		try {
			writer = new FileWriter(cacheFile);

			// 缓存有效期限, 截止时间设定为半小时之后
			long validTime = System.currentTimeMillis() + 30 * 60 * 1000;
			writer.write(validTime + "\n");// 将缓存截止时间写入文件第�?�?
			writer.write(result);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.close(writer);
		}
	}

	/**
	 * 访问网络获取数据
	 * 
	 * @param index
	 *            分页请求数据的起始位�?
	 * @return
	 * http://v.juhe.cn/toutiao/index?type=top&key=00c6aec543d813e7b3cfd24594742950
	 * 
	 */
	private String getDataFromNet() {
		HttpResult result = HttpHelper.get(HttpHelper.URL + 
				getParams()+getKey());
		Log.i("edu", "+++++++++++"+HttpHelper.URL + 
				getParams()+getKey());
		
		if (result != null) {
			String strResult = result.getString();
			if (!StringUtils.isEmpty(strResult)) {
				// 将缓存写到本地文件中
				setCache(strResult);
				return strResult;
			}
		}
		return null;
	}

	// 获取网络接口的具体地�?,每个页面都不�?�?,必须由子类实�?
	public String getKey(){
		return "&key=00c6aec543d813e7b3cfd24594742950";
	}

	// 获取网络接口的具体参�?,每个页面都不�?�?,必须由子类实�?
	public abstract String getParams();

	/**
	 * 解析json数据 ,每个页面要求的解析对象都不一�?,必须由子类实�?
	 * 
	 * @param result
	 */
	public abstract T parseJson(String result);


}
