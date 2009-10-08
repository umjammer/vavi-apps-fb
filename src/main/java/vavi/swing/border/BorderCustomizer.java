/*
 * Copyright (c) 2002 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.swing.border;

import java.beans.PropertyChangeListener;

import javax.swing.border.Border;


/**
 * BorderCustomizer.
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 020518 nsano initial version <br>
 *          1.00 020527 nsano complete <br>
 */
public interface BorderCustomizer /* extends Customizer */ {

    /**
     * PropertyChange �C�x���g�̃��X�i�[��o�^���܂��B
     * @param listener PropertyChange �C�x���g���g���K�[���ꂽ�Ƃ���
     *		       �Ăяo�����I�u�W�F�N�g
     */
    void addPropertyChangeListener(PropertyChangeListener listener);

    /**
     * PropertyChange �C�x���g�̃��X�i�[���폜���܂��B
     * @param listener �폜����� PropertyChange ���X�i�[
     */
    void removePropertyChangeListener(PropertyChangeListener listener);
 
    /**
     * �J�X�^�}�C�Y���� Border ��ݒ肵�܂��B
     * ���̃��\�b�h�́ACustomizer ��e AWT �R���e�i�ɒǉ�����O��
     * 1 �񂾂��Ăяo���܂��B
     */
    void setObject(Border border);

    /** TODO TabbedPane �� ChangeEvent �̂��߂��� */
    Border getObject();
}

/* */
