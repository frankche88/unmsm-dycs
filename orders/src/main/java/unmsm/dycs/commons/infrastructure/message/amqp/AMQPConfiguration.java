package unmsm.dycs.commons.infrastructure.message.amqp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AMQPConfiguration {


	@JsonProperty
	private String mqUrl;

	/**
	 * The server-side consumer's queue that provides point-to-point semantics for
	 * stock requests.
	 */
	@JsonProperty
	private String queue = "TASK_QUEUE_NAME";

	public String getMqUrl() {
		return mqUrl;
	}

	public void setMqUrl(String mqUrl) {
		this.mqUrl = mqUrl;
	}

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	

}
