/*
 * Copyright Grant Malcolm 22-Apr-2018.
 * This source code may be used, modified, and distributed,
 *  provided due credit be given.
 */
package league;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Label;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

/**
 * Set up a league of Rugby Union teams and play a season of fixtures. The user
 * specifies the teams and the format of the league; class <code>League</code>
 * gives the fixture list and plays through the fixtures week-by-week.
 *
 * Note: this application is being used as an example as I learn about
 * implementing Restful architectures in Java, and much of the functionality
 * will be and is being pushed onto web-accessed dBs, often for no other reason
 * than because it can (and sometimes even though it makes little sense to do it
 * that way!).
 *
 * Future versions in this pathway:
 * <ul>
 * <li> dBs of Players, Teams and Leagues </li>
 * <li> live streaming of games </li>
 * <li> use of OPTA or suchlike for fantasy leagues </li>
 * </ul>
 *
 * The {@link MatchViewer#play(int) game-player} uses random match events; see
 * package Rugboids for a better attempts at match-play simulation.
 *
 * @author Grant Malcolm (grantrmalcolm@gmail.com)
 */
public class League extends JFrame {

    /**
     * The name of the league.
     */
    private final String leagueName;

    /**
     * Default league name.
     */
    public static final String DEFAULT_NAME = "Magner's League";

    /**
     * Default teams for the league.
     */
    private static final TeamData[] DEFAULT_TEAMS
            = new TeamData[]{new TeamData("Edinburgh Rugby"),
                new TeamData("Glasgow Warriors"),
                new TeamData("Munster"),
                new TeamData("Leinster"),
                new TeamData("Ulster"),
                new TeamData("Connacht"),
                new TeamData("Scarlets"),
                new TeamData("Ospreys"),
                new TeamData("Cardiff Blues"),
                new TeamData("Dragons")
            };

    /**
     * The number of teams in the league.
     */
    private final int numTeams;

    /**
     * Default number of teams in the league.
     *
     * @see DEFAULT_TEAMS
     */
    private static final int DEFAULT_NUM_TEAMS = DEFAULT_TEAMS.length;

    /**
     * The table for this league. This should be updated after each game is
     * {@link MatchViewer#play(int) played}.
     */
    private final Table standings;

    /**
     * The fixture list for this league.
     */
    private final FixtureList fixtures;

    /**
     * The number of rounds for the league. How many times does one team play
     * each other team.
     */
    private final int numRounds;

    /**
     * The default {@link numRounds number of rounds} for the league.
     */
    private static final int DEFAULT_ROUNDS = 2;

    /**
     * Counter for the current week number.
     */
    private int week = 0;

    /**
     * The total number of weeks' fixtures.
     */
    private int totalWeeks;

    /**
     * The current week's fixtures.
     */
    private FixturesForWeek currentMatches;

    /**
     * The next match to be played.
     */
    private int nextMatch;

    /**
     * The basic game player.
     */
    private MatchViewer matchReports = new MatchViewer();

    /**
     * GUI area showing {@link currentMatches the current week's fixtures}.
     */
    private JPanel weekPanel = new JPanel();

    /**
     * User control to play a single game.
     */
    private final JButton playB = new JButton("Play");

    /**
     * User control to skip the play-through of a week's matches; the results of
     * those matches are generated.
     */
    private final JButton skipB = new JButton("Skip");

    /**
     * User control to play through all of
     * {@link currentMatches the current week's matches}.
     */
    private final JButton playAllB = new JButton("Play All");

    /**
     * User control to move on to the next week's fixtures.
     */
    private final JButton nextB = new JButton("Next>");

    /**
     * Header for GUI showing league title and week number.
     */
    private final HeaderPanel headPanel;

    /**
     * Creates a new League with default teams and number of rounds.
     *
     */
    public League() {
        this(DEFAULT_NAME, DEFAULT_TEAMS, DEFAULT_ROUNDS);
        this.nextMatch = 0;
    }

