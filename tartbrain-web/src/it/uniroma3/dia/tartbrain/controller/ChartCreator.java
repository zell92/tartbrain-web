package it.uniroma3.dia.tartbrain.controller;



import it.uniroma3.dia.tartbrain.facade.SolrFacade;
import it.uniroma3.dia.tartbrain.model.Tweet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.solr.client.solrj.SolrServerException;

public class ChartCreator {

	private String keyword; //da passare	
	private String user; //da passare
	private String dateStart=""; //da passare
	private String dateEnd=""; //da passare
	private String sentimento; //da passare

	private Map annoToTweet;
	private Map meseToTweet;

	private SolrFacade solrFacade;

	

	
	public ChartCreator(String keyword, String user, String dateStart,
			String dateEnd, String sentimento) {
		super();
		this.keyword = keyword;
		this.user = user;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.sentimento = sentimento;
		this.solrFacade = new SolrFacade();;
		
		if(this.dateEnd==null || this.dateStart==null || this.dateEnd.length()==0 || this.dateStart.length()==0){
			this.dateEnd="";
			this.dateStart="";}
		

		if (this.user==null || this.user.length()==0)
			this.user=null;
		if (this.dateStart==null || this.dateStart.length()==0)
			this.dateStart = this.dateFormatter("21/03/2006");
		else
			this.dateStart = this.dateFormatter(this.dateStart);

		if (this.dateEnd==null || this.dateEnd.length()==0)
			this.dateEnd = this.dateFormatter(this.giornoCorrente());
		else
			this.dateEnd = this.dateFormatter(this.dateEnd);

		
	}
	
	

	public List<List<String>> generaDatiSentimenti(){
		List<List<String>> dati = new ArrayList<List<String>>();
		
		return dati;
	}
	
	
	private String dateFormatter(String dateStr) {
		if(dateStr==null || dateStr.length()==0)
			return null;
		SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String dateFormat = "dd/MM/yyyy"; 
		try {
			return out.format(new SimpleDateFormat(dateFormat).parse(dateStr));
		} catch (ParseException ignore) { }
		throw new IllegalArgumentException("Invalid date: " + dateStr);
	}

	
	private String giornoCorrente(){
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);

		return day+"/"+month+"/"+year;
	}


}

	