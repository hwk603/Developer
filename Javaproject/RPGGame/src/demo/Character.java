/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package demo;

 //人物力量计算公式     strength = 10 + levle * 3;

import java.awt.Image;


 //人物智力计算公式     intelligence  = 5 + level * 2 ;
import javax.swing.ImageIcon;
 //人物速度计算公式     speed = 10 + level *2;
 //人物hp计算公式       hp = 30 + strength * 4;
 //人物MP计算公式       mp = 20 + intelligence * 3;
 //攻击力计算公式       attact = (int)(strength * 1.5);
 //防御力计算公式       defence = (int)((strength + speed) * 0.5 );    

public class Character {
    
    Image image = (new ImageIcon("./util/主角头像.png")).getImage();;//人物头像
    //状态最大值
    String name;
    int level;
    int strength;
    int intelligence;
    int speed;
    int hp;
    int mp;
    int attact;
    int defence;
    
    //装备 技能
    int[] equip = new int[10];
    int[] equip2 = new int[5];
    Equipment[] equipment = new Equipment[10];
    Equipment[] equipment2 = new Equipment[5];//0表示武器。1表示衣服，2表示鞋，3表示装饰品1，4表示装饰品2
    int[] ski = new int[10];
    Skill[] skill = new Skill[10];
    //道具
    int[] ite = new int[30];
    Item[] item = new Item[30];
    
    //状态现有值
    int hpp;
    int mpp;
    int speedd;
    int attactt;
    int defencee;
    
    //人物状态 0表示正常 1表示中毒 2表示麻痹 3表示
    int state;
    
    public Character(){
        
    }
   
    public Character(String name,int level){
        
        
     this.level = level;
     this.name = name;
     strength = 10 + level * 3;
     intelligence  = 5 + level * 2;
     speed = 5 + level * 2;
     //如果改变请更改Monster类中的monsterParticular方法
     hp = 30 + strength * 4;
     hpp = hp ; 
     mp = 20 + intelligence * 3;
     mpp = mp ;
     attact = (int)(strength * 1.5);
     attactt = attact;
     defence = (int)((strength + speed) * 0.5 );    
     defencee = defence ;
     getSkill();
     getEquipment();
    }
    //从文件中获得该生物的技能
    public void getSkill(){  
               ski[0]=2;
               skill[0] = new Skill(ski[0]);
           for(int i = 1; i < 10 ; i++){
               ski[i]=1;
               skill[i] = new Skill(ski[i]);
          
      } 
 }
    //从文件中获得该生物的装备（可能掉下的装备）
    public void getEquipment(){ //从文件中获得该人物的装备
           for(int i = 0; i < 10 ; i++){
             equip[i]=1;
             equipment[i] = new Equipment(equip[i]);
           
    }
   }  
    public void getEquipment2(){ //从文件中获得该人物的装备
           for(int i = 0; i < 5 ; i++){
             equip2[i]=1;
             equipment2[i] = new Equipment(equip[i]);
           
    }
   }  
    
    public void getItem(){ //从文件中获得该人物的装备
           for(int i = 0; i < 20 ; i++){
             ite[i]=1;
             item[i] = new Item(ite[i]);
           
    }
   }  
    
}

