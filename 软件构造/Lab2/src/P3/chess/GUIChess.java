package P3.chess;

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
 * {@link GUIChess} is the GUI client of Chess.
 */
public class GUIChess extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final Color dark_bg = new Color(0xd1, 0x8b, 0x47);
	private static final Color light_bg = new Color(0xff, 0xce, 0x9e);

	private Game game;
	private JButton skipButton;
	private JButton[][] pieceButtons;
	private String player1, player2, currentPlayer;
	private Icon previousIcon;
	private Position previousPosition;
	private List<Action> actions;
	private List<String> players;

	public GUIChess(String player1, String player2) {
		game = new ChessGame(player1, player2);
		game.initNewGame();
		this.player1 = player1;
		this.player2 = player2;
		previousIcon = null;
		previousPosition = null;
		actions = new LinkedList<>();
		players = new LinkedList<>();

		initUI();
		initActions();
	}

	private void initUI() {
		currentPlayer = player1;
		setTitle(currentPlayer + "'s Turn");
		setLayout(new GridLayout(ChessGame.ROW + 1, ChessGame.COLUMN));
		pieceButtons = new JButton[ChessGame.ROW][ChessGame.COLUMN];
		skipButton = new JButton("SKIP");

		for (int i = 0; i < ChessGame.ROW; ++i)
			for (int j = 0; j < ChessGame.COLUMN; ++j) {
				pieceButtons[i][j] = new JButton();
				pieceButtons[i][j].setOpaque(true);
				pieceButtons[i][j].setBorderPainted(false);
				pieceButtons[i][j].setBackground((i + j) % 2 == 1 ? dark_bg : light_bg);
				add(pieceButtons[i][j]);
			}

		List<Player> list = game.getGameState();
		for (Piece piece : list.get(0).pieces()) {
			int x = piece.getPosition().getX();
			int y = piece.getPosition().getY();
			pieceButtons[7 - y][x].setIcon(new ImageIcon(String.format("resource/P3/chess/player1/%s.png", piece.getName())));
		}
		for (Piece piece : list.get(1).pieces()) {
			int x = piece.getPosition().getX();
			int y = piece.getPosition().getY();
			pieceButtons[7 - y][x].setIcon(new ImageIcon(String.format("resource/P3/chess/player2/%s.png", piece.getName())));
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
		for (int i = 0; i < ChessGame.ROW; ++i) {
			for (int j = 0; j < ChessGame.COLUMN; ++j) {
				pieceButtons[i][j].addActionListener(new PieceActionListener(j, i));
			}
		}
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
			if (previousPosition == null) {
				if (game.getStateOfPosition(Position.valueOf(x, ChessGame.ROW - 1 - y)) == null) {
					// 点击到空白
					return;
				}
				if (game.getPlayerOfPosition(Position.valueOf(x, ChessGame.ROW - 1 - y)) != currentPlayer) {
					// 非自己的棋子
					return;
				}
				previousPosition = Position.valueOf(x, ChessGame.ROW - 1 - y);
				previousIcon = pieceButtons[y][x].getIcon();
				pieceButtons[y][x].setIcon(null);
				game.printBoard();
			} else {
				if (previousPosition.getX() == x && ChessGame.ROW - 1 - previousPosition.getY() == y) {
					// 双击取消
					pieceButtons[y][x].setIcon(previousIcon);
					previousPosition = null;
					game.printBoard();
					return;
				}
				Piece currentPiece = game.getStateOfPosition(Position.valueOf(x, ChessGame.ROW - 1 - y));
				if (currentPiece != null) {
					players.add(game.getPlayerOfPosition(Position.valueOf(x, ChessGame.ROW - 1 - y)));
					actions.add(new RemoveAction(Position.valueOf(x, ChessGame.ROW - 1 - y)));
				}
				players.add(game.getPlayerOfPosition(previousPosition));
				actions.add(new MoveAction(previousPosition, Position.valueOf(x, ChessGame.ROW - 1 - y)));

				game.addActions(players, actions);
				players.clear();
				actions.clear();

				pieceButtons[y][x].setIcon(previousIcon);
				previousPosition = null;

				changePlayer();
				game.printBoard();
			}
		}
	}
}
