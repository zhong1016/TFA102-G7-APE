package com.mem.model;

import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.json.JSONObject;

import com.activitytype.model.ActivitytypeVO;
import com.mem.model.MemVO;
import com.ser.model.SerService;

public class MemService {

	private MemDAO_interface dao;

	public MemService() {
		dao = new MemDAO();
	}

	public MemVO addMem(MemVO memVO) {
		System.out.println("### addMem service");
		java.util.Date date = new java.util.Date();
		java.sql.Date dateSql = new java.sql.Date(date.getTime());
		memVO.setUserAddressCity("臺北市");
		memVO.setUserAddressDistrict("中山區");
		memVO.setCreateTime(dateSql);
		memVO.setStatus("0");
		dao.insert(memVO);

		return memVO;
	}

	public MemVO updateMem(MemVO memVO) {
		System.out.println("### updateMem service");
		dao.update(memVO);
		return memVO;
	}

	public void deleteMem(String userId) {
		dao.delete(userId);
	}

	public MemVO getOneMem(String userId) {
		System.out.println("### getOneMem service");
		return dao.findByUesr(userId);
	}

	public MemVO getOneMem(String userId, String userPassword) {
		return dao.findByUserId(userId, userPassword);
	}

	public List<MemVO> getAll() {
		return dao.getAll();
	}

	public String pwdhash(String password) {
		dao.pwdhash(password);
		return password;
	}
}
