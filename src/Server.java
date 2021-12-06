import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket servSocket = new ServerSocket(23444)) {
            while (true) {
                try (Socket socket = servSocket.accept();
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new
                             InputStreamReader(socket.getInputStream()))) {
                    String line;
                    long numberN;
                    while ((line = in.readLine()) != null) {
                        if (line.equals("end")) {
                            break;
                        } else
                            numberN = Long.parseLong(line);
                        if (numberN == 0) {
                            out.println(numberN + "-ное число Фибоначчи равно 0");
                        } else if (numberN == 1) {
                            out.println(numberN + "-ное число Фибоначчи равно 1");
                        } else {
                            long a = 0;
                            long b = 1;
                            for (long i = 2; i <= numberN; ++i) {
                                long next = a + b;
                                a = b;
                                b = next;
                            }
                            out.println(numberN + "-ное число Фибоначчи равно " + b);
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace(System.out);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
