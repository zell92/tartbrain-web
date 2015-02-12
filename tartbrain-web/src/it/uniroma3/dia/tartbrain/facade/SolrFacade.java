package it.uniroma3.dia.tartbrain.facade;


import it.uniroma3.dia.tartbrain.model.Tweet;
import it.uniroma3.dia.tartbrain.util.Messages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public List<Tweet> search(String keyword, int nRecord) throws SolrServerException  {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setRows(10);
		query.setStart(nRecord);
		return this.getRecords(query);

	}



	@Override
	public List<Tweet> search(String keyword, String dateStart, String dateEnd,
			int nRecord) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.addFilterQuery("data:["+dateStart+" TO "+dateEnd+"]");
		query.setRows(10);
		query.setStart(nRecord);
		return this.getRecords(query);
	}



	@Override
	public List<Tweet> search(String keyword, String user, String dateStart,
			String dateEnd, int nRecord) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.addFilterQuery("utente:\""+user+"\"");
		query.addFilterQuery("data:["+dateStart+" TO "+dateEnd+"]");
		query.setRows(10);
		query.setStart(nRecord);;
		return this.getRecords(query);
	}


	@Override
	public List<Tweet> search(String keyword, String user, int nRecord) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setRows(10);
		query.setStart(nRecord);
		query.addFilterQuery("utente:\""+user+"\"");
		return this.getRecords(query);
	}


	@Override
	public List<Tweet> searchSentimento(String keyword, String sentimento, int nRecord) throws SolrServerException  {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setRows(10);
		query.setStart(nRecord);
		query.addFilterQuery("sentimento:"+sentimento);
		return this.getRecords(query);

	}



	@Override
	public List<Tweet> searchSentimento(String keyword, String dateStart, String dateEnd, String sentimento,
			int nRecord) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.addFilterQuery("sentimento:"+sentimento);
		query.addFilterQuery("data:["+dateStart+" TO "+dateEnd+"]");
		query.setRows(10);
		query.setStart(nRecord);
		
		return this.getRecords(query);
	}



	@Override
	public List<Tweet> searchSentimento(String keyword, String user, String dateStart,
			String dateEnd,String sentimento, int nRecord) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.addFilterQuery("utente:\""+user+"\"");
		query.addFilterQuery("data:["+dateStart+" TO "+dateEnd+"]");
		query.addFilterQuery("sentimento:"+sentimento);
		query.setRows(10);
		query.setStart(nRecord);;
		return this.getRecords(query);
	}


	@Override
	public List<Tweet> searchSentimento(String keyword, String user, String sentimento, int nRecord) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setRows(10);
		query.setStart(nRecord);
		query.addFilterQuery("utente:\""+user+"\"");
		query.addFilterQuery("sentimento:"+sentimento);
		return this.getRecords(query);
	}


	@Override
	public long getSentimento(String keyword, String sentimento) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setRows(0);
		if(sentimento.equals("positivo")||sentimento.equals("negativo")||sentimento.equals("neutro"))
		query.addFilterQuery("sentimento:"+sentimento);
		return solr.query(query).getResults().getNumFound();
	}



	@Override
	public long getSentimento(String keyword,String sentimento, String user) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setRows(0);
		if(sentimento.equals("positivo")||sentimento.equals("negativo")||sentimento.equals("neutro"))
		query.addFilterQuery("sentimento:"+sentimento);
		if(user!=null)
		query.addFilterQuery("utente:\""+user+"\"");
		return solr.query(query).getResults().getNumFound();
	}

	@Override
	public long getSentimento(String keyword,String sentimento, String user, String dateStart, String dateEnd) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setRows(0);
		if(sentimento.equals("positivo")||sentimento.equals("negativo")||sentimento.equals("neutro"))
		query.addFilterQuery("sentimento:"+sentimento);
		if(user!=null)
		query.addFilterQuery("utente:\""+user+"\"");
		if(!(dateStart== null || dateEnd==null))
		query.addFilterQuery("data:["+dateStart+" TO "+dateEnd+"]");
		return solr.query(query).getResults().getNumFound();
	}

	@Override
	public long getSentimento(String keyword, String sentimento, String dateStart, String dateEnd) throws SolrServerException {
		SolrQuery query = new SolrQuery();
		query.setQuery(keyword);
		query.setRows(0);
		if(sentimento.equals("positivo")||sentimento.equals("negativo")||!sentimento.equals("neutro"))

		query.addFilterQuery("sentimento:"+sentimento);
		if(!(dateStart== null) || !(dateEnd==null))
		query.addFilterQuery("data:["+dateStart+" TO "+dateEnd+"]");
		return solr.query(query).getResults().getNumFound();
	}

	private List<Tweet> getRecords(SolrQuery query) throws SolrServerException{
		QueryResponse response = solr.query(query);

		SolrDocumentList results = response.getResults();

		System.out.println(results.getNumFound());
		List<String> ret = new ArrayList<String>();

		for (int i = 0; i < results.size(); ++i) {
			ret.add(results.get(i)+"\n\n"); //$NON-NLS-1$
		} 
		return this.filterMessage(ret);

	}

	private List<Tweet> filterMessage(List<String> a){
		List<Tweet> result = new ArrayList<Tweet>();

		for(String s:a){
			Tweet t = new Tweet();
			t.setData(s.substring(s.indexOf(", data="),s.indexOf(", utente=")).replaceFirst(", data=", ""));
			t.setTesto(s.substring(s.indexOf(", testo="),s.indexOf(", includes=")).replaceFirst(", testo=", ""));
			t.setUtente((s.substring(s.indexOf(", utente="),s.indexOf(", testo=")).replaceFirst(", utente=", "")));

			if(s.indexOf(", screenName=")>0)
				t.setSentimento(s.substring(s.indexOf(", sentimento="),s.indexOf(", screenName=")).replaceFirst(", sentimento=", ""));

			else
				t.setSentimento(s.substring(s.indexOf(", sentimento="),s.indexOf(", _version_=")).replaceFirst(", sentimento=", ""));

			result.add(t);

		}
		return result;
	}
	
	


	
	





}
