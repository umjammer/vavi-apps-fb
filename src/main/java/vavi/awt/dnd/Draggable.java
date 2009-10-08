/*
 * Copyright (c) 2001 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.awt.dnd;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.InvalidDnDOperationException;

import vavi.util.Debug;


/**
 * �h���b�O�A���h�h���b�v���ł���N���X�D
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 010820 nsano initial version <br>
 *          0.10 010910 nsano fix #setDragAction <br>
 */
public abstract class Draggable {

    /** The drag source */
    private DragSource dragSource;
    /** The drag gesture listener */
    private DragGestureListener dgListener;
    /** the drag source listener */
    private DragSourceListener dsListener;
    /** the drag gesture recognizer */
    private DragGestureRecognizer dsRecognizer;
    /** The data */
    protected Object data;
    /** The image for dragging */
    protected Image image;
    /** The component to drag */
    @SuppressWarnings("unused")
    private Component source;
    /** The point for cursor */
    private static final Point point0 = new Point(0, 0);

    /**
     * �h���b�O����� Transferable ��Ԃ��������I�[�o���C�h���ď����Ă��������D
     * �h���b�O���Ȃ��ꍇ�� null ��Ԃ��悤�ɂ��Ă��������D
     * ���̃��\�b�h�̓h���b�O�J�n�̏��������Ƃ��Ďg�p�ł��܂��D
     * TODO �h���b�O�J�n�̏��������Ƃ��Ă̓l�[�~���O������
     * @see #dragDropEnd
     */
    protected abstract Transferable getTransferable(DragGestureEvent ev);

    /**
     * �h���b�O�A���h�h���b�v�̏I���������I�[�o���C�h���ď����Ă��������D
     */
    protected abstract void dragDropEnd(DragSourceEvent ev);

    /**
     * �󂯕t����h���b�O�A�N�V�������Z�b�g���܂��D
     */
    public void setDragAction(int dragAction) {
//      this.dragAction = dragAction;
        dsRecognizer.setSourceActions(dragAction);
    }

    /**
     * �h���b�O���̃C���[�W���Z�b�g���܂��D
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * �h���b�O����鏈�������������܂��D
     * ���̃R���|�[�l���g�� Draggable ��ݒ肷��ۂ�
     * data �ɃR���|�[�l���g�̃f�[�^���w�肵���ق����y�ł��D
     * ����ȊO (JTree ��) �� data �� null �ɂ��Ă����܂��D
     * @param	source	�h���b�O�����R���|�[�l���g
     * @param	data	the real data
     */
    public Draggable(Component source, Object data) {

    	this.source = source; 
    
    	this.data = data;
    
    	dragSource = DragSource.getDefaultDragSource();
    	dgListener = new DGListener();
    	dsListener = new DSListener();
    
Debug.println("image: " + DragSource.isDragImageSupported());
    	// component, action, listener
    	dsRecognizer = dragSource.createDefaultDragGestureRecognizer(
    	    source,
    	    DnDConstants.ACTION_MOVE,
    	    dgListener); 
    }

    /** */
    private Cursor getCursor(int dragAction) {
    	if ((dragAction & DnDConstants.ACTION_COPY) != 0) {
    	    return DragSource.DefaultCopyDrop;
//return Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR);
        } else if ((dragAction & DnDConstants.ACTION_MOVE) != 0) {
//	    return DragSource.DefaultMoveDrop;
return Toolkit.getDefaultToolkit().createCustomCursor(image, point0, "my cursor");
        } else if ((dragAction & DnDConstants.ACTION_LINK) != 0) {
    	    return DragSource.DefaultLinkDrop;
        } else {
    	    return DragSource.DefaultCopyNoDrop;
        }
    }


    /**
     * DragGestureListener
     * a listener that will start the drag.
     * has access to top level's dsListener and dragSource
     * @see java.awt.dnd.DragGestureListener
     * @see java.awt.dnd.DragSource
     * @see java.awt.datatransfer.StringSelection      
     */
    private class DGListener implements DragGestureListener {

