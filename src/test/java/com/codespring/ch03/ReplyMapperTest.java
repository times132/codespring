package com.codespring.ch03;

import com.codespring.ch03.domain.Criteria;
import com.codespring.ch03.domain.ReplyVO;
import com.codespring.ch03.mapper.ReplyMapper;
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
public class ReplyMapperTest {

    private int[] bnoArr = {133, 132, 131, 130};

    @Setter(onMethod_ = {@Autowired})
    private ReplyMapper mapper;

    @Test
    public void TestMapper(){
        log.info(mapper);
    }

    @Test
    public void TestCreate(){
        for(int i=0; i<=90; i++){
            ReplyVO vo = new ReplyVO();

            vo.setBno(bnoArr[0]);
            vo.setReply("댓글 테스트" + i);
            vo.setReplyer("times" + i);

            mapper.insert(vo);
        }
    }

    @Test
    public void TestRead(){
        int targetRno = 2;

        ReplyVO vo = mapper.read(targetRno);

        log.info(vo);
    }

    @Test
    public void TestDelete(){
        int targetRno = 11;

        mapper.delete(targetRno);
    }

    @Test
    public void TestUpdate(){

        int targetRno = 10;

        ReplyVO vo = mapper.read(targetRno);

        vo.setReply("댓글 업데이트");

        int count = mapper.update(vo);

        log.info("UPDATE COUNT: " + count);
    }

    @Test
    public void TestList(){
        Criteria cri = new Criteria(2, 5);

        List<ReplyVO> list = mapper.getListWithPaging(cri, bnoArr[0]);

        log.info(list);
    }
}
