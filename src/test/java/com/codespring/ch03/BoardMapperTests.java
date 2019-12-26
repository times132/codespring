package com.codespring.ch03;

import com.codespring.ch03.domain.BoardVO;
import com.codespring.ch03.domain.Criteria;
import com.codespring.ch03.mapper.BoardMapper;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml")
@Log4j
public class BoardMapperTests {

    @Setter(onMethod_ = @Autowired)
    private BoardMapper mapper;

    @Test
    public void testGetList(){
        mapper.getList().forEach(board -> log.info(board));
    }

    @Test
    public void testInsert(){

        BoardVO board = new BoardVO();
        board.setTitle("테스트 제목");
        board.setContent("테스트 내용");
        board.setWriter("times");

        mapper.insert(board);

        log.info(board);
    }

    @Test
    public void testInsertSelectKey(){

        BoardVO board = new BoardVO();
        board.setTitle("테스트 제목");
        board.setContent("테스트 내용");
        board.setWriter("times");
        for(int i=0;i<10;i++)
            mapper.insertSelectKey(board);

        log.info(board);
    }

    @Test
    public void testRead(){
        BoardVO board = mapper.read(1);

        log.info(board);
    }

    @Test
    public void testDelete(){
        log.info(mapper.delete(18));
    }

    @Test
    public void testUpdate(){
        BoardVO board = new BoardVO();
        board.setBno(2);
        board.setTitle("수정 테스트");
        board.setContent("내용 수정");
        board.setWriter("times");

        int count = mapper.update(board);
        log.info("UPDATE COUNT: " + count);
    }

    @Test
    public void testPaging(){
        Criteria cri = new Criteria();
        cri.setPageNum(2);
        cri.setAmount(10);

        List<BoardVO> list = mapper.getListWithPaging(cri);

        list.forEach(board -> log.info(board));
    }

    @Test
    public void testSearch(){
        Criteria cri = new Criteria();
        cri.setType("TC");
        cri.setKeyword("수정");

        List<BoardVO> list = mapper.getListWithPaging(cri);

        list.forEach(board -> log.info(board));
    }
}
