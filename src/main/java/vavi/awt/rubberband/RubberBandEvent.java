/*
 * Copyright (c) 2001 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.awt.rubberband;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.EventObject;


/**
 * RubberBand ���ύX����鎞���s����C�x���g�̃N���X�ł��D
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 010904 nsano initial version <br>
 *          0.10 010904 nsano fix specifications??? <br>
 *          0.20 020604 nsano fix specifications??? <br>
 *          0.21 020605 nsano fix specifications <br>
 */
public class RubberBandEvent extends EventObject {

    /** �R���|�[�l���g�̑��� location */
    private Point location;
    /** �R���|�[�l���g�̑��� size */
//  private Dimension size;
    /** �I�����ꂽ�̈� */
    private Rectangle bounds;

    /**
     * RubberBand �C�x���g���\�z���܂��D
     * @see	RubberBandListener#selecting
     * @see	RubberBandListener#selected
     */
    public RubberBandEvent(Object source, Rectangle bounds) {
        super(source);
        this.bounds = bounds;
    }

    /**
     * RubberBand �C�x���g���\�z���܂��D
     * @see	RubberBandListener#moving
     * @see	RubberBandListener#moved
     * @param	location �R���|�[�l���g�̑��� location
     */
    public RubberBandEvent(Object source, Point location) {
        super(source);
        this.location = location;
    }

    /**
     * RubberBand �C�x���g���\�z���܂��D
     * @see	RubberBandListener#resizing
     * @see	RubberBandListener#resized
     * @param	size �R���|�[�l���g�̑��� size
     */
//  public RubberBandEvent(Object source, Dimension size) {
//      super(source);
//      this.size = size;
//  }

    /** �I�����ꂽ�̈���擾���܂��D */
    public Rectangle getBounds() {
        return bounds;
    }

    /** �R���|�[�l���g�̑��� location ���擾���܂��D */
    public Point getLocation() {
        return location;
    }

    /** �R���|�[�l���g�̑��� size ���擾���܂��D */
//  public Dimension getDimension() {
//      return size;
//  }
}

/* */
