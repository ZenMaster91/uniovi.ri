package elasticSearch;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.node.Node;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ElasticSearchConnection extends ElasticSearch {

	private Client client;
	private IndexResponse response;

	public ElasticSearchConnection(String clusterName, int port) {
		super(clusterName, port);
	}

	public Client getConnection() {
		return this.client;
	}

	public ElasticSearchConnection connect() {
		try {
			Client client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), super.port()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return this;
	}

	public void executeQuery(String query) {}

	@SuppressWarnings("unchecked")
	public void indexDocument(String JSONDocument) {
		HashMap<String, Object> jsonAsMap = null;
		try {
			jsonAsMap = new ObjectMapper().readValue(JSONDocument,
					new TypeReference<HashMap<String, Object>>() {});
		} catch (IOException e) {
			System.err.println("Error while parsing the json document");
			e.printStackTrace();
		}

		this.response = client
				.prepareIndex("2008-Feb-02-04", "tweet",
						((HashMap<String, Object>) jsonAsMap.get("_source"))
								.get("id_str").toString())
				.setSource(((HashMap<String, Object>) jsonAsMap.get("_source")))
				.get();
	}
}
