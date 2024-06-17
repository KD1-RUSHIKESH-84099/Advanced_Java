package com.sunbeam.tester;

import static com.sunbeam.utils.HibernateUtils.getFactory;


import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.Entities.Category;
import com.sunbeam.dao.ProductDao;
import com.sunbeam.dao.ProductDaoImpl;




public class ProductDiscount {

	public static void main(String[] args) {
		
			
		try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)) {
			// create dao instance
			ProductDao dao = new ProductDaoImpl();
			System.out.println("Enter cat n discount amount");
			 System.out.println(dao.getDiscount(Category.valueOf(sc.next()), sc.nextDouble()));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
