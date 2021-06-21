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

	// import�ʿ� // ���� ���۸��� ���ؼ� ��ü ȭ�鿡 ���� �̹����� ��� �� �ν��Ͻ�
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

	
	// import �ʿ�
	// background �̹����� ��� ��ü
	private Image background = new ImageIcon(Main.class.getResource("../images/introbackground(title).jpg"))
			.getImage(); //getImage �޼ҵ带 ���ؼ� �̹���ȭ ���ش�

	// menuBar��� Jlabel ��ü ���� // import �ʿ�
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	
	// import �ʿ�
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);
	
	// ���α׷� �ȿ����� ���콺�� ��ǥ
	private int mouseX, mouseY;
	 
	private boolean isMainScreen = false;
	private boolean isGameScreen = false;
	
	// Array list�� ��� �������� ���� �� �ִ� �ϳ��� �̹� ������� �迭 // import�ʿ�
	ArrayList<Track> trackList = new ArrayList<Track>();
	
	private Image titleImage;
	private Image selectedImage;
	private Music selectedMusic;
	private Music introMusic = new Music("introMusic.mp3", true);
	
	// ArrayList�� �ε��� 0���� �����ϱ� ������ �ʱ⿡�� �翬�� 0���� �����
	private int nowSelected = 0;
	
	public static Game game; // ��Ű�� 12���� �ν��Ͻ� ������ �ƴ� �������������� �ٲ��ִ� ���� Ȯ�� // ������ ���� ������ ���� Ȯ��

	public SungHyun_Beat() {

		// 0��° �ε��� - �ڵ带 ���� �������� �÷��ִ� ���� : 
		// ���α׷� Ư���� �ε��� ������� ������ �߻��� �� �ֱ� ���� 
		// trackList�� add�� �̷������ ���� ���¿��� ��ư �̺�Ʈ�� �߻��ؼ� �ش� ���� ���ϵ��� ����ٸ� ������ �߻��ϱ� ����
		trackList.add(new Track("Happyrock Title Image.png", "Happyrock Start Image.jpg", 
				"Happyrock Game Image.jpg", "Happyrock Selected.mp3", "Happyrock.mp3", "Happy rock"));
		
		// 1��° �ε���
		trackList.add(new Track("Creativeminds Title Image.png", "Creativeminds Start Image.png", 
				"Creativeminds Game Image.jpg", "creativeminds Selected.mp3", "creativeminds.mp3", "creative minds"));
		
		// 2��° �ε���
		trackList.add(new Track("Suspense Title Image.png", "Suspense Start Image.png", 
				"Suspense Game Image.jpg", "suspense Selected.mp3", "suspense.mp3", "suspense"));
		
		setUndecorated(true); // �⺻������ �����ϴ� �޴��ٰ� ������ �ʰԵ�
		setTitle("SungHyun_Beat"); // ������ �̸�
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // ����â ũ��
		setResizable(false); // ����ڰ� ���������� ����â�� ���̰ų� �ø� �� ����
		setLocationRelativeTo(null); // ����â�� ���߾ӿ� ������
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ����â�� ���ԵǸ� ���α׷��� ����ǵ����� - �ݵ�� �ʿ�
		setVisible(true); // ����â�� ���̰� ���� (�⺻���� false�̱� ������ �ݵ�� ����� â�� ����)
		// import�ʿ�
		setBackground(new Color(0, 0, 0, 0)); // paintComponents�� ������ ����� ȸ���̾ƴ϶� ���� �Ͼ������ �ٲ�
		setLayout(null); // ��ư�̳� JLabel�� �־��� �� �ش� ��ġ��
		
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
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //import �ʿ� // ���콺 ������ �ո��
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ���콺 �������
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000); // ��ư �����ڸ��� ����Ǿ� ȿ������ �ȳ��� ������ ������ ������ �ɾ��ش�
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
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //import �ʿ� // ���콺 ������ �ո��
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ���콺 �������
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
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //import �ʿ� // ���콺 ������ �ո��
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ���콺 �������
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000); // ��ư �����ڸ��� ����Ǿ� ȿ������ �ȳ��� ������ ������ ������ �ɾ��ش�
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
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //import �ʿ� // ���콺 ������ �ո��
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ���콺 �������
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
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //import �ʿ� // ���콺 ������ �ո��
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ���콺 �������
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
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //import �ʿ� // ���콺 ������ �ո��
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ���콺 �������
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
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //import �ʿ� // ���콺 ������ �ո��
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ���콺 �������
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
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); //import �ʿ� // ���콺 ������ �ո��
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); // ���콺 �������
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonEnteredMusic = new Music("buttonPressedMusic.mp3", false);
				buttonEnteredMusic.start();
				backMain();
			}
		});
		
		add(backButton);
		
		menuBar.setBounds(0, 0, 1280, 30); // ��ġ�� ũ�⸦ ������
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) { //import �ʿ�
				mouseX = e.getX(); //���� �̺�Ʈ�� �߻����� �� X�� Y�� ��ǥ�� ����
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() { //  import�ʿ�
			@Override
			public void mouseDragged(MouseEvent e) { // �巡�� �̺�Ʈ�� �߻����� ��
				int x = e.getXOnScreen();			//  �� �������� X, Y�� �·Ḧ �����ͼ�
				int Y = e.getYOnScreen();
				setLocation(x - mouseX, Y - mouseY);  //�ڵ����� ���� JFrame �� ����â�� ��ġ�� �ٲ���
			}
		});
		add(menuBar); // JFrame�� menuBar�� �߰��� // �׷������� paintComponents�� ���� �׷���

	}

	public void paint(Graphics g) { // paint �Լ��� JFrame�� ��ӹ��� GUI���ӿ��� ���� ù��°�� ȭ���� �׷��ִ� �Լ� (��ӵȰŶ� �ٲ��� ����)
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw((Graphics2D) screenGraphic); //�ؽ�Ʈ�� �ε巴�� �ϱ� ���� Graphics2D�� ����ȯ // import
		g.drawImage(screenImage, 0, 0, null);
	}

	// drawImage�� �׸��°� �ϳ��� paintComponents�� �̿��켭 �׸��� ����ϳ��� 2���� ����� �ִµ�,
	// ������ �޴��� ���� �̹����� paintComponents�� ����
	// �Ϲ������� �̹�����, �������� �������� �����ִ� �̹����� Image�Լ��� �����
	public void screenDraw(Graphics2D g) { //Graphic2D ����ȯ ���� �Ű����� �̸��� ����
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
		paintComponents(g); // JLable�� JFrame�ȿ� �߰��ϸ� screenImage��� �����ȿ� �װ��� �׷��ִ°��� �ǹ���
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}
	
	public void selectTrack(int nowSelected) {
		if(selectedMusic != null)
			selectedMusic.close();														// ���� ���õ� Ÿ��Ʋ �̹��� ���� �����ͼ� �־���
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic. start();
	}
	
	public void selectLeft() {
		// ù��°(0) Ʈ���� �� ���� ��ư�� ������ ���� �����ʿ� �ִ� ���� ���õǰ� �� (0�϶� ����Ʈ ������� -1�� �� 3-2=2�� Ʈ��=������Ʈ��)
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
		// game �κ� �Ű������� �����ϱ� ��ƴ� ��
		game.start(); //game Ŭ������ run �Լ� ����
		requestFocus(); // setFocusable(true) �� requestFocus()�� �������� �����ؾ��Ѵ� ����..
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
	
	//�����丵 // �Լ�ȭ // ������ ū ����� ������ ������ �׸��� ��ư�� �����Ƿ� ������
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
