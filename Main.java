import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.Scanner; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.*;
import java.io.*; 
import javax.imageio.*;


class Main extends JFrame{
 GamePanel game= new GamePanel();
 
 public Main(){
   super("Harry Potter vs. Horcruxes");
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   add(game);
   setSize(1000,600); 
   setVisible(true);
 }
    
  public static void main(String[] arguments) {
    Main frame = new Main(); 
  }

}

class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener{
 public static final int INTRO=0, LEVEL1=1,LEVEL2=2, LEVEL3=3, HOWTO = 4, HIGH = 5, END=6;
 private int screen = LEVEL1; //the game starts on the intro screen
 private boolean []keys;
 private Harry harry; //CREATES A NEW HARRY POTTER OBJECT
 private Dementor d1;
 private Dementor d2;
 private Villian v;
 private int lives = 0;
 private int blueSpell = 0;
 private int greenSpell = 0;
 private int orangeSpell = 0;
 private int whiteSpell = 0;
 private int purpleSpell = 0;
 private int torquiseSpell = 0;

 private int currentSpell;
 private int bs = 0, gs = 1, os = 2, ws = 5, ps = 3, ts = 4;


 ArrayList <Platforms> listPlatforms= new ArrayList <Platforms>(); //platforms for L1
 ArrayList <Platforms> listPlatforms2= new ArrayList <Platforms>(); //platforms for L2
 ArrayList <Dementor> listDems= new ArrayList <Dementor>(); //dementor arraylist
 ImageIcon img = new ImageIcon("platform1.png");
 ImageIcon img2 = new ImageIcon("platform2.png");
 ImageIcon img3 = new ImageIcon("platform3.png");
 ImageIcon img4 = new ImageIcon("platform4.png");
 ImageIcon img5 = new ImageIcon("platform5.png");
 ImageIcon img6 = new ImageIcon("platform6.png");
 ImageIcon img7 = new ImageIcon("platform7.png");

 Platforms plat= new Platforms(70,400,95,52,img.getImage());
 Platforms plat2= new Platforms(260,320,318,52,img2.getImage());
 Platforms plat3= new Platforms(1100,290,54,56,img3.getImage());
 Platforms plat4= new Platforms(640,380,360,56,img4.getImage());
 Platforms plat5= new Platforms(1260,240,106,54,img5.getImage());
 Platforms plat6= new Platforms(1500,300,416,54,img6.getImage());
 Platforms plat7= new Platforms(2000,200,214,60,img7.getImage());
 Platforms plat8= new Platforms(2250,390,106,54,img5.getImage());
 Platforms plat9= new Platforms(2400,300,54,56,img3.getImage());
 Platforms plat10= new Platforms(2600,240,318,52,img2.getImage());
 Platforms plat11= new Platforms(3050,140,360,56,img4.getImage());
 Platforms plat12= new Platforms(3500,400,318,52,img.getImage());
 Platforms plat13= new Platforms(3700,300,106,54,img5.getImage());
 Platforms plat14= new Platforms(3900,360,54,56,img3.getImage());
 Platforms plat15= new Platforms(4075,450,318,52,img2.getImage());
 Platforms plat16= new Platforms(4500,300,416,54,img6.getImage());

 Platforms plat17= new Platforms(80,400,416,54,img2.getImage());
 Platforms plat18= new Platforms(525,332,416,54,img4.getImage());
 Platforms plat19= new Platforms(990,270,416,54,img6.getImage());
 Platforms plat20= new Platforms(1480,400,416,54,img7.getImage());
 Platforms plat21= new Platforms(1820,325,416,54,img2.getImage());
 Platforms plat22= new Platforms(2220,390,416,54,img.getImage());
 

 //horcrux image
 Image horcrux = new ImageIcon("yellowball.png").getImage(); 
 Image blueS = new ImageIcon("blueball.png").getImage();
 Image greenS = new ImageIcon("greenball.png").getImage();
 Image orangeS = new ImageIcon("orangeball.png").getImage();
 Image whiteS = new ImageIcon("whiteball.png").getImage();
 Image purpleS = new ImageIcon("purpleball.png").getImage();
 Image torquiseS = new ImageIcon("torquiseball.png").getImage();

 ArrayList <Horcrux> listHorcrux= new ArrayList <Horcrux>();
 //ArrayList <Horcrux> listHorcrux2= new ArrayList <Horcrux>(); 

 Horcrux b = new Horcrux(150,485, blueS,"b");
 Horcrux gr = new Horcrux(240, 485, greenS,"g");
 Horcrux o = new Horcrux(330, 485, orangeS,"o");
 Horcrux p = new Horcrux(150, 530, purpleS,"p");
 Horcrux t = new Horcrux(240, 530, torquiseS,"t");
 Horcrux w = new Horcrux(330, 530, whiteS,"w");

