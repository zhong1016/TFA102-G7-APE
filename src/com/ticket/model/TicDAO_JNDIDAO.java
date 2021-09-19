package com.ticket.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class TicDAO_JNDIDAO implements TicDAO_interface {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/APE");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
//	TICKET_NO, TICKET_NAME, TICKET_ID_CARD, TICKET_EMAIL, TICKET_QRCODE, EXHIBITION_NO, USER_ID
	private static final String INSERT_APE = "INSERT INTO TICKET (TICKET_NAME, TICKET_ID_CARD, TICKET_EMAIL, TICKET_QRCODE, EXHIBITION_NO, USER_ID) VALUES (?,?,?,?,?,?)";
	private static final String GET_ALL_APE = "SELECT * FROM TICKET order by TICKET_NO";
	private static final String GET_ONE_APE = "SELECT * FROM TICKET where TICKET_NO = ?";
	private static final String UPDATE = "UPDATE TICKET set TICKET_NAME=?, TICKET_ID_CARD=?, TICKET_EMAIL=? where TICKET_NO = ?";

	public void insert(TicVO ticVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_APE);
			System.out.println("連線成功!");

			pstmt.setString(1, ticVO.getTicketName());
			pstmt.setInt(2, ticVO.getTicketIdCard());
			pstmt.setString(3, ticVO.getTicketEmail());
			pstmt.setString(4, ticVO.getTicketQrcode());
			pstmt.setInt(5, ticVO.getExhibitionNo());
			pstmt.setInt(6, ticVO.getUserId());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.getMessage();
		} finally {
			if (con != null) {
				try {
					con.close();
					System.out.println("連線結束");
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(TicVO ticVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			System.out.println("連線成功!");

			pstmt.setString(1, ticVO.getTicketName());
			pstmt.setInt(2, ticVO.getTicketIdCard());
			pstmt.setString(3, ticVO.getTicketEmail());
			pstmt.setInt(4, ticVO.getTicketNo());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.getMessage();
		} finally {
			if (con != null) {
				try {
					con.close();
					System.out.println("連線結束");
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}

		}
	}

	@Override
	public void delete(Integer ticketNo) {
		// TODO Auto-generated method stub

	}

	@Override
	public TicVO findByPrimaryKey(Integer ticketNo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		TicVO TicVO = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_APE);
			System.out.println("連線成功!");
			pstmt.setInt(1, ticketNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TicVO = new TicVO();
				TicVO.setTicketNo(rs.getInt("TICKET_NO"));
				TicVO.setTicketName(rs.getString("TICKET_NAME"));
				TicVO.setTicketIdCard(rs.getInt("TICKET_ID_CARD"));
			}
		} catch (SQLException se) {
			se.getMessage();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		System.out.println("連線結束");
		return TicVO;

	}

	@Override
	public List<TicVO> getAll() {
		List<TicVO> list = new ArrayList<TicVO>();
		TicVO ticVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_APE);
			System.out.println("連線成功!");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ticVO = new TicVO();
//TICKET_NO, TICKET_NAME, TICKET_ID_CARD, TICKET_EMAIL, TICKET_QRCODE, EXHIBITION_NO
				ticVO.setTicketNo(rs.getInt("TICKET_NO"));
				ticVO.setTicketName(rs.getString("TICKET_NAME"));
				ticVO.setTicketIdCard(rs.getInt("TICKET_ID_CARD"));
				ticVO.setTicketEmail(rs.getString("TICKET_EMAIL"));
				ticVO.setExhibitionNo(rs.getInt("EXHIBITION_NO"));
				ticVO.setUserId(rs.getInt("USER_ID"));
				list.add(ticVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
					System.out.println("連線結束!");
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}

}
