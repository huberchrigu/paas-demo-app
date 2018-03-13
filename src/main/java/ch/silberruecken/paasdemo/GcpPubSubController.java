package ch.silberruecken.paasdemo;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gcp.pubsub.PubSubAdmin;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author christoph.huber
 * @since 15.01.2018
 */
@Profile("gcp")
@RestController
@RequestMapping("/messaging")
@RequiredArgsConstructor
public class GcpPubSubController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GcpPubSubController.class);

    private final PubSubTemplate pubSubTemplate;
    private final PubSubAdmin pubSubAdmin;

    private String latestMessage;

    @GetMapping("/subscribe")
    public void subscribe() {
        LOGGER.info("Add subscription...");
        pubSubTemplate.subscribe("paas-demo", (pubsubMessage, ackReplyConsumer) -> {
            LOGGER.info("Received message {}", pubsubMessage.getData());
            latestMessage = pubsubMessage.getData().toString();
            ackReplyConsumer.ack();
        });
        LOGGER.info("Topics: {}, subscriptions: {}", pubSubAdmin.listTopics(), pubSubAdmin.listSubscriptions());
    }

    @GetMapping("/publish")
    public void publish(@RequestParam String message) {
        pubSubTemplate.publish("paas-demo", message, null);
    }

    @GetMapping("/get")
    public String get() {
        return latestMessage;
    }
}
