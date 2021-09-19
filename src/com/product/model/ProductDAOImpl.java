package com.product.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Result;

import util.Util;

public class ProductDAOImpl implements ProductDAO {
	public static final String INSERT_PRODUCT 
	= "INSERT INTO PRODUCT(PRODUCT_CATEGORY_NO,PRODUCT_PRICE,PRODUCT_NAME,STOCK,PRODUCT_PIC,STATE,USER_NO) VALUES (?,?,?,?,?,?,?)";
	
	public static final String UPDATE_PRODUCT 
	= "UPDATE PRODUCT SET PRODUCT_CATEGORY_NO =? ,PRODUCT_PRICE=?,PRODUCT_NAME=? , STOCK=? ,PRODUCT_PIC=? WHERE PRODUCT_NO=?";
	
	public static final String UPDATE_PRODUCT_NOPIC 
	= "UPDATE PRODUCT SET PRODUCT_CATEGORY_NO =? ,PRODUCT_PRICE=?,PRODUCT_NAME=? , STOCK=?  WHERE PRODUCT_NO=?";
	
	public static final String FIND_PRODUCT 
	= "SELECT * FROM PRODUCT WHERE PRODUCT_NO=?";
	
	public static final String GET_ALL
	="SELECT * FROM PRODUCT";
	
	public static final String UPDATE_STATUS
	="UPDATE PRODUCT SET STATE ='下架' WHERE PRODUCT_NO = ? ";
	
	public static final String UPDATE_AMOUNT 
	="UPDATE PRODUCT SET STOCK = ? WHERE PRODUCT_NO = ?";

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		
		

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_PRODUCT);

			
			pstmt.setInt(1, productVO.getProduct_category_no());
			pstmt.setInt(2, productVO.getProduct_price());
			pstmt.setString(3,productVO.getProduct_name());
			pstmt.setInt(4, productVO.getStock());
			pstmt.setBytes(5,productVO.getProduct_pic());
			pstmt.setString(6,"正常");
			pstmt.setInt(7, productVO.getUser_no());
//			byte[] pic = getPicture("./backstage/img/coffee/1-1.jpg");
//			pstmt.setByte(6, productVO.getProduct_pic());
			
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
	public void update(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_PRODUCT);

			
			pstmt.setInt(1, productVO.getProduct_category_no());
			pstmt.setInt(2, productVO.getProduct_price());
			pstmt.setString(3, productVO.getProduct_name());
			pstmt.setInt(4, productVO.getStock());
			pstmt.setBytes(5,productVO.getProduct_pic());
			pstmt.setInt(6, productVO.getProduct_no());
			
			
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
//	public static void main(String[] args) {
//		ProductDAOImpl dao =new ProductDAOImpl();
//		ProductVO vo =new ProductVO();
//		PRODUCT_NO, PRODUCT_PRICE, PRODUCT_NAME, STOCK, PRODUCT_PIC, PRODUCT_CATEGORY_NO
//		vo.setProduct_no(2101);
//		vo.setProduct_category_no(1);
//		vo.setProduct_name("ji3");
//		vo.setProduct_price(123);
//		vo.setStock(10);
//		vo.setProduct_pic(new byte[1]);
//		dao.update(vo);
//	}

	@Override
	public void delete(Integer product_no) {  
		

	}

	@Override
	public ProductVO findByPRODUCT(Integer product_no) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_PRODUCT);

			pstmt.setInt(1, product_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productVO =new ProductVO();
				productVO.setProduct_no(rs.getInt("product_no"));
				productVO.setProduct_category_no(rs.getInt("product_category_no"));
				productVO.setProduct_price(rs.getInt("product_price"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setStock(rs.getInt("stock"));
//				productVO.setProduct_pic(rs.getBytes("product_pic"));
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

		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);

			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productVO =new ProductVO();
				productVO.setProduct_no(rs.getInt("product_no"));
				productVO.setProduct_category_no(rs.getInt("product_category_no"));
				productVO.setProduct_price(rs.getInt("product_price"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setStock(rs.getInt("stock"));
				productVO.setProduct_pic(rs.getBytes("product_pic"));
				productVO.setState(rs.getString("state"));
				productVO.setUser_no(rs.getInt("user_no"));
				list.add(productVO);
			}
		}catch(SQLException se) {
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

	@Override
	public void updateNoPic(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_PRODUCT_NOPIC);

			
			pstmt.setInt(1, productVO.getProduct_category_no());
			pstmt.setInt(2, productVO.getProduct_price());
			pstmt.setString(3, productVO.getProduct_name());
			pstmt.setInt(4, productVO.getStock());
			pstmt.setInt(5, productVO.getProduct_no());
			
			
			
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
	public void updateStatus(Integer product_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STATUS);

			pstmt.setInt(1, product_no);		
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
	public List<ProductVO> addcar() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);

			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productVO =new ProductVO();
				productVO.setProduct_no(rs.getInt("product_no"));
				productVO.setProduct_name(rs.getString("product_name"));
				
				list.add(productVO);
			}
		}catch(SQLException se) {
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
	
		

@Override
public void decreaseProductAmount(Integer product_no, Integer decrease_no) {
	Connection con = null;
	PreparedStatement pstmt = null;

	try {
		con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
		pstmt = con.prepareStatement(UPDATE_AMOUNT);
		
		ProductDAOImpl dao = new ProductDAOImpl();
		ProductVO productVO = dao.findByPRODUCT(product_no);
		System.out.println(product_no);
		System.out.println(productVO.getStock() - decrease_no);
		pstmt.setInt(1, productVO.getStock() - decrease_no);
		pstmt.setInt(2, product_no);

					
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

	
}
