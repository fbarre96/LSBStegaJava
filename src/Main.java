import java.awt.image.*;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Main {
	public static void main(String[] args){
		File fichierImage = new File("C:\\Users\\fabien\\Documents\\pole.png");
		File fichierOutput = new File("C:\\Users\\fabien\\Downloads\\out2.txt");
		FileOutputStream out;
		try {
			out = new FileOutputStream(fichierOutput);
			DataOutputStream outs = new DataOutputStream(out);
		try {
			BufferedImage monImage = ImageIO.read(fichierImage);
			Raster tramePixel = monImage.getRaster();
			ColorModel modeleCouleur = monImage.getColorModel();
			int data=0;
			int bit=0;
			for(int x = 0 ; x < monImage.getWidth() ; x++){
				for(int y = 0 ; y < monImage.getHeight() ; y++){
				
					Object objCouleur   = tramePixel.getDataElements(x, y, null);
					if(bit<8){
						data=data<<1;
						if((modeleCouleur.getRed(objCouleur)&0x1)==0x1)
							data=data|0x1;
						bit++;
						if(bit==8){
							outs.write(data);
							data=0;
							bit=0;
						}
					}
					if(bit<8){
						data=data<<1;
						if((modeleCouleur.getBlue(objCouleur)&0x1)==1)
							data=data|0x1;
						bit++;
						if(bit==8){
							outs.write(data);
							data=0;
							bit=0;
						}
					}
					if(bit<8){
						data=data<<1;
						if((modeleCouleur.getGreen(objCouleur)&0x1)==1)
							data=data|0x1;
						bit++;
						if(bit==8){
							outs.write(data);
							data=0;
							bit=0;
						}
					}
					
				
					/*outs.write(new String(x+","+y+": "+modeleCouleur.getRed(objCouleur)+","+modeleCouleur .getGreen(objCouleur)+","+modeleCouleur.getBlue(objCouleur)+"\r\n").getBytes());
					System.out.println("teinte rouge = "+ modeleCouleur.getRed(objCouleur));
					System.out.println("teinte verte = "+ modeleCouleur .getGreen(objCouleur)) ;
					System.out.println("teinte bleu  = "+ modeleCouleur.getBlue(objCouleur));*/
					
				}
				outs.write(new String("\r\n").getBytes());
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
