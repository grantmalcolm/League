/*
 * Copyright Grant Malcolm 22-Apr-2018.
 * This source code may be used, modified, and distributed,
 *  provided due credit be given.
 */
package league;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author Grant Malcolm (grantrmalcolm@gmail.com)
 */
class TableRow extends JComponent
        implements TableRowConst, Comparable<TableRow> {

    /**
     * Describe constant <code>HEADER</code> here.
     *
     */
    public static final TableRow HEADER
            = new TableRow() {
        @Override
        public void paint(Graphics g) {
            g.setColor(new Color(160, 160, 255));
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.black);
            write(g, "Name", TableRowConst.NAME_POS);
            write(g, "P", TableRowConst.PLAYED_POS);
            write(g, "W", TableRowConst.WON_POS);
            write(g, "D", TableRowConst.DRAWN_POS);
            write(g, "L", TableRowConst.LOST_POS);
            write(g, "F", TableRowConst.FOR_POS);
            write(g, "A", TableRowConst.AGAINST_POS);
            write(g, "TF", TableRowConst.TFOR_POS);
            write(g, "TA", TableRowConst.TAGAINST_POS);
            write(g, "TBP", TableRowConst.TBP_POS);
            write(g, "LBP", TableRowConst.LBP_POS);
            write(g, "Points", TableRowConst.POINTS_POS);
        }
    };

    /**
     * Describe constant <code>BGCOLOUR1</code> here.
     *
     */
    private static final Color BGCOLOUR1 = Color.white;

    /**
     * Describe constant <code>BGCOLOUR2</code> here.
     *
     */
    private static final Color BGCOLOUR2 = new Color(240, 240, 255);

    /**
     * Describe variable <code>colour</code> here.
     *
     */
    private Color colour = BGCOLOUR1;

    /**
     * Describe variable <code>data</code> here.
     *
     */
    private TeamData data;

    /**
     * Creates a new <code>TableRow</code> instance.
     *
     */
    private TableRow() {
    }

    /**
     * Creates a new <code>TableRow</code> instance.
     *
     * @param td a <code>TeamData</code> value
     */
    public TableRow(TeamData td) {
        data = td;

        setPreferredSize(TableRowConst.SIZE);
    }

    @Override
    public int compareTo(TableRow tr) {
        return data.compareTo(tr.data);
    }

    /**
     * Describe <code>setColour</code> method here.
     *
     * @param i an <code>int</code> value
     */
    public void setColour(int i) {
        if (i % 2 == 0) {
            colour = BGCOLOUR1;
        } else {
            colour = BGCOLOUR2;
        }
    }

    /**
     * Describe <code>write</code> method here.
     *
     * @param g a <code>Graphics</code> value
     * @param s a <code>String</code> value
     * @param pos an <code>int</code> value
     */
    private static void write(Graphics g, String s, int pos) {
        g.drawString(s, pos, TableRowConst.HEIGHTP);
    }

    /**
     * Describe <code>paint</code> method here.
     *
     * @param g a <code>Graphics</code> value
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(colour);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.black);
        write(g, data.getName(), TableRowConst.NAME_POS);
        write(g, "" + data.getPlayed(), TableRowConst.PLAYED_POS);
        write(g, "" + data.getWon(), TableRowConst.WON_POS);
        write(g, "" + data.getDrawn(), TableRowConst.DRAWN_POS);
        write(g, "" + data.getLost(), TableRowConst.LOST_POS);
        write(g, "" + data.getPointsFor(), TableRowConst.FOR_POS);
        write(g, "" + data.getPointsAgainst(), TableRowConst.AGAINST_POS);
        write(g, "" + data.getTFor(), TableRowConst.TFOR_POS);
        write(g, "" + data.getTAgainst(), TableRowConst.TAGAINST_POS);
        write(g, "" + data.getTriesBP(), TableRowConst.TBP_POS);
        write(g, "" + data.getLosingBP(), TableRowConst.LBP_POS);
        write(g, "" + data.getPoints(), TableRowConst.POINTS_POS);
    }

    /**
     * Describe <code>main</code> method here.
     *
     * @param args a <code>String</code> value
     */
    public static void main(String[] args) {
        JFrame f = new JFrame("Testing Table Rows");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new GridLayout(7, 1));
        f.add(HEADER);
        TeamData td = new TeamData("Edinburgh Rugby");
        TableRow tr = new TableRow(td);
        tr.setColour(0);
        f.add(tr);
        td = new TeamData("Glasgow Warriors");
        tr = new TableRow(td);
        tr.setColour(1);
        f.add(tr);
        td = new TeamData("Harlequins");
        tr = new TableRow(td);
        tr.setColour(2);
        f.add(tr);
        td = new TeamData("Northampton Saints");
        tr = new TableRow(td);
        tr.setColour(3);
        f.add(tr);
        td = new TeamData("Ospreys");
        tr = new TableRow(td);
        tr.setColour(4);
        f.add(tr);
        td = new TeamData("Caldy");
        tr = new TableRow(td);
        tr.setColour(5);
        f.add(tr);
        f.pack();
        f.setVisible(true);
    }
}
