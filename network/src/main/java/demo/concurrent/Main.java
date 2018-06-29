package demo.concurrent;

import demo.concurrent.share.Customer;
import demo.concurrent.share.Producer;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){

        List<String> queue = new ArrayList<String>();

        Thread producerA = new Thread(new Producer(queue));
        Thread customerA = new Thread(new Customer(queue));
        Thread customerB = new Thread(new Customer(queue));
        Thread customerC = new Thread(new Customer(queue));

        producerA.start();
        customerA.start();
        customerB.start();
        customerC.start();
    }
}
