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
        Vertice emisor = new Vertice("B");
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
    int a =0;
    int g =0;
    public void expansion(Vertice emisor, int ruido, int con) {
        
        for (i = 0; i < vertices.length; i++) {
            if(emisor.equals(vertices[i])){
                a=i;
                System.out.println("/*/*/*/*Vertice de entrada: " + vertices[i]);
            }
        }
        try{
        for (Arista arista : vertices[a].getVecinos()) {
            //arista.getVertice2().getEtiqueta().equals(Character.toString(etiquetas[2]))
            System.out.println("||||||  " + vertices[a]);
            if (arista.getPeso() == 1) {
                //System.out.println("dd " + arista.getVecinoDe(vertices[a]));
                veci[g] = arista.getVecinoDe(vertices[a]);
                g++;
//                miGrafo.eliminarArista(arista);
//                miGrafo.insertarArista(vertices[a], arista.getVecinoDe(vertices[a]), (ruido-con)); 
                System.out.println("// " + arista.getVecinoDe(vertices[a]) + " / " + con);
                //lvlRuido(arista.getVecinoDe(vertices[a]), p);
                //expansion(arista.getVecinoDe(vertices[a]), arista.getVecinoDe(vertices[a]).getRuido() , (con+1)); //Recursividad
             }
            
        }
            System.out.println("el emisor tiene "+ g + " Numero de vecinos");
        for (int i =0; i<3 ;i++){       //cambiar el valor de 3 veci.length
                System.out.println("val///// " + veci[i]);
                //System.out.println("vecinos " + veci[i].getVecinos());
                for (Arista arista: veci[i].getVecinos()){
                    //System.out.println("2 ||///|| "+ veci[i]);
                    if (g<5){
                    veci[g] = arista.getVecinoDe(veci[i]); 
                    g++;
                    }
                }
            }
            System.out.println("Vector vecinos: ");
            for(int i = 0; i<veci.length; i++){
                System.out.println("***** " + veci[i]);
            }
            for (int i =0; i<veci.length;i++){
                System.out.println("Vertice en analisis "+ veci[i]);
                for(Arista arista: veci[i].getVecinos()){
                    if(arista.getPeso()==1){
                        System.out.println("fase 3 conexion con: "+  arista.getVecinoDe(veci[i]));
                        miGrafo.eliminarArista(arista);
                        miGrafo.insertarArista(vertices[i], arista.getVecinoDe(veci[i]), (ruido-con)); 
                    }
                    System.out.println("-----------------------------------------------------");
                }
            }
            
        System.out.println("Modificacion de aristas ");
        
//            
        //vertices[3].setRuido(5);
        for (i = 0; i < vertices.length; i++) {
            System.out.println(vertices[i]);
            System.out.println("ruido " + vertices[i].getRuido());
            for (int k = 0; k < vertices[i].getContarVecinos(); k++) {
                System.out.println(vertices[i].getVecino(k));
            }
        }
        }catch(NullPointerException e){
            System.out.println("Error " + e.getMessage()); 
       }
    
    }
    public int lvlRuido(Vertice receptor, int peso){
        int ruido=0;
        
        receptor.setRuido(ruido+(peso-1));
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
        miGrafo.insertarArista(vertices[0], vertices[3], 1); // A -> D
        miGrafo.insertarArista(vertices[0], vertices[4], 1); // A -> E
        miGrafo.insertarArista(vertices[1], vertices[2], 1); // B -> C
        miGrafo.insertarArista(vertices[1], vertices[5], 1); // B -> F
        miGrafo.insertarArista(vertices[4], vertices[5], 1); // E -> F
        miGrafo.insertarArista(vertices[2], vertices[3], 1); // C-> D

        //Sacamos las adyacencias de cada vértice
//        for (i = 0; i < vertices.length; i++) {
////            System.out.println(vertices[i]); // Muestra el vertice
//            for (int k = 0; k < vertices[i].getContarVecinos(); k++) {
//                System.out.println(vertices[i].getVecino(k)); // Muestra los vecinos de cada vertice[i]
//                
//            }
//        }

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
