package it.uniroma3.dia.tartbrain.controller;



import it.uniroma3.dia.tartbrain.facade.SolrFacade;
import it.uniroma3.dia.tartbrain.model.Tweet;

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

	private String keyword; //da passare	
	private String user; //da passare
	private String dateStart=""; //da passare
	private String dateEnd=""; //da passare
	private String sentimento; //da passare
	private List<Tweet> results; 
	
	private long nResults=0; 
	private long nPagine=1;
	
	private long risAttuali=0;
	private int pagina=1;
	private String messaggio="nessun risultato";
	
	private long positivi;//da togliere
	private long negativi;//da togliere
	private long neutri;//da togliere
	private boolean avviato;
	public List<String> stringhe;
	public List<List<String>> prova;

	private SolrFacade solrFacade;
	

	public String searchKeyword() throws SolrServerException{
		
		this.prova= new ArrayList<List<String>>();
		this.stringhe= new ArrayList<String>();
		this.stringhe.add("'Anno'");
		this.stringhe.add("'Valore'");
		this.prova.add(stringhe);
		
		for(int i=0;i<4;i++){
			this.stringhe= new ArrayList<String>();
			this.stringhe.add("'2012'");
			this.stringhe.add("10");
			this.prova.add(stringhe);
		}
		

		
		if(this.dateEnd==null || this.dateStart==null || this.dateEnd.length()==0 || this.dateStart.length()==0){
			this.dateEnd="";
			this.dateStart="";}
		
		this.avviato=true;
		this.solrFacade=new SolrFacade();
		this.pagina=1;
		
		


		this.results= this.ricerca();

		this.positivi=solrFacade.getSentimento(keyword, "positivo", this.user, this.dateFormatter(dateStart), this.dateFormatter(dateEnd));
		this.negativi=solrFacade.getSentimento(keyword, "negativo",this.user, this.dateFormatter(dateStart), this.dateFormatter(dateEnd));
		this.neutri=solrFacade.getSentimento(keyword, "neutro",this.user, this.dateFormatter(dateStart), this.dateFormatter(dateEnd));

		this.nResults=this.solrFacade.getSentimento(keyword, sentimento, user, this.dateFormatter(dateStart), this.dateFormatter(dateEnd));
		this.nPagine=this.nResults/10+1;
		this.risAttuali = results.size();
		if(this.nResults==0)
			messaggio="nessun risultato";
		else
			messaggio=this.risAttuali+" su " +this.nResults + "risultati";
		return "indexU";

	}

	public String next() throws SolrServerException{
		if(this.nResults-risAttuali>0){
			this.pagina++;
			results= this.ricerca();
			this.risAttuali = this.risAttuali+results.size();
		}

		

		messaggio=this.risAttuali+" su " +this.nResults + "risultati";
		return "indexU";

	}
	public String prev() throws SolrServerException{
		if(this.pagina!=1){
			this.pagina--;
			this.risAttuali = this.risAttuali-results.size();
			results= this.ricerca();
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

	public List<String> getStringhe() {
		return stringhe;
	}

	public void setStringhe(List<String> stringhe) {
		this.stringhe = stringhe;
	}

	public List<List<String>> getProva() {
		return prova;
	}

	public void setProva(List<List<String>> prova) {
		this.prova = prova;
	}

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


	public String prova(){
		return "ciao";
	}
}
