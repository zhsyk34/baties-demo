package com.cat.crg.dao;

import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface CommonDao<Entity, Id> {

	SqlSession getSession();

	int save(String namespace, Entity entity);

	int save(Entity entity);

	int saves(String namespace, Collection<Entity> entities);

	int saves(Collection<Entity> entities);

	int deleteById(String namespace, Id id);

	int deleteById(Id id);

	int deleteByEntity(String namespace, Entity entity);

	int deleteByEntity(Entity entity);

	int deleteByIds(String namespace, Id[] ids);

	int deleteByIds(Id[] ids);

	int deleteByEntities(String namespace, Collection<Entity> entities);

	int deleteByEntities(Collection<Entity> entities);

	int update(String namespace, Entity entity);

	int update(Entity entity);

	int updates(String namespace, Collection<Entity> entities);

	int updates(Collection<Entity> entities);

	int merge(String namespace, Entity entity, String key);

	int merge(String namespace, Entity entity);

	int merge(Entity entity);

	Entity findById(String namespace, Id id);

	Entity findById(Id id);

	List<Entity> findAll(String namespace);

	List<Entity> findAll();

	List<Entity> findByPage(String namespace, int offset, int limit);

	List<Entity> findByPage(int offset, int limit);

	int countAll(String namespace);

	int countAll();

	List<Entity> selectList(Map<String, Object> params);

	int selectOne(Map<String, Object> params);
}
