package P3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import P3.base.Game;
import P3.chess.GUIChess;
import P3.go.GUIGo;

/**
 * Class {@link MyChessAndGoGame} is the GUI client for {@link Game}
 *
 * @author cycleke
 */
public class MyChessAndGoGame extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPanel;
	private JTextField txtInputPlayer1Name;
	private JTextField txtInputPlayer2Name;

	public static void main(String[] args) {
		try {
			MyChessAndGoGame frame = new MyChessAndGoGame();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MyChessAndGoGame() {
		setTitle("Game Launcher");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 270, 300);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);

		txtInputPlayer1Name = new JTextField();
		txtInputPlayer1Name.setText("Input Player1 Name");
		txtInputPlayer1Name.setBounds(49, 19, 173, 26);
		contentPanel.add(txtInputPlayer1Name);
		txtInputPlayer1Name.setColumns(10);

		txtInputPlayer2Name = new JTextField();
		txtInputPlayer2Name.setText("Input Player2 Name");
		txtInputPlayer2Name.setBounds(49, 57, 173, 26);
		contentPanel.add(txtInputPlayer2Name);
		txtInputPlayer2Name.setColumns(10);

		JButton chessButton = new JButton("Chess Game");
		chessButton.setBounds(49, 134, 160, 60);
		chessButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIChess game = new GUIChess(txtInputPlayer1Name.getText(), txtInputPlayer2Name.getText());
				game.setLocation(200, 200);
				game.setSize(480, 540);
				game.setVisible(true);
			}
		});
		contentPanel.add(chessButton);

		JButton goButton = new JButton("Go Game");
		goButton.setBounds(49, 201, 160, 60);
		goButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIGo game = new GUIGo(txtInputPlayer1Name.getText(), txtInputPlayer2Name.getText());
				game.setLocation(200, 200);
				game.setSize(900, 900);
				game.setVisible(true);
			}
		});
		contentPanel.add(goButton);
	}
}
