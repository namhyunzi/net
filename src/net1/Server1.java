package net1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server1 {

	public static void main(String[] args) throws IOException {
		
		// 클라이언트의 요청을 기다리는 ServerSocket 객체를 생성한다.
		// ServerSocket 겍체는 자신이 사용할 포트번호를 지정받는다.
		ServerSocket server = new ServerSocket(30000);
		System.out.println("서버가 기동됨...");
		System.out.println("클라이언트의 연결요청 대기중...");
		
		// accept() 메소드는 클라이언트의 연결요청을 기다리는 메소드다.
		// 클라이언트의 연결요청이 접수될 때까지 메소드의 실행이 연기된다.
		// 클라이언트의 연결요청이 접수되면 그 클라이언트와 통신하는 Socket 객체를 반환한다.
		Socket socket = server.accept();
		System.out.println("클라이언트의 연결요청이 접수됨...");
		
		InetAddress inetAddress = socket.getInetAddress();
		System.out.println("접속을 요청한 클라이언트 ip:  "+ inetAddress.getHostAddress());

	}

}
