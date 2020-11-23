package P3.go;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import P3.base.Action;
import P3.base.Game;
import P3.base.Piece;
import P3.base.Player;
import P3.base.Position;

/**
 * {@link GUIGo} is the GUI client of Go.
 */
public class GUIGo extends JFrame {
	private static final long serialVersionUID = 1L;

	private Game game;
	private JButton skipButton;
	private JButton[][] pieceButtons;
	private String player1, player2, currentPlayer;
	private List<Action> actions;
	private List<String> players;
	private Icon blank = new ImageIcon("resource/P3/go/blankcross.jpg");
	private Icon black = new ImageIcon("resource/P3/go/blackcross.jpg");
	private Icon white = new ImageIcon("resource/P3/go/whitecross.jpg");

	public GUIGo(String player1, String player2) {
		game = new GoGame(player1, player2);
		game.initNewGame();
		this.player1 = player1;
		this.player2 = player2;
		actions = new LinkedList<>();
		players = new LinkedList<>();

		initUI();
		initActions();
	}

	private void initUI() {
		currentPlayer = player1;
		setTitle(currentPlayer + "'s Turn");
		setLayout(new GridLayout(GoGame.ROW + 1, GoGame.COLUMN));
		pieceButtons = new JButton[GoGame.ROW][GoGame.COLUMN];
		skipButton = new JButton("SKIP");

		for (int i = 0; i < GoGame.ROW; ++i)
			for (int j = 0; j < GoGame.COLUMN; ++j) {
				pieceButtons[i][j] = new JButton();
				pieceButtons[i][j].setOpaque(true);
				pieceButtons[i][j].setBorderPainted(false);
				pieceButtons[i][j].setIcon(blank);
				add(pieceButtons[i][j]);
			}
		add(skipButton);
	}

	private void initActions() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		skipButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changePlayer();
			}
		});
		for (int i = 0; i < GoGame.ROW; ++i)
			for (int j = 0; j < GoGame.COLUMN; ++j)
				pieceButtons[i][j].addActionListener(new PieceActionListener(j, i));
	}

	private void changePlayer() {
		currentPlayer = currentPlayer == player1 ? player2 : player1;
		setTitle(currentPlayer + "'s Turn");
	}

	private class PieceActionListener implements ActionListener {
		private final int x, y;

		public PieceActionListener(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String positionPlayer = game.getPlayerOfPosition(Position.valueOf(x, GoGame.ROW - 1 - y));
			if (positionPlayer == null) {
				players.add(currentPlayer);
				actions.add(new PutAction(Position.valueOf(x, GoGame.ROW - 1 - y)));
				pieceButtons[y][x].setIcon(currentPlayer.equals(player1) ? black : white);
				changePlayer();
			} else {
				players.add(positionPlayer);
				actions.add(new RemoveAction(Position.valueOf(x, GoGame.ROW - 1 - y)));
				pieceButtons[y][x].setIcon(blank);
			}
			game.addActions(players, actions);
			players.clear();
			actions.clear();
			game.printBoard();
		}
	}
}
