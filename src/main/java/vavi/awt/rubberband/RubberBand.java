/*
 * Copyright (c) 2001 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.awt.rubberband;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.logging.Level;

import vavi.util.Debug;


/**
 * ���o�[�o���h�̃��f���ł��D
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 010829 nsano initial version <br>
 *          0.10 010903 nsano repackage <br>
 *          0.10 010904 nsano fix specifications??? <br>
 *          0.20 020509 nsano separate RubberBundSupport <br>
 *          0.30 020603 nsano make simple <br>
 *          0.21 020605 nsano fix specifications <br>
 */
public class RubberBand {

    /** ��Ƀ��T�C�Y���邱�Ƃ�����킷�萔 */
    public static final int RESIZE_N  = 1;
    /** ���Ƀ��T�C�Y���邱�Ƃ�����킷�萔 */
    public static final int RESIZE_S  = 2;
    /** �E�Ƀ��T�C�Y���邱�Ƃ�����킷�萔 */
    public static final int RESIZE_E  = 4;
    /** ���Ƀ��T�C�Y���邱�Ƃ�����킷�萔 */
    public static final int RESIZE_W  = 8;
    /** �E��Ƀ��T�C�Y���邱�Ƃ�����킷�萔 */
    public static final int RESIZE_NE = 5;
    /** ����Ƀ��T�C�Y���邱�Ƃ�����킷�萔 */
    public static final int RESIZE_NW = 9;
    /** �E���Ƀ��T�C�Y���邱�Ƃ�����킷�萔 */
    public static final int RESIZE_SE = 6;
    /** �����Ƀ��T�C�Y���邱�Ƃ�����킷�萔 */
    public static final int RESIZE_SW = 10;
    /** �ʏ��\���̒萔 */
    public static final int NORMAL_MODE = 0;
    /** �ړ���\���萔 */
    public static final int MOVE_MODE = 3;

    /** RubberBnad �̃��[�h */
    protected int mode;

    /** ���T�C�Y����R���|�[�l���g��ɂ�����}�E�X�������ꂽ�Ƃ��̍��W */
    protected Point resize;
    /** �ړ�����R���|�[�l���g��ɂ�����}�E�X�������ꂽ�Ƃ��̍��W */
    protected Point move;
    /** �I���Ńv���X���ꂽ�Ƃ��̍��W */
    protected Point pressed;
    /** �̈�I���Ńh���b�O���Ă�����W */
    protected Point dragged;
    /** �̈�I���Ń����[�X���ꂽ�Ƃ��̍��W */
    protected Point released;

    /** �̈�I�������Ă��邩�ǂ��� (�h���b�O�� true, �������� false) */
    private boolean isSelecting;

    /** RubberBand �������R���e�i */
//  private Container container;

    /**
     * ���o�[�o���h���\�z���܂��D
     */
    public RubberBand() {
        mode = NORMAL_MODE;
        isSelecting = false;
    }

    /**
     * ���o�[�o���h�̃��[�h��ݒ肵�܂��D
     */
    public void setMode(int mode) {
        this.mode = mode;
    }

    /** ���o�[�o���h�̃��[�h���擾���܂��D */
    public int getMode() {
        return mode;
    }

    //-------------------------------------------------------------------------

    /**
     * �I�𒆂� Bounds ��Ԃ��܂��D
     */
    protected Rectangle getSelectionBounds(Point point) {
        isSelecting = true;

        dragged = point;

        int x = Math.min(pressed.x, dragged.x);
        int y = Math.min(pressed.y, dragged.y);
        int w = Math.abs(dragged.x - pressed.x);
        int h = Math.abs(dragged.y - pressed.y);

        return new Rectangle(x, y, w, h);
    }

    /**
     * �I���m�莞�� Bounds ��Ԃ��܂��D
     */
    protected Rectangle getSelectedBounds(Point point) {

        released = point;

        int x = Math.min(pressed.x, released.x);
        int y = Math.min(pressed.y, released.y);
        int w = x + Math.abs(released.x - pressed.x);
        int h = y + Math.abs(released.y - pressed.y);

        return new Rectangle(x, y, w, h);
    }

