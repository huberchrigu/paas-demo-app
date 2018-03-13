package ch.silberruecken.paasdemo;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author christoph.huber
 * @since 19.01.2018
 */
@Configuration
@Profile("gcp")
public class GcpConfig {
    @Bean
    public Datastore cloudDatastoreService() {
        return DatastoreOptions.getDefaultInstance().getService();
    }
}
