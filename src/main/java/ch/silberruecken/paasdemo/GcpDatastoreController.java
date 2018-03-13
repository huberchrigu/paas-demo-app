package ch.silberruecken.paasdemo;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

/**
 * @author christoph.huber
 * @since 10.01.2018
 */
@RestController
@RequestMapping("/datastore")
@Profile("gcp")
@RequiredArgsConstructor
public class GcpDatastoreController {
    private final Datastore datastore;

    @GetMapping("/put")
    public void put(@RequestParam String value) {
        Entity entity = Entity.newBuilder(getKey()).set("value", value).build();
        datastore.put(entity);
    }

    @GetMapping("/get")
    public String get() {
        return datastore.get(getKey()).getString("value");
    }

    private Key getKey() {
        return datastore.newKeyFactory().setKind("test").newKey("test");
    }
}
