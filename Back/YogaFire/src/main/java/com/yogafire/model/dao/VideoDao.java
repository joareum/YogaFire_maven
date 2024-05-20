package com.yogafire.model.dao;

import java.util.List;
import java.util.Map;

import com.yogafire.model.dto.SearchCondition;
import com.yogafire.model.dto.Video;
import com.yogafire.model.dto.VideoComment;

public interface VideoDao {

	public List<Video> selectAll();

	public Video selectOne(String videoKey);

	public void insertVideo(Video video);

	public void deleteVideo(int videoKey);

	public void updateVideo(Video video);

	public List<Video> searchDetail(SearchCondition searchCondition);

	public List<VideoComment> selectAllComment(String videoId);
	
	public int insertVComment(VideoComment videoComment);

	public void deleteVComment(int vCommentId);

	public int updateVComment(VideoComment videoComment);

	public void like(Map<String, Object> map);

//	public void unlike(int videoKey);

	public List<Video> selectLike(String userId);

	public List<Video> getVideosBySessionId(String sessionId);

}
