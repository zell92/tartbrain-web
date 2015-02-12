package it.uniroma3.dia.tartbrain.facade;


import it.uniroma3.dia.tartbrain.model.Tweet;

import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

public interface ISolrFacade {



	/**
	 * Ricerca tutti i tweet conteneti la keyword specificata,
	 * è possibile specificare il numero di record da visualizzare.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  nRecord visualizza 10 elementi a partire da nRecord
	 * @return      Una lista di stringe di grandezza 10 conteneti tutti i tweet con la keyword specificata
	 */
	public List<Tweet> search (String keyword, int nRecord) throws SolrServerException;

	
	/**
	 * Ricerca tutti i tweet conteneti la keyword specificata,
	 * è possibile specificare un range di date e il numero di record da visualizzare.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  dateStart data di inizio
	 * @param  dateEnd data di fine
	 * @param  nRecord visualizza 10 elementi a partire da nRecord
	 * @return      Una lista di stringhe di grandezza 10 conteneti tutti i tweet con la keyword specificata scritte dal giorno dateStart al giorno dateEnd
	 */
	public List<Tweet> search (String keyword, String dateStart, String dateEnd, int nRecord) throws SolrServerException;

/**
	 * Ricerca tutti i tweet conteneti la keyword specificata,
	 * è possibile specificare un range di date, l'utente che ha pubblicato i tweet 
	 * e il numero di tweet da visualizzare.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  dateStart data di inizio
	 * @param  dateEnd data di fine
	 * @param  nRecord visualizza 10 elementi a partire da nRecord
	 * @param  user utente che ha pubblicato i tweet
	 * @return      Una lista di stringhe di grandezza 10 conteneti tutti i tweet dell'utente user con la keyword specificata scritte dal giorno dateStart al giorno dateEnd
	 */
	public List<Tweet> search (String keyword, String user, String dateStart, String dateEnd, int nRecord) throws SolrServerException;

	/**
	 * Ricerca tutti i tweet conteneti la keyword specificata,
	 * è possibile specificare un range di date, l'utente che ha pubblicato i tweet 
	 * e il numero di tweet da visualizzare.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  nRecord visualizza 10 elementi a partire da nRecord
	 * @param  user utente che ha pubblicato i tweet
	 * @return      Una lista di stringhe di grandezza 10 conteneti tutti i tweet dell'utente user con la keyword specificata 
	 */
	public List<Tweet> search(String keyword, String user, int nRecord)
			throws SolrServerException;
	
	/**
	 * Ricerca tutti i tweet conteneti la keyword specificata,
	 * è possibile specificare un range di date, l'utente che ha pubblicato i tweet 
	 * e il numero di tweet da visualizzare.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  sentimento sentimento dei tweet da ricercare
	 * @param  nRecord visualizza 10 elementi a partire da nRecord
	 * @return      Una lista di stringhe di grandezza 10 conteneti tutti i tweet dell'utente user con la keyword specificata 
	 */
	public List<Tweet> searchSentimento(String keyword, String sentimento, int nRecord)
			throws SolrServerException;
	
	/**
	 * Ricerca tutti i tweet conteneti la keyword specificata,
	 * è possibile specificare un range di date, l'utente che ha pubblicato i tweet 
	 * e il numero di tweet da visualizzare.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  dateStart data di inizio
	 * @param  dateEnd data di fine
	 * @param  sentimento sentimento dei tweet da ricercare
	 * @param  nRecord visualizza 10 elementi a partire da nRecord
	 * @return      Una lista di stringhe di grandezza 10 conteneti tutti i tweet dell'utente user con la keyword specificata 
	 */
	public List<Tweet> searchSentimento(String keyword, String dateStart,
			String dateEnd, String sentimento, int nRecord)
			throws SolrServerException;

