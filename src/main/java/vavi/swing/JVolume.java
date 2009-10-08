/*
 * Copyright (c) 2001 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.io.Serializable;

import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;


/**
 * �{�����[��
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 010919 nsano initial version <br>
 *          0.01 020516 nsano use paintComponent <br>
 */
public class JVolume extends JComponent {

    /**
     * The data model that handles the numeric maximum value,
     * minimum value, and current-position value for the volume.
     */
    protected BoundedRangeModel volumeModel;

    /*
     * @see #addChangeListener
     * @see #createChangeListener
     */
    protected ChangeListener changeListener = createChangeListener();

    /**
     * @see #fireStateChanged
     */
    protected transient ChangeEvent changeEvent = null;

    /**
     * �͈͂� 0 �` 100�A�����l�� 50 �̃{�����[�����쐬���܂��B
     */
    public JVolume() {
        this(0, 100, 50);
    }

    /**
     * �ŏ��l�ƍő�l���w�肵�āA�����l�� 50 �̃{�����[�����쐬���܂��B
     */
    public JVolume(int min, int max) {
        this(min, max, (min + max) / 2);
    }

    /**
     * �ŏ��l�A�ő�l�A����я����l���w�肵�āA�{�����[�����쐬���܂��B
     * @see #setMinimum
     * @see #setMaximum
     * @see #setValue
     */
    public JVolume(int min, int max, int value) {
        volumeModel = new DefaultBoundedRangeModel(value, 0, min, max);
        volumeModel.addChangeListener(changeListener);
        
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }

    /**
     * Creates a volume using the specified
     * BoundedRangeModel.
     */
    public JVolume(BoundedRangeModel brm) {
        setModel(brm);
        volumeModel.addChangeListener(changeListener);
        
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }

    /**
     * We pass Change events along to the listeners with the
     * the volume (instead of the model itself) as the event source.
     */
    private class ModelListener implements ChangeListener, Serializable {
        public void stateChanged(ChangeEvent e) {
            fireStateChanged();
        }
    }

    /**
     * Subclasses that want to handle model ChangeEvents differently
     * can override this method to return their own ChangeListener
     * implementation.  The default ChangeListener just forwards
     * ChangeEvents to the ChangeListeners added directly to the volume.
     *
     * @see #fireStateChanged
     */
    protected ChangeListener createChangeListener() {
        return new ModelListener();
    }

    /**
     * Adds a ChangeListener to the volume.
     *
     * @param l the ChangeListener to add
     * @see #fireStateChanged
     * @see #removeChangeListener
     */
    public void addChangeListener(ChangeListener l) {
        listenerList.add(ChangeListener.class, l);
    }

    /**
     * Removes a ChangeListener from the volume.
     *
     * @param l the ChangeListener to remove
     * @see #fireStateChanged
     * @see #addChangeListener
     */
    public void removeChangeListener(ChangeListener l) {
        listenerList.remove(ChangeListener.class, l);
    }

    /**
     * Returns an array of all the <code>ChangeListener</code>s added
     * to this JVolume with addChangeListener().
     *
     * @return all of the <code>ChangeListener</code>s added or an empty
     *         array if no listeners have been added
     * @since 1.4
     */
    public ChangeListener[] getChangeListeners() {
	return listenerList.getListeners(
	    ChangeListener.class);
    }

