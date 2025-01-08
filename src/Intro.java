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
	
	// �г� ����
	private JPanel Intro;
	
	// �� ����
	private JLabel introJLabel;

	// Ŭ������ �� �Է��� �Ǵ� ��(?)
	private boolean IntroPassive = false;
	
	// ��Ʈ�� ������ ����
	public Intro() {
		// ������ ũ�� ����
		setSize(1280, 720);

		// ������ ũ�� ���� ��Ȱ��ȭ �� Ÿ��Ʋ�� ����
		setResizable(false);
		setUndecorated(true);

		// ������ ���� ��, ������ ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ������ ��� ��ġ
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);
		
		// ������ �̵� ���콺 ������
		this.addMouseListener(new PositionListener());
		this.addMouseMotionListener(new PositionListener());
		
		// ��Ʈ�� �г� ����
		Intro = new JPanel() {
			public void paintComponent(Graphics g) {
				Image background = new ImageIcon(Intro.class.getResource("/Images/Outline.png")).getImage();
				g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};

		// �г� ���̰� �ϱ�
		Intro.setVisible(true);
		Intro.setLayout(null);
		setContentPane(Intro);

		// �� ���� 
		introJLabel = new JLabel("Press to GameStart");
		introJLabel.setBounds(400, 610, 600, 100);
		introJLabel.setFont(new Font("�޸ո���ü", Font.BOLD, 50));

		// �� �߰�
		Intro.add(introJLabel);
		this.setVisible(true);
		
	}

	class PositionListener extends MouseAdapter {
		// ��Ʈ�ο��� �α������� �Ѿ�� ���� ���� 1ȸ Ŭ��
		public void mouseClicked(MouseEvent e) {
			if (!IntroPassive) {
				dispose();
				 LoginFrame loginFrame = new LoginFrame();
				 loginFrame.setVisible(true);
			}
		}

		// ������ �̵��� ���� ó�� Ŭ���� ���콺 ����Ʈ ��ġ ���
		public void mousePressed(MouseEvent e) {
			mouseClick = e.getPoint();
			getComponentAt(mouseClick);
			
		}

		// ������ �̵�
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


