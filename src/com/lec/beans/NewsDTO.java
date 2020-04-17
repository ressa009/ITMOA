package com.lec.beans;

public class NewsDTO {
	//매개변수
	private int news_brd_uid;
	private String news_brd_title;
	private String news_brd_content;
	private String news_brd_img;
	private int news_brd_viewcnt;
	
	// 생성자
	public NewsDTO() {
		super();
	}

	// 매개변수 생성자, 특정 뉴스 불러오기
	public NewsDTO(int news_brd_uid, String news_brd_title, String news_brd_content, String news_brd_img,
			int news_brd_viewcnt) {
		super();
		this.news_brd_uid = news_brd_uid;
		this.news_brd_title = news_brd_title;
		this.news_brd_content = news_brd_content;
		this.news_brd_img = news_brd_img;
		this.news_brd_viewcnt = news_brd_viewcnt;
	}
	
	// 관리자페이지 뉴스정보검색
	public NewsDTO(int news_brd_uid, String news_brd_title, int news_brd_viewcnt) {
		super();
		this.news_brd_uid = news_brd_uid;
		this.news_brd_title = news_brd_title;
		this.news_brd_viewcnt = news_brd_viewcnt;
	}
	
	// 사용자페이지 뉴스 검색, 뉴스 불러오기
	public NewsDTO(int news_brd_uid, String news_brd_title, String news_brd_img, int news_brd_viewcnt) {
		super();
		this.news_brd_uid = news_brd_uid;
		this.news_brd_title = news_brd_title;
		this.news_brd_img = news_brd_img;
		this.news_brd_viewcnt = news_brd_viewcnt;
	}
	
	// 게터세터
	public int getNews_brd_uid() {
		return news_brd_uid;
	}



	public String getNews_brd_title() {
		return news_brd_title;
	}

	public String getNews_brd_content() {
		return news_brd_content;
	}

	public String getNews_brd_img() {
		return news_brd_img;
	}

	public int getNews_brd_viewcnt() {
		return news_brd_viewcnt;
	}
	
	

}
