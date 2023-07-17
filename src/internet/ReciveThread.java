package internet;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

//@author 数科21-1 陈巴楚 2110181194
public class ReciveThread implements Runnable{
    @Override
    public void run() {
        ReciveThread x2=new ReciveThread();
        try {
            x2.Server();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Server() throws IOException {
        //1.开启服务器，参数是指开着的端口号
        ServerSocket server = new ServerSocket(8899);
        System.out.println("服务器已成功启动");
        //2.接收客户端发来的连接
        Socket socket = server.accept();
        System.out.println("接收到客户端发来的请求");
        //3.获取读取流
        InputStream in = socket.getInputStream();
        //4.读取数据
        GuiIn.j1.append(GuiIn.jt1.getText()+"对"+GuiIn.jt2.getText()+"说：");
        byte[] bys = new byte[1024];
        while((in.read(bys))!=-1) {
            String data = new String(bys);
            GuiIn.j1.append(data);
        }
        GuiIn.j1.append("\n");
        in.close();
        server.close();

    }
}
