import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Adventure extends JFrame {

	private Point mouseClick;

	// 패널 목록
	private JPanel Main;
	private JPanel newAd;
	private JPanel gameAd;
	private JPanel Setting;

	// 버튼 목록
	private JButton newButton;
	private JButton loadButton;
	private JButton settButton;
	private JButton backButton;
	private JButton soundButton;
	private JButton null1;
	private JButton null2;

	// 스트링 목록
	private String IdField;

	public Adventure(String idField) {

		IdField = idField;

		// 아이디 값 가지고 왔는지 확인 
		System.out.println(IdField);
		
		// 프레임 크기 지정
		setSize(1280, 720);

		// 프레임 크기 변경 비활성화 및 타이틀바 제거
		setResizable(false);
		setUndecorated(true);
		this.setVisible(true);

		// 프레임 종료 시, 데이터 삭제
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 프레임 가운데 배치
		setLocationRelativeTo(null);
		setLayout(null);

		this.addMouseListener(new PositionListener());
		this.addMouseMotionListener(new PositionListener());

		// 메인 패널 설정
		Main = new JPanel() {
			public void paintComponent(Graphics g) {
				Image background = new ImageIcon(Adventure.class.getResource("/Images/Game.png")).getImage();
				g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};

		// 메인 패널 보이게 하기
		setContentPane(Main);
		Main.setLayout(null);

		// 버튼 설정 
		newButton = new JButton("새로운 모험?");
		newButton.setBounds(542, 279, 215, 62);
		newButton.setFont(new Font("휴먼매직체", Font.PLAIN, 20));

		loadButton = new JButton("모험을 이어서 할까?");
		loadButton.setBounds(542, 400, 215, 62);
		loadButton.setFont(new Font("휴먼매직체", Font.PLAIN, 20));

		settButton = new JButton("환 경 설 정");
		settButton.setBounds(542, 521, 215, 62);
		settButton.setFont(new Font("휴먼매직체", Font.PLAIN, 20));

		backButton = new JButton("뒤 로 가 기");
		backButton.setBounds(10, 521, 215, 62);
		backButton.setFont(new Font("휴먼매직체", Font.PLAIN, 22));

		Main.add(newButton);
		Main.add(loadButton);
		Main.add(settButton);
		Main.add(backButton);
		
		Main.setVisible(true);

		// 새로 하기 버튼 누르면 게임이 새로 시작됨(진짜 게임)
		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.setVisible(false);
				gameAd.setVisible(true);
				setContentPane(gameAd);

			}
		});

		// 이어서 하기 버튼을 누르면 게임이 이어서 실행(디비에 저장된 위치값 기억)
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.setVisible(false);
				gameAd.setVisible(true);
				setContentPane(gameAd);

			}
		});

		// 환경설정 창으로 이동
		settButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.setVisible(false);
				Setting.setVisible(true);
				setContentPane(Setting);
			}
		});

		// 뒤로가기 (로그인 프레임)
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setVisible(true);
			}
		});

		// 게임 인트로 패널
		gameAd = new JPanel() {
			public void paintComponent(Graphics g) {
				Image background = new ImageIcon(Adventure.class.getResource("/Images/GameAd.png")).getImage();
				g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};

		// 환경설정 패널
		Setting = new JPanel() {
			public void paintComponent(Graphics g) {
				Image background = new ImageIcon(Adventure.class.getResource("/Images/Setting.png")).getImage();
				g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		
		Setting.setLayout(null);

		// 버튼 설정
		soundButton = new JButton("소리 ON/OFF");
		soundButton.setBounds(542, 248, 215, 62);
		soundButton.setFont(new Font("휴먼매직체", Font.PLAIN, 20));

		null1 = new JButton("null");
		null1.setBounds(542, 339, 215, 62);
		null1.setFont(new Font("휴먼매직체", Font.PLAIN, 20));

		null2 = new JButton("null");
		null2.setBounds(542, 430, 215, 62);
		null2.setFont(new Font("휴먼매직체", Font.PLAIN, 20));

		backButton = new JButton("뒤 로 가 기");
		backButton.setBounds(542, 521, 215, 62);
		backButton.setFont(new Font("휴먼매직체", Font.PLAIN, 22));

		Setting.add(soundButton);
		Setting.add(null1);
		Setting.add(null2);
		Setting.add(backButton);

		// 뒤로 가기 버튼을 눌렀을 때 메인으로 이동
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Setting.setVisible(false);
				Main.setVisible(true);
				setContentPane(Main);

			}
		});
	}

	class PositionListener extends MouseAdapter {
		// 인트로에서 로그인으로 넘어가기 위해 최초 1회 클릭
		public void mouseClicked(MouseEvent e) {

		}

		public void mousePressed(MouseEvent e) {
			mouseClick = e.getPoint();
			getComponentAt(mouseClick);
		}

		// 프레임 이동
		public void mouseDragged(MouseEvent e) {
			JFrame mainViewFrame = (JFrame) e.getSource();

			int FrameX = mainViewFrame.getLocation().x;
			int FrameY = mainViewFrame.getLocation().y;

			int MoveX = e.getX() - mouseClick.x;
			int MoveY = e.getY() - mouseClick.y;

			int FinalX = FrameX + MoveX;
			int FinalY = FrameY + MoveY;

			mainViewFrame.setLocation(FinalX, FinalY);
		}
	}
}
