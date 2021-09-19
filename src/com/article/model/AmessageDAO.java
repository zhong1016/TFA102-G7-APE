package com.article.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AmessageDAO  implements AmessageDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/APE?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";
	
	//SQL���O
	private static final String INSERT_STMT = 
			"INSERT INTO AMESSAGE (MSG,MSG_TM , ARTICLE_NO ,USER_NO) VALUES ( ?, NOW() , ? ,?)";
	private static final String GET_ALL_STMT = 
			"SELECT  MSG_NO,MSG,MSG_TM AMESSAGE order by MSG_NO";
	private static final String GET_ONE_STMT = 
			"SELECT MSG_NO,MSG,MSG_TM  FROM AMESSAGE where MSG_NO = ?";
	private static final String DELETE = 
			"DELETE FROM AMESSAGE where MSG_NO = ?";
	private static final String UPDATE = 
			"UPDATE AMESSAGE set MSG=?, MSG_TM=NOW()  where MSG_NO = ?";
	private static final String GET_ALL_NO_STRING = 
			"SELECT  MSG_NO,MSG,MSG_TM , USER_NO from AMESSAGE  where ARTICLE_NO = ?";
	
	@Override
	public void insert(AmessageVO amessageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, amessageVO.getMSG());
			pstmt.setInt(2, amessageVO.getARTICLE_NO());
			pstmt.setInt(3, amessageVO.getUSER_NO());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	@Override
	public void update(AmessageVO amessageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, "����");
			pstmt.setInt(2, amessageVO.getMSG_NO());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	@Override
	public void delete(AmessageVO amessageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con= DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, amessageVO.getMSG_NO());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	@Override
	public AmessageVO findPrimaryKey(Integer MSG_NO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		AmessageVO avo = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, MSG_NO);
			rs = pstmt.executeQuery();
			
			while( rs.next()) {
				avo = new AmessageVO();
				avo.setMSG(rs.getString("MSG"));
				avo.setMSG_NO(rs.getInt("MSG_NO"));
				avo.setMSG_TM(rs.getString("MSG_TM"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (rs!= null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return avo;
	}
	
	
	@Override
	public List<AmessageVO> getALL() {
		Connection con = null;
		PreparedStatement pstmt = null;
		AmessageVO avo = null;
		ResultSet rs = null;
		ArrayList list = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while( rs.next()) {
				avo = new AmessageVO();
				avo.setMSG(rs.getString("MSG"));
				avo.setMSG_NO(rs.getInt("MSG_NO"));
				avo.setMSG_TM(rs.getString("MSG_TM"));
				list.add(avo);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		return list;
	}
	
	public List <AmessageVO> getAllByNo(Integer i ){
		Connection con = null;
		PreparedStatement pstmt = null;
		AmessageVO avo = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_NO_STRING);
			pstmt.setInt(1, i);
			rs = pstmt.executeQuery();
			while( rs.next()) {
				avo = new AmessageVO();
				avo.setMSG(rs.getString("MSG"));
				avo.setMSG_NO(rs.getInt("MSG_NO"));
				avo.setMSG_TM(rs.getString("MSG_TM"));
				avo.setUSER_NO(rs.getInt("USER_NO"));
//				avo.setARTICLE_NO(i);
				list.add(avo);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		return list;
	}

	
	public static void main(String[] args) {
		AmessageDAO dao = new AmessageDAO();
		List list = dao.getAllByNo(1);
		System.out.println(list.get(0));
	}


}
