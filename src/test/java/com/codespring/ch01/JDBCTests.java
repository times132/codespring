package com.codespring.ch01;

import lombok.extern.log4j.Log4j;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.Assert.fail;


@Log4j
public class JDBCTests {
    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection(){
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/codespring?useSSL=false",
                "times132", "dkdldb9498")){
            log.info(con);
        }catch (Exception e){
            fail(e.getMessage());
        }
    }
}
