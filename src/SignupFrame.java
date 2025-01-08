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
	
	// 패널 목록
	private JPanel Signup;
	
	// 버튼 목록
	private JButton signButton;
	private JButton backButton;
	
	// 텍스트필드 목록
	private JTextField idField;
	
	// 비밀번호 필드 목록
	private JPasswordField pwField;
	
	// 라벨 목록
	private JLabel aboutI;
	private JLabel idLabel;
	private JLabel pwLabel;
	
	public SignupFrame() {
		// 프레임 크기 지정
		setSize(1280, 720);

		// 프레임 크기 변경 비활성화 및 타이틀바 제거
		setResizable(false);
		setUndecorated(true);
		setVisible(true);

		this.addMouseListener(new PositionListener());
		this.addMouseMotionListener(new PositionListener());

		// 프레임 종료 시, 데이터 삭제
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 프레임 가운데 배치
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
		backButton = new JButton("뒤 로 가 기");
		backButton.setBounds(30, 635, 215, 62);
		backButton.setFont(new Font("휴먼매직체", Font.PLAIN, 22));
		
		idField = new JTextField();
		idField.setBounds(920, 400, 330, 62);
		idField.setFont(new Font("휴먼매직체", Font.PLAIN, 18));
		
		idLabel = new JLabel("<html>I<br>D</html>");
		idLabel.setBounds(900, 400, 50, 50);
		idLabel.setFont(new Font("휴먼매직체", Font.PLAIN, 20));
		
		pwField = new JPasswordField();
		pwField.setBounds(920, 510, 330, 62);
		pwField.setFont(new Font("휴먼매직체", Font.PLAIN, 18));
		
		pwLabel = new JLabel("<html>P<br>W</html>");
		pwLabel.setBounds(900, 510, 50, 50);
		pwLabel.setFont(new Font("휴먼매직체", Font.PLAIN, 20));

		aboutI = new JLabel("아이디는 닉네임과 동일해요!");
		aboutI.setBounds(905, 290, 400, 100);
		aboutI.setFont(new Font("휴먼매직체", Font.PLAIN, 30));
		
		signButton = new JButton("Sign Up?");
		signButton.setBounds(900, 635, 350, 62);
		signButton.setFont(new Font("휴먼매직체", Font.BOLD, 18));
		
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
				
				// 드라이버적재
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException t) {
					// TODO Auto-generated catch block
					t.printStackTrace();
				}
				System.out.println("드라이버 로딩 성공");
				// DB연결
				try {
					Connection con = DriverManager.getConnection(url, id, pw);
					System.out.println("DB 연결 성공~");

					Statement stmt = con.createStatement();

					String sql = "select * from users";
					ResultSet rs = stmt.executeQuery(sql);
					boolean isUser = false;
					while(rs.next()) {
						if(idField.getText().equals(rs.getString("u_id"))) {
							JOptionPane.showMessageDialog(null, "이미 있는 아이디 입니다.");
							isUser=true;
						}
					}
					if(!isUser) {
						sql = "insert into users(u_id, u_pw) values('" + idField.getText() + "','" + pwField.getText() + "')";
						int result = stmt.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "등록 완료");
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
