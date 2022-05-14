package com.javarevolutions.ws.rest.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.javarevolutions.ws.rest.vo.Paquet;

public class ServiceXat {
	ServerSocket ss; 
	Socket s; 
	BufferedReader br; 
	PrintWriter pw;
	
	void enviaText(String nom,String IP,String missatge) throws UnknownHostException, IOException {
		Socket mySocket = new Socket("127.0.0.1",9999); 
		Paquet enviament = new Paquet(nom,IP,missatge); 
		ObjectOutputStream envioDades = new ObjectOutputStream(mySocket.getOutputStream());
		envioDades.writeObject(enviament);
		
	}
	
	public void run() throws IOException {
		Socket rebutReceptor = new Socket("127.0.0.1",999); 
		String nom,ip,missatge; 
		Paquet paquetRebut; 
		
		while (true) {
			ObjectInputStream input = new ObjectInputStream(rebutReceptor.getInputStream()); 
			try {
				paquetRebut = (Paquet)input.readObject();
				ip = paquetRebut.getIp(); 
				nom = paquetRebut.getNom(); 
				missatge = paquetRebut.getMissatge(); 
				Socket envioDestinatari = new Socket("127.0.0.1",999); 
				ObjectOutputStream output = new ObjectOutputStream(envioDestinatari.getOutputStream()); 
				output.writeObject(paquetRebut); 
				envioDestinatari.close();
				rebutReceptor.close(); 
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
}
