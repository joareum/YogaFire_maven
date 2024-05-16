package com.yogafire.model.dto;

import java.time.LocalTime;

public class Center {
	private String centerName;
	private String centerDetail;
	private String centerAddress;
	private String centerImg;
	private int centerPrice;
	private int videoKey;
	private String centerLocation;
	private String centerNumber;
	private LocalTime centerTime;

	public Center() {
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getCenterDetail() {
		return centerDetail;
	}

	public void setCenterDetail(String centerDetail) {
		this.centerDetail = centerDetail;
	}

	public String getCenterAddress() {
		return centerAddress;
	}

	public void setCenterAddress(String centerAddress) {
		this.centerAddress = centerAddress;
	}

	public String getCenterImg() {
		return centerImg;
	}

	public void setCenterImg(String centerImg) {
		this.centerImg = centerImg;
	}

	public int getCenterPrice() {
		return centerPrice;
	}

	public void setCenterPrice(int centerPrice) {
		this.centerPrice = centerPrice;
	}

	public int getVideoKey() {
		return videoKey;
	}

	public void setVideoKey(int videoKey) {
		this.videoKey = videoKey;
	}

	public String getCenterLocation() {
		return centerLocation;
	}

	public void setCenterLocation(String centerLocation) {
		this.centerLocation = centerLocation;
	}

	public String getCenterNumber() {
		return centerNumber;
	}

	public void setCenterNumber(String centerNumber) {
		this.centerNumber = centerNumber;
	}

	public LocalTime getCenterTime() {
		return centerTime;
	}

	public void setCenterTime(LocalTime centerTime) {
		this.centerTime = centerTime;
	}

	@Override
	public String toString() {
		return "Center [centerName=" + centerName + ", centerDetail=" + centerDetail + ", centerAddress="
				+ centerAddress + ", centerImg=" + centerImg + ", centerPrice=" + centerPrice + ", videoKey=" + videoKey
				+ ", centerLocation=" + centerLocation + ", centerNumber=" + centerNumber + ", centerTime=" + centerTime
				+ "]";
	}
	
}