    	/**
    	 * Start the drag if the operation is ok.
    	 * @param	ev	the event object
    	 */
    	public void dragGestureRecognized(DragGestureEvent ev) {

Debug.println("-------------------------");
Debug.println("accept action: " + dsRecognizer.getSourceActions() + ": " +
((dsRecognizer.getSourceActions()&DnDConstants.ACTION_COPY)!=0?"copy":"")+
((dsRecognizer.getSourceActions()&DnDConstants.ACTION_MOVE)!=0?", move":"")+
((dsRecognizer.getSourceActions()&DnDConstants.ACTION_LINK)!=0?", link":""));
Debug.println("my action: " + ev.getDragAction() + ": " +
((ev.getDragAction() & DnDConstants.ACTION_COPY) != 0 ? "copy" : "") +
((ev.getDragAction() & DnDConstants.ACTION_MOVE) != 0 ? "move" : "") +
((ev.getDragAction() & DnDConstants.ACTION_LINK) != 0 ? "link" : ""));
    	    // if the action is ok we go ahead
    	    // otherwise we punt
    	    if ((ev.getDragAction() & dsRecognizer.getSourceActions()) == 0) {
    	        return;
            }
          
    	    Transferable transferable = getTransferable(ev);
    	    if (transferable == null) {
    	        return;
            }
    
    	    // now kick off the drag
    	    try {
        		if (image != null) {
        		    ev.startDrag(DragSource.DefaultCopyNoDrop,
                				 image,
                				 point0,
                				 transferable,
                				 dsListener);
        		} else {
        		    ev.startDrag(DragSource.DefaultCopyNoDrop,
                				 transferable,
                				 dsListener);
        		}
    	    } catch (InvalidDnDOperationException e) {
Debug.printStackTrace(e);
    	    }
    	}
    }

    /**
     * DragSourceListener
     * a listener that will track the state of the DnD operation
     * 
     * @see java.awt.dnd.DragSourceListener
     * @see java.awt.dnd.DragSource
     * @see java.awt.datatransfer.StringSelection      
     */
    private class DSListener implements DragSourceListener {

    	/**
    	 * @param	ev	the event
    	 */
    	public void dragDropEnd(DragSourceDropEvent ev) {
//	        if (ev.getDropSuccess() == false) {
//		        return;
//	        }

    	    /*
    	     * the dropAction should be what the drop target specified
    	     * in acceptDrop
    	     */
//Debug.println(Debug.DEBUG, "action: " + ev.getDropAction());
    	    Draggable.this.dragDropEnd(ev);
    	}
	    
    	/**
    	 * �h���b�O��ԂɂȂ����Ƃ��ɌĂ΂�܂��D
    	 * @param	ev	the event
    	 */
    	public void dragEnter(DragSourceDragEvent ev) {
//Debug.println(Debug.DEBUG, ev);
    	    DragSourceContext context = ev.getDragSourceContext();
    	    // intersection of the users selected action,
    	    // and the source and target actions
Debug.println("my action: " + ev.getDropAction() + ": " +
((ev.getDropAction() & DnDConstants.ACTION_COPY) != 0 ? "copy" : "") +
((ev.getDropAction() & DnDConstants.ACTION_MOVE) != 0 ? "move" : "") +
((ev.getDropAction() & DnDConstants.ACTION_LINK) != 0 ? "link" : ""));
//	        if ((myaction & Draggable.this.dragAction) != 0) {    
//		        context.setCursor(DragSource.DefaultCopyDrop);
		        // �J�[�\����ύX���܂��D
		        context.setCursor(getCursor(ev.getDropAction()));
//	        } else {
//		        context.setCursor(DragSource.DefaultCopyNoDrop);
//	        }
    	}

    	/**
    	 * @param	ev	the event
    	 */
    	public void dragOver(DragSourceDragEvent ev) {
//		    DragSourceContext context = ev.getDragSourceContext();
//		    int sa = context.getSourceActions();
//		    int ua = ev.getUserAction();
//		    int da = ev.getDropAction();
//		    int ta = ev.getTargetActions();
//Debug.println(Debug.DEBUG, "dl dragOver source actions: " + sa);
//Debug.println(Debug.DEBUG, "user action: " + ua);
//Debug.println(Debug.DEBUG, "drop actions: " + da);
//Debug.println(Debug.DEBUG, "target actions: " + ta);      
    	}

    	/**
    	 * @param	ev	the event
    	 */
    	public void dragExit(DragSourceEvent ev) {
//Debug.println(Debug.DEBUG, "exit: " + ev);
//	        DragSourceContext context = ev.getDragSourceContext();
    	}

    	/**
    	 * �h���b�v�A�N�V�������ύX���ꂽ�Ƃ��ɌĂ΂�܂��D
    	 * @param	ev	the event     
    	 */
    	public void dropActionChanged(DragSourceDragEvent ev) {
    	    DragSourceContext context = ev.getDragSourceContext();
Debug.println("my action: " + ev.getUserAction() + ": " +
((ev.getUserAction() & DnDConstants.ACTION_COPY) != 0 ? "copy" : "") +
((ev.getUserAction() & DnDConstants.ACTION_MOVE) != 0 ? "move" : "") +
((ev.getUserAction() & DnDConstants.ACTION_LINK) != 0 ? "link" : ""));
    	    // �J�[�\����ύX���܂��D
    	    // TODO �Ȃ� action = 0 �ɂȂ邼�H
    	    context.setCursor(getCursor(ev.getUserAction()));
    	}
    }
}

/* */