 String [] randList = {"b", "b","w", "b", "g", "o", "o", "o", "o", "t", "w", "p","p", "g", "g", "y", "y", "y", "y", "y"};
 String [] randList2 = {"y", "y","y", "y", "y", "p", "p", "p", "g", "t", "w", "p","p", "g", "g", "g", "w", "b", "b", "b"};
 HashMap<String, Image> horPic;
 HashMap<Integer, Image> platsPic;
 
 ArrayList <MakeSpell> listSpell= new ArrayList <MakeSpell>();

 private boolean ready= false;
 int count = 0;
 private Rectangle menuInstructions, menuPlay, menuScores, back, backC;;
 private Ellipse2D spellbtn, spellbtn2, spellbtn3, spellbtn4, spellbtn5, spellbtn6; 
 private Point mouse= MouseInfo.getPointerInfo().getLocation();
 Timer timer;
 Image intro;
 Image how2;
 Image level2;
 Image level3;
 Image game;
 Image hover1;
 Image hover2;
 Image hover3;
 Image backHover;
 Image backClick;
 Image click1;
 Image click2;
 Image click3;


//  public ArrayList<Point> load(String name) {
//   ArrayList<Point>points= new ArrayList<Point>();
//   try{
//    Scanner inFile = new Scanner(new File(name));
//    while(inFile.hasNextInt()){
//        int x = inFile.nextInt();
//        int y = inFile.nextInt();
//        int w = inFile.nextInt();
//        int h = inFile.nextInt();
//        plats.add(new Platforms(x,y,w,h));    
//    }
//   }
//   catch(IOException ex){
//    System.out.println(ex);
//   }
//   return points; 
//  }

    
//  public ArrayList <Platforms>loadPlats(String fname){
//   ArrayList <Point>points = load(fname);
//   ArrayList <Platforms>plats = new ArrayList <Platforms>();
//   for(Point p:points){
//     plats.add(new Platforms(x,y,w,h));
//    //plats.add(new Platforms(80,400,416,54,img2.getImage()); )
//   }
//  }

//  public ArrayList <Platforms> loadPlats(String fname){
//    ArrayList <Platforms> plats=new ArrayList<Platforms>();
//    try{
//    Scanner sc = new Scanner(new File(fname));
//       while(sc.hasNextInt()){
//        int x = sc.nextInt();
//        int y = sc.nextInt();
//        int w = sc.nextInt();
//        int h = sc.nextInt();
//        plats.add(new Platforms(x,y,w,h));    
//       }
//      }   
//      catch(IOException ex){
//        System.out.println(ex);
//      }
//    return plats;
//  }

 
 public GamePanel(){
   horPic = new HashMap<String, Image> ();
   horPic.put("h", horcrux);
   horPic.put("b", blueS);
   horPic.put("g", greenS);
   horPic.put("o", orangeS);
   horPic.put("w", whiteS);
   horPic.put("p", purpleS);
   horPic.put("t", torquiseS);

   platsPic = new HashMap<Integer, Image>();
   platsPic.put(90,  img.getImage());
   platsPic.put(318, img2.getImage());
   platsPic.put(54,  img3.getImage());
   platsPic.put(360, img4.getImage());
   platsPic.put(106, img5.getImage());
   platsPic.put(416, img6.getImage());
   platsPic.put(214, img7.getImage());
   
  keys = new boolean[KeyEvent.KEY_LAST+1];
  harry = new Harry(40,370,"jump",11); //new harry
  d1 = new Dementor(50,300, "dementor", 11);
  d2 = new Dementor(600,300, "dementor", 11);
  intro = new ImageIcon("intro.png").getImage(); //gets all the images
  game = new ImageIcon("full.png").getImage(); 
  level2 = new ImageIcon("level2_.png").getImage();
  level3= new ImageIcon("level3_.png").getImage();
  how2= new ImageIcon("how2.png").getImage(); 
  hover1 = new ImageIcon("hover1.png").getImage();
  hover2 = new ImageIcon("hover2.png").getImage();
  hover3 = new ImageIcon("hover3.png").getImage();
  click1 = new ImageIcon("click1.png").getImage();
  click2 = new ImageIcon("click2.png").getImage();
  click3 = new ImageIcon("click3.png").getImage();
  backHover = new ImageIcon("back_hover.png").getImage();
  backClick= new ImageIcon("back_click.png").getImage();

  menuPlay = new Rectangle(380,310,246,85);
  menuInstructions = new Rectangle(43,408,252,85); 
  menuScores = new Rectangle(672,400,295,95);
  back = new Rectangle(825,8,145,59);
  backC = new Rectangle(825,8,145,59);


  spellbtn =new Ellipse2D.Float(120, 485, 35, 35);
  spellbtn2 = new Ellipse2D.Float(210, 485, 35, 35);
  spellbtn3 = new Ellipse2D.Float(300, 485, 35, 35);
  spellbtn4 = new Ellipse2D.Float(120, 530, 35, 35);
  spellbtn5 = new Ellipse2D.Float(210, 530, 35, 35);
  spellbtn6 = new Ellipse2D.Float(300, 530, 35, 35);


  listPlatforms.add(plat);
  listPlatforms.add(plat2);
  listPlatforms.add(plat3);
  listPlatforms.add(plat4);
  listPlatforms.add(plat5);
  listPlatforms.add(plat6);
  listPlatforms.add(plat7);
  listPlatforms.add(plat8);
  listPlatforms.add(plat9);
  listPlatforms.add(plat10);
  listPlatforms.add(plat11);
  listPlatforms.add(plat12);
  listPlatforms.add(plat13);
  listPlatforms.add(plat14);
  listPlatforms.add(plat15);
  listPlatforms.add(plat16);

  listPlatforms2.add(plat17);
  listPlatforms2.add(plat18);
  listPlatforms2.add(plat19);
  listPlatforms2.add(plat20);
  listPlatforms2.add(plat21);
  listPlatforms2.add(plat22);

  listDems.add(d1);
  listDems.add(d2);

 //addHorcrus(randList,listPlatforms);
 //addHorcrus(randList2,listPlatforms2);


  // for(int i = 0; i<5; i++){
  //   int rand = randint(0, randList2.length-1);
  //   String hr = randList2[rand];
  //   int pnum = randint(0, listPlatforms2.size()-1);
  //   Point pos = listPlatforms2.get(i).getHorcSpot();
  //   Horcrux randHorc = new Horcrux(pos.x,pos.y, horPic.get(hr),hr);
  //   listHorcrux.add(randHorc);
  // }

  setPreferredSize(new Dimension(1000, 600));
  setFocusable(true);
  requestFocus();
  addKeyListener(this);
  addMouseListener(this);
  
  timer = new Timer(20, this);
  timer.start();
 }

