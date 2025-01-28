package co.onlysystems.transacciones.RabbitMq.listener;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountListener {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RabbitListener(queues = "accountQueue")
    public void receiveMessage(String account) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("Content-Type", "text/event-stream");
        // Enviar el mensaje a través de WebSocket
        System.out.println("Received <" + account + ">");
        messagingTemplate.convertAndSend("/topic/accountUpdates", account, headers);
    }
}