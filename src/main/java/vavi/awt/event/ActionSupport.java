/*
 * Copyright (c) 2002 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.awt.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Vector;


/**
 * Action ���X�i�[�̃��[�e�B���e�B�ł��D
 * 
 * @target 1.1
 * @caution JDK 1.1 �p�ɏ�����Ă���̂� swing �͎g�����Ⴂ����I
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 020503 nsano initial version <br>
 *          0.01 020914 nsano TODO sychronized <br>
 */
public class ActionSupport implements Serializable {

    /** Action �̃��X�i�[ */
    private Vector listeners = new Vector();

    /**
     * Action ���X�i�[���A�^�b�`���܂��D
     * 
     * @param l ActionListener
     */
    public void addActionListener(ActionListener l) {
        listeners.addElement(l);
    }

    /**
     * Action ���X�i�[�������[�u���܂��D
     * 
     * @param l ActionListener
     */
    public void removeActionListener(ActionListener l) {
        listeners.removeElement(l);
    }

    /**
     * �C�x���g�𔭍s���܂��D
     */
    public synchronized void fireActionPerformed(ActionEvent ev) {
        for (int i = 0; i < listeners.size(); i++) {
            ((ActionListener) listeners.elementAt(i)).actionPerformed(ev);
        }
    }
}

/* */
