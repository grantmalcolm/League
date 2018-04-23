/*
 * Copyright Grant Malcolm 22-Apr-2018.
 * This source code may be used, modified, and distributed,
 *  provided due credit be given.
 */
package league;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

/**
 *
 * @author Grant Malcolm (grantrmalcolm@gmail.com)
 */
class ColumnLayout {

    public static <A extends Component>
            Container addColumn(Container target, A[] comps) {
        final GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gridconst;
        final Insets insets = new Insets(1, 1, 1, 1);

        target.setLayout(grid);

        for (int i = 0; i < comps.length; i++) {
            gridconst = new GridBagConstraints(0, i,
                    1, 1,
                    1.0, 0.0,
                    GridBagConstraints.WEST,
                    GridBagConstraints.HORIZONTAL,
                    insets,
                    0, 0);
            grid.setConstraints(comps[i], gridconst);
            target.add(comps[i]);
        }

        return target;
    }

}
