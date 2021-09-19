package com.cop.model;

import java.security.SecureRandom;
import java.sql.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CopDAO implements CopDAO_interface {

	private static DataSource ds;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/APE");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/APE?serverTimezone=Asia/Taipei";
//	String userid = "David";
//	String passwd = "123456";
	
	private static final String INSERT_COP = 
			"INSERT INTO COMPANY (COMPANY_ID, COMPANY_BRC, COMPANY_PIC, COMPANY_PASSWORD, COMPANY_NAME, COMPANY_PHONE, COMPANY_ADDRESS_City, COMPANY_ADDRESS_District, COMPANY_ADDRESS, COMPANY_EMail, CREATE_TIME, STATUS)"
			+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_COP = 
			"UPDATE COMPANY SET COMPANY_BRC = ?, COMPANY_PIC = ?, COMPANY_PASSWORD = ?, COMPANY_NAME = ?, COMPANY_PHONE = ?, COMPANY_ADDRESS_City = ?, COMPANY_ADDRESS_District = ?, COMPANY_ADDRESS = ?, COMPANY_EMail = ? WHERE COMPANY_ID = ?";
	private static final String DELETE_COP =
			"DELETE FROM COMPANY WHERE COMPANY_ID = ?";
	private static final String GET_ONE_COP =
			"SELECT COMPANY_NO, COMPANY_ID, COMPANY_BRC, COMPANY_PIC, COMPANY_PASSWORD, COMPANY_NAME, COMPANY_PHONE, COMPANY_ADDRESS_City, COMPANY_ADDRESS_District, COMPANY_ADDRESS, COMPANY_EMail, CREATE_TIME, STATUS FROM COMPANY WHERE COMPANY_ID = ?";
	private static final String GET_ALL_COP =
			"SELECT COMPANY_NO, COMPANY_ID, COMPANY_BRC, COMPANY_PIC, COMPANY_PASSWORD, COMPANY_NAME, COMPANY_PHONE, COMPANY_ADDRESS_City, COMPANY_ADDRESS_District, COMPANY_ADDRESS, COMPANY_EMail, CREATE_TIME, STATUS FROM COMPANY ORDER BY COMPANY_NO";
	
	
	@Override
	public void insert(CopVO copVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println("CopDAO insert");
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_COP);
			
			System.out.println("insert");
			
			pstmt.setString( 1, copVO.getCompanyId());
			pstmt.setBytes( 2, copVO.getCompanyBRC());
			pstmt.setBytes( 3, copVO.getCompanyPic());
			pstmt.setString( 4, copVO.getCompanyPassword());
			pstmt.setString( 5, copVO.getCompanyName());
			pstmt.setString( 6, copVO.getCompanyPhone());
			pstmt.setString( 7, copVO.getCompanyAddressCity());
			pstmt.setString( 8, copVO.getCompanyAddressDistrict());
			pstmt.setString( 9, copVO.getCompanyAddress());
			pstmt.setString( 10, copVO.getCompanyEmail());
			pstmt.setDate( 11, copVO.getCreateTime());
			pstmt.setString( 12, copVO.getStatus());
			
			pstmt.executeUpdate();	
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if ( con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(CopVO copVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println("CopDAO update");
		
		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_COP);
						
			pstmt.setBytes( 1, copVO.getCompanyBRC());
			pstmt.setBytes( 2, copVO.getCompanyPic());
			pstmt.setString( 3, copVO.getCompanyPassword());
			pstmt.setString( 4, copVO.getCompanyName());
			pstmt.setString( 5, copVO.getCompanyPhone());
			pstmt.setString( 6, copVO.getCompanyAddressCity());
			pstmt.setString( 7, copVO.getCompanyAddressDistrict());
			pstmt.setString( 8, copVO.getCompanyAddress());
			pstmt.setString( 9, copVO.getCompanyEmail());
			pstmt.setString( 10, copVO.getCompanyId());
			
			pstmt.executeUpdate();	
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if ( con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
	}

	@Override
	public void delete(String companyId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_COP);
			
			pstmt.setString(1, companyId);		
			pstmt.executeUpdate();
				
		}/* catch (ClassNotFoundException e) {
			e.printStackTrace();
		} */catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if ( con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public CopVO findByCompanyId(String companyId) {
		
		CopVO copVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			System.out.println("Connecting to database successfully! ");
			pstmt = con.prepareStatement(GET_ONE_COP);
			
			pstmt.setString(1, companyId);		

			rs = pstmt.executeQuery();
			
			while( rs.next()) {
				copVO = new CopVO();
				copVO.setCompanyNo(rs.getInt("COMPANY_NO"));
				copVO.setCompanyId(rs.getString("COMPANY_ID"));
				copVO.setCompanyBRC(rs.getBytes("COMPANY_BRC"));
				copVO.setCompanyPic(rs.getBytes("COMPANY_PIC"));
				copVO.setCompanyPassword(rs.getString("COMPANY_PASSWORD"));
				copVO.setCompanyName(rs.getString("COMPANY_NAME"));
				copVO.setCompanyPhone(rs.getString("COMPANY_PHONE"));
				copVO.setCompanyAddressCity(rs.getString("COMPANY_Address_City"));
				copVO.setCompanyAddressDistrict(rs.getString("COMPANY_Address_District"));
				copVO.setCompanyAddress(rs.getString("COMPANY_Address"));
				copVO.setCompanyEmail(rs.getString("COMPANY_Email"));
				copVO.setCreateTime(rs.getDate("create_Time"));
				copVO.setStatus(rs.getString("status"));
				
			}
		}/* catch (ClassNotFoundException e) {
			e.printStackTrace();
		} */catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if ( pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if ( con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return copVO;
	}

	@Override
	public CopVO findByCompanyId(String companyId, String companyPassword) {
		CopVO copVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			System.out.println("Connecting to database successfully! ");
			pstmt = con.prepareStatement(GET_ONE_COP);
			
			pstmt.setString(1, companyId);		
			pstmt.setString(2, companyPassword);	

			rs = pstmt.executeQuery();
			
			while( rs.next()) {
				copVO = new CopVO();
				copVO.setCompanyNo(rs.getInt("COMPANY_NO"));
				copVO.setCompanyId(rs.getString("COMPANY_ID"));
				copVO.setCompanyBRC(rs.getBytes("COMPANY_BRC"));
				copVO.setCompanyPic(rs.getBytes("COMPANY_PIC"));
				copVO.setCompanyPassword(rs.getString("COMPANY_PASSWORD"));
				copVO.setCompanyName(rs.getString("COMPANY_NAME"));
				copVO.setCompanyPhone(rs.getString("COMPANY_PHONE"));
				copVO.setCompanyAddressCity(rs.getString("COMPANY_Address_City"));
				copVO.setCompanyAddressDistrict(rs.getString("COMPANY_Address_District"));
				copVO.setCompanyAddress(rs.getString("COMPANY_Address"));
				copVO.setCompanyEmail(rs.getString("COMPANY_Email"));
				copVO.setCreateTime(rs.getDate("create_Time"));
				copVO.setStatus(rs.getString("status"));
			}
		}/* catch (ClassNotFoundException e) {
			e.printStackTrace();
		} */catch (Exception e) {
			e.printStackTrace();
		} finally {
			if ( rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if ( pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if ( con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return copVO;
	}

	@Override
	public List<CopVO> getAll() {
		
		List<CopVO> list = new ArrayList<>();
		CopVO copVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			System.out.println("Connecting to database successfully! ");
			pstmt = con.prepareStatement(GET_ALL_COP);
			
//			pstmt.setString(1, companyId);		

			rs = pstmt.executeQuery();
			
			while( rs.next()) {
				copVO = new CopVO();
				copVO.setCompanyNo(rs.getInt("COMPANY_NO"));
				copVO.setCompanyId(rs.getString("COMPANY_ID"));
				copVO.setCompanyBRC(rs.getBytes("COMPANY_BRC"));
				copVO.setCompanyPic(rs.getBytes("COMPANY_PIC"));
				copVO.setCompanyPassword(rs.getString("COMPANY_PASSWORD"));
				copVO.setCompanyName(rs.getString("COMPANY_NAME"));
				copVO.setCompanyPhone(rs.getString("COMPANY_PHONE"));
				copVO.setCompanyAddressCity(rs.getString("COMPANY_ADDRESS_City"));
				copVO.setCompanyAddressDistrict(rs.getString("COMPANY_ADDRESS_District"));
				copVO.setCompanyAddress(rs.getString("COMPANY_ADDRESS"));
				copVO.setCompanyEmail(rs.getString("COMPANY_EMAIL"));
				copVO.setCreateTime(rs.getDate("create_Time"));
				copVO.setStatus(rs.getString("status"));
				list.add(copVO);
			}
		}/* catch (ClassNotFoundException e) {
			e.printStackTrace();
		} */catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if ( rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if ( pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if ( con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public String pwdhash(String password) {
//		SecureRandom random = new SecureRandom();
//		byte[] salt = new byte[16];
//		random.nextBytes(salt);
		try {
		//	KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		//	SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		//	byte[] hash = f.generateSecret(spec).getEncoded();
			Base64.Encoder enc = Base64.getEncoder();
			String newPwd = enc.encodeToString(password.getBytes());
			System.out.println("==="+newPwd+"=====");
			return newPwd;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
