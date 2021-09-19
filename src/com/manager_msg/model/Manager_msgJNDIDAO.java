package com.manager_msg.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.manager.model.ManagerVO;

public class Manager_msgJNDIDAO implements Manager_msgDAO_interface{
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/APE");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
		
		private static String INSERT_STMT=
				"INSERT INTO manager_msg(established_time, work_record, manager_no) values(?,?,?)";
		private static String UPDATE=
				"UPDATE manager_msg set established_time=?, work_record=?, manager_no=? where message_no = ?";
		private static String DELETE=
				"DELETE FROM manager_msg where manager_no=?";
		private static String GET_ONE_STMT=
				"SELECT * FROM manager_msg where message_no=?";
		private static String GET_ALL_STMT=
				"SELECT e.message_no, e.manager_no, e.established_time, e.work_record, d.account, d.nickname from MANAGER_MSG e join manager d on e.manager_no = d.manager_no; ";
			
		@Override
		public void insert(Manager_msgVO manager_msgVO) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
	
				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);			
				pstmt.setTimestamp(1, manager_msgVO.getEstablished_time());
				pstmt.setString(2, manager_msgVO.getWork_record());
				pstmt.setInt(3, manager_msgVO.getManagerVO().getManager_no());			
				pstmt.executeUpdate();					
				System.out.println("上傳成功");
			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			}finally {
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
		public void update(Manager_msgVO manager_msgVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setTimestamp(1, manager_msgVO.getEstablished_time());
				pstmt.setString(2, manager_msgVO.getWork_record());
				pstmt.setInt(3,  manager_msgVO.getManagerVO().getManager_no());
				pstmt.setInt(4, manager_msgVO.getMessage_no());
				
				pstmt.executeUpdate();			
				
			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			}finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) 
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
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
				
			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			}finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) 
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}		
		}
		
			
		@Override
		public Manager_msgVO findByPrimaryKey(Integer manager_no) {
			Manager_msgVO manager_msgVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;				
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);
				
				pstmt.setInt(1, manager_no);
		
				rs = pstmt.executeQuery();
				
				ManagerVO managerVO = new ManagerVO();
				managerVO.setManager_no(manager_no);
		
				manager_msgVO =new Manager_msgVO();
				manager_msgVO.setMessage_no(rs.getInt("message_no"));
				manager_msgVO.setEstablished_time(rs.getTimestamp("established_time"));
				manager_msgVO.setWork_record(rs.getString("work_record"));
				manager_msgVO.setManagerVO(managerVO);
						
			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
			return  manager_msgVO;
		}	
		@Override
		public List<Manager_msgVO> getAll() {
			List<Manager_msgVO> list = new ArrayList();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;				
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();
								
				while(rs.next()) {				
					Integer message_no = rs.getInt("message_no");
					Timestamp established_time = rs.getTimestamp("established_time");
					String work_record = rs.getString("work_record");
					Integer manager_no = rs.getInt("manager_no");
					String nickname = rs.getString("nickname");
					ManagerVO managerVO = new ManagerVO();
					managerVO.setManager_no(manager_no);	
					managerVO.setNickname(nickname);
					Manager_msgVO msgVO = new Manager_msgVO();
					msgVO.setMessage_no(message_no);
					msgVO.setEstablished_time(established_time);
					msgVO.setWork_record(work_record);
					msgVO.setManagerVO(managerVO);
					list.add(msgVO);
				}
							
			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		
		
		
	

}
