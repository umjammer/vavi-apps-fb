/*
 * Copyright (c) 2001 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.awt.rubberband;

import java.io.Serializable;

import javax.swing.event.EventListenerList;


/**
 * RubberBand �C�x���g�@�\�̃��[�e�B���e�B�ł��D
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 010904 nsano initial version <br>
 *          0.10 010904 nsano fix specifications??? <br>
 *          0.20 020503 nsano use EventListenerList <br>
 */
public class RubberBandSupport
    implements Serializable {

    /** RubberBand �̃��X�i�[ */
    private EventListenerList listenerList = new EventListenerList();

    /**
     * RubberBand ���X�i�[��ǉ����܂��D
     * @param l RubberBandListener
     */
    public void addRubberBandListener(RubberBandListener l) {
        listenerList.add(RubberBandListener.class, l);
    }

    /**
     * RubberBand ���X�i�[���폜���܂��D
     * @param l RubberBandListener
     */
    public void removeRubberBandListener(RubberBandListener l) {
        listenerList.remove(RubberBandListener.class, l);
    }

    /**
     * �I�𒆂̃C�x���g�𔭍s���܂��D
     */
    public void fireSelecting(RubberBandEvent ev) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == RubberBandListener.class) {
                ((RubberBandListener) listeners[i + 1]).selecting(ev);
            }
        }
    }

    /**
     * �I���m��̃C�x���g�𔭍s���܂��D
     */
    public void fireSelected(RubberBandEvent ev) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == RubberBandListener.class) {
                ((RubberBandListener) listeners[i + 1]).selected(ev);
            }
        }
    }

    /**
     * �I��Ώۂ��ړ����̃C�x���g�𔭍s���܂��D
     */
    public void fireMoving(RubberBandEvent ev) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == RubberBandListener.class) {
                ((RubberBandListener) listeners[i + 1]).moving(ev);
            }
        }
    }

    /**
     * �I��Ώۂ��ړ��m��̃C�x���g�𔭍s���܂��D
     */
    public void fireMoved(RubberBandEvent ev) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == RubberBandListener.class) {
                ((RubberBandListener) listeners[i + 1]).moved(ev);
            }
        }
    }

    /**
     * �I��Ώۂ����T�C�Y���̃C�x���g�𔭍s���܂��D
     */
    public void fireResizing(RubberBandEvent ev) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == RubberBandListener.class) {
                ((RubberBandListener) listeners[i + 1]).resizing(ev);
            }
        }
    }

    /**
     * �I��Ώۂ����T�C�Y�m��̃C�x���g�𔭍s���܂��D
     */
    public void fireResized(RubberBandEvent ev) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == RubberBandListener.class) {
                ((RubberBandListener) listeners[i + 1]).resized(ev);
            }
        }
    }
}

/* */
