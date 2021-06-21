package sunghyun_beat_edit;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SungHyun_Beat extends JFrame {

	// import필요 // 더블 버퍼링을 위해서 젠체 화면에 대한 이미지를 담는 두 인스턴스
	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButtonBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));

	
	// import 필요
	// background 이미지를 담는 객체
	private Image background = new ImageIcon(Main.class.getResource("../images/introbackground(title).jpg"))
			.getImage(); //getImage 메소드를 통해서 이미지화 해준다

	// menuBar라는 Jlabel 객체 생성 // import 필요
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	// import 필요
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);
	
	// 프로그램 안에서의 마우스의 좌표
	private int mouseX, mouseY;
	 
	private boolean isMainScreen = false;
	private boolean isGameScreen = false;
	
	// Array list는 어떠한 변수들을 담을 수 있는 하나의 이미 만들어진 배열 // import필요
	ArrayList<Track> trackList = new ArrayList<Track>();
	
	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic;
	private Music introMusic = new Music("introMusic.mp3", true);
	
	// ArrayList가 인덱스 0부터 시작하기 때문에 초기에는 당연히 0으로 잡아줌
	private int nowSelected = 0;
	
	public static Game game; // 패키지 12에서 인스턴스 생성이 아닌 변수생성까지로 바꿔주는 이유 확인 // 오류가 떴던 이유도 같이 확인

	public SungHyun_Beat() {

		// 0번째 인덱스 - 코드를 가장 위쪽으로 올려주는 이유 : 
		// 프로그럼 특성상 로딩이 길어지면 오류가 발생할 수 있기 때문 
		// trackList에 add가 이루어지지 않은 상태에서 버튼 이벤트가 발생해서 해당 곡을 실하도록 만들다면 오류가 발생하기 때문
		trackList.add(new Track("Happyrock Title Image.png", "Happyrock Start Image.jpg", 
				"Happyrock Game Image.jpg", "Happyrock Selected.mp3", "Happyrock.mp3", "Happy rock"));
		
		// 1번째 인덱스
		trackList.add(new Track("Creativeminds Title Image.png", "Creativeminds Start Image.png", 
				"Creativeminds Game Image.jpg", "creativeminds Selected.mp3", "creativeminds.mp3", "creative minds"));
		
		// 2번째 인덱스
		trackList.add(new Track("Suspense Title Image.png", "Suspense Start Image.png", 
				"Suspense Game Image.jpg", "suspense Selected.mp3", "suspense.mp3", "suspense"));
		
		setUndecorated(true); // 기본적으로 존재하는 메뉴바가 보이지 않게됨
		setTitle("SungHyun_Beat"); // 게임의 이름
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 게임창 크기
		setResizable(false); // 사용자가 임의적으로 게임창을 줄이거나 늘릴 수 없음
		setLocationRelativeTo(null); // 게임창이 정중앙에 생성됨
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 게임창을 끄게되면 프로그램이 종료되도록함 - 반드시 필요
		setVisible(true); // 게임창을 보이게 해줌 (기본값은 false이기 때문에 반드시 해줘야 창이 보임)
		// import필요
		setBackground(new Color(0, 0, 0, 0)); // paintComponents를 했을때 배경이 회색이아니라 전부 하얀색으로 바뀜
		setLayout(null); // 버튼이나 JLabel을 넣었을 때 해당 위치에
		
		addKeyListener(new KeyListener());
		
		introMusic.start();

		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //import 필요 // 마우스 오버시 손모양
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000); // 버튼 누르자마자 종료되어 효과음이 안나기 때문에 스레드 슬립을 걸어준다
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		add(exitButton);
	
		startButton.setBounds(110, 250, 180, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //import 필요 // 마우스 오버시 손모양
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				introMusic.close();
				enterMain();
			}
		});
		
		add(startButton);
		
		quitButton.setBounds(110, 380, 180, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //import 필요 // 마우스 오버시 손모양
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000); // 버튼 누르자마자 종료되어 효과음이 안나기 때문에 스레드 슬립을 걸어준다
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		add(quitButton);
		
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //import 필요 // 마우스 오버시 손모양
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				selectLeft();
			}
		});
		
		add(leftButton);
		
		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //import 필요 // 마우스 오버시 손모양
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				selectRight();
			}
		});
		
		add(rightButton);
		
		easyButton.setVisible(false);
		easyButton.setBounds(375, 580, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //import 필요 // 마우스 오버시 손모양
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Easy");
			}
		});
		
		add(easyButton);
		
		hardButton.setVisible(false);
		hardButton.setBounds(655, 580, 250, 67);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //import 필요 // 마우스 오버시 손모양
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				gameStart(nowSelected, "Hard");
			}
		});
		
		add(hardButton);
		
		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //import 필요 // 마우스 오버시 손모양
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // 마우스 원래모양
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				backMain();
			}
		});
		
		add(backButton);
		
		menuBar.setBounds(0, 0, 1280, 30); // 위치와 크기를 정해줌
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) { //import 필요
				mouseX = e.getX(); //실제 이벤트가 발생했을 때 X와 Y의 좌표를 얻어옴
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() { //  import필요
			@Override
			public void mouseDragged(MouseEvent e) { // 드래그 이벤트가 발생했을 때
				int x = e.getXOnScreen();			//  그 순간마다 X, Y의 좌료를 가져와서
				int Y = e.getYOnScreen();
				setLocation(x - mouseX, Y - mouseY);  //자동으로 현재 JFrame 즉 게임창의 위치를 바꿔줌
			}
		});
		add(menuBar); // JFrame에 menuBar가 추가됨 // 그려질때는 paintComponents를 통해 그려짐

	}

	public void paint(Graphics g) { // paint 함수는 JFrame을 상속받은 GUI게임에서 가장 첫번째로 화면을 그려주는 함수 (약속된거라 바뀌지 않음)
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic); //텍스트를 부드럽게 하기 위해 Graphics2D로 형변환 // import
		g.drawImage(screenImage, 0, 0, null);
	}

	// drawImage로 그리는것 하나와 paintComponents을 이용헤서 그리는 방법하나로 2가지 방법이 있는데,
	// 고정된 메뉴바 등의 이미지는 paintComponents로 구현
	// 일반적으로 이미지나, 역동적인 움직임을 보여주는 이미지는 Image함수를 사용함
	public void screenDraw(Graphics2D g) { //Graphic2D 형변환 이유 매개변수 이름도 변경
		g.drawImage(background, 0, 0, null);
		if(isMainScreen)
		{
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		if(isGameScreen) 
		{
			game.screenDraw(g);
		}
		paintComponents(g); // JLable을 JFrame안에 추가하면 screenImage라는 변수안에 그것을 그려주는것을 의미함
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}
	
	public void selectTrack(int nowSelected) {
		if(selectedMusic != null)
			selectedMusic.close();														// 현재 선택된 타이틀 이미지 값을 가져와서 넣어줌
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic. start();
	}
	
	public void selectLeft() {
		// 첫번째(0) 트랙일 때 왼쪽 버튼을 누르면 가장 오른쪽에 있는 곡이 선택되게 함 (0일때 리스트 사이즈에서 -1함 즉 3-2=2번 트랙=마지막트랙)
		if(nowSelected == 0)
			nowSelected = trackList.size() -1;
		
		else
			nowSelected--;
		selectTrack(nowSelected);
	}
	
	public void selectRight() {
		if(nowSelected == trackList.size() - 1)
			nowSelected = 0;
		
		else
			nowSelected++;
		selectTrack(nowSelected);
	}
	
	public void gameStart(int nowSelected, String difficulty) {
		if(selectedMusic != null)
			selectedMusic.close();
		isMainScreen = false;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage())).getImage();
		backButton.setVisible(true);
		isGameScreen = true;
		game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic());
		// game 부분 매개변수를 이해하기 어렵다 ㅠ
		game.start(); //game 클래스의 run 함수 시작
		requestFocus(); // setFocusable(true) 와 requestFocus()의 차이점을 공부해야한다 ㅋㅋ..
	}
	
	public void backMain() {
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/Mainbackground.jpg"))
				.getImage();
		backButton.setVisible(false);
		selectTrack(nowSelected);
		isGameScreen = false;
		 game.close();
	}
	
	//리팩토링 // 함수화 // 순서가 큰 상관은 없으나 배경먼저 그리고 버튼이 생성되록 정리함
	public void enterMain() {
		startButton.setVisible(false);
		quitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/mainbackground.jpg")).getImage();
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		introMusic.close();
		selectTrack(0);
	}

}
