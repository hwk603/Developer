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
public class Skill {
     
     int serialNumber;
     String defi;
     String definition;
     String definitionHpp;
     String definitionMpp;
     String definitionSpeedd;
     String definitionAttactt;
     String definitionDefencee;;
     String name ;
     int hpp;
     int mpp;
     int speedd;
     int attactt;
     int defencee;
     int area;
    
     public Skill(int serialNumber){
         skillParticular(serialNumber) ;
      }
     
    //从文件得到技能信息 
     public void skillParticular(int serialNumber){
         try{
                BufferedReader input;
                FileReader reader=null;
                reader = new FileReader("./util/Skill.txt");
                input=new BufferedReader(reader);
                for(int i=0;i<serialNumber;i++)input.readLine();
                
                 StringTokenizer intro = new StringTokenizer(input.readLine()," ");
                 intro.nextToken();
                 area = Integer.parseInt(intro.nextToken());
                 name = intro.nextToken();
                 hpp = Integer.parseInt(intro.nextToken());
                 //System.out.println(hpp);
                 mpp = Integer.parseInt(intro.nextToken());
                 speedd = Integer.parseInt(intro.nextToken());
                 attactt = Integer.parseInt(intro.nextToken());
                 defencee = Integer.parseInt(intro.nextToken());
                 defi=intro.nextToken();
                 reader.close();
                 input.close(); 
                 }catch (FileNotFoundException ex) {
                 }catch (IOException ex) {}
   }
     
     //怪物承受技能
     public void Drawme(Monster monster , Player player){
           
         monster.attactt+=this.attactt;
         if(attactt<0)definitionAttactt = "攻击力减少  "+this.attactt + "\n";
         else if(attactt>0)definitionAttactt = "攻击力增加  "+this.attactt + "\n";
         else definitionAttactt = "";
         monster.defencee=this.defencee;
         if(defencee<0)definitionDefencee = "防御力减少  "+this.attactt + "\n";
         else if(defencee>0)definitionDefencee = "防御力增加  "+this.attactt + "\n";
         else definitionDefencee = "";
         if(hpp<0){int temp =(int)(hpp*(Math.random()+0.5));monster.hpp+=temp;definitionHpp = "HP减少  "+ temp + "\n"; }
         else if(hpp>0){int temp =(int)(hpp*(Math.random()+0.5));monster.hpp+=temp;definitionHpp = "自己HP回复  "+ temp + "\n"; }
         else definitionHpp = "";
         player.mpp-=this.mpp ;
         if(mpp>0)definitionMpp = "消耗MP  "+this.mpp + "\n";
         monster.speedd+=this.speedd;
         if(speedd<0)definitionSpeedd = "速度降低  "+this.attactt + "\n";
         else if(speedd>0)definitionSpeedd = "速度提升  "+this.attactt + "\n";
         else definitionSpeedd = "";
         definition = definitionAttactt+"  "+ definitionDefencee +"  "+ definitionHpp +"  "+ definitionMpp+"  " + definitionSpeedd ;
         
     }
      public void useSkill(Monster monster , Player player){
           if(player.mpp<=mpp){
             new Textdialog("内力不足，你的攻击落空");
             definition="无效果";
             return;
            }
         Drawme(monster,player);
     }
     public void useSkill(Monster monster1 ,Monster monster2 , Player player){
          if(player.mpp<=mpp*2){
             new Textdialog("内力不足，你的攻击落空");
             definition="无效果";
             return;
            }
          Drawme(monster1,player);Drawme(monster2,player);
      }
     public void useSkill(Monster monster1,Monster monster2,Monster monster3 , Player player){
          if(player.mpp<=mpp*3){
             new Textdialog("内力不足，你的攻击落空");
             definition="无效果";
             return;
            }
         Drawme(monster1,player);Drawme(monster2,player);Drawme(monster3,player);
    }
     public void miss(){
         
     }
     
     //人物承受技能
     public void Drawme(Player player , Monster monster){
         if(monster.mpp<mpp){
             definition="无效果";
             return;       
         }
         player.attactt+=this.attactt;
          if(attactt<0)definitionAttactt = "攻击力减少  "+this.attactt + "\n";
         else if(attactt>0)definitionAttactt = "攻击力增加  "+this.attactt + "\n";
         else definitionAttactt = "";
         player.defencee=this.defencee;
          if(defencee<0)definitionDefencee = "防御力减少  "+this.attactt + "\n";
         else if(defencee>0)definitionDefencee = "防御力增加  "+this.attactt + "\n";
         else definitionDefencee = "";
         if(hpp<0){int temp =(int)(hpp*(Math.random()+0.5));player.hpp+=temp;definitionHpp = "HP减少  "+ temp + "\n"; }
         else if(hpp>0){int temp =(int)(hpp*(Math.random()+0.5));player.hpp+=temp;definitionHpp = "自己HP回复  "+ temp + "\n"; }
         else definitionHpp = "";
         monster.mpp-= this.mpp ;
         if(mpp<0)definitionMpp = "消耗MP  "+this.mpp + "\n";
         player.speedd+=this.speedd;
         if(speedd<0)definitionSpeedd = "速度降低  "+this.attactt + "\n";
         else if(speedd>0)definitionSpeedd = "速度提升  "+this.attactt + "\n";
         else definitionSpeedd = "";
         definition = definitionAttactt+"  " + definitionDefencee +"  "+ definitionHpp +"  "+ definitionMpp +"  "+ definitionSpeedd  ;
     }
     public void useSkill(Player player, Monster monster){
         Drawme(player,monster);
     }
     public void useSkill(Player player1,Player player2,Monster monster){
         Drawme(player1,monster);Drawme(player2,monster);
     }
     public void useSkill(Player player1,Player player2,Player player3,Monster monster){
         Drawme(player1,monster);Drawme(player2,monster);Drawme(player3,monster);
     }
}

