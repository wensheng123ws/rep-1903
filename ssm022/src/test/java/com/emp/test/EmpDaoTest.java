package com.emp.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emp.dao.EmpDao;
import com.emp.dao.EmpLazyDao;
import com.emp.entity.Dept;
import com.emp.entity.Emp;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:applicationContext.xml"})
public class EmpDaoTest {
	@Resource
	private EmpDao empDao;
	@Test
	public void testQueryAll() {
		List<Emp> emps = empDao.queryAll();
		for(Emp e:emps){
			System.out.println(e.getEname()+","+e.getDept().getDname()
		    +","+e.getMgr().getEname());
		}
	}
	
	@Test
	public void testQueryById(){
		Emp e=empDao.queryById("e017");
		System.out.println(e);
	}
	@Test
	public void testQueryLikeName(){
		List<Emp> es=empDao.queryLikeName("��");
		for(Emp e:es){
			System.out.println(e);
		}
	}
	@Test//���Ա��
	public void testAddEmp(){
		//����һ��Ա������
		Emp e=new Emp();
		e.setEmpno("e668");
		e.setEname("�Ź���");
		e.setEsex("��");
		e.setEage(32);
		e.setEsalary(30000F);
		//����һ�����Ŷ���
		Dept dept=new Dept();
		dept.setDeptno("d001");
		e.setDept(dept);
		//����һ���������
		Emp mgr=new Emp();
		mgr.setEmpno("e001");
		e.setMgr(mgr);
		//��e���󱣴浽���ݿ���
		empDao.addEmp(e);
		System.out.println("OK");
	}
	
	@Test
	public void testDelete(){
		empDao.deleteEmp("e668");
		System.out.println("OK");
	}
	
	@Test
	public void testUpdate(){
		Emp e = empDao.queryById("e668");
		System.out.println(e);
		e.setEname("�Ź���");
		e.setEsex("��");
		e.setEsalary(35000F);
		e.getDept().setDeptno("d003");
		e.getMgr().setEmpno("e004");
		//���޸ĵ����ݱ��浽���ݿ���
		empDao.updateEmp(e);
		System.out.println("OK");
	}
	
	@Test
	public void testQueryByDeptno(){
		List<Emp> es = empDao.queryByDeptno("d001");
		for(Emp e:es){
			System.out.println(e);
		}
			
	}
	
	@Test
	public void testQueryMgr(){
		List<Emp> es = empDao.queryMgrs();
		for(Emp e:es){
			System.out.println(e);
		}
	}
	
	@Resource
	private EmpLazyDao empLazyDao;
	//����������
	@Test
	public void testLazy(){
		Emp e = empLazyDao.queryById("e002");
		/*System.out.println(e.getEname());
		System.out.println("~~~~~~~~~~~~~~~");
		System.out.println(e.getDept().getDname());*/
		System.out.println(e);
	}
	
	@Test
	public void testLazy2(){
		List<Emp> es = empLazyDao.queryAll();
		for(Emp e:es){
			if(e.getDept()!=null){
				System.out.println(e.getEname()+","+e.getDept().getDname());
			}
		}
	}
}
