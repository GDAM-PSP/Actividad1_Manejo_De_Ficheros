package principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import clases.Articulo;

public class Main {
	
	private static ArrayList<Articulo> articulos = new ArrayList<>();
	private static Scanner sc = new Scanner(System.in);
	private static int id = 0;
	
	public static void main(String[] args) {
		//Comprobamos que existe el fichero articulos.dat
		File fn = new File("articulos.dat");
		if(!fn.exists()) {
			try {
				//Creamos el fichero
				fn.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//Preguntamos si hay mas contenido
					bytesEnBuffer = fr.available();
				}
				
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		int opcion;
		do {
		menu();
		opcion = sc.nextInt();
		sc.nextLine();
		switch(opcion) {
		case 1:
			anadirArticulo();
			break;
		case 2:
			if(borrarArticulo()) {
				System.out.println("El artículo a sido eliminado con éxito");
			}else {
				System.out.println("El id proporcionado no a sido encontrado");
			}
			break;
		case 3:
			break;
		case 4:
			listarArticulos();
			break;
		case 5:
			break;
		}
		}while(opcion != 5);
		try(FileOutputStream file = new FileOutputStream("articulos.dat", false);
				ObjectOutputStream buffer = new ObjectOutputStream(file)){
			for(Articulo c : articulos) {
				buffer.writeObject(c);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("El objeto no a podido ser añadido");
		}
	}
	public static void menu() {
		System.out.println("Elige una opción:");
		System.out.println("Menu:");
		System.out.println("1-Añadir nuevo articulo");
		System.out.println("2-Borrar artículo por ID");
		System.out.println("3-Consulta artículo por ID");
		System.out.println("4-Listado de todos los artículos");
		System.out.println("5.Terminar el programa");
	}
	public static void anadirArticulo() {
		id++;
		System.out.println("Escribe un nombre");
		String nombre = sc.nextLine();
		System.out.println("Escribe una descripción");
		String descripcion = sc.nextLine();
		System.out.println("Escribe una cantidad");
		int cantidad = sc.nextInt();
		System.out.println("Escribe un precio");
		double precio = sc.nextDouble();
				
		Articulo articulo = new Articulo(id,nombre,descripcion,cantidad,precio);
		
		articulos.add(articulo);
	}
	public static boolean borrarArticulo() {
		System.out.println("Escribe un id");
		int idBorrar = sc.nextInt();
		for(Articulo c: articulos) {
			if(c.getId() == idBorrar) {
				System.out.println(c);
				articulos.remove(c);
				return true;
			}
		}
		return false;
	}
	public static void listarArticulos() {
		for(Articulo c: articulos) {
			System.out.println(c);						
		}
	}
}
