package Menu;

import javax.swing.*;

public class PopMenu extends JPopupMenu {
    public PopMenu() {
        add(new JMenuItem("SubMenuA"));
        add(new JMenuItem("SubMenuB"));
        add(new JMenuItem("SubMenuC"));
        add(new JMenuItem("SubMenuD"));
    }
}
