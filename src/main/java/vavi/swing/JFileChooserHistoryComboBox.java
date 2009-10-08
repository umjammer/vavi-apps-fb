/*
 * Copyright (c) 2002 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.swing;

import java.io.File;


/**
 * �t�@�C��������͂��邽�߂̃R���|�[�l���g�ł��D
 * �q�X�g���t���R���{�{�b�N�X��"�Q��"�{�^�������Ă��܂��D
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 020503 nsano initial version <br>
 *          0.10 020504 nsano deprecate Text setter, getter <br>
 *          0.11 020515 nsano fix getSelectedFile <br>
 *          0.12 020515 nsano revive Text setter, getter <br>
 */
public class JFileChooserHistoryComboBox extends JFileChooserField {

    /**
     * �q�X�g���t���R���{�{�b�N�X�t���t�@�C���`���[�U���\�z���܂��D
     */
    public JFileChooserHistoryComboBox() {
        super();
    }

    /**
     * �q�X�g���t���R���{�{�b�N�X�t���t�@�C���`���[�U���\�z���܂��D
     */
    public JFileChooserHistoryComboBox(File file) {
        super(file);
    }

    /** �q�X�g���t���R���{�{�b�N�X */
    protected void setPathFieldImpl() {
        pathField = new JHistoryComboBox();
    }

    /** �R���{�{�b�N�X�̃A�N�V�������X�i */
    protected void addActionListenerImpl() {
        ((JHistoryComboBox) pathField).addActionListener(pathFieldActionListener);
    }

    /**
     * ��������R���{�{�b�N�X�ɐݒ肵�܂��D
     * 
     * @param text ������
     */
    protected void setTextImpl(String text) {
        ((JHistoryComboBox) pathField).setSelectedItem(text);
    }

    /**
     * �R���{�{�b�N�X�őI������Ă��镶�����Ԃ��܂��D
     * 
     * @return �I������Ă��镶����
     */
    protected String getTextImpl() {
        return (String) ((JHistoryComboBox) pathField).getSelectedItem();
    }

    /**
     * �t�@�C�����R���{�{�b�N�X�ɐݒ肵�܂��D
     * 
     * @param file �t�@�C��
     */
    protected void setSelectedFileImpl(File file) {
        setTextImpl(file.toString());
    }

    /**
     * �R���{�{�b�N�X�őI������Ă���t�@�C����Ԃ��܂��D
     * 
     * @return �I������Ă���t�@�C��
     */
    protected File getSelectedFileImpl() {
        String path = getTextImpl();
        return path == null ? null : new File(path);
    }
}

/* */
