package com.product_category;

import java.io.Serializable;

public class ProductCateGoryVO implements Serializable {
	private Integer pRODUCT_CATEGORY_NO;
	private String pRODUCT_CATEGORY_NAME;

	public ProductCateGoryVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductCateGoryVO(Integer pRODUCT_CATEGORY_NO, String pRODUCT_CATEGORY_NAME) {
		super();
		this.pRODUCT_CATEGORY_NO = pRODUCT_CATEGORY_NO;
		this.pRODUCT_CATEGORY_NAME = pRODUCT_CATEGORY_NAME;
	}

	public Integer getpRODUCT_CATEGORY_NO() {
		return pRODUCT_CATEGORY_NO;
	}

	public void setpRODUCT_CATEGORY_NO(Integer pRODUCT_CATEGORY_NO) {
		this.pRODUCT_CATEGORY_NO = pRODUCT_CATEGORY_NO;
	}

	public String getpRODUCT_CATEGORY_NAME() {
		return pRODUCT_CATEGORY_NAME;
	}

	public void setpRODUCT_CATEGORY_NAME(String pRODUCT_CATEGORY_NAME) {
		this.pRODUCT_CATEGORY_NAME = pRODUCT_CATEGORY_NAME;
	}

	@Override
	public String toString() {
		return "ProductCateGoryVO [pRODUCT_CATEGORY_NO=" + pRODUCT_CATEGORY_NO + ", pRODUCT_CATEGORY_NAME="
				+ pRODUCT_CATEGORY_NAME + "]";
	}

}
