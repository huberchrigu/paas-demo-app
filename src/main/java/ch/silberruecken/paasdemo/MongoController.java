package ch.silberruecken.paasdemo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
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
@RequestMapping("/mongo")
@AllArgsConstructor
@Profile("!gcp")
public class MongoController {
    private final MongoTemplate mongoTemplate;

    @GetMapping("/insert")
    public void insertValue(@RequestParam String value) {
        mongoTemplate.insert(new MongoObject(value));
    }

    @GetMapping("/find")
    public List<MongoObject> findValues() {
        return mongoTemplate.findAll(MongoObject.class);
    }

    @AllArgsConstructor
    @Getter
    private class MongoObject {
        private final String value;
    }
}
