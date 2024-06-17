package com.sunbeam.tester;

import static com.sunbeam.utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.sunbeam.Entities.Category;
import com.sunbeam.dao.ProductDao;
import com.sunbeam.dao.ProductDaoImpl;

public class ProductPurchased {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)) {
			// create dao instance
			ProductDao dao = new ProductDaoImpl();
			System.out.println("Enter product id");
			 System.out.println(dao.productPurchased(sc.nextLong() , sc.nextInt()));
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
