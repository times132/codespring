package com.codespring.ch04.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SampleVO {

    private int mno;
    private String firstName;
    private String lastName;
}
