package com.company.auto;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TestAuto implements MouseListener {

    static boolean isAim = false;

    public static void main(String[] args) {
        try {
            Robot robot = new Robot();
            while (true){
                Point point = MouseInfo.getPointerInfo().getLocation();
                robot.mouseMove(point.x, point.y + 1);

            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        isAim = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isAim = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
