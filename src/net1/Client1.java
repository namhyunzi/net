package net1;

import java.io.IOException;
import java.net.Socket;

public class Client1 {

	public static void main(String[] args) throws IOException {
		// Socket 객체를 생성한다.
		// 이 Socket 객체는 ip주소가 192.168.0.7이고 port 번호가 30000번으로
		// 연결요청을 보낸다.
		Socket socket = new Socket("192.168.0.7", 30000);

	}

}
