package com.example.twoway_movie.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "TWOWAY_MOVIE_BOARD")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "board_seq_generator"
    )
    @SequenceGenerator(
            name = "board_seq_generator",
            sequenceName = "TWOWAY_MOVIE_BOARD_SEQ",
            allocationSize = 1
    )
    private Long bbunho;   // 문의번호 (시퀀스)

    private String bname;  // 문의자명

    private LocalDate bdate; // 문의날짜

    @Column(length = 1000)
    private String bmemo;  // 문의내용
}
