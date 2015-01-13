package it.uniroma3.dia.tartbrain.util;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import java.net.MalformedURLException;

public class SolrJSearcher {
  public static void main(String[] args) throws MalformedURLException, SolrServerException {
    HttpSolrServer solr = new HttpSolrServer("http://localhost:8983/solr/#/tartbrain");

    SolrQuery query = new SolrQuery();
    query.setQuery("allItem");
    query.addFilterQuery("*:*");
    query.setFields("id","data","utente","testo","includes","sentimento","screenName","_version_");
    query.setStart(0);    
    query.set("defType", "edismax");

    QueryResponse response = solr.query(query);
    SolrDocumentList results = response.getResults();
    for (int i = 0; i < results.size(); ++i) {
      System.out.println(results.get(i));
    }
  }
}