    /**
     * ���T�C�Y���̑��� Bounds ��Ԃ��܂��D
     * TODO �傫���̐���
     */
    protected Rectangle getResizedBounds(Point point) {

        int x = 0;
        int y = 0;
        int w = 0;
        int h = 0;

        int dx = point.x - resize.x;
        int dy = point.y - resize.y;

        switch (mode) {
        case RESIZE_N:
            y += dy;
            h -= dy;
            break;
        case RESIZE_S:
            h += dy;
            break;
        case RESIZE_E:
            w += dx;
            break;
        case RESIZE_W:
            x += dx;
            w -= dx;
            break;
        case RESIZE_NE:
            y += dy;
            h -= dy;
            w += dx;
            break;
        case RESIZE_NW:
            y += dy;
            h -= dy;
            x += dx;
            w -= dx;
            break;
        case RESIZE_SE:
            h += dy;
            w += dx;
            break;
        case RESIZE_SW:
            h += dy;
            x += dx;
            w -= dx;
            break;
        default:
Debug.println(Level.SEVERE, "wrong resize mode: " + mode);
            break;
        }
        
        return new Rectangle(x, y, w, h);
    }

    /**
     * �ړ����̑��� Point ��Ԃ��܂��D
     */
    protected Point getMovedPoint(Point point) {

        int x = point.x - move.x;
        int y = point.y - move.y;

        return new Point(x, y);
    }

    //-------------------------------------------------------------------------

    /**
     * ���o�[�o���h�̊J�n�����s���܂��D
     */
    public void start(Point point) {

        switch (mode) {
        case NORMAL_MODE:
            pressed = point;
            break;
        case MOVE_MODE:
            move = point;
// Debug.println(move.x + ", " + move.y);
            break;
        default:
            resize = point;
            break;
        }
    }

    /**
     * ���o�[�o���h�̓r���o�߂����s���܂��D
     */
    public void doing(Point point) {
        switch (mode) {
        case NORMAL_MODE: { // �̈�I��
            Rectangle r = getSelectionBounds(point);
            fireSelecting(new RubberBandEvent(this, r));
            break;
        }
        case MOVE_MODE: { // �ړ����[�h
            isSelecting = false;
            Point p = getMovedPoint(point);
// Debug.println(p.x + ", " + p.y);
            fireMoving(new RubberBandEvent(this, p));
            move = point;
            break;
        }
        default: { // ���T�C�Y
            isSelecting = false;
            Rectangle r = getResizedBounds(point);
            fireResizing(new RubberBandEvent(this, r));
            resize = point;
            break;
        }}
    }

    /**
     * ���o�[�o���h�̏I�������s���܂��D
     */
    public void done(Point point) {
        switch (mode) {
        case NORMAL_MODE: { // �̈�I��
            if (isSelecting) {
                Rectangle r = getSelectedBounds(point);
                fireSelected(new RubberBandEvent(this, r));
            }
            isSelecting = false;
            break;
        }
        case MOVE_MODE: { // �ړ����[�h�̏���
            Point p = getMovedPoint(point);
            fireMoved(new RubberBandEvent(this, p));
            break;
        }
        default: { // ���T�C�Y����
            Rectangle r = getResizedBounds(point);
            fireResized(new RubberBandEvent(this, r));
            break;
        }}
    }

    //-------------------------------------------------------------------------

    /** RubberBand �C�x���g�@�\�̃T�|�[�g */
    private RubberBandSupport rbs = new RubberBandSupport();

    /** RubberBand ���X�i�[��ǉ����܂��D */
    public void addRubberBandListener(RubberBandListener l) {
        rbs.addRubberBandListener(l);
    }

    /** RubberBand ���X�i�[���폜���܂��D */
    public void removeRubberBandListener(RubberBandListener l) {
        rbs.removeRubberBandListener(l);
    }

    /** �I�𒆂̃C�x���g�𔭍s���܂��D */
    public void fireSelecting(RubberBandEvent ev) {
        rbs.fireSelecting(ev);
    }

    /** �I���m��̃C�x���g�𔭍s���܂��D */
    public void fireSelected(RubberBandEvent ev) {
        rbs.fireSelected(ev);
    }

    /** �I��Ώۂ��ړ����̃C�x���g�𔭍s���܂��D */
    public void fireMoving(RubberBandEvent ev) {
        rbs.fireMoving(ev);
    }

    /** �I��Ώۂ��ړ��m��̃C�x���g�𔭍s���܂��D */
    public void fireMoved(RubberBandEvent ev) {
        rbs.fireMoved(ev);
    }

    /** �I��Ώۂ����T�C�Y���̃C�x���g�𔭍s���܂��D */
    public void fireResizing(RubberBandEvent ev) {
        rbs.fireResizing(ev);
    }

    /** �I��Ώۂ����T�C�Y�m��̃C�x���g�𔭍s���܂��D */
    public void fireResized(RubberBandEvent ev) {
        rbs.fireResized(ev);
    }
}

/* */
