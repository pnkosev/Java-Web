package javache;

import javache.http.HttpSessionStorage;
import javache.http.impl.HttpSessionStorageImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.FutureTask;

public class Server {
    private static final String LISTENING_MESSAGE = "Listening on port: ";
    private static final int SOCKET_TIMEOUT_MILLISECONDS = 5000;
    private static final String SOCKET_TIMEOUT_EXCEPTION_MESSAGE = "Socket timeout detection!";

    private int port;
    private int timeouts;

    private ServerSocket server;

    public Server(int port) {
        this.port = port;
        this.timeouts = 0;
    }

    public void run() throws IOException {
        this.server = new ServerSocket(this.port);
        System.out.println(LISTENING_MESSAGE + this.port);

        this.server.setSoTimeout(SOCKET_TIMEOUT_MILLISECONDS);

        HttpSessionStorage sessionStorage = new HttpSessionStorageImpl();

        while (true) {
            try (Socket clientSocket = this.server.accept()) {
                clientSocket.setSoTimeout(SOCKET_TIMEOUT_MILLISECONDS);

                ConnectionHandler connectionHandler = new ConnectionHandler(clientSocket, new RequestHandler(sessionStorage));
                FutureTask<?> task = new FutureTask<>(connectionHandler, null);
                task.run();
            } catch (SocketTimeoutException e) {
                System.out.println(SOCKET_TIMEOUT_EXCEPTION_MESSAGE);
                this.timeouts++;
//                e.printStackTrace();
            }
        }
    }
}
