/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podam;

import java.util.Random;
import uk.co.jemos.podam.common.AttributeStrategy;

/**
 * Esta estragia sirve para crear strings random que NO tengan numeros
 * @author jc.montoyar
 */
public class StringConNumerosStrategy implements AttributeStrategy<String>
{   
    @Override
    public String getValue() 
    {
        Random rand = new Random();
        String numero = "12345666789";
        String cadena = "RicuRandomm";
        String cadenaARetornar = "";
        int numeroRandom = rand.nextInt((15 - 4) + 1) + 4;
        for(int i = 0; i< numeroRandom ; i++)
        {
            int indexRandom = rand.nextInt((10 - 0) + 1) + 0;     
            cadenaARetornar = cadenaARetornar + numero.split("")[indexRandom];
            cadenaARetornar = cadenaARetornar + cadena.split("")[indexRandom];
        }
        return cadenaARetornar;
    }
}
