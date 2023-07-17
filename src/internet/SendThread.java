package internet;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

//@author 数科21-1 陈巴楚 2110181194
public class SendThread implements Runnable{

    @Override
    public void run() {
        SendThread x1=new SendThread();
        try {
            x1.Client();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Client() throws IOException {
        Socket socket = new Socket("127.0.0.1",8899);
        System.out.println("已连接成功");
        //2.获取写出流
        OutputStream out = socket.getOutputStream();
        //3.写出数据,字节流只能写出整数或字节数组
        //将hello对应整数编程对应的字节数组，getBytes()将String转换为byte[]
        out.write(GuiIn.jt3.getText().getBytes());
        out.close();
    }
}
