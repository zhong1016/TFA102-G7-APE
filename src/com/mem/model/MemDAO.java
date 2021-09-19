package com.mem.model;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.sql.*;
import java.util.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Part;
import javax.sql.DataSource;

//import org.json.JSONString;

/*
 * new context.xml
 * <Resource auth="Container" driverClassName="com.mysql.cj.jdbc.Driver" maxIdle="10" maxTotal="20" maxWaitMillis="-1" name="jdbc/APEDB" password="123456" type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/APE?serverTimezone=Asia/Taipei" username="David"/>
 */
public class MemDAO implements MemDAO_interface {

	private static DataSource ds = null;

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

	private static final String INSERT_MEM = "INSERT INTO members (user_Id, user_Pic, user_Password, user_fName, user_lName, user_Phone, user_Address_City, user_Address_District, user_Address, user_Email, create_Time, status) "
			+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_MEM = "UPDATE members SET user_Pic = ?, user_Password = ?, user_fName = ?, user_lName = ?, user_Phone = ?, user_Address_City = ?, user_Address_District = ?, user_Address = ?, user_Email = ? WHERE user_Id = ?";
	private static final String DELETE_MEM = "DELETE FROM members WHERE user_Id = ?";
	private static final String LOGIN_MEM = "SELECT USER_NO, USER_ID, USER_PIC, USER_PASSWORD, USER_LNAME, USER_FNAME, USER_PHONE, USER_ADDRESS_City, USER_ADDRESS_District, USER_ADDRESS, USER_EMAIL, CREATE_TIME, STATUS FROM members WHERE user_Id = ? AND USER_PASSWORD = ?";
	private static final String GET_ONE_MEM = "SELECT USER_NO, USER_ID, USER_PIC, USER_PASSWORD, USER_FNAME, USER_LNAME, USER_PHONE, USER_ADDRESS_City, USER_ADDRESS_District, USER_ADDRESS, USER_EMAIL, CREATE_TIME, STATUS FROM members WHERE user_Id = ?";
	private static final String GET_ALL_MEM = "SELECT USER_NO, USER_ID, USER_PIC, USER_PASSWORD, USER_LNAME, USER_FNAME, USER_PHONE, USER_ADDRESS_City, USER_ADDRESS_District, USER_ADDRESS, USER_EMAIL, CREATE_TIME, STATUS FROM members ORDER BY user_No";

	@Override
	public void insert(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_MEM);

			pstmt.setString(1, memVO.getUserId());
			pstmt.setBytes(2, memVO.getUserPic());			
			pstmt.setString(3, memVO.getUserPassword());
			pstmt.setString(4, memVO.getUserfName());
			pstmt.setString(5, memVO.getUserlName());
			pstmt.setString(6, memVO.getUserPhone());
			pstmt.setString(7, memVO.getUserAddressCity());
			pstmt.setString(8, memVO.getUserAddressDistrict());
			pstmt.setString(9, memVO.getUserAddress());
			pstmt.setString(10, memVO.getUserEmail());
			pstmt.setDate(11, memVO.getCreateTime());
			pstmt.setString(12, memVO.getStatus());

