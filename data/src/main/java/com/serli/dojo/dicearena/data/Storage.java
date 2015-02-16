package com.serli.dojo.dicearena.data;

import java.io.Closeable;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

public class Storage implements Closeable {

	private Node node = null;

	public void start() throws IOException, InterruptedException, ExecutionException {
		node = NodeBuilder.nodeBuilder().node();

		Client client = node.client();
		client.admin().indices().create(new CreateIndexRequest(Entity.INDEX)
				.mapping(Game.TYPE, Entity.readMapping(Game.TYPE))
				.mapping(Account.TYPE, Entity.readMapping(Account.TYPE))
				.mapping(Player.TYPE, Entity.readMapping(Player.TYPE))
				.mapping(Match.TYPE, Entity.readMapping(Match.TYPE))).get();
	}

	public BulkResponse index(List<? extends Entity> entities) {
		BulkResponse response = null;

		if (entities != null) {
			Client client = node.client();
			BulkRequestBuilder bulkRequest = client.prepareBulk();
			entities.stream().map(entity -> prepareIndex(client, entity)).forEach(bulkRequest::add);
			response = bulkRequest.execute().actionGet();
		}

		return response;
	}

	@Override
	public void close() throws IOException {
		if (node != null) {
			node.close();
		}
	}

	private IndexRequestBuilder prepareIndex(Client client, Entity entity) {
		LocalDateTime timestamp = LocalDateTime.of(2014, 1, 1, 0, 0).plusMinutes(new Random().nextInt(365 * 24 * 60));
		return client.prepareIndex(Entity.INDEX, entity.getType(), entity.getId())
				.setTimestamp(timestamp.toString()).setSource(entity.toJsonString());
	}
}
