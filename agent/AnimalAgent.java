package com.farmSystem.farmSystem.agent;

import java.util.ArrayList;

import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import com.farmSystem.farmSystem.entity.Animal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class AnimalAgent extends Agent{
	
	private AnimalOntology animalOntology;
	
	@Override
	protected void setup() {
		
		animalOntology = new AnimalOntology();
		
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		
		ServiceDescription sd = new ServiceDescription();
		sd.setType("animall");
		sd.setName("yammu animall");
		
		dfd.addServices(sd);
		
		try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addBehaviour(new AnimalReqestBehaviour());
		
	}
	
	public void createNewAnimalType(String animalType) throws OWLOntologyStorageException {
		animalOntology.addAnimalType(animalType);
	}
	
	public void addBreedToAnimal(String animalType, String animalBreed) throws OWLOntologyStorageException {
		animalOntology.addBreedToAnimal(animalType, animalBreed);
	}

	private class AnimalReqestBehaviour extends CyclicBehaviour{

		@Override
		public void action() {
			MessageTemplate mt = MessageTemplate.
					MatchPerformative(ACLMessage.CFP);
			
			ACLMessage msg = receive(mt);
			
			if(msg != null) {
				
				
				String animalBreed = msg.getContent();
				System.out.println("Someone serch animal with.. " + animalBreed);
				
				ACLMessage reply = msg.createReply();
				
				ArrayList<Animal> result = 
						animalOntology.getAnimalByBreed(animalBreed);
				
				if(result.size() > 0) {
					
					System.out.println("I have this breed...");
					ObjectMapper mapper = new ObjectMapper();
					
					reply.setPerformative(ACLMessage.PROPOSE);
					
					try {
						reply.setContent(
								mapper.writeValueAsString(result));
					
						reply.setLanguage("JSON");
						
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}					
				}else {
					reply.setPerformative(ACLMessage.REFUSE);
					reply.setContent("Nooooo");
					System.out.println("Noooo animal");
				}
				
				send(reply);				
			}
			
			
		}		
	}

	public void changeBreedName(String oldName, String newName) {
		animalOntology.renameBreed(oldName, newName);		
	}

	public void deleteAnimal(String nameToDelete) {
		animalOntology.removeAnimal(nameToDelete);		
	}
	
}
