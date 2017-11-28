package elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;

public class ElasticSearchConnection extends ElasticSearch {

	private Client client;
	private IndexResponse response;
	private String hostmane;
	private TransportClient transportClient;

	public ElasticSearchConnection() {
		super();
	}

	public ElasticSearchConnection(String clusterName) {
		super(clusterName);
	}

	public ElasticSearchConnection(String clusterName, int port) {
		super(clusterName, port);
	}

	public Client getConnection() {
		return this.client;
	}

	public ElasticSearchConnection setHostName(String hostmane) {
		this.hostmane = hostmane;
		return this;
	}

	@SuppressWarnings("resource")
	@Override
	public void connect() throws UnknownHostException {

		transportClient = new PreBuiltTransportClient(Settings.builder()
				.put("cluster.name", super.getClusterName())
				.put("client.transport.sniff", false)
				.put("client.transport.ping_timeout", 20, TimeUnit.SECONDS)
				.build());

		transportClient.addTransportAddress(new TransportAddress(
				InetAddress.getByName(this.hostmane), super.getPort()));
		
		this.client = transportClient;
	}

	public void executeQuery(String query) {

	}

	public void indexDocument(String JSONDocument) {
		BulkRequestBuilder bulkRequest = this.client.prepareBulk();
		this.response = new TweetIndex(this, bulkRequest).createIndex(JSONDocument)
				.getResponse();
		bulkRequest.get();
	}

	@Override
	public void disconnect() {
		this.client.close();
	}

	@Override
	public Client getClient() {
		return this.client;
	}
}
