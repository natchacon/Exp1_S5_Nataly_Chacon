/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cl.duoc.tarea5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Nataly Chacón
 */
public class TeatroMoro {

    static double DESCUENTO_ESTUDIANTE = 0.9;
    static double DESCUENTO_TERCERA_EDAD = 0.85;
    static String[] opcionesMenuPrincipal = {"Venta de entradas","Promociones","Busqueda de Entradas", "Eliminar Entrada", "Ver Asientos del Teatro", "Salir"};
    static String[] tiposEntrada = {"VIP","Platea","General"};
    static int[] cantidadAsientos = {10,15,20};
    static List reservasVip = new ArrayList();
    static List reservasPlatea = new ArrayList();
    static List reservasGeneral = new ArrayList();
    static int[] tarifaPublicoGeneral = {25000,19000,7200};
    static String[] promociones = {"Tercerta Edad 15% descuento","Estudiante 10% descuento"};
    static Scanner leer = new Scanner(System.in);
    
    public static void promociones(){
        System.out.println("\r\rPromociones disponibles:");
        for(int i=0;i<promociones.length;i++){
            System.out.println(promociones[i]);
        }
        System.out.println("\r\r");
    }
    
    public static Boolean estaDisponibleAsiento(String tipoEntrada, int numeroAsiento){
         if(tipoEntrada.equals(tiposEntrada[0])){
            return !reservasVip.contains(numeroAsiento);
        } else if(tipoEntrada.equals(tiposEntrada[1])){
            return !reservasPlatea.contains(numeroAsiento);
        }  else if(tipoEntrada.equals(tiposEntrada[2])){
            return !reservasGeneral.contains(numeroAsiento);
        } 
        return Boolean.FALSE;
    }


    public static void reservarEntrada(String tipoEntrada, int numeroAsiento){
         if(tipoEntrada.equals(tiposEntrada[0])){
            reservasVip.add(numeroAsiento);
        } else if(tipoEntrada.equals(tiposEntrada[1])){
            reservasPlatea.add(numeroAsiento);
        }  else if(tipoEntrada.equals(tiposEntrada[2])){
            reservasGeneral.add(numeroAsiento);
        } 
    }

    public static String buscarEntrada(Boolean devolverAsiento){
        
        System.out.println("\r\rIngrese el tipo de la entrada que desea: (VIP, Platea, General)");

        //Lectura del tipo de entrada
        Boolean tipoEntradaCorrecta = Boolean.FALSE;
        String tipoEntrada= null;
        do{
        tipoEntrada= leer.nextLine();    
        tipoEntradaCorrecta = (tipoEntrada!=null && (tipoEntrada.equals(tiposEntrada[0]) || tipoEntrada.equals(tiposEntrada[1]) || tipoEntrada.equals(tiposEntrada[2])));
        if(!tipoEntradaCorrecta){
            System.out.println("Tipo de entrada incorrecta. Ingrese VIP, Platea o General");
        }
        } while(!tipoEntradaCorrecta);
        //Mostrar entradas disponibles
        List reservas = null;
        int totalAsientos = 0;
        switch(tipoEntrada){
            case "VIP":
                reservas = reservasVip;
                totalAsientos = cantidadAsientos[0];
                break;
            case "Platea":  
                reservas = reservasPlatea;
                totalAsientos = cantidadAsientos[1];
                break;
            case "General":  
                reservas = reservasGeneral;
                totalAsientos = cantidadAsientos[2];
                break;
            default:
                //Este caso no se dadebido a la validacion previa
                break;
        }
        
        if(reservas.size()==totalAsientos){
          System.out.print("No quedan disponibles asientos de categoria: " + tipoEntrada);
          return buscarEntrada(devolverAsiento);
        } else {
          List asientosDisponibles = new ArrayList();
          System.out.print("Asientos "+ tipoEntrada +" disponibles: ");
          for(int i=0;i<totalAsientos;i++){
               if(estaDisponibleAsiento(tipoEntrada, i+1)){
                  asientosDisponibles.add(i+1);
                  System.out.print("\t"+ (i+1) + "\t");
               }
          }
          if(!devolverAsiento){
             //Es solo buscar asientos disponibles y no seleccionar uno
              return null;
          } else {
              String asientoSeleccionado = null;
              Boolean asientoCorrecto = Boolean.TRUE;
              do {
                System.out.println("\r\rIngrese el numero de asiento o un S para salir");
                asientoSeleccionado = leer.nextLine();
                asientoCorrecto = (esEntero(asientoSeleccionado) && asientosDisponibles.contains(Integer.valueOf(asientoSeleccionado)));
                if(asientoSeleccionado.equals("S")){
                    //Se sale sin seleccionar un asiento
                    return null;
                }
                if(!asientoCorrecto){
                  System.out.println("Asiento incorrecto, ingreso el numero de asiento");
                }
              } while(!asientoCorrecto);
          
              return tipoEntrada + ";" + asientoSeleccionado;  
          }
        }
    }
    
