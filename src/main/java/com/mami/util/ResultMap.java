package com.mami.util;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResultMap extends HashMap<String, Object>{
	private static final long serialVersionUID = 4247940093127262806L;

	public class Fields{
		private static final String SUCCESS="success";
		private static final String MSG="msg";
		private static final String ERROR="error";
		private static final String DATA="data";
	}
	
	
	
	
	//-----------------------------返回信息----------------------------------------

	public static ResultMap success(Object o){
		ResultMap resultMap = new ResultMap();
		resultMap.put(Fields.SUCCESS,"true");
		resultMap.put(Fields.DATA, o);
		return resultMap;
	}
	public static ResultMap success(){
		ResultMap resultMap = new ResultMap();
		resultMap.put(Fields.SUCCESS,"true");
		resultMap.put(Fields.DATA, "");
		return resultMap;
	}
	public static ResultMap fail(String msg){
		ResultMap resultMap = new ResultMap();
		resultMap.put(Fields.SUCCESS,"false");
		resultMap.put(Fields.MSG, msg);
		return resultMap;
	}
	
	public static ResultMap error(Exception e){
		ResultMap resultMap = new ResultMap();
		resultMap.put(Fields.SUCCESS,"false");
		resultMap.put(Fields.MSG, e.getMessage());
		resultMap.put(Fields.ERROR, e);
		return resultMap;
	}
	
	
	/**
	 * 返回一个表格数据类型
	 * @param data 
	 * @param recordsTotal
	 * @param recordsFiltered
	 * @param draw
	 * @return
	 */
	public static ResultMap addDataTable(Object data,long recordsTotal,long recordsFiltered,int draw){
		ResultMap resultMap = new ResultMap();
		resultMap.put("data", data);
		resultMap.put("recordsTotal", recordsTotal);
		resultMap.put("recordsFiltered", recordsFiltered);
		resultMap.put("draw",draw);
		
		return resultMap;
	}
	
	public static ResultMap addMap(Map<String, Object> map){
		ResultMap resultMap = new ResultMap();
		resultMap.putAll(map);
		return resultMap;
	}
	
	public static ResultMap error(String msg,String error){
		ResultMap resultMap = new ResultMap();
		resultMap.put(Fields.SUCCESS,"false");
		resultMap.put(Fields.MSG, msg);
		resultMap.put(Fields.ERROR, error);
		return resultMap;
	}
	
	
	@Override
	public String toString(){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return "{\"error\":\"\",\"msg\":\"内部异常\",\"success\":\"false\"}";
		}
	}


	
}
