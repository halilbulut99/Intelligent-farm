package com.farmSystem.farmSystem.agent;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.coode.owlapi.rdfxml.parser.DataQualifiedCardinalityTranslator;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyChange;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.util.OWLEntityRemover;
import org.semanticweb.owlapi.util.OWLEntityRenamer;

import com.farmSystem.farmSystem.entity.Animal;

public class AnimalOntology {

	private OWLOntologyManager ontoManager;
	private OWLOntology animalOntology;
	private OWLDataFactory dataFactory;
	private OWLReasoner reasoner;
	
	private String ontologyIRIStr;
	private boolean contains = false;
	
	public AnimalOntology() {
		ontoManager = OWLManager.createOWLOntologyManager();
		dataFactory = ontoManager.getOWLDataFactory();
		
		loadOntologyFromFile();
		
		ontologyIRIStr = animalOntology.getOntologyID()
				.getOntologyIRI().toString() + "#";
		
	}
	
	private void loadOntologyFromFile() {
		File ontoFile = new File("src/main/java/com/farmSystem/farmSystem/ontology/farm10.owl");
		
		try {
		
			animalOntology = ontoManager
					.loadOntologyFromOntologyDocument(ontoFile);
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Animal> getAnimalByBreed(String breed){
		
		ArrayList<Animal> result = new ArrayList<>();
		
		OWLObjectProperty hasBreed = dataFactory
				.getOWLObjectProperty(
						IRI.create(ontologyIRIStr + "hasBreed"));
		OWLClass nameBreedClass = dataFactory.getOWLClass(
				IRI.create(ontologyIRIStr + breed));
		
		//��������� ������ ������� �� �����
		for(OWLAxiom axiom : 
			nameBreedClass.getReferencingAxioms(animalOntology)) {
			
			//������� ��� �������� ���� ������� ����� �� ��� ����
			if(axiom.getAxiomType() == AxiomType.SUBCLASS_OF) {

				//������� ������ �������� �� ������������ �������
				for(OWLObjectProperty op: 
					axiom.getObjectPropertiesInSignature()) {
					
					//����������� ��� �������� IRI � ���� ����� ������
					if(op.getIRI().equals(hasBreed.getIRI())) {
						
						//������� ������ ������� �� ���������
						for(OWLClass classInAxiom: 
							axiom.getClassesInSignature()) {
							
							if(containsSuperClass(
									classInAxiom.getSuperClasses(animalOntology),
									dataFactory.getOWLClass(
											IRI.create(ontologyIRIStr + "Animal")))) {
								
								contains = false;
								
								Animal p = new Animal();
								p.setName(getClassFriendlyName(classInAxiom));
								p.setIdAsString(classInAxiom.getIRI().toQuotedString());
								
								p.setBreed(getAllBreeds(classInAxiom
										, hasBreed));
								
								result.add(p);							
							}
							
						}
					}
					
				}
				
			}
			
		}
		
		return result;
	}
	
	private List<String> getAllBreeds(OWLClass animalClass, OWLObjectProperty hasBreed) {

		List<String> breeds = new ArrayList<>();
		
		for(OWLAxiom axiom : 
			animalClass.getReferencingAxioms(animalOntology)) {
						
			if(axiom.getAxiomType() == AxiomType.SUBCLASS_OF) {
				
				for(OWLObjectProperty op : 
					axiom.getObjectPropertiesInSignature()) {
					
					if(op.getIRI().equals(hasBreed.getIRI())) {
						
						for(OWLClass classInAxiom: 
							axiom.getClassesInSignature()) {
							
							if(!classInAxiom.getIRI().equals(animalClass.getIRI())) {
								breeds.add(getClassFriendlyName(classInAxiom));
							}
							
						}
					}
				}
			}			
		}
		
		
		return breeds;
	}

	private boolean containsSuperClass(
			Set<OWLClassExpression> setOfClasses,
			OWLClass superClass) {
		
		for(OWLClassExpression clsExps : setOfClasses) {
			
			for(OWLClass cls : clsExps.getClassesInSignature()) {
				if(cls.getIRI().equals(superClass.getIRI())) {
					contains = true;
				}else {
					if(cls.getSubClasses(animalOntology).size() > 0) {
						containsSuperClass(
								cls.getSuperClasses(animalOntology), 
								superClass);
					}
				}
			}
			
		}	
		return contains;
	}
	
	private String getClassFriendlyName(OWLClass cls) {
		
		String label = cls.getIRI().toString();
		label = label.substring(label.indexOf('#') + 1);
		
		return label;	
		
	}
	
	private void saveOntology() {
		try {
			ontoManager.saveOntology(animalOntology);
		} catch (OWLOntologyStorageException e) {
			e.printStackTrace();
		}
	}
	
	
	public void addAnimalType(String animalTypeName) {
		//Класът който ще създаваме
		OWLClass animalClass = dataFactory.getOWLClass(
				IRI.create(ontologyIRIStr + animalTypeName));
		//Неговият родител (къде ще го сложим)
		OWLClass paretClass = dataFactory.getOWLClass(
				IRI.create(ontologyIRIStr + "NameBreed"));
	
		//Създаваме SubClass аксиома за връзка между двата класа
		OWLSubClassOfAxiom subClassOf = dataFactory.
				getOWLSubClassOfAxiom(animalClass, paretClass);
		
		//Създаваме аксиома
		AddAxiom axiom = new AddAxiom(animalOntology, subClassOf);
		//Добавяме е към манаджера
		ontoManager.applyChange(axiom);
		//Записваме промените в онотологията
		saveOntology();		
	}
	
	public void addBreedToAnimal(String animalTypeName,
			String breedName) {
		//Животното към която ще слагам порода
		OWLClass animalCls = dataFactory.getOWLClass(
				IRI.create(ontologyIRIStr + animalTypeName));
		//Порода която ще сложа на животното.
		OWLClass  nameBreedCls = dataFactory.getOWLClass(
				IRI.create(ontologyIRIStr + breedName));
		//Достъпваме пропертито което овръзва породата с животното.
		OWLObjectProperty hasBreed = dataFactory.
				getOWLObjectProperty(IRI.create(
						ontologyIRIStr + "hasBreed"));
		//Правим експресион за обвързването
		OWLClassExpression clssExpression = dataFactory.
				getOWLObjectSomeValuesFrom(hasBreed, nameBreedCls);
		
		//Правим събклас аксиом
		OWLSubClassOfAxiom axiom = dataFactory.
				getOWLSubClassOfAxiom(animalCls,clssExpression);
		
		//Създаваме аксиом за добавяне
		AddAxiom addAxiom = new AddAxiom(animalOntology, axiom);
		//Добавяме аксиома
		ontoManager.applyChange(addAxiom);
		//Записваме онтологията
		saveOntology();
		
		
	}
	
	public void renameBreed(String oldName, String newName) {
		//Взимаме инстанция на класа който ще преименуваме
		OWLClass oldClass = dataFactory.getOWLClass(
				IRI.create(ontologyIRIStr + oldName));
		
		//Създаваме renamer който ще се използва за преименуване
		OWLEntityRenamer renamer = 
				new OWLEntityRenamer(ontoManager, 
						Collections.singleton(animalOntology));
		
		//Взимаме старото IRI
		IRI oldIRI = oldClass.getIRI();
		//Създаване новото IRI
		IRI newIRI = IRI.create(oldIRI.getNamespace(), newName);
		
		//Стартираме преименуванете и взимаме инстанция на всички промени
		List<OWLOntologyChange> changes = renamer.changeIRI(oldIRI, newIRI);
		//Обновяваме онтологията
		ontoManager.applyChanges(changes);
		//Записваме промените
		saveOntology();
	}
	
	public void removeAnimal(String name) {
		//Класът който ще изтриваме
		OWLClass animalToRemove = dataFactory.getOWLClass(
				IRI.create(ontologyIRIStr + name));
		//Създаваме remover
		OWLEntityRemover remover = 
				new OWLEntityRemover
					(ontoManager, Collections.singleton(animalOntology));
	
		//Начало на премахване
		animalToRemove.accept(remover);
		
		//Завършваме премахването
		ontoManager.applyChanges(remover.getChanges());
		
		//Записваме промените
		saveOntology();
	}
	
	
}
