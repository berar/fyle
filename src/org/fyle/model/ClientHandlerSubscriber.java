package org.fyle.model;

public interface ClientHandlerSubscriber {
	void publish(ClientHandlerEvent event);
}
