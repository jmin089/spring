package com.java.www.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter            //getter만 입력
//@Setter            //setter만 입력
@Data                //getter,setter 모두 입력
@NoArgsConstructor   //기본생성자
@AllArgsConstructor  //전체생성자
public class MemberDto {

	private String id;
	private String pw;
	private String name;
	private String phone;
	private String gender;
	private String hobby;
	private Timestamp mdate;
	
}
