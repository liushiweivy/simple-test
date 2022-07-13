package simple.jvmdemo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RoseJFrame extends JFrame implements ActionListener 
{
    private RoseCanvas canvas;                             //自定义画布组件
    
    public RoseJFrame()
    {
        super("四叶玫瑰线");                                    //框架边布局
        Dimension dim=this.getToolkit().getScreenSize();    //获得屏幕分辨率
        this.setBounds(dim.width/4,dim.height/4,dim.width/2,dim.height/2);  //窗口居中
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel jpanel = new JPanel();                      //面板流布局，居中
        this.getContentPane().add(jpanel,"North");
        JButton button_color = new JButton("选择颜色");
        jpanel.add(button_color);
        button_color.addActionListener(this);

        this.canvas = new RoseCanvas(Color.red);           //创建自定义画布组件
        this.getContentPane().add(this.canvas,"Center");
        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ev)            //按钮动作事件处理方法
    {
        Color c=JColorChooser.showDialog(this,"选择颜色",Color.blue); //弹出JColorChooser颜色选择对话框，返回选中颜色
        this.canvas.setColor(c);
        this.canvas.repaint();                             //调用canvas的paint(Graphics)方法，重画
    }
    public static void main(String arg[])
    {
        new RoseJFrame();
    }
}