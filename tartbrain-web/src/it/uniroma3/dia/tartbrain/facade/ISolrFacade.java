package it.uniroma3.dia.tartbrain.facade;

import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

public interface ISolrFacade {

	/**
	 * Ricerca tutti i tweet conteneti la keyword specificata
	 *
	 * @param  keyword parola chiave da ricercare
	 * @return      Una lista di stringe conteneti tutti i tweet con la keyword specificata
	 */
	public List<String> search (String keyword) throws SolrServerException;

	/**
	 * Ricerca tutti i tweet conteneti la keyword specificata,
	 * è possibile specificare il numero di record da visualizzare.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  nRecord numero di record da visualizzare
	 * @return      Una lista di stringe di grandezza nRecord conteneti tutti i tweet con la keyword specificata
	 */
	public List<String> search (String keyword, int nRecord) throws SolrServerException;

	/**
	 * Ricerca tutti i tweet conteneti la keyword specificata,
	 * è possibile specificare un range di date.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  dateStart data di inizio
	 * @param  dateEnd data di fine
	 * @return      Una lista di stringhe conteneti tutti i tweet con la keyword specificata scritte dal giorno dateStart al giorno dateEnd
	 */
	public List<String> search (String keyword, String dateStart, String dateEnd) throws SolrServerException;
	/**
	 * Ricerca tutti i tweet conteneti la keyword specificata,
	 * è possibile specificare un range di date e il numero di record da visualizzare.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  dateStart data di inizio
	 * @param  dateEnd data di fine
	 * @param  nRecord numero di record da visualizzare
	 * @return      Una lista di stringhe di grandezza nRecord conteneti tutti i tweet con la keyword specificata scritte dal giorno dateStart al giorno dateEnd
	 */
	public List<String> search (String keyword, String dateStart, String dateEnd, int nRecord) throws SolrServerException;

	/**
	 * Ricerca tutti i tweet conteneti la keyword specificata,
	 * è possibile specificare un range di date e l'utente che ha pubblicato i tweet.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  dateStart data di inizio
	 * @param  dateEnd data di fine
	 * @param  user utente che ha pubblicato i tweet
	 * @return      Una lista di stringhe conteneti tutti i tweet dell'utente user con la keyword specificata scritte dal giorno dateStart al giorno dateEnd
	 */
	public List<String> search (String keyword, String user, String dateStart, String dateEnd) throws SolrServerException;
	/**
	 * Ricerca tutti i tweet conteneti la keyword specificata,
	 * è possibile specificare un range di date, l'utente che ha pubblicato i tweet 
	 * e il numero di tweet da visualizzare.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  dateStart data di inizio
	 * @param  dateEnd data di fine
	 * @param  nRecord numero di record da visualizzare
	 * @param  user utente che ha pubblicato i tweet
	 * @return      Una lista di stringhe di grandezza nRecord conteneti tutti i tweet dell'utente user con la keyword specificata scritte dal giorno dateStart al giorno dateEnd
	 */
	public List<String> search (String keyword, String user, String dateStart, String dateEnd, int nRecord) throws SolrServerException;

	/**
	 * Ricerca tutti i tweet dell'utente user
	 *
	 * @param  user utente che ha pubblicato i tweet
	 * @return      Una lista di stringhe conteneti tutti i tweet dell'utente user
	 */
	public List<String> searchByUser (String user) throws SolrServerException;
	/**
	 * Ricerca tutti i tweet dell'utente user
	 * è possibile specificare il numero di tweet da visualizzare.
	 *
	 * @param  user utente che ha pubblicato i tweet
	 * @param  nRecord numero di record da visualizzare
	 * @return      Una lista di stringhe di grandezza nRecord conteneti tutti i tweet dell'utente user
	 */
	public List<String> searchByUser (String user, int nRecord) throws SolrServerException;
	/**
	 * Ricerca tutti i tweet dell'utente user
	 * è possibile specificare un range di date.
	 *
	 * @param  user utente che ha pubblicato i tweet
	 * @param  dateStart data di inizio
	 * @param  dateEnd data di fine
	 * @return      Una lista di stringheconteneti tutti i tweet dell'utente user scritti dal giorno dateStart al giorno dateEnd
	 */
	public List<String> searchByUser (String user, String dateStart, String dateEnd) throws SolrServerException;

	/**
	 * Ricerca tutti i tweet dell'utente user
	 * è possibile specificare un range di date e il numero di tweet da visualizzare.
	 *
	 * @param  user utente che ha pubblicato i tweet
	 * @param  dateStart data di inizio
	 * @param  dateEnd data di fine
	 * @param  nRecord numero di record da visualizzare
	 * @return      Una lista di stringhe di grandezza nRecord conteneti tutti i tweet dell'utente user scritti dal giorno dateStart al giorno dateEnd
	 */
	public List<String> searchByUser (String user, String dateStart, String dateEnd, int nRecord) throws SolrServerException;

