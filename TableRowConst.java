/*
 * Copyright Grant Malcolm 22-Apr-2018.
 * This source code may be used, modified, and distributed,
 *  provided due credit be given.
 */
package league;

import java.awt.Dimension;

/**
 * Fixes positions for the various elements in a row in a league table. Each row
 * specifies:
 * <ul>
 * <li> name of the team </li>
 * <li> number of games played </li>
 * <li> number of games won </li>
 * <li> number of games drawn </li>
 * <li> number of games lost </li>
 * <li> number of points for </li>
 * <li> number of points against </li>
 * <li> number of tries for </li>
 * <li> number of tries against </li>
 * <li> number of try bonus points </li>
 * <li> number of losing bonus points </li>
 * <li> total number of points. </li>
 * </ul>
 * There are also constants for the height of a table row
 *
 * @author Grant Malcolm (grantrmalcolm@gmail.com)
 */
interface TableRowConst {

    /**
     * The x-coordinate of the team's name.
     *
     */
    public static final int NAME_POS = 10;

    /**
     * The x-coordinate of the number of games played.
     *
     */
    public static final int PLAYED_POS = NAME_POS + 230;

    /**
     * The x-coordinate of the number of games won.
     *
     */
    public static final int WON_POS = PLAYED_POS + 40;

    /**
     * The x-coordinate of the number of games drawn.
     *
     */
    public static final int DRAWN_POS = WON_POS + 30;

    /**
     * The x-coordinate of the number of games lost.
     *
     */
    public static final int LOST_POS = DRAWN_POS + 30;

    /**
     * The x-coordinate of the number of points for.
     *
     */
    public static final int FOR_POS = LOST_POS + 40;

    /**
     * The x-coordinate of the number of points against.
     *
     */
    public static final int AGAINST_POS = FOR_POS + 40;

    /**
     * The x-coordinate of the number of tries for.
     *
     */
    public static final int TFOR_POS = AGAINST_POS + 40;

    /**
     * The x-coordinate of the number of tries against.
     *
     */
    public static final int TAGAINST_POS = TFOR_POS + 30;

    /**
     * The x-coordinate of the number of try bonus points.
     *
     */
    public static final int TBP_POS = TAGAINST_POS + 30;

    /**
     * The x-coordinate of the number fo losing bonus points.
     *
     */
    public static final int LBP_POS = TBP_POS + 30;

    /**
     * The x-coordinate of the total league points.
     *
     */
    public static final int POINTS_POS = LBP_POS + 30;

    /**
     * The y-coordinate for writing strings in a row.
     *
     */
    public static final int HEIGHTP = 12;

    /**
     * The overall size of a table row.
     *
     */
    public static final Dimension SIZE = new Dimension(710, 20);

}
