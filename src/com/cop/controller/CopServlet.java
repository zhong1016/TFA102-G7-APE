package com.cop.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.cop.model.CopService;
import com.cop.model.CopVO;
import com.mem.model.MailService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class CopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 確認標頭
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		System.out.println("ContentType=" + req.getContentType());

		// 確認傳入的值
		String coploginid = req.getParameter("coploginid");
		System.out.println("######  into CopServlet  ######. memlogin is " + coploginid);
		
		String url = "/company/CopDtal.jsp";
		String errorUrl ="/members/Member.jsp";
		HttpSession session = req.getSession();
		

		// 會員登入
		if ("logInCop".equals(coploginid)) {
			System.out.println("### into collection logInMem ###");
			// 設定錯誤訊息陣列
			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 確認傳入的值
				String companyId = req.getParameter("companyId").trim().toUpperCase();
				System.out.println(companyId);
				String companyPassword = req.getParameter("companyPassword").trim();
				// 錯誤處理
				if (companyId == null || (companyId.trim().length() == 0)) {
					errorMsgs.add("print CopBER ID");
				} else if (companyPassword == null || (companyPassword.trim().length() == 0)) {
					errorMsgs.add("print CopBER PASSWORD");
				}
				// 設定CopService傳入資訊
				CopService copService = new CopService();
				CopVO copVO = copService.getOneCop(companyId);
				String copPwd = copService.pwdhash(companyPassword);
				
				if (copVO == null) {
					errorMsgs.add("undefined");
				}
				
				String passWord = copVO.getCompanyPassword();				
				if( !copPwd.equals(passWord)) {
					errorMsgs.add("姆丟唷");
				}
				// 確認資料有誤，印出錯誤資料並跳回原頁
				if (!errorMsgs.isEmpty()) {
//					session.setAttribute("copVO", copVO);
					RequestDispatcher successView = req.getRequestDispatcher(errorUrl);
					successView.forward(req, res);
					return;
				}
				// 確認資料無誤，則設定
				session.setAttribute("copVO", copVO);
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

		// 廠商修改密碼
		if ("changePassword".equals(coploginid)) {
			System.out.println("### into collection changePassword ###");
			// 設定錯誤訊息陣列
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 呼叫CopService程式
				CopService copService = new CopService();
				// 呼叫原頁面設定的CopVO資訊
				CopVO copVO = (CopVO) session.getAttribute("copVO");

				String companyPassword = req.getParameter("companyPassword").trim();
				if (companyPassword == null || companyPassword.trim().length() == 0) {
					errorMsgs.add("Please confirm your password!");
				}
				
				copVO.setCompanyPassword(copService.pwdhash(companyPassword));
				copVO = copService.updateCop(copVO);

				if (!errorMsgs.isEmpty()) {
					System.out.println("!errorMsgs.isEmpty()");
//					session.setAttribute("copVO", copVO);
					RequestDispatcher successView = req.getRequestDispatcher(errorUrl);
					successView.forward(req, res);
					return;
				}

				session.setAttribute("copVO", copVO);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(errorUrl);
				failureView.forward(req, res);
			}
		}

		if ("update".equals(coploginid)) { // update <input>
			System.out.println("### into collection update ###");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage
			// view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				CopService copService = new CopService();
				CopVO copVO = (CopVO) session.getAttribute("copVO");
				System.out.println("### into collection update ### 1");

				String companyName = req.getParameter("companyName").trim();
				System.out.println("### into collection update ### 2");

				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{1,80}$";
				if (companyName == null || companyName.trim().length() == 0) {
					System.out.println("try2 userfName == null");
					errorMsgs.add("Please confirm your userfName! 111");
				} else if (!companyName.trim().matches(enameReg)) {
					System.out.println("try2 userfName == null 333");
					errorMsgs.add("Please confirm your userfName enameReg!");
				}
				System.out.println("### into collection update ### 3");
				
				Part partBRC = req.getPart("companyBRC");
				System.out.println("### into collection update ### 4 ");
				byte[] companyBRC = null;
				companyBRC = copVO.getBytesByPart(partBRC);
				System.out.println("byte[] userPic" + companyBRC);
				int copBrclength = companyBRC.length;
				System.out.println(copBrclength);
				if (copBrclength != 0) {
					System.out.println("BRC");
					copVO.setCompanyBRC(companyBRC);
				}
				
				System.out.println("### into collection update ### 5 userPic");
				Part partPIC = req.getPart("companyPic");
				System.out.println("### into collection update ### 4 ");
				byte[] companyPIC = null;
				companyPIC = copVO.getBytesByPart(partPIC);
				System.out.println("byte[] userPic" + companyPIC);
				int copPiclength = companyPIC.length;
				System.out.println(copPiclength);
				if (copPiclength != 0) {
					System.out.println("PIC");
					copVO.setCompanyPic(companyPIC);
				}
				System.out.println("### into collection update ### 5 userPic");
				
				
				String companyPhone = req.getParameter("companyPhone").trim();
				System.out.println(companyPhone);
				String copPhoneReg = "^0[0-9]{3}\\d{5,6}$";
//				String copPhoneReg = "^09[0-9]{2}\\d{6}$";
				if (companyPhone == null || companyPhone.trim().length() == 0) {
					System.out.println("try2 userPhone == null");
					errorMsgs.add("Please confirm your userPhone!");
				} else if (!companyPhone.trim().matches(copPhoneReg)) {
					System.out.println("try2 userPhone == copPhoneReg");
					errorMsgs.add("Please confirm your Phone !");
				}
				System.out.println("### into collection update ### userPhone ");

				String companyAddressCity = req.getParameter("companyAddressCity");
				if (companyAddressCity == null || companyAddressCity.trim().length() == 0) {
					System.out.println("try2 companyAddressCity == null");
					errorMsgs.add("Please confirm your companyAddressCity!");
				}

				String companyAddressDistrict = req.getParameter("companyAddressDistrict");
				if (companyAddressDistrict == null || companyAddressDistrict.trim().length() == 0) {
					System.out.println("try2 companyAddressDistrict == null");
					errorMsgs.add("Please confirm your companyAddressDistrict!");
				}

				String companyAddress = req.getParameter("companyAddress").trim();
				if (companyAddress == null || companyAddress.trim().length() == 0) {
					System.out.println("try2 companyAddress == null");
					errorMsgs.add("Please confirm your companyAddress!");
				}

				String companyEmail = req.getParameter("companyEmail").trim();
				System.out.println(companyEmail);
				String copEmailReg = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,20})$";
				if (companyEmail == null || companyEmail.trim().length() == 0) {
					System.out.println("try2 userEmail == null");
					errorMsgs.add("Please confirm your userEmail!");
				} else if (!companyEmail.trim().matches(copEmailReg)) {
					System.out.println("try2 memEmailReg == memEmailReg");
					errorMsgs.add("Please confirm your memEmailReg !");
				}

				copVO.setCompanyName(companyName);
				copVO.setCompanyPhone(companyPhone);
				copVO.setCompanyAddressCity(companyAddressCity);
				copVO.setCompanyAddressDistrict(companyAddressDistrict);
				copVO.setCompanyAddress(companyAddress);
				copVO.setCompanyEmail(companyEmail);
				System.out.println("### into collection update ### 6 ");

				if (!errorMsgs.isEmpty()) {
					System.out.println("!errorMsgs.isEmpty()");
//					session.setAttribute("copVO", copVO);
					RequestDispatcher successView = req.getRequestDispatcher(errorUrl);
					successView.forward(req, res);
					return;
				}

				copVO = copService.updateCop(copVO);
				session.setAttribute("copVO", copVO);
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

		if ("SingupCop".equals(coploginid)) {
			System.out.println("### into collection SingupCop ###");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage
			// view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				CopService copService = new CopService();

				String companyId = req.getParameter("companyId").trim().toUpperCase();
				String memUserIdReg = "\\d{8}";
				if (companyId == null || companyId.trim().length() == 0) {
					errorMsgs.add("Please confirm your UserId!");
				} else if (!companyId.trim().matches(memUserIdReg)) {
					System.out.println("try2 userPhone == memUserIdReg");
					errorMsgs.add("Please, your userPassword need 6 to 20 type !");
				}

				String companyPassword = req.getParameter("companyPassword").trim();
				String copPasswordReg = "^[\\w\\+\\*\\.]{6,20}$";

				if (companyPassword == null || companyPassword.trim().length() == 0) {
					errorMsgs.add("Please confirm your password!");
				} else if (!companyPassword.trim().matches(copPasswordReg)) {
					System.out.println("try2 userPhone == memPasswordReg");
					errorMsgs.add("Please, your userPassword need 6 to 20 type !");
				}

				String companyEmail = req.getParameter("companyEmail").trim();
				System.out.println(companyEmail);
				String copEmailReg = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";

				if (companyEmail == null || companyEmail.trim().length() == 0) {
					System.out.println("try2 userEmail == null");
					errorMsgs.add("Please confirm your userEmail!");
				} else if (!companyEmail.trim().matches(copEmailReg)) {
					System.out.println("try2 memEmailReg == memEmailReg");
					errorMsgs.add("Please confirm your memEmailReg !");
				}

				CopVO copVO = new CopVO();
				copVO.setCompanyId(companyId);
				copVO.setCompanyPassword(companyPassword);
				copVO.setCompanyEmail(companyEmail);

				if (!errorMsgs.isEmpty()) {
//					session.setAttribute("copVO", copVO);
					RequestDispatcher successView = req.getRequestDispatcher(errorUrl);
					successView.forward(req, res);
					return;
				}

				copVO = copService.addCop(copVO);
				copVO = copService.getOneCop(companyId);
				session.setAttribute("copVO", copVO);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println("### into collection update ### 7");
				return;
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("test!!!!:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(errorUrl);
				failureView.forward(req, res);
			}
		}

		if ("forgetPasswordCop".equals(coploginid)) {
			System.out.println("### into collection memForgetPassword ###");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage
			// view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				// 確認傳入的值
				String companyId = req.getParameter("companyId").trim().toUpperCase();
				System.out.println(companyId);
				String companyEmail = req.getParameter("companyEmail").trim();
				System.out.println(companyEmail);
				// 錯誤處理
				if (companyId == null || (companyId.trim().length() == 0)) {
					errorMsgs.add("print MEMBER ID");
				} else if (companyEmail == null || (companyEmail.trim().length() == 0)) {
					errorMsgs.add("print MEMBER Email");
				}

				CopService copService = new CopService();
				CopVO copVO = copService.getOneCop(companyId);
				if (copVO == null) {
					errorMsgs.add("統編有誤");
				}
				
				if(!companyEmail.equals(copVO.getCompanyEmail())) {
					errorMsgs.add("非註冊信箱");
				}

				// 確認資料有誤，印出錯誤資料並跳回原頁
				if (!errorMsgs.isEmpty()) {
//					session.setAttribute("copVO", copVO);
					RequestDispatcher successView = req.getRequestDispatcher(errorUrl);
					successView.forward(req, res);
					return;
				}
				// 確認資料無誤，則設定
				MailService mailService = new MailService();
				String newPassword = mailService.genAuthCode();
				String subject = "If you've forgotten your password";
				String messageText = "Hello! " + copVO.getCompanyName() + "this is your new password " + newPassword;

				mailService.sendMail(companyEmail, subject, messageText);

				copVO.setCompanyPassword(copService.pwdhash(newPassword));
				copVO = copService.updateCop(copVO);

//				session.setAttribute("copVO", copVO);
				RequestDispatcher successView = req.getRequestDispatcher(errorUrl);
				successView.forward(req, res);
			} catch (Exception e) {
				e.getStackTrace();
				errorMsgs.add("test!!!!:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(errorUrl);
				failureView.forward(req, res);
			}
		}
	}
}