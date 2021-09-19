package com.ticket.model;

import java.util.*;


public interface TicDAO_interface {

	public void insert(TicVO apeVO);
	public void update(TicVO apeVO);
	public void delete(Integer ticketNo);
	public TicVO findByPrimaryKey(Integer ticketNo);
	public List<TicVO> getAll();
}
