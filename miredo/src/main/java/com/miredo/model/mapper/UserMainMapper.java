/**
 * 
 */
package com.miredo.model.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;
/**
 * @author 김수현
 * @since 2023.12.01
 *
 */

@Mapper
public interface UserMainMapper {
	
	//select * from Miredo_Table
	public List<Map<String, Object>> SelectAllList() throws Exception;

}