	/**
	 * Ricerca tutti i tweet conteneti la keyword specificata,
	 * è possibile specificare un range di date, l'utente che ha pubblicato i tweet 
	 * e il numero di tweet da visualizzare.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  user	 Utente che ha creato il tweet
	 * @param  dateStart data di inizio
	 * @param  dateEnd data di fine
	 * @param  sentimento sentimento dei tweet da ricercare
	 * @param  nRecord visualizza 10 elementi a partire da nRecord
	 * @return      Una lista di stringhe di grandezza 10 conteneti tutti i tweet dell'utente user con la keyword specificata 
	 */
	public List<Tweet> searchSentimento(String keyword, String user, String dateStart,
			String dateEnd, String sentimento, int nRecord)
			throws SolrServerException;

	/**
	 * Ricerca tutti i tweet conteneti la keyword specificata,
	 * è possibile specificare un range di date, l'utente che ha pubblicato i tweet 
	 * e il numero di tweet da visualizzare.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  user	 Utente che ha creato il tweet
	 * @param  sentimento sentimento dei tweet da ricercare
	 * @param  nRecord visualizza 10 elementi a partire da nRecord
	 * @return      Una lista di stringhe di grandezza 10 conteneti tutti i tweet dell'utente user con la keyword specificata 
	 */
	public List<Tweet> searchSentimento(String keyword, String user,
			String sentimento, int nRecord) throws SolrServerException;

	

	/**
	 * Ricerca il numero di tweet in base al sentimento contenenti la keyword specificata, 
	 * se il sentimento non è né positivo, né negativo, né neutro allora restituisce il numero
	 * di tweet sensa considerare il sentimento.
	 * è possibile specificare un range di date e l'utente che ha pubblicato i tweet.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  sentimento il sentimento da ricercare
	 * @return  Il numero di tweet in base al sentimento specificato
	 */
	long getSentimento(String keyword, String sentimento)
			throws SolrServerException;
	
	/**
	 * Ricerca il numero di tweet in base al sentimento contenenti la keyword specificata, 
	 * se il sentimento non è né positivo, né negativo, né neutro allora restituisce il numero
	 * di tweet sensa considerare il sentimento.
	 * è possibile specificare un range di date e l'utente che ha pubblicato i tweet.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  sentimento il sentimento da ricercare
	 * @param  user utente che ha pubblicato i tweet
	 * @return  Il numero di tweet in base al sentimento specificato, pubblicati dall'utente user
	 */
	long getSentimento(String keyword, String sentimento, String user)
			throws SolrServerException;

	/**
	 * Ricerca il numero di tweet in base al sentimento contenenti la keyword specificata, 
	 * se il sentimento non è né positivo, né negativo, né neutro allora restituisce il numero
	 * di tweet sensa considerare il sentimento.
	 * è possibile specificare un range di date e l'utente che ha pubblicato i tweet.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  sentimento il sentimento da ricercare
	 * @param  user utente che ha pubblicato i tweet
	 * @param  dateStart data di inizio
	 * @param  dateEnd data di fine
	 * @return  Il numero di tweet in base al sentimento specificato, pubblicati dall'utente user e scritti dal giorno dateStart al giorno dateEnd
	 */
	long getSentimento(String keyword, String sentimento, String user,
			String dateStart, String dateEnd) throws SolrServerException;

	/**
	 * Ricerca il numero di tweet in base al sentimento contenenti la keyword specificata, 
	 * se il sentimento non è né positivo, né negativo, né neutro allora restituisce il numero
	 * di tweet sensa considerare il sentimento.
	 * è possibile specificare un range di date e l'utente che ha pubblicato i tweet.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  sentimento il sentimento da ricercare
	 * @param  dateStart data di inizio
	 * @param  dateEnd data di fine
	 * @return  Il numero di tweet in base al sentimento specificato, scritti dal giorno dateStart al giorno dateEnd
	 */
	long getSentimento(String keyword, String sentimento, String dateStart,
			String dateEnd) throws SolrServerException;


	

}
