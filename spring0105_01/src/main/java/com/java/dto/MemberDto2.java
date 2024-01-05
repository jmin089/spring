package com.java.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDto2 {

	
	public String id;
	public String pw;
	public String name;
	public String phone;
	public String email;
	public String gender;
	public String hobby;
	public Timestamp mdate;
	
}
