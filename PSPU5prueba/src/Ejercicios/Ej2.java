package Ejercicios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

class Ej2 {
	private static final String ALGORITMO_CLAVE_SIMETRICA = "DESede"; // 3DES
	private static final String FICH_CLAVE = "cifradoDES.encript";
	
void descifrado() {
	// Nombre del fichero a descifrar
			String mensajeCifrado = "ÃUjçÁ…s¬\"=DŽ½va";

			// Obtenemos la clave leyendo el fichero donde se encuentra
			byte valorClave[];
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
				// partir de ésta, generemos una clave opaca
				SecretKeySpec keySpec = new SecretKeySpec(valorClave, ALGORITMO_CLAVE_SIMETRICA);
				SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITMO_CLAVE_SIMETRICA);
				SecretKey clave = keyFactory.generateSecret(keySpec);
				
				// Creamos un objeto Cipher y realizamos la encriptación con la clave privada
				Cipher cifrado = Cipher.getInstance(ALGORITMO_CLAVE_SIMETRICA);
				cifrado.init(Cipher.DECRYPT_MODE, clave);

				//Desciframos el mensaje
				String mensajeDescifrado=new String(cifrado.doFinal(mensajeCifrado.getBytes()));
				System.out.println(mensajeDescifrado);
				
				try{
					String fileName = "D:\\Eclipse\\aña"+"\\"+FICH_CLAVE+"2.txt";
				    String encoding = "UTF-8";
				    
				    PrintWriter writer = new PrintWriter(fileName, encoding);
				    writer.println(mensajeDescifrado);
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
				System.out.println("Especificación de clave no válida.");
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				System.out.println("Clave no válida.");
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				System.out.println("Tamaño de bloque no válido.");
				e.printStackTrace();
			} catch (BadPaddingException e) {
				System.out.println("Excepción con relleno.");
				e.printStackTrace();
			}
}

public static void main(String[] args) {
	Ej2 tarea2 = new Ej2();
	tarea2.descifrado();
}
	
}