    public static void eliminarEntrada(){
        
        System.out.println("\r\rIngrese el tipo de la entrada que desea eliminar: (VIP, Platea, General)");

        //Lectura del tipo de entrada
        Boolean tipoEntradaCorrecta = Boolean.FALSE;
        String tipoEntrada= null;
        do{
        tipoEntrada= leer.nextLine();    
        tipoEntradaCorrecta = (tipoEntrada!=null && (tipoEntrada.equals(tiposEntrada[0]) || tipoEntrada.equals(tiposEntrada[1]) || tipoEntrada.equals(tiposEntrada[2])));
        if(!tipoEntradaCorrecta){
            System.out.println("Tipo de entrada incorrecta. Ingrese VIP, Platea o General");
        }
        } while(!tipoEntradaCorrecta);
        //Mostrar entradas disponibles
        List reservas = null;
        switch(tipoEntrada){
            case "VIP":
                reservas = reservasVip;
                break;
            case "Platea":  
                reservas = reservasPlatea;
                break;
            case "General":  
                reservas = reservasGeneral;
                break;
            default:
                //Este caso no se dadebido a la validacion previa
                break;
        }
        
        String asientoSeleccionado = null;
        Boolean asientoCorrecto = Boolean.TRUE;
        do {
            System.out.println("\r\rIngrese el numero de asiento aeliminar o un S para salir");
            asientoSeleccionado = leer.nextLine();
            asientoCorrecto = (esEntero(asientoSeleccionado) && reservas.contains(Integer.valueOf(asientoSeleccionado)));
                
            if(!asientoCorrecto){
              System.out.println("Asiento incorrecto, ingreso el numero de asiento a eliminar reserva");
            } else {
              System.out.println("Reserva eliminada");
              reservas.remove(reservas.indexOf(Integer.valueOf(asientoSeleccionado)));
            }
        } while(!asientoCorrecto);        
    }
    
    public static double obtenerTarifa(int posicionTipoEntradaAComprar, boolean esEstudiante, boolean esTerceraEdad){
     int tarifa = tarifaPublicoGeneral[posicionTipoEntradaAComprar];
     if(esTerceraEdad){
      return tarifa * DESCUENTO_TERCERA_EDAD; // 15% de descuento
     } else if (esEstudiante){
         return tarifa * DESCUENTO_ESTUDIANTE; //10% de descuento
     }
     return tarifa;
    }
       
    public static void imprimirAsientos(){
        System.out.print("\r\rAsientos del Teatro Moro: (D)-Disponible (ND)-No Disponible");
        for(int i = 0; i< tiposEntrada.length; i++){                        
            System.out.println("\r\r" +tiposEntrada[i]);
            for(int j = 0; j< cantidadAsientos[i]; j++){
                Boolean disponible = estaDisponibleAsiento(tiposEntrada[i], j+1);
                String estaDisponible = "(D)";
                if(!disponible){
                    estaDisponible = "(ND)";
                }
                System.out.print("\t" + "Asiento_" + (j+1) + " " + estaDisponible);
            }
            
        }
    }
    
    public static boolean esEntero(String numero){
     try{
        Integer.valueOf(numero);
        return true;
     }
     catch(Exception e){
        System.out.println("Ingrese numero entero");
        return false;
     }
    }
    
