package com.product_category;

import java.util.List;

public interface ProductCateGoryDAO {
	public void insert(ProductCateGoryVO productCateGoryVO );
	public void update(ProductCateGoryVO productCateGoryVO );
	public void delete(Integer pRODUCT_CATEGORY_NO );
	ProductCateGoryVO findByPrimaryKey(Integer pRODUCT_CATEGORY_NO);
	List<ProductCateGoryVO> getAll();
}
