package xyz.jadonfowler.obx;

import java.io.*;
import java.util.*;
import xyz.jadonfowler.obx.operator.*;

public class Obx {
    private static HashMap<Character, Operator> operators = new HashMap<Character, Operator>();
    private static Obx instance;

    public static void main(String[] args) {
        instance = new Obx();
        if (args.length > 0) {
            // file mode
            String output = instance.runFile(args[0], args);
            System.out.println(output);
        }
        else {
            // REPL
            System.out.println("obx REPL");
            Scanner in = new Scanner(System.in);
            String line;
            while ((line = in.nextLine()) != null) {
                if (line.trim().equals("")) break;
                Function f = new Function(line);
                // TODO: Replace with something else
                String result = f.run(0, 1, 2).toString();
                System.out.println(result + "\n" + f.id + "(0,1,2): " + result + " " + f.getStack().toString());
            }
            in.close();
        }
    }

    public static Obx getInstance() {
        return instance;
    }

    public static HashMap<Character, Operator> getOperators() {
        return operators;
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
            Function lastFunction = Function.getFunctions().get((char) ('A' + functionSize - 1));
            Object o = lastFunction.run(x, y, z);
            return o.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}