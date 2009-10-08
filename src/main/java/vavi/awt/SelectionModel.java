/*
 * Copyright (c) 2002 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.awt;

import java.util.List;
import java.util.Vector;

import vavi.awt.event.SelectionEvent;
import vavi.awt.event.SelectionListener;
import vavi.awt.event.SelectionSupport;
import vavi.util.Debug;


/**
 * SelectionModel.
 * 
 * @event SelectionEvent(Container, List<Selectable>)
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 020529 nsano initial version <br>
 */
public class SelectionModel {

    /** �I������Ă��Ă�I�u�W�F�N�g�̃��X�g */
    private List<Selectable> selected;

    /** */
    public SelectionModel() {
        selected = new Vector<Selectable>();
    }

    /**
     * �I����Ԃ� Selectable �̃x�N�^��Ԃ��܂��D
     */
    public List<Selectable> getSelected() {
        return selected;
    }

    /**
     * �w�肵�� Selectable �̃x�N�^��I����Ԃɂ��܂��D
     */
    public void setSelected(List<Selectable> selected) {
Debug.println("Warnning: be careful to use this method.");
        this.selected = selected;

        fireValueChanged(new SelectionEvent(this, selected));
    }

    /**
     * ���ׂĂ� Selectable ���I����Ԃɂ��܂��D
     */
    public void deselectAll() {
        for (int i = 0; i < selected.size(); i++) {
            selected.get(i).setSelected(false);
        }

        selected.clear();

        fireValueChanged(new SelectionEvent(this, selected));
    }

    /**
     * Selectable ��I����Ԃɂ��܂��D
     * 
     * @param selectables
     */
    public void select(Selectable[] selectables) {

        deselectAll();

        for (int i = 0; i < selectables.length; i++) {
            select(selectables[i], true);
        }
    }

    /**
     * Selectable ��I����Ԃɂ��܂��D
     * 
     * @param selectable �ΏۂƂȂ� Object
     * @param isMultiSelection �����I�����ǂ���
     */
    public void select(Selectable selectable, boolean isMultiSelection) {

        // Debug.println(selectable);
        boolean isOldSelection = false;

        for (int i = 0; i < selected.size(); i++) {
            if (selectable == selected.get(i)) {
                if (isMultiSelection) {
                    // �V�����I�����C���łɑI���ς݂̏ꍇ�C
                    // ���̑I����Ԃ���������
                    selected.get(i).setSelected(false);
                    selected.remove(i);
                    isOldSelection = true;
                }
            }
        }

        if (!isOldSelection) {
            // �V�������̂��I�����ꂽ�Ƃ�
            if (!isMultiSelection) {
                // ���ׂĂ��I����Ԃ�
                for (int i = 0; i < selected.size(); i++) {
                    selected.get(i).setSelected(false);
                }
                selected.clear();
            }
            selected.add(selectable);

            // �I������Ă��邷�ׂĂ�I����Ԃɂ���
            for (int i = 0; i < selected.size(); i++) {
                selected.get(i).setSelected(true);
            }
        }

        fireValueChanged(new SelectionEvent(this, selected));
    }

    // -------------------------------------------------------------------------

    /** SelectionEvent �@�\�̃��[�e�B���e�B */
    private SelectionSupport ss = new SelectionSupport();

    /** Selection ���X�i�[���A�^�b�`���܂��D */
    public void addSelectionListener(SelectionListener l) {
        ss.addSelectionListener(l);
    }

    /** Selection ���X�i�[�������[�u���܂��D */
    public void removeSelectionListener(SelectionListener l) {
        ss.removeSelectionListener(l);
    }

    /** */
    protected void fireValueChanged(SelectionEvent ev) {
        ss.fireValueChanged(ev);
    }
}

/* */
