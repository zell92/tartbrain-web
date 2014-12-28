import it.uniroma3.dia.tartbrain.util.Messages;
import it.uniroma3.dia.tartbrain.util.SolrJSearcher;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;


public class search {

	public static void main(String[] args) throws SolrServerException {
		   
		SolrJSearcher s = new SolrJSearcher();
		List<String> l = s.search("europarlamentari", "24-04-2014", "25-04-2014",0);
		    for (String record:l) {
		      System.out.println(record);
		    }
		    System.out.println(s.getNegativo("europarlamentari"));
	}
}
