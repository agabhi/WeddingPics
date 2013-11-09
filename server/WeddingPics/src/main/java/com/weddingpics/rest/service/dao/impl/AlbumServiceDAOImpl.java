package com.weddingpics.rest.service.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.weddingpics.rest.entity.Album;
import com.weddingpics.rest.service.dao.AlbumServiceDAO;

@Repository(value= "albumServiceDAO")
@Transactional
public class AlbumServiceDAOImpl implements AlbumServiceDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addAlbum(Album album) {
		Session session = sessionFactory.getCurrentSession();
		try
		{
			session.save(album);
		}
		finally
		{
			//session.close();
		}
		
	}

	@Override
	public Album findAlbumById(Long albumId) {
		Session session = sessionFactory.getCurrentSession();
		try
		{
			@SuppressWarnings("unchecked")
			List<Album> albums = session.createCriteria(Album.class)
					.add(Restrictions.eq("albumId",albumId ))
					.list();
			if (CollectionUtils.isNotEmpty(albums)) {
				Album album = albums.get(0);	
				return album;
			} else {
				return null;
			}
		}
		finally
		{
			//session.close();
		}
	}

	@Override
	public Album findAlbumByUserId(Long userId) {
		Session session = sessionFactory.getCurrentSession();
		try
		{
			@SuppressWarnings("unchecked")
			List<Album> albums = session.createCriteria(Album.class)
					.add(Restrictions.eq("user.userId",userId ))
					.list();
			if (CollectionUtils.isNotEmpty(albums)) {
				Album album = albums.get(0);	
				return album;
			} else {
				return null;
			}
		}
		finally
		{
			//session.close();
		}
	}

	@Override
	public void deleteAlbums(String[] idList) {
		Session session = sessionFactory.getCurrentSession();
		try
		{
			session.createSQLQuery("delete from Album where albumId in (:list)").setParameterList("list", idList).executeUpdate();
		}
		finally
		{
			//session.close();
		}
		
	}

	@Override
	public void UpdateAlbum(Album album) {
		Session session = sessionFactory.getCurrentSession();
		try
		{
			session.saveOrUpdate(album);
		}
		finally
		{
			//session.close();
		}
	}

	@Override
	public Album findAlbumByWeddingId(String weddingId) {
		Session session = sessionFactory.getCurrentSession();
		try
		{
			@SuppressWarnings("unchecked")
			List<Album> albums = session.createCriteria(Album.class)
					.add(Restrictions.eq("weddingId",weddingId ))
					.list();
			if (CollectionUtils.isNotEmpty(albums)) {
				Album album = albums.get(0);	
				return album;
			} else {
				return null;
			}
		}
		finally
		{
			//session.close();
		}
	}

}
