package src;
import java.util.InputMismatchException;
import java.util.Scanner;
public class prueba {

    public prueba() {
    }
    
    public void probar(){
        Scanner entrada = new Scanner(System.in);
        boolean control = true;
        int num=0;
        while(control){
            try{
                System.out.println("Ingresa un número");
                 num = entrada.nextInt();
                control=false;
            }catch(InputMismatchException e){
                System.out.println("Caracter invalido");
                entrada.next();
            }
        }
        
        System.out.println("el numero es "+num);
    }
    
}
