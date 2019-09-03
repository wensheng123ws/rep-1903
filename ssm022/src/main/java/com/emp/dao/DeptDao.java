package com.emp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.emp.entity.Dept;

public interface DeptDao {
	//查询所有
	@Select("select deptno,dname,location from t_dept")
	@Results(id="DeptMapper",value={
		@Result(id=true,column="deptno",property="deptno"),
		@Result(column="dname",property="dname"),
		@Result(column="location",property="location"),
	})
	List<Dept> queryAll();
	//依据编号查询
	@Select("select deptno,dname,location from t_dept where deptno=#{deptno}")
	@ResultMap("DeptMapper")
	Dept queryById(@Param("deptno")String deptno);
	//增
	@Insert("insert into t_dept values(deptno=#{deptno},dname=#{dname},location=#{location})")
	void addDept(Dept dept);
	//删
	@Delete("delete from t_dept where deptno=#{deptno}")
	void deleteDept(@Param("deptno")String deptno);
	//改
	@Update("update t_dept set deptno=#{deptno},dname=#{dname},location=#{location} where deptno=#{deptno}")
	void updateDept(Dept dept);
	
}
