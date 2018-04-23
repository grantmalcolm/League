/*
 * Copyright Grant Malcolm 22-Apr-2018.
 * This source code may be used, modified, and distributed,
 *  provided due credit be given.
 */
package league;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 *
 * @author Grant Malcolm (grantrmalcolm@gmail.com)
 */
class Fixture extends Canvas {

    /**
     * Describe variable <code>teamA</code> here.
     *
     */
    private final TeamData teamA;

    /**
     * Describe variable <code>teamB</code> here.
     *
     */
    private final TeamData teamB;

    /**
     * Describe variable <code>result</code> here.
     *
     */
    private MatchResult result = null;

    /**
     * Describe constant <code>SIZE</code> here.
     *
     */
    private static final Dimension SIZE = new Dimension(250, 20);

    /**
     * Describe constant <code>NORMAL_COLOR</code> here.
     *
     */
    private static final Color NORMAL_COLOR = Color.white;

    /**
     * Describe constant <code>HIGHLIT_COLOR</code> here.
     *
     */
    private static final Color HIGHLIT_COLOR = Color.yellow;

    /**
     * Describe constant <code>TEXT_COLOR</code> here.
     *
     */
    private static final Color TEXT_COLOR = Color.black;

    /**
     * Describe variable <code>selected</code> here.
     *
     */
    private boolean selected = false;

    /**
     * Creates a new <code>Fixture</code> instance.
     *
     * @param a a <code>TeamData</code> value
     * @param b a <code>TeamData</code> value
     */
    public Fixture(TeamData a, TeamData b) {
        teamA = a;
        teamB = b;

        setPreferredSize(SIZE);
    }

    /**
     * Describe <code>getA</code> method here.
     *
     * @return a <code>TeamData</code> value
     */
    public TeamData getA() {
        return teamA;
    }

    /**
     * Describe <code>getB</code> method here.
     *
     * @return a <code>TeamData</code> value
     */
    public TeamData getB() {
        return teamB;
    }

    /**
     * Describe <code>setResult</code> method here.
     *
     * @param mr a <code>MatchResult</code> value
     */
    public void setResult(MatchResult mr) {
        result = mr;
    }

    /**
     * Describe <code>setSelected</code> method here.
     *
     * @param b a <code>boolean</code> value
     */
    public void setSelected(boolean b) {
        selected = b;
        repaint();
    }

    /**
     * Describe <code>swap</code> method here.
     *
     * @return a <code>Fixture</code> value
     */
    public Fixture swap() {
        return new Fixture(teamB, teamA);
    }

    /**
     * Describe <code>print</code> method here.
     *
     */
    public void print() {
        System.out.println("  " + teamA.getName() + " v " + teamB.getName());
    }

    /**
     * Describe <code>paint</code> method here.
     *
     * @param g a <code>Graphics</code> value
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(selected ? HIGHLIT_COLOR : NORMAL_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(TEXT_COLOR);
        String text = teamA.getName() + " ";
        text += (result == null) ? " v "
                : result.getScoreA() + "  " + result.getScoreB();
        text += " " + teamB.getName();
        g.drawString(text, 10, 12);
    }
}
