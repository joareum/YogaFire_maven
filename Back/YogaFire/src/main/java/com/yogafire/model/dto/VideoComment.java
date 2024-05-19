package com.yogafire.model.dto;

import java.sql.Timestamp;

public class VideoComment {
	private String videoId;
	private int vCommentId;
	private String vCommentContent;
	private Timestamp vCommentReg;
	private String deleteYn;
	private String userId;
	
	public VideoComment() {
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public int getvCommentId() {
		return vCommentId;
	}

	public void setvCommentId(int vCommentId) {
		this.vCommentId = vCommentId;
	}

	public String getvCommentContent() {
		return vCommentContent;
	}

	public void setvCommentContent(String vCommentContent) {
		this.vCommentContent = vCommentContent;
	}

	public Timestamp getvCommentReg() {
		return vCommentReg;
	}

	public void setvCommentReg(Timestamp vCommentReg) {
		this.vCommentReg = vCommentReg;
	}

	public String getDeleteYn() {
		return deleteYn;
	}

	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "VideoComment [videoId=" + videoId + ", vCommentId=" + vCommentId + ", vCommentContent="
				+ vCommentContent + ", vCommentReg=" + vCommentReg + ", deleteYn=" + deleteYn + ", userId=" + userId
				+ "]";
	}
	
}
