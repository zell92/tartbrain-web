package it.uniroma3.dia.tartbrain.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tweet {

	private String data;
	private String utente;
	private String testo;
	private String sentimento;
	
	public Tweet(){
	
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = this.dateFormatter(data);
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getSentimento() {
		return sentimento;
	}

	public void setSentimento(String sentimento) {
		this.sentimento = sentimento;
	}

	@Override
	public String toString() {
		return "Tweet [data=" + data + ", utente=" + utente + ", testo="
				+ testo + ", sentimento=" + sentimento + "]";
	}
	
	
	private static String dateFormatter(String dateStr) {
		String data = "";
		data = dateStr.substring(8,10)+" "+ dateStr.substring(4,7)+" "+dateStr.substring(dateStr.length()-4,dateStr.length());
		return data;
				
	}

	
}