    /**
     * Creates a new League with given data.
     *
     * @param name the name of the league
     * @param teams the teams
     * @param rounds the number of times each team plays any other team
     */
    public League(String name, TeamData[] teams, int rounds) {
        // set up the fixtures
        this.nextMatch = 0;
        leagueName = name;
        numTeams = teams.length;
        totalWeeks = rounds * (teams.length - 1);
        numRounds = rounds;
        fixtures = new FixtureList(teams, rounds);
        // set up the GUI: league title and week number:
        headPanel = new HeaderPanel(name);
        // league table
        standings = new Table(teams);
        setColours(standings);
        //get the week's fixtures
        currentMatches = fixtures.getWeeksMatches(week);
        currentMatches.init();
        setColours(currentMatches);
        weekPanel.add(currentMatches);
        setColours(weekPanel);
        // show the week's fixtures
        JPanel fixturesPanel = new JPanel();
        setColours(fixturesPanel);
        fixturesPanel.setLayout(new BorderLayout());
        fixturesPanel.add(weekPanel, BorderLayout.CENTER);

        // set up user controls
        JPanel fixturesButtons = new JPanel();
        setColours(fixturesButtons);
        fixturesButtons.add(playB);
        fixturesButtons.add(skipB);
        fixturesButtons.add(playAllB);
        fixturesButtons.add(nextB);
        nextB.setEnabled(false);
        fixturesPanel.add(fixturesButtons, BorderLayout.SOUTH);
        setColours(fixturesPanel);

        // event handlers
        playB.addActionListener((ActionEvent ae) -> {
            play(1);
        });

        skipB.addActionListener((ActionEvent ae) -> {
            play(0);
        });

        playAllB.addActionListener((ActionEvent ae) -> {
            playB.setEnabled(false);
            skipB.setEnabled(false);
            playAllB.setEnabled(false);

            Runnable r = () -> {
                MatchResult mr;
                while (!currentMatches.completed()) { // play each match
                    mr = matchReports.play(currentMatches.nextMatch());
                    updateAfterMatch(mr);
                }
                standings.updateStandings();
            };
            new Thread(r).start();
        });

        nextB.addActionListener((ActionEvent ae) -> {
            nextWeek();
        });

        setColours(matchReports);
        // create GUI
        Container contents = getContentPane();
        contents.add(headPanel, BorderLayout.NORTH);
        contents.add(fixturesPanel, BorderLayout.WEST);
        contents.add(matchReports, BorderLayout.CENTER);
        contents.add(standings, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Play a match.
     * 
     * @param s 0 to skip a match (not implemented in v1)
     */
    private synchronized void play(final int s) {
        playB.setEnabled(false);
        skipB.setEnabled(false);
        playAllB.setEnabled(false);

        Runnable r = () -> {
            MatchResult mr
                    = matchReports.play(currentMatches.nextMatch());
            updateAfterMatch(mr);
            standings.updateStandings();
        };
        new Thread(r).start();
    }

    /**
     * Update the league table with the given match result.
     * 
     * @param mr the result of a match
     */
    private void updateAfterMatch(MatchResult mr) {
        // get the two teams and update currentMatches
        Fixture f = currentMatches.nextMatch();
        currentMatches.goNext();

        boolean ba = true;  // to access team A
        boolean bb = false; // to access team B
        // update team A's stats
        f.getA().update(mr.getTries(ba),
                mr.getConvs(ba),
                mr.getPens(ba),
                mr.getDGs(ba),
                mr.getTries(bb),
                mr.getConvs(bb),
                mr.getPens(bb),
                mr.getDGs(bb)
        );
        // update Team B's stats
        f.getB().update(mr.getTries(bb),
                mr.getConvs(bb),
                mr.getPens(bb),
                mr.getDGs(bb),
                mr.getTries(ba),
                mr.getConvs(ba),
                mr.getPens(ba),
                mr.getDGs(ba)
        );
        f.setSelected(false);  // change GUI highlight
        f.repaint();
        // reset buttons
        if (currentMatches.completed()) {
            // enable next button; disable play buttons
            enablePlay(false);
        } else {
            currentMatches.nextMatch().repaint();
            enablePlay(true);
        }
    }

    /**
     * Move on to the next week's fixtures.
     */
    public void nextWeek() {
        if (week >= totalWeeks) {
            /*
         LeaguePlayer.setUpFrame.setVisible(true);
         this.dispose();
             */
            return;
        }

        // go to next week
        headPanel.incrementLabel();
        // update fixtures for the week
        weekPanel.remove(currentMatches);
        week++;
        if (week < totalWeeks) {
            currentMatches = fixtures.getWeeksMatches(week);
            currentMatches.init();
            weekPanel.add(currentMatches);
            weekPanel.repaint();
            weekPanel.revalidate();
            // enable play buttons
            enablePlay(true);
        } else {
            Label l1 = new Label("League is finished");
            Label l2 = new Label(standings.getLeader() + " are the champions!");
            Label[] labels = new Label[]{l1, l2};
            ColumnLayout.addColumn(weekPanel, labels);
            weekPanel.repaint();
            weekPanel.revalidate();
        }
    }

    /**
     * Enable or disable user controls.
     * 
     * @param flag true to enable controls; false to disable
     */
    public void enablePlay(boolean flag) {
        playB.setEnabled(flag);
        skipB.setEnabled(flag);
        playAllB.setEnabled(flag);
        nextB.setEnabled(!flag);
    }

    /**
     * Accessor for GUI header.
     * 
     * @return the header for the GUI
     */
    private String headerString() {
        return leagueName + ", Week " + week;
    }

    /**
     * Accessor for the fixture list.
     * 
     * @return the fixture list for the league
     */
    public FixtureList getFixtures() {
        return fixtures;
    }

    /**
     * Accessor for the League's name.
     * 
     * @return the name of the league
     */
    public String getName() {
        return leagueName;
    }

    /**
     * Set GUI background and foreground colours.
     * 
     * @param comp the component to set colours for
     */
    private static void setColours(Component comp) {
        comp.setBackground(new Color(66, 99, 66));
        comp.setForeground(Color.yellow);
    }

    /**
     * Top level for the League player.
     * 
     * @param args command-line arguments: not used
     */
    public static void main(String[] args) {
        League ml = new League();
    }

}