    /**
     * Send a ChangeEvent, whose source is this Volume, to
     * each listener.  This method method is called each time
     * a ChangeEvent is received from the model.
     *
     * @see #addChangeListener
     * @see EventListenerList
     */
    protected void fireStateChanged() {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ChangeListener.class) {
                if (changeEvent == null) {
                    changeEvent = new ChangeEvent(this);
                }
                ((ChangeListener) listeners[i+1]).stateChanged(changeEvent);
            }
        }
    }

    /**
     * Returns data model that handles the volumes three
     * fundamental properties: minimum, maximum, value.
     *
     * @see #setModel
     */
    public BoundedRangeModel getModel() {
        return volumeModel;
    }

    /**
     * Sets the model that handles the volumes three
     * fundamental properties: minimum, maximum, value.
     *
     * @see #getModel
     * @beaninfo
     *       bound: true
     * description: The volumes BoundedRangeModel.
     */
    public void setModel(BoundedRangeModel newModel) {
        BoundedRangeModel oldModel = getModel();

        if (oldModel != null) {
            oldModel.removeChangeListener(changeListener);
        }

        volumeModel = newModel;

        if (newModel != null) {
            newModel.addChangeListener(changeListener);
        }

        firePropertyChange("model", oldModel, volumeModel);
    }

    /** �l���擾���܂��D */
    public int getValue() {
	return getModel().getValue();
    }

    /** �l��ݒ肵�܂��D */
    public void setValue(int value) {
        BoundedRangeModel m = getModel();
        int oldValue = m.getValue();
        if (oldValue == value) {
            return;
        }
        m.setValue(value);
    }

    /** �ŏ��l���擾���܂��D */
    public int getMinimum() {
	return getModel().getMinimum();
    }

    /** �ŏ��l��ݒ肵�܂��D */
    public void setMinimum(int min) {
        int oldMin = getModel().getMinimum();
        getModel().setMinimum(min);
        firePropertyChange("minimum", new Integer(oldMin), new Integer(min));
    }

    /** �ő�l���擾���܂��D */
    public int getMaximum() {
	return getModel().getMaximum();
    }

    /** �ő�l��ݒ肵�܂��D */
    public void setMaximum(int max) {
        int oldMax = getModel().getMaximum();
        getModel().setMaximum(max);
        firePropertyChange("maximum", new Integer(oldMax), new Integer(max));
    }

    /**
     * True if the volume knob is being dragged.
     *
     * @return the value of the models valueIsAdjusting property
     * @see #setValueIsAdjusting
     */
    public boolean getValueIsAdjusting() {
        return getModel().getValueIsAdjusting();
    }

    /**
     * Sets the models valueIsAdjusting property.  Volume look and
     * feel implementations should set this property to true when
     * a knob drag begins, and to false when the drag ends.  The
     * volume model will not generate ChangeEvents while
     * valueIsAdjusting is true.
     *
     * @see #getValueIsAdjusting
     * @see BoundedRangeModel#setValueIsAdjusting
     * @beaninfo
     *      expert: true
     * description: True if the volume knob is being dragged.
     */
    public void setValueIsAdjusting(boolean b) {
        BoundedRangeModel m = getModel();
//      boolean oldValue = m.getValueIsAdjusting();
        m.setValueIsAdjusting(b);
    }

    /**
     * Returns the "extent" -- the range of values "covered" by the knob.
     * @return an int representing the extent
     * @see #setExtent
     * @see BoundedRangeModel#getExtent
     */
    public int getExtent() {
        return getModel().getExtent();
    }

    /**
     * Sets the size of the range "covered" by the knob.  Most look
     * and feel implementations will change the value by this amount
     * if the user clicks on either side of the knob.
     *
     * @see #getExtent
     * @see BoundedRangeModel#setExtent
     * @beaninfo
     *      expert: true
     * description: Size of the range covered by the knob.
     */
    public void setExtent(int extent) {
        getModel().setExtent(extent);
    }

    //---- UI -----------------------------------------------------------------

    /** �}�E�X�� x ���W */
    private transient int x;
    /** �}�E�X�� y ���W */
    private transient int y;

    /** �}�E�X���X�i */
    private MouseInputListener mouseListener = new MouseInputAdapter() {
        /** �}�E�X�������ꂽ�Ƃ��ɌĂ΂�܂��D*/
        public void mousePressed(MouseEvent ev) {
            setValueIsAdjusting(true);
//Debug.println(ev.getX() + ", " + ev.getY());
        }
        /** �}�E�X���h���b�O�����Ƃ��ɌĂ΂�܂��D */
        public void mouseDragged(MouseEvent ev) {
            if (getValueIsAdjusting()) {
                x = ev.getX() - getWidth() / 2;
                y = getHeight() / 2 - ev.getY();
                repaint();
//Debug.println(x + ", " + y);
            }
        }
        /** �}�E�X�������ꂽ�Ƃ��ɌĂ΂�܂��D */
        public void mouseReleased(MouseEvent ev) {
            if (getValueIsAdjusting()) {
                x = ev.getX() - getWidth() / 2;
                y = getHeight() / 2 - ev.getY();
                setValueIsAdjusting(false);
//Debug.println(x + ", " + y);
                double theta = StrictMath.atan2(x, y);
                if (theta < 0) theta = 2 * Math.PI + theta; 
                setValue((int) (getMinimum() +
                        theta / (2 * Math.PI) *
                        (getMaximum() - getMinimum())));
//Debug.println(getValue());
                repaint();
            }
        }
    };

    /** �{�����[���m�u�̕� */
    private double r = 0.4;

    /** �{�����[����`�悵�܂��D */
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setStroke(new BasicStroke(2.0f));
        
        RenderingHints qualityHints =
            new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                               RenderingHints.VALUE_ANTIALIAS_ON);
        
        qualityHints.put(RenderingHints.KEY_RENDERING,
                         RenderingHints.VALUE_RENDER_QUALITY);
        
        g2.setRenderingHints(qualityHints);
        
        int w = getWidth();
        int h = getHeight();

