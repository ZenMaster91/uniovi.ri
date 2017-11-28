package elasticsearch;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;

public class ElasticSearchSearch {
	
	private Client client;
	
	public ElasticSearchSearch(Client client) {
		this.client = client;
	}
	
	public SearchResponse getHitsOf(String term) {
		return client.prepareSearch()
		  .setTypes()
		  .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		  .setPostFilter(QueryBuilders.matchQuery("text", term))
		  .execute()
		  .actionGet();
	}
	

}
