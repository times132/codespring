package com.codespring.ch03.mapper;

import com.codespring.ch03.domain.Criteria;
import com.codespring.ch03.domain.ReplyVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyMapper {

    public int insert(ReplyVO vo);
    public ReplyVO read(Integer rno);
    public int delete(Integer rno);
    public int update(ReplyVO vo);
    public List<ReplyVO> getListWithPaging(@Param("cri") Criteria cri, @Param("bno") int bno);
    public int getCountByBno(Integer bno);
    public void deleteAll(Integer bno);
}
