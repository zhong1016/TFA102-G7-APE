package com.article.model;

public class ArticleVO  implements java.io.Serializable{
	
 	private	Integer ARTICLE_NO ;
	private	String ARTICLE_TIME;
	private	Boolean ARTICLE_STATUS; 
	private	String ARTICLE_CLASS; 
	private	String ARTICLE_MAIN; 
	private	String ARTICLE ; 
	private	Integer COUNT; 
	private	String AREA; 
	private	Integer USER_NO; 
	
	
//	
//	
//	@Override
//	public String toString() {
////		return " ARTICLE_NO=" + ARTICLE_NO + ", ARTICLE_TIME=" + ARTICLE_TIME + ", ARTICLE_STATUS="
////				+ ARTICLE_STATUS + ", ARTICLE_CLASS=" + ARTICLE_CLASS + ", ARTICLE_MAIN=" + ARTICLE_MAIN + ", ARTICLE="
////				+ ARTICLE + ", COUNT=" + COUNT + ", AREA=" + AREA + "]";
//		
//		
//		return	 "{\"ARTICLE_NO\" :  \"" + ARTICLE_NO +"\"," + "\"ARTICLE_TIME\" : \"" +ARTICLE_TIME  +"\"," +"\"ARTICLE_STATUS\" : \"" +ARTICLE_STATUS +"\"," + "\"ARTICLE_CLASS\" : \"" +ARTICLE_CLASS +"\"," + "\"ARTICLE_MAIN\" : \"" +ARTICLE_MAIN +"\"," + "\"ARTICLE\" : \"" +ARTICLE +"\"," + "\"COUNT\" : \"" +COUNT +"\"," + "\"AREA\" : \"" +AREA +"\"}";
////				res.getWriter().write("{\"tibame\" : \""+a+"\"}");
//		 
//	}
//	
//	
	
	
	
	public Integer getARTICLE_NO() {
		return ARTICLE_NO;
	}
	public void setARTICLE_NO(Integer aRTICLE_NO) {
		this.ARTICLE_NO = aRTICLE_NO;
	}
	public String getARTICLE_TIME() {
		return ARTICLE_TIME;
	}
	public void setARTICLE_TIME(String aRTICLE_TIME) {
		this.ARTICLE_TIME = aRTICLE_TIME;
	}
	public Boolean getARTICLE_STATUS() {
		return ARTICLE_STATUS;
	}
	public void setARTICLE_STATUS(Boolean aRTICLE_STATUS) {
		this.ARTICLE_STATUS = aRTICLE_STATUS;
	}
	public String getARTICLE_CLASS() {
		return ARTICLE_CLASS;
	}
	public void setARTICLE_CLASS(String aRTICLE_CLASS) {
		this.ARTICLE_CLASS = aRTICLE_CLASS;
	}
	public String getARTICLE_MAIN() {
		return ARTICLE_MAIN;
	}
	public void setARTICLE_MAIN(String aRTICLE_MAIN) {
		this.ARTICLE_MAIN = aRTICLE_MAIN;
	}
	public String getARTICLE() {
		return ARTICLE;
	}
	public void setARTICLE(String aRTICLE) {
		this.ARTICLE = aRTICLE;
	}
	public Integer getCOUNT() {
		return COUNT;
	}
	public void setCOUNT(Integer cOUNT) {
		this.COUNT = cOUNT;
	}
	public void setAREA(String aREA) {
		this.AREA = aREA;
	}
	public String getAREA() {
		return AREA;
	}
	public Integer getUSER_NO() {
		return USER_NO;
	}
	public void setUSER_NO(Integer uSER_NO) {
		this.USER_NO = uSER_NO;
	}
	
	
	
}
