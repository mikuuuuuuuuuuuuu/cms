package gui;
import jdbc.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
//@author 数科21-1 陈巴楚 2110181194
public class Login {
    //窗口全部设成静态变量
    public static JFrame jf1=new JFrame("学生信息管理系统");
    public static JFrame jf2=new JFrame("注册");
    public static JFrame jf3=new JFrame("主界面");

    //主界面的两个面板静态
    public static JPanel jpanel1=new JPanel(new FlowLayout());//流式布局
    public static JPanel jpanel2=new JPanel();//空布局

    //主界面的两个查询文本静态
    public static JTextField jt1=new JTextField(15);
    public static JTextField jt2=new JTextField(15);
    //主界面的文本域静态
    public static JTextArea j1=new JTextArea(30,50);

    //	登录界面的文本框和密码框
    public static JTextField jtext1=new JTextField(20);
    public static JPasswordField jpassword1=new JPasswordField(20);

    //	注册界面的文本框和密码框
    public static JTextField jtext3=new JTextField(20);
    public static JTextField jtext4=new JTextField(20);
    public static JTextField jtext5=new JTextField(20);
    public static JTextField jtext6=new JTextField(20);
    public static JTextField jtext7=new JTextField(20);
    public static JTextField jtext8=new JTextField(20);

    //程序入口
    public static void main(String[] args) {
        Login.init();
    }

