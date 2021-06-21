package sunghyun_beat_edit;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {
	
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	// 580은 노투 판정라인 까지의 좌표 // 나머지 공식은 생각을 좀해봐야 이해가 가능할 것 같음 // 580 - 700 = -120
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME; //노트가 1초뒤에 판정라인에 도달하게 끔 y좌료를 조정
	private String noteType;
	private boolean proceeded = true; // 현재노트의 진행여부에 대해서 체크
	
	public String getNoteType() {
		return noteType;
	}
	
	public boolean isProceeded() { // 현재 진행이 되고 있는지
		return proceeded;
	}
	
	public void close() { // 현재 노트가 움직이지 않게 해주는 함수 // 노트가 성공적으로 입력했을 때 노트가 더이상 이동하지 않도록
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
		this.noteType = noteType; //노트타입이 어떤냐에따라서 서로 다른 그림을 그림
	}
	
	public void screenDraw(Graphics2D g) {
		if(!noteType.equals("Space")) // 스페이스가 아닌경우
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
				drop(); // 7픽셀 만큼 떨어짐
				if(proceeded) { // 현재 진행중이라면 정상적으로 슬립되면서 내려오게 됨
					Thread.sleep(Main.SLEEP_TIME);
				}
				else { // 노트를 입력했거나 해서 해당 노트에대한 작업처리가 끝났을 경우에는 proceeded 가 false 로 바뀌고 쓰레드가 정지하도록 인터럽트를 걸어줌
					interrupt();
					break;
				}
				// Ver.15에서 삭제됨
				//Thread.sleep(Main.SLEEP_TIME); //0.01초 슬립 // 1초에 100번 실행 // 1초에 700픽셀 만큼 Y좌표가 아래로 내려감
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
	}

	public String judge() { // Ver.16 String으로 바꿔주고 return값 입력
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
