package com.code.dream.dto;

public class OAuthDto {

	private String id;
	private String naver;
	private String kakao;
	private String google;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNaver() {
		return naver;
	}
	public void setNaver(String naver) {
		this.naver = naver;
	}
	public String getKakao() {
		return kakao;
	}
	public void setKakao(String kakao) {
		this.kakao = kakao;
	}
	public String getGoogle() {
		return google;
	}
	public void setGoogle(String google) {
		this.google = google;
	}
	@Override
	public String toString() {
		return "OAuthDto [id=" + id + ", naver=" + naver + ", kakao=" + kakao + ", google=" + google + "]";
	}
	
	
}
