package ch.silberruecken.paasdemo;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author christoph.huber
 * @since 10.01.2018
 */
@RestController
@RequestMapping("/mysql")
@AllArgsConstructor
public class MySqlController {
    private final JdbcTemplate jdbcTemplate;

    @GetMapping("/insert")
    public void insertValue(@RequestParam String value) {
        jdbcTemplate.update("INSERT INTO TEST(VALUE) VALUES (?)", value);
    }

    @GetMapping("/select")
    public List<String> selectValues() {
        return jdbcTemplate.queryForList("SELECT value FROM TEST", String.class);
    }
}
