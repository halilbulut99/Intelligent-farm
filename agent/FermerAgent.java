package com.farmSystem.farmSystem.agent;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.farmSystem.farmSystem.entity.Animal;
import com.fasterxml.jackson.databind.ObjectMapper;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

@Component
public class FermerAgent extends Agent{

	private FermerAgentGUI gui;
	private String searchedBreed = null;
	private ArrayList<AID> fermers;
	
	@Override
	protected void setup() {
		gui = new FermerAgentGUI(this);
		
		addBehaviour(new TickerBehaviour(this, 20000) {
			
			@Override
			protected void onTick() {
				
				if(searchedBreed != null) {
					System.out.println("Search animal with.. " + 
							searchedBreed);
					
					DFAgentDescription dfd = new DFAgentDescription();
					ServiceDescription sd = new ServiceDescription();
					
					sd.setType("animall");
					
					dfd.addServices(sd);
					
					try {
						
						DFAgentDescription[] descrptions = 
								DFService.search(myAgent, dfd);
						
						fermers = new ArrayList<>();
						
						for(int i = 0; i < descrptions.length; i++) {
							fermers.add(descrptions[i].getName());
						}
						
					} catch (FIPAException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
					
					if(fermers.size() > 0) {
						myAgent.addBehaviour(new SerchingBehaviour());
					}else {
						System.out.println("No farms");
					}
						
				}
				
			}
		});
		
	}

	public String getSearchedBreed() {
		return searchedBreed;
	}

	public void setSearchedBreed(String searchedBreed) {
		this.searchedBreed = searchedBreed;
	}
	
	
	private class SerchingBehaviour extends Behaviour{

		int step = 0;
		MessageTemplate mt;
		int repliesCount = 0;
		
		Animal[] animalArray;
		
		@Override
		public void action() {
			switch(step) {
			case 0:
				
				System.out.println("Serching for animal with.. " + searchedBreed);
				ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
				
				for(int i=0; i < fermers.size(); i++) {
					cfp.addReceiver(fermers.get(i));
				}
				
				cfp.setContent(searchedBreed);
				cfp.setConversationId("animal stuff");
				cfp.setReplyWith("cfp" 
						+ System.currentTimeMillis());
				
				mt = MessageTemplate.and(
						MessageTemplate.MatchConversationId("animal stuff"),
						MessageTemplate.MatchInReplyTo(cfp.getReplyWith())
						);
				
				send(cfp);
				
				step++;
				
				break;
				
			case 1:
				
				ACLMessage reply = receive(mt);
				
				if(reply != null) {
					
					if(reply.getPerformative() == ACLMessage.PROPOSE) {
						ObjectMapper mapper = new ObjectMapper();
						
						try {
							animalArray = mapper.readValue(
									reply.getContent(), Animal[].class);
						
							for(int i = 0; i < animalArray.length; i++) {
								System.out.println(animalArray[i]);
							}
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					repliesCount++;
					
					System.out.println("New answer");
					if(repliesCount >= fermers.size()) {
						System.out.println("The and!");
						step++;
					}
					
				}
				
				break;
				
			}
			
		}

		@Override
		public boolean done() {
			if(step == 2) {		
								
				if(animalArray == null || animalArray.length == 0)
				{
					System.out.println("Oooo No NO");
				}
				
				searchedBreed = null;
				
				removeBehaviour(this);
				
				return true;
			}
			
			return false;
		}
		
	}
}
