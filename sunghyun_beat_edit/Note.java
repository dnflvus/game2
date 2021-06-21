package sunghyun_beat_edit;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {
	
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	// 580�� ���� �������� ������ ��ǥ // ������ ������ ������ ���غ��� ���ذ� ������ �� ���� // 580 - 700 = -120
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME; //��Ʈ�� 1�ʵڿ� �������ο� �����ϰ� �� y�·Ḧ ����
	private String noteType;
	private boolean proceeded = true; // �����Ʈ�� ���࿩�ο� ���ؼ� üũ
	
	public String getNoteType() {
		return noteType;
	}
	
	public boolean isProceeded() { // ���� ������ �ǰ� �ִ���
		return proceeded;
	}
	
	public void close() { // ���� ��Ʈ�� �������� �ʰ� ���ִ� �Լ� // ��Ʈ�� ���������� �Է����� �� ��Ʈ�� ���̻� �̵����� �ʵ���
		proceeded = false;
	}
	
	
	public Note(String noteType) {
		if(noteType.equals("S")) {
			x = 228;
		}
		else if(noteType.equals("D")) {
			x = 332;
		}
		else if(noteType.equals("F")) {
			x = 436;
		}
		else if(noteType.equals("Space")) {
			x = 540;
		}
		else if(noteType.equals("J")) {
			x = 744;
		}
		else if(noteType.equals("K")) {
			x = 848;
		}
		else if(noteType.equals("L")) {
			x = 952;
		}
		this.noteType = noteType; //��ƮŸ���� ��Ŀ����� ���� �ٸ� �׸��� �׸�
	}
	
	public void screenDraw(Graphics2D g) {
		if(!noteType.equals("Space")) // �����̽��� �ƴѰ��
		{
			g.drawImage(noteBasicImage, x, y, null);
		}
		else
		{
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null);
		}
	}
	
	public void drop() {
		y += Main.NOTE_SPEED;
		if(y > 620) {
			System.out.println("Miss");
			close();
		}
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				drop(); // 7�ȼ� ��ŭ ������
				if(proceeded) { // ���� �������̶�� ���������� �����Ǹ鼭 �������� ��
					Thread.sleep(Main.SLEEP_TIME);
				}
				else { // ��Ʈ�� �Է��߰ų� �ؼ� �ش� ��Ʈ������ �۾�ó���� ������ ��쿡�� proceeded �� false �� �ٲ�� �����尡 �����ϵ��� ���ͷ�Ʈ�� �ɾ���
					interrupt();
					break;
				}
				// Ver.15���� ������
				//Thread.sleep(Main.SLEEP_TIME); //0.01�� ���� // 1�ʿ� 100�� ���� // 1�ʿ� 700�ȼ� ��ŭ Y��ǥ�� �Ʒ��� ������
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
	}

	public String judge() { // Ver.16 String���� �ٲ��ְ� return�� �Է�
		if(y >= 613) {
			System.out.println("Late");
			close();
			return "Late";
		}
		else if(y >= 600) {
			System.out.println("Good");
			close();
			return "Good";
		}
		else if(y >= 587) {
			System.out.println("Great");
			close();
			return "Grate";
		}
		else if(y >= 573) {
			System.out.println("Perfect");
			close();
			return "Perfect";
		}
		else if(y >= 565) {
			System.out.println("Great");
			close();
			return "Great";
		}
		else if(y >= 550) {
			System.out.println("Good");
			close();
			return "Good";
		}
		else if(y >= 535) {
			System.out.println("Early");
			close();
			return "Early";
		}
		return "None";
	}	
	
	public int getY() {
		return y;
	}
}
