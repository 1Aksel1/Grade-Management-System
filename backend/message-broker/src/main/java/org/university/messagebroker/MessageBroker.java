package org.university.messagebroker;

import org.apache.activemq.broker.BrokerService;

public class MessageBroker {

    public static void main(String[] args) throws Exception {

        BrokerService broker = new BrokerService();

        broker.addConnector("tcp://0.0.0.0:61616");
        broker.start();

    }

}
