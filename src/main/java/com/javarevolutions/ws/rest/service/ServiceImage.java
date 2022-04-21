package com.javarevolutions.ws.rest.service;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.javarevolutions.ws.rest.database.Database;

@Path("/images")
public class ServiceImage {
	
	BufferedImage imatge; 
	Database db; 
	
	@POST
	@Path("/writeImage/{path}")
	public void writeImageInDatabase(@PathParam("path") String path) throws SQLException, IOException {
		FileInputStream is = new FileInputStream(path);
		PreparedStatement st = db.getConnection().prepareStatement("insert into fotos values(null,?,?)");
		st.setString(1, "paisaje.jpg");
		st.setBlob(2, is);
		st.execute();
		is.close();
		st.close();	
	}

	public void ReadImageInDatabase() throws SQLException, IOException {
		PreparedStatement st = db.getConnection().prepareStatement("select * from fotos");
		ResultSet rs = st.executeQuery();
		
		InputStream is = null; 
		
		String nombre = null; 
		while (rs.next()) {
			   nombre = rs.getString(2);

			   Blob bytesImagen = rs.getBlob(3);
			   is = bytesImagen.getBinaryStream();
		}
		FileOutputStream fw = new FileOutputStream("copia_" + nombre);

		byte bytes[] = new byte[1024];
		int leidos = is.read(bytes);
		while (leidos > 0) {
		   fw.write(bytes);
		   leidos = is.read(bytes);
		}
		fw.close();
		is.close();
	}
}

