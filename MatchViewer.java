/*
 * Copyright Grant Malcolm 22-Apr-2018.
 * This source code may be used, modified, and distributed,
 *  provided due credit be given.
 */
package league;

import java.awt.BorderLayout;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Grant Malcolm (grantrmalcolm@gmail.com)
 */
class MatchViewer extends JPanel {

    private TeamData teamA;

    private TeamData teamB;

    private final ScoreBoard board;

    private StadiumClock clock;

    private final JTextArea commentary = new JTextArea(20, 10);

    private final JScrollPane scroll = new JScrollPane(commentary);

    private static final Random RNG = new Random();

    private static final int TIME_INTERVAL = 8;

    private static final int MIN_INTERVAL = 3;

    private static final int MATCH_DURATION = 80;

    /**
     * Creates a new <code>MatchViewer</code> instance.
     *
     */
    public MatchViewer() {
        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);

        board = new ScoreBoard();
        add(board, BorderLayout.SOUTH);
    }

    public MatchResult play(Fixture fixture) {
        commentary.setText("");

        teamA = fixture.getA();
        String a = teamA.getName();
        teamB = fixture.getB();
        String b = teamB.getName();
        board.setTeams(a, b);

        MatchResult score = new MatchResult();

        int elapsedTime = 0;
        int scoreType;
        boolean ha;

        boolean firstHalf = true;

        report(a + " v " + b + "\n");
        report("  whistle blows... Play!\n");

        while (elapsedTime <= MATCH_DURATION) {
            elapsedTime += RNG.nextInt(TIME_INTERVAL) + MIN_INTERVAL;
            if (firstHalf && elapsedTime >= MATCH_DURATION / 2) {
                report("End of first half.\n");
                firstHalf = false;
            } else if (elapsedTime <= MATCH_DURATION) {
                report(elapsedTime + " minutes");
                // who scores?
                ha = (RNG.nextInt(100) < 55);
                // what sort of score
                scoreType = RNG.nextInt(100);
                if (scoreType < 40) {
                    report("Try! for " + (ha ? a : b));
                    score.addTry(ha);
                    if (scoreType < 27) {
                        report("Conversion successful - beautiful strike!\n");
                        score.addConv(ha);
                    } else {
                        report("The conversion's gone just wide of the posts\n");
                    }
                } else if (scoreType < 90) {
                    report("Penalty awarded to " + (ha ? a : b));
                    report("... lining up a shot at goal...");

                    if (scoreType < 70) {
                        report("GOAL!!!\n");
                        score.addPen(ha);
                    } else {
                        report("Oh!  He missed.\n");
                    }
                } else {
                    report("GOAL!");
                    report("Drop goal for " + (ha ? a : b) + "\n");
                    score.addDG(ha);
                }
                board.setScore(score.getScoreA(), score.getScoreB());
            }

            try {
                Thread.sleep(2000 + 500 * RNG.nextInt(7));
            } catch (InterruptedException e) {
                // shouldn't be interrupted
            }
        }

        report("\nFull time ... game over!");

        report("Final score:");
        report(a + " " + score.getScoreA());
        report(b + " " + score.getScoreB() + "\n");

        fixture.setResult(score);

        return score;
    }

    private void report(String t) {
        commentary.append(t + "\n");
        commentary.setCaretPosition(commentary.getText().length());
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Test ScoreBoard");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MatchViewer mv = new MatchViewer();

        f.add(mv, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);

        TeamData t1 = new TeamData("Edinburgh Rugby");
        TeamData t2 = new TeamData("Glasgow Warriors");
        Fixture fix = new Fixture(t1, t2);
        MatchResult mr = mv.play(fix);
    }
}
