<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%
	if(session.getAttribute("memVO")==null){
		%>
		<script>
		if (window.confirm('是否要登入會員?')){
			window.location.replace("http://localhost:8081/APE/members/Member.jsp");
        }else{
        	alert('非會員無法發文，僅能瀏覽');
        	document.location.href="http://localhost:8081/APE/articleMain/fourm.jsp"; //js頁面跳轉
        }
		</script>
		<%
		
	}
%>
    
    
    
 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>發文畫面</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/article/article.css">
</head>
<body>
  	 <%@ include file="/header.file"%>

    <main class="main_area">
        <section class="main_post">
            <div class="post_div">
                主題分類:
                <select name="type" id="post_type" name="article_type">
                    <option value="心得">心得</option>
                    <option value="展訊">展訊</option>
                    <option value="推廣">推廣</option>
                    <option value="黑特">黑特</option>
                    <option value="其它">其它</option>
                </select>
            </div>
            <div class="theme_div">
                文章主題:<input type="text" id="text"  class="theme_input" name="post_theme" placeholder="請輸入主題名稱...">
            </div>
            <hr id="h"> 
            <div class="area">
              <div class="test"></div>
              發文板塊:
                  <select name="area_type" id="post_area_type" id="area_id">
                    <option value="taipei">大台北地區</option>
                    <option value="tao">桃、竹、苗</option>
                    <option value="taichung">中部生活圈</option>
                    <option value="south">雲、嘉、南</option>
                    <option value="kaosh">大高屏地區</option>
                    <option value="east">東部/外島</option>
                  </select>
            </div>
            <div class="input_article">
                註:請勿出現人身攻擊、政治言論及不雅言詞。共同維持一個優良風氣的平台。
            </div>
            <div class="main_article">
              <script src="https://cdn.ckeditor.com/4.16.2/standard/ckeditor.js"></script>

              <textarea name="editor1" id="editor1" class="ed"></textarea>
              <script>CKEDITOR.replace("editor1" , {height : '650px'});</script>
            </div>
            <div id="btn_sub">
              <button type="submit" id="subbtn">送出</button>
              <button type="submit" id="return">返回</button>
            </div>
       
        </section>
        <div class="adv">
            <img src="<%=request.getContextPath()%>/img/ArticleIMG/廠商文宣示範.png" alt="1" class="img_1">
            <img src="<%=request.getContextPath()%>/img/ArticleIMG/文宣2.png" alt="2" class="img_2">
        </div>

        <div class="zoom">
          <iframe class="ccc" src="http://localhost:8081/APE/articleMain/index.html" width="100%" height="100%"></iframe>
        </div>
      
    </main> 

<%@ include file="/footer.file"%>

    <script src="<%=request.getContextPath()%>/js/articleJS/index.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/articleJS/article.js"></script>
 
   
</body>
</html>
