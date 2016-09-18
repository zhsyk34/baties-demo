package com.cat.crg.dao.impl;

import com.cat.crg.dao.CommonDao;
import com.cat.crg.dao.UserDao;
import com.cat.crg.entity.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

	@Resource
	private CommonDao<User, Long> commonDao;

	@Override
	public int save(User user) {
		return commonDao.save(user);
	}

	@Override
	public int save(Collection<User> users) {
		return commonDao.saves(users);
	}

	@Override
	public int delete(long id) {
		return commonDao.deleteById(id);
	}

	@Override
	public int delete(User user) {
		return commonDao.deleteByEntity(user);
	}

	@Override
	public int delete(long[] ids) {
		//TODO:angry
		Long[] wrap = new Long[ids.length];
		for (int i = ids.length - 1; i >= 0; i--) {
			wrap[i] = ids[i];
		}
		return commonDao.deleteByIds(wrap);
	}

	@Override
	public int delete(Collection<User> users) {
		return commonDao.deleteByEntities(users);
	}

	@Override
	public int update(User user) {
		return commonDao.update(user);
	}

	@Override
	public int update(Collection<User> users) {
		return commonDao.updates(users);
	}

	@Override
	public int merge(User user) {
		return commonDao.merge(user);
	}

	@Override
	public User find(long id) {
		return commonDao.findById(id);
	}

	@Override
	public List<User> findList() {
		return commonDao.findAll();
	}

	@Override
	public List<User> findList(int offset, int limit) {
		return commonDao.findByPage(offset, limit);
	}

	@Override
	public int count() {
		return commonDao.countAll();
	}

	@Override
	public List<User> findList(String name, String sort, String order, int offset, int limit) {
		Map<String, Object> map = new HashMap<>();
		map.put("name", name);
		map.put("sort", sort);
		map.put("order", order);
		map.put("offset", offset);
		map.put("limit", limit);
		return commonDao.selectList(map);
	}

	@Override
	public int count(String name) {
		Map<String, Object> map = new HashMap<>();
		map.put("name", name);
		return commonDao.selectOne(map);
	}
}
