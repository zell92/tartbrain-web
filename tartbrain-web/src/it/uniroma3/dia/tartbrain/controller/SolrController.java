package it.uniroma3.dia.tartbrain.controller;


import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.solr.client.solrj.SolrServerException;

import it.uniroma3.dia.tartbrain.solr.facade.SolrFacade;

/**
 * Session Bean implementation class SolrController
 */
@ManagedBean
@SessionScoped
public class SolrController {
	
	private String keyword;
	private String user;
	private String dateStart;
	private String dateEnd;
	private List<String> results; 
	
	@EJB
	private SolrFacade solrFacade;

  public String searchKeyword() throws SolrServerException{
	  results= solrFacade.search(this.keyword);
	  return "results";
	  
  }

public String getKeyword() {
	return keyword;
}

public void setKeyword(String keyword) {
	this.keyword = keyword;
}

public String getUser() {
	return user;
}

public void setUser(String user) {
	this.user = user;
}

public String getDateStart() {
	return dateStart;
}

public void setDateStart(String dateStart) {
	this.dateStart = dateStart;
}

public String getDateEnd() {
	return dateEnd;
}

public void setDateEnd(String dateEnd) {
	this.dateEnd = dateEnd;
}

public List<String> getResults() {
	return results;
}

public void setResults(List<String> results) {
	this.results = results;
}

public SolrFacade getSolrFacade() {
	return solrFacade;
}

public void setSolrFacade(SolrFacade solrFacade) {
	this.solrFacade = solrFacade;
}

}
