package com.product.model;

import java.util.Scanner;

public class test {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Integer product_no = sc.nextInt();
		Integer product_category_no = sc.nextInt();
		Integer product_price = sc.nextInt();
		String product_name = sc.next();
		Integer stock = sc.nextInt();
	
		
		//利用 Bean包裝著要新增的資料
//		ProductVO productVO =new ProductVO(product_no,product_category_no,product_price,product_name,stock,pic);
		
		//對資料庫的存取，透過dao物件呼叫方法
		ProductDAOImpl dao =new ProductDAOImpl();
		
		System.out.println(dao.getAll());
//		System.out.println("新增成功");
	}
}