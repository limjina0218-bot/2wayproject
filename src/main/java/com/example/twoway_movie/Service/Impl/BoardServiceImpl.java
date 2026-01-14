package com.example.twoway_movie.Service.Impl;

import com.example.twoway_movie.DTO.BoardDTO;
import com.example.twoway_movie.Repository.BoardRepository;
import com.example.twoway_movie.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public void insert(BoardDTO dto) {
        boardRepository.insert(dto);
    }

    @Override
    public List<BoardDTO> selectAll() {
        return boardRepository.selectAll();
    }

    @Override
    public BoardDTO selectOne(Long bbunho) {
        return boardRepository.selectOne(bbunho);
    }

    @Override
    public void update(BoardDTO dto) {
        boardRepository.update(dto);
    }

    @Override
    public void delete(Long bbunho) {
        boardRepository.delete(bbunho);
    }

    @Override
    public void updateReply(Long bbunho, String breply) {
        boardRepository.updateReply(bbunho, breply);
    }

    @Override
    public List<BoardDTO> selectByCategory(String category) {
        return boardRepository.selectByCategory(category);
    }

    @Override
    public List<BoardDTO> pagingAll(int page, int size) {
        int start = (page - 1) * size + 1;
        int end = page * size;
        return boardRepository.selectAllPaging(start, end);
    }

    @Override
    public List<BoardDTO> pagingByCategory(String category, int page, int size) {
        int start = (page - 1) * size + 1;
        int end = page * size;
        return boardRepository.selectByCategoryPaging(category, start, end);
    }

    @Override
    public int countAll() {
        return boardRepository.countAll();
    }

    @Override
    public int countByCategory(String category) {
        return boardRepository.countByCategory(category);
    }
}
