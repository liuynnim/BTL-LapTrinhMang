package Server;


import com.mycompany.myserverpj.model.control.ConnectDB;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author quang
 */
public class Server {

    private ServerSocket server = null;
    private Socket clientSocket = null;
    private ConnectDB con = null;
    final private int port = 8080;
    
    public void openServer() {
        try {
            server = new ServerSocket(port);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
}
