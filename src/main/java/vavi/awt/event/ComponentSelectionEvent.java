/*
 * Copyright (c) 2002 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.awt.event;

import java.util.EventObject;


/**
 * Component が選択される時発行するイベントのクラスです．
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 020509 nsano initial version <br>
 */
public class ComponentSelectionEvent extends EventObject {

    /** */
    private Object selected;

    /**
     * ComponentSelection イベントを構築します．
     */
    public ComponentSelectionEvent(Object source, Object selected) {
        super(source);

        this.selected = selected;
    }

    /** */
    public Object getSelected() {
        return selected;
    }
}

/* */
