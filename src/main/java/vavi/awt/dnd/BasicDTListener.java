/*
 * Copyright (c) 2001 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.awt.dnd;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.util.logging.Level;

import vavi.util.Debug;


/**
 * The basic DTListener a listener that tracks the state of the operation.
 *
 * @see java.awt.dnd.DropTargetListener
 * @see java.awt.dnd.DropTarget
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (nsano)
 * @version 0.00 010820 nsano initial version <br>
 */
public abstract class BasicDTListener implements DropTargetListener {

    /** The drag action */
    protected int dragAction = DnDConstants.ACTION_MOVE;

    /**
     * Called by isDragOk. Checks to see if the flavor drag flavor is
     * acceptable.
     *
     * @param ev the DropTargetDragEvent object
     * @return whether the flavor is acceptable
     */
    protected abstract boolean isDragFlavorSupported(DropTargetDragEvent ev);

    /**
     * Called by drop. Checks the flavors and operations.
     *
     * @param ev the DropTargetDropEvent object
     * @return the chosen DataFlavor or null if none match
     */
    protected abstract DataFlavor chooseDropFlavor(DropTargetDropEvent ev);

    /**
     * Called by dragEnter and dragOver. Checks the flavors and operations.
     *
     * @param ev the event object
     * @return whether the flavor and operation is ok
     */
    private boolean isDragOk(DropTargetDragEvent ev) {

        if (isDragFlavorSupported(ev) == false) {
            Debug.println(Level.WARNING, "no flavors chosen");
            return false;
        }

        // the actions specified when the source
        // created the DragGestureRecognizer
        // int sa = ev.getSourceActions();

        // the docs on DropTargetDragEvent rejectDrag says that
        // the dropAction should be examined
        int da = ev.getDropAction();
// Debug.println("drop action: " + da + " my acceptable actions " + dragAction);

        // we're saying that these actions are necessary
        if ((da & dragAction) == 0)
            return false;

        return true;
    }

    /**
     * start "drag under" feedback on component invoke acceptDrag or rejectDrag
     * based on isDragOk
     * オーバーライドするときは super.dragEnter(ev) を忘れずに．
     */
    public void dragEnter(DropTargetDragEvent ev) {
// Debug.println(ev);
        if (isDragOk(ev) == false) {
            Debug.println(Level.WARNING, "not ok");
            ev.rejectDrag();
            return;
        }
// Debug.println("accepting: " + ev.getDropAction());
        ev.acceptDrag(ev.getDropAction());
    }

    /**
     * continue "drag under" feedback on component invoke acceptDrag or
     * rejectDrag based on isDragOk
     * オーバーライドするときは super.dragOver(ev) を忘れずに．
     */
    public void dragOver(DropTargetDragEvent ev) {

        if (isDragOk(ev) == false) {
            Debug.println(Level.WARNING, "not ok");
            ev.rejectDrag();
            return;
        }
// Debug.println("accepting");
        ev.acceptDrag(ev.getDropAction());
    }

    /**
     * オーバーライドするときは super.dropActionChanged(ev) を忘れずに．
     */
    public void dropActionChanged(DropTargetDragEvent ev) {

        if (isDragOk(ev) == false) {
            Debug.println(Level.WARNING, "not ok");
            ev.rejectDrag();
            return;
        }
// Debug.println("accepting: " + ev.getDropAction());
        ev.acceptDrag(ev.getDropAction());
    }

    /**
     * ドラッグ動作が終了したときに呼ばれます．
     * オーバーライドするときは super.dragExit(ev) 特にしなくていいです．
     */
    public void dragExit(DropTargetEvent ev) {
        // Debug.println(ev);
    }

    /**
     * perform action from getSourceActions on the transferable invoke
     * acceptDrop or rejectDrop invoke dropComplete if its a local (same JVM)
     * transfer, use StringTransferable.localStringFlavor find a match for the
     * flavor check the operation get the transferable according to the chosen
     * flavor do the transfer
     */
    public void drop(DropTargetDropEvent ev) {
// Debug.println(ev);

        DataFlavor chosen = chooseDropFlavor(ev);
        if (chosen == null) {
            Debug.println(Level.WARNING, "No flavor match found");
            ev.rejectDrop();
            return;
        }
// Debug.println("data flavor is " + chosen.getMimeType());

        // the actual operation
        // int da = ev.getDropAction();
        // the actions that the source has specified with
        // DragGestureRecognizer
        int sa = ev.getSourceActions();
// Debug.println("sourceActions: " + sa);
// Debug.println("dropAction: " + da);

        if ((sa & dragAction) == 0) {
            Debug.println(Level.WARNING, "No action match found");
            ev.rejectDrop();
            return;
        }

        Object data = null;
        try {
            /*
             * the source listener receives this action in dragDropEnd. if the
             * action is DnDConstants.ACTION_COPY_OR_MOVE then the source
             * receives MOVE!
             */
            ev.acceptDrop(dragAction);

            data = ev.getTransferable().getTransferData(chosen);
            if (data == null)
                throw new NullPointerException("transfered data is null");
        } catch (Throwable e) { // TODO
Debug.println(Level.SEVERE, e);
Debug.printStackTrace(e);
            ev.dropComplete(false);
            return;
        }

        ev.dropComplete(dropImpl(ev, data));
    }

    /**
     * You need to implement here dropping procedure.
     *
     * @param ev
     * @param data 便宜的なドロップされたデータです
     * @return ドロップが成功したかどうか
     */
    protected abstract boolean dropImpl(DropTargetDropEvent ev, Object data);
}

/* */
