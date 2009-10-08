/*
 * Copyright (c) 2001 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.swing.event;

import java.io.Serializable;

import javax.swing.event.EventListenerList;


/**
 * Editor �@�\�̂̊�{�����N���X�ł��D
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 010820 nsano initial version <br>
 *          0.10 020503 nsano use EventListenerList <br>
 *          0.11 020503 nsano repackage <br>
 *          0.12 020510 nsano repackage <br>
 *          0.20 020510 nsano deprecate open/close <br>
 */
public class EditorSupport
    implements Serializable {

    /** The editor listeners */
    private EventListenerList listenerList = new EventListenerList();

    /** EditorListener ��ǉ����܂��D */
    public void addEditorListener(EditorListener l) {
        listenerList.add(EditorListener.class, l);
    }

    /** EditorListener ���폜���܂��D */
    public void removeEditorListener(EditorListener l) {
        listenerList.remove(EditorListener.class, l);
    }

    /** �G�f�B�^���I�[�v�����ꂽ�C�x���g�𔭍s���܂��D */
//      public void fireEditorOpened(EditorEvent ev) {
//          Object[] listeners = listenerList.getListenerList();
//          for (int i = listeners.length - 2; i >= 0; i -= 2) {
//              if (listeners[i] == EditorListener.class) {
//                  ((EditorListener) listeners[i + 1]).editorOpened(ev);
//              }
//          }
//      }

    /** �G�f�B�^���A�b�v�f�[�g���ꂽ�C�x���g�𔭍s���܂��D */
    public void fireEditorUpdated(EditorEvent ev) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == EditorListener.class) {
                ((EditorListener) listeners[i + 1]).editorUpdated(ev);
            }
        }
    }

    /** �G�f�B�^���N���[�Y���ꂽ�C�x���g�𔭍s���܂��D */
//      public void fireEditorClosed(EditorEvent ev) {
//          Object[] listeners = listenerList.getListenerList();
//          for (int i = listeners.length - 2; i >= 0; i -= 2) {
//              if (listeners[i] == EditorListener.class) {
//                  ((EditorListener) listeners[i + 1]).editorClosed(ev);
//              }
//          }
//      }
}

/* */
