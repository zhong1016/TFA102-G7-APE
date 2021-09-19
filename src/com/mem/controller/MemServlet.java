package com.mem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONObject;

import com.cop.model.CopVO;
import com.mem.model.MailService;
import com.mem.model.MemService;
import com.mem.model.MemVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 確認標頭
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		System.out.println("ContentType=" + req.getContentType());
		HttpSession session = req.getSession();
		String url = "/members/MemDtal.jsp";
		String errorUrl = "/members/Member.jsp";

		// 確認傳入的值
		String memloginid = req.getParameter("memloginid");
		System.out.println("######  into MemServlet  ######. memlogin is " + memloginid);

		if (memloginid == null) {
			RequestDispatcher failureView = req.getRequestDispatcher("/members/Member.jsp");
			failureView.forward(req, res);
			return;
		}
		// 會員登入
		if ("logInMem".equals(memloginid)) {
			System.out.println("### into collection logInMem ###");
			// 設定錯誤訊息陣列
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 確認傳入的值
				String userId = req.getParameter("userId").trim().toUpperCase();
				String userPassword = req.getParameter("userPassword");
				// 錯誤處理
				if (userId == null || (userId.trim().length() == 0)) {
					errorMsgs.add("print MEMBER ID");
				} else if (userPassword == null || (userPassword.trim().length() == 0)) {
					errorMsgs.add("print MEMBER PASSWORD");
				}

				// 設定MemService傳入資訊
				MemService dao = new MemService();				
				MemVO memVO = dao.getOneMem(userId);
				String userPwd = dao.pwdhash(userPassword);

				if (memVO == null) {
					errorMsgs.add("undefined");
				}
				String userPwd2 = memVO.getUserPassword();

				if (!userPwd.equals(userPwd2)) {
					errorMsgs.add("姆丟唷");
				}
				// 確認資料有誤，印出錯誤資料並跳回原頁
				if (!errorMsgs.isEmpty()) {
//					session.setAttribute("memVO", memVO);
					RequestDispatcher successView = req.getRequestDispatcher(errorUrl);
					successView.forward(req, res);
					return;
				}
				// 確認資料無誤，則設定
				session.setAttribute("memVO", memVO);
				System.out.println("be login...");
				String location = (String) session.getAttribute("location");
				
				if (location != null) {
					session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					res.sendRedirect(location);
					return;
				}
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
				// 其他錯誤處理
			} catch (Exception e) {
				errorMsgs.add(":" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(errorUrl);
				failureView.forward(req, res);
			}
		}

		// 會員修改密碼
		if ("changePassword".equals(memloginid)) {
			System.out.println("### into collection changePassword ###");
			// 設定錯誤訊息陣列
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 呼叫MemService程式
				MemService memService = new MemService();
				
				// 呼叫原頁面設定的MemVO資訊
				MemVO memVO = (MemVO) session.getAttribute("memVO");

				String userPassword = req.getParameter("userPassword").trim();
				if (userPassword == null || userPassword.trim().length() == 0) {
					errorMsgs.add("Please confirm your password!");
				}
				
				memVO.setUserPassword(memService.pwdhash(userPassword));
				memVO = memService.updateMem(memVO);

				if (!errorMsgs.isEmpty()) {
					System.out.println("!errorMsgs.isEmpty()");
					session.setAttribute("memVO", memVO);
					RequestDispatcher successView = req.getRequestDispatcher(errorUrl);
					successView.forward(req, res);
					return;
				}

				session.setAttribute("memVO", memVO);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(errorUrl);
				failureView.forward(req, res);
			}
		}

		if ("update".equals(memloginid)) { // update <input>
			System.out.println("### into collection update ###");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage
			// view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				MemService memService = new MemService();
				MemVO memVO = (MemVO) session.getAttribute("memVO");
				System.out.println("### into collection update ### 1");

				String userfName = req.getParameter("userfName").trim();
				String userlName = req.getParameter("userlName").trim();
				System.out.println("### into collection update ### 2");

				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{1,15}$";
				if (userfName == null || userfName.trim().length() == 0) {
					System.out.println("try2 userfName == null");
					errorMsgs.add("Please confirm your userfName! 111");
				} else if (userlName == null || userlName.trim().length() == 0) {
					System.out.println("try2 userfName == null 222");
					errorMsgs.add("Please confirm your userlName!");
				} else if (!userfName.trim().matches(enameReg)) {
					System.out.println("try2 userfName == null 333");
					errorMsgs.add("Please confirm your userfName enameReg!");
				} else if (!userlName.trim().matches(enameReg)) {
					System.out.println("try2 userfName == null 444");
					errorMsgs.add("Please confirm your userlName enameReg!");
				}
				System.out.println("### into collection update ### 3");
				/*
				 * thanks for teacher William support!
				 */
//				String userPic = req.getParameter("userPic");
//				String myString = image.substring(image.lastIndexOf(',') + 1);
//				String userPic = req.getParameter("userPic");
//				String myString = image.substring(image.lastIndexOf(',') + 1);
//				Part part = req.getPart("userPic");
//				byte[] memPic = null;
//				memPic = memVO.getBytesByPart(part);
//				if(memPic == null || memPic.length == 0) {
//					errorMsgs.add(" need PIC");
//				}
				Part part = req.getPart("userPic");
				System.out.println("### into collection update ### 4 ");
				byte[] userPic = null;
				userPic = memVO.getBytesByPart(part);
				System.out.println("byte[] userPic" + userPic);
				int userPiclength = userPic.length;
				System.out.println(userPiclength);
				if (userPiclength != 0) {
					System.out.println("PIC");
					memVO.setUserPic(userPic);
				}
				System.out.println("### into collection update ### 5 userPic");

				String userPhone = req.getParameter("userPhone").trim();
				System.out.println(userPhone);
//				String memPhoneReg = "^0[0-9]{3}-\\\\d{5,6}}$";
				String memPhoneReg = "^0[0-9]{3}\\d{6}$";
				if (userPhone == null || userPhone.trim().length() == 0) {
					System.out.println("try2 userPhone == null");
					errorMsgs.add("Please confirm your userPhone!");
				} else if (!userPhone.trim().matches(memPhoneReg)) {
					System.out.println("try2 userPhone == memPhoneReg");
					errorMsgs.add("Please confirm your userPhone !");
				}
				System.out.println("### into collection update ### userPhone ");

				String userAddressCity = req.getParameter("userAddressCity");
				if (userAddressCity == null || userAddressCity.trim().length() == 0) {
					System.out.println("try2 userAddressCity == null");
					errorMsgs.add("Please confirm your userAddressCity!");
				}

				String userAddressDistrict = req.getParameter("userAddressDistrict");
				if (userAddressDistrict == null || userAddressDistrict.trim().length() == 0) {
					System.out.println("try2 userAddressDistrict == null");
					errorMsgs.add("Please confirm your userAddressDistrict!");
				}

				String userAddress = req.getParameter("userAddress").trim();
				if (userAddress == null || userAddress.trim().length() == 0) {
					System.out.println("try2 userAddress == null");
					errorMsgs.add("Please confirm your userAddress!");
				}

				String userEmail = req.getParameter("userEmail").trim();
				System.out.println(userEmail);
				String memEmailReg = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,20})$";
				if (userEmail == null || userEmail.trim().length() == 0) {
					System.out.println("try2 userEmail == null");
					errorMsgs.add("Please confirm your userEmail!");
				} else if (!userEmail.trim().matches(memEmailReg)) {
					System.out.println("try2 memEmailReg == memEmailReg");
					errorMsgs.add("Please confirm your memEmailReg !");
				}

				memVO.setUserfName(userfName);
				memVO.setUserlName(userlName);
				memVO.setUserPhone(userPhone);
				memVO.setUserAddressCity(userAddressCity);
				memVO.setUserAddressDistrict(userAddressDistrict);
				memVO.setUserAddress(userAddress);
				memVO.setUserEmail(userEmail);
				System.out.println("### into collection update ### 6 ");

				if (!errorMsgs.isEmpty()) {
					System.out.println("!errorMsgs.isEmpty()");
					session.setAttribute("memVO", memVO);
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					return;
				}

				memVO = memService.updateMem(memVO);
				session.setAttribute("memVO", memVO);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("### into collection update ### 7");

			} catch (Exception e) {
				System.out.println("@@ exception   :" + e);
				errorMsgs.add("test!!!!:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(errorUrl);
				failureView.forward(req, res);
			}
		}

		if ("SingupMem".equals(memloginid)) {
			System.out.println("### into collection SingupMem ###");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage
			// view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				MemService memService = new MemService();

				String userId = req.getParameter("userId").trim().toUpperCase();
				String memUserIdReg = "([A-Z][1-2]){1}\\d{8}";
				if (userId == null || userId.trim().length() == 0) {
					errorMsgs.add("Please confirm your UserId!");
				} else if (!userId.trim().matches(memUserIdReg)) {
					System.out.println("try2 userPhone == memUserIdReg");
					errorMsgs.add("Please, your userPassword need 6 to 20 type !");
				}
				String value = "0123456789ABCDEFGHJKLMNPQRSTUVXYWZIO";
				int idfirst = value.indexOf(userId.charAt(0));
				int total = idfirst / 10 + (idfirst % 10) * 9;

				for (int i = 1; i < 9; i++) {
					total += value.indexOf(userId.charAt(i)) * (9 - i);
				}
				total += value.indexOf(userId.charAt(9));
				if (total % 10 != 0) {
					errorMsgs.add("Please confirm your userId!");
				}

				String userPassword = req.getParameter("userPassword").trim();
				String memPasswordReg = "^[\\w\\+\\*\\.]{6,20}$";
				if (userPassword == null || userPassword.trim().length() == 0) {
					errorMsgs.add("Please confirm your password!");
				} else if (!userPassword.trim().matches(memPasswordReg)) {
					System.out.println("try2 userPhone == memPasswordReg");
					errorMsgs.add("Please, your userPassword need 6 to 20 type !");
				}

				String userEmail = req.getParameter("userEmail").trim();
				System.out.println(userEmail);
				String memEmailReg = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";

				if (userEmail == null || userEmail.trim().length() == 0) {
					System.out.println("try2 userEmail == null");
					errorMsgs.add("Please confirm your userEmail!");
				} else if (!userEmail.trim().matches(memEmailReg)) {
					System.out.println("try2 memEmailReg == memEmailReg");
					errorMsgs.add("Please confirm your memEmailReg !");
				}

				MemVO memVO = new MemVO();
				memVO.setUserId(userId);
				memVO.setUserPassword(memService.pwdhash(userPassword));
				memVO.setUserEmail(userEmail);

				if (!errorMsgs.isEmpty()) {

					System.out.println("!errorMsgs.isEmpty()");
					session.setAttribute("memVO", memVO);
					RequestDispatcher successView = req.getRequestDispatcher(errorUrl);
					successView.forward(req, res);
					return;
				}

				memVO = memService.addMem(memVO);
				session.setAttribute("memVO", memVO);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("### into collection update ### 7");

			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("test!!!!:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(errorUrl);
				failureView.forward(req, res);
			}
		}

		if ("loginOut".equals(memloginid)) {
//			session.getAttribute("memVO");
//			session = req.getSession(false);// 防止建立Session
//			if (session == null) {
//				res.sendRedirect("/index.html");
//				return;
//			}
			try {
				System.out.println("loginOut");
				session.removeAttribute("memVO");
				session.removeAttribute("copVO");

				HashMap<String, String> userInfoMap = new HashMap<String, String>();
				userInfoMap.put("check", "2");

				session.invalidate();

				JSONObject obj = new JSONObject(userInfoMap);
				PrintWriter out = res.getWriter();
				out.println(obj);
				System.out.println(obj + "loginOut");
				return;
			} catch (Exception e) {
				e.getStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher(errorUrl);
				failureView.forward(req, res);
			}
		}

		if ("forgetPasswordMem".equals(memloginid)) {
			System.out.println("### into collection memForgetPassword ###");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage
			// view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				// 確認傳入的值
				String userId = req.getParameter("userId").trim().toUpperCase();
				System.out.println(userId);
				String userEmail = req.getParameter("userEmail").trim();
				System.out.println(userEmail);
				// 錯誤處理
				if (userId == null || (userId.trim().length() == 0)) {
					errorMsgs.add("print MEMBER ID");
				} else if (userEmail == null || (userEmail.trim().length() == 0)) {
					errorMsgs.add("print MEMBER Email");
				}

				MemService memService = new MemService();
				MemVO memVO = memService.getOneMem(userId);
				if (memVO == null) {
					errorMsgs.add("Your userID memVO != null");
				}
				
				if(!userEmail.equals(memVO.getUserEmail())) {
					errorMsgs.add("非註冊信箱");
				}

				// 確認資料有誤，印出錯誤資料並跳回原頁
				if (!errorMsgs.isEmpty()) {
					session.setAttribute("memVO", memVO);
					RequestDispatcher successView = req.getRequestDispatcher(errorUrl);
					successView.forward(req, res);
					return;
				}
				// 確認資料無誤，則設定
				MailService mailService = new MailService();
				String newPassword = mailService.genAuthCode();
				String subject = "If you've forgotten your password";
				String messageText = "Hello! " + memVO.getUserlName() + "this is your new password : 「　" + newPassword +"　」";
				mailService.sendMail(userEmail, subject, messageText);

				memVO.setUserPassword(memService.pwdhash(newPassword));
				memVO = memService.updateMem(memVO);
				System.out.println("forgetPasswordMem");
				RequestDispatcher successView = req.getRequestDispatcher(errorUrl);
				successView.forward(req, res);
			} catch (Exception e) {
				e.getStackTrace();
				errorMsgs.add("test!!!!:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(errorUrl);
				failureView.forward(req, res);
			}
		}
		if ("checkLogin".equals(memloginid)) {

			try {
//				res.addHeader("Access-Control-Allow-Origin", "*");
				MemVO memVO = (MemVO) session.getAttribute("memVO");
				CopVO copVO = (CopVO) session.getAttribute("copVO");

				HashMap<String, String> userInfoMap = new HashMap<String, String>();
				userInfoMap.put("check", "2");	

				if (memVO != null) {
					userInfoMap.put("check", "1");
					userInfoMap.put("name", memVO.getUserlName());
					userInfoMap.put("url", "/APE/members/MemDtal.jsp");
				} else if (copVO != null) {
					userInfoMap.put("check", "1");
					String copName = copVO.getCompanyName().substring(0, 4);
					userInfoMap.put("name", copName);
					userInfoMap.put("url", "/APE/company/CopDtal.jsp");
				}

				JSONObject obj = new JSONObject(userInfoMap);
				PrintWriter out = res.getWriter();
				out.println(obj);
				System.out.println(obj);
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher(errorUrl);
				failureView.forward(req, res);
			}
		}
	}

	/*
	 * thanks for teacher William support!
	 */
//	private byte[] part2Bytes(Part part) {
//		try (InputStream is = part.getInputStream()){
//			byte[] buff = new byte[is.available()];
//			if (is.read(buff) != -1) {
//				return buff;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

}
