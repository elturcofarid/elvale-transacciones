package co.onlysystems.transacciones.RabbitMq.service;

public interface IMessageProducer {

    void sendMessage(String message);

}