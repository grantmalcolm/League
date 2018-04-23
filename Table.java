/*
 * Copyright Grant Malcolm 22-Apr-2018.
 * This source code may be used, modified, and distributed,
 *  provided due credit be given.
 */
package league;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Grant Malcolm (grantrmalcolm@gmail.com)
 */
class Table extends JPanel {

    /**
     * Describe variable <code>teams</code> here.
     *
     */
    private final TableRow[] teams;

    /**
     * Describe variable <code>numTeams</code> here.
     *
     */
    private final int numTeams;

    /**
     * Describe variable <code>numRows</code> here.
     *
     */
    private final int numRows;

    /**
     * Creates a new <code>Table</code> instance.
     *
     * @param ts a <code>TableRow</code> value
     */
    public Table(TeamData[] ts) {
        numTeams = ts.length;
        teams = new TableRow[numTeams];
        for (int i = 0; i < numTeams; i++) {
            teams[i] = new TableRow(ts[i]);
        }

        numRows = numTeams + 1;

        setLayout(new GridLayout(numRows, 1));

        setRows();
    }

    private void setRows() {
        setLayout(new GridLayout(numRows, 1));

        add(TableRow.HEADER);
        TableRow tr;
        for (int i = 0; i < numTeams; i++) {
            tr = teams[i];
            tr.setColour(i);
            add(tr);
        }
    }

    public String getLeader() {
        return teams[0].getName();
    }

    /**
     * Describe <code>updateAfterMatch</code> method here.
     *
     * @param f a <code>Fixture</code> value
     * @param mr a <code>MatchResult</code> value
     */
    private void updateAfterMatch(Fixture f, MatchResult mr) {
        f.getA().update(mr.getTries(true),
                mr.getConvs(true),
                mr.getPens(true),
                mr.getDGs(true),
                mr.getTries(false),
                mr.getConvs(false),
                mr.getPens(false),
                mr.getDGs(false)
        );
        f.getB().update(mr.getTries(false),
                mr.getConvs(false),
                mr.getPens(false),
                mr.getDGs(false),
                mr.getTries(true),
                mr.getConvs(true),
                mr.getPens(true),
                mr.getDGs(true)
        );
    }

    public void updateStandings() {
        int i = 1;
        int j;
        TableRow temp;
        while (i < teams.length) {
            if (teams[i - 1].compareTo(teams[i]) > 0) {
                temp = teams[i - 1];
                teams[i - 1] = teams[i];
                teams[i] = temp;
                j = i - 1;
                while (0 < j && teams[j - 1].compareTo(teams[j]) > 0) {
                    temp = teams[j - 1];
                    teams[j - 1] = teams[j];
                    teams[j] = temp;
                    j--;
                }
            }
            i++;
        }
        removeAll();
        setRows();
        revalidate();
        repaint();
    }

    /**
     * Describe <code>main</code> method here.
     *
     * @param args a <code>String</code> value
     */
    public static void main(String[] args)
            throws InterruptedException {
        JFrame f = new JFrame("Testing Table Rows");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TeamData t1 = new TeamData("Edinburgh Rugby");
        TeamData t2 = new TeamData("Glasgow Warriors");
        TeamData t3 = new TeamData("Caldy");
        TeamData t4 = new TeamData("Northampton Saints");
        TeamData t5 = new TeamData("Harley Quins");
        TeamData t6 = new TeamData("Hairsprays");
        TeamData t7 = new TeamData("Drag Queens");

        TeamData[] ts = new TeamData[]{
            t1, t2, t3, t4, t5, t6, t7
        };

        Table t = new Table(ts);

        f.add(t, BorderLayout.CENTER);

        f.pack();
        f.setVisible(true);

        Thread.sleep(1000);

        System.out.println("fixture then update");

        Fixture fix = new Fixture(t1, t7);
        MatchResult mr = new MatchResult(1, 2, 5, 3, 4, 0, 1, 1);
        t.updateAfterMatch(fix, mr);
        t.updateStandings();

        System.out.println("done fixture then update");

        Thread.sleep(1000);

        System.out.println("fixture then update");

        fix = new Fixture(t7, t1);
        t.updateAfterMatch(fix, mr);
        t.updateStandings();

        System.out.println("done fixture then update");

        Thread.sleep(1000);

        System.out.println("fixture then update");

        fix = new Fixture(t3, t7);
        t.updateAfterMatch(fix, mr);
        t.updateStandings();

        System.out.println("done fixture then update");

        Thread.sleep(1000);

        System.out.println("fixture then update");

        fix = new Fixture(t1, t5);
        t.updateAfterMatch(fix, mr);
        t.updateStandings();

        System.out.println("done fixture then update");
    }
}
