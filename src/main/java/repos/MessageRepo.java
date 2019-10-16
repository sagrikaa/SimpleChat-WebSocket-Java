/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repos;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;
import model.messageModel;

/**
 *
 * @author sagri
 */
@ApplicationScoped
public class MessageRepo {
    private List<Session> sessions = new ArrayList<>();
    private List<messageModel> messages = new ArrayList<>();
    public void addSession(Session session){
        sessions.add(session);
    }
     public void removeSession(Session session){
        sessions.remove(session);
    }
      public void addMessage(messageModel msg){
        messages.add(msg);
   
      }
      
          public List<Session> getSession(){
        return sessions;
    }
       public List<messageModel> getMessage(){
        return messages;
    }
}
