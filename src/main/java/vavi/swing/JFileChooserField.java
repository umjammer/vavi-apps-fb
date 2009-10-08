/*
 * Copyright (c) 2002 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import vavi.util.Debug;


/**
 * �t�@�C��������͂��邽�߂̃R���|�[�l���g�̊�{�N���X�ł��D
 * ���̓t�B�[���h��"�Q��"�{�^�������Ă��܂��D
 * ���݂̂Ƃ���t�@�C���`���[�U�ƃe�L�X�g�t�B�[���h�̊g���Ƃ݂Ȃ��Ă��܂��D
 * 
 * @event PropetyChangeEvent("text",,String)
 * @event PropetyChangeEvent("selectedFile",,File)
 * 
 * @depends /vavi/swing/resource${I18N}.properties
 * 
 * @done Listener �̌�����
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 020503 nsano initial version <br>
 *          0.10 020504 nsano deprecate Text setter, getter <br>
 *          0.20 020515 nsano revive Text setter, getter <br>
 *          0.21 020603 nsano refine <br>
 *          0.22 020618 nsano fix firePropertyChange <br>
 */
public abstract class JFileChooserField extends JComponent {

    /** */
    private static final ResourceBundle rb = ResourceBundle.getBundle("vavi.swing.resource", Locale.getDefault());

    /** �t�@�C���`���[�U */
    private JFileChooser chooser;

    /** filechooser's approve button title */
    private String title;

    /** �t�@�C���`���[�U�N���{�^�� */
    private JButton selectButton;

    /** ���̓t�B�[���h */
    protected JComponent pathField;

    /**
     * ���̓t�B�[���h�t���t�@�C���`���[�U�̃R���|�[�l���g���\�z���܂��D
     */
    public JFileChooserField(File file) {
        this();
        setSelectedFile(file);
    }

    /**
     * ���̓t�B�[���h�t���t�@�C���`���[�U�̃R���|�[�l���g���\�z���܂��D
     */
    public JFileChooserField() {

        this.setLayout(new BorderLayout());

        // filechooser
        chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        title = rb.getString("jFileChooser.dialog.title");

        // field
        setPathFieldImpl();
        addActionListenerImpl();
        this.add(pathField, BorderLayout.CENTER);

        // button
        selectButton = new JButton();
        selectButton.setText(rb.getString("jFileChooser.button.text"));
        selectButton.addActionListener(selectButtonActionListener);
        this.add(selectButton, BorderLayout.EAST);
    }

    /**
     * #pathField �ɃR���|�[�l���g�̃C���X�^���X��ݒ肵�ĉ������D
     */
    protected abstract void setPathFieldImpl();

    /**
     * #pathField �� #pathFieldActionListener �� add ���Ă��������D JComponent �ɂ�
     * addActionListener �͖����D
     */
    protected abstract void addActionListenerImpl();

