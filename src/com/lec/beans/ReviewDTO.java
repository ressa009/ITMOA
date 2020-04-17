package com.lec.beans;

public class ReviewDTO {
	//매개변수
	private int mb_uid;
	private String mb_id;
	private String mb_name;
	private String mb_img;
	private String ins_name;
	private int review_brd_uid;
	private String review_brd_title;
	private String review_brd_content;
	private String review_brd_regdate;
	private int review_brd_viewcnt;
	private int rep_uid;
	private String rep_content;
	private String rep_regdate;
	private int class_uid;
	private String cur_name;

	// 생성자
	public ReviewDTO() {
		super();
	}

	public ReviewDTO(int mb_uid, String mb_id, String mb_name, String mb_img, String ins_name, int review_brd_uid,
			String review_brd_title, String review_brd_content, String review_brd_regdate, int review_brd_viewcnt,
			int rep_uid, String rep_content, String rep_regdate) {
		super();
		this.mb_uid = mb_uid;
		this.mb_id = mb_id;
		this.mb_name = mb_name;
		this.mb_img = mb_img;
		this.ins_name = ins_name;
		this.review_brd_uid = review_brd_uid;
		this.review_brd_title = review_brd_title;
		this.review_brd_content = review_brd_content;
		this.review_brd_regdate = review_brd_regdate;
		this.review_brd_viewcnt = review_brd_viewcnt;
		this.rep_uid = rep_uid;
		this.rep_content = rep_content;
		this.rep_regdate = rep_regdate;
	}
	
	
	
	// 매개변수 생성자(관리자 목록)
	public ReviewDTO(int review_brd_uid, String mb_id, String mb_name, String ins_name, String review_brd_regdate, String review_brd_title, int review_brd_viewcnt) {
		super();
		
		this.review_brd_uid = review_brd_uid;
		this.mb_id = mb_id;
		this.mb_name = mb_name;
		this.ins_name = ins_name;
		this.review_brd_regdate = review_brd_regdate;
		this.review_brd_title = review_brd_title;
		this.review_brd_viewcnt = review_brd_viewcnt;

	}
		
	// 매개변수 생성자(사용자 목록)
	public ReviewDTO(int review_brd_uid, String mb_id, String ins_name, String review_brd_regdate, String review_brd_title, int review_brd_viewcnt) {
		super();
		
		this.review_brd_uid = review_brd_uid;
		this.mb_id = mb_id;
		this.ins_name = ins_name;
		this.review_brd_regdate = review_brd_regdate;
		this.review_brd_title = review_brd_title;
		this.review_brd_viewcnt = review_brd_viewcnt;

	}
	
	// 매개변수 생성자(내용)
	public ReviewDTO(int review_brd_uid, String mb_id, int mb_uid, String ins_name, String review_brd_regdate, int review_brd_viewcnt, String review_brd_title, String review_brd_content) {
		super();
		
		this.review_brd_uid = review_brd_uid;
		this.mb_id = mb_id;
		this.mb_uid = mb_uid;
		this.ins_name = ins_name;
		this.review_brd_regdate = review_brd_regdate;
		this.review_brd_title = review_brd_title;
		this.review_brd_viewcnt = review_brd_viewcnt;
		this.review_brd_content = review_brd_content;

	}
	
	public ReviewDTO(int review_brd_uid, String mb_id, String ins_name, String review_brd_regdate, String review_brd_title, int review_brd_viewcnt, String review_brd_content) {
		super();
		
		this.review_brd_uid = review_brd_uid;
		this.mb_id = mb_id;
		this.ins_name = ins_name;
		this.review_brd_regdate = review_brd_regdate;
		this.review_brd_title = review_brd_title;
		this.review_brd_viewcnt = review_brd_viewcnt;
		this.review_brd_content = review_brd_content;

	}
	
	// 매개변수 생성자(댓글)
	public ReviewDTO(int review_brd_uid, int rep_uid, String mb_id, int mb_uid, String mb_img, String rep_content, String rep_regdate) {
		super();
		
		this.review_brd_uid = review_brd_uid;
		this.rep_uid = rep_uid;
		this.mb_id = mb_id;
		this.mb_uid = mb_uid;
		this.mb_img = mb_img;
		this.rep_content = rep_content;
		this.rep_regdate = rep_regdate;

	}
	
	// 학원 목록 생성자(학원 선택)
	public ReviewDTO(int class_uid, String ins_name, String cur_name) {
		super();
		this.class_uid = class_uid;
		this.ins_name = ins_name;
		this.cur_name = cur_name;
	}
		

	public int getMb_uid() {
		return mb_uid;
	}

	public void setMb_uid(int mb_uid) {
		this.mb_uid = mb_uid;
	}

	public String getMb_id() {
		return mb_id;
	}

	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}

	public String getMb_name() {
		return mb_name;
	}

	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}
	
	public String getMb_img() {
		return mb_img;
	}

	public void setMb_img(String mb_img) {
		this.mb_img = mb_img;
	}

	public String getIns_name() {
		return ins_name;
	}

	public void setIns_name(String ins_name) {
		this.ins_name = ins_name;
	}

	public int getReview_brd_uid() {
		return review_brd_uid;
	}

	public void setReview_brd_uid(int review_brd_uid) {
		this.review_brd_uid = review_brd_uid;
	}

	public String getReview_brd_title() {
		return review_brd_title;
	}

	public void setReview_brd_title(String review_brd_title) {
		this.review_brd_title = review_brd_title;
	}

	public String getReview_brd_content() {
		return review_brd_content;
	}

	public void setReview_brd_content(String review_brd_content) {
		this.review_brd_content = review_brd_content;
	}

	public String getReview_brd_regdate() {
		return review_brd_regdate;
	}

	public void setReview_brd_regdate(String review_brd_regdate) {
		this.review_brd_regdate = review_brd_regdate;
	}

	public int getReview_brd_viewcnt() {
		return review_brd_viewcnt;
	}

	public void setReview_brd_viewcnt(int review_brd_viewcnt) {
		this.review_brd_viewcnt = review_brd_viewcnt;
	}

	public int getRep_uid() {
		return rep_uid;
	}

	public void setRep_uid(int rep_uid) {
		this.rep_uid = rep_uid;
	}

	public String getRep_content() {
		return rep_content;
	}

	public void setRep_content(String rep_content) {
		this.rep_content = rep_content;
	}

	public String getRep_regdate() {
		return rep_regdate;
	}

	public void setRep_regdate(String rep_regdate) {
		this.rep_regdate = rep_regdate;
	}

	public int getClass_uid() {
		return class_uid;
	}

	public void setClass_uid(int class_uid) {
		this.class_uid = class_uid;
	}

	public String getCur_name() {
		return cur_name;
	}

	public void setCur_name(String cur_name) {
		this.cur_name = cur_name;
	}
	
	


}
