package com.mycompany.client.control;

import com.mycompany.client.model.ClientState;
import com.mycompany.client.view.MainPanel;
import com.mycompany.shared.Message;
import com.mycompany.shared.Player;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientSocket {

    private Socket clientSocket;

    private ObjectOutputStream objOut;
    private ObjectInputStream objIn;

    private final String serverHost = "127.0.0.1";
    private final int serverPort = 8080;

    private Player player;
    private boolean status;
    private ArrayList<Player> listPlayer;
    private MainPanel mainPanel;
    private ClientState state;
    private HashMap<?, ?> data;

    // Hàng đợi để lưu trữ các thông điệp từ server
    private final BlockingQueue<Message> messageQueue;

    public ClientState getState() {
        return state;
    }

    public void setState(ClientState state) {
        this.state = state;
    }

    public HashMap<?, ?> getData() {
        return data;
    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public Player getPlayer() {
        return player;
    }
    
    public ArrayList<Player> getListPlayer() {
        return listPlayer;
    }

    public ClientSocket() {
        messageQueue = new LinkedBlockingQueue<>();
        status = true;
    }

    public Socket requestConnection() throws IOException {
        try {
            clientSocket = new Socket(serverHost, serverPort);
            this.objOut = new ObjectOutputStream(clientSocket.getOutputStream());
            this.objIn = new ObjectInputStream(clientSocket.getInputStream());

            // Khởi động thread để nhận dữ liệu từ server
            new Thread(new ListenFromServer()).start();

            // Khởi động thread để xử lý thông điệp trong hàng đợi
            new Thread(new MessageHandler()).start();
        } catch (IOException e) {
            return null;
        }
        return clientSocket;
    }

    public void disconnection() throws IOException {
        objOut.writeObject(new Message("DISCONNEC", null));
    }

    // inner class để nghe thông tin từ server gửi về
    class ListenFromServer implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    // Nhận thông điệp từ server
                    Message message = (Message) objIn.readObject();
                    // Đưa thông điệp vào hàng đợi
                    messageQueue.put(message);
                }
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
            }
        }
    }

    // inner class để xử lý thông điệp trong hàng đợi
    class MessageHandler implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    // Lấy thông điệp từ hàng đợi
                    Message message = messageQueue.take();
                    // Xử lý thông điệp
                    handleMessage(message);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    // các message nhận thụ động từ server
    private void handleMessage(Message message) {
        if ("LIST_PLAYER".equals(message.getType())) {
            listPlayer = (ArrayList<Player>) message.getContent();
            Collections.sort(listPlayer, (Player p1, Player p2) -> p1.getPlayerName().compareTo(p2.getPlayerName()));
            for (Player pl : listPlayer) {
                System.out.println(pl.getPlayerName());
            }
            if (mainPanel != null) {
                mainPanel.setListPlayer();
            }
        } else if ("LOGIN_SUCCESS".equals(message.getType())) {
            state = ClientState.LOGIN_SUCCESS;
            this.player = (Player) message.getContent();
        } else if ("LOGIN_FAILED".equals(message.getType())) {
            state = ClientState.LOGIN_FAILED;
        } else if ("HV_DUP".equals(message.getType())) {
            state = ClientState.HV_DUP;
        } else if ("NO_DUP".equals(message.getType())) {
            state = ClientState.NO_DUP;
        } else if ("THREE_HIGHEST".equals(message.getType())) {
            state = ClientState.THREE_HIGHEST;
            data = (HashMap<String, HashMap<String, String>>) message.getContent();
        } else if ("GET_RANK".equals(message.getType())) {
            state = ClientState.GET_RANK;
            data = (HashMap<String, String>) message.getContent();
        } else if ("NEW_ROOM".equals(message.getType())) {
            state = ClientState.NEW_ROOM;
        }
        // Xử lý thêm các loại thông điệp khác ở đây
//        else if ("INVITE".equals(message.getType())) {
//            String invitePlayerName = (String) message.getContent();
//            // Hiển thị thông báo mời cho người dùng
//            mainPanel.showInviteDialog(invitePlayerName);
//        }
    }

    // gửi thông điệp login
    public void Login(String username, String password) throws IOException, ClassNotFoundException {
        HashMap<String, String> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);
        try {
            objOut.writeObject(new Message("LOGIN", data));
            objOut.flush();
        } catch (IOException e) {
        }
    }

    // kiểm tra trùng lặp username, email, playerName
    public void checkDuplicates(String type, String content) {
        HashMap<String, String> data = new HashMap<>();
        data.put((type), content);
        try {
            objOut.writeObject(new Message("CHECK_DUP", data));
            objOut.flush();
        } catch (IOException e) {
        }
    }

    public void register(String username, String password, String email, String playerName) {
        HashMap<String, String> data = new HashMap<>();
        data.put("username", username);
        data.put("email", email);
        data.put("password", password);
        data.put("playerName", playerName);
        try {
            objOut.writeObject(new Message("REGISTER", data));
            objOut.flush();
        } catch (IOException e) {
        }
    }

    public void getThreeHighest() {
        try {
            objOut.writeObject(new Message("THREE_HIGHEST", null));
        } catch (IOException e) {
        }
    }

    public void getRank(String ID) {
        try {
            objOut.writeObject(new Message("GET_RANK", ID));
        } catch (IOException e) {
        }
    }

    public void newRoom() throws IOException {
        try {
            objOut.writeObject(new Message("NEW_ROOM", player));
        } catch (IOException e) {
        }
    }
}
