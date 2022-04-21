package com.javarevolutions.ws.rest.vo;

import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Image {
     private JFileChooser selectorImage;
     
     private void errorImagen(){
          JOptionPane.showMessageDialog(null,"No se pudo cargar la imagen","Error",JOptionPane.ERROR_MESSAGE);
     }
     
     private boolean abrirJFileChooser(){
        this.selectorImage=new JFileChooser();
        this.selectorImage.setDialogTitle("Choose an image");
        int flag=this.selectorImage.showOpenDialog(null);
        if (flag==JFileChooser.APPROVE_OPTION){
            return true;
        }else{
           return false;
        }
    }   
     
    public BufferedImage abrirImagenLocal(){
        BufferedImage imagenRetorno=null;
        if(this.abrirJFileChooser()==true){
            try {
                imagenRetorno = ImageIO.read(this.selectorImage.getSelectedFile());
            } catch (Exception e) {
                errorImagen();
            }
        }        
        return imagenRetorno;
    }
    
    public BufferedImage abrirImagenURL(String URLimagen){
        BufferedImage imagenRetorno=null;
        try {
            URL url = new URL(URLimagen);
            imagenRetorno = ImageIO.read(url);
        } catch (Exception e) {
            errorImagen();
        }
        return imagenRetorno;
    }
}
