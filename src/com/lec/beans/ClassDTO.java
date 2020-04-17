package com.lec.beans;

public class ClassDTO {
	
	//매개변수
	private int class_uid;
	private int cur_uid;
	private String cur_name;
	private int cur_hours;
	private int cur_months;
	private String cur_month1;
	private String cur_month2;
	private String cur_month3;
	private String cur_month4;
	private String cur_month5;
	private String cur_month6;
	private int zzim_uid;
	private int class_zzimcnt;
	private int ins_uid;
	private String ins_name;
	private int ins_zip;
	private String ins_add1;
	private String ins_add2;
	private String ins_tel;
	private String ins_img;
	private String ins_branch;
	private String ins_location;
	private double ins_x;
	private double ins_y;
	private int mb_uid;
	// 생성자
	public ClassDTO() {
		super();
	}

	
	
	//전체 매개변수 생성자
	public ClassDTO(String cur_name, int cur_hours, int cur_months, String cur_month1, String cur_month2,
			String cur_month3, String cur_month4, String cur_month5, String cur_month6, String ins_name, String ins_tel, String ins_img
			, double ins_x, double ins_y, int class_uid) {
		super();
		this.cur_name = cur_name;
		this.cur_hours = cur_hours;
		this.cur_months = cur_months;
		this.cur_month1 = cur_month1;
		this.cur_month2 = cur_month2;
		this.cur_month3 = cur_month3;
		this.cur_month4 = cur_month4;
		this.cur_month5 = cur_month5;
		this.cur_month6 = cur_month6;
		this.ins_name = ins_name;
		this.ins_tel = ins_tel;
		this.ins_img = ins_img;
		this.ins_x = ins_x;
		this.ins_y = ins_y;
		this.class_uid = class_uid;
	}


	//관리자 생성자 - 학원정보 
	public ClassDTO(int ins_uid, String ins_name, String ins_add1, String ins_tel) {
		super();
		this.ins_uid = ins_uid;
		this.ins_name = ins_name;
		this.ins_add1 = ins_add1;
		this.ins_tel = ins_tel;
	}
	
	

	//사용자:  학원검색에서 전체 출력 생성자
	public ClassDTO(String ins_name, String ins_branch ,String cur_name, int class_zzimcnt, String ins_img, int class_uid) {
		super();
		this.ins_name = ins_name;
		this.ins_branch = ins_branch;
		this.cur_name = cur_name;
		this.class_zzimcnt = class_zzimcnt;
		this.ins_img = ins_img;
		this.class_uid = class_uid;
	}
	
	
	//관리자 페이지 생성자2
	public ClassDTO(String ins_name, int ins_zip, String ins_add1, String ins_add2, String ins_tel, String ins_img,
			String ins_branch, String ins_location, double ins_x, double ins_y, int ins_uid) {
		super();
		this.ins_name = ins_name;
		this.ins_zip = ins_zip;
		this.ins_add1 = ins_add1;
		this.ins_add2 = ins_add2;
		this.ins_tel = ins_tel;
		this.ins_img = ins_img;
		this.ins_branch = ins_branch;
		this.ins_location = ins_location;
		this.ins_x = ins_x;
		this.ins_y = ins_y;
		this.ins_uid = ins_uid;
	}
	
	//사용자:  학원검색에서 전체 출력 생성자
	public ClassDTO(String ins_name, int ins_uid, String cur_name, int cur_hours, int class_uid, int cur_uid) {
		super();
		this.ins_name = ins_name;
		this.ins_uid = ins_uid;
		this.cur_name = cur_name;
		this.cur_hours = cur_hours;
		this.class_uid = class_uid;
		this.cur_uid = cur_uid;
	}
	
	
	//관리자자:  과정출력
		public ClassDTO(String cur_name, int cur_hours, int class_uid, int cur_uid) {
			super();
			this.cur_uid = cur_uid;
			this.cur_name = cur_name;
			this.cur_hours = cur_hours;
			this.class_uid = class_uid;
		}
		
	
	
	//관리자 학원과정 수정
	public ClassDTO(int cur_uid, String cur_name, int cur_hours, int cur_months, String cur_month1, String cur_month2,
			String cur_month3, String cur_month4, String cur_month5, String cur_month6) {
		super();
		this.cur_uid = cur_uid;
		this.cur_name = cur_name;
		this.cur_hours = cur_hours;
		this.cur_months = cur_months;
		this.cur_month1 = cur_month1;
		this.cur_month2 = cur_month2;
		this.cur_month3 = cur_month3;
		this.cur_month4 = cur_month4;
		this.cur_month5 = cur_month5;
		this.cur_month6 = cur_month6;
	}
	


