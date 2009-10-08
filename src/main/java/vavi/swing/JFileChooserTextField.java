/*
 * Copyright (c) 2001 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.swing;

import java.io.File;

import javax.swing.JTextField;


/**
 * �t�@�C��������͂��邽�߂̃R���|�[�l���g�ł��D �e�L�X�g�t�B�[���h��"�Q��"�{�^�������Ă��܂��D
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 010823 nsano initial version <br>
 *          0.10 020427 nsano repackage <br>
 *          0.11 020427 nsano refine <br>
 *          0.12 020428 nsano refine ResourceBundle <br>
 *          0.20 020503 nsano separate base class <br>
 *          0.30 020504 nsano deprecate Text setter, getter <br>
 *          0.31 020515 nsano fix getSelectedFile <br>
 *          0.32 020515 nsano revive Text setter, getter <br>
 */
public class JFileChooserTextField extends JFileChooserField {

    /**
     * �e�L�X�g�t�B�[���h�t���t�@�C���`���[�U���\�z���܂��D
     */
    public JFileChooserTextField() {
        super();
    }

    /**
     * �e�L�X�g�t�B�[���h�t���t�@�C���`���[�U���\�z���܂��D
     */
    public JFileChooserTextField(File file) {
        super(file);
    }

    /** �e�L�X�g�t�B�[���h */
    protected void setPathFieldImpl() {
        pathField = new JTextField(20);
    }

    /** �e�L�X�g�t�B�[���h�̃A�N�V�������X�i */
    protected void addActionListenerImpl() {
        ((JTextField) pathField).addActionListener(pathFieldActionListener);
    }

    /**
     * ��������e�L�X�g�t�B�[���h�ɐݒ肵�܂��D
     * 
     * @param text ������
     */
    protected void setTextImpl(String text) {
        ((JTextField) pathField).setText(text);
    }

    /**
     * �e�L�X�g�t�B�[���h�ɕ\������Ă��镶�����Ԃ��܂��D
     * 
     * @return �\������Ă��镶����
     */
    protected String getTextImpl() {
        return ((JTextField) pathField).getText();
    }

    /**
     * �t�@�C�����e�L�X�g�t�B�[���h�ɐݒ肵�܂��D
     * 
     * @param file �t�@�C��
     */
    protected void setSelectedFileImpl(File file) {
        setTextImpl(file.toString());
    }

    /**
     * �e�L�X�g�t�B�[���h�ɕ\������Ă���t�@�C����Ԃ��܂��D
     * 
     * @return �\������Ă���t�@�C��
     */
    protected File getSelectedFileImpl() {
        String path = getTextImpl();
        return path == null ? null : new File(path);
    }
}

/* */
