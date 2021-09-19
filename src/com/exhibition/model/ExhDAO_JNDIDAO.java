package com.exhibition.model;

import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;

public class ExhDAO_JNDIDAO implements ExhDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/APE");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
//	EXHIBITION_NO, EXHIBITION_PIC, EXHIBITION_DATE, EXHIBITION_TOPIC, EXHIBITION_CONTENT,EXHIBITION_AREA
	private static final String INSERT_EXH = "INSERT INTO EXHIBITION (EXHIBITION_PIC, EXHIBITION_DATE, EXHIBITION_TOPIC,EXHIBITION_CONTENT,EXHIBITION_AREA)VALUES(?,now(),?,?,?)";
	private static final String UPDATE = "UPDATE EXHIBITION set EXHIBITION_PIC=?, EXHIBITION_DATE=?, EXHIBITION_TOPIC=?,EXHIBITION_CONTENT=? where EXHIBITION_NO = ?";
	private static final String UPDATE2 = "UPDATE EXHIBITION set EXHIBITION_DATE=?, EXHIBITION_TOPIC=?,EXHIBITION_CONTENT=? where EXHIBITION_NO = ?";
	private static final String GET_ALL_EXH = "SELECT * FROM EXHIBITION order by EXHIBITION_NO DESC";

	@Override
	public void insert(ExhVO exhVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_EXH);
			System.out.println("連線成功");

			pstmt.setBytes(1, exhVO.getExhibitionPic());
			pstmt.setString(2, exhVO.getExhibitionTopic());
			pstmt.setString(3, exhVO.getExhibitionContent());
			pstmt.setString(4, exhVO.getExhibitionArea());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
					System.out.println("連線關閉");
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(ExhVO exhVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			System.out.println("連線成功");

			pstmt.setBytes(1, exhVO.getExhibitionPic());
			System.out.println("1");
			pstmt.setDate(2, exhVO.getExhibitionDate());
			System.out.println("2");
			pstmt.setString(3, exhVO.getExhibitionTopic());
			System.out.println("3");
			pstmt.setString(4, exhVO.getExhibitionContent());
			System.out.println("4");
			pstmt.setInt(5, exhVO.getExhibitionNo());
			System.out.println("5");
			pstmt.executeUpdate();
			System.out.println("6");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}

		}
		System.out.println("連線關閉");
	}

	public void update2(ExhVO exhVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE2);
			System.out.println("連線成功");

			pstmt.setDate(1, exhVO.getExhibitionDate());
			System.out.println("1");
			pstmt.setString(2, exhVO.getExhibitionTopic());
			System.out.println("2");
			pstmt.setString(3, exhVO.getExhibitionContent());
			System.out.println("3");
			pstmt.setInt(4, exhVO.getExhibitionNo());
			System.out.println("4");
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}

		}
		System.out.println("連線關閉");
	}

	@Override
	public void delete(Integer exhibitionNo) {
		// TODO Auto-generated method stub

	}

	@Override
	public ExhVO findByPrimaryKey(Integer exhibitionNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExhVO> getAll() {
		List<ExhVO> list = new ArrayList<ExhVO>();
		ExhVO exhVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_EXH);
			System.out.println("連線成功");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				exhVO = new ExhVO();
//EXHIBITION_NO, EXHIBITION_PIC, EXHIBITION_DATE, EXHIBITION_TOPIC, EXHIBITION_CONTENT EXHIBITION_AREA
				exhVO.setExhibitionNo(rs.getInt("EXHIBITION_NO"));
				exhVO.setExhibitionPic(rs.getBytes("EXHIBITION_PIC"));
				exhVO.setExhibitionDate(rs.getDate("EXHIBITION_DATE"));
				exhVO.setExhibitionTopic(rs.getString("EXHIBITION_TOPIC"));
				exhVO.setExhibitionContent(rs.getString("EXHIBITION_CONTENT"));
				exhVO.setExhibitionArea(rs.getString("EXHIBITION_AREA"));
				list.add(exhVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
					System.out.println("連線關閉");
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}

}
