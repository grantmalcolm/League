/*
 * Copyright Grant Malcolm 22-Apr-2018.
 * This source code may be used, modified, and distributed,
 *  provided due credit be given.
 */
package league;

import java.util.Random;

/**
 * List of fixtures for a league with an even number of teams.
 * 
 * 
 * @author Grant Malcolm (grantrmalcolm@gmail.com)
 */
class FixtureList {

    /**
     * Number of rounds.
     */
    private final int numWeeks;

    /**
     * List of fixtures by week.
     */
    private final FixturesForWeek[] fixtures;

    /**
     * Create a new fixture list for a league.
     * The given array of teams should have even length.
     * @param teams an array of teams in the league
     * @param rounds the number of times each team plays any other team
     */
    public FixtureList(TeamData[] teams, int rounds) {
        int teamsCount = teams.length - 1; // number of teams

        // shuffle teams: swap enough times for random order
        final int shuffleCount = 3 * teams.length;
        int first;
        int second;
        TeamData temp;
        final Random rng = new Random();
        for (int i = 0; i < shuffleCount; i++) {
            first = rng.nextInt(teams.length);
            second = rng.nextInt(teams.length);
            if (first != second) {
                temp = teams[first];
                teams[first] = teams[second];
                teams[second] = temp;
            }
        }
        // set up list of weekly rounds
        numWeeks = rounds * (teamsCount);
        fixtures = new FixturesForWeek[numWeeks];
        for (int i = 0; i < numWeeks; i++) {
            fixtures[i] = new FixturesForWeek(teams.length / 2);
        }

        // do all but the bottom row
        int week;
        int maxWeeks = teamsCount - 1;
        int teamA = 1;
        int teamB;
        while (teamA < teamsCount) {
            week = teamA - 1; // index from 0
            teamB = 0;
            while (teamB < teamA) { // "diagonal" order
                if (week % 2 == 0) { // home or away
                    fixtures[week].addFixture(teams[teamA], teams[teamB]);
                } else {
                    fixtures[week].addFixture(teams[teamB], teams[teamA]);
                }
                week++;
                if (week >= teamsCount) {
                    week -= teamsCount;
                }
                teamB++;
            }
            teamA++;
        }

        // do the bottom row
        teamB = 0;
        week = maxWeeks;
        while (teamB < teamsCount) {
            if (week % 2 == 0) {
                fixtures[week].addFixture(teams[teamsCount], teams[teamB]);
            } else {
                fixtures[week].addFixture(teams[teamB], teams[teamsCount]);
            }
            week += 2;
            if (week >= teamsCount) {
                week -= teamsCount;
            }
            teamB++;
        }
        // balance home and away ties
        for (int i = 0; i < rounds - 1; i++) {
            for (int wj = 0; wj < teamsCount; wj++) {
                fixtures[(i + 1) * teamsCount + wj] = fixtures[i * teamsCount + wj].swap();
            }

        }
    }

    /**
     * Accessor for a given week.
     * 
     * @param i index of the week
     * @return the fixtures for that week
     */
    public FixturesForWeek getWeeksMatches(int i) {
        return fixtures[i];
    }
}
