package com.farmSystem.farmSystem.agent;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class FermerAgentGUI extends JFrame {

	
	public FermerAgentGUI(FermerAgent agent) {
		
		Container cp = getContentPane();
		
		JTextField breedNameTF = new JTextField();
		
		JButton searchButton = new JButton("Search");
		
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String breedName = breedNameTF.getText();
				
				if(breedName != null && 
						breedName.length() > 0) {
					agent.setSearchedBreed(breedName);
				}
				
			}
		});
		
		Box content = Box.createHorizontalBox();
		
		content.add(Box.createRigidArea(new Dimension(5, 1)));
		content.add(breedNameTF);
		content.add(Box.createRigidArea(new Dimension(5, 1)));
		content.add(searchButton);
		content.add(Box.createRigidArea(new Dimension(5, 1)));
		
		cp.add(content);
		
		this.setSize(300, 100);
		setVisible(true);
	}
	
}
