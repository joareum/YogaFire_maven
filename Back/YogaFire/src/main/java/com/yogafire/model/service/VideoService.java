package com.yogafire.model.service;

import java.util.List;
import java.util.Map;

import com.yogafire.model.dto.SearchCondition;
import com.yogafire.model.dto.Video;
import com.yogafire.model.dto.VideoComment;

public interface VideoService {
	
	// 영상 리스트 전체 조회
	public List<Video> getVideoList();
	
	// 영상 하나만 가져오기
	public Video getVideo(int videoKey);

	// 영상 등록
	public void uploadVideo(Video video);
	
	// 영상 삭제
	public void removeVideo(int videoKey);
	
	// 영상 수정
	public void modifyVideo(Video video);
	
	// 영상 검색하기
	public List<Video> searchVideo(SearchCondition searchCondition);
	
	
	public List<VideoComment> getComments(String videoId);
	
	// 영상 댓글 작성
	public int writeComment(VideoComment videoComment);
	
	// 영상 댓글 삭제
	public void removeComment(int vCommentId);
	
	// 영상 댓글 수정
	public int editComment(VideoComment videoComment);
	
	// 영상 찜하기
	public void likeVideo(String videoId);
	
//	// 영상 찜 해제
//	public void unlikeVideo(int videoKey);
	
	// 찜한 영상 조회
	public List<Video> getLikeList(String userId);
	
	// 세션 아이디로 찾는 찜한 영상 리스트
	public List<Video> getVideosBySessionId(String sessionId);

}
