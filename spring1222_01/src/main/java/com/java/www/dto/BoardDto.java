package com.java.www.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDto {
	
	private int bno;
	private String btitle;
	private String bcontent;
	private Timestamp bdate;
	private String id;
	private int bgroup;  //정렬할때 답글달기
	private int bstep;   //답글달기 순서
	private int bindent; //답글 들여쓰기
	private int bhit;
	private String bfile;

}