  public void loadHorcrux(String [] randList){
    for(int i = 0; i<16; i++){
      int rand = randint(0, randList.length-1);
      String hr = randList[rand];
      int pnum = randint(0, listPlatforms.size()-1);
      Point pos = listPlatforms.get(i).getHorcSpot();
      Horcrux randHorc = new Horcrux(pos.x,pos.y, horPic.get(hr),hr);
      listHorcrux.add(randHorc);
    }
  }

@Override
  public void actionPerformed(ActionEvent ae){
    move();
   repaint();
  }

 @Override
  public void keyReleased(KeyEvent ke){
   int key = ke.getKeyCode();
   keys[key] = false;
  } 
 
 @Override
  public void keyPressed(KeyEvent ke){
   int key = ke.getKeyCode();
   keys[key] = true;
   if(key == KeyEvent.VK_SPACE){ //if the space bar is pressed, a spell is released
       listSpell.add(b.spell(harry));
    }
  }

 @Override
 public void keyTyped(KeyEvent ke){}
 @Override
 public void mouseClicked(MouseEvent e){}

 @Override
 public void mouseEntered(MouseEvent e){}

 @Override
 public void mouseExited(MouseEvent e){}

 @Override
 public void mousePressed(MouseEvent e){
  if(menuInstructions==null || menuPlay==null){
    return;
  }
  if(screen == INTRO){
    if(menuInstructions.contains(mouse)){
      screen = HOWTO; 
    }
    if(menuPlay.contains(mouse)){
      screen = LEVEL1;
    }
  }
  if(screen == HOWTO){
    if(back.contains(mouse)){
      screen = INTRO;
    }
  }
  if(screen == LEVEL1){
    if(backC.contains(mouse)){
      screen = INTRO;
    }
  }
  if(screen == LEVEL1 || screen == LEVEL2 || screen == LEVEL3){
    if(spellbtn.contains(mouse)){
      currentSpell = bs;
    }
    if(spellbtn2.contains(mouse)){
      currentSpell = gs;
    }
    if(spellbtn3.contains(mouse)){
      currentSpell = os;
    }
    if(spellbtn4.contains(mouse)){
      currentSpell = ps;
    }
    if(spellbtn5.contains(mouse)){
      currentSpell = ts;
    }
    if(spellbtn6.contains(mouse)){
      currentSpell = ws;
    }
   }
  System.out.println(""+currentSpell);
  System.out.println(e.getX()+","+e.getY());
 }

  @Override
  public void mouseReleased(MouseEvent e){}

  public static int randint(int low, int high){
		return (int)(Math.random()*(high-low+1)+low);
	}


  public void imageInRect(Graphics g, Image img, Rectangle area){
   g.drawImage(img, area.x, area.y, area.width, area.height, null);
  }

