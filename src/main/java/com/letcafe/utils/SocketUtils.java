package com.letcafe.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class SocketUtils {

    public static final CountDownLatch latch=new CountDownLatch(8);
    public static File file;
    public static BufferedWriter bufferedWriter;

    static {
        file = new File("./portScannerFileResult[MySQL2].txt");
        try {
        if(!file.exists()) {
            file.createNewFile();
        }
        bufferedWriter = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        String ipAddress = "219.150.";

        MultiThreads[] multiThreads = new MultiThreads[8];
        for(int i = 0; i < multiThreads.length; i ++) {
            multiThreads[i] = new MultiThreads(3306);
            List<String> searchList = new ArrayList<>();
            for(int j = 0; j <= 255; j ++) {
                if(i == j % 8) {
                    for(int k = 0; k <= 255; k ++) {
                        searchList.add(ipAddress + j + "." + k);
                    }
                }
            }
            multiThreads[i].setSearchList(searchList);
//            multiThreads[i].setSearchList(); = i;
//            multiThreads[i].start();
        }
        int i = 0;
        for(MultiThreads multiThreads1 : multiThreads) {
            System.out.println("[index = " + (i++) + "]" + multiThreads1.getSearchList().size());
        }
        System.out.println(multiThreads[7].getSearchList());
        latch.await();
        System.out.println("MultiThreads.count = " + MultiThreads.count);
    }



    public static boolean testServerPortIsOpen(String hostname, int port, int timeout) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(hostname, port), timeout);
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
