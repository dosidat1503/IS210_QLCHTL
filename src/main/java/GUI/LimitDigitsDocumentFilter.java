package GUI;

import javax.swing.*;
import javax.swing.text.*;

public class LimitDigitsDocumentFilter extends DocumentFilter {
    private int maxLength;

    public LimitDigitsDocumentFilter(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
        // Kiểm tra độ dài sau khi thêm chuỗi
        if (fb.getDocument().getLength() + text.length() <= maxLength) {
            // Kiểm tra xem chuỗi thêm vào có chứa toàn ký tự số không
            if (text.matches("\\d+")) {
                super.insertString(fb, offset, text, attr);
            }
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        // Kiểm tra độ dài sau khi thay thế chuỗi
        if (fb.getDocument().getLength() - length + text.length() <= maxLength) {
            // Kiểm tra xem chuỗi thay thế có chứa toàn ký tự số không
            if (text.matches("\\d+")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
}