  public void drawMenu(Graphics g){
   if(menuInstructions==null || menuPlay==null || menuScores==null){
     return;
   }    
   g.drawImage(intro,0,0,this);

   if(menuInstructions.contains(mouse)){
    imageInRect(g, hover1, menuInstructions);
   }

   if(menuPlay.contains(mouse)){
    imageInRect(g, hover2, menuPlay);
   }

   if(menuScores.contains(mouse)){
     imageInRect(g, hover3, menuScores);
   }
 }

 @Override // draws the game
 public void paint(Graphics g){
  mouse = MouseInfo.getPointerInfo().getLocation();
   Point offset = getLocationOnScreen();
   mouse.translate(-offset.x, -offset.y);

   if(screen==INTRO){
      drawMenu(g);
   }
   else if(screen==LEVEL1){
      drawGame(g);
   }
   else if(screen==LEVEL2){
      drawLevel2(g);
      listHorcrux.clear();
   }
  //  else if(screen == LEVEL3){
  //    drawLevel3(g);
  //  }
   else if(screen==HOWTO){
      drawInstructions(g);
   }
 }

 public void addNotify() {
    super.addNotify();
    requestFocus();
    ready = true;
  }
  
  boolean flag=false;
  int drop=-1;

  public void move() { 

    //  if(screen = LEVEL2){
    //   listPlatforms = loadPlats("level2.txt")
    //   listHorcrux = loadHorcrux(randList2, )
    //  }

    if(screen==LEVEL1 || screen==LEVEL2){ 
      //movement for harry 
      if(keys[KeyEvent.VK_RIGHT] ){
        harry.move(5);
      }
      if(keys[KeyEvent.VK_LEFT] ){
       harry.move(-5);
      }
      if(keys[KeyEvent.VK_UP] && harry.getInAir()==false ){
        harry.jump(); 
      }
      harry.update();
      // if (harry.checkPlats(listPlatforms)){
      //   harry.resetY();
      // }
      harry.checkPlats(listPlatforms);
      harry.setInAir(false);
      
//Platforms collidedP=checkPlats(harry);
      // Platforms collidedP=null;
      // for(Platforms pl:listPlatforms){ //putting harry on the platform once he collides
      //   flag=pl.detectHarryCollision(harry);
      //   collidedP=pl;
      //   if(flag){ 
      //     harry.setY(pl.yy);
      //     drop++;
      //     break;
      //   }
      // }
      harry.checkPlats(listPlatforms2);
      harry.setInAir(false);
      //collision for level 2 
      // Platforms collided=null;
      // for(Platforms p:listPlatforms2){ //putting harry on the platform once he collides
      //   flag=p.detectHarryCollision(harry);
      //   collided=p;
      //   if(flag){ 
      //     harry.setY(p.yy);
      //     drop++;
      //     break;
      //   }
      // }

      // if(drop>collidedP.w){ //when harry falls off
      //   harry.setY(455);
      //   drop=-1;
      //   //System.out.println(drop + "" + flag);
      // } 

      ArrayList<Dementor>gone = new ArrayList<Dementor>(); //removing the dementor list-->similar to the one done in asteriod code
      ArrayList<MakeSpell>hit = new ArrayList<MakeSpell>(); //hitlist --> similar to the one done in asteriod code
      for(MakeSpell b:listSpell){
        b.move(); //moving the spell
        for(Dementor dm: listDems){
          if(b.detectSpellCollision(dm)){
            gone.add(dm);//adds all the dementors to an arraylist for deletion
            hit.add(b); //adds all the bullets to an arraylist for deletion
          }
        }
      }
      listDems.removeAll(gone); //removes the asteroids
      listSpell.removeAll(hit); //removes all the bullets


      for(Horcrux hor:listHorcrux){ 
        if(harry.collideHorcrux(hor)){//when harry collides with a spell, what ever type it was, the amount goes up by one
          if(hor.type.equals("h")){
             hor.setEnable(false);
             lives++;
          }
          if(hor.type.equals("b")){
             hor.setEnable(false);
             blueSpell++;
          }
          if(hor.type.equals("g")){
             hor.setEnable(false);
             greenSpell++;
          }
          if(hor.type.equals("o")){
             hor.setEnable(false);
             orangeSpell++;
          }
          if(hor.type.equals("p")){
             hor.setEnable(false);
             purpleSpell++;
          }
          if(hor.type.equals("w")){
             hor.setEnable(false);
             whiteSpell++;
          }
          if(hor.type.equals("t")){
             hor.setEnable(false);
             torquiseSpell++;
          }
        }  
      }
       
      for(Dementor dd : listDems){
        if(harry.collideDementor(d1)){ //dementor collision
          lives--;//when a dementor collides with harry, he loses a life
          if(lives<0){
            lives=0;
          }
        }
      }
    }
    if(screen==LEVEL2){ //moving the dementors only in level 2
       for(Dementor d: listDems){
         d.move();
        }
    }
  }

