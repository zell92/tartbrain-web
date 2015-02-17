package it.uniroma3.dia.tartbrain.util;



import it.uniroma3.dia.tartbrain.facade.SolrFacade;
import it.uniroma3.dia.tartbrain.model.Tweet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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


	private SolrFacade solrFacade;




	public ChartCreator(String keyword, String user, String dateStart,
			String dateEnd, String sentimento) throws SolrServerException {
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
			this.dateStart = this.dateFormatter("1/01/"+this.dataMinore());
		else
			this.dateStart = this.dateFormatter(this.dateStart);

		if (this.dateEnd==null || this.dateEnd.length()==0)
			this.dateEnd = this.dateFormatter(this.giornoCorrente());
		else
			this.dateEnd = this.dateFormatter(this.dateEnd);


	}



	public List<List<String>> generaDatiSentimenti() throws SolrServerException, ParseException{

		int annoStart =this.getAnno(dateStart);
		int annoEnd=this.getAnno(dateEnd);
		int meseStart=this.getMese(dateStart);
		int meseEnd=this.getMese(dateEnd);
		int giornoStart=this.getGiorno(dateStart);
		int giornoEnd=this.getGiorno(dateEnd);


		int rangeGiorni = -this.rangeGiorni(dateStart, dateEnd);



		List<List<String>> dati = new ArrayList<List<String>>();
		List<String> elementi = new ArrayList<String>();

		elementi.add("'Tempo'");
		elementi.add("'Positivi'");
		elementi.add("'Negativi'");
		elementi.add("'Neutro'");
		dati.add(elementi);

		if(rangeGiorni<=31){
			int g = giornoStart;
			int m= meseStart;
			int a = annoStart;
			while(!(g==giornoEnd+1 && m==meseEnd && a == annoEnd)){

				int giornoDopo=g+1;
				int meseDopo=m;
				int annoDopo=a;

				if(g==this.getGiornoMaggiore(a, m) && g!=giornoEnd ){
					giornoDopo=1;
					if(m==12){
						meseDopo=1;
						annoDopo=a+1;
					}else
						meseDopo=m+1;

				}



				elementi = new ArrayList<String>();
				elementi.add("'"+g+"/"+m+"/"+annoStart+"'");
				elementi.add(""+this.solrFacade.getSentimento(keyword, "positivo", user, this.creaData(g, m, a), this.creaData(giornoDopo, meseDopo, annoDopo)));
				elementi.add(""+this.solrFacade.getSentimento(keyword, "negativo", user, this.creaData(g, m, a), this.creaData(giornoDopo, meseDopo, annoDopo)));
				elementi.add(""+this.solrFacade.getSentimento(keyword, "neutro", user, this.creaData(g, m, a), this.creaData(giornoDopo, meseDopo, annoDopo)));

				dati.add(elementi);

				g=giornoDopo;
				m=meseDopo;
				a=annoDopo;
			}

		}else
			if(rangeGiorni<=365){
				int m= meseStart;
				int a = annoStart;
				while(!(m==meseEnd+1 && a == annoEnd)){

					elementi = new ArrayList<String>();
					elementi.add("'"+m+"/"+a+"'");
					elementi.add(""+this.solrFacade.getSentimento(keyword, "positivo", user, this.creaData(1, m, a), this.creaData(this.getGiornoMaggiore(a, m), m, a)));
					elementi.add(""+this.solrFacade.getSentimento(keyword, "negativo", user, this.creaData(1, m, a), this.creaData(this.getGiornoMaggiore(a, m), m, a)));
					elementi.add(""+this.solrFacade.getSentimento(keyword, "neutro", user, this.creaData(1, m, a), this.creaData(this.getGiornoMaggiore(a, m), m, a)));

					dati.add(elementi);

					if(m==12 && m!=meseEnd){
						m=1;
						a=a+1;

					}else 
						m=m+1;


				}
			} else
				if(rangeGiorni<=365*3){

					for(int i=annoStart;i<=annoEnd;i++){

						elementi = new ArrayList<String>();
						elementi.add("'Gen/Mar "+i+"'");
						elementi.add(""+this.solrFacade.getSentimento(keyword, "positivo", user, this.creaData(1, 1, i), this.creaData(31, 3, i)));
						elementi.add(""+this.solrFacade.getSentimento(keyword, "negativo", user, this.creaData(1, 1, i), this.creaData(31, 3, i)));
						elementi.add(""+this.solrFacade.getSentimento(keyword, "neutro", user, this.creaData(1, 1, i), this.creaData(31, 3, i)));
						dati.add(elementi);

						elementi = new ArrayList<String>();
						elementi.add("'Apr/Giu "+i+"'");
						elementi.add(""+this.solrFacade.getSentimento(keyword, "positivo", user, this.creaData(1, 4, i), this.creaData(30, 6, i)));
						elementi.add(""+this.solrFacade.getSentimento(keyword, "negativo", user, this.creaData(1, 4, i), this.creaData(30, 6, i)));
						elementi.add(""+this.solrFacade.getSentimento(keyword, "neutro", user, this.creaData(1, 4, i), this.creaData(30, 6, i)));

						dati.add(elementi);

						elementi = new ArrayList<String>();
						elementi.add("'Lug/Set "+i+"'");
						elementi.add(""+this.solrFacade.getSentimento(keyword, "positivo", user, this.creaData(1, 7, i), this.creaData(30, 9, i)));
						elementi.add(""+this.solrFacade.getSentimento(keyword, "negativo", user, this.creaData(1, 7, i), this.creaData(30, 9, i)));
						elementi.add(""+this.solrFacade.getSentimento(keyword, "neutro", user, this.creaData(1, 7, i), this.creaData(30, 9, i)));

						dati.add(elementi);

						elementi = new ArrayList<String>();
						elementi.add("'Ott/Dic "+i+"'");
						elementi.add(""+this.solrFacade.getSentimento(keyword, "positivo", user, this.creaData(1, 10, i), this.creaData(31, 12, i)));
						elementi.add(""+this.solrFacade.getSentimento(keyword, "negativo", user, this.creaData(1, 10, i), this.creaData(31, 12, i)));
						elementi.add(""+this.solrFacade.getSentimento(keyword, "neutro", user, this.creaData(1, 10, i), this.creaData(31, 12, i)));

						dati.add(elementi);
					}


				} else{

					for(int i= annoStart;i<=annoEnd;i++){
						elementi = new ArrayList<String>();
						elementi.add("'"+i+"'");
						elementi.add(""+this.solrFacade.getSentimento(keyword, "positivo", user, this.creaData(1, 1, i), this.creaData(31, 12, i)));
						elementi.add(""+this.solrFacade.getSentimento(keyword, "negativo", user, this.creaData(1, 1, i), this.creaData(31, 12, i)));
						elementi.add(""+this.solrFacade.getSentimento(keyword, "neutro", user, this.creaData(1, 1, i), this.creaData(31, 12, i)));

						dati.add(elementi);


					}
				}



		return dati;
	}



	public List<List<String>> generaDatiTotali() throws SolrServerException, ParseException{

		int annoStart =this.getAnno(dateStart);
		int annoEnd=this.getAnno(dateEnd);
		int meseStart=this.getMese(dateStart);
		int meseEnd=this.getMese(dateEnd);
		int giornoStart=this.getGiorno(dateStart);
		int giornoEnd=this.getGiorno(dateEnd);



		int rangeGiorni = -this.rangeGiorni(dateStart, dateEnd);



		List<List<String>> dati = new ArrayList<List<String>>();
		List<String> elementi = new ArrayList<String>();

		elementi.add("'Data'");
		elementi.add("'Tweet effettuati'");

		dati.add(elementi);

		if(rangeGiorni<=31){
			int g = giornoStart;
			int m= meseStart;
			int a = annoStart;
			while(!(g==giornoEnd+1 && m==meseEnd && a == annoEnd)){

				int giornoDopo=g+1;
				int meseDopo=m;
				int annoDopo=a;

				if(g==this.getGiornoMaggiore(a, m) && g!=giornoEnd ){
					giornoDopo=1;
					if(m==12){
						meseDopo=1;
						annoDopo=a+1;
					}else
						meseDopo=m+1;

				}



				elementi = new ArrayList<String>();
				elementi.add("'"+g+"/"+m+"/"+annoStart+"'");
				elementi.add(""+this.solrFacade.getSentimento(keyword, this.sentimento, user, this.creaData(g, m, a), this.creaData(giornoDopo, meseDopo, annoDopo)));


				dati.add(elementi);

				g=giornoDopo;
				m=meseDopo;
				a=annoDopo;
			}

		}else
			if(rangeGiorni<=365){
				int m= meseStart;
				int a = annoStart;
				while(!(m==meseEnd+1 && a == annoEnd)){

					elementi = new ArrayList<String>();
					elementi.add("'"+m+"/"+a+"'");
					elementi.add(""+this.solrFacade.getSentimento(keyword, this.sentimento, user, this.creaData(1, m, a), this.creaData(this.getGiornoMaggiore(a, m), m, a)));


					dati.add(elementi);

					if(m==12 && m!=meseEnd){
						m=1;
						a=a+1;

					}else 
						m=m+1;


				}
			} else
				if(rangeGiorni<=365*3){

					for(int i=annoStart;i<=annoEnd;i++){

						elementi = new ArrayList<String>();
						elementi.add("'Gen/Mar "+i+"'");
						elementi.add(""+this.solrFacade.getSentimento(keyword, this.sentimento, user, this.creaData(1, 1, i), this.creaData(31, 3, i)));
						dati.add(elementi);

						elementi = new ArrayList<String>();
						elementi.add("'Apr/Giu "+i+"'");
						elementi.add(""+this.solrFacade.getSentimento(keyword,  this.sentimento, user, this.creaData(1, 4, i), this.creaData(30, 6, i)));
						dati.add(elementi);

						elementi = new ArrayList<String>();
						elementi.add("'Lug/Set "+i+"'");
						elementi.add(""+this.solrFacade.getSentimento(keyword,  this.sentimento, user, this.creaData(1, 7, i), this.creaData(30, 9, i)));
						dati.add(elementi);

						elementi = new ArrayList<String>();
						elementi.add("'Ott/Dic "+i+"'");
						elementi.add(""+this.solrFacade.getSentimento(keyword,  this.sentimento, user, this.creaData(1, 10, i), this.creaData(31, 12, i)));
						dati.add(elementi);
					}


				} else{
					for(int i= annoStart;i<=annoEnd;i++){
						elementi = new ArrayList<String>();
						elementi.add("'"+i+"'");
						elementi.add(""+this.solrFacade.getSentimento(keyword,  this.sentimento, user, this.creaData(1, 1, i), this.creaData(31, 12, i)));


						dati.add(elementi);


					}
				}



		return dati;
	}


	public List<List<String>> generaDatiTotaliTrimestre(String annoMinore) throws SolrServerException, ParseException{

		int annoFinale = this.getAnno(this.dateFormatter(this.giornoCorrente()));


		List<List<String>> dati = new ArrayList<List<String>>();
		List<String> elementi = new ArrayList<String>();

		elementi.add("'Trimestre'");
		elementi.add("'Tweet effettuati'");
		dati.add(elementi);

		for(int i=Integer.parseInt(annoMinore);i<=annoFinale;i++){

			elementi = new ArrayList<String>();
			elementi.add("'Gen/Mar "+i+"'");
			elementi.add(""+this.solrFacade.getSentimento(keyword, "qualsiasi", user, this.creaData(1, 1, i), this.creaData(31, 3, i)));
			dati.add(elementi);

			elementi = new ArrayList<String>();
			elementi.add("'Apr/Giu "+i+"'");
			elementi.add(""+this.solrFacade.getSentimento(keyword, "qualsiasi", user, this.creaData(1, 4, i), this.creaData(30, 6, i)));
			dati.add(elementi);

			elementi = new ArrayList<String>();
			elementi.add("'Lug/Set "+i+"'");
			elementi.add(""+this.solrFacade.getSentimento(keyword, "qualsiasi", user, this.creaData(1, 7, i), this.creaData(30, 9, i)));
			dati.add(elementi);

			elementi = new ArrayList<String>();
			elementi.add("'Ott/Dic "+i+"'");
			elementi.add(""+this.solrFacade.getSentimento(keyword, "qualsiasi", user, this.creaData(1, 10, i), this.creaData(31, 12, i)));
			dati.add(elementi);
		}

		return dati;

	}

	public List<List<String>> generaDatiSentimentiTrimestre(String annoMinore) throws SolrServerException, ParseException{

		int annoFinale = this.getAnno(this.dateFormatter(this.giornoCorrente()));


		List<List<String>> dati = new ArrayList<List<String>>();
		List<String> elementi = new ArrayList<String>();

		elementi.add("'Trimestre'");
		elementi.add("'Positivi'");
		elementi.add("'Negativi'");
		elementi.add("'Neutro'");
		dati.add(elementi);

		for(int i=Integer.parseInt(annoMinore);i<=annoFinale;i++){

			elementi = new ArrayList<String>();
			elementi.add("'Gen/Mar "+i+"'");
			elementi.add(""+this.solrFacade.getSentimento(keyword, "positivo", user, this.creaData(1, 1, i), this.creaData(31, 3, i)));
			elementi.add(""+this.solrFacade.getSentimento(keyword, "negativo", user, this.creaData(1, 1, i), this.creaData(31, 3, i)));
			elementi.add(""+this.solrFacade.getSentimento(keyword, "neutro", user, this.creaData(1, 1, i), this.creaData(31, 3, i)));

			dati.add(elementi);

			elementi = new ArrayList<String>();
			elementi.add("'Apr/Giu "+i+"'");
			elementi.add(""+this.solrFacade.getSentimento(keyword, "positivo", user, this.creaData(1, 4, i), this.creaData(30, 6, i)));
			elementi.add(""+this.solrFacade.getSentimento(keyword, "negativo", user, this.creaData(1, 4, i), this.creaData(30, 6, i)));
			elementi.add(""+this.solrFacade.getSentimento(keyword, "neutro", user, this.creaData(1, 4, i), this.creaData(30, 6, i)));

			dati.add(elementi);

			elementi = new ArrayList<String>();
			elementi.add("'Lug/Set "+i+"'");
			elementi.add(""+this.solrFacade.getSentimento(keyword, "positivo", user, this.creaData(1, 7, i), this.creaData(30, 9, i)));
			elementi.add(""+this.solrFacade.getSentimento(keyword, "negativo", user, this.creaData(1, 7, i), this.creaData(30, 9, i)));
			elementi.add(""+this.solrFacade.getSentimento(keyword, "neutro", user, this.creaData(1, 7, i), this.creaData(30, 9, i)));

			dati.add(elementi);

			elementi = new ArrayList<String>();
			elementi.add("'Ott/Dic "+i+"'");
			elementi.add(""+this.solrFacade.getSentimento(keyword, "positivo", user, this.creaData(1, 10, i), this.creaData(31, 12, i)));
			elementi.add(""+this.solrFacade.getSentimento(keyword, "negativo", user, this.creaData(1, 10, i), this.creaData(31, 12, i)));
			elementi.add(""+this.solrFacade.getSentimento(keyword, "neutro", user, this.creaData(1, 10, i), this.creaData(31, 12, i)));
			dati.add(elementi);
		}

		return dati;

	}

	public long getSentimento(String sentimento) throws SolrServerException{
		int g=this.getGiorno(this.dateEnd);
		int m=this.getMese(this.dateEnd);
		int a=this.getAnno(this.dateEnd);
		int giornoDopo=g+1;
		int meseDopo=m;
		int annoDopo=a;

		if(g==this.getGiornoMaggiore(a, m) ){
			giornoDopo=1;
			if(m==12){
				meseDopo=1;
				annoDopo=a+1;
			}else
				meseDopo=m+1;

		}
		return solrFacade.getSentimento(keyword, sentimento, this.user, dateStart, this.creaData(giornoDopo, meseDopo, annoDopo));

	}

	public String dataMinore() throws SolrServerException{
		int i = 2006;
		boolean trovato = false;
		while(i<this.getAnno(this.dateFormatter(this.giornoCorrente()))&&!trovato){
			if(this.solrFacade.getSentimento("*:*", "qualsiasi",null, this.creaData(1, 1, i), this.creaData(31, 12, i))!=0)
				trovato = true;
			i++;

		}
		return (i-1)+"";
	}

	public List<Tweet> ricerca(int pagina) throws SolrServerException{
		String dataIn;
		String dataFin;


		if (this.user==null || this.user.length()==0)
			this.user=null;
		if (this.dateStart==null || this.dateStart.length()==0)
			dataIn = this.dateFormatter("21/03/2006");
		else
			
			dataIn = this.dateStart;

		if (this.dateEnd==null || this.dateEnd.length()==0)
			dataFin = this.dateFormatter(this.giornoCorrente());
		else  {
			int g=this.getGiorno(this.dateEnd);
			int m=this.getMese(this.dateEnd);
			int a=this.getAnno(this.dateEnd);
			int giornoDopo=g+1;
			int meseDopo=m;
			int annoDopo=a;

			if(g==this.getGiornoMaggiore(a, m) ){
				giornoDopo=1;
				if(m==12){
					meseDopo=1;
					annoDopo=a+1;
				}else
					meseDopo=m+1;

			}
			dataFin =this.creaData(giornoDopo, meseDopo, annoDopo);
					}




		//keyword+sentimento+data
		if (!this.sentimento.equals("qualsiasi") && this.user==null && this.dateStart!=null)
			return solrFacade.searchSentimento(this.keyword,dataIn,dataFin, this.sentimento, (pagina-1)*10);

		//keyword+data
		if (this.sentimento.equals("qualsiasi") && this.user==null && this.dateStart!=null)
			return solrFacade.search(keyword, dataIn, dataFin, (pagina-1)*10);
		//keyword+user+data
		if (this.sentimento.equals("qualsiasi") && this.user!=null && this.dateStart!=null)
			return solrFacade.search(keyword, user, dataIn, dataFin, (pagina-1)*10);
		//keyword+sentimento+user+data
		return solrFacade.searchSentimento(keyword, user, dataIn, dataFin, sentimento, (pagina-1)*10);
	}



	private int getAnno(String data){
		int anno;
		anno=Integer.parseInt(data.substring(0, 4));
		return anno;
	}

	private int getMese(String data){
		int mese;
		mese=Integer.parseInt(data.substring(5, 7));
		return mese;
	}

	private int getGiorno(String data){
		int mese;
		mese=Integer.parseInt(data.substring(8, 10));
		return mese;
	}

	private int getGiornoMaggiore(int anno, int mese){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, anno);
		c.set(Calendar.MONTH, mese-1);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}


	private String creaData(int giorno,int mese, int anno){
		String data = giorno+"/"+mese+"/"+anno;
		return this.dateFormatter(data);
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

	private int rangeGiorni (String data1, String data2) throws ParseException{
		Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

		Date date = sdf.parse(data1);
		cal1.setTime(date);
		date = sdf.parse(data2);
		cal2.setTime(date);

		//cal1.set(2008, 8, 1); 
		//cal2.set(2008, 9, 31);
		return (int)( (cal1.getTime().getTime() - cal2.getTime().getTime()) / (1000 * 60 * 60 * 24));

	}

}