	//지역 생성자
	public ClassDTO(String ins_branch) {
		super();
		this.ins_branch = ins_branch;
	}
	
	//찜 생성자
	public ClassDTO(int mb_uid, int zzim_uid) {
		super();
		this.mb_uid = mb_uid;
		this.zzim_uid = zzim_uid;
		this.class_uid = zzim_uid;

	}

	//과정목록에 학원정보용
	public ClassDTO(int ins_uid, String ins_name) {
		super();
		this.ins_uid = ins_uid;
		this.ins_name = ins_name;

	}


	//getter/setter
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

	public int getCur_hours() {
		return cur_hours;
	}

	public void setCur_hours(int cur_hours) {
		this.cur_hours = cur_hours;
	}

	public int getCur_months() {
		return cur_months;
	}

	public void setCur_months(int cur_months) {
		this.cur_months = cur_months;
	}

	public String getCur_month1() {
		return cur_month1;
	}

	public void setCur_month1(String cur_month1) {
		this.cur_month1 = cur_month1;
	}

	public String getCur_month2() {
		return cur_month2;
	}

	public void setCur_month2(String cur_month2) {
		this.cur_month2 = cur_month2;
	}

	public String getCur_month3() {
		return cur_month3;
	}

	public void setCur_month3(String cur_month3) {
		this.cur_month3 = cur_month3;
	}

	public String getCur_month4() {
		return cur_month4;
	}

	public void setCur_month4(String cur_month4) {
		this.cur_month4 = cur_month4;
	}

	public String getCur_month5() {
		return cur_month5;
	}

	public void setCur_month5(String cur_month5) {
		this.cur_month5 = cur_month5;
	}

	public String getCur_month6() {
		return cur_month6;
	}

	public void setCur_month6(String cur_month6) {
		this.cur_month6 = cur_month6;
	}

	public int getZzim_uid() {
		return zzim_uid;
	}

	public void setZzim_uid(int zzim_uid) {
		this.zzim_uid = zzim_uid;
	}

	public int getClass_zzimcnt() {
		return class_zzimcnt;
	}

	public void setClass_zzimcnt(int class_zzimcnt) {
		this.class_zzimcnt = class_zzimcnt;
	}

	public int getIns_uid() {
		return ins_uid;
	}

	public void setIns_uid(int ins_uid) {
		this.ins_uid = ins_uid;
	}

	public String getIns_name() {
		return ins_name;
	}

	public void setIns_name(String ins_name) {
		this.ins_name = ins_name;
	}

	public int getIns_zip() {
		return ins_zip;
	}

	public void setIns_zip(int ins_zip) {
		this.ins_zip = ins_zip;
	}

	public String getIns_add1() {
		return ins_add1;
	}

	public void setIns_add1(String ins_add1) {
		this.ins_add1 = ins_add1;
	}

	public String getIns_add2() {
		return ins_add2;
	}

	public void setIns_add2(String ins_add2) {
		this.ins_add2 = ins_add2;
	}

	public String getIns_tel() {
		return ins_tel;
	}

	public void setIns_tel(String ins_tel) {
		this.ins_tel = ins_tel;
	}

	public String getIns_img() {
		return ins_img;
	}

	public void setIns_img(String ins_img) {
		this.ins_img = ins_img;
	}

	public String getIns_branch() {
		return ins_branch;
	}

	public void setIns_branch(String ins_branch) {
		this.ins_branch = ins_branch;
	}

	public String getIns_location() {
		return ins_location;
	}

	public void setIns_location(String ins_location) {
		this.ins_location = ins_location;
	}
	
	public double getIns_x() {
		return ins_x;
	}
	
	public void setIns_x(double ins_x) {
		this.ins_x = ins_x;
	}
	
	public double getIns_y() {
		return ins_y;
	}
	
	public void setIns_y(double ins_y) {
		this.ins_y = ins_y;
	}

	public int getCur_uid() {
		return cur_uid;
	}

	public void setCur_uid(int cur_uid) {
		this.cur_uid = cur_uid;
	}

	public int getMb_uid() {
		return mb_uid;
	}

	public void setMb_uid(int mb_uid) {
		this.mb_uid = mb_uid;
	}
	
}