  public void drawGame(Graphics g){
   int offset = 20-harry.getX();
   Graphics2D g2 = (Graphics2D)g;
   g.drawImage(game,0,0,null);  
   harry.draw(g);

   b.setX(150); 
   b.setY(485);
   b.draw(g);

   gr.setX(240);
   gr.setY(485);
   gr.draw(g);

   o.setX(330);
   o.setY(485);
   o.draw(g);

   p.setX(150); 
   p.setY(530);
   p.draw(g);

   t.setX(240);
   t.setY(530);
   t.draw(g);

   w.setX(330);
   w.setY(530);
   w.draw(g);

   for(Horcrux h:listHorcrux){
    if (h.isEnable()){
      h.setX(h.orgX); //drawing the horcruxes
      h.setOffset(offset);
      h.draw(g);
    } 
   }

   for(Platforms pla:listPlatforms){//drawing the platforms
     pla.x=pla.orgX;
     pla.setOffset(offset);
     pla.draw(g);
   }

   if(backC.contains(mouse)){
     imageInRect(g,backHover,backC);
   }
   else{
     imageInRect(g,backClick,backC);
    }

    g.setColor(Color.white);
    g.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 30)); 
    g.drawString("Lives: "+lives, 796,508); //displays the points
    
    g.drawString("Spells: ", 8,508); 
    g.drawString("x"+blueSpell, 160, 508);
    g.drawString("x"+greenSpell, 250, 508);
    g.drawString("x"+orangeSpell, 340, 508);
    g.drawString("x"+purpleSpell, 160, 550);
    g.drawString("x"+torquiseSpell, 250, 550);
    g.drawString("x"+whiteSpell, 340, 550);

    g.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 50)); 
    g.drawString("Level 1 ", 430,35); 

  }

  public void drawInstructions(Graphics g){
   g.drawImage(how2,0,0,this);
   if(back.contains(mouse)){
     imageInRect(g, backHover, back);
   }
  }

  public void drawLevel2(Graphics g){
    int offset = 20-harry.getX();
    Graphics2D g2 = (Graphics2D)g;
    g.drawImage(level2,0,0,this);
    d1.draw(g);
    d2.draw(g); 
    harry.draw(g);
    if(back.contains(mouse)){
      imageInRect(g, backHover, back);
    }

    b.setX(150); 
    b.setY(485);
    b.draw(g);

    gr.setX(240);
    gr.setY(485);
    gr.draw(g);

    o.setX(330);
    o.setY(485);
    o.draw(g);

    p.setX(150); 
    p.setY(530);
    p.draw(g);

    t.setX(240);
    t.setY(530);
    t.draw(g);

    w.setX(330);
    w.setY(530);
    w.draw(g);

    for(Platforms pl:listPlatforms2){//drawing the platforms
     pl.x=pl.orgX;
     pl.setOffset(offset);
     pl.draw(g);
   }

   for(Horcrux h:listHorcrux){
    if (h.isEnable()){
      h.setX(h.orgX); //drawing the horcruxes
      h.setOffset(offset);
      h.draw(g);
    } 
   }

    if(spellbtn.contains(mouse)){
     g2.drawOval(b.orgX-30,485, 35, 33);
    }
   if(spellbtn2.contains(mouse)){
     g2.drawOval(gr.orgX-30,485, 35, 33);
   }
   if(spellbtn3.contains(mouse)){
     g2.drawOval(o.orgX-30,485, 35, 36);
   }
   if(spellbtn4.contains(mouse)){
     g2.drawOval(p.orgX-30,530, 35, 36);
   }
   if(spellbtn5.contains(mouse)){
     g2.drawOval(t.orgX-30,530, 35, 31);
   }
   if(spellbtn6.contains(mouse)){
     g2.drawOval(w.orgX-30,530, 35, 37);
   }

   g2.setColor(Color.YELLOW);
   if(currentSpell == bs){
     g2.drawOval(b.orgX-30,485, 35, 33);
   }
   if(currentSpell == gs){
     g2.drawOval(gr.orgX-30,485, 35, 33);
   }
   if(currentSpell == os){
     g2.drawOval(o.orgX-30,485, 35, 36);
   }
   if(currentSpell == ps){
     g2.drawOval(p.orgX-30,530, 35, 36);
   }
   if(currentSpell == ts){
     g2.drawOval(t.orgX-30,530, 35, 31);
   }
   if(currentSpell == ws){
     g2.drawOval(w.orgX-30,530, 35, 37);
   }

    g.setColor(Color.white);
    g.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 30)); 
    g.drawString("Lives: "+lives, 796,508); //displays the points
    
    g.drawString("Spells: ", 8,508); 
    g.drawString("x"+blueSpell, 160, 508);
    g.drawString("x"+greenSpell, 250, 508);
    g.drawString("x"+orangeSpell, 340, 508);
    g.drawString("x"+purpleSpell, 160, 550);
    g.drawString("x"+torquiseSpell, 250, 550);
    g.drawString("x"+whiteSpell, 340, 550);

    g.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 50)); 
    g.drawString("Level 2 ", 430,35); 

    for(MakeSpell b:listSpell){ //drawing the bullets
      b.draw(g);
    } 
 }
 

}

