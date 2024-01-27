package clases;

import java.util.ArrayList;
import java.util.Scanner;

public class ArticuloImp extends Articulo {
	private static Scanner sc = new Scanner(System.in);

	public static void listarArticulos(ArrayList<Articulo> art) {
		for (Articulo c : art) {
			System.out.println(c);
		}
	}

	public static void anadirArticulo(ArrayList<Articulo> art) {
		// Obtenemos la ID automaticamente dependiendo del ultimo artículo
		Articulo ultimoArticulo = art.get(art.size() - 1);
		int id = ultimoArticulo.getId() + 1;

		System.out.print("Escribe un nombre: ");
		String nombre = sc.nextLine();

		System.out.print("Escribe una descripción: ");
		String descripcion = sc.nextLine();

		System.out.print("Escribe una cantidad: ");
		int cantidad = sc.nextInt();

		System.out.print("Escribe un precio: ");
		double precio = sc.nextDouble();

		Articulo articulo = new Articulo(id, nombre, descripcion, cantidad, precio);
		art.add(articulo);

		System.out.println("Artículo creado.");
	}

	public static boolean borrarArticulo(ArrayList<Articulo> art) {
		System.out.println("Escribe un id");
		int idBorrar = sc.nextInt();
		for (Articulo c : art) {
			if (c.getId() == idBorrar) {
				System.out.println(c);
				art.remove(c);
				return true;
			}
		}
		return false;
	}

	public static boolean consultarArticulo(ArrayList<Articulo> art) {
		boolean existearticulo = false;
		int id;
		System.out.println("Introduce un ID para buscar por Articulo: ");
		id = sc.nextInt();
		for (Articulo articulo : art) {
			if (articulo.getId() == id) {
				existearticulo = true;
				System.out.println(articulo.toString());
				return existearticulo;
			}
		}
		System.out.println("El articulo con ID " + id + " No Existe");
		return existearticulo;
	}
	
	
	
	
}

