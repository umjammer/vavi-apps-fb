/*
 * Copyright (c) 2002 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.swing.containertree;

import java.awt.Component;

import javax.swing.tree.DefaultMutableTreeNode;


/**
 * �R���e�i�c���[��̃m�[�h�ł��D
 *
 * @author	<a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version	0.00	020510	nsano	initial version <br>
 */
public class ComponentTreeNode extends DefaultMutableTreeNode {

    /**
     * �R���|�[�l���g��\���c���[�m�[�h���\�z���܂��D
     * @param component Component
     */
    public ComponentTreeNode(Component component) {
        this.userObject = component;
    }

    /**
     * ���̃m�[�h��\���������Ԃ��܂��D
     */
    public String getName() {
        return toString();
    }

    /**
     * ���̃m�[�h��\���������Ԃ��܂��D
     */
    public String getDescription() {
        return toString();
    }

    /**
     * ���̃m�[�h��\���������Ԃ��܂��D
     */
    public String toString() {
        return ((Component) userObject).getName();
    }
}

/* */
