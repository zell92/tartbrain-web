package it.uniroma3.dia.tartbrain.util;

import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@Singleton
@Startup
public class MyLog4J {
	private Logger log;
	
	public MyLog4J(){
		//load configuration File
	    PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("").getPath()+"log.properties");


	    //get Logger Instance
	     this.log = Logger.getLogger(MyLog4J.class);
	}

	public Logger getLog() {
		return log;
	}
	
 
}