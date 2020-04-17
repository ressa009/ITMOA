package com.lec.beans;

public class MbDTO {
	
	//매개변수
	private int mb_uid;
	private String mb_name;
	private String mb_id;
	private String mb_pw;
	private String mb_add1;
	private String mb_add2;
	private int mb_zip;
	private String mb_email;
	private int mb_level;
	private String mb_regdate;
	private String mb_img;
	private int zzim_uid;
	private String ins_name;
	private String cur_name;
	
	// 생성자
	public MbDTO() {
		super();
	}

	// 매개변수 생성자
	public MbDTO(int mb_uid, String mb_name, String mb_id, String mb_pw, String mb_add1, String mb_add2, int mb_zip,
			String mb_email, int mb_level, String mb_regdate, String mb_img, int zzim_uid, String ins_name,
			String cur_name) {
		super();
		this.mb_uid = mb_uid;
		this.mb_name = mb_name;
		this.mb_id = mb_id;
		this.mb_pw = mb_pw;
		this.mb_add1 = mb_add1;
		this.mb_add2 = mb_add2;
		this.mb_zip = mb_zip;
		this.mb_email = mb_email;
		this.mb_level = mb_level;
		this.mb_regdate = mb_regdate;
		this.mb_img = mb_img;
		this.zzim_uid = zzim_uid;
		this.ins_name = ins_name;
		this.cur_name = cur_name;
	}
	
	// MbDAO
	// 로그인
	public MbDTO(int mb_uid, String mb_id, String mb_pw, int mb_level, String mb_img) {
		this.mb_uid = mb_uid;
		this.mb_id = mb_id;
		this.mb_pw = mb_pw;
		this.mb_level = mb_level;
		this.mb_img = mb_img;
	}
	
	// 마이페이지 - 1.회원정보
	public MbDTO(String mb_name, int mb_uid, String mb_id, String mb_pw, String mb_email, int mb_zip, String mb_add1, String mb_add2, String mb_img) {
		this.mb_name = mb_name;
		this.mb_uid = mb_uid;
		this.mb_pw = mb_pw;
		this.mb_email = mb_email;
		this.mb_zip = mb_zip;
		this.mb_add1 = mb_add1;
		this.mb_add2 = mb_add2;
		this.mb_img = mb_img;
	}
	
	
	// 마이페이지 - 2. 찜
		public MbDTO(int zzim_uid, String ins_name, String cur_name) {
	
			this.zzim_uid = zzim_uid;
			this.ins_name = ins_name;
			this.cur_name = cur_name;
			
		}
		

	// AdminMbDAO
	// 관리자페이지 회원정보검색
	public MbDTO(int mb_uid, String mb_name, String mb_id, String mb_email, String mb_add1, String mb_add2,
			String mb_regdate) {
		super();
		this.mb_uid = mb_uid;
		this.mb_name = mb_name;
		this.mb_id = mb_id;
		this.mb_email = mb_email;
		this.mb_add1 = mb_add1;
		this.mb_add2 = mb_add2;
		this.mb_regdate = mb_regdate;
	}

	// 관리자페이지 회원상세정보(수정. 회원번호 눌렀을 때)
	public MbDTO(int mb_uid, String mb_id, String mb_pw, String mb_name, String mb_email, int mb_level, int mb_zip, String mb_add1, String mb_add2,
			String mb_img) {
		super();
		this.mb_uid = mb_uid;
		this.mb_id = mb_id;
		this.mb_pw = mb_pw;
		this.mb_name = mb_name;
		this.mb_add1 = mb_add1;
		this.mb_add2 = mb_add2;
		this.mb_level = mb_level;
		this.mb_zip = mb_zip;
		this.mb_email = mb_email;
		this.mb_img = mb_img;
	}
	
	// 회원정보찾기 - ID 
	public MbDTO(String mb_id, String mb_name, String mb_email) {
		super();
		this.mb_id = mb_id;
		this.mb_name = mb_name;
		this.mb_email = mb_email;
	}
	
	// 회원정보찾기 - PW
	public MbDTO(String mb_id, String mb_name, String mb_email, String mb_pw) {
		super();
		this.mb_id = mb_id;
		this.mb_name = mb_name;
		this.mb_email = mb_email;
		this.mb_pw = mb_pw;
	}
	
	public MbDTO(int mb_uid, String mb_id, String mb_pw, int mb_zip, String mb_add1, String mb_add2,
			String mb_img) {
		this.mb_id = mb_id;
		this.mb_pw = mb_pw;
		this.mb_zip = mb_zip;
		this.mb_add1 = mb_add1;
		this.mb_add2 = mb_add2;
	}

	// 게터세터
	public int getMb_uid() {
		return mb_uid;
	}


	public void setMb_uid(int mb_uid) {
		this.mb_uid = mb_uid;
	}

	public String getMb_name() {
		return mb_name;
	}

	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}

	public String getMb_id() {
		return mb_id;
	}

	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}

	public String getMb_pw() {
		return mb_pw;
	}

	public void setMb_pw(String mb_pw) {
		this.mb_pw = mb_pw;
	}

	public String getMb_add1() {
		return mb_add1;
	}

	public void setMb_add1(String mb_add1) {
		this.mb_add1 = mb_add1;
	}

	public String getMb_add2() {
		return mb_add2;
	}

	public void setMb_add2(String mb_add2) {
		this.mb_add2 = mb_add2;
	}

	public int getMb_zip() {
		return mb_zip;
	}

	public void setMb_zip(int mb_zip) {
		this.mb_zip = mb_zip;
	}

	public String getMb_email() {
		return mb_email;
	}

	public void setMb_email(String mb_email) {
		this.mb_email = mb_email;
	}

	public int getMb_level() {
		return mb_level;
	}

	public void setMb_level(int mb_level) {
		this.mb_level = mb_level;
	}

	public String getMb_regdate() {
		return mb_regdate;
	}

	public void setRegdate(String mb_regdate) {
		this.mb_regdate = mb_regdate;
	}

	public String getMb_img() {
		return mb_img;
	}

	public void setMb_img(String mb_img) {
		this.mb_img = mb_img;
	}

	public int getZzim_uid() {
		return zzim_uid;
	}

	public void setZzim_uid(int zzim_uid) {
		this.zzim_uid = zzim_uid;
	}

	public String getIns_name() {
		return ins_name;
	}

	public void setIns_name(String ins_name) {
		this.ins_name = ins_name;
	}

	public String getCur_name() {
		return cur_name;
	}

	public void setCur_name(String cur_name) {
		this.cur_name = cur_name;
	}

	
}
