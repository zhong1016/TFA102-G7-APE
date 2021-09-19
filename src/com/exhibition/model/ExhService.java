package com.exhibition.model;

import java.sql.Date;
import java.util.List;

public class ExhService {

	private ExhDAO_JNDIDAO dao;

	public ExhService() {
		dao = new ExhDAO_JNDIDAO();
	}

	public ExhVO addexh(byte[] exhibitionPic, String exhibitionTopic, String exhibitionContent,String exhibitionArea) {

		ExhVO exhVO = new ExhVO();
		exhVO.setExhibitionPic(exhibitionPic);
		exhVO.setExhibitionTopic(exhibitionTopic);
		exhVO.setExhibitionContent(exhibitionContent);
		exhVO.setExhibitionArea(exhibitionArea);
		
		dao.insert(exhVO);
		return exhVO;
	}
	
	public ExhVO updateExh(byte[] exhibitionPic, java.sql.Date exhibitionDate, String exhibitionTopic, String exhibitionContent,Integer exhibitionNo) {

		ExhVO exhVO = new ExhVO();
		exhVO.setExhibitionPic(exhibitionPic);
		exhVO.setExhibitionDate(exhibitionDate);
		exhVO.setExhibitionTopic(exhibitionTopic);
		exhVO.setExhibitionContent(exhibitionContent);
		exhVO.setExhibitionNo(exhibitionNo);
		
		dao.update(exhVO);
		return exhVO;
	}

	public ExhVO updateExh2(java.sql.Date exhibitionDate, String exhibitionTopic, String exhibitionContent,Integer exhibitionNo) {
		
		ExhVO exhVO = new ExhVO();
		exhVO.setExhibitionDate(exhibitionDate);
		exhVO.setExhibitionTopic(exhibitionTopic);
		exhVO.setExhibitionContent(exhibitionContent);
		exhVO.setExhibitionNo(exhibitionNo);
		
		dao.update2(exhVO);
		return exhVO;
	}

	public List<ExhVO> getAll() {
		ExhVO exhVO = new ExhVO();
		return dao.getAll();
	}
}
