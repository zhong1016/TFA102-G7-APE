<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.article.model.ArticleVO"%>
<%@page import="com.article.model.ArticleDAO"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
 <% 
 ArticleDAO dao = new ArticleDAO();
List<ArticleVO> list = new ArrayList();
list = dao.getAll();

ArticleDAO hdao = new ArticleDAO();
List<ArticleVO> hlist = new ArrayList(); //熱門文章
hlist = hdao.getAllByCount();


ArticleDAO Adao = new ArticleDAO(); 
List<ArticleVO> Alist = new ArrayList(); //分區文章
Alist =(List) request.getAttribute("area");
pageContext.setAttribute("Alist", Alist);

%>
 
 
<!DOCTYPE html>
<html lang="zh-TW">

<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>艾普藝森-討論區</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/article/index.css" />
  <link href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap" rel="stylesheet" />
</head>

<body>
  	 <%@ include file="/header.file"%>

  <section id="hotTopic">
    <div id="aside">
        <br>
        今<br>
        <br>
        日<br>
        <br>
        熱<br>
        <br>
        門<br>
    </div>
    <div id="table_div">
        <table id="hotArticle">
            <tr>

                <th class="top_sty"><b>類別</b></th>
                <th class="top_topic"><b>主題</b></th>
                <th class="top_num"><b>回應</b></th>
                <th class="top_author"><b>發文者</b></th>
                <th class="top_time"><b>時間</b></th>
            </tr>

				<%for (ArticleVO havo : hlist){%>			          
            <tr class="test">
                <td class="hot_sty"> <P class="hot_a"><%=havo.getARTICLE_CLASS() %> </p></td>
                <td class="hot_topic"> <p  class="hot_a"> <%=havo.getARTICLE() %></p></td>
                <td class="hot_num"> <p href="#" class="hot_a"> <%=havo.getCOUNT()%></p></td>
                <td class="hot_author"> <p href="#" class="hot_a"> David</p></td>
                <td class="hot_time"><p href="#" class="hot_a"><%=havo.getARTICLE_TIME() %></p></td>
                <form  action ="<%=request.getContextPath()%>/control" method ="GET"> 
                	<INPUT TYPE="hidden" NAME="articleNo" value="<%=havo.getARTICLE_NO()%>"></INPUT>          	
                </form>
            </tr>
            <%} %>
            
            
            
        </table>
    </div>
    <div id="img_div">
        <a href="https://tw.yahoo.com/" target="_blank"><img src="<%=request.getContextPath()%>/img/ArticleIMG/廠商文宣示範.png" alt="adv." id="client_img"></a>
        <a href="https://www.youtube.com/" target="_blank"><img src="<%=request.getContextPath()%>/img/ArticleIMG/文宣4.png" alt="adv." id="client_img_1"></a>
    </div>
</section>

<main>
    <div class="area_btn">
   	 
        <button  type="button"><a href="#" class="for">大台北地區</a></button> 
        
     
        <button class="tfabtn"  type="button"><a href="#" class="for">桃、竹、苗</a></button>    
        <form class="btnform" style="display: inline-block;width: 0" action ="<%=request.getContextPath()%>/AreaControl" method ="GET">
  		<INPUT class="tao" TYPE="hidden" NAME="area" value="tao"></INPUT>          	
     </form>
       	
        <button type="button"><a href="#" class="for">中部生活圈</a></button>
        <button type="button"><a href="#" class="for">雲、嘉、南</a></button>
        <button type="button"><a href="#" class="for">大高屏地區</a></button>
        <button type="button"><a href="#" class="for">東部/外島</a></button>
        <button type="button" class="article_post"><a href="http://127.0.0.1:5500/article.html" target="_blank" id="post"> 我要發文!</a></button>
    </div>
    <div class="main_content">
        <div class="main_article">
            <table id="ttt">
                <tr id="all1">
                    <th class="sty" id="sty_id"> <b> 類別</b></th>
                    <th class="topic" id="topic_id"><b>主題</b></th>
                    <th class="num" id="num_id"><b>回應</b></th>
                    <th class="author" id="author_id"><b>發文者</b></th>
                    <th class="time" id="time_id"><b>時間</b></th>
                </tr>
                
                
               <%@ include file="page3.file" %> 
                <c:forEach var = "avo" items = "${Alist}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> 
                 <tr class="test"> 
                    <td class="sty tran"> <p href="" class="a_link"> ${avo.ARTICLE_CLASS} </p></td>
                    <td class="topic tran"> <p class="a_link">${avo.ARTICLE}</p></td>
                    <td class="num tran"><p  class="a_link">${avo.COUNT} </p></td>
                    <td class="author tran" style="display:none"><p class="a_link">${avo.ARTICLE_NO}</p></td>
                    <td class="author tran"><p class="a_link">${avo.USER_NO}</p></td>
                    <td class="time tran"><p  class="a_link">${avo.ARTICLE_TIME}</p></td>
                      <form action ="<%=request.getContextPath()%>/control" method ="GET">
                		<INPUT TYPE="hidden" NAME="articleNo" value="${avo.ARTICLE_NO}">         	
                	</form>
                </tr>              
                </c:forEach >
                </table>
               <%@ include file="page4.file" %>  
           
        </div>

       <div class="main_asside">
            <img src="<%=request.getContextPath()%>/img/ArticleIMG/pc1.png" class="pc_img" alt="1">
            <img src="<%=request.getContextPath()%>/img/ArticleIMG/pc2.jpg" class="pc_img" alt="1">
            <img src="<%=request.getContextPath()%>/img/ArticleIMG/pc3.jpg" class="pc_img" alt="1">
            <div class="chat">
                <iframe class="ccc" src="http://localhost:8081/APE/articleMain/index.html" width="100%" height="100%"></iframe>
            </div>
        </div>
    </div>
    
  
</main>
<%@ include file="/footer.file"%>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/articleJS/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/articleJS/test.js"></script>
	<script src="<%=request.getContextPath()%>/js/articleJS/fourmAPI.js"></script>
	<script>
        document.querySelector('.ccc').addEventListener('click' , function(){
          alert("加入聊天室~")
          window.open("http://localhost:8081/APE/articleMain/index.html")
        })
</script>
	
</body>

</html>
 