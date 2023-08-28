package project;

import java.io.*;
import java.util.*;

class Project implements Serializable {
    // Project class implementation
}

class Employee implements Serializable {
    // Employee class implementation
}

class ProjectSerializer implements Serializable {
    // ProjectSerializer class implementation
}

class InterThreadCom {
    public static void main(String[] args) {
        // Create sample data
        // ... (Project, Employee, and data initialization)

        HashMap<Project, ArrayList<Employee>> hMap1 = new HashMap<>();
        HashMap<Project, ArrayList<Employee>> hMap2 = new HashMap<>();
        HashMap<Project, ArrayList<Employee>> hMap3 = new HashMap<>();
        // ... (Add data to HashMaps)

        Thread1 obj1 = new Thread1(hMap1);
        producer p1 = new producer(obj1);
        consumer c1 = new consumer(obj1);

        Thread1 obj2 = new Thread1(hMap2);
        producer p2 = new producer(obj2);
        consumer c2 = new consumer(obj2);

        Thread1 obj3 = new Thread1(hMap3);
        producer p3 = new producer(obj3);
        consumer c3 = new consumer(obj3);
    }
}

class Thread1 extends Thread {
    private HashMap<Project, ArrayList<Employee>> projectMap;

    public Thread1(HashMap<Project, ArrayList<Employee>> projectMap) {
        this.projectMap = projectMap;
    }

    public synchronized void serializeData() {
        // Serialization code
        // ...
        System.out.println("Serialize called by Producer");
        // Print serialized data
    }

    public synchronized void deserializeData() {
        // Deserialization code
        // ...
        System.out.println("DeSerialize Called by Consumer");
        // Print deserialized data
    }

    @Override
    public void run() {
        serializeData();
        try {
            wait(); // Wait for consumer thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deserializeData();
    }
}

class producer extends Thread {
    private Thread1 t;

    public producer(Thread1 t) {
        this.t = t;
        start();
    }

    @Override
    public void run() {
        synchronized (t) {
            t.notify(); // Notify waiting thread
        }
    }
}

class consumer extends Thread {
    private Thread1 t;

    public consumer(Thread1 t) {
        this.t = t;
        start();
    }

    @Override
    public void run() {
        synchronized (t) {
            // Perform any consumer-specific actions
        }
    }
}
    