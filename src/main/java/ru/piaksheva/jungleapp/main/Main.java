package ru.piaksheva.jungleapp.main;

import ru.piaksheva.jungleapp.entity.Tiger;
import ru.piaksheva.jungleapp.util.EventProducer;

public class Main {
    public static void main(String[] args) {
        Tiger tiger = new Tiger();
        EventProducer eventProducer = new EventProducer();
        eventProducer.startSimulation(tiger);
    }
}
