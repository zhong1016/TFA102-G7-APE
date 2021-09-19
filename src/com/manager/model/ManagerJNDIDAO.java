package com.manager.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ManagerJNDIDAO implements ManagerDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/APE");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO manager(account,password,nickname,phone,email,headshot)  values (?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE manager set account=?,  password=?,  nickname=?, phone=?, email=? where manager_no =?";
	private static final String DELETE = "DELETE FROM manager where manager_no = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM manager where manager_no = ?";
	private static final String GET_ALL_STMT = "SELECT  * FROM manager";
	private static final String GET_BY_ACCOUNT = "SELECT * FROM manager where account = ?";
	// 更新管理員資料(含頭貼)
	private static final String UPDATE_INCLUDE_PIC = "UPDATE MANAGER set headshot = ?,nickname = ?, phone = ?, email =  ? where manager_no = ?";;

	// 更新管理員資料(不含頭貼)
	private static final String UPDATE_EXCLUDE_PIC = "UPDATE MANAGER set nickname = ?, phone = ?, email =  ? where manager_no = ?";

	@Override
	public void insert(ManagerVO managerVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, managerVO.getAccount());
			pstmt.setString(2, managerVO.getPassword());
			pstmt.setString(3, managerVO.getNickname());
			pstmt.setString(4, managerVO.getPhone());
			pstmt.setString(5, managerVO.getEmail());
			pstmt.setBytes(6, managerVO.getHeadshot());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(ManagerVO managerVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, managerVO.getAccount());
			pstmt.setString(2, managerVO.getPassword());
			pstmt.setString(3, managerVO.getNickname());
			pstmt.setString(4, managerVO.getPhone());
			pstmt.setString(5, managerVO.getEmail());
			pstmt.setInt(6, managerVO.getManager_no());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(Integer manager_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, manager_no);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public ManagerVO findByPrimaryKey(Integer manager_no) {

		ManagerVO managerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, manager_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				managerVO = new ManagerVO();
				managerVO.setManager_no(rs.getInt("manager_no"));
				managerVO.setAccount(rs.getString("account"));
				managerVO.setPassword(rs.getString("password"));
				managerVO.setNickname(rs.getString("nickname"));
				managerVO.setPhone(rs.getString("phone"));
				managerVO.setEmail(rs.getString("email"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return managerVO;
	}

	@Override
	public List<ManagerVO> getAll() {

		List<ManagerVO> list = new ArrayList<ManagerVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Integer manager_no = rs.getInt("manager_no");
				String account = rs.getString("account");
				String password = rs.getString("password");
				String nickname = rs.getString("nickname");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				ManagerVO managerVO = new ManagerVO(manager_no, account, password, nickname, phone, email, null);
				list.add(managerVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override

	public ManagerVO findByAccount(String account) {
		ManagerVO managerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_ACCOUNT);

			pstmt.setString(1, account);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				managerVO = new ManagerVO();
				managerVO.setManager_no(rs.getInt("manager_no"));
				managerVO.setAccount(rs.getString("account"));
				managerVO.setPassword(rs.getString("password"));
				managerVO.setNickname(rs.getString("nickname"));
				managerVO.setPhone(rs.getString("phone"));
				managerVO.setEmail(rs.getString("email"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return managerVO;
	}

	/***************** 更新管理員資料(含大頭貼) ******************/
	@Override
	public void updateIncludePic(ManagerVO managerVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();

			InputStream stream = new ByteArrayInputStream(managerVO.getHeadshot());
			pstmt = con.prepareStatement(UPDATE_INCLUDE_PIC);
			pstmt.setBytes(1, managerVO.getHeadshot());
			pstmt.setString(2, managerVO.getNickname());
			pstmt.setString(3, managerVO.getPhone());
			pstmt.setString(4, managerVO.getEmail());
			pstmt.setInt(5, managerVO.getManager_no());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	/***************** 更新管理員資料(不含大頭貼) ******************/
	@Override
	public void updateExcludePic(ManagerVO managerVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt = con.prepareStatement(UPDATE_EXCLUDE_PIC);
			pstmt.setString(1, managerVO.getNickname());
			pstmt.setString(2, managerVO.getPhone());
			pstmt.setString(3, managerVO.getEmail());
			pstmt.setInt(4, managerVO.getManager_no());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
}
