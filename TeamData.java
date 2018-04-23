/*
 * Copyright Grant Malcolm 22-Apr-2018.
 * This source code may be used, modified, and distributed,
 *  provided due credit be given.
 */
package league;

/**
 *
 * @author Grant Malcolm (grantrmalcolm@gmail.com)
 */
class TeamData implements Comparable<TeamData> {

    /**
     * Describe variable <code>name</code> here.
     *
     */
    private final String name;

    /**
     * Describe variable <code>pointsFor</code> here.
     *
     */
    private int pointsFor = 0;

    /**
     * Describe variable <code>pointsAgainst</code> here.
     *
     */
    private int pointsAgainst = 0;

    /**
     * Describe variable <code>tFor</code> here.
     *
     */
    private int tFor = 0;

    /**
     * Describe variable <code>tAgainst</code> here.
     *
     */
    private int tAgainst = 0;

    /**
     * Describe variable <code>won</code> here.
     *
     */
    private int won = 0;

    /**
     * Describe variable <code>drawn</code> here.
     *
     */
    private int drawn = 0;

    /**
     * Describe variable <code>lost</code> here.
     *
     */
    private int lost = 0;

    /**
     * Describe variable <code>triesBP</code> here.
     *
     */
    private int triesBP = 0;

    /**
     * Describe variable <code>losingBP</code> here.
     *
     */
    private int losingBP = 0;

    /**
     * Creates a new <code>Team</code> instance.
     *
     * @param n a <code>String</code> value
     */
    public TeamData(String n) {
        name = n;
    }

    /**
     * Describe <code>getName</code> method here.
     *
     * @return a <code>String</code> value
     */
    public String getName() {
        return name;
    }

    /**
     * Describe <code>update</code> method here.
     *
     * @param triesFor an <code>int</code> value
     * @param convsFor an <code>int</code> value
     * @param pensFor an <code>int</code> value
     * @param dropsFor an <code>int</code> value
     * @param triesAgt an <code>int</code> value
     * @param convsAgt an <code>int</code> value
     * @param pensAgt an <code>int</code> value
     * @param dropsAgt an <code>int</code> value
     */
    public void update(int triesFor, int convsFor, int pensFor, int dropsFor,
            int triesAgt, int convsAgt, int pensAgt, int dropsAgt) {
        int teamScore = 5 * triesFor + 2 * convsFor + 3 * pensFor + 3 * dropsFor;
        int oppoScore = 5 * triesAgt + 2 * convsAgt + 3 * pensAgt + 3 * dropsAgt;
        pointsFor += teamScore;
        pointsAgainst += oppoScore;
        tFor += triesFor;
        tAgainst += triesAgt;

        if (teamScore > oppoScore) {
            won++;
        } else if (teamScore == oppoScore) {
            drawn++;
        } else {
            lost++;
            if (oppoScore - teamScore < 8) {
                losingBP++;
            }
        }
        if (triesFor > 3) {
            triesBP++;
        }
    }

    /**
     * Describe <code>getPointsFor</code> method here.
     *
     * @return an <code>int</code> value
     */
    public int getPointsFor() {
        return pointsFor;
    }

    /**
     * Describe <code>getPointsAgainst</code> method here.
     *
     * @return an <code>int</code> value
     */
    public int getPointsAgainst() {
        return pointsAgainst;
    }

    /**
     * Describe <code>getTFor</code> method here.
     *
     * @return an <code>int</code> value
     */
    public int getTFor() {
        return tFor;
    }

    /**
     * Describe <code>getTAgainst</code> method here.
     *
     * @return an <code>int</code> value
     */
    public int getTAgainst() {
        return tAgainst;
    }

    /**
     * Describe <code>getWon</code> method here.
     *
     * @return an <code>int</code> value
     */
    public int getWon() {
        return won;
    }

    /**
     * Describe <code>getDrawn</code> method here.
     *
     * @return an <code>int</code> value
     */
    public int getDrawn() {
        return drawn;
    }

    /**
     * Describe <code>getLost</code> method here.
     *
     * @return an <code>int</code> value
     */
    public int getLost() {
        return lost;
    }

    /**
     * Describe <code>getTriesBP</code> method here.
     *
     * @return an <code>int</code> value
     */
    public int getTriesBP() {
        return triesBP;
    }

    /**
     * Describe <code>getLosingBP</code> method here.
     *
     * @return an <code>int</code> value
     */
    public int getLosingBP() {
        return losingBP;
    }

    /**
     * Describe <code>getPoints</code> method here.
     *
     * @return an <code>int</code> value
     */
    public int getPoints() {
        return 4 * won + 2 * drawn + triesBP + losingBP;
    }

    /**
     * Describe <code>getPlayed</code> method here.
     *
     * @return an <code>int</code> value
     */
    public int getPlayed() {
        return won + drawn + lost;
    }

    /**
     * Describe <code>compareTo</code> method here.
     *
     * @param t a <code>TeamData</code> value
     * @return an <code>int</code> value
     */
    @Override
    public int compareTo(TeamData t) {
        // return 1 if t should be higher in league
        if (t.getPoints() < getPoints()) {
            return -1;
        } else if (getPoints() < t.getPoints()) {
            return 1;
        } else if (t.pointsFor - t.pointsAgainst < pointsFor - pointsAgainst) {
            return -1;
        } else if (pointsFor - pointsAgainst < t.pointsFor - t.pointsAgainst) {
            return 1;
        } else if (t.tFor < tFor) {
            return -1;
        } else if (tFor < t.tFor) {
            return 1;
        } else if (t.tAgainst < tAgainst) {
            return 1;
        } else if (tAgainst < t.tAgainst) {
            return -1;
        } else if (t.triesBP < triesBP) {
            return -1;
        } else if (triesBP < t.triesBP) {
            return 1;
        } else if (t.losingBP < losingBP) {
            return -1;
        } else if (losingBP < t.losingBP) {
            return 1;
        }

        return 0;
    }
}
