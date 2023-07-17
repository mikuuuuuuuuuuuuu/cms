package internet;
//@author 数科21-1 陈巴楚 2110181194
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GuiIn {
    //窗口
    public static JFrame jframe1 =new JFrame("聊天框");
    //组件
    public static JPanel jpanel1=new JPanel();//流式布局
    public static JPanel jpanel2=new JPanel();//空布局
    //文本框
    public static JTextField jt1=new JTextField(11);
    public static JTextField jt2=new JTextField(11);
    public static JTextField jt3=new JTextField(20);
    //文本域
    public static JTextArea j1=new JTextArea(30,30);


    public static void main(String[] args) {
        GuiIn.jiemian();
    }

    public static void jiemian(){

        JLabel jLabel1 = new JLabel("本机ip：");
        JLabel jLabel2 = new JLabel("对方ip：");
        JLabel jLabel3 = new JLabel("输入：");

        JButton jbutton1=new JButton("发送");

        jframe1.setSize(400,500);
        jframe1.setLayout(null);
        jframe1.setLocation(600,200);
        jframe1.setResizable(false);

        jpanel1.setBounds(0,0,400,350);
        jpanel2.setBounds(0,350,400,150);
        jpanel1.add(j1);

        jpanel2.add(jLabel1);
        jpanel2.add(jt1);
        jpanel2.add(jLabel2);
        jpanel2.add(jt2);
        jpanel2.add(jLabel3);
        jpanel2.add(jt3);
        jpanel2.add(jbutton1);

        jframe1.add(jpanel1);
        jframe1.add(jpanel2);



        jbutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SendThread sendThread = new SendThread();
                ReciveThread reciveThread = new ReciveThread();

                Thread thread2 = new Thread(reciveThread);
                Thread thread1 = new Thread(sendThread);

                thread2.start();
                thread1.start();
            }
        });

        jframe1.setVisible(true);
        jframe1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }



}
