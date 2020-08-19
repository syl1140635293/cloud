package com.syl.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Bean;


@Setter
@Getter
@ToString
public class Test {

    private long id;

    private String uuid;

    private String des;

    private String text;
}
