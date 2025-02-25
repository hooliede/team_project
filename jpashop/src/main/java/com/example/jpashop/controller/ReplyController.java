package com.example.jpashop.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.jpashop.dto.ReplyDTO;
import com.example.jpashop.entity.Board;
import com.example.jpashop.entity.Member;
import com.example.jpashop.entity.Reply;
import com.example.jpashop.repository.ReplyRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reply/*")
public class ReplyController {
	@Autowired
	ReplyRepository replyRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@RequestMapping("insert")
	public ResponseEntity<String> insert(ReplyDTO dto, HttpSession session) {
		ResponseEntity<String> entity = null;
		try {
			String userid = (String) session.getAttribute("userid");
			Reply r = modelMapper.map(dto,  Reply.class);
			r.setMember(new Member(userid));
			r.setRegdate(new Date());;
			r.setBoard(new Board(dto.getBoardIdx()));
			replyRepository.save(r);
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	@RequestMapping("/delete/{idx}")
	public ResponseEntity<String> delete(@PathVariable(name = "idx") int idx) {
		ResponseEntity<String> entity = null;
		try {
			replyRepository.deleteById(idx);
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping("/detail/{idx}")
	public ModelAndView detail(@PathVariable(name = "idx") int idx, ModelAndView mav) {
		Optional<Reply> result = replyRepository.findById(idx);
		Reply dto = result.get();
		mav.setViewName("board/reply_detail");
		mav.addObject("dto", dto);
		return mav;
	}
	
	
	@RequestMapping("list")
	public ModelAndView list(@RequestParam(name = "boardIdx") int boardIdx, ModelAndView mav) {
		List<Reply> list = replyRepository.findByBoardIdx(boardIdx);
		mav.setViewName("board/reply_list");
		mav.addObject("list", list);
		return mav;
	}
	
	
	@RequestMapping("/update/{idx}")
	public ResponseEntity<String> update(@PathVariable(name = "idx") int idx, ReplyDTO dto, HttpSession session) {
		ResponseEntity<String> entity = null;
		try {
			Optional<Reply> result = replyRepository.findById(idx);
			Reply r1 = result.get();
			Reply r2 = modelMapper.map(dto,  Reply.class);
			String userid=  (String) session.getAttribute("userid");
			r2.setMember(new Member(userid));
			r2.setReplyIdx(idx);
			r2.setBoard(new Board(r1.getBoard().getIdx()));
			r2.setRegdate(r1.getRegdate());
			replyRepository.save(r2);
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
			 } catch (Exception e) {
				 e.printStackTrace();
				 entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
}

