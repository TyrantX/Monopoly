package com.ooad.view;

import com.ooad.controller.GameController;
import com.ooad.controller.Monopoly;
import com.ooad.model.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @param: none
 * @description:
 * @author: KingJ
 * @create: 2018-12-18 19:29
 **/
public class GamePanel extends JPanel {

    private GameFrame gameFrame = null;
    private JLayeredPane layeredPane;

    //游戏背景
    private List<Layer> lays = null;
    private BoardView boardView = null;
    private PieceView pieceView = null;
    private DiceView diceView = null;
    private PlayerView playerView = null;
    private BuildingsView buildingsView = null;
    private TextTipView textTipView = null;
    private RunningView running = null;
    private PlayersPanel playersPanel = null;
    private PlayerInfo playerInfo = null;

    private Monopoly game;
    private GameController run;

    /**
     * 全局左上角X
     */
    public int posX = 440;
    /**
     * 全局左上角Y
     * */
    public int posY = 90;


    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public RunningView getRunning() {
        return running;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public Monopoly getGame() {
        return game;
    }

    public GamePanel(){
        //采用BorderLayout布局
        setLayout(new BorderLayout());
        initGame();
    }

    public void initGame(){
        game = new Monopoly();
        initUI();
        game.setPanel(this);
    }

    public void initUI(){
        // 实例化背景
        this.boardView = new BoardView(0, 0, 1280, 853,
                game.getBoard(),this);
        // 创建土地UI
        this.pieceView = new PieceView(posX, posY, 1280, 853, game.getPiece());
        // 创建房屋UI
        this.buildingsView = new BuildingsView(posX, posY, 1280, 853,
                game.getBuildings());
        // 创建玩家显示UI
        this.playerView = new PlayerView(posX, posY, 1280, 853, game, game.getPlayers());
        // 文字显示面板UI
        this.textTipView = new TextTipView(0,0,1280,853, game.getTextTip());
        // 骰子事件UI
        this.diceView = new DiceView(posX + 80, posY + 200, 250, 160, game);
        // 游戏运转界面UI
        this.running = new RunningView(780, 0, 200, 80, game,this);
        // 玩家信息面板UI
        this.playersPanel = new PlayersPanel(posX + 80, posY + 66, 250,
                130, game.getPlayers());
        // 玩家信息面板显示
        this.playerInfo = new PlayerInfo(game.getPlayers(),this);

        // lays存放所有panel组件
        lays = new ArrayList<Layer>();
        lays.add(boardView);
        lays.add(diceView);
        lays.add(playerView);
        lays.add(buildingsView);
        lays.add(pieceView);
        lays.add(running);
        lays.add(playersPanel);

        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        int add = 1;
        layeredPane.add(this.textTipView, add++);
        layeredPane.add(this.diceView, add++);
        layeredPane.add(this.playerView, add++);
        layeredPane.add(this.buildingsView, add++);
        layeredPane.add(this.pieceView, add++);
        layeredPane.add(this.running, add++);
        layeredPane.add(this.playersPanel, add++);
        layeredPane.add(this.boardView, add++);
        layeredPane.add(this.playerInfo, add++);

        add(layeredPane);
    }

    /**
     *
     * 初始化游戏配置
     *
     */
    public void startGamePanelInit() {
        for (Layer temp : this.lays) {
            // 刷新窗口UI
            temp.startPanel();
        }
    }

}
