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
class MatchResult {

    /**
     * Describe variable <code>triesA</code> here.
     *
     */
    private int triesA;

    /**
     * Describe variable <code>triesB</code> here.
     *
     */
    private int triesB;

    /**
     * Describe variable <code>convsA</code> here.
     *
     */
    private int convsA;

    /**
     * Describe variable <code>convsB</code> here.
     *
     */
    private int convsB;

    /**
     * Describe variable <code>pensA</code> here.
     *
     */
    private int pensA;

    /**
     * Describe variable <code>pensB</code> here.
     *
     */
    private int pensB;

    /**
     * Describe variable <code>dgsA</code> here.
     *
     */
    private int dgsA;

    /**
     * Describe variable <code>dgsB</code> here.
     *
     */
    private int dgsB;

    /**
     * Creates a new <code>MatchResult</code> instance.
     *
     */
    public MatchResult() {
        this(0, 0, 0, 0, 0, 0, 0, 0);
    }

    /**
     * Creates a new <code>MatchResult</code> instance.
     *
     * @param ta an <code>int</code> value
     * @param tb an <code>int</code> value
     * @param ca an <code>int</code> value
     * @param cb an <code>int</code> value
     * @param pa an <code>int</code> value
     * @param pb an <code>int</code> value
     * @param da an <code>int</code> value
     * @param db an <code>int</code> value
     */
    public MatchResult(int ta, int tb, int ca, int cb,
            int pa, int pb, int da, int db) {
        triesA = ta;
        triesB = tb;
        convsA = ca;
        convsB = cb;
        pensA = pa;
        pensB = pb;
        dgsA = da;
        dgsB = db;
    }

    /**
     * Describe <code>getTries</code> method here.
     *
     * @param ha a <code>boolean</code> value
     * @return an <code>int</code> value
     */
    public int getTries(boolean ha) {
        if (ha) {
            return triesA;
        } else {
            return triesB;
        }
    }

    /**
     * Describe <code>getConvs</code> method here.
     *
     * @param ha a <code>boolean</code> value
     * @return an <code>int</code> value
     */
    public int getConvs(boolean ha) {
        if (ha) {
            return convsA;
        } else {
            return convsB;
        }
    }

    /**
     * Describe <code>getPens</code> method here.
     *
     * @param ha a <code>boolean</code> value
     * @return an <code>int</code> value
     */
    public int getPens(boolean ha) {
        if (ha) {
            return pensA;
        } else {
            return pensB;
        }
    }

    /**
     * Describe <code>getDGs</code> method here.
     *
     * @param ha a <code>boolean</code> value
     * @return an <code>int</code> value
     */
    public int getDGs(boolean ha) {
        if (ha) {
            return dgsA;
        } else {
            return dgsB;
        }
    }

    /**
     * Describe <code>addTry</code> method here.
     *
     * @param ha a <code>boolean</code> value
     */
    public void addTry(boolean ha) {
        if (ha) {
            triesA++;
        } else {
            triesB++;
        }
    }

    /**
     * Describe <code>addConv</code> method here.
     *
     * @param ha a <code>boolean</code> value
     */
    public void addConv(boolean ha) {
        if (ha) {
            convsA++;
        } else {
            convsB++;
        }
    }

    /**
     * Describe <code>addPen</code> method here.
     *
     * @param ha a <code>boolean</code> value
     */
    public void addPen(boolean ha) {
        if (ha) {
            pensA++;
        } else {
            pensB++;
        }
    }

    /**
     * Describe <code>addDG</code> method here.
     *
     * @param ha a <code>boolean</code> value
     */
    public void addDG(boolean ha) {
        if (ha) {
            dgsA++;
        } else {
            dgsB++;
        }
    }

    /**
     * Describe <code>getScoreA</code> method here.
     *
     * @return an <code>int</code> value
     */
    public int getScoreA() {
        return 5 * triesA + 2 * convsA + 3 * pensA + 3 * dgsA;
    }

    /**
     * Describe <code>getScoreB</code> method here.
     *
     * @return an <code>int</code> value
     */
    public int getScoreB() {
        return 5 * triesB + 2 * convsB + 3 * pensB + 3 * dgsB;
    }
}
