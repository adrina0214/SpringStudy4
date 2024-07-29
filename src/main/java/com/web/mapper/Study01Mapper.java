package com.web.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.StatementType;

@Mapper
public interface Study01Mapper {

	@SelectKey(statementType = StatementType.PREPARED, statement = "select last_insert_id() as no", keyProperty = "no", before = false, resultType = int.class)
	@Insert("INSERT INTO images (`file`, `type`, `size`) VALUE (#{file}, #{type}, #{size})")
	public int save(Study01DTO dto);
	
	@Select("SELECT * FROM images WHERE no = #{no}")
	public Study01DTO findByNo(int no);
	
}