//      if (!isOpaque()) {
//          g.setColor(getBackground());
//          g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
//      }

        g.setColor(Color.white);
        g2.draw(new Arc2D.Double(0, 0, w, h, 90, 90, Arc2D.OPEN));
        
        g.setColor(Color.black);
        g2.draw(new Arc2D.Double(0, 0, w, h, 270, 90, Arc2D.OPEN));
        
//      g.setColor(UIManager.getColor("control"));
        g.setColor(new Color(0xcc, 0xcc, 0xff));
        g.fillOval(0, 0, w, h);
        
        double X;
        double Y;
        
        double W = w / 2d;
        double H = h / 2d;
        // �{�����[���m�u�̈ʒu
        double a = W * (1 - r / 2 * 1.2);
        double b = H * (1 - r / 2 * 1.2);

        if (getValueIsAdjusting()) {
//Debug.println("(x, y) = (" + x + ", " + y + ")");
            // X, Y �͐��K���W
            X = Math.sqrt((a * a * b * b * x * x) / (b * b * x * x + a * a * y * y));
            Y = Math.sqrt((a * a * b * b * y * y) / (b * b * x * x + a * a * y * y));
            
            if (x < 0) X *= -1;
            if (y < 0) Y *= -1;

//Debug.println("(X, Y) = (" + X + ", " + Y + ")");
            // Java ���W�ւ̕ϊ�
            X = W + X;
            Y = H - Y;
        } else {
            // TODO theta �̍��W�͐������Ȃ�
            double theta = (double) (getValue() - getMinimum()) / (double) (getMaximum() - getMinimum()) * (Math.PI * 2) - Math.PI / 2;
// Debug.println(getValue());
//Debug.println(theta);
            // X, Y �͐��K���W
            X = Math.sqrt((a * a * b * b) / (b * b + a * a * Math.pow(Math.tan(theta), 2)));
            Y = Math.sqrt((a * a * b * b) / (b * b / Math.pow(Math.tan(theta), 2) + a * a));
            
            // TODO �������Ȃ� theta �̍��W�ɑ΂��Ă̕␳
            if (theta > Math.PI / 2 && theta < Math.PI * 1.5) X *= -1;
            if (theta < Math.PI     && theta > 0)             Y *= -1;

//Debug.println("(X, Y) = (" + X + ", " + Y + ")");
            // Java ���W�ւ̕ϊ�
            X = W + X;
            Y = H - Y;
        }
        
        // �m�u�̔��a
        double R = Math.min(W, H) * r / 2;
        
        int nx = (int) (X - R);
        int ny = (int) (Y - R);
        int nr = (int) (2 * R);
        
        g.setColor(Color.black);
        g2.draw(new Arc2D.Double(nx, ny, nr, nr, 90, 90, Arc2D.OPEN));
        
        g.setColor(Color.white);
        g2.draw(new Arc2D.Double(nx, ny, nr, nr, 270, 90, Arc2D.OPEN));
        
        g.setColor(new Color(0x99, 0x99, 0xcc));
//      g.setColor(Color.pink);
        g.fillOval(nx, ny, nr, nr);
//Debug.println(X + ", " + Y);
    }

    //-------------------------------------------------------------------------

    /** ���̃N���X���e�X�g���܂��D */
    public static void main(String[] args) {
        JFrame frame = new JFrame("JVolume Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JVolume volume = new JVolume();
        volume.setPreferredSize(new Dimension(120, 100));
        frame.getContentPane().add(volume);
        frame.pack();
        frame.setVisible(true);

//        Enumeration e = UIManager.getDefaults().keys();
//        while (e.hasMoreElements()) {
//            Object o = e.nextElement();
//            System.err.println(o + "=" + UIManager.getDefaults().get(o));
//        }
    }
}

/* */
