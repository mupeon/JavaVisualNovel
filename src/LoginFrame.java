import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame {

	private Point mouseClick;

	// �г� ���
	private JPanel Login;

	// ��ư ���
	private JButton signButton;
	private JButton loginButton;
	private JButton backButton;
	
	// �ؽ�Ʈ�ʵ� ���
	private JTextField idField;
	
	// ��й�ȣ�ʵ� ���
	private JPasswordField pwField;
	
	// �� ���
	private JLabel idLabel;
	private JLabel pwLabel;

	// �α��� ������ ����
	public LoginFrame() {
		// ������ ũ�� ����
		setSize(1280, 720);

		// ������ ũ�� ���� ��Ȱ��ȭ �� Ÿ��Ʋ�� ����
		setResizable(false);
		setUndecorated(true);
		setVisible(true);

		// ���콺 ������ ����
		this.addMouseListener(new PositionListener());
		this.addMouseMotionListener(new PositionListener());

		// ������ ���� ��, ������ ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ������ ��� ��ġ
		setLocationRelativeTo(null);
		setLayout(null);

		// �α��� �г� ����
		Login = new JPanel() {
			public void paintComponent(Graphics g) {
				Image background = new ImageIcon(LoginFrame.class.getResource("/Images/Login.png")).getImage();
				g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};

		// �α��� �г� â ����ֱ�
		setContentPane(Login);
		this.setVisible(true);
		Login.setLayout(null);

		// ��ư, �ʵ�, �� ��� && ����
		// setBounds(x, y, w, h); setSize(1280, 720);
		backButton = new JButton("�� �� �� ��");
		backButton.setBounds(30, 635, 215, 62);
		backButton.setFont(new Font("�޸ո���ü", Font.PLAIN, 22));
		
		idField = new JTextField();
		idField.setBounds(920, 310, 330, 62);
		idField.setFont(new Font("�޸ո���ü", Font.PLAIN, 18));
		
		idLabel = new JLabel("<html>I<br>D</html>");
		idLabel.setBounds(900, 310, 50, 50);
		idLabel.setFont(new Font("�޸ո���ü", Font.PLAIN, 20));

		pwField = new JPasswordField();
		pwField.setBounds(920, 410, 330, 62);
		pwField.setFont(new Font("�޸ո���ü", Font.PLAIN, 18));
		pwField.setEchoChar('*');
		
		pwLabel = new JLabel("<html>P<br>W</html>");
		pwLabel.setBounds(900, 410, 50, 50);
		pwLabel.setFont(new Font("�޸ո���ü", Font.PLAIN, 20));

		loginButton = new JButton("Login");
		loginButton.setBounds(900, 520, 350, 62);
		loginButton.setFont(new Font("�޸ո���ü", Font.BOLD, 18));

		signButton = new JButton("Sign Up!");
		signButton.setBounds(900, 635, 350, 62);
		signButton.setFont(new Font("�޸ո���ü", Font.BOLD, 18));

		// �гο� �߰�
		add(loginButton);
		add(idField);
		add(pwField);
		add(signButton);
		add(idLabel);
		add(pwLabel);
		add(backButton);

		// �α��� ��ư Ŭ���� �����ϱ� - ������ ���̽� ���
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// ���� �ּ�, ���̵�, ��й�ȣ 
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String id = "insa";
				String pw = "insa";
				String u_id = "" ;

				// ����̹�����
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException t) {
					// TODO Auto-generated catch block
					t.printStackTrace();
				}
				System.out.println("����̹� �ε� ����");
				// DB����
				try {
					Connection con = DriverManager.getConnection(url, id, pw);
					System.out.println("DB ���� ����");
					Statement stmt = con.createStatement();
					// users ���̺� ��ü ����
					String sql = "SELECT * FROM users";
					ResultSet rs = stmt.executeQuery(sql);
					boolean isUser = false;
					while (rs.next()) {
						// users ��Ͽ� �ִ� ����(id, pw) ��ü ���
						System.out.println(rs.getString("u_id") + " " + rs.getString("u_pw"));
						if (idField.getText().equals(rs.getString("u_id"))
								&& pwField.getText().equals(rs.getString("u_pw"))) {
							u_id = idField.getText();
							JOptionPane.showMessageDialog(null, "�α��� ����");
							setVisible(false);
							isUser = true;
						}
					}
					if (isUser) {
//						new Adventure(idField.getText());
						// ������ ���� 
						dispose();
						
						// �Է��� ���̵�(�г���) ���
						System.out.println(u_id);
						
						// ���̵�(�г���) �� ������ �̵�
						Adventure adventure = new Adventure(u_id);
//						adventure.setVisible(true);
					
					} else {
						JOptionPane.showMessageDialog(null, "�������� �ʴ� ����� �Դϴ�");
					}

				} catch (SQLException a) {
					// TODO Auto-generated catch block
					a.printStackTrace();
				}

				}
		});
		
		// ȸ������ ��ư ���� �� �α��� ������ ���� ȸ������ ���������� �̵�
		signButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				SignupFrame signupFrame = new SignupFrame();
				signupFrame.setVisible(true);
			}
		});
		
		// �ڷΰ���(��Ʈ��)
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Intro intro = new Intro();
				intro.setVisible(true);
			}
		});


	}

	class PositionListener extends MouseAdapter {

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