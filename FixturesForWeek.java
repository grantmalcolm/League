/*
 * Copyright Grant Malcolm 22-Apr-2018.
 * This source code may be used, modified, and distributed,
 *  provided due credit be given.
 */
package league;

import javax.swing.JPanel;

/**
 *
 * @author Grant Malcolm (grantrmalcolm@gmail.com)
 */
class FixturesForWeek extends JPanel {

    private final Fixture[] fixtures;

    private int count = 0;

    private int currentFixture = 0;

    /**
     * Creates a new <code>FixturesForWeek</code> instance.
     *
     */
    public FixturesForWeek(int len) {
        fixtures = new Fixture[len];
    }

    public Fixture getFixture(int i) {
        return fixtures[i];
    }

    public Fixture nextMatch() {
        return fixtures[currentFixture];
    }

    public void goNext() {
        fixtures[currentFixture].setSelected(false);
        fixtures[currentFixture].repaint();
        currentFixture++;
        if (!completed()) {
            fixtures[currentFixture].setSelected(true);
            fixtures[currentFixture].repaint();
        }
    }

    public boolean completed() {
        return (currentFixture == fixtures.length);
    }

    public void addFixture(TeamData a, TeamData b) {
        fixtures[count++] = new Fixture(a, b);
    }

    public void init() {
        ColumnLayout.addColumn(this, fixtures);

        currentFixture = 0;
        fixtures[currentFixture].setSelected(true);
    }

    public void updateAfterMatch() {
        fixtures[currentFixture++].setSelected(false);
        if (currentFixture < fixtures.length) {
            // not end of week
            fixtures[currentFixture].setSelected(true);
        }
    }

    public FixturesForWeek swap() {
        FixturesForWeek swaps = new FixturesForWeek(fixtures.length);
        for (int i = 0; i < fixtures.length; i++) {
            swaps.fixtures[i] = fixtures[i].swap();
        }
        return swaps;
    }

    public void print() {
        System.out.println("*** count = " + count);
        for (int i = 0; i < fixtures.length; i++) {
            System.out.print("" + i);
            fixtures[i].print();
        }
    }
}
