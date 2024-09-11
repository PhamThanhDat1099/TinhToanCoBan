package BaiTapGiaoDien;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleCalculator extends JFrame implements ActionListener {
    // Khai báo các thành phần giao diện
    private JTextField txtDisplay;
    private JButton[] btnNumbers;
    private JButton btnAdd, btnSub, btnMul, btnDiv, btnEq, btnClear, btnDot;
    private String operator;
    private double num1, num2, result;
    private boolean dotUsed = false; // Để theo dõi xem dấu chấm đã được sử dụng hay chưa

    // Hàm khởi tạo giao diện
    public SimpleCalculator() {
        setTitle("Máy tính");
        setLayout(new BorderLayout());

        // Tạo panel hiển thị và nút Clear (C)
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        
        // Tạo text field hiển thị
        txtDisplay = new JTextField();
        txtDisplay.setEditable(false);
        txtDisplay.setHorizontalAlignment(JTextField.RIGHT);
        txtDisplay.setFont(new Font("Arial", Font.PLAIN, 24));  
        displayPanel.add(txtDisplay, BorderLayout.CENTER);
        
        // Nút Clear (C)
        btnClear = new JButton("C");
        btnClear.addActionListener(this);
        displayPanel.add(btnClear, BorderLayout.EAST);
        
        add(displayPanel, BorderLayout.NORTH);

        // Tạo panel chứa các nút số và các phép toán
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5)); 

        // Tạo các nút số từ 0 đến 9
        btnNumbers = new JButton[10];
        for (int i = 0; i < 10; i++) {
            btnNumbers[i] = new JButton(String.valueOf(i));
            btnNumbers[i].addActionListener(this);
        }

        // Tạo nút chấm (.) và các nút cho các phép toán
        btnDot = new JButton(".");
        btnAdd = new JButton("+");
        btnSub = new JButton("-");
        btnMul = new JButton("*");
        btnDiv = new JButton("/");
        btnEq = new JButton("=");

        // Thêm sự kiện cho các nút
        btnDot.addActionListener(this);
        btnAdd.addActionListener(this);
        btnSub.addActionListener(this);
        btnMul.addActionListener(this);
        btnDiv.addActionListener(this);
        btnEq.addActionListener(this);

        // Sắp xếp các nút vào GridLayout (4x4)
        panel.add(btnNumbers[7]);
        panel.add(btnNumbers[8]);
        panel.add(btnNumbers[9]);
        panel.add(btnAdd);
        panel.add(btnNumbers[4]);
        panel.add(btnNumbers[5]);
        panel.add(btnNumbers[6]);
        panel.add(btnSub);
        panel.add(btnNumbers[1]);
        panel.add(btnNumbers[2]);
        panel.add(btnNumbers[3]);
        panel.add(btnMul);
        panel.add(btnDot);     // Thêm nút chấm (.)
        panel.add(btnNumbers[0]);
        panel.add(btnEq);
        panel.add(btnDiv);

        // Thêm panel chứa các nút vào giao diện chính
        add(panel, BorderLayout.CENTER);

        // Cài đặt kích thước mặc định cho giao diện
        setSize(300, 400);
        setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Xử lý các sự kiện cho nút bấm
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        
        // Xử lý nút số
        for (int i = 0; i < 10; i++) {
            if (src == btnNumbers[i]) {
                txtDisplay.setText(txtDisplay.getText() + i);
                return;
            }
        }

        // Xử lý nút chấm (.) để nhập số thập phân
        if (src == btnDot) {
            if (!dotUsed) {  // Chỉ cho phép một dấu chấm
                txtDisplay.setText(txtDisplay.getText() + ".");
                dotUsed = true;  // Đánh dấu đã sử dụng dấu chấm
            }
            return;
        }

        // Xử lý các phép toán
        if (src == btnAdd) {
            operator = "+";
            num1 = Double.parseDouble(txtDisplay.getText());
            txtDisplay.setText("");
            dotUsed = false;  // Reset cho phép dùng dấu chấm sau phép tính mới
        } else if (src == btnSub) {
            operator = "-";
            num1 = Double.parseDouble(txtDisplay.getText());
            txtDisplay.setText("");
            dotUsed = false;
        } else if (src == btnMul) {
            operator = "*";
            num1 = Double.parseDouble(txtDisplay.getText());
            txtDisplay.setText("");
            dotUsed = false;
        } else if (src == btnDiv) {
            operator = "/";
            num1 = Double.parseDouble(txtDisplay.getText());
            txtDisplay.setText("");
            dotUsed = false;
        }

        // Xử lý nút bằng (=)
        if (src == btnEq) {
            num2 = Double.parseDouble(txtDisplay.getText());
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        txtDisplay.setText("Lỗi chia 0");
                        return;
                    }
                    break;
            }
            txtDisplay.setText(String.valueOf(result));
            dotUsed = false; 
        }

        if (src == btnClear) {
            txtDisplay.setText("");
            dotUsed = false;  
        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}
