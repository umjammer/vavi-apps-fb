/*
 * Copyright (c) 2001 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.swing.event;

import java.util.EventListener;


/**
 * �G�f�B�^�̃��X�i�C���^�[�t�F�[�X�ł��D
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 010820 nsano initial version <br>
 *          0.10 010906 nsano be generic <br>
 *          0.11 020503 nsano repackage <br>
 *          0.12 020510 nsano repackage <br>
 *          0.20 020510 nsano deprecate open/close <br>
 */
public interface EditorListener extends EventListener {

    /**
     * �G�f�B�^���I�[�v�������Ƃ��Ă΂�܂��D
     * @param	ev	�G�f�B�^�C�x���g
     */
//    void editorOpened(EditorEvent ev);

    /**
     * �G�f�B�^���A�b�v�f�[�g���ꂽ�Ƃ��Ă΂�܂��D
     * @param	ev	�G�f�B�^�C�x���g
     */
    void editorUpdated(EditorEvent ev);

    /**
     * �G�f�B�^���I�������Ƃ��Ă΂�܂��D
     * @param	ev	�G�f�B�^�C�x���g
     */
//    void editorClosed(EditorEvent ev);
}

/* */
