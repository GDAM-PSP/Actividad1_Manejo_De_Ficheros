package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import com.opencsv.CSVWriter;

public class FicherosUtils {
	private static int id = 0;

	public static void lecturaFichero(ArrayList<Articulo> articulos) {
		//Comprobamos que existe el fichero articulos.dat
		File fn = new File("articulos.dat");
		if(!fn.exists()) {
			try {
				//Creamos el fichero
				fn.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("No se a podido crear el fichero");
			}
		}else {
			try(FileInputStream fr = new FileInputStream("articulos.dat");
				ObjectInputStream br = new ObjectInputStream(fr);){
				//Leo los numeros de bytes que hay disponible para leer
				int bytesEnBuffer = fr.available();
				//Leo los articulos
				Articulo articulo;
				while(bytesEnBuffer>0) {
					id++;
					//Leo el articulo x
					articulo  = (Articulo) br.readObject();
					System.out.println("Articulo: "+articulo.toString()+", A sido añadido con éxito");
					//Cargo el artículo al arrayList
					try {
						articulo.setId(id);
						articulos.add(articulo);
					} catch (Exception e) {
						e.printStackTrace();
					}
					//Preguntamos si hay mas contenido
					bytesEnBuffer = fr.available();
				}
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
	}

	public static void exportarArticulosACSV(ArrayList<Articulo> articulos) {
		try (CSVWriter writerCsv = new CSVWriter(new FileWriter("articulos.csv"))) {
			for (Articulo a : articulos) {
				String[] datos = { String.valueOf(a.getId()), a.getNombre(), a.getDescripcion(),
						String.valueOf(a.getCantidad()), String.valueOf(a.getPrecio()) };
				writerCsv.writeNext(datos);
			}
			System.out.println("Artículos exportados a 'articulos.csv' correctamente.");
		} catch (IOException e) {
			System.out.println("Error al escribir el archivo CSV. ¿Puede estar abierto por otro programa?");
		}
	}

}
