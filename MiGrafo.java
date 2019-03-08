package habitable;

import java.util.ArrayList;

/**
 * En esta clase se demuestra como funciona la clase Grafo Comenzaremos creando
 * un objeto Grafo vacío. Se crean 6 vértices: V{A, B, C, D, E, F} y sus
 * adyacencias insertando aristas de tal forma que {A, B, C} y {D, E, F} forman
 * dos triángulos isósceles, a continuación, el vértice A se une con el vértice
 * D; y el C con el E; de esta forma quedan comunicados ambos triángulos.
 */
public class MiGrafo {

    public static void main(String[] args) {
        MiGrafo eje = new MiGrafo();
        Vertice emisor = new Vertice("A");
        emisor.setRuido(5);
        eje.inicio();

        eje.expansion(emisor, emisor.getRuido(), 0);
    }

    Grafo miGrafo = new Grafo();
    Vertice[] vertices = new Vertice[6];
    Vertice[] veci = new Vertice[5];

    char[] etiquetas = {'A', 'B', 'C', 'D', 'E', 'F'};
    int i;
    boolean sobreescribe = true;
    int p = 5;
    int a = 0;
    int g = 0;
    
    public void expansion(Vertice emisor, int ruido, int con) {

        for (i = 0; i < vertices.length; i++) {
            if (emisor.equals(vertices[i])) {
                a = i;
                System.out.println("/*/*/*/*Vertice de entrada: " + vertices[i]);
            }
        }
        
        try {
//// FASE 1
            for (Arista arista : vertices[a].getVecinos()) {
                //arista.getVertice2().getEtiqueta().equals(Character.toString(etiquetas[2]))
                System.out.println("||||||  " + vertices[a]);
                if (arista.getPeso() == 1) {
                    //System.out.println("dd " + arista.getVecinoDe(vertices[a]));
                    veci[g] = arista.getVecinoDe(vertices[a]);
                    g++;
//                miGrafo.eliminarArista(arista);
//                miGrafo.insertarArista(vertices[a], arista.getVecinoDe(vertices[a]), (ruido-con)); 
                    System.out.println("// " + arista.getVecinoDe(vertices[a]));
                    //lvlRuido(arista.getVecinoDe(vertices[a]), p);
                    //expansion(arista.getVecinoDe(vertices[a]), arista.getVecinoDe(vertices[a]).getRuido() , (con+1)); //Recursividad
                }
            }
            System.out.println("el emisor tiene "+ g + " Numero de vecinos");
            int t = g;
            boolean ya = false;
// FASE 2 agrego los demas vecinos a la lista
            for (int i = 0; i < veci.length; i++) {       //
                System.out.println("valor ///// " + veci[i]);
                //System.out.println("vecinos " + veci[i].getVecinos());
                for (Arista arista : veci[i].getVecinos()) {
                    System.out.println("2 ||///|| " + arista.getVecinoDe(veci[i]) + " Valor de g " + g);
                    for (int m = 0; m < g; m++) {
                        if (arista.getVecinoDe(veci[i]).getEtiqueta().equals(veci[m].getEtiqueta()) || arista.getVecinoDe(veci[i]).getEtiqueta().equals(vertices[a].getEtiqueta()) ) {
                            ya = true;
                            //System.out.println("**********entre "+veci[m].getEtiqueta());
                        }
                    }
                    if (g < veci.length & ya == false) {
                        veci[g] = arista.getVecinoDe(veci[i]);
                        System.out.println("Agregue a " +arista.getVecinoDe(veci[i]) +" en la posicion: " + g);
                        g++;
                    }
                    ya = false;
                }
            }

            System.out.println("Vector vecinos: ");
            for (int i = 0; i < veci.length; i++) {
                System.out.println("***** " + veci[i]);

            }
//// FOR 3
            for (int i =0; i<veci.length;i++){
                System.out.println("Vertice en analisis "+ veci[i]);
                if(i>=t){ // condicional para controlar el peso de las aristas
//                    System.out.println("aumente "+ i + " "+ con);
                    con++;
                }
                //System.out.println("contador "+ con + " valor de i " + i );//+ " ////" +veci[i].getVecinos());
                for(Arista arista: veci[i].getVecinos()){
                   
                    if(arista.getPeso()==1 || i< veci.length){ // el error está aca
                        System.out.println("fase 3 conexion con: "+  arista.getVecinoDe(veci[i]) + " peso "+ (ruido-con));
                        miGrafo.eliminarArista(arista);
                        miGrafo.insertarArista(veci[i], arista.getVecinoDe(veci[i]), (ruido-con)); 
                        //miGrafo.insertarArista(arista.getVecinoDe(veci[i]), veci[i] );
                    }
                    System.out.println("-----------------------------------------------------");
                    lvlRuido(veci[i], (ruido-con));
                }
            }
            
            System.out.println("Modificacion de aristas ");

            
        for (i = 0; i < vertices.length; i++) {
            System.out.println(vertices[i]);
            System.out.println("ruido " + vertices[i].getRuido());
            for (int k = 0; k < vertices[i].getContarVecinos(); k++) {
                System.out.println(vertices[i].getVecino(k));
            }
        }
        } catch (NullPointerException e) {
            System.out.println("Error " + e.getMessage());
        }

    }

    public int lvlRuido(Vertice receptor, int peso) {
        int ruido = 0;

        receptor.setRuido(ruido + (peso - 1));
        return ruido;
    }

    public void inicio() {

        for (i = 0; i < vertices.length; i++) {
            vertices[i] = new Vertice(Character.toString(etiquetas[i]));
            vertices[i].setRuido(0);
            //System.out.println(vertices[i]);	    	
        }
        System.out.println();
//miGrafo.insertarArista(ver[0][0], ver[0][1],5); // a b
        // miGrafo.insertarArista(ver[0][0], ver[1][0],3); // a g

        miGrafo.insertarArista(vertices[0], vertices[1], 1); // A -> B
        miGrafo.insertarArista(vertices[0], vertices[5], 1); // A -> F
        miGrafo.insertarArista(vertices[5], vertices[2], 1); // F -> C
        miGrafo.insertarArista(vertices[4], vertices[3], 1); //  E ->D
        miGrafo.insertarArista(vertices[1], vertices[2], 1); // B -> C
//        miGrafo.insertarArista(vertices[1], vertices[5], 1); // B -> F
        miGrafo.insertarArista(vertices[4], vertices[5], 1); // E -> F
        miGrafo.insertarArista(vertices[2], vertices[3], 1); // C-> D

        
//        
        
        
        
        
    }

}

//        for(i =0; i < vertices.length ;i++){ Con esto recorro todos los vertices y les cambio su valor a las aristas 
//            for(Arista arista : vertices[i].getVecinos())
//	    { 
//                //arista.getVertice2().getEtiqueta().equals(Character.toString(etiquetas[2]))
////                System.out.println("Veci " + arista.getVecinoDe(vertices[i]));
//		if(  arista.getPeso() == 1){
//                    //System.out.println("dd " + arista.getVecinoDe(vertices[1]));
//		    miGrafo.eliminarArista(arista);
//                    miGrafo.insertarArista(vertices[i], arista.getVecinoDe(vertices[i]), p);
//                    p=p+3;
//                }
//	    }
//        }
