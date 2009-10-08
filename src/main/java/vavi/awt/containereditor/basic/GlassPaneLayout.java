/*
 * Copyright (c) 2002 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.awt.containereditor.basic;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Point;

import vavi.util.Debug;


/**
 * GlassPaneLayout
 *
 * @author	<a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version	0.00	020613	nsano	initial version <br>
 */
public class GlassPaneLayout implements LayoutManager {

    /**
     * ���̃N���X�ł͎g�p���܂���B
     */
    public void addLayoutComponent(String name, Component comp) {
    }

    /**
     * �w�肳�ꂽ�p�l���ɃR���e�i��z�u���܂��B
     */
    public void layoutContainer(Container parent) {
//Debug.println(Debug.getCallerMethod());
//Debug.println(parent.getPreferredSize().width + ", " + parent.getPreferredSize().height);
//Debug.println(parent);
//Debug.println(parent.getSize().width + ", " + parent.getSize().height);
        for (int i = 0; i < parent.getComponentCount(); i++) {
            Component component = parent.getComponent(i);
            component.setLocation(new Point(0, 0));
            component.setSize(parent.getSize());
        }
    }

    /**
     * �w�肳�ꂽ�e�R���e�i�ɃR���|�[�l���g��z�u��������
     * �p�l���̍ŏ��T�C�Y���v�Z���܂��B
     */
    public Dimension minimumLayoutSize(Container parent) {
Debug.println(parent.getSize().width + ", " + parent.getSize().height);
        return parent.getSize();
    }

    /**
     * �w�肳�ꂽ�e�R���e�i�ɃR���|�[�l���g��z�u��������
     * �p�l���̐����T�C�Y���v�Z���܂��B
     */
    public Dimension preferredLayoutSize(Container parent) {
Debug.println(parent.getSize().width + ", " + parent.getSize().height);
        return parent.getSize();
    }

    /**
     * ���̃N���X�ł͎g�p���܂���B
     */
    public void removeLayoutComponent(Component comp) {
    }
}

/* */
