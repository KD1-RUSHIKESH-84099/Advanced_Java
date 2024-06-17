package com.sunbeam.dao;

import static com.sunbeam.utils.HibernateUtils.getFactory;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sunbeam.Entities.Category;
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

	@Override
	public List<Product> getProductsByCategoryAndRange(Category category, double min, double max) {
		String jpql = "select p from Product p where p.Price between :start and :end and p.category = :catg";
		List<Product> products = null;
		
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			products = session.createQuery(jpql , Product.class).setParameter("start", min).setParameter("end", max).setParameter("catg", category).getResultList();
			tx.commit();
			
		}catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return products;
	}
	
	@Override
    public String getDiscount(Category category, double discount) {


		String mesg = "discount failed !!!";
		String jpql = "update Product p set p.Price = p.Price-:disc where p.category = :catg";
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			int rows = session.createQuery(jpql).setParameter("catg", category).setParameter("disc", discount).executeUpdate();
			tx.commit();
			mesg = "Applied discount to products - " + rows;
		}
		catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exc to the caller
			throw e;
		}

		return mesg;
		
		
		
	}

	@Override
	public String productPurchased(Long productId, int quantity) {
		String msg = "product not found";
		String jpql = "select p from Product p where p.id = :pId";
		Product product = null;
		Session session = getFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			product = session.get(Product.class, productId);
			if(product.getAvailableQuantity() >= quantity) {
				int newStock = product.getAvailableQuantity() - quantity;
				product.setAvailableQuantity(newStock);
				msg = "wow you just placed your order";
				tx.commit();
			}else {
				System.out.println("out of stock");
				
				
			}
			
		}
		catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exc to the caller
			throw e;
		}
		
		
		return msg;
	}
	@Override
	public String deleteProduct(String pName) {
		String mesg="No product deleted";
		String jpql ="delete from Product p where p.productName=:name";
		
		Session session=getFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		try {
			
			session.createQuery(jpql).setParameter("name", pName).executeUpdate();
			
			tx.commit();
			mesg= "Product deleted successfully";
			
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}
		
		return mesg;
	}

	
	

}
