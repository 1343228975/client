package qq.com.zl.service;


import qq.com.zl.comment.Message;
import qq.com.zl.comment.MessageType;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author zhaolun
 * @version 1.0
 * @item xxx
 */
public class ColientConectServerThred  extends Thread{
    private Socket socket;
    public ColientConectServerThred(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true){
        try {
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Message message = (Message)objectInputStream.readObject();
            if (message.getMessageType().equals(MessageType.MESSAGE_SET_ONLINE)){
                System.out.println("\n-----------------在线用户----------------");
                System.out.println(message.getContent());
                System.out.println("----------------------------------------");
            }else if (message.getMessageType().equals(MessageType.MESSAGE_COMM_MES))
            {
                System.out.println("\n---------------你收到了一条消息---------------------");
                System.out.println(message.getContent());
                System.out.println("--------------------------------------------------");
            }else if (message.getMessageType().equals(MessageType.MESSAGE_ALL_MES)){
                System.out.println("\n---------------你收到了一条群发消息---------------------");
                System.out.println(message.getContent());
                System.out.println("--------------------------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }}

    }

    public Socket getSocket() {
        return socket;
    }
}
