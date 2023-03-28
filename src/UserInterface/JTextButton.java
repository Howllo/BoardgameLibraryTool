package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class JTextButton extends JButton{
    private Color onMouseHover;
    private Color onMouseClick;
    private Color onMouseExit;

    public JTextButton(String text){
        setText(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);

        // Set Text
        Map<TextAttribute, Integer> textAttribute = new HashMap<>();
        textAttribute.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        Font newFont = new Font("Serif", getFont().getStyle(), getFont().getSize()).deriveFont(textAttribute);
        setFont(newFont);
        setForeground(Color.decode("#d7d7d7"));
    }

    public void setOnMouseClick(Color onMouseClick) {
        this.onMouseClick = onMouseClick;
    }

    public void setOnMouseHover(Color onMouseHover) {
        this.onMouseHover = onMouseHover;
    }

    public void setOnMouseExit(Color onMouseExit) {
        this.onMouseExit = onMouseExit;
    }

    @Override
    protected void paintComponent(Graphics g) {

        if(getModel().isPressed())
            setForeground(onMouseClick);
         else if(getModel().isRollover())
            setForeground(onMouseHover);
         else
            setForeground(onMouseExit);

        super.paintComponent(g);
    }
}
