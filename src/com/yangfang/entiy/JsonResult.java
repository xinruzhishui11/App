package com.yangfang.entiy;

public class JsonResult {
	public String reason;
	public Result result;
	
	public int error_code;

	@Override
	public String toString() {
		return "JsonResult [reason=" + reason + ", result=" + result
				+ ", error_code=" + error_code + "]";
	}
	
	
	
}
