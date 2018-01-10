package ch.silberruecken.paasdemo;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author christoph.huber
 * @since 10.01.2018
 */
@RestController
@RequestMapping("/messaging")
@AllArgsConstructor
public class MessagingController {
    private final RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public void sendMessage(@RequestParam String message) {
        rabbitTemplate.convertAndSend(message);
    }

    @GetMapping("/receive")
    public String receiveMessage() {
        return (String) rabbitTemplate.receiveAndConvert();
    }
}
