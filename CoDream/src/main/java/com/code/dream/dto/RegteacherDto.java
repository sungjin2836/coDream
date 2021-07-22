package com.code.dream.dto;

public class RegteacherDto {

	private String te_seq;
	private String te_title;
	private String userid;
	private String te_content;
	private String file_gid;
	private String te_admit;
	public String getTe_seq() {
		return te_seq;
	}
	public void setTe_seq(String te_seq) {
		this.te_seq = te_seq;
	}
	public String getTe_title() {
		return te_title;
	}
	public void setTe_title(String te_title) {
		this.te_title = te_title;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTe_content() {
		return te_content;
	}
	public void setTe_content(String te_content) {
		this.te_content = te_content;
	}
	public String getFile_gid() {
		return file_gid;
	}
	public void setFile_gid(String file_gid) {
		this.file_gid = file_gid;
	}
	public String getTe_admit() {
		return te_admit;
	}
	public void setTe_admit(String te_admit) {
		this.te_admit = te_admit;
	}
	@Override
	public String toString() {
		return "RegteacherDto [te_seq=" + te_seq + ", te_title=" + te_title + ", userid=" + userid + ", te_content="
				+ te_content + ", file_gid=" + file_gid + ", te_admit=" + te_admit + "]";
	}
}
