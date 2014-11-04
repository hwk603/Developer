/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
//玩家
public class Player extends Character{
    
    
    int xp;
    int xpp;
    int money;
    
    int i = -1;//游戏进度
    int j = 0;//游戏进度
    int m = 0;//游戏进度
    
    

    //人物坐标 
    int x=5,y=5;
    //人物所在的地图
    int mapnumber = 1;
    
    
    public Player(){
        image = (new ImageIcon("./util/主角头像.png")).getImage();
        name = "菜鸟";
        level = 1;
        
}
    public Player(String name){
        
        image = (new ImageIcon("./util/主角头像.png")).getImage();
        
        this.name = name;
        level = 1;
        strength = 25; 
        intelligence  = 8 ;
        speed = 10 ; 
        hp = 30 + strength * 2;
        hpp = hp ; 
        mp = 20 + intelligence * 2;
        mpp = mp ;
        attact = (int)(strength * 1.5);
        attactt = attact;
        defence = (int)((strength + speed) * 0.5 );    
        defencee = defence ;
        xpp=0;
        xp=(level+2)*(level+2)*10;
        xpp=0;
        money = 100 ;
        super.getEquipment();
        super.getSkill();
        super.getItem();
        super.getEquipment2();
    
       
}     
    //人物升级
    public String levelup(int i,int j){
           if(j>=i){
             double temp = Math.random();
             level++;
             if(temp<0.1)strength += 2;
             if(temp>=0.1&&temp<0.5)strength += 3;
             if(temp>=0.5&&temp<=0.8)strength += 4;
             if(temp>0.8)strength += 5;
             temp = Math.random();
             if(temp<0.1)intelligence += 1;
             if(temp>=0.1&&temp<0.5)intelligence += 2;
             if(temp>=0.5&&temp<=0.8)intelligence += 3;
             if(temp>0.8)intelligence += 4;
             temp = Math.random();
             if(temp<0.1)speed += 1;
             if(temp>=0.1&&temp<0.5)speed += 2;
             if(temp>=0.5&&temp<=0.8)speed += 3;
             if(temp>0.8)speed += 4;
             hp = 30 + strength * 2;
             mp = 20 + intelligence * 2;
             xp=xp*5/3;
             if(level%3==0){
                 newSkill((int)((Math.random()*15+1)));
             }
             return "恭喜升级了 你现在的等级为"+level;
             
}
             return "";
    }
    
        public void getSkill(){  
              
           for(int i = 0; i < 10 ; i++){
              skill[i] = new Skill(ski[i]);
          
      } 
 }
    //从文件中获得该生物的装备（可能掉下的装备）
    public void getEquipment(){ //从文件中获得该人物的装备
           for(int i = 0; i < 10 ; i++){
             equipment[i] = new Equipment(equip[i]);
    }
   }  
    
    public void getItem(){ //从文件中获得该人物的装备
           for(int i = 0; i < 20 ; i++){
             item[i] = new Item(ite[i]);
    }
   }  
    public void getEquipment2(){
        for(int i = 0; i < 5 ; i++){
             equipment2[i] = new Equipment(equip2[i]);
    }
    }
    
