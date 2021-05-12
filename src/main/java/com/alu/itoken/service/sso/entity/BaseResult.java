package com.alu.itoken.service.sso.entity;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class BaseResult implements Serializable {
   public static final String RESULT_OK="ok";
   public static final String RESULT_NOT_OK="not ok";
   public static final String SUCCESS="操作成功";
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 7858622171238117764L;
	private String result;
	private Object data;
	private String success;
	private Cursor cursor;
	private List<Error> error;
	
	/**
	 * 
	 * @param result
	 * @param data
	 * @param success
	 * @param cursor
	 * @param error
	 * @return
	 */
	private static BaseResult createBaseResult( String result,
	 Object data,String success,
	 Cursor cursor,
	 List<Error> error) {
		BaseResult baseResult=new BaseResult();
		baseResult.setData(data);
		baseResult.setSuccess(success);
		baseResult.setCursor(cursor);
		baseResult.setError(error);
		
		return baseResult;
		
	}
	
	//都是实例化添加部分信息
	public static BaseResult  ok() {
		return createBaseResult(RESULT_OK, null, SUCCESS, null, null);
	}
	
	public static BaseResult  ok(Object object) {
		return createBaseResult(RESULT_OK, object, SUCCESS, null, null);
	}
	public static BaseResult  ok(List list,Cursor cursor) {
		return createBaseResult(RESULT_OK, list, SUCCESS, cursor, null);
	}
	
	public static BaseResult notOk(List<BaseResult.Error> errors) {
		return createBaseResult(RESULT_NOT_OK, null, "", null, errors); 
	}
	@Data
	public static class Cursor{
		private int total;
		private int offset;
		private int limit;
		public Cursor() {
		}
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}
		public int getOffset() {
			return offset;
		}
		public void setOffset(int offset) {
			this.offset = offset;
		}
		public int getLimit() {
			return limit;
		}
		public void setLimit(int limit) {
			this.limit = limit;
		}
		
	}
	
	@Data
	public static class Error{
		private String field;
		private String message;
		
		public Error(String field, String message) {
			super();
			this.field = field;
			this.message = message;
		}
		public String getField() {
			return field;
		}
		public void setField(String field) {
			this.field = field;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public Cursor getCursor() {
		return cursor;
	}

	public void setCursor(Cursor cursor) {
		this.cursor = cursor;
	}

	public List<Error> getError() {
		return error;
	}

	public void setError(List<Error> error) {
		this.error = error;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
