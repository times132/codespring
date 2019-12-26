package com.codespring.ch03;

import com.codespring.ch03.domain.BoardVO;
import com.codespring.ch03.domain.Criteria;
import com.codespring.ch03.service.BoardService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml")
@Log4j
public class BoardServiceTests {

    @Setter(onMethod_ = {@Autowired})
    private BoardService service;

    @Test
    public void testExist(){
        log.info(service);
        assertNotNull(service);
    }

    @Test
    public void testRegister(){
        BoardVO board = new BoardVO();
        board.setTitle("새로 작성 하는 글");
        board.setContent("새로 작성하는 내용");
        board.setWriter("times");

        service.register(board);

        log.info(board);
    }

//    @Test
//    public void testGetList(){
//
//        service.getList().forEach(boardVO -> log.info(boardVO));
//    }

    @Test
    public void testGetList(){
        Criteria cri = new Criteria(2,10);

        service.getList(cri).forEach(boardVO -> log.info(boardVO));
//        service.getList(new Criteria(2, 10)).forEach(board -> log.info(board));
    }

    @Test
    public void testGet(){
        log.info(service.get(19));
    }

    @Test
    public void testModify(){
        BoardVO board = service.get(18);

        if (board == null){
            log.info("게시물이 존재하지 않습니다.");
            return ;
        }

        board.setTitle("비지니스 수정 테스트");
        board.setContent("비지니스 수정 내용");
        board.setWriter("times1");

        service.modify(board);

        log.info("modify result: " + board);
    }

    @Test
    public void testRemove(){
        assertNotNull(service.get(18));

        log.info(service.remove(18));
    }
}