    void save() {
            try { 
FileWriter fw = new FileWriter("./util/player.txt"); 
BufferedWriter buffw=new BufferedWriter(fw);
PrintWriter pw=new PrintWriter(buffw);
                     pw.println();
                     pw.println(name);
                     pw.println(level);
                     pw.println(strength);
                     pw.println(intelligence);
                     pw.println(speed);
                     pw.println(hp);
                     pw.println(mp);
                     pw.println(attact);
                     pw.println(defence);
                     pw.println(hpp);
                     pw.println(mpp);
                     pw.println(xp);
                     pw.println(xpp);
                     pw.println(x);
                     pw.println(y);
                     pw.println(mapnumber);
                     pw.println(money);
                     pw.println(i);
                     pw.println(j);
                     pw.println(m);
                     for(int i = 0 ;i < 10 ;i++){
                         pw.println(equip[i]);
                     }
                     for(int i = 0 ;i < 5 ;i++){
                         pw.println(equip2[i]);
                     }
                      for(int i = 0 ;i < 20 ;i++){
                         pw.println(ite[i]);
                     }
                     for(int i = 0 ;i < 10 ;i++){
                         pw.println(ski[i]);
                     }
                       
               pw.close();
	       buffw.close();
	       fw.close(); 
    }
            catch(Exception e) { 
} 

    }
    public void load(){
           try {
              BufferedReader input;
              FileReader reader=null;
              reader = new FileReader("./util/player.txt");
              input=new BufferedReader(reader);
              input.readLine();
              name = input.readLine();
              System.out.println(name);
              level = Integer.parseInt(input.readLine());
              strength = Integer.parseInt(input.readLine());
              intelligence = Integer.parseInt(input.readLine());
              speed = Integer.parseInt(input.readLine());
              hp = Integer.parseInt(input.readLine());
              mp = Integer.parseInt(input.readLine());
              attact = Integer.parseInt(input.readLine());
              defence = Integer.parseInt(input.readLine());
              hpp = Integer.parseInt(input.readLine());
              
              mpp = Integer.parseInt(input.readLine());
              xp = Integer.parseInt(input.readLine());
              xpp = Integer.parseInt(input.readLine());
              x = Integer.parseInt(input.readLine());
              y = Integer.parseInt(input.readLine());
              mapnumber = Integer.parseInt(input.readLine());
              money = Integer.parseInt(input.readLine());
              i = Integer.parseInt(input.readLine());
              j = Integer.parseInt(input.readLine());
              m = Integer.parseInt(input.readLine());
              for(int i = 0 ;i < 10 ;i++){
                         equip[i] = Integer.parseInt(input.readLine());
                     }
              for(int i = 0 ;i < 5 ;i++){
                         equip2[i] = Integer.parseInt(input.readLine());
                     }
                      for(int i = 0 ;i < 20 ;i++){
                         ite[i] = Integer.parseInt(input.readLine());
                     }
                     for(int i = 0 ;i < 10 ;i++){
                         ski[i] = Integer.parseInt(input.readLine());
                     }
              
                       
                       getSkill();
                       getEquipment(); 
                       getEquipment2();
                       getItem(); 
              
              reader.close();
              input.close();
            }catch (FileNotFoundException ex) {
            }catch (IOException ex) {}
    }

    public void newItem(int serialNumber){
        if(serialNumber==1) return;
        int i;
        for( i=0 ; i <=20; i++){
            if(i==20){
               new Textdialog("道具栏已经满了!"); 
            }
            if(ite[i]==1){
                ite[i]=serialNumber;break;
            }
        }
         item[i].getItem(serialNumber);  
         new Textdialog("获得道具："+item[i].name);
    }
     public void throwItem(int i){
        if(i==1) return;
        ite[i]=1;
        item[i].getItem(1);    
    }
     public void newEquip(int serialNumber){
          if(serialNumber==1) return;
        int i;
        for( i=0 ; i <=10; i++){
            if(i==10){
               new Textdialog("装备栏已经满了!"); 
            }
            if(equip[i]==1){
                equip[i]=serialNumber;break;
            }
        }
         equipment[i].equipmentParticular(serialNumber); 
        new Textdialog("获得装备："+equipment[i].name);
         
       
    }
     public void throwEquip(int i){
         if(i==1) return;
        equip[i]=1;
        equipment[i].equipmentParticular(1);    
    }
     public void newSkill(int serialNumber){
         if(serialNumber==1) return;
        int i;
        for( i=0 ; i < 10; i++){
            if(ski[i]==1){
                ski[i]=serialNumber;break;
            }
        }
        skill[i].skillParticular(serialNumber);   
        new Textdialog("你学会了新技能"+skill[i].name);
    }
     public void throwSkill(int i){
         if(i==1) return;
        ski[i]=1;
        skill[i].skillParticular(1);    
    }
}