package com.ticket.model;

import java.util.List;

public class TicService {

	private TicDAO_JNDIDAO dao;

	public TicService() {
		dao = new TicDAO_JNDIDAO();
	}

	public TicVO addApe(String ticketName, Integer ticketIdCard, String ticketEmail, String ticketQrcode,
			Integer exhibitionNo,Integer userId) {

		TicVO ticVO = new TicVO();

		ticVO.setTicketName(ticketName);
		ticVO.setTicketIdCard(ticketIdCard);
		ticVO.setTicketEmail(ticketEmail);
		ticVO.setTicketQrcode(ticketQrcode);
		ticVO.setExhibitionNo(exhibitionNo);
		ticVO.setUserId(userId);
		dao.insert(ticVO);

		return ticVO;
	}

	public TicVO updateApe(String ticketName, Integer ticketIdCard, String ticketEmail, Integer ticketNo) {

		TicVO ticVO = new TicVO();
		ticVO.setTicketName(ticketName);
		ticVO.setTicketIdCard(ticketIdCard);
		ticVO.setTicketEmail(ticketEmail);
		ticVO.setTicketNo(ticketNo);
		dao.update(ticVO);

		return ticVO;
	}

	public TicVO getOneApe(Integer ticketNo) {
		return dao.findByPrimaryKey(ticketNo);
	}

	public List<TicVO> getAll() {
		TicVO ticVO = new TicVO();
		return dao.getAll();
	}

}
