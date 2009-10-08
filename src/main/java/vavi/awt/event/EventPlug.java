/*
 * Copyright (c) 2002 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.awt.event;

import java.lang.reflect.Method;
import java.util.EventListener;

import vavi.util.Debug;


/**
 * �C�x���g�������C�������邽�߂̃N���X�ł��D
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 020512 nsano initial version <br>
 */
public class EventPlug {
    /** �v���O�̖��O */
    private String name;
    /** �C�x���g�𔭍s����I�u�W�F�N�g */
    private Object invoker;
    /** �C�x���g���󂯎�郊�X�i */
    private EventListener listener;
    /** �ڑ����Ă��邩�ǂ��� */
    private boolean connected = false;

    /**
     * �C�x���g�������C��������v���O���\�z���܂��D
     * 
     * @param name �v���O�̖��O
     * @param invoker �C�x���g�𔭍s����I�u�W�F�N�g
     * @param listener �C�x���g���󂯎�郊�X�i
     */
    public EventPlug(String name, Object invoker, EventListener listener) {
        this.name = name;
        this.invoker = invoker;
        this.listener = listener;
    }

    /** �v���O�̖��O���擾���܂��D */
    public String getName() {
        return name;
    }

    /** �C�x���g�𔭍s����I�u�W�F�N�g��ݒ肵�܂��D */
    public void setInvoker(Object invoker) {
        this.invoker = invoker;
    }

    /** �C�x���g�𔭍s����I�u�W�F�N�g���擾���܂��D */
    public Object getInvoker() {
        return invoker;
    }

    /** �C�x���g���󂯎�郊�X�i��ݒ肵�܂��D */
    public void setEventListener(EventListener listener) {
        this.listener = listener;
    }

    /** �C�x���g���󂯎�郊�X�i���擾���܂��D */
    public EventListener getEventListener() {
        return listener;
    }

    /** �v���O���ڑ�����Ă��邩�ǂ�����Ԃ��܂��D */
    public boolean isConnected() {
        return connected;
    }

    /** �v���O��ڑ��C�������܂��D */
    public void setConnected(boolean connected) {
        if (connected) {
            plugImpl("add");
        } else {
            plugImpl("remove");
        }
        this.connected = connected;
    }

    /**
     * ���X�i�̃N���X���擾���܂��D
     * ���X�i�� FooListener �`���̖����łȂ���΂����܂���D
     * TODO interface �� 2 �ȏ�̏ꍇ�̏����D
     * TODO FooListener ��T���ă��[�v���ׂ��H
     */
    private Class<?> getEventListenerClass() {
        Class<?> clazz = listener.getClass();
        String className = clazz.getName();
        if (!className.endsWith("Listener")) {
            Class<?>[] classes = clazz.getInterfaces();
//Debug.println(classes.length);
//for (int i = 0; i < classes.length; i++) {
// System.err.println(classes[i]);
//}
            clazz = classes[0];
        }
        return clazz;
    }

    /**
     * ���X�i�̖��O���擾���܂��D
     */
    private String getEventListenerName() {
        return getClassName(getEventListenerClass().getName());
    }

    /** Gets class name w/o package name. */
    private static String getClassName(String className) {
        return className.substring(className.lastIndexOf(".") + 1);
    }

    /**
     * �v���O��ڑ��C�������鏈���ł��D
     * 
     * @param type "add" or "remove"
     */
    private void plugImpl(String type) {
        try {
            Class<?> clazz = invoker.getClass();
            Class<?>[] argTypes = {
                getEventListenerClass()
            };
            String methodName = type + getEventListenerName();
            Method method = clazz.getMethod(methodName, argTypes);
            Object[] args = {
                listener
            };
            method.invoke(invoker, args);
        } catch (Exception e) {
Debug.printStackTrace(e);
            throw new IllegalStateException(getClassName(invoker.getClass().getName()) + "." + type + getEventListenerName() + "(" + getEventListenerClass() + ")");
        }
    }
}

/* */
