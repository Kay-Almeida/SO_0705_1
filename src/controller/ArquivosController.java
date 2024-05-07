package controller;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class ArquivosController implements IArquivosController{
	
	public ArquivosController () {
		super(); 
	}

	@Override
	public void readDir(String path) throws IOException {
		File dir = new File(path);
		if(dir.exists() && dir.isDirectory()) {
			File[] files = dir.listFiles(); 
			for(File f: files) {
				if(f.isFile()) {
					System.out.println("     \t"+f.getName());
				}else{
					System.out.println("<DIR>\t"+f.getName());
				}
			}
		}else {
			throw new IOException("Diretório inválido"); 
		}
	}

	@Override
	public void createFile(String path, String nome) throws IOException {
		File dir = new File(path);
		File arq = new File(path, nome);
		if(dir.exists() && dir.isDirectory()) {
			boolean existe = false; 
			if(arq.exists()) {
				existe = true; 
			}
			String conteudo = geraTxt(); 
			FileWriter fileWriter = new FileWriter(arq, existe); 
			PrintWriter print = new PrintWriter(fileWriter); 
			print.write(conteudo);
			print.flush(); 
			print.close();
			fileWriter.close(); 
		}else {
			throw new IOException("Diretório Inválido"); 
		}
	}

	private String geraTxt() {
		StringBuffer buffer = new StringBuffer(); 
		String linha = ""; 
		while(!linha.equalsIgnoreCase("fim")) {
			linha = JOptionPane.showInputDialog(null, "DIGITE UMA FRASE", "ENTRADA DE TEXTO", JOptionPane.INFORMATION_MESSAGE); 
			if(!linha.equalsIgnoreCase("fim")) {
				buffer.append(linha + " "); 
			}
		}
		return buffer.toString();
	}

	@Override
	public void readFile(String path, String nome) throws IOException {
	    File arq = new File(path, nome);
	    if (arq.exists() && arq.isFile()) {
	        try (BufferedReader buffer = new BufferedReader(new FileReader(arq))) {
	            String linha;
	            while ((linha = buffer.readLine()) != null) {
	                String[] campos = linha.split(",");
	                if (campos.length >= 4 && campos[2].equals("Fruits")) {
	                    String foodName = campos[0];
	                    String scientificName = campos[1];
	                    String subGroup = campos[3];
	                    System.out.println(foodName + "\t" + scientificName + "\t" + subGroup);
	                }
	            }
	        }
	    } else {
	        throw new IOException("Arquivo Inválido");
	    }
		
	}

	@Override
	public void openFile(String path, String nome) throws IOException {
		File arq = new File(path, nome);
		if(arq.exists() && arq.isFile()) {
			Desktop desktop = Desktop.getDesktop(); 
			desktop.open(arq);
		}else {
			throw new IOException ("Arquivo Inválido");
		}
	}
	
}
