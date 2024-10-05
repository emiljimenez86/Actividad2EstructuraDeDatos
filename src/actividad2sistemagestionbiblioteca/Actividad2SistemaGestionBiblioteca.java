
package actividad2sistemagestionbiblioteca;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Emil
 */
public class Actividad2SistemaGestionBiblioteca {

    
    public static void main(String[] args) {
        
        ArrayList<String[]> libros = new ArrayList<>();
        LinkedList<String[]> usuarios = new LinkedList<>();
        Stack<String[]> librosAlquilados = new Stack<>();
        Queue<String[]> colaEspera = new LinkedList<>();
        
        Scanner escaner = new Scanner(System.in);
        
        int opcion;
        do{
            System.out.println("Emil Jiménez - 1032247340 - Ingeniería de Software");
            System.out.println("=========================================");
            System.out.println("     SISTEMA DE GESTION DE BIBLIOTECAS     ");
            System.out.println("=========================================");
            System.out.println("1. Agregar Libro");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Alquilar Libro");
            System.out.println("4. Devolver Libro");
            System.out.println("5. Mostrar Libros disponibles");
            System.out.println("6. Mostrar usuarios registrados");
            System.out.println("7. Salir");
            System.out.println("Seleccione una opcion: ");
            
            while(!escaner.hasNextInt()){
                System.out.println("Error en numero de opcion");
                escaner.next();
                System.out.println("Seleccione una opcion");
                
            }
            
            opcion = escaner.nextInt();
            escaner.nextLine();
            switch(opcion){
                case 1:
                    System.out.println("Ingrese el ID del libro: ");
                    String idLibro = escaner.nextLine();
                    boolean idDuplicado= false;
                    for(String[] libro: libros){
                        if(libro[0].equals(idLibro)){
                            idDuplicado = true;
                            break;
                        }
                    }
                    if(idDuplicado){
                        System.out.println("Error: El ID del libro ya existe!!!");
                    }else{
                        System.out.println("Ingrese el nombre del libro: ");
                    String nombreLibro = escaner.nextLine();
                        System.out.println("Ingrese el autor del libro: ");
                        String autorLibro = escaner.nextLine();
                        libros.add(new String []{idLibro, nombreLibro, autorLibro});
                        System.out.println("Libro agregado con éxito");
                        
                    }
                        
                                           
                    break;
                case 2:
                    System.out.println("ingrese la cédula del usuario (Sin puntos): ");
                    
                    while(!escaner.hasNextInt()){
                    System.out.println("Error: Ingrese un numero Valido!!");
                    escaner.next();
                    System.out.println("Ingrese la cédula del usuario con numeros: ");
                    
                    }
                    int cedulaUsuario = escaner.nextInt();
                    escaner.nextLine();
                    System.out.println("Ingrese el nombre de usuario: ");
                    String nombreUsuario = escaner.nextLine();
                    System.out.println("Ingrese los apellidos del usuario: ");
                    String apellidosUsuario = escaner.nextLine();
                    
                    boolean cedulaDuplicado= false;
                    for(String[] usuario: usuarios){
                        if(usuario[0].equals(String.valueOf(cedulaUsuario))){
                            cedulaDuplicado = true;
                            break;
                        }
                    }
                    if(cedulaDuplicado){
                        System.out.println("Error: El usuario ya existe!!!");
                    }else{
                        usuarios.add(new String[]{String.valueOf(cedulaUsuario),nombreUsuario,apellidosUsuario});
                        System.out.println("Usuario registrado exitosamente!!!");
                    }
                    
                    
                    break;
                case 3:
                    System.out.println("Ingrese el Id del libro que desea alquilar: ");
                    String idAlquilar = escaner.nextLine();
                    System.out.println("Ingrese la cédula del usuario que alquila el libro: ");
                      while(!escaner.hasNextInt()){
                    System.out.println("Error: Ingrese un numero Valido!!");
                    escaner.next();
                    System.out.println("Ingrese la cédula del usuario con numeros: ");
                    
                    }
                    int cedulaAlquilar = escaner.nextInt(); 
                    escaner.nextLine();
                    
                    boolean usuarioRegistrado = false;
                    for(String[] usuario:usuarios){
                        if(usuario[0].equals(String.valueOf(cedulaAlquilar))){
                            usuarioRegistrado = true;
                            break;
                            
                        }
                    }
                    if(!usuarioRegistrado){
                        System.out.println("Error el usuario con cédula "+cedulaAlquilar + "No está registrado, No se puede alquilar el libro");
                    }else{
                        boolean libroEncontrado= false;
                        for(String[] libro: libros){
                           if(libro[0].equals(idAlquilar)){
                            librosAlquilados.push(new String[]{idAlquilar,libro[1], libro[2],String.valueOf(cedulaAlquilar)});
                            libros.remove(libro);
                            libroEncontrado = true;
                            System.out.println("Libro alquilado con éxito!!!");
                            break;
                        }
                    }
                    if(!libroEncontrado){
                        System.out.println("Libro no disponible. ¿desea agregar a la cola de espera? (si/no): ");
                        String respuesta = escaner.nextLine();
                        if(respuesta.equalsIgnoreCase("si")){
                            colaEspera.add(new String[]{idAlquilar,String.valueOf(cedulaAlquilar) });
                            System.out.println("Agregado a la cola de espera con éxito");
                        }
                   }
               }
                    break;
                case 4:
                    if(!librosAlquilados.isEmpty()){
                        String[] libroDevuelto = librosAlquilados.pop();
                        libros.add(new String[]{libroDevuelto[0], libroDevuelto[1], libroDevuelto[2]});
                        System.out.println("Libro devuelto exitosamente");
                    }
                    if(!colaEspera.isEmpty()){
                        String[] proximosEnCola = colaEspera.poll();
                        System.out.println("El usuario con cédula "+proximosEnCola[1] + 
                                " esta en cola y ahora alquilará el libro con ID : "+ proximosEnCola[0] );
                        librosAlquilados.push(proximosEnCola);
                    }else{
                        System.out.println("No hay libros prestados");
                    }
                    break;
                case 5:
                    if(libros.isEmpty()){
                        System.out.println("No hay libros disponibles");
                    }else{
                        System.out.println("----   Libros Disponibles   ----");
                        System.out.printf("%-10s %-20s %-30s%n", "ID", "Nombre", "Autor");
                        for(String[] libro: libros){
                            System.out.printf("%-10s %-20s %-30s%n", libro[0], libro[1], libro[2]);
                        }
                    }
                    break;
                case 6:
                    if(usuarios.isEmpty()){
                        System.out.println("No hay usuarios disponibles");
                    }else{
                        System.out.println("----   usuarios Disponibles   ----");
                        System.out.printf("%-10s %-15s %-20s%n", "Cédula", "Nombre", "Apellidos");
                        for(String[] usuario: usuarios){
                            System.out.printf("%-10s %-15s %-20s%n", usuario[0], usuario[1], usuario[2]);
                        }
                    }
                    break;
                case 7:
                    System.out.println("Has salido del menú, muchas gracias por visitar la Biblioteca");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo");
                    break;
                    
            }
            
            
        }while (opcion != 7);
            
            
            
            
            
        }
        
        
}
    

