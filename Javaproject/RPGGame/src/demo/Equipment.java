/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package demo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author Administrator
 */
public class Equipment {
       String name;
       String definition;
       int serialNumber;
       int attact;
       int defence;
       int speed;
       int body;
       int price;
       
       public Equipment(int serialNumber){
           equipmentParticular(serialNumber);
       }
       
       public void useEquipment(Player player,int i){
          
          player.attact-=player.equipment2[body].attact;
          player.defence-=player.equipment2[body].defence;
          player.speed-=player.equipment2[body].speed;
          player.equipment2[body].equipmentParticular(player.equip[i]);
          player.attact+=player.equipment2[body].attact;
          player.defence+=player.equipment2[body].defence;
          player.speed+=player.equipment2[body].speed;
          player.equip2[body]=player.equip[i];
          
       }
       
       public void equipmentParticular(int serialNumber){
           try{
                BufferedReader input;
                FileReader reader=null;
                reader = new FileReader("./util/Equipment.txt");
                input=new BufferedReader(reader);
                for(int i=0;i<serialNumber;i++){input.readLine();}
                StringTokenizer intro = new StringTokenizer(input.readLine()," ");
                intro.nextToken();
                name = intro.nextToken();
                attact = Integer.parseInt(intro.nextToken());
                defence = Integer.parseInt(intro.nextToken());
                speed = Integer.parseInt(intro.nextToken());
                body = Integer.parseInt(intro.nextToken());
                price = Integer.parseInt(intro.nextToken());
                definition = intro.nextToken();
                //reader.close();
                input.close(); 
                 }catch (FileNotFoundException ex) {
                 }catch (IOException ex) {}
                 this.serialNumber = serialNumber ;
   }
       
       }