			pstmt.executeUpdate();

		} /*
			 * catch (ClassNotFoundException e) { e.printStackTrace(); }
			 */ catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	public void update(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MEM);
			
			System.out.println("@@ into pstmt "+pstmt);
			pstmt.setBytes(1, memVO.getUserPic());
			pstmt.setString(2, memVO.getUserPassword());
			pstmt.setString(3, memVO.getUserfName());
			pstmt.setString(4, memVO.getUserlName());
			pstmt.setString(5, memVO.getUserPhone());
			pstmt.setString(6, memVO.getUserAddressCity());
			pstmt.setString(7, memVO.getUserAddressDistrict());
			pstmt.setString(8, memVO.getUserAddress());
			pstmt.setString(9, memVO.getUserEmail());
			pstmt.setString(10, memVO.getUserId());

			pstmt.executeUpdate();

		} /*
			 * catch (ClassNotFoundException e) { e.printStackTrace(); }
			 */ catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	public void delete(String userId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_MEM);

			pstmt.setString(1, userId);
			pstmt.executeUpdate();

		} /*
			 * catch (ClassNotFoundException e) { e.printStackTrace(); }
			 */catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	public MemVO findByUserId(String userId, String userPassword) {

		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			System.out.println("Connecting to database successfully findByUserId! ");
			pstmt = con.prepareStatement(LOGIN_MEM);

			pstmt.setString(1, userId);
			pstmt.setString(2, userPassword);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setUserNo(rs.getInt("USER_NO"));
				memVO.setUserId(rs.getString("USER_ID"));
				memVO.setUserPic(rs.getBytes("USER_PIC"));
				memVO.setUserPassword(rs.getString("USER_PASSWORD"));
				memVO.setUserfName(rs.getString("USER_LNAME"));
				memVO.setUserlName(rs.getString("USER_FNAME"));
				memVO.setUserPhone(rs.getString("USER_PHONE"));
				memVO.setUserAddressCity(rs.getString("user_Address_City"));
				memVO.setUserAddressDistrict(rs.getString("user_Address_District"));
				memVO.setUserAddress(rs.getString("user_Address"));
				memVO.setUserEmail(rs.getString("user_Email"));
				memVO.setCreateTime(rs.getDate("create_Time"));
				memVO.setStatus(rs.getString("status"));
			}
		} /*
			 * catch (ClassNotFoundException e) { e.printStackTrace(); }
			 */catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		return memVO;
	}

	@Override
	public MemVO findByUesr(String userId) {
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			System.out.println("Connecting to database successfully findByUesr! ");
			pstmt = con.prepareStatement(GET_ONE_MEM);
			System.out.println("findByUesr");
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setUserNo(rs.getInt("USER_NO"));
				memVO.setUserId(rs.getString("USER_ID"));
				memVO.setUserPic(rs.getBytes("USER_PIC"));
				memVO.setUserPassword(rs.getString("USER_PASSWORD"));
				memVO.setUserfName(rs.getString("USER_fNAME"));
				memVO.setUserlName(rs.getString("USER_lNAME"));
				memVO.setUserPhone(rs.getString("USER_PHONE"));
				memVO.setUserAddressCity(rs.getString("user_Address_City"));
				memVO.setUserAddressDistrict(rs.getString("user_Address_District"));
				memVO.setUserAddress(rs.getString("user_Address"));
				memVO.setUserEmail(rs.getString("user_Email"));
				memVO.setCreateTime(rs.getDate("create_Time"));
				memVO.setStatus(rs.getString("status"));
			}
		} /*
			 * catch (ClassNotFoundException e) { e.printStackTrace(); }
			 */catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
		return memVO;
	}

	@Override
	public List<MemVO> getAll() {

		List<MemVO> list = new ArrayList<>();
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			System.out.println("Connecting to database successfully getAll! ");
			pstmt = con.prepareStatement(GET_ALL_MEM);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memVO = new MemVO();
				memVO.setUserNo(rs.getInt("USER_NO"));
				memVO.setUserId(rs.getString("USER_ID"));
				memVO.setUserPic(rs.getBytes("USER_PIC"));
				memVO.setUserPassword(rs.getString("USER_PASSWORD"));
				memVO.setUserfName(rs.getString("USER_LNAME"));
				memVO.setUserlName(rs.getString("USER_FNAME"));
				memVO.setUserPhone(rs.getString("USER_PHONE"));
				memVO.setUserAddressCity(rs.getString("user_Address_City"));
				memVO.setUserAddressDistrict(rs.getString("user_Address_District"));
				memVO.setUserAddress(rs.getString("user_Address"));
				memVO.setUserEmail(rs.getString("user_Email"));
				memVO.setCreateTime(rs.getDate("create_Time"));
				memVO.setStatus(rs.getString("status"));
				list.add(memVO);
			}

		} /*
			 * catch (ClassNotFoundException e) { e.printStackTrace(); }
			 */catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
	
	
