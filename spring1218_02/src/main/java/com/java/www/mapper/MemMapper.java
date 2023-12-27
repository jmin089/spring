package com.java.www.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.java.www.dto.MemberDto;

@Mapper
public interface MemMapper {

	MemberDto loginSelect(MemberDto mdto);

}
