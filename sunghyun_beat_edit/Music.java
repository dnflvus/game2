package sunghyun_beat_edit;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {
	
	private Player player; // javazoom에서 별도로 다운로드한 라이브러리 중 하나
	private boolean isLoop; // 곡이 무한반복인지 확인
	private File file;                                    
	private FileInputStream fis;                          
	private BufferedInputStream bis;                      
	
	public Music(String name, boolean isLoop) { // 곡의 제목과 해당곡이 무한반복인지 여부를 입력받는다
		try {
			this.isLoop = isLoop;
			file  = new File(Main.class.getResource("../music/" + name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
		// 노트 떨어뜨릴때 필요
		public int getTime() { // 현재 실행되고 있는 음악이 현재 어떤 위치에 실행되고 있는지 알려줌 (10초 재생중이면 10000 반환)
			if (player == null)
				return 0;
			return player.getPosition();
		}
		
		public void close() { //음악이 언제 실행되고 있던지 항상 종료할 수 있게 해주는 함수
			isLoop = false;
			player.close();
			this.interrupt(); // 해당 쓰레드를 중지시킴 - 여기서는 따로 곡을 실행해주고있는 쓰레드를 중지시킴
		}
		
		@Override
		public void run() { //쓰레드 상속시 무조건 사용해야하는 함수
			try {
				do {
					player.play();
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					player = new Player(bis);
				} while (isLoop); // isLoop가 true라면 해당 곡은 무한반복으로 재생됨
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
