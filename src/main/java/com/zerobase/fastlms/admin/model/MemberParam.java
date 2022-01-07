package com.zerobase.fastlms.admin.model;

import lombok.Data;

@Data
public class MemberParam {

	//현재 페이지
	long pageIndex;
	//페이지에 출력되어야하는 쿼리 수
	long pageSize;
	
	String searchType;
	String searchValue;
	
	String userId;
	
	/**
	 * pageIndex: 1 -> limit 0, 10
	 * pageIndex: 2 -> limit 10, 10
	 * pageIndex: 3 -> limit 20, 10
	 * pageIndex: 4 -> limit 30, 10
	 */
	  
	//페이지에 노출되는 10개의 쿼리중 시작부분을 리턴해주는 메소드
	public long getPageStart() {
		init();
		
		
		return (pageIndex - 1) * pageSize;
	}
	
	//페이지에 출력되는 쿼리 개수(10)
	public long getPageEnd() {
		init();
		if(pageSize < 10) {
			pageSize = 10;
		}
		return pageSize;
	}
	
	public void init() {
		if(pageIndex < 1) {
			pageIndex = 1;
		}
		
		if(pageSize < 10) {
			pageSize = 10;
		}
	}
	
	//검색 시 파라미터 저장 메소드
	public String getQueryString() {
		init();
		
		StringBuilder sb = new StringBuilder();
		
		//검색 타입이 존재할 시
		if(searchType != null && searchType.length() > 0) {
			sb.append(String.format("searchType=%s", searchType));
		}
		
		//검색 값이 존재할 시
		if(searchValue != null && searchValue.length() > 0) {
			//searchType과 searchValue 사이에 & 삽입
			if(sb.length() > 0)
			{
				sb.append("&");
			}
			sb.append(String.format("searchValue=%s", searchValue));
		}
		
		return sb.toString();
	}
	
}
