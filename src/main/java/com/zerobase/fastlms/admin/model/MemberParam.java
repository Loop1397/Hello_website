package com.zerobase.fastlms.admin.model;

import lombok.Data;

@Data
public class MemberParam {

	long pageIndex;
	
	String searchType;
	String searchValue;
	
}
