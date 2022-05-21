package com.tygr.inspectors.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

@Service
public class ExecutorService {

    public String execute(String cmdStr) {

        try {
            return run(cmdStr);
        } catch (IOException e) {
            System.out.println("execute cmd error.");
        }
        return new String();
    }

    private String run(String cmdStr) throws IOException {

        StringBuilder sb = new StringBuilder();

        Runtime rt = Runtime.getRuntime();
        Process proc = rt.exec(new String[] { cmdStr, "exit;\n" });

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

        // Read the output from the command
        System.out.println("Here is the standard output of the command:\n");
        String s = null;
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
            sb.append(s + "\n");
        }

        return sb.toString();
    }
}
