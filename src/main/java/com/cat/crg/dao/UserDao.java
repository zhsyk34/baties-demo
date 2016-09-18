package com.cat.crg.dao;

import com.cat.crg.entity.User;

import java.util.Collection;
import java.util.List;

public interface UserDao {

	int save(User user);

	int save(Collection<User> users);

	int delete(long id);

	int delete(User user);

	int delete(long[] ids);

	int delete(Collection<User> users);

	int update(User user);

	int update(Collection<User> users);

	int merge(User user);

	User find(long id);

	List<User> findList();

	List<User> findList(int offset, int limit);

	int count();

	List<User> findList(String name, String sort, String order, int offset, int limit);

	int count(String name);
}
