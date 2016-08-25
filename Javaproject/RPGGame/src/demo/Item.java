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
 *时间关系 物品功能 为 测试功能 
 * @author Administrator
 */
public class Item {

    String name ;
    String definition;
    int hp;
    int mp;
    int serialNumber;
    int price;
    
    
    
    public Item(){
        
    }
    public Item(int serialNumber){
        getItem(serialNumber);
        
    }
    public void setItem(int serialNumber){
        
    }
   
    public void getItem(int serialNumber){
           try{
                BufferedReader input;
                FileReader reader=null;
                reader = new FileReader("./util/Item.txt");
                input=new BufferedReader(reader);
                for(int i=0;i<serialNumber;i++)input.readLine();
                StringTokenizer intro = new StringTokenizer(input.readLine()," ");
                intro.nextToken();
                name = intro.nextToken();
                hp = Integer.parseInt(intro.nextToken());
                mp = Integer.parseInt(intro.nextToken());
                price =Integer.parseInt(intro.nextToken());
                definition = intro.nextToken();
                //reader.close();
                input.close(); 
                 }catch (FileNotFoundException ex) {
                 }catch (IOException ex) {}
           this.serialNumber = serialNumber ;
   }
    
        public void useItem(Player player){
        if(player.hpp<=0){
            new Textdialog("体力不支回去睡觉吧！");
            return;
        }
        player.hpp += this.hp;
        if(player.hpp > player.hp)player.hpp = player.hp;
        player.mpp += this.mp;
        if(player.mpp > player.mp)player.mpp = player.mp;
        if(player.hpp < 0)player.hpp = 0;
        if(player.mpp < 0)player.mpp = 0;
        serialNumber=1;
        getItem(1);
        
    }
        
       
        
}

        
    
    
    
    
    
    

