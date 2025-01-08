import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Intro extends JFrame {
	
	private Point mouseClick;
	
	// 패널 설정
	private JPanel Intro;
	
	// 라벨 설정
	private JLabel introJLabel;

	// 클릭했을 때 입력이 되는 것(?)
	private boolean IntroPassive = false;
	
	// 인트로 프레임 설정
	public Intro() {
		// 프레임 크기 지정
		setSize(1280, 720);

		// 프레임 크기 변경 비활성화 및 타이틀바 제거
		setResizable(false);
		setUndecorated(true);

		// 프레임 종료 시, 데이터 삭제
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 프레임 가운데 배치
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);
		
		// 프레임 이동 마우스 리스너
		this.addMouseListener(new PositionListener());
		this.addMouseMotionListener(new PositionListener());
		
		// 인트로 패널 설정
		Intro = new JPanel() {
			public void paintComponent(Graphics g) {
				Image background = new ImageIcon(Intro.class.getResource("/Images/Outline.png")).getImage();
				g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};

		// 패널 보이게 하기
		Intro.setVisible(true);
		Intro.setLayout(null);
		setContentPane(Intro);

		// 라벨 설정 
		introJLabel = new JLabel("Press to GameStart");
		introJLabel.setBounds(400, 610, 600, 100);
		introJLabel.setFont(new Font("휴먼매직체", Font.BOLD, 50));

		// 라벨 추가
		Intro.add(introJLabel);
		this.setVisible(true);
		
	}

	class PositionListener extends MouseAdapter {
		// 인트로에서 로그인으로 넘어가기 위해 최초 1회 클릭
		public void mouseClicked(MouseEvent e) {
			if (!IntroPassive) {
				dispose();
				 LoginFrame loginFrame = new LoginFrame();
				 loginFrame.setVisible(true);
			}
		}

		// 프레임 이동을 위한 처음 클릭한 마우스 포인트 위치 기록
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


