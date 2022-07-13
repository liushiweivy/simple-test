package simple.jvmdemo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RoseNJFrame extends JFrame implements ActionListener,ComponentListener
{
    private JRadioButton radiobutton[];                    //单选按钮
    private JCheckBox checkbox;                            //复选框
    private RoseNCanvas canvas;                            //自定义画布组件
    
    public RoseNJFrame()
    {
        super("多叶玫瑰线");                                //框架边布局
        Dimension dim=getToolkit().getScreenSize();        //获得屏幕分辨率
        this.setBounds(dim.width/4,dim.height/4,dim.width/2,dim.height/2);  //窗口居中
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addComponentListener(this);                   //注册组件事件监听器

        JToolBar toolbar=new JToolBar();                   //创建工具栏，默认水平方向
        this.getContentPane().add(toolbar,"North");        //工具栏添加到框架内容窗格北部
        String rosestr[]={"一叶","四叶","三叶","八叶","五叶","十二叶","七叶","十六叶","九叶"};
        ButtonGroup bgroup = new ButtonGroup();            //按钮组
        radiobutton = new JRadioButton[rosestr.length];    //单选按钮数组
        for (int i=0; i<radiobutton.length; i++)
        {
            radiobutton[i]=new JRadioButton(rosestr[i]);   //单选按钮
            radiobutton[i].addActionListener(this);
            bgroup.add(radiobutton[i]);                    //单选按钮添加到按钮组
            toolbar.add(radiobutton[i]);                   //单选按钮添加到工具栏
        }        
        radiobutton[0].setSelected(true);                  //设置单选按钮的选中状态
        
        checkbox = new JCheckBox("Y轴",false);             //复选框
        toolbar.add(checkbox);
        checkbox.addActionListener(this);                  //复选框注册动作事件监听器
        JButton button_color = new JButton("选择颜色");
        toolbar.add(button_color);
        button_color.addActionListener(this);

        canvas = new RoseNCanvas(1,Color.red);                //创建自定义画布组件
        this.getContentPane().add(canvas,"Center");
        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e)             //按钮动作事件处理方法
    {
        if (e.getSource() instanceof JRadioButton)         //选择一个颜色复选框
            for (int i=0; i<radiobutton.length; i++)
                  if (e.getSource()==radiobutton[i])
                   {
                    canvas.setLeaf(i+1);
                    break;
                   }
        if (e.getSource()==checkbox)
        {
            canvas.setAxis(checkbox.isSelected());
            if (e.getActionCommand().equals("Y轴"))
                checkbox.setText("X轴");
            else
                checkbox.setText("Y轴");
        }
        if (e.getActionCommand().equals("选择颜色"))
        {
            Color c=JColorChooser.showDialog(this,"选择颜色",Color.blue); //弹出JColorChooser颜色选择对话框，返回选中颜色
            canvas.setColor(c);
        }
        canvas.repaint();                                  //重画
    }
    @Override
    public void componentResized(ComponentEvent e)         //改变组件大小时
    {
        canvas.repaint();                                  //重画
    }
    @Override
    public void componentMoved(ComponentEvent e) {}
    @Override
    public void componentHidden(ComponentEvent e) {}
    @Override
    public void componentShown(ComponentEvent e) {}

    public static void main(String arg[])
    {
        new RoseNJFrame();
    }
}

class RoseNCanvas extends Canvas                           //画布组件
{
    private int leaf;                                      //多叶玫瑰线的叶数  
    private boolean axis;                                  //轴，默认Y轴
    private Color color;                                   //颜色

    public RoseNCanvas(int leaf, Color color)
    {
        this.axis = false;
        this.setLeaf(leaf);   
        this.setColor(color);
    }
    void setLeaf(int leaf)
    {
        this.leaf = leaf;
    }
    void setAxis(boolean axis)
    {
        this.axis = axis;
    }
    void setColor(Color color)
    {
        this.color = color;
    }
    @Override
    public void paint(Graphics g)                          //在Canvas上作图
    {
        int x0 = this.getWidth()/2;                        //(x0,y0)是组件正中点坐标
        int y0 = this.getHeight()/2; 
        g.setColor(this.color);                            //设置画线颜色
        g.drawLine(x0,0,x0,y0*2);                          //画X轴
        g.drawLine(0,y0,x0*2,y0);                          //画Y轴
        for (int j=40; j<200; j+=20)                       //画若干圈多叶玫瑰线
            for (int i=0; i<1023; i++)                     //画一圈多叶玫瑰线的若干点
            {
                double angle = i*Math.PI/512, radius;
                if (!axis)
                    radius = j*Math.sin(this.leaf*angle);  //多叶玫瑰线沿X轴
                else
                    radius = j*Math.cos(this.leaf*angle);  //多叶玫瑰线沿Y轴
                int x =(int)Math.round(radius * Math.cos(angle));
                int y =(int)Math.round(radius * Math.sin(angle)); 
                g.fillOval(x0+x,y0+y,2,2);                 //画直径为1的圆就是一个点
            }
    }
}