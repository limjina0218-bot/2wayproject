package com.example.twoway_movie.Repository;

import com.example.twoway_movie.DTO.BoardDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(BoardDTO dto) {
        String sql = """
        INSERT INTO TWOWAY_MOVIE_BOARD
        (bname, bdate, bmemo, bcategory)
        VALUES (?, SYSDATE, ?, ?)
    """;
        jdbcTemplate.update(
                sql,
                dto.getBname(),
                dto.getBmemo(),
                dto.getBcategory()
        );
    }


    public List<BoardDTO> selectAll() {
        String sql = "SELECT * FROM TWOWAY_MOVIE_BOARD ORDER BY bbunho DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new BoardDTO(
                rs.getLong("bbunho"),
                rs.getString("bname"),
                rs.getDate("bdate"),
                rs.getString("bmemo"),
                rs.getString("breply"),
                rs.getString("bcategory")
        ));
    }

    public BoardDTO selectOne(Long bbunho) {
        String sql = "SELECT * FROM TWOWAY_MOVIE_BOARD WHERE bbunho = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new BoardDTO(
                rs.getLong("bbunho"),
                rs.getString("bname"),
                rs.getDate("bdate"),
                rs.getString("bmemo"),
                rs.getString("breply"),
                rs.getString("bcategory")
        ), bbunho);
    }

    public void update(BoardDTO dto) {
        String sql = """
            UPDATE TWOWAY_MOVIE_BOARD
            SET bname = ?, bmemo = ?
            WHERE bbunho = ?
        """;
        jdbcTemplate.update(sql,
                dto.getBname(),
                dto.getBmemo(),
                dto.getBbunho());
    }

    public void delete(Long bbunho) {
        String sql = "DELETE FROM TWOWAY_MOVIE_BOARD WHERE bbunho = ?";
        jdbcTemplate.update(sql, bbunho);
    }
    public void updateReply(Long bbunho, String breply) {
        String sql = """
        UPDATE TWOWAY_MOVIE_BOARD
        SET breply = ?
        WHERE bbunho = ?
    """;
        jdbcTemplate.update(sql, breply, bbunho);
    }
    public List<BoardDTO> selectByCategory(String category) {
        String sql = """
        SELECT * FROM TWOWAY_MOVIE_BOARD
        WHERE bcategory = ?
        ORDER BY bbunho DESC
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new BoardDTO(
                        rs.getLong("bbunho"),
                        rs.getString("bname"),
                        rs.getDate("bdate"),
                        rs.getString("bmemo"),
                        rs.getString("breply"),
                        rs.getString("bcategory")
                ), category
        );
    }
    public List<BoardDTO> selectByCategoryPaging(
            String category, int start, int end) {

        String sql = """
        SELECT *
        FROM (
            SELECT t.*, ROW_NUMBER() OVER (ORDER BY bbunho DESC) rn
            FROM TWOWAY_MOVIE_BOARD t
            WHERE bcategory = ?
        )
        WHERE rn BETWEEN ? AND ?
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new BoardDTO(
                        rs.getLong("bbunho"),
                        rs.getString("bname"),
                        rs.getDate("bdate"),
                        rs.getString("bmemo"),
                        rs.getString("breply"),
                        rs.getString("bcategory")
                ), category, start, end
        );
    }
    public List<BoardDTO> selectAllPaging(int start, int end) {

        String sql = """
        SELECT *
        FROM (
            SELECT t.*, ROW_NUMBER() OVER (ORDER BY bbunho DESC) rn
            FROM TWOWAY_MOVIE_BOARD t
        )
        WHERE rn BETWEEN ? AND ?
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new BoardDTO(
                        rs.getLong("bbunho"),
                        rs.getString("bname"),
                        rs.getDate("bdate"),
                        rs.getString("bmemo"),
                        rs.getString("breply"),
                        rs.getString("bcategory")
                ), start, end
        );
    }
    public int countAll() {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM TWOWAY_MOVIE_BOARD",
                Integer.class
        );
    }

    public int countByCategory(String category) {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM TWOWAY_MOVIE_BOARD WHERE bcategory = ?",
                Integer.class,
                category
        );
    }

}
