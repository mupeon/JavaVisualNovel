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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignupFrame extends JFrame {

	private Point mouseClick;
	
	// �г� ���
	private JPanel Signup;
	
	// ��ư ���
	private JButton signButton;
	private JButton backButton;
	
	// �ؽ�Ʈ�ʵ� ���
	private JTextField idField;
	
	// ��й�ȣ �ʵ� ���
	private JPasswordField pwField;
	
	// �� ���
	private JLabel aboutI;
	private JLabel idLabel;
	private JLabel pwLabel;
	
	public SignupFrame() {
		// ������ ũ�� ����
		setSize(1280, 720);

		// ������ ũ�� ���� ��Ȱ��ȭ �� Ÿ��Ʋ�� ����
		setResizable(false);
		setUndecorated(true);
		setVisible(true);

		this.addMouseListener(new PositionListener());
		this.addMouseMotionListener(new PositionListener());

		// ������ ���� ��, ������ ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ������ ��� ��ġ
		setLocationRelativeTo(null);
		setLayout(null);
		
		Signup = new JPanel() {
			public void paintComponent(Graphics g) {
				Image background = new ImageIcon(SignupFrame.class.getResource("/Images/Signup.png")).getImage();
				g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		
		Signup.setVisible(true);
		Signup.setLayout(null);
		setContentPane(Signup);
		
		// setBounds(x, y, w, h); setSize(1280, 720);
		backButton = new JButton("�� �� �� ��");
		backButton.setBounds(30, 635, 215, 62);
		backButton.setFont(new Font("�޸ո���ü", Font.PLAIN, 22));
		
		idField = new JTextField();
		idField.setBounds(920, 400, 330, 62);
		idField.setFont(new Font("�޸ո���ü", Font.PLAIN, 18));
		
		idLabel = new JLabel("<html>I<br>D</html>");
		idLabel.setBounds(900, 400, 50, 50);
		idLabel.setFont(new Font("�޸ո���ü", Font.PLAIN, 20));
		
		pwField = new JPasswordField();
		pwField.setBounds(920, 510, 330, 62);
		pwField.setFont(new Font("�޸ո���ü", Font.PLAIN, 18));
		
		pwLabel = new JLabel("<html>P<br>W</html>");
		pwLabel.setBounds(900, 510, 50, 50);
		pwLabel.setFont(new Font("�޸ո���ü", Font.PLAIN, 20));

		aboutI = new JLabel("���̵�� �г��Ӱ� �����ؿ�!");
		aboutI.setBounds(905, 290, 400, 100);
		aboutI.setFont(new Font("�޸ո���ü", Font.PLAIN, 30));
		
		signButton = new JButton("Sign Up?");
		signButton.setBounds(900, 635, 350, 62);
		signButton.setFont(new Font("�޸ո���ü", Font.BOLD, 18));
		
		Signup.add(idField);
		Signup.add(pwField);
		Signup.add(signButton);
		Signup.add(aboutI);
		Signup.add(idLabel);
		Signup.add(pwLabel);
		Signup.add(backButton);
		
		signButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String id = "insa";
				String pw = "insa";
				
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
					System.out.println("DB ���� ����~");

					Statement stmt = con.createStatement();

					String sql = "select * from users";
					ResultSet rs = stmt.executeQuery(sql);
					boolean isUser = false;
					while(rs.next()) {
						if(idField.getText().equals(rs.getString("u_id"))) {
							JOptionPane.showMessageDialog(null, "�̹� �ִ� ���̵� �Դϴ�.");
							isUser=true;
						}
					}
					if(!isUser) {
						sql = "insert into users(u_id, u_pw) values('" + idField.getText() + "','" + pwField.getText() + "')";
						int result = stmt.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "��� �Ϸ�");
						setVisible(false);
						new LoginFrame();
					}


				} catch (SQLException a) {
					// TODO Auto-generated catch block
					a.printStackTrace();
				}
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setVisible(true);
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