    //登录界面图片方法
    public static void background1(){
        ((JPanel)jf1.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon("E:\\java-app\\\\idea\\\\fileaddress\\\\12.15大作业/tp.jpg"); //添加图片
        JLabel background = new  JLabel(img);
        jf1.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }
    //注册界面图片方法
    public static void background2(){
        ((JPanel)jf2.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon("E:\\java-app\\idea\\fileaddress\\12.15大作业/tp3.jpg"); //添加图片
        JLabel background = new  JLabel(img);
        jf2.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }
/*    登录界面图片原理详细
public static void background(){
        JLabel label3=new JLabel();//把背景图片添加到标签里
        ImageIcon bg = new ImageIcon("E:\\java-app\\idea\\fileaddress\\12.15大作业/tp.jpg");	//创建一个背景图片
        label3.setIcon(bg);

        label3.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());	//把标签设置为和图片等高等宽
        JPanel jp = (JPanel)jf1.getContentPane();		//把我的面板设置为内容面板
        jp.setOpaque(false);//把我的面板设置为不可视
        jf1.getLayeredPane().add(label3,new Integer(Integer.MIN_VALUE));

    }*/
//登陆界面方法
    public static void init(){

        Login.background1();
        jf1.setSize(320,240);
        jf1.setLayout(new FlowLayout());
        jf1.setLocation(500,300);
//		窗口不可调整
        jf1.setResizable(false);
        JLabel jLabel1 = new JLabel("学号：");
        JLabel jLabel2 = new JLabel("密码：");
        jtext1.setOpaque(false);
        jpassword1.setOpaque(false);
        jpassword1.setEchoChar('*');
//按钮
        JButton jbutton1=new JButton("登录");
        JButton jbutton2=new JButton("注册");

//组件加到窗口jf1里
        jf1.add(jLabel1);
        jf1.add(jtext1);
        jf1.add(jLabel2);
        jf1.add(jpassword1);
        jf1.add(jbutton1);
        jf1.add(jbutton2);

        //登录按钮监听
        jbutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //如果账号格式错误
                //声明对象c1调用CheakLoginWay

                //登录只需要判断账号密码是否存在
                Login c1 = new Login();
                CheakExist x1=new CheakExist();//创建调用 CheakExist的方法的对象x1
                    if (c1.CheakLoginWay(0)==false) {
                        jtext1.setText("");
                        JOptionPane.showMessageDialog(jbutton1, "学号格式错误\n允许10位数字", "提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                    //如果密码格式错误
                    else if (c1.CheakLoginWay(1)==false) {
                        jpassword1.setText("");
                        JOptionPane.showMessageDialog(jbutton1, "密码格式错误\n字母开头，允许5-16字节，允许字母数字下划线", "提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        //如果账号密码格式正确，账号不存在
                        try {
                            if (x1.CheakExcuteQuery(jtext1.getText(),String.valueOf(jpassword1.getPassword()))==1){
                                jtext1.setText("");
                                JOptionPane.showMessageDialog(jbutton1, "账号错误", "提示", JOptionPane.INFORMATION_MESSAGE);
                            }
                            //如果账号密码格式正确，账号存在，密码不存在
                            else if(x1.CheakExcuteQuery(jtext1.getText(),String.valueOf(jpassword1.getPassword()))==2){
                                jpassword1.setText("");
                                JOptionPane.showMessageDialog(jbutton1, "密码错误", "提示", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else{
                                JOptionPane.showMessageDialog(jbutton1, "登录成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                                jf1.dispose();//关闭jf1窗口
                                //初始化
                                Login.init3();//打开主界面
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
            }
        });
        //注册按钮监听
        jbutton2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //点击了注册按钮
                jf1.dispose();
                Login.init2();

            }
        });
        jf1.setVisible(true);
        jf1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }



    //注册界面方法
    public static void init2(){
        Login.background2();
        jf2.setSize(320,320);
        jf2.setLayout(new FlowLayout());
        jf2.setLocation(500,300);
//		窗口不可调整
        jf2.setResizable(false);
        JLabel jLabel3 = new JLabel("学号：");
        JLabel jLabel4 = new JLabel("密码：");
        JLabel jLabel5 = new JLabel("姓名：");
        JLabel jLabel6 = new JLabel("班级：");
        JLabel jLabel7 = new JLabel("邮箱：");
        JLabel jLabel8 = new JLabel("宿舍：");


        jtext3.setOpaque(false);
        jtext4.setOpaque(false);
        jtext5.setOpaque(false);
        jtext6.setOpaque(false);
        jtext7.setOpaque(false);
        jtext8.setOpaque(false);

        JButton jbutton3=new JButton("确定");

        jf2.add(jLabel3);
        jf2.add(jtext3);
        jf2.add(jLabel4);
        jf2.add(jtext4);
        jf2.add(jLabel5);
        jf2.add(jtext5);
        jf2.add(jLabel6);
        jf2.add(jtext6);
        jf2.add(jLabel7);
        jf2.add(jtext7);
        jf2.add(jLabel8);
        jf2.add(jtext8);
        jf2.add(jbutton3);

        jbutton3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //点击了注册确定按钮
                //声明对象c2调用CheakLoginWay

                //注册需要判断账号邮箱是否已经有了
                Login c2=new Login();
                CheakExist x2=new CheakExist();//创建调用 CheakExist的方法的对象x2
                if (c2.CheakRegisterWay(0)==false) {
                    jtext3.setText("");
                    JOptionPane.showMessageDialog(jbutton3, "学号格式错误\n允许10位数字", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
                //如果密码格式错误
                else if (c2.CheakRegisterWay(1)==false) {
                    jtext4.setText("");
                    JOptionPane.showMessageDialog(jbutton3, "密码格式错误\n字母开头，允许5-12字节，允许字母数字下划线", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (c2.CheakRegisterWay(2)==false) {
                    jtext7.setText("");
                    JOptionPane.showMessageDialog(jbutton3, "邮箱格式错误\n", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    //如果账号密码格式正确，账号存在
                    try {
                        if (x2.ExcuteQuery(jtext3.getText(),null)==1) {
                            jtext3.setText("");
                            JOptionPane.showMessageDialog(jbutton3, "账号已存在", "提示", JOptionPane.INFORMATION_MESSAGE);
                        }
                        //如果账号密码格式正确，账号不存在，学号是唯一判定重不重人。
                        else{
                            JOptionPane.showMessageDialog(jbutton3, "注册成功", "提示", JOptionPane.INFORMATION_MESSAGE);


                            try {
                                x2.AddRegister();
                            } catch (ClassNotFoundException ex) {
                                ex.printStackTrace();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }

                            jf2.dispose();//关闭jf2注册窗口
                            //初始化
                            Login.init3();//打开主界面
                        }
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        jf2.setVisible(true);
        jf2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }



    //主界面方法
    public static void init3(){
        jf3.setSize(810,400);
        jf3.setLayout(null);
        jf3.setResizable(false);
//		关闭窗口则退出程序
        jf3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jpanel1.setBounds(0,0,200,400);
        jpanel2.setBounds(210,0,600,400);
        jpanel1.setBackground(Color.LIGHT_GRAY);
        jpanel2.setBackground(Color.LIGHT_GRAY);
        //查询学生按钮的监听
        Login.find();
        jf3.add(jpanel1);
        jf3.add(jpanel2);
        //		窗口居中
        jf3.setLocationRelativeTo(null);
//		窗口显示
        jf3.setVisible(true);


    }

    //主界面查询功能
    public static void find() {
        JLabel jlb1=new JLabel("学号：");
        JLabel jlb2=new JLabel("宿舍：");
        JButton jbn1=new JButton("查找");

        jpanel1.add(jlb1);
        jpanel1.add(jt1);
        jpanel1.add(jlb2);
        jpanel1.add(jt2);
        jpanel1.add(jbn1);
        j1.setBounds(0,0,600,400);
        jpanel2.add(j1);
        CheakExist x3=new CheakExist();//创建调用 CheakExist的方法的对象x3
        jbn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Login c3=new Login();
                j1.setText("");
                    try {
                        if(x3.ExcuteQuery(jt1.getText(), jt2.getText()) == 3){
                            JOptionPane.showMessageDialog(jbn1, "查询学号宿舍成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                            x3.ExcuteQueryTest3(jt1.getText(),jt2.getText());
                        }
                        else {
                            if (x3.ExcuteQuery(jt1.getText(), jt2.getText()) == 1) {
                                JOptionPane.showMessageDialog(jbn1, "查询学号成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                                x3.ExcuteQueryTest1(jt1.getText());
                            } else if (x3.ExcuteQuery(jt1.getText(), jt2.getText()) == 2) {
                                JOptionPane.showMessageDialog(jbn1, "查询宿舍成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                                x3.ExcuteQueryTest2(jt2.getText());
                            } else {
                                jt1.setText("");
                                jt2.setText("");
                                JOptionPane.showMessageDialog(jbn1, "查询失败", "提示", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

            }
        });
    }


    //登录格式判断方法
    public  boolean CheakLoginWay(int shu){//如果shu是0返回账号的判断，是1返回密码的判断
        String regex1 = "^[0-9]{10,10}$";//用正则表达式定义
        String regex2 = "^[a-zA-Z][a-zA-Z0-9_]{4,11}$";//用正则表达式定义
        String str1 =jtext1.getText();
        String str2 = new String(jpassword1.getPassword());
        boolean flag1 = str1.matches(regex1);
        boolean flag2 = str2.matches(regex2);
        if(shu==0){
            return flag1;
        }
        else{
            return flag2;
        }
    }

    //主界面查找格式判断方法
    public  boolean CheakExcuteQueryWay(int shu){//如果shu是0返回账号的判断，是1返回密码的判断
        String regex1 = "^[0-9]{10,10}$";//用正则表达式定义
        String str1 =jt1.getText();
        boolean flag1 = str1.matches(regex1);
        if(shu==0){
            return flag1;
        }
        else
            return false;
    }

    //注册格式判断方法
    public boolean CheakRegisterWay(int shu){
        String regex1 = "^[0-9]{10,10}$";//用正则表达式定义
        String regex2 = "^[a-zA-Z][a-zA-Z0-9_]{4,11}$";//用正则表达式定义
        String regex3 = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";//用正则表达式定义
        //只需要判断学号密码邮箱
        String str1 =jtext3.getText();
        String str2 =jtext4.getText();
        String str3 =jtext7.getText();

        boolean flag1 = str1.matches(regex1);
        boolean flag2 = str2.matches(regex2);
        boolean flag3 = str3.matches(regex3);

        if(shu==0){
            return flag1;
        }
        else if(shu==1){
            return flag2;
        }
        else if(shu==2){
            return flag3;
        }
        return false;
    }

}
