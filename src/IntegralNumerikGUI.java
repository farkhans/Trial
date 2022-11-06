import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class IntegralNumerikGUI  extends JFrame {

    // Ubah fungsi ini untuk mengganti fungsi matematika
    public double f(double x) {
        return Math.exp(Math.pow(x, 2));
    }

    public double midpoint(double upperBound, double lowerBound) {
        return (upperBound - lowerBound) * f((upperBound + lowerBound) / 2);
    }

    public double trapezoid(double upperBound, double lowerBound) {
        return (f(upperBound) + f(lowerBound)) * (upperBound - lowerBound) / 2;
    }

    public double simpson(double upperBound, double lowerBound) {
        return (upperBound - lowerBound) / 6 * (f(lowerBound) + f((lowerBound + upperBound) / 2) + f(upperBound));
    }

    public double compositeMidpoint(double upperBound, double lowerBound, int subintervals) {
        double res = 0;
        double stepSize = (upperBound - lowerBound) / subintervals;
        for (int i = 0; i < subintervals; i++) {
            res += stepSize * f(lowerBound + (i + 0.5) * stepSize);
        }
        return res;
    }

    public double compositeTrapezoid(double upperBound, double lowerBound, int subintervals) {
        double res = (f(lowerBound) + f(upperBound)) / 2;
        double stepSize = (upperBound - lowerBound) / subintervals;
        for (int i = 1; i < subintervals - 1; i++) {
            res += f(lowerBound + stepSize * i);
        }
        return res * stepSize;
    }

    public double compositeSimpson(double upperBound, double lowerBound, int subintervals) {
        double stepSize = (upperBound - lowerBound) / subintervals;
        double res = f(lowerBound) + f(upperBound);
        for (int i = 0; i < subintervals; i++) {
            res += 4 * f(lowerBound + (i + 0.5) * stepSize);
        }
        for (int i = 1; i < subintervals; i++) {
            res += 2 * f(lowerBound + i * stepSize);
        }
        return res * stepSize / 6;
    }

    public IntegralNumerikGUI() {
        setTitle("Kalkulator Integral Numerik");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 450);
        
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel("Selamat datang di Kalkulator Integral Numerik");
        titleLabel.setFont(new Font("Montserrat", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(new CompoundBorder(titleLabel.getBorder(), new EmptyBorder(10, 0, 10, 0)));
        add(titleLabel);

        // Ubah keterangan fungsi jika diperlukan
        JLabel subtitleLabel = new JLabel("Kalkulator ini menghitung integral tentu dari fungsi e^(x^2) secara numerik");
        subtitleLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(subtitleLabel);
        add(Box.createRigidArea(new Dimension(10,10)));

        JLabel methodLabel = new JLabel("Metode");
        methodLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        methodLabel.setBorder(new CompoundBorder(methodLabel.getBorder(), new EmptyBorder(10, 0, 5, 0)));
        add(methodLabel);

        String[] methods = {"Midpoint", "Trapezoid", "Simpson"};
        JComboBox<String> methodDropDown = new JComboBox<>(methods);
        methodDropDown.setSelectedIndex(0);
        methodDropDown.setPreferredSize(new Dimension(300, 20));
        methodDropDown.setMaximumSize(new Dimension(300, 20));
        add(methodDropDown);

        JPanel compositePanel = new JPanel();
        compositePanel.setLayout(new BoxLayout(compositePanel, BoxLayout.X_AXIS));
        compositePanel.setPreferredSize(new Dimension(300, 65));
        compositePanel.setMaximumSize(new Dimension(300, 65));
        compositePanel.setBorder(new CompoundBorder(compositePanel.getBorder(), new EmptyBorder(10, 0, 10, 0)));

        JRadioButton compositeRadioButton = new JRadioButton("Composite");
        compositePanel.add(compositeRadioButton);

        compositePanel.add(Box.createRigidArea(new Dimension(50, 20)));

        JPanel intervalPanel = new JPanel();
        intervalPanel.setLayout(new BoxLayout(intervalPanel, BoxLayout.Y_AXIS));
        
        JLabel intervalLabel = new JLabel("Banyak Interval");
        intervalLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        intervalPanel.add(intervalLabel);
        intervalPanel.add(Box.createRigidArea(new Dimension(10, 3)));
        
        JTextField intervalTextField = new JTextField();
        intervalTextField.setEnabled(false);
        intervalTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        intervalPanel.add(intervalTextField);
        
        compositePanel.add(intervalPanel);
        
        add(compositePanel);

        JPanel boundsPanel = new JPanel();
        boundsPanel.setLayout(new BoxLayout(boundsPanel, BoxLayout.X_AXIS));
        boundsPanel.setPreferredSize(new Dimension(300, 55));
        boundsPanel.setMaximumSize(new Dimension(300, 55));
        boundsPanel.setBorder(new CompoundBorder(boundsPanel.getBorder(), new EmptyBorder(0, 0, 10, 0)));

        JPanel upperBoundPanel = new JPanel();
        upperBoundPanel.setLayout(new BoxLayout(upperBoundPanel, BoxLayout.Y_AXIS));
        
        JLabel upperBoundLabel = new JLabel("Batas atas");
        upperBoundLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        upperBoundPanel.add(upperBoundLabel);
        upperBoundPanel.add(Box.createRigidArea(new Dimension(10, 3)));
        
        JTextField upperBoundTextField = new JTextField();
        upperBoundTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        upperBoundPanel.add(upperBoundTextField);

        boundsPanel.add(upperBoundPanel);

        boundsPanel.add(Box.createRigidArea(new Dimension(10, 20)));

        JPanel lowerBoundPanel = new JPanel();
        lowerBoundPanel.setLayout(new BoxLayout(lowerBoundPanel, BoxLayout.Y_AXIS));
        
        JLabel lowerBoundLabel = new JLabel("Batas bawah");
        lowerBoundLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        lowerBoundPanel.add(lowerBoundLabel);
        lowerBoundPanel.add(Box.createRigidArea(new Dimension(10, 3)));
        
        JTextField lowerBoundTextField = new JTextField();
        lowerBoundTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        lowerBoundPanel.add(lowerBoundTextField);

        boundsPanel.add(lowerBoundPanel);

        add(boundsPanel);

        add(Box.createRigidArea(new Dimension(10, 10)));

        JButton calculateButton = new JButton("Hitung");
        calculateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        calculateButton.setFocusable(false);
        calculateButton.setPreferredSize(new Dimension(100, 30));
        calculateButton.setMaximumSize(new Dimension(100, 30));
        add(calculateButton);

        add(Box.createRigidArea(new Dimension(10, 15)));

        JLabel resultLabel = new JLabel("",JLabel.CENTER);
        add(resultLabel);
        
        setVisible(true);

        compositeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (compositeRadioButton.isSelected()) {
                    intervalTextField.setEnabled(true);
                }
                else {
                    intervalTextField.setEnabled(false);
                }
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean intervalError = false;
                try {
                    double upperBound = Double.parseDouble(upperBoundTextField.getText());
                    double lowerBound = Double.parseDouble(lowerBoundTextField.getText());
                    if (upperBound < lowerBound) {
                        throw new LowerUpperBoundException();
                    }
                    double res;
                    // Ubah keterangan fungsi jika diperlukan
                    String resultString = "<html>f(x) = e^(x^2)<br>";
                    if (compositeRadioButton.isSelected()) {
                        intervalError = true;
                        int intervals = Integer.parseInt(intervalTextField.getText());
                        if (intervals <= 0) {
                            throw new InvalidIntervalException();
                        }
                        if (methodDropDown.getSelectedIndex() == 0) {
                            resultString += "Metode: Composite Midpoint<br>";
                            res = compositeMidpoint(upperBound, lowerBound, intervals);
                        } else if (methodDropDown.getSelectedIndex() == 1) {
                            resultString += "Metode: Composite Trapezoid<br>";
                            res = compositeTrapezoid(upperBound, lowerBound, intervals);
                        } else {
                            resultString += "Metode: Composite Simpson<br>";
                            res = compositeSimpson(upperBound, lowerBound, intervals);
                        }
                        resultString += String.format("Batas atas: %.2f<br>Batas bawah: %.2f<br>", upperBound, lowerBound);
                        resultString += String.format("Banyak interval: %d<br>", intervals);
                        resultString += String.format("Hasil: %3.3E", res);

                    } else {
                        if (methodDropDown.getSelectedIndex() == 0) {
                            resultString += "Metode: Midpoint<br>";
                            res = midpoint(upperBound, lowerBound);
                        } else if (methodDropDown.getSelectedIndex() == 1) {
                            resultString += "Metode: Trapezoid<br>";
                            res = trapezoid(upperBound, lowerBound);
                        } else {
                            resultString += "Metode: Simpson<br>";
                            res = simpson(upperBound, lowerBound);
                        }
                        resultString += String.format("Batas atas: %.2f<br>Batas bawah: %.2f<br>", upperBound, lowerBound);
                        resultString += String.format("Hasil: %3.3E", res);
                    }
                    resultLabel.setText(resultString);
                    resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                } catch (NumberFormatException exception) {
                    if (intervalError) {
                        JOptionPane.showMessageDialog(IntegralNumerikGUI.this, "Input interval haruslah bilangan bulat positif", "Kesalahan Input", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(IntegralNumerikGUI.this, "Input batas atas dan bawah haruslah bilangan riil", "Kesalahan Input", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (LowerUpperBoundException exception) {
                    JOptionPane.showMessageDialog(IntegralNumerikGUI.this, "Input batas atas harus lebih besar atau sama dengan input batas bawah", "Kesalahan Input", JOptionPane.ERROR_MESSAGE);
                } catch (InvalidIntervalException exception) {
                    JOptionPane.showMessageDialog(IntegralNumerikGUI.this, "Input interval haruslah bilangan bulat positif", "Kesalahan Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        new IntegralNumerikGUI();
    }
}

class LowerUpperBoundException extends Exception {
    public LowerUpperBoundException() {
        super();
    }
}

class InvalidIntervalException extends Exception {
    public InvalidIntervalException() {
        super();
    }
}