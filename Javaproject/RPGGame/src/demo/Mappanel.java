/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package demo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */

//地图类

public class Mappanel extends JPanel implements KeyListener{
    
        int m = (int)(Math.random()*10+5);//遇敌的步数
        int[][] map = new int[20][20];//地图数组
        String face = "面";//人物的方向
        
        int mapnumber = 0;//地图编号
        int nextright =1;//向右一张地图的在文件位置
        int nextleft =1;//向左一张地图的在文件位置
        int nextup =1;//向上一张地图的在文件位置
        int nextdown =1;//向下一张地图的在文件位置
        
        Image image = (new ImageIcon("./util/"+"人物"+face+".png")).getImage();//人物图标
        Image image0 = (new ImageIcon("./util/草地.png")).getImage();
        Image image1 = (new ImageIcon("./util/沙漠.png")).getImage();
        Image image2 = (new ImageIcon("./util/森林.png")).getImage();
        Image image3 = (new ImageIcon("./util/众山.png")).getImage();
        Image image4 = (new ImageIcon("./util/海洋.png")).getImage();
        Image image5 = (new ImageIcon("./util/城镇.png")).getImage();
        Image image6 = (new ImageIcon("./util/路标.png")).getImage();
        Image image7 = (new ImageIcon("./util/树桩.png")).getImage();
        Image image8 = (new ImageIcon("./util/浅草.png")).getImage();
        Image image9 = (new ImageIcon("./util/出口.png")).getImage();
        Image image10 = (new ImageIcon("./util/宝箱.png")).getImage();
        Image image11 = (new ImageIcon("./util/花.png")).getImage();
        
        int x = 5, y = 5; //人物图标的坐标
        
        
        Statepanel statepanel;
        Player player;
        
        
        public Mappanel(Statepanel statepanel ,Player player){
            this.player = player;
            x= player.x;
            y= player.y;
            mapnumber = player.mapnumber;
            map = this.getMap(player.mapnumber);
            
            setLocation();//确定主角坐标
            
            this.statepanel = statepanel;
            this.setBounds(0, 0, 600, 600);
        }
        
       public void paintComponent(Graphics g){
         for(int i = 0 ; i < 20 ; i++)
             for(int j = 0 ;j < 20 ; j++)
                 switch(map[i][j]){
                     case 0 : g.drawImage(image0, i*30, j*30, this);break;
                     case 1 : g.drawImage(image1, i*30, j*30, this);break;
                     case 2 : g.drawImage(image2, i*30, j*30, this);break;
                     case 3 : g.drawImage(image3, i*30, j*30, this);break;
                     case 4 : g.drawImage(image4, i*30, j*30, this);break;
                     case 5 : g.drawImage(image5, i*30, j*30, this);break;
                     case 6 : g.drawImage(image6, i*30, j*30, this);break;
                     case 7 : g.drawImage(image7, i*30, j*30, this);break;
                     case 8 : g.drawImage(image8, i*30, j*30, this);break;
                     case 9 : g.drawImage(image9, i*30, j*30, this);break;
                     case 10 : g.drawImage(image10, i*30, j*30, this);break;
                     case 11 : g.drawImage(image11, i*30, j*30, this);break;
             }
           g.drawImage(image, x*image.getWidth(this), y*(image.getHeight(this)-30)-30, this);
                 
        }
       
        public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            
            case KeyEvent.VK_DOWN:  
                y++; if(y*(image.getHeight(this)-30)>570){
                   if(nextdown!=0) {y-=20; face = "面"; image = (new ImageIcon("./util/"+"人物"+face+".png")).getImage(); map = this.getMap(nextdown);}//换地图
                   else y--;
                }
                else if(map[x][y]!=0&&map[x][y]!=5&&map[x][y]!=6&&map[x][y]!=10) {y--; break;}
                else {m--; face = "面"; image = (new ImageIcon("./util/"+"人物"+face+".png")).getImage();}  
                if( m == 0) {this.repaint();m = (int)(Math.random()*10+15); Fightdialog fight =  new Fightdialog(player);statepanel.repaint(); break;}
                break;
                
            case KeyEvent.VK_UP: 
                y--; if(y<0){
                    if(nextup!=0) {y+=20; face = "背"; image = (new ImageIcon("./util/"+"人物"+face+".png")).getImage(); map = this.getMap(nextup);}//换地图
                    else y++;
                }
                else if(map[x][y]!=0&&map[x][y]!=5&&map[x][y]!=6&&map[x][y]!=10) {y++; break;} 
                else {m--;face = "背"; image = (new ImageIcon("./util/"+"人物"+face+".png")).getImage();}  
                if( m == 0) {this.repaint();m = (int)(Math.random()*10+15); Fightdialog fight =  new Fightdialog(player);statepanel.repaint(); break;}
                break;
                
