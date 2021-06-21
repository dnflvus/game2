package sunghyun_beat_edit;

public class Main {
	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED = 3;
	public static final int SLEEP_TIME = 10; // 슬립타임이 없으면 노트속도가 아무리 느리더라도 육안으로는 너무 빠른 속도로 보임
	public static final int REACH_TIME = 2;  // 노트가 생성되고 나서 판정바에 도달하기까지 걸리는 시간
	public static String Score = "0";
	public static int sc = 0;

	public static void main(String[] args) {

		new SungHyun_Beat();
	}
}
