package socketexercise3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoServerEgen implements Runnable {

    Socket s;
    BufferedReader in;
    String echo;
    PrintWriter out;

    public EchoServerEgen(Socket soc) {
        s = soc;
    }

    @Override
    public void run() {

        try {
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);
            while (true) {
                echo = in.readLine();
                String[] array = parseCmd(echo);

                switch (array[0].toUpperCase()) {
                    case "UPPER":
                        out.println(array[1].toUpperCase());
                        break;
                    case "LOWER":
                        out.println(array[1].toLowerCase());
                        break;
                    case "REVERSE":
                        out.println(reverse(array[1]));
                        break;
                    case "TRANSLATE": 
                        out.println(translate(array[1].toLowerCase()));
                        break;
                    default:
                        s.close();
                        break;
                }

                out.println(echo);
            }
        } catch (IOException ex) {
            Logger.getLogger(EchoServerEgen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String[] parseCmd(String str) {
        String[] array = new String[2];
        int index = echo.indexOf("#");
        String cmd = echo.substring(0, index);
        String message = echo.substring(index + 1, echo.length());
        array[0] = cmd;
        array[1] = message;
        

        return array;
    }

    private String reverse(String str) {
        String reversed = "";
        for (int i = str.length(); i > 0; i++) {
            reversed += str.charAt(i);
        }

        return reversed;
    }
    
    private String translate(String str) {
        Map<String, String> words = new HashMap<>();
        words.put("hund", "dog");
        words.put("kat", "cat");
        words.put("fisk", "fish");
        String translated;
        if (words.containsKey(str)) {
            translated = words.get(str);
        } else {
            translated = "#NOT_FOUND";
        }
        
        return translated;
    }

    public static void main(String[] args) throws IOException {
        String ip = "localhost";
        int port = 4321;
        if (args.length == 2) {
            ip = args[0];
            port = Integer.parseInt(args[1]);
        }
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(ip, port));
        Socket s;
        BufferedReader in;
        String echo;
        PrintWriter out;

        while (true) {
            EchoServerEgen e = new EchoServerEgen(ss.accept());
            Thread t1 = new Thread();
            t1.start();

        }

    }

}
