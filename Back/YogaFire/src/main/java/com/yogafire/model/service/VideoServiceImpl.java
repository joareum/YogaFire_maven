package com.yogafire.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yogafire.model.dao.VideoDao;
import com.yogafire.model.dto.SearchCondition;
import com.yogafire.model.dto.Video;
import com.yogafire.model.dto.VideoComment;

@Service
public class VideoServiceImpl implements VideoService {
	private final VideoDao videoDao;
	
	@Autowired
	public VideoServiceImpl(VideoDao videoDao) {
		this.videoDao = videoDao;
	}
	
	@Override
	public List<Video> getVideoList() {
		return videoDao.selectAll();
	}
	@Override
	public Video getVideo(String videoId) {
		System.out.println(videoId + "번 영상을 불러옵니다.");
		return videoDao.selectOne(videoId);
	}
	@Override
	public void uploadVideo(Video video) {
		if(videoDao.selectOne(video.getVideoId()) == null) {			
			videoDao.insertVideo(video);
			System.out.println("영상을 등록합니다.");
		}else {
			System.out.println("중복된 영상이라 등록이 불가합니다. ");
		}
	}
	@Override
	public void removeVideo(int videoKey) {
		videoDao.deleteVideo(videoKey);
		System.out.println(videoKey + "번 영상을 삭제합니다.");
	}
	@Override
	public void modifyVideo(Video video) {
		videoDao.updateVideo(video);
		System.out.println("영상을 수정합니다.");
	}
	@Override
	public List<Video> searchVideo(SearchCondition searchCondition) {
		return videoDao.searchDetail(searchCondition);
	}
	
	@Override
	public List<VideoComment> getComments(String videoId) {
		return videoDao.selectAllComment(videoId);
	}
	@Override
	public int writeComment(VideoComment videoComment) {
		return videoDao.insertVComment(videoComment);
	}
	@Override
	public void removeComment(int vCommentId) {
		videoDao.deleteVComment(vCommentId);
	}
	
	@Override
	public int editComment(VideoComment videoComment) {
		return videoDao.updateVComment(videoComment);
	}

	// 좋아요 수정 -> vLike, isLike, unLike
	@Override
	public int likeVideo(Video video) {
		return videoDao.vLike(video);
	}
	
	@Override
	public int isLikeVideo(Video video) {
		return videoDao.isLike(video);
	}

	@Override
	public int unLikeVideo(Video video) {
		return videoDao.unLike(video);
	}

	@Override
	public List<Video> getLikeList(String userId) {
		return videoDao.selectLike(userId);
	}
	
	@Override
	public List<Video> getVideosBySessionId(String sessionId) {
		return videoDao.getVideosBySessionId(sessionId);
	}
}