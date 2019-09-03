package com.emp.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emp.dao.DeptDao;
import com.emp.dao.DeptLazyDao;
import com.emp.dao.EmpDao;
import com.emp.entity.Dept;
import com.emp.entity.Emp;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:applicationContext.xml"})
public class DeptDaoTest {
    @Resource
    private DeptDao deptDao;
    
	@Test
	public void testQueryAll() {
		List<Dept> ds = deptDao.queryAll();
		for(Dept d:ds){
			System.out.println(d.getDeptno()+","+d.getDname()+","+d.getLocation());
		}
	}

	@Test
	public void testQueryById() {
		Dept d = deptDao.queryById("d001");
		System.out.println(d);
	}

	@Test
	public void testAddDept() {
		Dept d=new Dept();
		d.setDeptno("d005");
		d.setDname("���۲�");
		d.setLocation("����");
		deptDao.addDept(d);
		System.out.println("OK");
	}

	@Test
	public void testDeleteDept() {
		deptDao.deleteDept("0");
		System.out.println("OK");
	}

	@Test
	public void testUpdateDept() {
		Dept d = deptDao.queryById("0");
		System.out.println(d);
		d.setDeptno("d005");
		d.setDname("�з���");
		d.setLocation("֣��");
		deptDao.updateDept(d);
		System.out.println("OK");
	}
	
	//������
	@Resource
	private DeptLazyDao deptLazyDao;
	@Test
	public void testQueryAll2(){
		List<Dept> depts = deptLazyDao.queryAll();
		for(Dept d:depts){
			System.out.println(d.getDname());
			System.out.println("~~~~~~~~~~~~~~~");
			//��ӡ���������е�Ա������
			List<Emp> es=d.getEmps();
			for(Emp e:es){
				System.out.println(e.getEname());
			}
			System.out.println("~~~~~~~~~~~~~~~");
		}
	}
	
	@Test
	public void testLazyQueryById(){
		Dept d = deptLazyDao.queryById("d001");
		System.out.println(d.getDname());
		System.out.println("~~~~~~~~~~~~~");
		List<Emp> emps=d.getEmps();
		System.out.println(d.getEmps());
	}

}
