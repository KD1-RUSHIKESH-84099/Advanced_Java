package com.sunbeam.dao;

import static com.sunbeam.utils.HibernateUtils.getFactory;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sunbeam.Entities.Product;


public class ProductDaoImpl implements ProductDao {

	@Override
	public String addProduct(Product product) {
		String mesg="Product not able to add !!!!";
		
		Session session=getFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		try {
			
			Serializable productId = session.save(product);
			
			tx.commit();
			mesg="product added Succesfully , with ID"+ productId;
		} catch (RuntimeException e) {
			
			if(tx != null)
				tx.rollback();//re throw the same exc to the caller
			throw e;
		}
		return mesg;
		
	}

	@Override
	public Product getProductById(Long productId) {
		Product product = null;
		
		Session session=getFactory().getCurrentSession();
	
		Transaction tx=session.beginTransaction();
		try {
			
			product = session.get(Product.class, productId);//select
			
			tx.commit();
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
						throw e;
		}
		return product;
	}
	
	
	

}
