package com.farmSystem.farmSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

@SpringBootApplication
public class FarmSystemApplication {

	public static void main(String[] args) {
		
		Runtime rt = Runtime.instance();
		
		Profile profile = new ProfileImpl();
		
		profile.setParameter(Profile.MAIN_HOST, "localhost");
		profile.setParameter(Profile.MAIN_PORT, "1099");
		profile.setParameter(Profile.GUI, "true");
		
		AgentContainer mainContainer =  
				rt.createMainContainer(profile);
		
		try {
			AgentController ag = mainContainer.createNewAgent("Fermer", 
					"com.farmSystem.farmSystem.agent.FermerAgent", null);
			
			AgentController ag2 = mainContainer.createNewAgent("Animal",
					"com.farmSystem.farmSystem.agent.AnimalAgent", null);
			
			ag.start();
			ag2.start();
			
		} catch (StaleProxyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SpringApplication.run(FarmSystemApplication.class, args);
	}

}