//	@Override //�o�Ӥ��ᦳ�ŦA��g(����)
//	public List<MemVO> getAll(Map<String, String[]> map) {
//		return null;
//	} 
//	public static void main(String[] args) {
//		MemDAO dao = new MemDAO();
//		java.util.Date now = new java.util.Date();
//		java.sql.Date sqlDate = new java.sql.Date(now.getTime());
//		String status = "0";
//		/*
//		 * insert
//		 */
//		MemVO memVOIn = new MemVO();
//		memVOIn.setUserId("A122222222");
//		memVOIn.setUserPic(null);
//		memVOIn.setUserPassword("Test123");
//		memVOIn.setUserfName("����");
//		memVOIn.setUserlName("ù");
//		memVOIn.setUserPhone("0225219822");
//		memVOIn.setUserAddressCity("�x�_��");
//		memVOIn.setUserAddressDistrict("���s��");
//		memVOIn.setUserAddress("�n�ʦ��12��13��9��");
//		memVOIn.setUserEmail("test2@edu.tw");
//		memVOIn.setCreateTime(sqlDate);
//		memVOIn.setStatus(status);
//		dao.insert(memVOIn);
//		/*
//		 * update
//		 */
//		MemVO memVOUp = new MemVO();
//		memVOUp.setUserPic(null);
//		memVOUp.setUserPassword("Test123");
//		memVOUp.setUserfName("��");
//		memVOUp.setUserlName("�C��");
//		memVOUp.setUserPhone("0225230302");
//		memVOUp.setUserAddressCity("�x�_��");
//		memVOUp.setUserAddressDistrict("���s��");
//		memVOUp.setUserAddress("���s�_���G�q42��34��");
//		memVOUp.setUserEmail("test1@edu.tw");
//		memVOUp.setUserId("A122222222");
//		dao.update(memVOUp);
	/*
	 * delete
	 */
//		dao.delete("A122222222");
	/*
	 * select one
	 */
//		MemVO memVOS1 = dao.findByUserId("A123456789");
//		System.out.print(memVOS1.getUserNo() + "\t");
//		System.out.print(memVOS1.getUserId() + "\t");
//		System.out.print(memVOS1.getUserPic() + "\t");
//		System.out.print(memVOS1.getUserPassword() + "\t");
//		System.out.print(memVOS1.getUserfName() + "\t");
//		System.out.print(memVOS1.getUserlName() + "\t");
//		System.out.print(memVOS1.getUserPhone() + "\t");
//		System.out.print(memVOS1.getUserAddressCity() + "\t");
//		System.out.print(memVOS1.getUserAddressDistrict() + "\t");
//		System.out.print(memVOS1.getUserAddress() + "\t");
//		System.out.print(memVOS1.getUserEmail() + "\t");
//		System.out.print(memVOS1.getCreateTime() + "\t");
//		System.out.println(memVOS1.getStatus());
//		System.out.println();
//		/*
//		 * select all
//		 */
//		List<MemVO> MemVOseleAll = dao.getAll();
//		for(MemVO memVOAll : MemVOseleAll) {
//			System.out.print(memVOAll.getUserNo() + "\t");
//			System.out.print(memVOAll.getUserId() + "\t");
//			System.out.print(memVOAll.getUserPic() + "\t");
//			System.out.print(memVOAll.getUserPassword() + "\t");
//			System.out.print(memVOAll.getUserfName() + "\t");
//			System.out.print(memVOAll.getUserlName() + "\t");
//			System.out.print(memVOAll.getUserPhone() + "\t");
//			System.out.print(memVOAll.getUserAddressCity() + "\t");
//			System.out.print(memVOAll.getUserAddressDistrict() + "\t");
//			System.out.print(memVOAll.getUserAddress() + "\t");
//			System.out.print(memVOAll.getUserEmail() + "\t");
//			System.out.print(memVOAll.getCreateTime() + "\t");
//			System.out.print(memVOAll.getStatus());
//			System.out.println();
//		}
//	}

}
