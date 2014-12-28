package it.uniroma3.dia.tartbrain.solr.facade;

import it.uniroma3.dia.tartbrain.util.Messages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

public class SolrFacade implements ISolrFacade {


	private static HttpSolrServer solr = new HttpSolrServer(Messages.getString("SolrJSearcher.0")); //$NON-NLS-1$

	@Override
	public List<String> search(String keyword) throws SolrServerException {

		return this.search(keyword,Integer.MAX_VALUE);


	}

	@Override
	public List<String> search(String keyword, int nRecord) throws SolrServerException  {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setRows(nRecord);
		return this.getRecords(query);

	}

	@Override
	public List<String> search(String keyword,String dateStart, String dateEnd) throws SolrServerException {
		return this.search(keyword, dateStart, dateEnd, Integer.MAX_VALUE);
	}

	@Override
	public List<String> search(String keyword, String dateStart, String dateEnd,
			int nRecord) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setFilterQueries("data:["+dateStart+" TO "+dateEnd+"]");
		query.setRows(nRecord);
		return this.getRecords(query);
	}

	@Override
	public List<String> search(String keyword, String user, String dateStart,
			String dateEnd) throws SolrServerException {
		return this.search(keyword,user,dateStart,dateEnd,Integer.MAX_VALUE);
	}

	@Override
	public List<String> search(String keyword, String user, String dateStart,
			String dateEnd, int nRecord) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setFilterQueries("utente:\""+user+"\"");
		query.setFilterQueries("data:["+dateStart+" TO "+dateEnd+"]");
		query.setRows(nRecord);
		return this.getRecords(query);
	}

	@Override
	public List<String> searchByUser(String user) throws SolrServerException {
		return searchByUser(user, Integer.MAX_VALUE);
	}

	@Override
	public List<String> searchByUser(String user, int nRecord) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
		query.setRows(nRecord);
		query.setFilterQueries("utente:\""+user+"\"");
		return this.getRecords(query);
	}

	@Override
	public List<String> searchByUser(String user,String dateStart, String dateEnd) throws SolrServerException {
		return searchByUser(user, dateStart, dateEnd, Integer.MAX_VALUE);
	}

	@Override
	public List<String> searchByUser(String user, String dateStart, String dateEnd,int nRecord) throws SolrServerException{
		return search("*:*", user, dateStart, dateEnd, nRecord);
	}

	@Override
	public long getPositivo(String keyword) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setRows(0);
		query.setFilterQueries("sentimento:positivo");
		return solr.query(query).getResults().getNumFound();
	}



	@Override
	public long getPositivo(String keyword, String user) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setRows(0);
		query.setFilterQueries("sentimento:positivo");
		query.setFilterQueries("utente:\""+user+"\"");
		return solr.query(query).getResults().getNumFound();
	}

	@Override
	public long getPositivo(String keyword, String user, String dateStart, String dateEnd) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setRows(0);
		query.setFilterQueries("sentimento:positivo");
		query.setFilterQueries("utente:\""+user+"\"");
		query.setFilterQueries("data:["+dateStart+" TO "+dateEnd+"]");
		return solr.query(query).getResults().getNumFound();
	}

	@Override
	public long getPositivo(String keyword, String dateStart, String dateEnd) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setRows(0);
		query.setFilterQueries("sentimento:positivo");
		query.setFilterQueries("data:["+dateStart+" TO "+dateEnd+"]");
		return solr.query(query).getResults().getNumFound();
	}


	@Override
	public long getNegativo(String keyword)throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setRows(0);
		query.setFilterQueries("sentimento:negativo");
		return solr.query(query).getResults().getNumFound();
	}

	@Override
	public long getNegativo(String keyword, String user) throws SolrServerException{
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setRows(0);
		query.setFilterQueries("sentimento:negativo");
		query.setFilterQueries("utente:\""+user+"\"");
		return solr.query(query).getResults().getNumFound();
	}

	@Override
	public long getNegativo(String keyword, String user, String dateStart, String dateEnd)throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setRows(0);
		query.setFilterQueries("sentimento:negativo");
		query.setFilterQueries("utente:\""+user+"\"");
		query.setFilterQueries("data:["+dateStart+" TO "+dateEnd+"]");
		return solr.query(query).getResults().getNumFound();
	}

	@Override
	public long getNegativo(String keyword,String dateStart, String dateEnd) throws SolrServerException{
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setRows(0);
		query.setFilterQueries("sentimento:negativo");
		query.setFilterQueries("data:["+dateStart+" TO "+dateEnd+"]");
		return solr.query(query).getResults().getNumFound();
	}

	@Override
	public long getNeutro(String keyword) throws SolrServerException{
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setRows(0);
		query.setFilterQueries("sentimento:neutro");
		return solr.query(query).getResults().getNumFound();
	}

	@Override
	public long getNeutro(String keyword, String user) throws SolrServerException{
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setRows(0);
		query.setFilterQueries("sentimento:neutro");
		query.setFilterQueries("utente:\""+user+"\"");
		return solr.query(query).getResults().getNumFound();
	}

	@Override
	public long getNeutro(String keyword, String user, String dateStart, String dateEnd) throws SolrServerException{
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setRows(0);
		query.setFilterQueries("sentimento:neutro");
		query.setFilterQueries("utente:\""+user+"\"");
		query.setFilterQueries("data:["+dateStart+" TO "+dateEnd+"]");
		return solr.query(query).getResults().getNumFound();
	}

	@Override
	public long getNeutro(String keyword, String dateStart, String dateEnd) throws SolrServerException{
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setRows(0);
		query.setFilterQueries("sentimento:neutro");
		query.setFilterQueries("data:["+dateStart+" TO "+dateEnd+"]");
		return solr.query(query).getResults().getNumFound();
	}

	private List<String> getRecords(SolrQuery query) throws SolrServerException{
		QueryResponse response = solr.query(query);

		SolrDocumentList results = response.getResults();

		System.out.println(results.getNumFound());
		List<String> ret = new ArrayList<String>();

		for (int i = 0; i < results.size(); ++i) {
			ret.add(results.get(i)+"\n\n"); //$NON-NLS-1$
		} 
		return ret;

	}



}
