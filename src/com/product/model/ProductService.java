package com.product.model;

import java.util.List;

public class ProductService {
	private ProductDAO dao ;
	
	public ProductService() {
		dao =new ProductDAOImpl();
	}
	
	
	//===================新增商品=======================
	public ProductVO insert(Integer product_category_no,Integer product_price,String product_name,
			Integer stock,byte[] product_pic) {
	
	ProductVO productVO =new ProductVO();
	
	productVO.setProduct_category_no(product_category_no);
	productVO.setProduct_pic(product_pic);
	productVO.setProduct_name(product_name);
	productVO.setStock(stock);
	productVO.setProduct_pic(product_pic);
	dao.insert(productVO);
	
	return productVO;
	
	}
	//===================更新商品=======================
	
	public ProductVO update(Integer product_category_no,Integer product_no,
			Integer product_price,String product_name,Integer stock,byte[] product_pic) {
	
	ProductVO productVO =new ProductVO();
	
	productVO.setProduct_no(product_no);
	productVO.setProduct_category_no(product_category_no);
	productVO.setProduct_pic(product_pic);
	productVO.setProduct_name(product_name);
	productVO.setStock(stock);
	productVO.setProduct_pic(product_pic);
	dao.update(productVO);
	
	return productVO;
	
	}
	//===================刪除下架=======================
	public void delete(Integer product_no) {
		dao.delete(product_no);
	}
	
	//===================尋找商品=======================
	public ProductVO findByPRODUCT(Integer product_no ) {
		return dao.findByPRODUCT(product_no);
	}
	//===================全部商品=======================
	public List<ProductVO> getAll(){
		return dao.getAll();
	}
	
}
