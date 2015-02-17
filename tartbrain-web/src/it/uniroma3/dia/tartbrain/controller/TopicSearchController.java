package it.uniroma3.dia.tartbrain.controller;





import java.io.IOException;
import java.text.ParseException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.apache.solr.client.solrj.SolrServerException;

import twitter4j.examples.search.RecursiveTopicSearch;



@ManagedBean
@ApplicationScoped
public class TopicSearchController {

	private String keyword; 	
	private int depth;
	private boolean searching=false;
	private String messaggio="";



	public String redirect(){
		return "topicSearch";

	}


	public void search() throws SolrServerException, IOException, InterruptedException, ParseException{
		this.searching=true;
	
		this.messaggio="ricerca in corso...";
		
		new Thread(cerca()).start();

		this.searching=false;
		this.messaggio="";
	}

	private Runnable cerca() throws SolrServerException, IOException, InterruptedException{
		RecursiveTopicSearch.search(this.keyword, this.depth);
		return null;
		
	}

	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}



	public String getMessaggio() {
		return messaggio;
	}


	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}




	public boolean getSearching() {
		return searching;
	}


	public void setSearching(boolean searching) {
		this.searching = searching;
	}



}