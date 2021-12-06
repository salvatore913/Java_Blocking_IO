import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 23444);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {
            String numberN;
            while (true) {
                System.out.println("Введите целое положительное число для расчета...(\"end\" для завершения программы)");
                numberN = scanner.nextLine();
                out.println(numberN);
                if ("end".equals(numberN)) break;
                System.out.println("SERVER: " + in.readLine());
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
//Выбран способ взаимодействия Blocking IO, поскольку на сервер передается лишь 1 число для работы потока с ним.
//А нам же необходимо получить конечный результат этого расчета, без промежуточных результатов.
//Поэтому Non-Blocking IO в данной задачи не имеет никакого смысла, хотя тоже может быть реализован.
