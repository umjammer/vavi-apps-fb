/*
 * Copyright (c) 2002 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.awt.containereditor;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JComponent;


/**
 * �R���g���[���ł��D
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 020529 nsano initial version <br>
 *          0.01 020611 nsano change package <br>
 */
public abstract class AbstractController extends JComponent implements LocatableController {

    /** �I����Ԃ��ǂ��� */
    private boolean isSelected = false;

    /** */
    protected Component view;

    /**
     * �R���g���[�����\�z���܂��D
     */
    public AbstractController(Component view) {
        this.view = view;
//      this.setDoubleBuffered(true);
    }

    /**
     * view ��ݒ肵�܂��D
     * 
     * @param view a view component
     */
    public void setView(Component view) {
        this.view = view;
    }

    /**
     * view ���擾���܂��D
     */
    public Component getView() {
        return view;
    }

    /**
     * �I����Ԃ�ݒ肵�܂��D
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        repaint();
    }

    /**
     * �I����Ԃ�Ԃ��܂��D
     */
    public boolean isSelected() {
        return isSelected;
    }

    /** */
    public void setLocation(Point location) {
        super.setLocation(location);
        view.setLocation(location);
    }

    /** */
    public Point getLocation() {
        return view.getLocation();
    }

    /** */
    public void setSize(Dimension size) {
        super.setSize(size);
        view.setSize(size);
    }

    /** */
    public Dimension getSize() {
        return view.getSize();
    }

    /** */
    public void setBounds(Rectangle bounds) {
        super.setBounds(bounds);
        view.setBounds(bounds);
    }

    /** */
    public Rectangle getBounds() {
        return view.getBounds();
    }
}

/* */
