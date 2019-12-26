package com.codespring.ch03.mapper;

import com.codespring.ch03.domain.BoardVO;
import com.codespring.ch03.domain.Criteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BoardMapper {

    //@Select("select * from tbl_board where bno > 0")
    public List<BoardVO> getList();
    public List<BoardVO> getListWithPaging(Criteria cri);
    public void insert(BoardVO boardVO);
    public void insertSelectKey(BoardVO boardVO);
    public BoardVO read(Integer bno);
    public int delete(Integer bno);
    public int update(BoardVO boardVO);
    public int getTotalCount(Criteria cri);
    public void updateReplyCnt(@Param("bno") int bno, @Param("amount") int amount);
}
