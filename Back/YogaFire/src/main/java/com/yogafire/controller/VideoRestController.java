package com.yogafire.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.yogafire.model.dto.SearchCondition;
import com.yogafire.model.dto.Video;
import com.yogafire.model.dto.VideoComment;
import com.yogafire.model.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@RequestMapping("/video")
@Tag(name = "VideoRestController", description = "영상 관련 기능")
@CrossOrigin(value = "*")
public class VideoRestController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private final VideoService videoService;
	@Autowired
	public VideoRestController(VideoService videoService) {
		this.videoService = videoService;
	}


	// 세션 아이디로 DB에 연결해서 영상 리스트 가져오는 메서드 --- 찜목록

	@GetMapping("/session")
	public ResponseEntity<List<Video>> getVideoBySession(@RequestParam String sessionId) {
		System.out.println("Session ID: " + sessionId);
		List<Video> videos = videoService.getVideosBySessionId(sessionId);
		return new ResponseEntity<List<Video>>(videos, HttpStatus.OK);
	}
	// 영상 리스트 전체 조회
	@GetMapping("/")
	@Operation(summary = "영상 리스트 전체 조회")
	public ResponseEntity<?> getVideoList() {
		List<Video> list = videoService.getVideoList();
		return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
	}
	// 영상 하나만 가져오기
	@GetMapping("/{videoId}")
	@Operation(summary = "영상 1개 조회")
	public ResponseEntity<Video> getVideo(@PathVariable("videoId") String videoId) {
		Video video = videoService.getVideo(videoId);
		if (video != null) {
			return new ResponseEntity<Video>(video, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	// 영상 등록
	@PostMapping("/{videoId}")
	@Operation(summary = "영상 등록")
	public ResponseEntity<?> uploadVideo(@RequestBody Video video, @PathVariable("videoId") String videoId) {
		Video getVideo = videoService.getVideo(video.getVideoId());
		if (getVideo == null) {
			videoService.uploadVideo(video);
			return new ResponseEntity<Video>(video, HttpStatus.CREATED);
		}
		else {
			System.out.println("중복으로 insert 안됨");
			return new ResponseEntity<>(HttpStatus.OK);			
		}
	}
	// 영상 삭제
	@DeleteMapping("/{videoKey}")
	@Operation(summary = "영상 삭제")
	public ResponseEntity<?> removeVideo(@PathVariable("videoKey") int videoKey) {
		videoService.removeVideo(videoKey);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	// 영상 수정
	@PutMapping("/{videoKey}")
	@Operation(summary = "영상 수정")
	public ResponseEntity<?> modifyVideo(@PathVariable("videoKey") int videoKey, @ModelAttribute Video video) {
		video.setVideoKey(videoKey);
		videoService.modifyVideo(video);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	// 영상 검색하기
	@GetMapping("/detail")
	@Operation(summary = "영상 상세 검색")
	public ResponseEntity<?> searchVideo(@ModelAttribute SearchCondition condition) {
		List<Video> list = videoService.searchVideo(condition);
		if (list == null || list.size() == 0) {
			return new ResponseEntity<>("검색된 영상이 없습니다.", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
	}
	// 영상 댓글 전체 조회
	@GetMapping("/{videoId}/comment")
	@Operation(summary = "영상 댓글 전체 조회")
	public ResponseEntity<?> getComments(@PathVariable("videoId") String videoId) {
		List<VideoComment> list = videoService.getComments(videoId);
		return new ResponseEntity<List<VideoComment>>(list, HttpStatus.OK);
	}
	// 영상 댓글 작성
	@PostMapping("/{videoId}/comment")
	@Operation(summary = "영상 댓글 작성")
	public ResponseEntity<?> writeComment(@RequestBody VideoComment videoComment,
			@PathVariable("videoId") String videoId) {
		videoComment.setVideoId(videoId);
		if (videoService.writeComment(videoComment) > 0) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	// 이거 사용	
	// 영상 댓글 삭제
	@DeleteMapping("/{videoId}/comment/{vCommentId}")
	@Operation(summary = "영상 댓글 삭제")
	public ResponseEntity<?> removeComment(@PathVariable("videoId") String videoId, @PathVariable("vCommentId") int vCommentId) {
		System.out.println(vCommentId);
		videoService.removeComment(vCommentId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	// 영상 댓글 수정
	@PutMapping("/{videoId}/comment/{vCommentId}")
	@Operation(summary = "영상 댓글 수정")
	public ResponseEntity<?> editComment(@PathVariable("videoId") String videoId,
			@PathVariable("vCommentId") int vCommentId, @RequestBody VideoComment videoComment) {
		videoComment.setvCommentId(vCommentId);
		videoComment.setVideoId(videoId);
		if (videoService.editComment(videoComment) > 0) {
			return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
		}
	}

	// 영상 찜하기
	@GetMapping("/{videoId}/like/{sessionId}")
	@Operation(summary = "영상 찜")
	public ResponseEntity<?> likeVideo(@PathVariable("videoId") String videoId, @PathVariable("sessionId") String sessionId) {
		System.out.println(sessionId);
	    if (sessionId.isEmpty()) {
	        return new ResponseEntity<>("Session ID cannot be empty", HttpStatus.BAD_REQUEST);
	    }
	    Video video = new Video();
	    video.setVideoId(videoId);
	    video.setSessionId(sessionId);
	    int res = videoService.likeVideo(video);
	    return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/{videoId}/like/{sessionId}")
	@Operation(summary = "영상 찜 삭제")
	public ResponseEntity<?> unLikeVideo(@PathVariable("videoId") String videoId, @PathVariable("sessionId") String sessionId) {
		Video video = new Video();
		video.setVideoId(videoId);
		video.setSessionId(sessionId);
		videoService.unLikeVideo(video);
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	@GetMapping("/{videoId}/isLike/{sessionId}")
	@Operation(summary = "영상 찜 count")
	public ResponseEntity<?> isLikeVideo(@PathVariable("videoId") String videoId, @PathVariable("sessionId") String sessionId) {
		Video video = new Video();
		video.setVideoId(videoId);
		video.setSessionId(sessionId);
		int res = videoService.isLikeVideo(video);
		System.out.println(res);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// 찜한 영상 조회
	@GetMapping("/{userId}/like")
	@Operation(summary = "찜한 영상 리스트 조회")
	public ResponseEntity<?> getLikeList(@PathVariable("userId") String userId) {
		List<Video> list = videoService.getLikeList(userId);
		if (list == null || list.size() == 0) {
			return new ResponseEntity<>("찜한 영상이 없습니다.", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Video>>(list, HttpStatus.OK);
	}
}