package kr.co.jhta.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.jhta.dao.BoardDao;
import kr.co.jhta.dao.UserDao;
import kr.co.jhta.dto.Pagination;
import kr.co.jhta.vo.Board;
import kr.co.jhta.web.form.ModifyBoardForm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final UserDao userDao;
	private final BoardDao boardDao;
	
	// 새 게시글 등록하기
	public void createBoard(Board board) {
		boardDao.insertBoard(board);
	}
	
	// 게시글 목록 조회하기(페이징처리 포함)
	public Map<String, Object> getBoards(Map<String, Object> param) {
		int page = (int) param.get("page");
		
		// 전체 게시글 갯수 조회
		int totalRows = boardDao.getTotalRows(param);
		
		// Pagination객체생성 <--- page,  totalRow, 
		Pagination pagination = new Pagination(page, totalRows);
		// begin, end값 조회
		int begin = pagination.getBegin();
		int end = pagination.getEnd();
		
		// param에 begin end put
		param.put("begin", begin);
		param.put("end", end);
		List<Board> boardList = boardDao.getBoards(param);
		
		return Map.of("boardList", boardList, "pagination", pagination);
	}
	
	// 게시글 상세정보 조회하기
	public Board getBoard(int boardNo) {
		Board board = boardDao.getBoardByNo(boardNo);
		
		board.setReadCount(board.getReadCount()+1);
		boardDao.updateBoard(board); 
		
		return boardDao.getBoardByNo(boardNo);
	}
	
	// 게시글 삭제하기
	public void deleteBoard(int boardNo) {
		Board board = boardDao.getBoardByNo(boardNo);
		board.setDeleted("Y");
		
		boardDao.updateBoard(board); 
	}
	
	// 게시글 정보 수정하기
	public void modifyBoard(ModifyBoardForm form) {
		Board board = boardDao.getBoardByNo(form.getNo());
		BeanUtils.copyProperties(form, board);
		
		boardDao.updateBoard(board); 
	}

	
	
	
}