    public static void comprarEntrada(){
        String asiento = buscarEntrada(Boolean.TRUE);
        if(asiento!=null){
            
            String edad = "";
            Boolean esTerceraEdad = Boolean.FALSE;
            System.out.println("Ingrese su edad:");        
            do {
                edad = leer.next();
            }
            while(!esEntero(edad));   
            
            double tarifaACobrar = 0;
            esTerceraEdad = Integer.valueOf(edad)>=65;
            boolean esEstudiante = false;
            String tipoEntrada = asiento.split(";")[0];
            String numeroAsiento = asiento.split(";")[1];
            int posicionTipoEntradaAComprar = Arrays.asList(tiposEntrada).indexOf(tipoEntrada);
            if(!esTerceraEdad){ // Se consulta si es estudiante solo si no es tercera edad, considerando tercera edad mas de 65 años
                System.out.println("¿Es estudiante? si o no");            
                Boolean continuarCiclo = true;
                
                do {
                    String respuestaEsEstudiante = leer.next();
                    if(respuestaEsEstudiante.equals("si")){
                        tarifaACobrar += obtenerTarifa(posicionTipoEntradaAComprar, true, esTerceraEdad);
                        continuarCiclo = false;
                        esEstudiante = true;
                    } else if(respuestaEsEstudiante.equals("no")) {
                        tarifaACobrar += obtenerTarifa(posicionTipoEntradaAComprar, false, esTerceraEdad);
                        continuarCiclo = false;
                        esEstudiante = false;
                    } else {
                        System.out.println("Debe escribir si o no");
                        continuarCiclo = true;
                        esEstudiante = false;
                    }
                } while(continuarCiclo);
            } else {
                tarifaACobrar += obtenerTarifa(posicionTipoEntradaAComprar, false, esTerceraEdad);
            }
            reservarEntrada(tipoEntrada, Integer.valueOf(numeroAsiento));
            System.out.println("Compró una entrada " + tipoEntrada);
            System.out.println("Precio base " + tarifaPublicoGeneral[posicionTipoEntradaAComprar]);
            if(esTerceraEdad){
               System.out.println("Descuento Aplicado 15% por Tercera Edad");
            }
            else if(esEstudiante){
               System.out.println("Descuento Aplicado 10% por Estudiante");
            }     
            System.out.println("Total a Pagar $" + tarifaACobrar);          
        }
    }
    
    
    public static void main(String[] args) {
        
        boolean mostrarMenu = true;
        String opcionMenuPrincipal = null;
        do {
            opcionMenuPrincipal = null;
            if(mostrarMenu){
                System.out.println("\r");            
                System.out.println("...::: Menu :::...");
                for(int i=0; i<opcionesMenuPrincipal.length;i++){
                System.out.println((i+1) + ") " + opcionesMenuPrincipal[i]);
                }
            }
            System.out.println("Ingrese el numero de su opcion de menú");
            opcionMenuPrincipal = leer.nextLine();
            if(opcionMenuPrincipal!=null && opcionMenuPrincipal.equals("1")){
                comprarEntrada();
                mostrarMenu = true;
            }
            else if(opcionMenuPrincipal!=null && opcionMenuPrincipal.equals("2")){
                promociones();
                mostrarMenu = true;
            }
            else if(opcionMenuPrincipal!=null && opcionMenuPrincipal.equals("3")){
                buscarEntrada(Boolean.FALSE);
                mostrarMenu = true;
            }
            else if(opcionMenuPrincipal!=null && opcionMenuPrincipal.equals("4")){
                eliminarEntrada();
                mostrarMenu = true;
            }
            else if(opcionMenuPrincipal!=null && opcionMenuPrincipal.equals("5")){
                imprimirAsientos();
                mostrarMenu = true;
            }
            else if(opcionMenuPrincipal!=null && !opcionMenuPrincipal.equals("6")){
                System.out.println("Debe ingresar 1,2,3,4,5 o 6");
                mostrarMenu = false;
            } 
        }
        while(!opcionMenuPrincipal.equals("6"));//Se mantiene en el menu principal mientra no seleccione opcion 2) Salir
    
    }
}
