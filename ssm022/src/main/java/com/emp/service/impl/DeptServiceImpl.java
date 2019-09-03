package com.emp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.emp.dao.DeptDao;
import com.emp.entity.Dept;
import com.emp.service.DeptService;
@Service
public class DeptServiceImpl implements DeptService{
	//×¢Èë²¿ÃÅDao
	@Resource
	private DeptDao deptDao;
	@Override
	public List<Dept> queryAllDepts() {
		List<Dept> depts = deptDao.queryAll();
		return depts;
	}

	@Override
	public Dept queryById(String deptno) {
		Dept d = deptDao.queryById(deptno);
		return d;
	}

	@Override
	public void addDept(Dept dept) {
		deptDao.addDept(dept);
		
	}

	@Override
	public void updateDept(Dept dept) {
		deptDao.updateDept(dept);
		
	}

	@Override
	public void deleteDept(String dept) {
		deptDao.deleteDept(dept);
		
	}

}