    /** �t�B�[���h�̏��� TODO text only �ł����̂��H */
    protected ActionListener pathFieldActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
            // Debug.println(ev.getSource().getClass().getName());
            setText(getText());
        }
    };

    /** �I���{�^���̏��� */
    private ActionListener selectButtonActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
            // Debug.println(ev.getSource().getClass().getName());
            int returnValue = chooser.showDialog(getParent(), title);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                setSelectedFile(chooser.getSelectedFile());
            }
        }
    };

    /**
     * #pathField �ɕ������ݒ肵�ĉ������D
     * 
     * @param text ������
     */
    protected abstract void setTextImpl(String text);

    /**
     * #pathField ����擾�����������Ԃ��ĉ������D
     * 
     * @return �\������Ă��镶����
     */
    protected abstract String getTextImpl();

    /**
     * #pathField �Ƀt�@�C����ݒ肵�ĉ������D
     * 
     * @param file �t�@�C��
     */
    protected abstract void setSelectedFileImpl(File file);

    /**
     * #pathField ����擾�����t�@�C����Ԃ��ĉ������D
     * 
     * @return �\������Ă���t�@�C��
     */
    protected abstract File getSelectedFileImpl();

    // Field �Ƃ��Ă̋@�\ -----------------------------------------------------

    /**
     * �������ݒ肵�܂��D
     */
    public void setText(String text) {
        String oldString = getTextImpl();
        setTextImpl(text);
        firePropertyChange("text", oldString, getText());
    }

    /**
     * ��������擾���܂��D
     */
    public String getText() {
        return getTextImpl();
    }

    // FileChooser �Ƃ��Ă̋@�\ -----------------------------------------------

    /**
     * �I�����ꂽ�t�@�C����ݒ肵�܂��D �t�@�C���`���[�U�̃J�����g�f�B���N�g���͎w�肵���t�@�C���� �f�B���N�g���ɂȂ�܂��D
     * 
     * @param file �t�@�C��
     */
    public void setSelectedFile(File file) {
        File oldFile = getSelectedFileImpl();
        setSelectedFileImpl(file);
        firePropertyChange("selectedFile", oldFile, getSelectedFile());
    }

    /**
     * �I�����ꂽ�t�@�C����Ԃ��܂��D
     * 
     * @return �\������Ă���t�@�C��
     */
    public File getSelectedFile() {
        return getSelectedFileImpl();
    }

    /** �t�@�C���`���[�U�̃J�����g�f�B���N�g����ݒ肵�܂��D */
    public void setCurrentDirectory(File file) {
        File defaultPath = null;
        if (file.exists()) {
            if (file.isFile())
                defaultPath = file.getParentFile();
            else
                defaultPath = file;
        } else {
            defaultPath = new File(System.getProperty("user.home"));
        }
        Debug.println(defaultPath);
        chooser.setCurrentDirectory(defaultPath);
    }

    /** �t�@�C���`���[�U�̃J�����g�f�B���N�g�����擾���܂��D */
    // private File getCurrentDirectory() {
    // return chooser.getCurrentDirectory();
    // }
    /**
     * �t�@�C���`���[�U�̃t�@�C���I�����[�h��ݒ肵�܂��D
     * 
     * @param selectionMode �t�@�C���I���^�C�v
     */
    public void setFileSelectionMode(int selectionMode) {
        chooser.setFileSelectionMode(selectionMode);
    }

    /** */
    public void setDialogTitle(String title) {
        this.title = title;
    }

    /** */
    public void setFileFilter(FileFilter filter) {
        chooser.setFileFilter(filter);
    }

    // -------------------------------------------------------------------------

    /**
     * ���ׂẴR���|�[�l���g�� setEnabled ����悤�ɃI�[�o�[���C�h���܂��D
     */
    public void setBackground(Color background) {
        super.setBackground(background);
        pathField.setBackground(background);
        selectButton.setBackground(background);
    }

    /**
     * ���ׂẴR���|�[�l���g�� setEnabled ����悤�ɃI�[�o�[���C�h���܂��D
     */
    public void setForeground(Color foreground) {
        super.setForeground(foreground);
        pathField.setForeground(foreground);
        selectButton.setForeground(foreground);
    }

    /**
     * ���ׂẴR���|�[�l���g�� setEnabled ����悤�ɃI�[�o�[���C�h���܂��D
     */
    public void setFont(Font font) {
        super.setFont(font);
        pathField.setFont(font);
        selectButton.setFont(font);
    }

    /**
     * ���ׂẴR���|�[�l���g�� setEnabled ����悤�ɃI�[�o�[���C�h���܂��D
     */
    public void setEnabled(boolean isEnabled) {
        super.setEnabled(isEnabled);
        pathField.setEnabled(isEnabled);
        selectButton.setEnabled(isEnabled);
    }

    /**
     * ���ׂẴR���|�[�l���g�� add ����悤�ɃI�[�o�[���C�h���܂��D
     * 
     * @param l �}�E�X���X�i
     */
    public synchronized void addMouseListener(MouseListener l) {
        super.addMouseListener(l);
        pathField.addMouseListener(l);
        selectButton.addMouseListener(l);
    }

    /**
     * ���ׂẴR���|�[�l���g�� add ����悤�ɃI�[�o�[���C�h���܂��D
     * 
     * @param l �}�E�X���[�V�������X�i
     */
    public synchronized void addMouseMotionListener(MouseMotionListener l) {
        super.addMouseMotionListener(l);
        pathField.addMouseMotionListener(l);
        selectButton.addMouseMotionListener(l);
    }

    /**
     * ���ׂẴR���|�[�l���g�� remove ����悤�ɃI�[�o�[���C�h���܂��D
     * 
     * @param l �}�E�X���X�i
     */
    public synchronized void removeMouseListener(MouseListener l) {
        super.removeMouseListener(l);
        pathField.removeMouseListener(l);
        selectButton.removeMouseListener(l);
    }

    /**
     * ���ׂẴR���|�[�l���g�� remove ����悤�ɃI�[�o�[���C�h���܂��D
     * 
     * @param l �}�E�X���[�V�������X�i
     */
    public synchronized void removeMouseMotionListener(MouseMotionListener l) {
        super.removeMouseMotionListener(l);
        pathField.removeMouseMotionListener(l);
        selectButton.removeMouseMotionListener(l);
    }
}

/* */