	/**
	 * Ricerca il numero di tweet positivi contenenti la keyword specificata
	 *
	 * @param  keyword parola chiave da ricercare
	 * @return      Il numero di tweet positivi trovati
	 */
	public long getPositivo (String keyword) throws SolrServerException;
	/**
	 * Ricerca il numero di tweet positivi contenenti la keyword specificata
	 * è possibile specificare l'utente che ha pubblicato i tweet.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  user utente che ha pubblicato i tweet

	 * @return      Il numero di tweet positivi trovati, pubblicati dall'utente user
	 */
	public long getPositivo (String keyword, String user) throws SolrServerException;

	/**
	 * Ricerca il numero di tweet positivi contenenti la keyword specificata
	 * è possibile specificare un range di date e l'utente che ha pubblicato i tweet.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  user utente che ha pubblicato i tweet
	 * @param  dateStart data di inizio
	 * @param  dateEnd data di fine
	 * @return      Il numero di tweet positivi trovati, pubblicati dall'utente user e scritti dal giorno dateStart al giorno dateEnd
	 */
	public long getPositivo (String keyword, String user, String dateStart, String dateEnd) throws SolrServerException;

	/**
	 * Ricerca il numero di tweet positivi contenenti la keyword specificata
	 * è possibile specificare un range di date 
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  dateStart data di inizio
	 * @param  dateEnd data di fine
	 * @return      Il numero di tweet positivi trovati, scritti dal giorno dateStart al giorno dateEnd
	 */
	public long getPositivo (String keyword,String dateStart, String dateEnd) throws SolrServerException;
	/**
	 * Ricerca il numero di tweet negativi contenenti la keyword specificata
	 *
	 * @param  keyword parola chiave da ricercare
	 * @return      Il numero di tweet negativi trovati
	 */
	public long getNegativo (String keyword)throws SolrServerException;
	/**
	 * Ricerca il numero di tweet negativi contenenti la keyword specificata
	 * è possibile specificare l'utente che ha pubblicato i tweet.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  user utente che ha pubblicato i tweet

	 * @return      Il numero di tweet negativi trovati, pubblicati dall'utente user
	 */
	public long getNegativo (String keyword, String user)throws SolrServerException;

	/**
	 * Ricerca il numero di tweet negativi contenenti la keyword specificata
	 * è possibile specificare un range di date e l'utente che ha pubblicato i tweet.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  user utente che ha pubblicato i tweet
	 * @param  dateStart data di inizio
	 * @param  dateEnd data di fine
	 * @return      Il numero di tweet negativi trovati, pubblicati dall'utente user e scritti dal giorno dateStart al giorno dateEnd
	 */
	public long getNegativo (String keyword, String user,String dateStart, String dateEnd)throws SolrServerException;
	/**
	 * Ricerca il numero di tweet negativi contenenti la keyword specificata
	 * è possibile specificare un range di date
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  dateStart data di inizio
	 * @param  dateEnd data di fine
	 * @return      Il numero di tweet negativi trovati, scritti dal giorno dateStart al giorno dateEnd
	 */
	public long getNegativo (String keyword, String dateStart, String dateEnd)throws SolrServerException;
	/**
	 * Ricerca il numero di tweet neutri contenenti la keyword specificata
	 *
	 * @param  keyword parola chiave da ricercare
	 * @return      Il numero di tweet neutri trovati
	 */
	public long getNeutro (String keyword)throws SolrServerException;
	/**
	 * Ricerca il numero di tweet neutri contenenti la keyword specificata
	 * è possibile specificare l'utente che ha pubblicato i tweet.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  user utente che ha pubblicato i tweet
	 * @return      Il numero di tweet neutri trovati, pubblicati dall'utente user
	 */
	public long getNeutro (String keyword, String user)throws SolrServerException;
	/**
	 * Ricerca il numero di tweet neutri contenenti la keyword specificata
	 * è possibile specificare un range di date e l'utente che ha pubblicato i tweet.
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  user utente che ha pubblicato i tweet
	 * @param  dateStart data di inizio
	 * @param  dateEnd data di fine
	 * @return      Il numero di tweet neutri trovati, pubblicati dall'utente user e scritti dal giorno dateStart al giorno dateEnd
	 */
	public long getNeutro (String keyword, String user, String dateStart, String dateEnd)throws SolrServerException;
	/**
	 * Ricerca il numero di tweet neutri contenenti la keyword specificata
	 * è possibile specificare un range di date
	 *
	 * @param  keyword parola chiave da ricercare
	 * @param  dateStart data di inizio
	 * @param  dateEnd data di fine
	 * @return      Il numero di tweet neutri trovati, scritti dal giorno dateStart al giorno dateEnd
	 */
	public long getNeutro (String keyword, String dateStart, String dateEnd)throws SolrServerException;


}
