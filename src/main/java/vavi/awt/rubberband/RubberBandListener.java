/*
 * Copyright (c) 2001 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.awt.rubberband;

import java.util.EventListener;


/**
 * RubberBand �̃��X�i�[�C���^�[�t�F�[�X�ł��D
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 010904 nsano initial version <br>
 */
public interface RubberBandListener extends EventListener {

    /**
     * �I�𒆂ɌĂ΂�܂��D
     */
    void selecting(RubberBandEvent ev);

    /**
     * �I���m�莞�ɌĂ΂�܂��D
     */
    void selected(RubberBandEvent ev);

    /**
     * �I��Ώۂ̈ړ����ɌĂ΂�܂��D
     */
    void moving(RubberBandEvent ev);

    /**
     * �I��Ώۂ̈ړ��m�莞�ɌĂ΂�܂��D
     */
    void moved(RubberBandEvent ev);

    /**
     * �I��Ώۂ̃��T�C�Y���ɌĂ΂�܂��D
     */
    void resizing(RubberBandEvent ev);

    /**
     * �I��Ώۂ̃��T�C�Y�m�莞�ɌĂ΂�܂��D
     */
    void resized(RubberBandEvent ev);
}

/* */
