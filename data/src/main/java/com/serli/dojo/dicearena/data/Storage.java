package com.serli.dojo.dicearena.data;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

public class Storage implements Closeable {

	private Node node = null;

	public void start() {
		node = NodeBuilder.nodeBuilder().local(true).node();

		Client client = node.client();
		client.admin().indices().delete(new DeleteIndexRequest(Entity.INDEX));
		client.admin().indices().create(new CreateIndexRequest(Entity.INDEX));
		client.admin().indices().putMapping(new PutMappingRequest(Entity.INDEX).source(Game.MAPPING));
		client.admin().indices().putMapping(new PutMappingRequest(Entity.INDEX).source(Account.MAPPING));
		client.admin().indices().putMapping(new PutMappingRequest(Entity.INDEX).source(Player.MAPPING));
		client.admin().indices().putMapping(new PutMappingRequest(Entity.INDEX).source(Match.MAPPING));
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
		return client.prepareIndex(Entity.INDEX, entity.getType(), entity.getId()).setSource(entity.toJsonString());
	}
}
