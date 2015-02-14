package it.uniroma3.dia.tartbrain.controller;


import it.uniroma3.dia.tartbrain.facade.RoleFacade;
import it.uniroma3.dia.tartbrain.facade.SolrFacade;
import it.uniroma3.dia.tartbrain.facade.UserFacade;
import it.uniroma3.dia.tartbrain.model.Role;
import it.uniroma3.dia.tartbrain.model.User;
import it.uniroma3.dia.tartbrain.util.MyLog4J;

import java.text.ParseException;
import java.util.List;
















import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.solr.client.solrj.SolrServerException;

@ManagedBean
@SessionScoped
public class UserController {

	private String username;
	private String email;
	private String password;
	private String newUsername;
	private String newEmail;
	private String newPassword;
	private Long id;
	private User user;
	private User newUser;
	private Role role;
	private Role newRole;
	private String message;
	private boolean AUTHENTICATED;
	private boolean isAdmin;
	private  List<Role> roles;
	
	//per la pagina iniziale
	private long positivi;
	private long negativi;
	private long neutri;
	private long nResults;
	private String dataMinore;
	private List<List<String>> datiChart;
	private List<List<String>> datiChartTotali;
	private ChartCreator chartCreator;

	
	private List<User> users;

	@EJB
	private UserFacade userFacade;
	@EJB
	private RoleFacade roleFacade;
	@EJB
	private MyLog4J myLog4J;
	
	

	public String createUser() {
		//myLog4J.getLog().debug(this.newRole.getType());
		this.newUser=userFacade.createUser(newEmail, newPassword,newUsername,newRole);
		this.newEmail=null;
		this.newPassword=null;
		this.newUsername=null;
		this.newRole=null;
		
		//this.id = user.getId();
		return this.usersList(); 
	}

	public String loginUser() throws SolrServerException, ParseException {

		//writing some logs at different levels
		//myLog4J.getLog().debug("login");
		//log.debug("Test Livello DEBUG");
		//log.info("Test Livello INFO");
		//log.warn("Test Livello WARNING");
		//log.error("Test Livello ERROR");
		//log.fatal("Test Livello FATAL");
		//myLog4J.getLog().debug(AUTHENTICATED);
		this.roles=this.roleFacade.getAllRole();
		this.user = userFacade.loginUser(username, password);
		if(this.user == null){
			this.message="Username o Password errati";
			return "login";
		}
		else{

			this.AUTHENTICATED=true; 
			this.chartGenerator();
			
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
			session.setAttribute("AUTHENTICATED", this.AUTHENTICATED);
			this.role=user.getRole();
			this.email=user.getEmail();
			this.message = "Benvenuto "+this.username+"!";
			if(this.role.getType().equals("Amministratore"))
				this.isAdmin=true;
			else
				this.isAdmin=false;
			return "indexU";
		}
		

	}

	public String logoutUser(){	

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.invalidate();

		return "login";

	}

	private void chartGenerator() throws SolrServerException, ParseException{
		this.chartCreator=new ChartCreator("*:*", null, "", "", "qualsiasi");
		this.datiChart=this.chartCreator.generaDatiSentimenti();
		this.datiChartTotali=this.chartCreator.generaDatiTotali();
		this.positivi=this.chartCreator.getSentimento("positivo");
		this.negativi=this.chartCreator.getSentimento("negativo");
		this.neutri=this.chartCreator.getSentimento("neutro");
		this.nResults=this.chartCreator.getSentimento("qualsiasi");
		this.setDataMinore(this.chartCreator.dataMinore());
		
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	//lista utenti
	public String usersList(){
		this.users=userFacade.getAllUser();
		this.users.remove(this.user);
		return "userList";
	}
	
	public String userProfile(){
		this.email=this.user.getEmail();
		this.newRole=this.user.getRole();
		this.password=this.user.getPassword();
		this.username=this.user.getUsername();
		return "user";
	}
	
	

	public String findUser(Long id){
		this.setNewUser(userFacade.getUser(id));
		return "user";
	}


	
	public String deleteUser (){
		if (!this.user.equals(this.userFacade.getUser(this.id)))
		this.userFacade.deleteUser(this.id);
		this.newEmail=null;
		this.newPassword=null;
		this.newUsername=null;
		this.newRole=null;
		return this.usersList();
		
	}
	
	public String setEditUser(){
		
		this.newUser=this.userFacade.getUser(this.id);
		this.newEmail=this.newUser.getEmail();
		this.newRole=this.newUser.getRole();
		this.newPassword=this.newUser.getPassword();
		this.newUsername=this.newUser.getUsername();
		return "editUser";
	}
	
	
	public String editUser(){
		
		this.newUser.setEmail(this.newEmail);
		this.newUser.setPassword(this.newPassword);
		this.newUser.setRole(this.newRole);
		this.newUser.setUsername(this.newUsername);
		this.userFacade.updateUser(newUser);			
		this.newEmail=null;
		this.newPassword=null;
		this.newUsername=null;
		this.newRole=null;
		
		
		return this.usersList();
	}
	


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getNewRole() {
		return newRole;
	}

	public void setNewRole(Role role) {
		this.newRole = role;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNewUsername() {
		return newUsername;
	}

	public void setNewUsername(String newUsername) {
		this.newUsername = newUsername;
	}

	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public ChartCreator getChartCreator() {
		return chartCreator;
	}

	public void setChartCreator(ChartCreator chartCreator) {
		this.chartCreator = chartCreator;
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


	public long getNeutri() {
		return neutri;
	}

	public void setNeutri(long neutri) {
		this.neutri = neutri;
	}

	public long getNegativi() {
		return negativi;
	}

	public void setNegativi(long negativi) {
		this.negativi = negativi;
	}

	public long getPositivi() {
		return positivi;
	}

	public void setPositivi(long positivi) {
		this.positivi = positivi;
	}

	public long getnResults() {
		return nResults;
	}

	public void setnResults(long nResults) {
		this.nResults = nResults;
	}

	public String getDataMinore() {
		return dataMinore;
	}

	public void setDataMinore(String dataMinore) {
		this.dataMinore = dataMinore;
	}






}
