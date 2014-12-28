package it.uniroma3.dia.tartbrain.util;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import it.uniroma3.dia.tartbrain.solr.facade.SolrFacade;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SolrJSearcher {

	private SolrFacade solrFacade;

	public SolrJSearcher() {
		this.solrFacade=new SolrFacade();
	}


	public List<String> search(String keyword) throws SolrServerException {

		return solrFacade.search(keyword);

	}


	public List<String> search(String keyword, int nRecord) throws SolrServerException {


		return solrFacade.search(keyword,nRecord);

	}


	public List<String> search (String keyword, String dateStart, String dateEnd) throws SolrServerException{

		return solrFacade.search(keyword, this.dateFormatter(dateStart), this.dateFormatter(dateEnd));
	}

	public List<String> search (String keyword, String dateStart, String dateEnd, int nRecord) throws SolrServerException{
		
		return solrFacade.search(keyword, this.dateFormatter(dateStart), this.dateFormatter(dateEnd), nRecord);

	}

	public List<String> search (String keyword, String user, String dateStart, String dateEnd) throws SolrServerException{
	return solrFacade.search(keyword, user,this.dateFormatter(dateStart), this.dateFormatter(dateEnd));
	}

	public List<String> search (String keyword, String user, String dateStart, String dateEnd, int nRecord) throws SolrServerException{
		return solrFacade.search(keyword, user, this.dateFormatter(dateStart), this.dateFormatter(dateEnd), nRecord);
	}

	public List<String> searchByUser (String user) throws SolrServerException{
		return solrFacade.searchByUser(user);
	}

	public List<String> searchByUser (String user, int nRecord) throws SolrServerException{
		return solrFacade.searchByUser(user, nRecord);
	}

	public List<String> searchByUser (String user, String dateStart, String dateEnd) throws SolrServerException{
		return solrFacade.searchByUser(user, this.dateFormatter(dateStart), this.dateFormatter(dateEnd));
	}

	public List<String> searchByUser (String user, String dateStart, String dateEnd, int nRecord) throws SolrServerException{
		return solrFacade.searchByUser(user, this.dateFormatter(dateStart), this.dateFormatter(dateEnd), nRecord);
	}

	public long getPositivo (String keyword) throws SolrServerException{
		return solrFacade.getPositivo(keyword);
	}

	public long getPositivo (String keyword, String user) throws SolrServerException{
		return solrFacade.getPositivo(keyword, user);
	}


	public long getPositivo (String keyword, String user, String  dateStart, String dateEnd) throws SolrServerException{
		return solrFacade.getPositivo(keyword, user, this.dateFormatter(dateStart), this.dateFormatter(dateEnd));
	}


	public long getPositivo (String keyword, String  dateStart, String dateEnd) throws SolrServerException{
		return solrFacade.getPositivo(keyword, this.dateFormatter(dateStart), this.dateFormatter(dateEnd));
	}

	public long getNegativo (String keyword)throws SolrServerException{
		return solrFacade.getNegativo(keyword);
	}

	public long getNegativo (String keyword, String user)throws SolrServerException{
		return solrFacade.getNegativo(keyword, user);
	}

	public long getNegativo (String keyword, String user, String  dateStart, String dateEnd)throws SolrServerException{
		return solrFacade.getNegativo(keyword, user, this.dateFormatter(dateStart), this.dateFormatter(dateEnd));
	}

	public long getNegativo (String keyword, String  dateStart, String dateEnd)throws SolrServerException{
		return solrFacade.getNegativo(keyword, this.dateFormatter(dateStart), this.dateFormatter(dateEnd));
	}

	public long getNeutro (String keyword)throws SolrServerException{
		return solrFacade.getNeutro(keyword);
	}

	public long getNeutro (String keyword, String user)throws SolrServerException{
		return solrFacade.getNeutro(keyword, user);
	}

	public long getNeutro (String keyword, String user, String  dateStart, String dateEnd)throws SolrServerException{
		return solrFacade.getNeutro(keyword, user, this.dateFormatter(dateStart), this.dateFormatter(dateEnd));
	}

	public long getNeutro (String keyword, String  dateStart, String dateEnd)throws SolrServerException{
		return solrFacade.getNeutro(keyword, this.dateFormatter(dateStart), this.dateFormatter(dateEnd));
	}


	
	private static String dateFormatter(String dateStr) {
	    SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	    String dateFormat = "dd-MM-yyyy"; 
	        try {
	            return out.format(new SimpleDateFormat(dateFormat).parse(dateStr));
	        } catch (ParseException ignore) { }
	    throw new IllegalArgumentException("Invalid date: " + dateStr);
	}

}
