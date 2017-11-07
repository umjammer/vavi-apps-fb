/*
 * Copyright (c) 2002 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.swing.layout;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.beans.BeanDescriptor;
import java.beans.SimpleBeanInfo;


/**
 * GridBagLayoutInfo.
 *
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 020527 nsano initial version <br>
 */
public class GridBagLayoutInfo extends SimpleBeanInfo {

    private final Class<?> clazz = GridBagLayout.class;
    private final Class<?> customizerClass = GridBagLayoutCustomizer.class;

    /** */
    public BeanDescriptor getBeanDescriptor() {
        return new BeanDescriptor(clazz, customizerClass);
    }

    /** */
    public Image getIcon(int iconKind) {
        return loadImage("resources/gridBagLayout.gif");
    }
}

/* */
