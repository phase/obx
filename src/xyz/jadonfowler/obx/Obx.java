package xyz.jadonfowler.obx;

import java.io.*;

public class Obx {
    private static Obx instance;

    public static void main(String[] args) {
        instance = new Obx();
        String output = instance.runFile(args[0], args);
        System.out.println(output);
    }

    public static Obx getInstance() {
        return instance;
    }

    public String runFile(String fileName, String[] args) {
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                new Function(line);
            }
            reader.close();
            int functionSize = Function.getFunctions().size();
            Object x = args.length > 1 ? args[1] : null;
            Object y = args.length > 2 ? args[2] : null;
            Object z = args.length > 3 ? args[3] : null;
            Object o = Function.getFunctions().get((char) ('A' + functionSize - 1)).run(x, y, z);
            return o.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}