/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equationPlotter.PlottingFrame;

/**
 *
 * @author dell
 */
import java.awt.*;
import javax.swing.*;

public class GraphPlotter extends JPanel
{
    // instance variables:
    private static String []functions;
    private static Double []constants;
    private static Color []colors;
    private int SCALE = 20;
    JButton zoomIn_Button;
    JButton zoomOut_Button;
     /*
    Constructor for objects of class Plotter
     */
    public GraphPlotter(String[] functions, Double[] constants, Color[] colors)
    {
        initComponent();
        
        GraphPlotter.functions = new String[3];
        GraphPlotter.constants = new Double[12];
        GraphPlotter.colors = new Color[3];
        System.arraycopy(functions, 0, GraphPlotter.functions, 0, functions.length);
        System.arraycopy(constants, 0, GraphPlotter.constants, 0, constants.length);
        System.arraycopy(colors, 0, GraphPlotter.colors, 0, colors.length);        
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(900,700);
    }

    public void initComponent()
    {
        setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
        
        zoomIn_Button = new javax.swing.JButton();
        zoomOut_Button = new javax.swing.JButton();

        zoomIn_Button.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        zoomIn_Button.setText("Zoom In");

        zoomOut_Button.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        zoomOut_Button.setText("Zoom Out");
        
        zoomIn_Button.setOpaque(false);
        zoomIn_Button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                zoomIn_ButtonMouseClicked(evt);
            }
        });

        zoomOut_Button.setOpaque(false);
        zoomOut_Button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                zoomOut_ButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(218, Short.MAX_VALUE)
                .addComponent(zoomIn_Button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(zoomOut_Button)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(241, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zoomIn_Button)
                    .addComponent(zoomOut_Button))
                .addGap(36, 36, 36))
        );
    }
    
    private void zoomIn_ButtonMouseClicked(java.awt.event.MouseEvent evt) {                                      
        if(SCALE <= 60)
        {
            SCALE = SCALE + 3;
            repaint();
        }
    }                                     

    private void zoomOut_ButtonMouseClicked(java.awt.event.MouseEvent evt) {                                  
        if(SCALE >= 12)
        {
            SCALE = SCALE - 3;
            repaint();
        }
    }             
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        int abscissa, ordinate, fontSize = 9;
        double a_const, b_const, c_const, d_const;
        Font font = new Font("Comic Sans MS", Font.BOLD, fontSize);
        g.setFont(font);
        Graphics2D gThick = (Graphics2D) g;
        gThick.setStroke(new BasicStroke(2));
       
        for(int i=Math.round(getHeight()/2); i>0; i-=SCALE) //Draws horizontal lines in upper-half region
        {
            g.setColor(Color.lightGray);
            g.drawLine(0,i,getWidth(),i);
            g.setColor(Color.black);
            ordinate = (getHeight()/2-i)/SCALE;
            if(ordinate != 0)   g.drawString(ordinate+"",getWidth()/2,i);
        }
        
        for(int i=Math.round(getWidth()/2); i>0; i-=SCALE) //Draws vertical lines in left-half region
        {
            g.setColor(Color.lightGray);
            g.drawLine(i,0,i,getHeight());
            g.setColor(Color.black);
            abscissa = (i-getWidth()/2)/SCALE;
            if(abscissa != 0)    g.drawString(abscissa+"",i,getHeight()/2+10);
        }
        
        for(int i=Math.round(getHeight()/2); i<getHeight(); i+=SCALE) //Draws horizontal lines in lower-half region
        {
            g.setColor(Color.lightGray);
            g.drawLine(0,i,getWidth(),i);
            g.setColor(Color.black);
            ordinate = (getHeight()/2-i)/SCALE;
            if(ordinate != 0)   g.drawString(ordinate+"",getWidth()/2,i);
        }
        
        for(int i=Math.round(getWidth()/2); i<getWidth(); i+=SCALE) //Draws vertical lines in right-half region
        {
            g.setColor(Color.lightGray);
            g.drawLine(i,0,i,getHeight());
            g.setColor(Color.black);
            abscissa = (i-getWidth()/2)/SCALE;
            if(abscissa != 0)    g.drawString(abscissa+"",i,getHeight()/2+10);
        }
               
        g.setColor(Color.black);
        g.drawLine(0,Math.round(getHeight()/2),Math.round(getWidth()),Math.round(getHeight()/2)); //X-Axis
        g.drawLine(Math.round(getWidth()/2),0,Math.round(getWidth()/2),Math.round(getHeight()));  //Y-Axis           
                
        for(int functionMode=0; functionMode<functions.length; functionMode++)
        {
            g.setColor(colors[functionMode]);
            try{
                a_const =  constants[4*functionMode+0];        
                b_const = constants[4*functionMode+1];
                c_const = constants[4*functionMode+2];
                d_const = constants[4*functionMode+3];
            }
            catch(NullPointerException e)
            {
                a_const = 1;
                b_const = 1;
                c_const = 1;
                d_const = 1;
            }
            switch(functions[functionMode])
            {
                case "a*Sin(bx)":

                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = SCALE * a_const * Math.sin(2.86 * b_const * x * (Math.PI/180));
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;
                
                case "a*Cos(bx)":
      
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = a_const * SCALE * (Math.cos(b_const * 2.86 * x * (Math.PI/180)));
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;

                case "a*Tan(bx)" :
     
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = a_const * SCALE * Math.tan(b_const * 2.86 * x * (Math.PI/180));
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;

                case "a*Cot(bx)" :
    
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = a_const * SCALE * (1.0/Math.tan(b_const * 2.86 * x * (Math.PI/180)));
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;

                case "a*Sec(bx)" :
   
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = a_const * SCALE * (1.0/Math.cos(b_const * 2.86 * x * (Math.PI/180)));
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;

                case "a*Cosec(bx)" :
     
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = a_const * SCALE * (1.0/Math.sin(b_const * 2.86 * x * (Math.PI/180)));
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;

                case "a*arcSin(bx)" :
       
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = a_const * SCALE * Math.asin(b_const * 2.86 * x * (Math.PI/180));
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;

                case "a*arcCos(bx)" :
          
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = a_const * SCALE * Math.acos(b_const * 2.86 * x * (Math.PI/180));
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;

                case "a*arcTan(bx)" :
           
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = a_const* SCALE * Math.atan(b_const * 2.86 * x * (Math.PI/180));
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;

                case "a*arcSec(bx)" :
          
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = a_const * SCALE * Math.acos(1 / (b_const* x * 2.86 * (Math.PI/180)));
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;

                case "a*arcCosec(bx)" :
            
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = a_const * SCALE * Math.asin(1 / (b_const * 2.86 * x * (Math.PI/180)));
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;

                case "a*arcCot(bx)" :
         
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = a_const * SCALE * Math.atan(1 / (b_const * 2.86 * x * (Math.PI/180)));
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;

                case "a*Sinh(bx)" :
        
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = a_const * SCALE * Math.sinh(b_const * 2.86 * x * (Math.PI/180));
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;

                case "a*Cosh(bx)" :
         
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = a_const * SCALE * Math.cosh(b_const * 2.86 * x * (Math.PI/180));
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;

                case "a*Tanh(bx)" :
         
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = a_const * SCALE * Math.tanh(b_const * 2.86 * x * (Math.PI/180));
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;
                    
                case "a*arcSinh(bx)" :
         
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = (a_const/2) * SCALE * Math.log(b_const * 2.86 * x * (Math.PI/180) + Math.sqrt(Math.pow(b_const * 2.86 * x * (Math.PI/180),2) + 1));
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;
                    
                case "a*arcCosh(bx)" :
         
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = (a_const/2) * SCALE * Math.log(b_const * 2.86 * x * (Math.PI/180) + Math.sqrt(Math.pow(b_const * 2.86 * x * (Math.PI/180),2) - 1));
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;
                
                case "a*arcTanh(bx)" :
         
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = (a_const/2) * SCALE * Math.log((1 + b_const * 2.86 * x * (Math.PI/180))/(1 - b_const * 2.86 * x * (Math.PI/180)));
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;

                case "1 Degree: y = ax + b" : 
         
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = SCALE * (a_const * (x / SCALE) + b_const);
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;

                case "2 Degree: ax^2 + bx + c" : 
   
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = SCALE * (a_const * Math.pow(x/SCALE,2) + b_const * (x/SCALE) + c_const);
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;

                case "3 Degree: ax^3 + bx^2 + cx + d" : 
       
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = SCALE * ((a_const * Math.pow(x/SCALE,3) + b_const * Math.pow(x/SCALE,2) + c_const * (x/SCALE) + d_const));
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }    
                    break;

                case "Circle: (x – a)^2 + (y – b)^2 = c^2" :  
         
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double yPos = SCALE * (b_const + Math.sqrt(Math.pow(c_const,2)-Math.pow((x-a_const)/SCALE,2)));
                        double yNeg = SCALE * (b_const - Math.sqrt(Math.pow(c_const,2)-Math.pow((x-a_const)/SCALE,2)));
                        int Y_Pos = (int)yPos;
                        int Y_Neg = (int)yNeg;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y_Pos,getWidth()/2+X,getHeight()/2-Y_Pos);
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y_Neg,getWidth()/2+X,getHeight()/2-Y_Neg);
                        
                    }
                    break;
                    
                case "Ellipse: (x/a)^2 + (y/b)^2 = 1" :  
                    
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double yPos = SCALE * (b_const * Math.sqrt(1-(Math.pow(x/(a_const*SCALE),2))));
                        double yNeg = -SCALE * (b_const * Math.sqrt(1-(Math.pow(x/(a_const*SCALE),2))));
                        int Y_Pos = (int)yPos;
                        int Y_Neg = (int)yNeg;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y_Pos,getWidth()/2+X,getHeight()/2-Y_Pos);
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y_Neg,getWidth()/2+X,getHeight()/2-Y_Neg);
                    }
                    break;

                case "Parabola 1: (y-b)^2 = 4c(x-a)" : 
                    
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double yPos = SCALE * (b_const + Math.sqrt(4 * c_const * ((x/SCALE)-a_const)));
                        double yNeg = SCALE * (b_const - Math.sqrt(4 * c_const * ((x/SCALE)-a_const)));
                        int Y_Pos = (int)yPos;
                        int Y_Neg = (int)yNeg;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y_Pos,getWidth()/2+X,getHeight()/2-Y_Pos);
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y_Neg,getWidth()/2+X,getHeight()/2-Y_Neg);
                    }
                    break;
                    
                case "Parabola 2: (x-a)^2 = 4c(y-b)" : 
         
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = SCALE * (b_const + (Math.pow((x/SCALE)-a_const,2) / (4*c_const)));
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;

                case "Hyperbola 1:  (x/a)^2 - (y/b)^2 = 1":
         
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double yPos = SCALE * b_const * Math.sqrt(Math.pow(x / (a_const * SCALE),2)-1);
                        double yNeg = -SCALE * b_const * Math.sqrt(Math.pow(x / (a_const * SCALE),2)-1);
                        int Y_Pos = (int)yPos;
                        int Y_Neg = (int)yNeg;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y_Pos,getWidth()/2+X,getHeight()/2-Y_Pos);
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y_Neg,getWidth()/2+X,getHeight()/2-Y_Neg);
                    }
                    break;

                case "Hyperbola 2:  (y/b)^2 - (x/a)^2 = 1": 
                    
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double yPos = SCALE * b_const * Math.sqrt(Math.pow(x / (a_const * SCALE),2) + 1);
                        double yNeg = -SCALE * b_const * Math.sqrt(Math.pow(x / (a_const * SCALE),2) + 1);
                        int Y_Pos = (int)yPos;
                        int Y_Neg = (int)yNeg;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y_Pos,getWidth()/2+X,getHeight()/2-Y_Pos);
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y_Neg,getWidth()/2+X,getHeight()/2-Y_Neg);
                    }
                    break;

                case "Rectangular Hyperbola: x*y = a" : 
        
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = 400 * (a_const / x);
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;

                case "ln(ax): (base e)" :
         
                    for(double x=0; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = SCALE*Math.log(a_const * x / SCALE);
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;

      
                case "log(ax): (base 10)" : 
                    
                    for(double x=0; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = SCALE * Math.log10(a_const * x / SCALE);
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;

                case "e^(ax)" :
         
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = SCALE*Math.pow(Math.E,a_const*x/SCALE);
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;

                case "(b/x)^a" :

                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = SCALE*Math.pow((SCALE*b_const/x),(a_const));
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;
                    
                case"a^(bx)" :
                    System.out.println("ENtered successfully");
                    for(double x=-getWidth()/2; x<=getWidth()/2; x=x+0.005)
                    {
                        double y = SCALE*Math.pow(a_const, b_const*x/SCALE);
                        int Y = (int)y;
                        int X = (int)x;
                        g.drawLine(getWidth()/2+X,getHeight()/2-Y,getWidth()/2+X,getHeight()/2-Y);
                    }
                    break;                    
            }
        }
    }
}

