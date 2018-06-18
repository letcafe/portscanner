package com.letcafe.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.letcafe.utils.SocketUtils.bufferedWriter;
import static com.letcafe.utils.SocketUtils.testServerPortIsOpen;

public class MultiThreads extends Thread {
    public static int count = 0;
    private List<String> searchList = new ArrayList<>();
    public int searchIndex;

    public List<String> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<String> searchList) {
        this.searchList = searchList;
    }

    private String address;
    private Integer checkPort;

    public MultiThreads(Integer checkPort) {
        this.checkPort = checkPort;
    }

    public MultiThreads(String address, Integer checkPort) {
        this.address = address;
        this.checkPort = checkPort;
    }

    @Override
    public void run() {
        try {
        for(int i = 0; i <= 255; i ++) {
            System.out.println("[ip scanner to] = " + this.address + i + ".XXX");
            for(int j = 0; j <= 255 && searchIndex == i % 8; j ++) {
                String nowIpAddress =  this.address + i + "." + j;
                if (testServerPortIsOpen(nowIpAddress, this.checkPort, 10)) {
                    System.out.println("---------------------------:" + nowIpAddress + ":" + this.checkPort + " open");
                    bufferedWriter.write(nowIpAddress + ":" + this.checkPort);
                        bufferedWriter.newLine();
                }
                count ++;
            }
            bufferedWriter.flush();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        SocketUtils.latch.countDown();
    }

    @Override
    public String toString() {
        return "MultiThreads{" +
                "address='" + address + '\'' +
                ", startPort=" + checkPort +
                ", checkPort=" + checkPort +
                '}';
    }

    @Override
    public synchronized void start() {
        super.start();
    }
}
