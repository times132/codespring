package com.codespring.ch03.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
@ToString
public class Criteria {
    private int pageNum;
    private int amount;

    private String type;
    private String keyword;

    public Criteria(){
        this(1, 10);
    }

    public Criteria(int pageNum, int amount){
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public int getPageStart(){
        return (this.pageNum-1) * amount;
    }

    public String[] getTypeArr() {
        return type == null? new String[] {} : type.split("");
    }

    public String getListLink(){
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("pageNum", getPageNum())
                .queryParam("amount", getAmount())
                .queryParam("type", getType())
                .queryParam("keyword", getKeyword());

        return builder.toUriString();
    }
}
