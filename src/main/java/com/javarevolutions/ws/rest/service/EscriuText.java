package com.javarevolutions.ws.rest.service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;


import com.javarevolutions.ws.rest.vo.Paquet;

public class EscriuText implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			ServerSocket servidorCliente = new ServerSocket(9090); 
			Socket cliente; 
			Paquet paquetRebut; 
			while(true) {
				cliente = servidorCliente.accept(); 
				ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream()); 
				paquetRebut = (Paquet) entrada.readObject();
			}
		}
		catch(Exception e) {
				
			}
			
		} 
		
}
