package com.codespring.ch02.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SampleDTOList {
    private List<SampleDTO> list;

    public SampleDTOList(){
        list = new ArrayList<>();
    }
}
