/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package demo;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
//怪物
public class Monster extends Character {
        int serialNumber=1;
        int[] fallItems = new int[3];
        Image image;
    
       public Monster(){
           image = (new ImageIcon("./util/"+serialNumber+".png")).getImage();//人物头像
       } 
       public Monster(int serialNumber){
            monsterParticular(serialNumber);
            image = (new ImageIcon("./util/"+serialNumber+".png")).getImage();
       }
        
        public void monsterParticular(int serialNumber){
            try{
                BufferedReader input;
                FileReader reader=null;
                reader = new FileReader("./util/Monster.txt");
                input=new BufferedReader(reader);
                for(int i=0;i<serialNumber;i++)input.readLine();
                
                 StringTokenizer intro = new StringTokenizer(input.readLine()," ");
                 serialNumber = Integer.parseInt(intro.nextToken());
                 this.name = intro.nextToken();
                 this.level = Integer.parseInt(intro.nextToken());
                 this.strength = Integer.parseInt(intro.nextToken());
                 this.intelligence = Integer.parseInt(intro.nextToken());
                 this.speed = Integer.parseInt(intro.nextToken());
                 for(int i=0;i<3;i++) fallItems[i]=Integer.parseInt(intro.nextToken());
                 for(int i=0;i<3;i++) ski[i]=Integer.parseInt(intro.nextToken());
                 hp = 30 + strength * 4;
                 hpp = hp ; 
                 mp = 20 + intelligence * 3;
                 mpp = mp ;
                 attact = (int)(strength * 1.5);
                 attactt = attact;
                 defence = (int)((strength + speed) * 0.5 );    
                 defencee = defence ;
                 reader.close();
                 input.close(); 
                 speedd=speed;
                 }catch (FileNotFoundException ex) {
                 }catch (IOException ex) {}
                 getSkill();
            
   }
        
        public void getSkill(){  
              
           for(int i = 0; i < 3 ; i++){
              skill[i] = new Skill(ski[i]);
}
        }
}
