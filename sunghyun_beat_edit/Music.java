package sunghyun_beat_edit;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {
	
	private Player player; // javazoom���� ������ �ٿ�ε��� ���̺귯�� �� �ϳ�
	private boolean isLoop; // ���� ���ѹݺ����� Ȯ��
	private File file;                                    
	private FileInputStream fis;                          
	private BufferedInputStream bis;                      
	
	public Music(String name, boolean isLoop) { // ���� ����� �ش���� ���ѹݺ����� ���θ� �Է¹޴´�
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
		// ��Ʈ ����߸��� �ʿ�
		public int getTime() { // ���� ����ǰ� �ִ� ������ ���� � ��ġ�� ����ǰ� �ִ��� �˷��� (10�� ������̸� 10000 ��ȯ)
			if (player == null)
				return 0;
			return player.getPosition();
		}
		
		public void close() { //������ ���� ����ǰ� �ִ��� �׻� ������ �� �ְ� ���ִ� �Լ�
			isLoop = false;
			player.close();
			this.interrupt(); // �ش� �����带 ������Ŵ - ���⼭�� ���� ���� �������ְ��ִ� �����带 ������Ŵ
		}
		
		@Override
		public void run() { //������ ��ӽ� ������ ����ؾ��ϴ� �Լ�
			try {
				do {
					player.play();
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					player = new Player(bis);
				} while (isLoop); // isLoop�� true��� �ش� ���� ���ѹݺ����� �����
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
