package com.article.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArticleDAO implements ArticleDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/APE?serverTimezone=Asia/Taipei";
	String userid = "David";
	String passwd = "123456";

	// SQL���O
	private static final String INSERT_STMT = "INSERT INTO ARTICLE (ARTICLE_TIME,ARTICLE_STATUS,ARTICLE_CLASS, ARTICLE_MAIN,ARTICLE,COUNT,AREA,USER_NO) VALUES ( NOW(), ?, ?, ?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT  ARTICLE_NO,ARTICLE_TIME,ARTICLE_STATUS,ARTICLE_CLASS, ARTICLE_MAIN,ARTICLE,COUNT,AREA , USER_NO FROM ARTICLE order by ARTICLE_TIME desc";
	private static final String GET_ALL_STMTBYCOUNT = "SELECT  ARTICLE_NO,ARTICLE_TIME,ARTICLE_STATUS,ARTICLE_CLASS, ARTICLE_MAIN,ARTICLE,COUNT,AREA , USER_NO FROM ARTICLE order by COUNT desc";
	private static final String GET_ONE_STMT = "SELECT ARTICLE_NO,ARTICLE_TIME,ARTICLE_STATUS,ARTICLE_CLASS, ARTICLE_MAIN,ARTICLE,COUNT,AREA , USER_NO FROM ARTICLE where ARTICLE_NO = ?";
	private static final String DELETE = "DELETE FROM ARTICLE where ARTICLE_NO = ?";
	private static final String UPDATE = "UPDATE ARTICLE set ARTICLE_TIME=?, ARTICLE_STATUS=?, ARTICLE_CLASS=?, ARTICLE_MAIN=? ,ARTICLE=? , COUNT=? ,AREA=?  where ARTICLE_NO = ?";
	private static final String GET_ALL_BYAREA = "SELECT * FROM ARTICLE WHERE AREA = ? order by ARTICLE_TIME desc";
	private static final String UPDATECOUNT = "UPDATE ARTICLE set COUNT=? where ARTICLE_NO = ?";
	private static final String GET_ALL_BYUSERNO = "SELECT * FROM ARTICLE WHERE USER_NO = ? order by ARTICLE_TIME desc";
	@Override
	public void insert(ArticleVO aVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver); // ���U�X��
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setBoolean(1, aVo.getARTICLE_STATUS());
			pstmt.setString(2, aVo.getARTICLE_CLASS());
			pstmt.setString(3, aVo.getARTICLE_MAIN());
			pstmt.setString(4, aVo.getARTICLE());
			pstmt.setInt(5, aVo.getCOUNT());
			pstmt.setString(6, aVo.getAREA());
			pstmt.setInt(7, aVo.getUSER_NO());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

	}

	@Override
	public void update(ArticleVO aVo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, aVo.getARTICLE_TIME());
			pstmt.setBoolean(2, aVo.getARTICLE_STATUS());
			pstmt.setString(3, aVo.getARTICLE_CLASS());
			pstmt.setString(4, aVo.getARTICLE_MAIN());
			pstmt.setString(5, aVo.getARTICLE());
			pstmt.setInt(6, aVo.getCOUNT());
			pstmt.setInt(7, aVo.getARTICLE_NO());
			pstmt.setString(8, aVo.getAREA());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

	}

	@Override
	public void delete(Integer aRTICLE_NO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, aRTICLE_NO);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
					;
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
	}

	@Override
	public ArticleVO findPrimartKey(Integer ARTICLE_NO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ArticleVO avo = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, ARTICLE_NO);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				avo = new ArticleVO();
				avo.setARTICLE_NO(ARTICLE_NO);
				avo.setARTICLE_TIME(rs.getString("ARTICLE_TIME"));
				avo.setARTICLE_STATUS(rs.getBoolean("ARTICLE_STATUS"));
				avo.setARTICLE_CLASS(rs.getString("ARTICLE_CLASS"));
				avo.setARTICLE_MAIN(rs.getString("ARTICLE_MAIN"));
				avo.setARTICLE(rs.getString("ARTICLE"));
				avo.setAREA(rs.getString("AREA"));
				avo.setCOUNT(rs.getInt("COUNT"));
				avo.setUSER_NO(rs.getInt("USER_NO"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
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
	public List<ArticleVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ArticleVO avo = null;
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				avo = new ArticleVO();
				avo.setARTICLE_NO(rs.getInt("ARTICLE_NO"));

				avo.setARTICLE_TIME(rs.getString("ARTICLE_TIME"));

				avo.setARTICLE_STATUS(rs.getBoolean("ARTICLE_STATUS"));
				avo.setARTICLE_CLASS(rs.getString("ARTICLE_CLASS"));
				avo.setARTICLE_MAIN(rs.getString("ARTICLE_MAIN"));
				avo.setARTICLE(rs.getString("ARTICLE"));
				avo.setCOUNT(rs.getInt("COUNT"));
				avo.setAREA(rs.getString("AREA"));
				avo.setUSER_NO(rs.getInt("USER_NO"));

				list.add(avo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
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
		return list;
	}

	@Override
	public List<ArticleVO> getAllByCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ArticleVO avo = null;
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ResultSet rs = null;
		List<ArticleVO> hlist = new ArrayList<ArticleVO>();
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMTBYCOUNT);
			rs = pstmt.executeQuery();

			

				while (rs.next()) {
					avo = new ArticleVO();
					avo.setARTICLE_NO(rs.getInt("ARTICLE_NO"));
					avo.setARTICLE_TIME(rs.getString("ARTICLE_TIME"));
					avo.setARTICLE_STATUS(rs.getBoolean("ARTICLE_STATUS"));
					avo.setARTICLE_CLASS(rs.getString("ARTICLE_CLASS"));
					avo.setARTICLE_MAIN(rs.getString("ARTICLE_MAIN"));
					avo.setARTICLE(rs.getString("ARTICLE"));
					avo.setCOUNT(rs.getInt("COUNT"));
					avo.setAREA(rs.getString("AREA"));
					avo.setUSER_NO(rs.getInt("USER_NO"));
					list.add(avo);

				}
				
			hlist.add(list.get(0));
			hlist.add(list.get(1));
			hlist.add(list.get(2));
			hlist.add(list.get(3));
			hlist.add(list.get(4));
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
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
		return hlist;
	}

	public static void main(String[] args) throws Exception {
		ArticleDAO dao = new ArticleDAO();
		dao.updateCount(10);
		
	}

	public static String getLongString(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		StringBuilder sb = new StringBuilder();
		String str;
		while ((str = br.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}

		br.close();
		return sb.toString();

	}

	@Override
	public List<ArticleVO> getAllByArea(String area) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ArticleVO avo = null;
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BYAREA);
			pstmt.setString(1, area);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				avo = new ArticleVO();
				avo.setARTICLE_NO(rs.getInt("ARTICLE_NO"));

				avo.setARTICLE_TIME(rs.getString("ARTICLE_TIME"));

				avo.setARTICLE_STATUS(rs.getBoolean("ARTICLE_STATUS"));
				avo.setARTICLE_CLASS(rs.getString("ARTICLE_CLASS"));
				avo.setARTICLE_MAIN(rs.getString("ARTICLE_MAIN"));
				avo.setARTICLE(rs.getString("ARTICLE"));
				avo.setCOUNT(rs.getInt("COUNT"));
				avo.setAREA(rs.getString("AREA"));
				avo.setUSER_NO(rs.getInt("USER_NO"));

				list.add(avo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
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
		return list;
	}

	@Override
	public void updateCount(Integer i) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ArticleDAO adao = new ArticleDAO();
		ArticleVO avo = new ArticleVO();
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATECOUNT);
			
			avo = adao.findPrimartKey(i);
			
			int j = avo.getCOUNT();
			
			

			pstmt.setInt(1, j+1);
			pstmt.setInt(2, i);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

	}

	@Override
	public List<ArticleVO> getAllByUSERNO(Integer userNO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ArticleVO avo = null;
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BYUSERNO);
			pstmt.setInt(1, userNO);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				avo = new ArticleVO();
				avo.setARTICLE_NO(rs.getInt("ARTICLE_NO"));

				avo.setARTICLE_TIME(rs.getString("ARTICLE_TIME"));

				avo.setARTICLE_STATUS(rs.getBoolean("ARTICLE_STATUS"));
				avo.setARTICLE_CLASS(rs.getString("ARTICLE_CLASS"));
				avo.setARTICLE_MAIN(rs.getString("ARTICLE_MAIN"));
				avo.setARTICLE(rs.getString("ARTICLE"));
				avo.setCOUNT(rs.getInt("COUNT"));
				avo.setAREA(rs.getString("AREA"));
				avo.setUSER_NO(rs.getInt("USER_NO"));

				list.add(avo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
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
		return list;
	}
	
	
}
