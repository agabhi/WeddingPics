package com.weddingpics.rest.service.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.weddingpics.rest.entity.Picture;
import com.weddingpics.rest.service.dao.PictureServiceDAO;

@Repository(value= "pictureServiceDAO")
@Transactional
public class PictureServiceDAOImpl implements PictureServiceDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addPicture(Picture picture) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try
		{
			session.save(picture);
		}
		finally
		{
			//session.close();
		}
	}

	@Override
	public Picture findPictureById(Long id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try
		{
			@SuppressWarnings("unchecked")
			List<Picture> pictures = session.createCriteria(Picture.class)
					.add(Restrictions.eq("pictureId",id ))
					.list();
			if (CollectionUtils.isNotEmpty(pictures)) {
				Picture picture = pictures.get(0);	
				return picture;
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
	public void deletePictures(String[] idList) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try
		{
			session.createSQLQuery("delete from Picture where pictureId in (:list)").setParameterList("list", idList).executeUpdate();
		}
		finally
		{
			//session.close();
		}
	}

	@Override
	public void UpdatePicture(Picture picture) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try
		{
			session.saveOrUpdate(picture);
		}
		finally
		{
			//session.close();
		}
	}

}
