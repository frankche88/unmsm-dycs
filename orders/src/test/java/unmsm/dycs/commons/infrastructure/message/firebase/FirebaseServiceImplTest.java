package unmsm.dycs.commons.infrastructure.message.firebase;

import org.junit.Test;

import unmsm.dycs.commons.infrastructure.message.MessageService;
import unmsm.dycs.commons.infrastructure.message.OrderCompletedEvent;

public class FirebaseServiceImplTest {
    
    @Test
    public void  test() {
        
        FirebaseConfiguration config = new FirebaseConfiguration();
        
        config.setKey("AIzaSyBn6w8KiDpB6Fm3UPq4qzVjewjhrurRqZ0");
        
        config.setUrl("https://fcm.googleapis.com/fcm/send");
        
        config.setReceiver("dO7j0zRnQWw:APA91bFTSHJd0l1GNcsuQ3yplRqMRWM7C_nC431OFn1Dvo6ee4xUwzYyl8cBw18X6qvegA35eaN1tR83y28Z_zbbJP0Zo3F_oy0JwsqC34QTZlP_WyGtsma00hHa8JNuSg1oawlUb7bS");
        
        
        MessageService mesage = new FirebaseServiceImpl(config);
        
        mesage.publish(new OrderCompletedEvent(1l));
        
    }

}
