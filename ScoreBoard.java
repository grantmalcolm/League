/*
 * Copyright Grant Malcolm 22-Apr-2018.
 * This source code may be used, modified, and distributed,
 *  provided due credit be given.
 */
package league;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Grant Malcolm (grantrmalcolm@gmail.com)
 */
class ScoreBoard extends JPanel {

    private String teamA = "";

    private String teamB = "";

    private String scoreA = "";

    private String scoreB = "";

    /**
     * Creates a new <code>ScoreBoard</code> instance.
     *
     */
    public ScoreBoard() {
        setPreferredSize(new Dimension(300, 60));
    }

    public void setTeams(String a, String b) {
        teamA = a;
        scoreA = "0";
        teamB = b;
        scoreB = "0";
    }

    public void setScore(int a, int b) {
        scoreA = "" + a;
        scoreB = "" + b;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.yellow);
        g.drawString(teamA, 20, 20);
        g.drawString(teamB, 20, 40);
        g.drawString(scoreA, 200, 20);
        g.drawString(scoreB, 200, 40);
    }

    public static void main(String[] args)
            throws InterruptedException {
        JFrame f = new JFrame("Test ScoreBoard");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ScoreBoard sb = new ScoreBoard();
        sb.setTeams("Edinburgh Rugby", "Glasgow Warriors");

        f.add(sb);
        f.pack();
        f.setVisible(true);

        Thread.sleep(1000);
        sb.setScore(7, 0);
    }

}
