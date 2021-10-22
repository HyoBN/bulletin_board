package com.board.service;

import javax.transaction.Transactional;

import com.board.dto.BoardDto;
import com.board.repository.BoardRepository;
import org.springframework.stereotype.Service;


@Service
public class BoardService {
    
    private BoardRepository boardRepository;
    
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    @Transactional 
    public Long savePost(BoardDto boardDto){
        return boardRepository.save(boardDto.toEntity()).getId();
    }
}

