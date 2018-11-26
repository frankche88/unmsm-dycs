package unmsm.dycs.commons.infrastructure.message.firebase;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

import unmsm.dycs.commons.infrastructure.message.MessageService;
import unmsm.dycs.commons.infrastructure.message.OrderCompletedEvent;

public class FirebaseServiceImpl implements MessageService {
    
    
    private Logger logger = LoggerFactory.getLogger(FirebaseServiceImpl.class);
    
    
    private FirebaseConfiguration firebaseConf;
    
    @Inject
    public FirebaseServiceImpl(FirebaseConfiguration firebaseConf) {
        
        this.firebaseConf = firebaseConf;
        
    }

    @Override
    public void publish(OrderCompletedEvent event) {
        
        try {
            callRemoteService(event);
        } catch (IOException e) {
            logger.error("Error al enviar mensaje: ", e);
        }
    }
    
    private void callRemoteService(OrderCompletedEvent event) throws ClientProtocolException, IOException {
        
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(firebaseConf.getUrl());
            
            Message Message = new Message(firebaseConf.getReceiver(), event.buyerId);
            
            httpPost.setEntity(new StringEntity(Message.asJson()));
            
            httpPost.addHeader("Authorization", "key=" + firebaseConf.getKey());
            
            httpPost.addHeader("Content-Type","application/json");

            // Create a custom response handler
            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            String responseBody = httpclient.execute(httpPost, responseHandler);
            logger.info("----------------------------------------");
            logger.info(responseBody);
        }
    }
    
    static class Message {
        
        private Notification notification;
        
        private String to;
        
        Message(String to, Long orderId){
            
            this.to = to;
            
            notification = new Notification(orderId);
            
        }

        public Notification getNotification() {
            return notification;
        }

        public String getTo() {
            return to;
        }
        
        public String asJson() {
            ObjectMapper mapper = new ObjectMapper();
            
            try {
                return mapper.writeValueAsString(this);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error de JSON ", e);
            }
        }

        
        
        
    }
    
    static class Notification {
        
        private String title = "New order";
        private String body = "A new order was created ";
        
        private Long orderId;
        
        Notification(Long orderId) {
            this.orderId = orderId;
        }
        
        public String getTitle() {
            return title;
        }
        public String getBody() {
            return body  + orderId;
        }
        
        
        

    }
    
    
    

}

