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

	// 패널 목록
	private JPanel Login;

	// 버튼 목록
	private JButton signButton;
	private JButton loginButton;
	private JButton backButton;
	
	// 텍스트필드 목록
	private JTextField idField;
	
	// 비밀번호필드 목록
	private JPasswordField pwField;
	
	// 라벨 목록
	private JLabel idLabel;
	private JLabel pwLabel;

	// 로그인 프레임 설정
	public LoginFrame() {
		// 프레임 크기 지정
		setSize(1280, 720);

		// 프레임 크기 변경 비활성화 및 타이틀바 제거
		setResizable(false);
		setUndecorated(true);
		setVisible(true);

		// 마우스 포인터 지정
		this.addMouseListener(new PositionListener());
		this.addMouseMotionListener(new PositionListener());

		// 프레임 종료 시, 데이터 삭제
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 프레임 가운데 배치
		setLocationRelativeTo(null);
		setLayout(null);

		// 로그인 패널 설정
		Login = new JPanel() {
			public void paintComponent(Graphics g) {
				Image background = new ImageIcon(LoginFrame.class.getResource("/Images/Login.png")).getImage();
				g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};

		// 로그인 패널 창 띄워주기
		setContentPane(Login);
		this.setVisible(true);
		Login.setLayout(null);

		// 버튼, 필드, 라벨 출력 && 설정
		// setBounds(x, y, w, h); setSize(1280, 720);
		backButton = new JButton("뒤 로 가 기");
		backButton.setBounds(30, 635, 215, 62);
		backButton.setFont(new Font("휴먼매직체", Font.PLAIN, 22));
		
		idField = new JTextField();
		idField.setBounds(920, 310, 330, 62);
		idField.setFont(new Font("휴먼매직체", Font.PLAIN, 18));
		
		idLabel = new JLabel("<html>I<br>D</html>");
		idLabel.setBounds(900, 310, 50, 50);
		idLabel.setFont(new Font("휴먼매직체", Font.PLAIN, 20));

		pwField = new JPasswordField();
		pwField.setBounds(920, 410, 330, 62);
		pwField.setFont(new Font("휴먼매직체", Font.PLAIN, 18));
		pwField.setEchoChar('*');
		
		pwLabel = new JLabel("<html>P<br>W</html>");
		pwLabel.setBounds(900, 410, 50, 50);
		pwLabel.setFont(new Font("휴먼매직체", Font.PLAIN, 20));

		loginButton = new JButton("Login");
		loginButton.setBounds(900, 520, 350, 62);
		loginButton.setFont(new Font("휴먼매직체", Font.BOLD, 18));

		signButton = new JButton("Sign Up!");
		signButton.setBounds(900, 635, 350, 62);
		signButton.setFont(new Font("휴먼매직체", Font.BOLD, 18));

		// 패널에 추가
		add(loginButton);
		add(idField);
		add(pwField);
		add(signButton);
		add(idLabel);
		add(pwLabel);
		add(backButton);

		// 로그인 버튼 클릭시 구분하기 - 데이터 베이스 사용
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// 접속 주소, 아이디, 비밀번호 
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String id = "insa";
				String pw = "insa";
				String u_id = "" ;

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
					System.out.println("DB 연결 성공");
					Statement stmt = con.createStatement();
					// users 테이블 전체 선택
					String sql = "SELECT * FROM users";
					ResultSet rs = stmt.executeQuery(sql);
					boolean isUser = false;
					while (rs.next()) {
						// users 목록에 있는 계정(id, pw) 전체 출력
						System.out.println(rs.getString("u_id") + " " + rs.getString("u_pw"));
						if (idField.getText().equals(rs.getString("u_id"))
								&& pwField.getText().equals(rs.getString("u_pw"))) {
							u_id = idField.getText();
							JOptionPane.showMessageDialog(null, "로그인 성공");
							setVisible(false);
							isUser = true;
						}
					}
					if (isUser) {
//						new Adventure(idField.getText());
						// 프레임 끄기 
						dispose();
						
						// 입력한 아이디(닉네임) 출력
						System.out.println(u_id);
						
						// 아이디(닉네임) 값 가지고 이동
						Adventure adventure = new Adventure(u_id);
//						adventure.setVisible(true);
					
					} else {
						JOptionPane.showMessageDialog(null, "존재하지 않는 사용자 입니다");
					}

				} catch (SQLException a) {
					// TODO Auto-generated catch block
					a.printStackTrace();
				}

				}
		});
		
		// 회원가입 버튼 누를 시 로그인 프레임 끄고 회원가입 프레임으로 이동
		signButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				SignupFrame signupFrame = new SignupFrame();
				signupFrame.setVisible(true);
			}
		});
		
		// 뒤로가기(인트로)
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