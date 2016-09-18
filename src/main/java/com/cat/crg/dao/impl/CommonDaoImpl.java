package com.cat.crg.dao.impl;

import com.cat.crg.dao.CommonDao;
import com.cat.crg.dao.InitDao;
import com.cat.crg.kit.DaoKit;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommonDaoImpl<Entity, Id> extends InitDao implements CommonDao<Entity, Id> {

	private String namespace() {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String path = DaoKit.targetPath(elements, this.getClass(), 1, 0);

		if (path == null) {
			throw new RuntimeException("can't find the method called stack.");
		}
		return path;
	}

	private String parentSpace() {
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		String path = DaoKit.targetPath(elements, this.getClass(), 1, 1);

		if (path == null) {
			throw new RuntimeException("can't find the method called stack.");
		}
		return path;
	}

	@Override
	public SqlSession getSession() {
		return super.getSqlSession();
	}

	@Override
	public int save(String namespace, Entity entity) {
		return super.getSqlSession().insert(namespace, entity);
	}

	@Override
	public int save(Entity entity) {
		return this.save(namespace(), entity);
	}

	@Override
	public int saves(String namespace, Collection<Entity> entities) {
		return super.getSqlSession().insert(namespace, entities);
	}

	@Override
	public int saves(Collection<Entity> entities) {
		return this.saves(namespace(), entities);
	}

	@Override
	public int deleteById(String namespace, Id id) {
		return super.getSqlSession().delete(namespace, id);
	}

	@Override
	public int deleteById(Id id) {
		return this.deleteById(namespace(), id);
	}

	@Override
	public int deleteByEntity(String namespace, Entity entity) {
		return super.getSqlSession().delete(namespace, entity);
	}

	@Override
	public int deleteByEntity(Entity entity) {
		return this.deleteByEntity(namespace(), entity);
	}

	@Override
	public int deleteByIds(String namespace, Id[] ids) {
		return super.getSqlSession().delete(namespace, ids);
	}

	@Override
	public int deleteByIds(Id[] ids) {
		return this.deleteByIds(namespace(), ids);
	}

	@Override
	public int deleteByEntities(String namespace, Collection<Entity> entities) {
		return super.getSqlSession().delete(namespace, entities);
	}

	@Override
	public int deleteByEntities(Collection<Entity> entities) {
		return this.deleteByEntities(namespace(), entities);
	}

	@Override
	public int update(String namespace, Entity entity) {
		return super.getSqlSession().update(namespace, entity);
	}

	@Override
	public int update(Entity entity) {
		return this.update(namespace(), entity);
	}

	@Override
	public int updates(String namespace, Collection<Entity> entities) {
		return super.getSqlSession().update(namespace, entities);
	}

	@Override
	public int updates(Collection<Entity> entities) {
		return this.updates(namespace(), entities);
	}

	@Override
	public int merge(String namespace, Entity entity, String key) {
		if (key == null || key.length() == 0) {
			key = "id";//auto pk is id
		}
		try {
			//BeanInfo beanInfo = Introspector.getBeanInfo(entity.getClass(), Object.class);
			//PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
			PropertyDescriptor descriptor = new PropertyDescriptor(key, entity.getClass());
			Object id = descriptor.getReadMethod().invoke(entity);
			return DaoKit.hasPersistent(id) ? this.update(namespace, entity) : this.save(namespace, entity);
		} catch (Exception e) {
			e.printStackTrace();
		}

		throw new RuntimeException("find key error or...");
	}

	@Override
	public int merge(String namespace, Entity entity) {
		return this.merge(namespace, entity, null);
	}

	@Override
	public int merge(Entity entity) {
		return this.merge(namespace(), entity);
	}

	@Override
	public Entity findById(String namespace, Id id) {
		return super.getSqlSession().selectOne(namespace, id);
	}

	@Override
	public Entity findById(Id id) {
		return this.findById(namespace(), id);
	}

	@Override
	public List<Entity> findAll(String namespace) {
		return super.getSqlSession().selectList(namespace);
	}

	@Override
	public List<Entity> findAll() {
		return this.findAll(namespace());
	}

	@Override
	public List<Entity> findByPage(String namespace, int offset, int limit) {
		Map<String, Object> map = new HashMap<>();
		map.put("offset", offset);
		map.put("limit", limit);
		return super.getSqlSession().selectList(namespace, map);
	}

	@Override
	public List<Entity> findByPage(int offset, int limit) {
		return this.findByPage(namespace(), offset, limit);
	}

	@Override
	public int countAll(String namespace) {
		return super.getSqlSession().selectOne(namespace);
	}

	@Override
	public int countAll() {
		return this.countAll(namespace());
	}

	@Override
	public List<Entity> selectList(Map<String, Object> params) {
		return super.getSqlSession().selectList(parentSpace(), params);
	}

	@Override
	public int selectOne(Map<String, Object> params) {
		return super.getSqlSession().selectOne(parentSpace(), params);
	}

}
