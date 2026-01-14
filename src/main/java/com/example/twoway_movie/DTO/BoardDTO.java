package com.example.twoway_movie.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long bbunho;
    private String bname;
    private Date bdate;
    private String bmemo;
    private String breply;
    private String bcategory;
}
