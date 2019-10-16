/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import com.google.gson.Gson;
import java.util.Date;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import model.messageModel;
import repos.MessageRepo;

/**
 *
 * @author sagri
 */
@ServerEndpoint("/chat")
public class socketServer {
    
    @Inject
    private MessageRepo repo;
    
    @OnOpen
    public void open(Session session){
        repo.addSession(session);
    }
    @OnClose
    public void close(Session session){
        repo.removeSession(session);
    }
    
    @OnMessage
    public void message(Session session, String msg)
    {
        Gson gson = new Gson();
        messageModel message = gson.fromJson(msg, messageModel.class);
        if(message.getAcion().equals("send")){
            message.setDate(new Date());
            repo.addMessage(message);
            sendToAll(message);
        }
      
        else if(message.getAcion().equals("list")){
           //send all the messages fromthe repo
            repo.getMessage().forEach(x-> sendToAll(x));
           
    }
    }
    
    private void sendToAll(messageModel message){
        Gson gson = new Gson();
        String json= gson.toJson(message);
        repo.getSession().forEach(x->{
            try {
                x.getBasicRemote().sendText(json);
            } catch (Exception e) {
            System.out.println(e);
            }
            
        });
   
    }
}
