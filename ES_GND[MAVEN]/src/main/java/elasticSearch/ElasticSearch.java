package elasticSearch;

public class ElasticSearch {

	private String clusterName = "elasticsearch";
	private int port = 9200;

	public ElasticSearch( String clusterName ) {
		this.clusterName = clusterName;
	}

	public ElasticSearch( String clusterName, int port ) {
		this( clusterName );
		this.port = port;
	}

	/**
	 * @return the clusterName
	 */
	public String clusterName() {
		return clusterName;
	}

	/**
	 * @param clusterName the clusterName to set
	 */
	void clusterName( String clusterName ) {
		this.clusterName = clusterName;
	}

	/**
	 * @return the port
	 */
	public int port() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void port( int port ) {
		this.port = port;
	}

}
