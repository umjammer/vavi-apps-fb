/*
 * Copyright (c) 2002 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.swing.border;

import java.beans.BeanInfo;


/**
 * Border �N���X�� BeanInfo �ł��D
 * <p>
 * Border �� getter ���\�b�h�������N���X���قƂ�ǂȂ̂ŁCgetter ���\�b�h �݂̂�����
 * PropertyDescriptor �Ƃ��� BorderPropertyDescriptor ��p�ӂ��� �g�p���܂��D
 * </p>
 * <p>
 * Beans �̎d�l�Ƃ͈Ⴄ�̂ŁCIntrospector �ł͂Ȃ� BorderInfoFactory �� �p���� BorderInfo
 * �N���X���擾���Ă��������D
 * </p>
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 020518 nsano initial version <br>
 */
public interface BorderInfo extends BeanInfo {

    /** */
    BorderPropertyDescriptor[] getBorderPropertyDescriptors();
}

/* */
