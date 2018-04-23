/*
 * Copyright Grant Malcolm 22-Apr-2018.
 * This source code may be used, modified, and distributed,
 *  provided due credit be given.
 */
package league;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Grant Malcolm (grantrmalcolm@gmail.com)
 */
class HeaderPanel extends JPanel {

    private int week = 0;

    private final String headerString;

    private final JLabel headerLabel;

    /**
     * Creates a new <code>HeaderPanel</code> instance.
     *
     */
    public HeaderPanel(String leagueName) {
        headerString = leagueName + ", Week ";
        headerLabel = new JLabel(headerString, SwingConstants.CENTER);
        incrementLabel();

        setBackground(new Color(66, 99, 66));
        headerLabel.setForeground(Color.yellow);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));

        setLayout(new BorderLayout());
        add(headerLabel, BorderLayout.CENTER);

        setPreferredSize(new Dimension(350, 35));
    }

    public void incrementLabel() {
        week++;
        headerLabel.setText(headerString + week);
        repaint();
    }
}
