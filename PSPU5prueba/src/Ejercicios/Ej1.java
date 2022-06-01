package Ejercicios;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Ej1 {
	private static final String ALGORITMO_CLAVE_SIMETRICA = "DESede"; // 3DES
    private static final String FICH_CLAVE = "cifradoDES.encript";
    
    // Nombre del fichero a cifrar
    String mensaje = "Hola Mundo";
    
    // Obtenemos la clave leyendo el fichero donde se encuentra
    byte valorClave[];
    
    void cifrado(){
        try (FileInputStream sClave = new FileInputStream(FICH_CLAVE)) {
			valorClave = sClave.readAllBytes();
		} catch (FileNotFoundException e) {
			System.out.printf("ERROR: no existe fichero de clave %s\n.", FICH_CLAVE);
			return;
		} catch (IOException e) {
			System.out.printf("ERROR: de E/S leyendo clave de fichero %s\n.", FICH_CLAVE);
			return;
		}
        
        try {
			// Obtenemos una clave transparente a partir de la clave ya generada, y luego, a
			// partir de �sta, generemos una clave opaca
			SecretKeySpec keySpec = new SecretKeySpec(valorClave, ALGORITMO_CLAVE_SIMETRICA);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITMO_CLAVE_SIMETRICA);
			SecretKey clave = keyFactory.generateSecret(keySpec);

			// Creamos un objeto Cipher y realizamos la encriptaci�n con la clave privada
			Cipher cifrado = Cipher.getInstance(ALGORITMO_CLAVE_SIMETRICA);
			cifrado.init(Cipher.ENCRYPT_MODE, clave);
			// A partir de aqu� todo lo que se escriba estar� cifrado usando la clave
			String mensajeCifrado=new String(cifrado.doFinal(mensaje.getBytes()));
			System.out.println(mensajeCifrado);
					
			try{
				String fileName = "D:\\Eclipse\\a�a"+"\\"+FICH_CLAVE+".txt";
			    String encoding = "UTF-8";
			    
			    PrintWriter writer = new PrintWriter(fileName, encoding);
			    writer.println(mensajeCifrado);
			    writer.close();
			    }
			    catch (IOException e){
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
			
		} catch (NoSuchAlgorithmException e) {
			System.out.printf("No existe algoritmo de cifrado %s.\n", ALGORITMO_CLAVE_SIMETRICA);
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			System.out.println("Especificaci�n de clave no v�lida.");
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			System.out.println("Clave no v�lida.");
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			System.out.println("Tama�o de bloque no v�lido.");
			e.printStackTrace();
		} catch (BadPaddingException e) {
			System.out.println("Excepci�n con relleno.");
			e.printStackTrace();
		}
    }
    
    
    
    public static void main(String[] args) {
    	Ej1 tarea = new Ej1();
    	tarea.cifrado();
    }
    
}