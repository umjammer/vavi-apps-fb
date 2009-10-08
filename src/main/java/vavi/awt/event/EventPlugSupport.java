/*
 * Copyright (c) 2002 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.awt.event;

import java.util.EventListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * EventPlug �N���X�̃��[�e�B���e�B�ł��D
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 020511 nsano initial version <br>
 */
public class EventPlugSupport {

    /** EventPlug �Ǘ��p Hashtable */
    protected volatile Map<String, EventPlug> eventPlugs = new HashMap<String, EventPlug>();

    /**
     * EventPlug ��ǉ����܂��D
     * 
     * @param eventPlug EventPlug
     */
    public void addEventPlug(EventPlug eventPlug) {
        eventPlugs.put(eventPlug.getName(), eventPlug);
    }

    /**
     * EventPlug ���폜���܂��D
     * 
     * @param eventPlug EventPlug
     */
    public void removeEventPlug(EventPlug eventPlug) {
        eventPlugs.remove(eventPlug.getName());
    }

    /**
     * �w�肵�����O�� EventPlug ���폜���܂��D
     * 
     * @param name EventPlug �̖��O
     */
    public void removeEventPlug(String name) {
        eventPlugs.remove(name);
    }

    /**
     * �w�肵�����O�� EventPlug ���擾���܂��D
     * 
     * @param name EventPlug �̖��O
     */
    private EventPlug getEventPlug(String name) {
        return eventPlugs.get(name);
    }

    /**
     * �w�肵�����O�� EventPlug �ɃC�x���g�𔭍s����I�u�W�F�N�g��ݒ肵�܂��D
     * 
     * @param name EventPlug �̖��O
     * @param invoker �C�x���g�𔭍s����I�u�W�F�N�g
     */
    public void setInvoker(String name, Object invoker) {
        EventPlug eventPlug = getEventPlug(name);
        eventPlug.setInvoker(invoker);
    }

    /**
     * �w�肵�����O�� EventPlug �̃C�x���g�𔭍s����I�u�W�F�N�g���擾���܂��D
     * 
     * @param name EventPlug �̖��O
     */
    public Object getInvoker(String name) {
        EventPlug eventPlug = getEventPlug(name);
        return eventPlug.getInvoker();
    }

    /**
     * �w�肵�����O�� EventPlug �ɃC�x���g���󂯎�郊�X�i��ݒ肵�܂��D
     * 
     * @param name EventPlug �̖��O
     * @param listener �C�x���g���󂯎�郊�X�i
     */
    public void setEventListener(String name, EventListener listener) {
        EventPlug eventPlug = getEventPlug(name);
        eventPlug.setEventListener(listener);
    }

    /**
     * �w�肵�����O�� EventPlug �̃C�x���g���󂯎�郊�X�i���擾���܂��D
     * 
     * @param name EventPlug �̖��O
     */
    public EventListener getEventListener(String name) {
        EventPlug eventPlug = getEventPlug(name);
        return eventPlug.getEventListener();
    }

    /**
     * �w�肵�����O�� EventPlug �𕡐��� �C�x���g�𔭍s����I�u�W�F�N�g��ݒ肵�܂��D
     * 
     * @param name EventPlug �̖��O
     * @param newName �������� EventPlug �̖��O
     * @param invoker �C�x���g�𔭍s����I�u�W�F�N�g
     */
    public void addNewEventPlug(String name, String newName, Object invoker) {
        EventListener listener = getEventListener(name);
        addEventPlug(new EventPlug(newName, invoker, listener));
    }

    /**
     * �w�肵�����O�� EventPlug �𕡐��� �C�x���g���󂯎�郊�X�i��ݒ肵�܂��D
     * 
     * @param name EventPlug �̖��O
     * @param newName �������� EventPlug �̖��O
     * @param l �C�x���g���󂯎�郊�X�i
     */
    public void addNewEventPlug(String name, String newName, EventListener l) {
        Object invoker = getInvoker(name);
        addEventPlug(new EventPlug(newName, invoker, l));
    }

    /**
     * �w�肵�����O�� EventPlug �������C�������܂��D
     * 
     * @param name EventPlug �̖��O
     * @param connected �������邩�ǂ���
     */
    public void setConnected(String name, boolean connected) {
        EventPlug eventPlug = getEventPlug(name);
        eventPlug.setConnected(connected);
    }

    /**
     * ���ׂĂ� EventPlug �������C�������܂��D
     * 
     * @param connected �������邩�ǂ���
     */
    public void setConnected(boolean connected) {
        Iterator<EventPlug> e = eventPlugs.values().iterator();
        while (e.hasNext()) {
            e.next().setConnected(connected);
        }
    }
}

/* */
