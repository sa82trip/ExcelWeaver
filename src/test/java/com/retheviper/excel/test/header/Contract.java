package com.retheviper.excel.test.header;

import com.retheviper.excel.definition.annotation.Column;
import com.retheviper.excel.definition.annotation.Sheet;
import lombok.Data;

import java.util.Date;

@Data
@Sheet(dataStartIndex = 2)
public class Contract {

    @Column(position = "B")
    private String name;

    @Column(position = "C")
    private String companyPhone;

    @Column(position = "D")
    private String cellPhone;

    @Column(position = "E")
    private String homePhone;

    @Column(position = "F")
    private String email;

    @Column(position = "G")
    private Date birth;

    @Column(position = "H")
    private String address;

    @Column(position = "I")
    private String city;

    @Column(position = "J")
    private String province;

    @Column(position = "K")
    private int post;

    @Column(position = "L")
    private String memo;

}
