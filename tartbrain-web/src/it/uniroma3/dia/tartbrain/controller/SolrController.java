package it.uniroma3.dia.tartbrain.controller;



import it.uniroma3.dia.tartbrain.facade.SolrFacade;
import it.uniroma3.dia.tartbrain.model.Tweet;
import it.uniroma3.dia.tartbrain.util.ChartCreator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.solr.client.solrj.SolrServerException;

@ManagedBean
@SessionScoped
public class SolrController {

	private String keyword; 	
	private String user; 
	private String dateStart=""; 
	private String dateEnd=""; 
	private String sentimento; 
	private List<Tweet> results; 

	private long nResults=0; 
	private long nPagine=1;

	private long risAttuali=0;
	private int pagina=1;
	private String messaggio="nessun risultato";

	private long positivi;
	private long negativi;
	private long neutri;
	private boolean avviato;

	private List<List<String>> datiChart;
	private List<List<String>> datiChartTotali;

	private SolrFacade solrFacade;
	private ChartCreator chartCreator;


	public String searchKeyword() throws SolrServerException, ParseException{



		this.initChart();
		this.results= this.chartCreator.ricerca(this.pagina);


		this.nPagine=this.nResults/10+1;
		this.risAttuali = results.size();
		if(this.nResults==0)
			messaggio="nessun risultato";
		else
			messaggio=this.risAttuali+" su " +this.nResults + " risultati";
		return "indexU";

	}
	

	public String next() throws SolrServerException{
		if(this.nResults-risAttuali>0){
			this.pagina++;
			results= this.chartCreator.ricerca(this.pagina);
			this.risAttuali = this.risAttuali+results.size();
		}

		messaggio=this.risAttuali+" su " +this.nResults + "risultati";
		return "indexU";

	}
	
	
	public String prev() throws SolrServerException{
		if(this.pagina!=1){
			this.pagina--;
			this.risAttuali = this.risAttuali-results.size();
			results= this.chartCreator.ricerca(this.pagina);
		}

		messaggio=this.risAttuali+" su " +this.nResults + "risultati";
		return "indexU";	  
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

	public List<Tweet> getResults() {
		return results;
	}

	public void setResults(List<Tweet> results) {
		this.results = results;
	}

	public SolrFacade getSolrFacade() {
		return solrFacade;
	}

	public void setSolrFacade(SolrFacade solrFacade) {
		this.solrFacade = solrFacade;
	}

	public long getnResults() {
		return nResults;
	}

	public void setnResults(long nResults) {
		this.nResults = nResults;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public long getRisAttuali() {
		return risAttuali;
	}

	public void setRisAttuali(long risAttuali) {
		this.risAttuali = risAttuali;
	}

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

	public long getPositivi() {
		return positivi;
	}

	public void setPositivi(long positivi) {
		this.positivi = positivi;
	}

	public long getNegativi() {
		return negativi;
	}

	public void setNegativi(long negativi) {
		this.negativi = negativi;
	}

	public long getNeutri() {
		return neutri;
	}

	public void setNeutri(long neutri) {
		this.neutri = neutri;
	}

	public boolean getAvviato() {
		return avviato;
	}

	public void setAvviato(boolean avviato) {
		this.avviato = avviato;
	}

	public long getnPagine() {
		return nPagine;
	}

	public void setnPagine(long nPagine) {
		this.nPagine = nPagine;
	}



	public String getSentimento() {
		return sentimento;
	}

	public void setSentimento(String sentimento) {
		this.sentimento = sentimento;
	}


	public List<List<String>> getDatiChartTotali() {
		return datiChartTotali;
	}

	public void setDatiChartTotali(List<List<String>> datiChartTotali) {
		this.datiChartTotali = datiChartTotali;
	}

	public List<List<String>> getDatiChart() {
		return datiChart;
	}

	public void setDatiChart(List<List<String>> datiChart) {
		this.datiChart = datiChart;
	}
	/*
	private List<Tweet> ricerca() throws SolrServerException{
		String dataIn;
		String dataFin;


		if (this.user==null || this.user.length()==0)
			this.user=null;
		if (this.dateStart==null || this.dateStart.length()==0)
			dataIn = this.dateFormatter("21/03/2006");
		else
			dataIn = this.dateFormatter(this.dateStart);

		if (this.dateEnd==null || this.dateEnd.length()==0)
			dataFin = this.dateFormatter(this.giornoCorrente());
		else
			dataFin = this.dateFormatter(this.dateEnd);




		//keyword+sentimento+data
		if (!this.sentimento.equals("qualsiasi") && this.user==null && this.dateStart!=null)
			return solrFacade.searchSentimento(this.keyword,dataIn,dataFin, this.sentimento, (this.pagina-1)*10);

		//keyword+data
		if (this.sentimento.equals("qualsiasi") && this.user==null && this.dateStart!=null)
			return solrFacade.search(keyword, dataIn, dataFin, (this.pagina-1)*10);
		//keyword+user+data
		if (this.sentimento.equals("qualsiasi") && this.user!=null && this.dateStart!=null)
			return solrFacade.search(keyword, user, dataIn, dataFin, (this.pagina-1)*10);
		//keyword+sentimento+user+data
		return solrFacade.searchSentimento(keyword, user, dataIn, dataFin, sentimento, (this.pagina-1)*10);
	}*/


	private void initChart() throws SolrServerException, ParseException{
		this.chartCreator=new ChartCreator(keyword, user, dateStart, dateEnd, sentimento);
		this.setDatiChart(this.chartCreator.generaDatiSentimenti());
		this.setDatiChartTotali(this.chartCreator.generaDatiTotali());


		if(this.dateEnd==null || this.dateStart==null || this.dateEnd.length()==0 || this.dateStart.length()==0){
			this.dateEnd="";
			this.dateStart="";}

		this.avviato=true;
		this.solrFacade=new SolrFacade();
		this.pagina=1;

		this.positivi= this.chartCreator.getSentimento("positivo");
		this.negativi=this.chartCreator.getSentimento("negativo");
		this.neutri=this.chartCreator.getSentimento("neutro");

		this.nResults=this.chartCreator.getSentimento(this.sentimento);





	}


}
