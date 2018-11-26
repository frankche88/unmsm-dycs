package unmsm.dycs.commons.infrastructure.message.firebase;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FirebaseConfiguration {

    @JsonProperty
    private String url;

    @JsonProperty
    private String key;

    @JsonProperty
    private String receiver;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

}
