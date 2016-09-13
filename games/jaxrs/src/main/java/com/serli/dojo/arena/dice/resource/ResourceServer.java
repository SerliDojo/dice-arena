package com.serli.dojo.arena.dice.resource;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public final class ResourceServer {

	private ResourceServer() {
	}

	public static void startResources(String address, Class<?>... classes) {
		GrizzlyHttpServerFactory.createHttpServer(
				UriBuilder.fromPath(address).build(),
				new ResourceConfig(classes));
	}

}