/*
 * Copyright (c) 2002 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.awt.rubberband;


/**
 * RubberBandAdapter.
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 020604 nsano initial version <br>
 */
public abstract class RubberBandAdapter implements RubberBandListener {

    /** �I�𒆂ɌĂ΂�܂��D */
    public void selecting(RubberBandEvent ev) {
    }

    /** �I���m�莞�ɌĂ΂�܂��D */
    public void selected(RubberBandEvent ev) {
    }

    /** �I��Ώۂ̈ړ����ɌĂ΂�܂��D */
    public void moving(RubberBandEvent ev) {
    }

    /** �I��Ώۂ̈ړ��m�莞�ɌĂ΂�܂��D */
    public void moved(RubberBandEvent ev) {
    }

    /** �I��Ώۂ̃��T�C�Y���ɌĂ΂�܂��D */
    public void resizing(RubberBandEvent ev) {
    }

    /** �I��Ώۂ̃��T�C�Y�m�莞�ɌĂ΂�܂��D */
    public void resized(RubberBandEvent ev) {
    }
}

/* */
