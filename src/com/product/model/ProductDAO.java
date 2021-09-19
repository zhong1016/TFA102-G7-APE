package com.product.model;

import java.util.List;

public interface ProductDAO {
	public void insert(ProductVO productVO);
	public void update(ProductVO productVO);
	public void updateNoPic(ProductVO productVO);
	public void delete(Integer product_no);
	public void updateStatus(Integer product_no);
	ProductVO findByPRODUCT(Integer product_no );
	List<ProductVO> getAll();
	public void decreaseProductAmount(Integer product_no , Integer decrease_no);
}
