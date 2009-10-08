/*
 * Copyright (c) 2002 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.awt.containereditor.basic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JComponent;

import vavi.awt.rubberband.RubberBand;
import vavi.awt.rubberband.RubberBandRenderer;


/**
 * Basic ���o�[�o���h�����_���ł��D
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 010829 nsano initial version <br>
 *          0.10 020611 nsano fix <br>
 *          0.11 020613 nsano refine <br>
 */
public class BasicRubberBandRenderer implements RubberBandRenderer {

    /** */
    private Container container;

    /** */
    public BasicRubberBandRenderer(Container container) {
        this.container = container;
    }

    /** ���T�C�Y�̕� */
    private Insets resizingInsets = new Insets(5, 5, 5, 5);

    /** �J�[�\���̃e�[�u�� TODO */
    private Cursor[] cursors = {
        Cursor.getDefaultCursor(),
        Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR),  // 1
        Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR),  // 2
        Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR),
        Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR),  // 4
        Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR), // 5
        Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR), // 6
        null,
        Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR),  // 8
        Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR), // 9
        Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR)  // 10
    };

    /** ���o�[�o���h�Z���N�^ */
    private JComponent selector = new JComponent() {
        /** �Ԃ��I��p���o�[�o���h�`�� */
        public void paintComponent(Graphics g) {
            g.setColor(Color.red);
            g.drawRect(0, 0, getSize().width - 1, getSize().height - 1);
        }
    };

    /** ���o�[�o���h�Z���N�^�\���C�ǉ��C�T�C�Y�ύX�D */
    public void drawSelecting(Rectangle bounds) {
        if (selector.getParent() == null) {
            container.add(selector, 0);
            selector.setVisible(true);
        }
        selector.setBounds(bounds);
    }

    /** ���o�[�o���h�Z���N�^��\���C�폜�D */
    public void drawSelected(Rectangle bounds) {
        selector.setVisible(false);
        container.remove(selector);
    }

    /** �ړ����̃��o�[�o���h��`�悵�܂��D */
    public void drawMoving(Rectangle r) {
    }

    /** �ړ���̃��o�[�o���h��`�悵�܂��D */
    public void drawMoved(Rectangle r) {
    }

    /** ���T�C�Y���̃��o�[�o���h��`�悵�܂��D */
    public void drawResizing(Rectangle r) {
    }

    /** ���T�C�Y��̃��o�[�o���h��`�悵�܂��D */
    public void drawResized(Rectangle r) {
    }

    /**
     * �}�E�X�̈ʒu�ɂ���ă��T�C�Y���ړ������擾���܂��D
     */
    public int getMode(Component component, Point point) {
        int mode;

        int x = point.x;
        int y = point.y;

        Insets ri = resizingInsets;

        int minX = ri.left;
        int maxX = -ri.right + component.getSize().width;
        int minY = ri.top;
        int maxY = -ri.bottom + component.getSize().height;

        if (x < minX && y < minY) { // ����Ƀ��T�C�Y
            mode = RubberBand.RESIZE_NW;
        } else if (x < minX && y > maxY) { // �����Ƀ��T�C�Y
            mode = RubberBand.RESIZE_SW;
        } else if (x > maxX && y < minY) { // �E��Ƀ��T�C�Y
            mode = RubberBand.RESIZE_NE;
        } else if (x > maxX && y > maxY) { // �E���Ƀ��T�C�Y
            mode = RubberBand.RESIZE_SE;
        } else if ((x > minX && x < maxX) && y < minY) { // ��Ƀ��T�C�Y
            mode = RubberBand.RESIZE_N;
        } else if ((x > minX && x < maxX) && y > maxY) { // ���Ƀ��T�C�Y
            mode = RubberBand.RESIZE_S;
        } else if (x < minX && (y > minY && y < maxY)) { // ���Ƀ��T�C�Y
            mode = RubberBand.RESIZE_W;
        } else if (x > maxX && (y > minY && y < maxY)) { // �E�Ƀ��T�C�Y
            mode = RubberBand.RESIZE_E;
        } else { // �ړ����[�h
            mode = RubberBand.MOVE_MODE;
        }

        return mode;
    }

    /**
     * @see RubberBand#getMode()
     * @param mode
     */
    public Cursor getCursor(int mode) {
        return cursors[mode];
    }
}

/* */
