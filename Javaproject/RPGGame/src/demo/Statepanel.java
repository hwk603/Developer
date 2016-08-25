/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package demo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */

//状态类

public class Statepanel extends JPanel{
    
    public Player player;  
    Image image = (new ImageIcon("./util/主角头像.png")).getImage();//人物头像图标
    
    public Statepanel(Player player){
        this.player = player;
        this.setSize(200, 600);
    }
    
    protected void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 200, 600);
        g.setColor(Color.BLACK);
        g.drawImage(image, 45, 0, this);
        g.drawString("名字： "+player.name, 10, 150);//名字
        g.drawString("等级："+player.level, 10, 175);
        g.drawString("力量 : "+player.strength, 10, 200);
        g.drawString("HP: "+player.hpp+"/"+player.hp, 10, 225);
        g.drawString("智力: "+player.intelligence, 10, 250);
        g.drawString("MP: "+player.mpp+"/"+player.mp, 10, 275);
        g.drawString("攻击力: "+player.attact, 10, 300);
        g.drawString("防守力: "+player.defence, 10, 325);
        g.drawString("速度 : "+player.speed, 10, 350);
        g.drawString("武器: "+player.equipment2[0].name, 10, 375);
        g.drawString("衣服: "+player.equipment2[1].name, 10, 400);
        g.drawString("鞋子: "+player.equipment2[2].name, 10, 425);
        g.drawString("装饰品(左): "+player.equipment2[3].name, 10, 450);
        g.drawString("装饰品(右): "+player.equipment2[4].name, 10, 475);
        g.drawString("金子："+player.money, 10, 500);
        g.drawString("经验："+player.xpp+"/"+player.xp, 10, 525);
        
        
    }
    

}
