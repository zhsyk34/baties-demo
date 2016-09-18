package com.cat.crg.dao;

import com.cat.crg.dao.impl.CommonDaoImpl;
import com.cat.crg.dao.impl.UserDaoImpl;
import com.cat.crg.entity.User;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class UserDaoTest extends BaseTest {

	@Resource
	private UserDaoImpl userDao;

	@Test
	public void type() throws Exception {
		CommonDaoImpl<User, Long> commonDao = new CommonDaoImpl<User, Long>();
//		System.out.println(commonDao.hasPersistent(5));
//		System.out.println(commonDao.hasPersistent(-1L));
//		System.out.println(commonDao.hasPersistent(0));
//		System.out.println(commonDao.hasPersistent(null));
//		System.out.println(commonDao.hasPersistent(""));

		System.out.println(Integer.TYPE);
	}

	@Test
	public void save() throws Exception {
		User user = new User(0L, "zsy");
		int r = userDao.save(user);
		System.out.println(r);
	}

	@Test
	public void saves() throws Exception {
		List<User> users = new ArrayList();
		for (int i = 1; i < 5; i++) {
			users.add(new User(-1L, "abc" + i));
		}
		int r = userDao.save(users);
		System.out.println(r);
	}

	@Test
	public void delete() throws Exception {
		int r = userDao.delete(7L);
		System.out.println(r);
	}

	@Test
	public void merge() throws Exception {
		User user = new User(2L, "new");
		userDao.merge(user);
	}

	@Test
	public void find() throws Exception {
		for (int i = 1; i < 10; i++) {
			User user = userDao.find(i);
			System.out.println(user);
		}
	}

	@Test
	public void findList() throws Exception {
		userDao.findList("a", "name", "desc", -1, 2).forEach(System.out::println);
	}

	@Test
	public void count() throws Exception {
		System.out.println(userDao.count("a"));
	}

}