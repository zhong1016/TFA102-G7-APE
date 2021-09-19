package com.product_category;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class ProductCateGoryDAOImpl implements ProductCateGoryDAO {
	public static final String INSERT_PRODUCT_CATEGORY = "INSERT INTO PRODUCT_CATEGORY(PRODUCT_CATEGORY_NO ,PRODUCT_CATEGORY_NAME) VALUES (?,?)";
	public static final String DELETE = "DELETE FROM PRODUCT_CATEGORY WHERE PRODUCT_CATEGORY_NO= ? ";
	public static final String UPDATE = "UPDATE PRODUCT_CATEGORY SET PRODUCT_CATEGORY_NO=? ,PRODUCT_CATEGORY_NAME= ?";
	public static final String GET_PRODUCT_CATEGORY = "SELECT PRODUCT_CATEGORY_NO ,PRODUCT_CATEGORY_NAME FROM PRODUCT_CATEGORY WHERE PRODUCT_CATEGORY_NO=? ";
	public static final String GET_ALL="SELECT * FROM PRODUCT_CATEGORY";
	
	
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	
	@Override
	public void insert(ProductCateGoryVO productCateGoryVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_PRODUCT_CATEGORY);

			pstmt.setInt(1, productCateGoryVo.getpRODUCT_CATEGORY_NO());
			pstmt.setString(2, productCateGoryVo.getpRODUCT_CATEGORY_NAME());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

		}

	}
	
	@Override
	public void update(ProductCateGoryVO productCateGoryVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, productCateGoryVo.getpRODUCT_CATEGORY_NO());
			pstmt.setString(2, productCateGoryVo.getpRODUCT_CATEGORY_NAME());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

		}

	}
	

	@Override
	public void delete(Integer pRODUCT_CATEGORY_NO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, pRODUCT_CATEGORY_NO);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

		}

	}
	

	@Override
	public ProductCateGoryVO findByPrimaryKey(Integer pRODUCT_CATEGORY_NO) {

		ProductCateGoryVO productCateGoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_PRODUCT_CATEGORY);

			pstmt.setInt(1, pRODUCT_CATEGORY_NO);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productCateGoryVO =new ProductCateGoryVO();
				productCateGoryVO.setpRODUCT_CATEGORY_NO(rs.getInt("pRODUCT_CATEGORY_NO"));
				productCateGoryVO.setpRODUCT_CATEGORY_NAME(rs.getString("pRODUCT_CATEGORY_NAME"));
				
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

		}

		return productCateGoryVO;
	}

	@Override
	public List<ProductCateGoryVO> getAll() {
		List<ProductCateGoryVO> list =new ArrayList<ProductCateGoryVO>();
		
		ProductCateGoryVO productCateGoryVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);

			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productCateGoryVO =new ProductCateGoryVO();
				productCateGoryVO.setpRODUCT_CATEGORY_NO(rs.getInt("pRODUCT_CATEGORY_NO"));
				productCateGoryVO.setpRODUCT_CATEGORY_NAME(rs.getString("pRODUCT_CATEGORY_NAME"));
				list.add(productCateGoryVO);
				
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

		}
		return list;
	}

}
