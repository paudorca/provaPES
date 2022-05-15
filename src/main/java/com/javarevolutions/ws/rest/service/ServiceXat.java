package com.javarevolutions.ws.rest.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.javarevolutions.ws.rest.vo.Paquet;

public class ServiceXat {
	
	public void run() throws IOException {
		try {
			@SuppressWarnings("resource")
			ServerSocket servidor = new ServerSocket(9090); 
			@SuppressWarnings("unused")
			String nom,ip,missatge; 
			Paquet rebut; 
			while (true) {
				Socket client_servidor = servidor.accept(); 
				ObjectInputStream dades_input = new ObjectInputStream(client_servidor.getInputStream()); 
				rebut = (Paquet)dades_input.readObject();
				nom = rebut.getNom(); 
				ip = rebut.getIp(); 
				missatge = rebut.getMissatge(); 
				Socket servidor_client = new Socket(ip,9090); 
				ObjectOutputStream dades_output = new ObjectOutputStream(servidor_client.getOutputStream()); 
				dades_output.writeObject(rebut);
				dades_output.close();
				servidor_client.close();
				client_servidor.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
