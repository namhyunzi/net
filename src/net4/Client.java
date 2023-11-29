package net4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Client {
	
	private Map<Integer, Handler> map = new HashMap<>();
	private Scanner scanner = new Scanner(System.in);
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	
	public Client() {
		// Map 객체에 메뉴별 핸들러 객체를 저장한다.
		/// ClientXXXHandler 객체가 형변환되어서 Handler 타입으로 변환되어 Map 에 저장된다.
		/*
		 * Map<integer, Handler>
		 * [key:정수, value: Handler 타입객체의 주소값]
		 */
		map.put(Cmd.MENU_LIST, new ClientFileListHandler());
		map.put(Cmd.MENU_DOWNLOAD, new ClientFileDownloadHandler(scanner));
		map.put(Cmd.MENU_UPLOAD, new ClientFileUploadHandler(scanner));
		map.put(Cmd.MENU_EXIT, new ClientExitHandler());
		
	}
	/**
	 * 파일서버로 연결요청을 보내고, 스트림을 연결해서 서버와 통신할 준비를 마친다.
	 * @throws IOException
	 */
	public void startup() throws IOException {
		socket = new Socket("192.168.0.7", 30000);
		
		out = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());
		
		showMenu();
	}
	
	public void showMenu() {
		try {	
			System.out.println("-------------------------------------------------");
			System.out.println("1.목록조회 2.다운로드 3. 업로드 0.종료");
			System.out.println("-------------------------------------------------");
			
			System.out.print("### 메뉴선택: ");
			int menu = scanner.nextInt();
			
			// menu 의 값에 따라서 
			// h 에는 ClientFileListHandler 객체의 Handler 객체를 참조한다.
			// h 에는 ClientFileDownloadHandler 객체의 Handler 객체를 참조한다.
			// h 에는 ClientFileUploadHandler 객체의 Handler 객체를 참조한다.
			// h 에는 ClientFileExitHandler 객체의 Handler 객체를 참조한다.
			// h 의 타입을 Handler 타입으로 했기때문에 
			// h 에는 다양한 Handler 구현객체의 Handler 를 참조할 수 있고,
			// menu 의 값에 따라서 즉시, 즉시 적절한 ClientXXXHandler 객체가 연결된다.
			Handler h = map.get(menu);
			// 다형성이 발현되는 지점이다.
			// h.handle(in, out)이라는 동일한 방식으로 실행하지만
			// h 가 실제로 참조하는 객체가 어떤 객체인가에 따라서
			// 실행결과가 다르게 발현된다.
			h.handle(in, out);
			
			System.out.println();
			System.out.println();
			System.out.println();
			showMenu();
		} catch (IOException ex) {
			System.out.println("### 오류: " + ex.getMessage());
		}
	
	}
	
	public static void main(String[]args) throws IOException {
		Client client = new Client();
		client.startup();
	}

}