//Harry class
class Harry{
 private int x,y,vy,startY; //the starting xy pos
 private Image[]pics;
  private Image[]castSpells;
 private boolean inAir;
 private int dir, jumpCount;
 public static final int LEFT = 0, RIGHT = 1, WAIT = 5; 
 private boolean isCollide;

  public Harry(int x, int y, String name, int n){
  this.x=x;
  this.y=y;
  startY=y;
  vy=0;
  dir = RIGHT;
  inAir = false;
  isCollide=false;
  pics = new Image[n]; 
  castSpells = new Image[n];  
  for(int i = 0; i<n; i++){
   pics[i] = new ImageIcon("images/"+name+"/"+name+i+".png").getImage();
  }
   for(int i = 0; i<n; i++){
   castSpells[i] = new ImageIcon("images3/"+name+"/"+name+i+".png").getImage();
  }
 }

  public boolean getInAir(){
    return inAir;
  }

  public int getX(){
   return x;
 }
 public int getY(){
   return y;
 }
 public void setCollide(boolean f){
   isCollide=f;
 }

  public void setY(int y){
   this.y=y;
  }

  public void setVy(int y){
    this.vy=y;
  }
  public void setInAir(boolean air){
    inAir = air;
  }

  public int getVy(){
    return vy;
  }
  public void resetY(){
    y=startY;
  }
  
  public void update(){   //updates harry 
  vy += 1;
  y += vy;
  
  // if(y>startY){
  //  inAir = false;
  //  y = startY;
  //  vy=0;
  // }
 }
 
 public void jump(){
  System.out.println('y'+y);
  vy = -15;
  inAir = true;
  jumpCount=0;
 }
 
 public void move(int dx){  
  x += dx;
  System.out.println("test"+x +" y" + y + "Inir"+inAir);
  if(dx>0){
   dir = RIGHT;
  }
  else{
   dir = LEFT;
  }
  //  if(x+dx>1000){
//    x = 1000; //im trying to find which x spot to put him in if he goes off
//  }
 }

 public void draw(Graphics g){
  int fr=0;
   
  if(inAir){
   jumpCount++;
   fr = Math.min(8,jumpCount/3);   
  }
  if(dir == RIGHT){
    g.drawImage(pics[fr], 20, y, null);
  }  
  //if(dir == RIGHT){
  // g.drawImage(pics[fr],20, y, null); //drawing harry 
  //}
  else if(dir == LEFT){
   int w = pics[fr].getWidth(null);
    int h = pics[fr].getHeight(null);
   g.drawImage(pics[fr], 20 + w, y, -w, h, null);
  }

  //g.drawRect(x, y,  pics[fr].getWidth(null), pics[fr].getHeight(null));
 }

 public void spellDraw(Graphics g){ //call draw when the space bar is pressed
  int fr=0;
  if(inAir){
   jumpCount++;
   fr = Math.min(8,jumpCount/3);   
  }
  
  if(dir == RIGHT){
   g.drawImage(castSpells[fr], 20, y, null);
  }
  else if(dir == LEFT){
   int w = castSpells[fr].getWidth(null);
   int h = castSpells[fr].getHeight(null);
   g.drawImage(pics[fr], 20 + w, y, -w, h, null);
  }
 }

 public boolean collideHorcrux(Horcrux hor){
  Shape circle = new Ellipse2D.Float(hor.getX(), hor.getY(), 25.0f, 25.0f);
  Rectangle2D harry = new Rectangle2D.Double(20, y,20, 54);
    if(circle.intersects(harry) && hor.isEnable()){
      return true;
    }
    return false;
 }

   public boolean collideDementor(Dementor dem){ //dementor collision
    Rectangle2D d = new Rectangle2D.Double(dem.getX(),dem.getY(),80,127);
    Rectangle2D harry = new Rectangle2D.Double(20, y,20, 54); 
    if(d.intersects(harry)){
      return true;
    }
    return false;
  }

