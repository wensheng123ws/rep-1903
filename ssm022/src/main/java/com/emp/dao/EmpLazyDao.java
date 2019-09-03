package com.emp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import com.emp.entity.Emp;
//mybatis��������  (��ѯ)
//ʹ�������ط�ʽ��ѯ
//�����صľ�����,�����ǰ��̨����Ŀ���ģʽ
//�����ݷ�װ��JSON���󷵻ظ�ǰ�������,�Ͳ��ܽ���
public interface EmpLazyDao {
	@Select("select empno,ename,esex,eage,esalary from t_emp where empno=#{empno}")
	@Results(id="empMapper",value={
		@Result(id=true,column="empno",property="empno"),
		@Result(column="ename",property="ename"),
		@Result(column="esex",property="esex"),
		@Result(column="eage",property="eage"),
		@Result(column="esalary",property="esalary"),
		@Result(column="deptno",property="dept",
		   one=@One(select="com.emp.dao.DeptDao.queryById",
		   fetchType=FetchType.LAZY)),
		@Result(column="mgrno",property="mgr",
		   one=@One(select="com.emp.dao.EmpLazyDao.queryById",
		   fetchType=FetchType.LAZY))
	})
	Emp queryById(@Param("empno")String empno);
	
	//��ѯ����Ա��
	@Select("select empno,ename,esex,eage,esalary,deptno,mgrno from t_emp")
	@ResultMap("empMapper")
	List<Emp> queryAll();
	
	//��������ģ����ѯ
	@Select("select empno,ename,esex,eage,esalary,deptno,mgrno from t_emp"
			+ " where INSTR(ename,#{ename})>0")
	@ResultMap("empMapper")
	List<Emp> queryLikeName(@Param("ename")String ename);
	
	//��ѯ�����еľ���		
	@Select(" select DISTINCT m.empno,m.ename "
			+ " from t_emp m inner join t_emp e "
			+ " on m.empno = e.mgrno  "
			+ " where m.deptno is not null ")
	List<Emp> queryMgrs();
	
	//���ݲ��ű�ţ���ѯ���������е�Ա��
	@Select(" select empno,ename,esex,eage,esalary,deptno,mgrno "
		 	+ " from t_emp where deptno=#{deptno} ")
	@ResultMap("empMapper")
	List<Emp> queryEmpByDeptno(@Param("deptno")String deptno);
	
}
