package com.sunbeam.tester;

import org.hibernate.SessionFactory;

import com.sunbeam.Entities.Category;
import com.sunbeam.Entities.Product;
import com.sunbeam.dao.ProductDao;
import com.sunbeam.dao.ProductDaoImpl;


import static com.sunbeam.utils.HibernateUtils.getFactory;


import java.util.Scanner;

public class ProductAdd {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory();
				Scanner sc=new Scanner(System.in)
				) {
		
			ProductDao dao = new ProductDaoImpl();
			System.out.println("Enter Product details - ");
			Product newProduct=new Product(Category.valueOf(sc.next().toUpperCase()), sc.next(), sc.nextDouble(), sc.nextInt());
					 
					
			System.out.println(dao.addProduct(newProduct));
		} //JVM : sc.close() , sf.close() -> DBCP will be cleaned up !
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