  public boolean checkPlats(ArrayList<Platforms> plats){
    //inAir = false;
		// ground
		if(y > 500){
			y = 500;
			vy = 0;
			inAir = false;
		}
		Rectangle gr = new Rectangle(x,y,20,50);
		Rectangle grNoVY = new Rectangle(x,y-vy,20,50);		
			
		for(Platforms r: plats){
			// currently overlaps
     Rectangle rr = r.genRects();
     
			if(gr.intersects(rr)){
        y = r.y;
         System.out.println("collide"+x +" y" + y +"plat"+r.x);
				// moving down
				if(vy > 0){
					// caused by moving down
					if(!grNoVY.intersects(rr)){
						y = r.y-50;
						vy = 0;
						inAir = false;						
					}					
				}
        return true; 

			}		
		}	
    return false;	
  }
}

class Platforms{
  private int offset;
  public int x,y,w,h; 
  public int yy=0;
  public int orgX; //original x
  Image platform;
 private  Rectangle plats;

  public Platforms(int x, int y,int w,int h,Image g){
    this.x=x;
    this.y=y;
    this.platform=g;
    this.w=w;
    this.h=h;
    this.yy=y-60;
    this.orgX=x;
    //plats = new ArrayList<Rectangle>();
    //System.out.println(yy + "plat" );   
  }
   public Platforms(int x, int y,int w,int h){
    this.x=x;
    this.y=y;
     
    this.w=w;
    this.h=h;
    this.yy=y-60;
    this.orgX=x;
    //plats = new ArrayList<Rectangle>();
    //System.out.println(yy + "plat" );   
  }
   public static int randint(int low, int high){
    return(int)(Math.random()*(high-low+1)+low);   
  }
 public Point getHorcSpot(){
   int spot = (int)(x+ w*0.90);
   return new Point(randint(x,spot), y-32);
 }

   public Platforms(){
    
  }

  public void draw(Graphics g){   
    //x=x+(offset-30);
    x= x+offset;
    g.drawImage(platform,x,y,null);
    //g.fillRect(x,y,w,h);
  }

  public void setOffset(int o){
    this.offset=o;
  }
  
  public Rectangle genRects() {
    Rectangle p1 = new Rectangle(x,y,w,h); 
    plats=p1;
    return p1;
  }

  public boolean detectHarryCollision(Harry harry){
    genRects();
    Rectangle h = new Rectangle(20,harry.getY(),20,54);//harry rectangle
    if(plats.intersects(h)){
      return true;
    }          
    return false; 
  }
}


class Horcrux{
  private int offset; 
  private int x,y; //the x y position
  public int orgX; //original x
  public String type; //variable that I use to differentiate between spells 
  Image gold;  
  boolean enable=true;
  
  public Horcrux(int x, int y, Image yellow, String s){
    this.x=x;
    this.y=y;
    this.gold=yellow;
    this.type=s;
    this.orgX=x;
  }

  int randint(int low, int high){
		return (int)(Math.random()*(high-low+1)+low);
	}

  public MakeSpell spell(Harry h){ 
    Point p= xy(0,50);
    return new MakeSpell(30+p.x,h.getY()+p.y+30,p.x/10.0,p.y/10.0);
  }

  public Point xy (double ang, double mag){ //returns a Point object when given an angle and magnitude 
     int x = (int)(Math.cos(Math.toRadians(ang)) *mag);
     int y = (int)(Math.sin(Math.toRadians(ang)) *mag);
     return new Point(x,y);
   }

  public void setOffset(int o){ //sets the offset so the horcrux does not move
    this.offset=o;
  }
    
  public void setX(int xx){ //sets the horcruxes x pos
    this.x = xx;
  }

  public void setY(int yy){ //sets the horcruxes y pos
     this.y= yy;
  }

  public int getX(){
    return x;
  }
  public boolean isEnable(){
    return enable;
  }
  public void setEnable(boolean flag){
    enable=flag;
  }

  public int getY(){
    return y;
  }
     
  public void draw(Graphics g){ //draws the horcrux
    x=x+(offset-30);
    //System.out.println(x + "cir"  +y);
    g.drawImage(gold,x,y,null); 
  } 

}


class Dementor{
  private int x,y,vy,startY; //the starting xy pos
  private Image[]pics;
  private boolean inAir;
  private int dir, jumpCount;
  private int dx;
  private int dy;
  public static final int LEFT = 0, RIGHT = 1, WAIT = 5; 
  private boolean isCollide;
  
  public Dementor(int x, int y, String name, int n){
    this.x=x;
    this.y=y;
    startY=y;
    vy=0;
    dir = RIGHT;
    inAir = false;
    isCollide=false;
    pics = new Image[n]; 
    dx = 1;
    dy = 2;
    for(int i = 0; i<n; i++){
    pics[i] = new ImageIcon("images2/"+name+"/"+name+i+".png").getImage();
    }
  }

  public boolean getInAir(){
    return inAir;
  }

  public void update(){  
    vy += 1;
    y += vy;
    if(y>startY){
    inAir = false;
    y = startY;
    vy=0;
    }
  }

