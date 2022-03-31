package qq.com.zl.service;


import qq.com.zl.comment.Message;
import qq.com.zl.comment.MessageType;
import qq.com.zl.comment.User;
import qq.com.zl.view.View;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zhaolun
 * @version 1.0
 * @item xxx
 */
public class UserClientSercive {
    private Socket socket ;
    private User user = new User();
    LocalDateTime now = LocalDateTime.now();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY年MM月dd日hh:mm:ss");
    String format = dateTimeFormatter.format(now);

    public boolean checkLogin(String usrID,String password){
        user.setUser_id(usrID);
        user.setPassword(password);
        boolean loop = false;
        try {
            socket = new Socket("127.0.0.1",9999);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream os= new ObjectOutputStream(outputStream);
            os.writeObject(user);
            //读取从服务器获取的消息
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Message message =  (Message)  objectInputStream.readObject();


            if (message.getMessageType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)){
                ColientConectServerThred colientConectServerThred = new ColientConectServerThred(socket);
                loop =  true;
                colientConectServerThred.start();
                MannerClientMessageThred.addThread(usrID,colientConectServerThred);


            }else {

                socket.close();

            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        return loop;
    }

    /**
     * 注册用户
     * @return 成功
     */
    public  boolean  checkResign(){
        boolean loop = false;
        User resign = View.resign();
        try {
            if (resign!=null){
                socket = new Socket("127.0.0.1",9999);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(resign);
                loop = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return loop;
    }

    /**
     * 请求在线列表
     */
    public void getOnline(){
        Message message = new Message();


        message.setMessageType(MessageType.MESSAGE_GET_ONLINE);
        try {
            ColientConectServerThred thread = MannerClientMessageThred.getThread(user.getUser_id());
            Socket socket = thread.getSocket();
            OutputStream outputStream =  socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(message);

            //写入
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 私聊代码
     */
    public void chatUser(String sender ,String getter,String content){
        Message message = new Message();

        message.setMessageType(MessageType.MESSAGE_COMM_MES);
        message.setSender(sender);
        message.setGetter(getter);
        message.setSendTime(format);

        message.setContent(content);

        try {
            ColientConectServerThred thread = MannerClientMessageThred.getThread(sender);
            Socket socket = thread.getSocket();
            OutputStream outputStream =  socket.getOutputStream();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(message);

            //写入
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 群发消息
     * @param sender 发送者
     * @param content 内容
     */
    public void chatAll(String sender,String content){
        Message message = new Message();
        message.setSendTime(format);
        message.setSender(sender);
        message.setMessageType(MessageType.MESSAGE_ALL_MES);
        message.setContent(content);
        ColientConectServerThred thread = MannerClientMessageThred.getThread(sender);
        Socket socket = thread.getSocket();

        try {
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
