/*
 * Copyright (c) 2001 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.awt.rubberband;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;


/**
 * ���o�[�o���h�����_���̃C���^�[�t�F�[�X�ł��D
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 010829 nsano initial version <br>
 *          0.01 010903 nsano repackage <br>
 *          0.10 010904 nsano fix specifications??? <br>
 *          0.11 020509 nsano add getXXXInset <br>
 *          0.12 020604 nsano remove getXXXInset <br>
 *          1.00 020606 nsano fix specification <br>
 */
public interface RubberBandRenderer {

    /**
     * �I�����̃��o�[�o���h��`���܂��D
     */
    void drawSelecting(Rectangle bounds);

    /**
     * �I����̃��o�[�o���h��`�悵�܂��D
     */
    void drawSelected(Rectangle bounds);

    /**
     * �ړ����̃��o�[�o���h��`�悵�܂��D
     */
    void drawMoving(Rectangle bounds);

    /**
     * �ړ���̃��o�[�o���h��`�悵�܂��D
     */
    void drawMoved(Rectangle bounds);

    /**
     * ���T�C�Y���̃��o�[�o���h��`�悵�܂��D
     */
    void drawResizing(Rectangle bounds);

    /**
     * ���T�C�Y��̃��o�[�o���h��`�悵�܂��D
     */
    void drawResized(Rectangle bounds);

    /** */
    int getMode(Component component, Point point);

    /** */
    Cursor getCursor(int mode);
}

/* */
