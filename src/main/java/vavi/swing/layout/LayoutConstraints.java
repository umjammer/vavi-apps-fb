/*
 * Copyright (c) 2002 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.swing.layout;

import java.beans.*;


/**
 * LayoutManager �� Constraints �N���X�� Beans �Ƃ��Ĉ���
 * ���b�p�[�N���X���쐬���邽�߂̊��N���X�ł��D
 * <p>
 * setter ���\�b�h�ł� PropertyChangeEvent �𔭍s���Ă��������D // TODO
 * </p>
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 020528 nsano initial version <br>
 */
public abstract class LayoutConstraints {

    /**
     * Gets constraints.
     */
    public abstract Object getConstraints();

    /**
     * Sets constraints.
     */
    public abstract void setConstraints(Object constraints);

    //-------------------------------------------------------------------------

    /** PropertyChange �C�x���g�@�\�̃��[�e�B���e�B */
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /** PropertyChangeListener ��ǉ����܂��D */
    public void addPropertyChangeListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    /** PropertyChangeListener ���폜���܂��D */
    public void removePropertyChangeListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    /** PropertyChangeEvent �𔭍s���܂��D */
    protected void firePropertyChange(String name,
                                      int oldValue, int newValue) {
        pcs.firePropertyChange(name, oldValue, newValue);
    }

    /** PropertyChangeEvent �𔭍s���܂��D */
    protected void firePropertyChange(String name,
                                      Object oldValue, Object newValue) {
        pcs.firePropertyChange(name, oldValue, newValue);
    }
}

/* */