  public static int randint(int low, int high){
    return(int)(Math.random()*(high-low+1)+low);   
  }
  
  public int getX(){ //gets  x position
    return x;
  }
  public int getY(){ //gets y position
    return y;
  }
    public void setCollide(boolean f){
    isCollide=f;
  }
  public void setY(int y){ //sets harrys y position
    this.y=y;
  }
  
  public void move(){
    if(x+dx>1000){ //if the dementor goes off the screen it comes back in a random spot on the screen
      x= randint(50,800);
    }
    else if(y+dy>600){ //if the dementor goes off the screen it comes back in a random spot on the screen
      y= randint(10,560);
    }
    x+=dx; //increases the dementor's x position
    y+=dy; //increases the dementor's y position
  }

    public void draw(Graphics g){
      int fr=0;
      if(inAir){
        jumpCount++;
        fr = Math.min(8,jumpCount/3);   
    } 
    if(dir == RIGHT){
      g.drawImage(pics[fr], x, y, null);
    }
    else if(dir == LEFT){
      int w = pics[fr].getWidth(null);
      int h = pics[fr].getHeight(null);
      g.drawImage(pics[fr], x + w, y, -w, h, null);
    }
  }
  //public void stop(){
     // dx=0;
     // dy=0;
   // }
}

class MakeSpell{
  private double vx; 
  private double vy;
  private double x,y; 
 
  public MakeSpell(double x,double y, double vx,double vy){
    this.x=x;
    this.y=y;
    this.vx=vx;
    this.vy=vy;
  }

  public void draw(Graphics g){ //draws the bullet
    Graphics2D g2d = (Graphics2D)g;  
    g2d.setColor(Color.white);
    g2d.fillRect((int)x,(int)y,3,3);    
  }

  public void move(){//moves the bullet
    x += vx;
    y += vy;
  } 

  public int getX(){ //use getX and getY in detectBulletCollide
    return (int)x;
  }
  public int getY(){
    return (int)y;
  }

  public boolean detectSpellCollision(Dementor dem){
   Rectangle2D d = new Rectangle2D.Double(dem.getX(),dem.getY(),70,127);
   Rectangle2D b = new  Rectangle2D.Double(x,y,3,3);
   if(b.intersects(d)){
     return true;
   }
   return false;
  }
}

class SpellDamage{
  private int x,y;

  public SpellDamage(){
    this.x=x;
    this.y=y;
  }


 
}

class Villian{
  private int x,y,vy,startY; //the starting xy pos
  private Image[]pics;
  private boolean inAir;
  private int dir, jumpCount;
  private int dx;
  private int dy;
  public static final int LEFT = 0, RIGHT = 1, WAIT = 5; 
  private boolean isCollide;
    public Villian(int x, int y, String name, int n){
    this.x=x;
    this.y=y;
    startY=y;
    vy=0;
    dir = RIGHT;
    inAir = false;
    isCollide=false;
    pics = new Image[n];   
    for(int i = 0; i<n; i++){
    pics[i] = new ImageIcon("images4/"+name+"/"+name+i+".png").getImage();
    }

    dx= 1;
    dy =1; 
  }
  public boolean getInAir(){
    return inAir;
  }

  public void update(){  
    vy += 1;
    y += vy;
    if(y>startY){
    inAir = false;
    y = startY;
    vy=0;
    }
  }

  public static int randint(int low, int high){
    return(int)(Math.random()*(high-low+1)+low);   
  }
  
  public int getX(){ //gets  x position
    return x;
  }
  public int getY(){ //gets y position
    return y;
  }
    public void setCollide(boolean f){
    isCollide=f;
  }
  public void setY(int y){ //sets harrys y position
    this.y=y;
  }
  
  public void move(){
    x+=dx; //increases the dementors x position
   if(x+dx>1000 && dx>0){ //if the dementor goes off the screen, it comes back onto the screen   
     dir=LEFT;
     x=0; 
   }
   if(x+dx<0 && dx<0){
     dir=RIGHT;
     x=1000;
   }
 }

 public void purpleMove(){
    x+=dx;
    if(dir==LEFT && x+dx>1000 && dx>0){
      dir=LEFT;
      x=1000;
    }
    if(dir==RIGHT && x+dx<0 && dx<0){
      dir=RIGHT;
      x=0;
    }
 }
  public void draw(Graphics g){ //call draw when the space bar is pressed
    int fr=0;
    if(inAir){
    jumpCount++;
    fr = Math.min(8,jumpCount/3);   
    }
    
    if(dir == RIGHT){
    g.drawImage(pics[fr], x, y, null);
    }
    else if(dir == LEFT){
    int w = pics[fr].getWidth(null);
    int h = pics[fr].getHeight(null);
    g.drawImage(pics[fr], x + w, y, -w, h, null);
    }
  }
}
