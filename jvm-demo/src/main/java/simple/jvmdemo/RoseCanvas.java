package simple.jvmdemo;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class RoseCanvas extends Canvas                     //四叶玫瑰线画布组件
{
    private Color color;                                   //颜色
    
    public RoseCanvas(Color color)
    {
        this.setColor(color);
    }
    public void setColor(Color color)
    {
        this.color = color;
    }
    public void paint(Graphics g)                          //在Canvas上作图
    {
        int x0 = this.getWidth()/2;                        //(x0,y0)是组件正中点坐标
        int y0 = this.getHeight()/2; 
        g.setColor(this.color);                            //设置画线颜色
        g.drawLine(x0,0,x0,y0*2);                          //画X轴
        g.drawLine(0,y0,x0*2,y0);                          //画Y轴
        for (int j=40; j<200; j+=20)                       //画若干圈四叶玫瑰线
            for (int i=0; i<1023; i++)                     //画一圈四叶玫瑰线的若干点
            {
                double angle = i*Math.PI/512;
                double radius = j*Math.sin(2*angle);       //四叶玫瑰线
                int x =(int)Math.round(radius * Math.cos(angle)*2);
                int y =(int)Math.round(radius * Math.sin(angle)); 
                g.fillOval(x0+x,y0+y,2,2);                 //画直径为1的圆就是一个点
            }
    }
}