/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

/**
 *
 * @author Lenovo G470
 */
public class ValidaRut {
    
    public static boolean validarRut(String vRut) 
    { 
        if(vRut.length() < 9 || vRut.length() > 10){
            return false;
        }
            
        String vverificador ="-1";
        
        String[] parts = vRut.split("-");
        
        String vrut = parts[0]; // 123
        try {
           vverificador = parts[1]; // 654321
        } catch (Exception e) {
            return false;
        }
        ////valido que ingrese mas de 1 -
         
        
        try {
        boolean flag = false; 
        String rut = vrut.trim(); 

        String posibleVerificador = vverificador.trim(); 
        int cantidad = rut.length(); 
        int factor = 2; 
        int suma = 0; 
        String verificador = ""; 

        for(int i = cantidad; i > 0; i--) 
        { 
            if(factor > 7) 
            { 
                factor = 2; 
            } 
            suma += (Integer.parseInt(rut.substring((i-1), i)))*factor; 
            factor++; 

        } 
        verificador = String.valueOf(11 - suma%11); 
        if(verificador.equals(posibleVerificador)) 
        { 
            flag = true; 
        } 
        else 
        { 
            if((verificador.equals("10")) && (posibleVerificador.toLowerCase().equals("k"))) 
            { 
                flag = true; 
            } 
            else 
            { 
                if((verificador.equals("11") && posibleVerificador.equals("0"))) 
                { 
                    flag = true; 
                } 
                else 
                { 
                    flag = false; 
                } 
            } 
        } 
        return flag; 
        } catch (Exception e) {
            return false;
        }        
    } 
    
}
