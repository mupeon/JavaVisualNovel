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

	// �г� ���
	private JPanel Main;
	private JPanel newAd;
	private JPanel gameAd;
	private JPanel Setting;

	// ��ư ���
	private JButton newButton;
	private JButton loadButton;
	private JButton settButton;
	private JButton backButton;
	private JButton soundButton;
	private JButton null1;
	private JButton null2;

	// ��Ʈ�� ���
	private String IdField;

	public Adventure(String idField) {

		IdField = idField;

		// ���̵� �� ������ �Դ��� Ȯ�� 
		System.out.println(IdField);
		
		// ������ ũ�� ����
		setSize(1280, 720);

		// ������ ũ�� ���� ��Ȱ��ȭ �� Ÿ��Ʋ�� ����
		setResizable(false);
		setUndecorated(true);
		this.setVisible(true);

		// ������ ���� ��, ������ ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ������ ��� ��ġ
		setLocationRelativeTo(null);
		setLayout(null);

		this.addMouseListener(new PositionListener());
		this.addMouseMotionListener(new PositionListener());

		// ���� �г� ����
		Main = new JPanel() {
			public void paintComponent(Graphics g) {
				Image background = new ImageIcon(Adventure.class.getResource("/Images/Game.png")).getImage();
				g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};

		// ���� �г� ���̰� �ϱ�
		setContentPane(Main);
		Main.setLayout(null);

		// ��ư ���� 
		newButton = new JButton("���ο� ����?");
		newButton.setBounds(542, 279, 215, 62);
		newButton.setFont(new Font("�޸ո���ü", Font.PLAIN, 20));

		loadButton = new JButton("������ �̾ �ұ�?");
		loadButton.setBounds(542, 400, 215, 62);
		loadButton.setFont(new Font("�޸ո���ü", Font.PLAIN, 20));

		settButton = new JButton("ȯ �� �� ��");
		settButton.setBounds(542, 521, 215, 62);
		settButton.setFont(new Font("�޸ո���ü", Font.PLAIN, 20));

		backButton = new JButton("�� �� �� ��");
		backButton.setBounds(10, 521, 215, 62);
		backButton.setFont(new Font("�޸ո���ü", Font.PLAIN, 22));

		Main.add(newButton);
		Main.add(loadButton);
		Main.add(settButton);
		Main.add(backButton);
		
		Main.setVisible(true);

		// ���� �ϱ� ��ư ������ ������ ���� ���۵�(��¥ ����)
		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.setVisible(false);
				gameAd.setVisible(true);
				setContentPane(gameAd);

			}
		});

		// �̾ �ϱ� ��ư�� ������ ������ �̾ ����(��� ����� ��ġ�� ���)
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.setVisible(false);
				gameAd.setVisible(true);
				setContentPane(gameAd);

			}
		});

		// ȯ�漳�� â���� �̵�
		settButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.setVisible(false);
				Setting.setVisible(true);
				setContentPane(Setting);
			}
		});

		// �ڷΰ��� (�α��� ������)
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setVisible(true);
			}
		});

		// ���� ��Ʈ�� �г�
		gameAd = new JPanel() {
			public void paintComponent(Graphics g) {
				Image background = new ImageIcon(Adventure.class.getResource("/Images/GameAd.png")).getImage();
				g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};

		// ȯ�漳�� �г�
		Setting = new JPanel() {
			public void paintComponent(Graphics g) {
				Image background = new ImageIcon(Adventure.class.getResource("/Images/Setting.png")).getImage();
				g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		
		Setting.setLayout(null);

		// ��ư ����
		soundButton = new JButton("�Ҹ� ON/OFF");
		soundButton.setBounds(542, 248, 215, 62);
		soundButton.setFont(new Font("�޸ո���ü", Font.PLAIN, 20));

		null1 = new JButton("null");
		null1.setBounds(542, 339, 215, 62);
		null1.setFont(new Font("�޸ո���ü", Font.PLAIN, 20));

		null2 = new JButton("null");
		null2.setBounds(542, 430, 215, 62);
		null2.setFont(new Font("�޸ո���ü", Font.PLAIN, 20));

		backButton = new JButton("�� �� �� ��");
		backButton.setBounds(542, 521, 215, 62);
		backButton.setFont(new Font("�޸ո���ü", Font.PLAIN, 22));

		Setting.add(soundButton);
		Setting.add(null1);
		Setting.add(null2);
		Setting.add(backButton);

		// �ڷ� ���� ��ư�� ������ �� �������� �̵�
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
		// ��Ʈ�ο��� �α������� �Ѿ�� ���� ���� 1ȸ Ŭ��
		public void mouseClicked(MouseEvent e) {

		}

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