            case KeyEvent.VK_RIGHT: 
                x++; if(x*image.getWidth(this)>570){
                   if(nextright!=0){ x-=20; face = "右"; image = (new ImageIcon("./util/"+"人物"+face+".png")).getImage(); map = this.getMap(nextright);}//换地图
                   else x--;
                }
                else if(map[x][y]!=0&&map[x][y]!=5&&map[x][y]!=6&&map[x][y]!=10) {x--;  break;}
                else {m--;face = "右"; image = (new ImageIcon("./util/"+"人物"+face+".png")).getImage();}    
                if( m == 0) {this.repaint();m = (int)(Math.random()*10+15);Fightdialog fight =  new Fightdialog(player);statepanel.repaint(); break;}
                break;
                
            case KeyEvent.VK_LEFT:
                x--; if(x<0){
                    if(nextleft!=0) {x+=20; face = "左"; image = (new ImageIcon("./util/"+"人物"+face+".png")).getImage(); map = this.getMap(nextleft);}//换地图
                    else x++;
                }
                else if(map[x][y]!=0&&map[x][y]!=5&&map[x][y]!=6&&map[x][y]!=10) {x++;  break;}
                else {m--;face = "左"; image = (new ImageIcon("./util/"+"人物"+face+".png")).getImage();}   
                if( m == 0) {this.repaint();m = (int)(Math.random()*10+15);Fightdialog fight =  new Fightdialog(player);statepanel.repaint(); break;}
                break;
                
             case KeyEvent.VK_ENTER: setEnter();
                
        }
        
        setLocation();
        this.repaint();
            
    }
    
        public void setEnter(){
            System.out.println(player.j);
            //System.out.println(x+"   "+y+"    "+mapnumber);
            switch(map[x][y]){
                    case 10:player.newEquip((int)(Math.random()*5+1)); map[x][y] = 0; break;
                    case 5: System.out.println(this.mapnumber);
                        if(this.mapnumber == 3 )new countydialog(player);statepanel.repaint(); 
                        if(this.mapnumber == 1 )new Homedialog(player); statepanel.repaint(); 
                        if(this.mapnumber == 14 )new Judialog(player); statepanel.repaint();
                        if(this.mapnumber == 13  ){
                            new Filedialog("./util/桃花源记.txt");
                            new Textdialog("游戏剧情暂告一段落。谢谢大家欣赏 。有什么意见或建议可加QQ649617818");
                            player.newSkill(20);
                            player.j++;
                            statepanel.repaint();
                        
                        } 
                        break;
                    case 0: 
                        if(mapnumber == 8 && x ==10 && y == 6 && player.j != 0){ new Treedialog(player); break;}
                        new Statedialog(player); statepanel.repaint(); break;
                    case 6: 
                        if(mapnumber == 2){new Textdialog("西边: 洛阳"); break;}
                        if(mapnumber == 8){new Textdialog("洛阳城郊"); break;}
                        if(mapnumber == 6){new Textdialog("左路--落英缤纷处 ,不识路者勿去，慎防迷路！"); break;}
                    
                }
        }
        
    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
    
    public void setLocation(){
        this.player.x = x;
        this.player.y = y;
        this.player.mapnumber = mapnumber;
    }
       
      
       

       public  int[][] getMap(int i) {//得到下一张地图的方法
           int[][] maps = new int[20][20];
           int n = 1;
           BufferedReader reader;
           
        try {
            reader = new BufferedReader(new FileReader("./util/map.txt"));
       
           String stringmap = reader.readLine();
           StringTokenizer tokenizer = new StringTokenizer(stringmap);
           
           while(!stringmap.equals("")){
           if(i==Integer.parseInt(tokenizer.nextToken())){n=0;break;}
           stringmap = reader.readLine();
           tokenizer = new StringTokenizer(stringmap);
           }
           try {
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(Mappanel.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           if(n == 0){
               mapnumber = i;
               nextup = Integer.parseInt(tokenizer.nextToken());
               nextdown = Integer.parseInt(tokenizer.nextToken());
               nextleft = Integer.parseInt(tokenizer.nextToken());
               nextright = Integer.parseInt(tokenizer.nextToken());
                    for(int w = 0 ; w < 20 ; w++)
                       for(int j = 0 ;j < 20 ; j++){
                           maps[j][w] = Integer.parseInt(tokenizer.nextToken());
                       }
               
           return  maps;
           }
           else {
               for(int w = 0 ; w < 20 ; w++)
                       for(int j = 0 ;j < 20 ; j++)
                           maps[j][w] = 0;
               return maps;
           }
           
       }
               catch (FileNotFoundException ex) {
            Logger.getLogger(Mappanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            Logger.getLogger(Mappanel.class.getName()).log(Level.SEVERE, null, ex);
        } 
        for(int w = 0 ; w < 20 ; w++)
                       for(int j = 0 ;j < 20 ; j++)
                           maps[j][w] = 0;
               return maps;
            
       }
    }
