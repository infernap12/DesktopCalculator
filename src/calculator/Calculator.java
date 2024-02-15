package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused, FieldCanBeLocal")
public class Calculator extends JFrame {

    public Calculator() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        initComponents();
        setContentPane(MainForm);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(320, 500));
        setVisible(true);
        setTitle(getClass().getSimpleName());
        setLocationRelativeTo(null);
    }

    // test for divide by zero
    //test if op is last character
    //if either, Colors.RED.darker()
    private void Equals(ActionEvent ignoredE) {
        String equation = EquationLabel.getText();
        if (Objects.equals(equation, "(-√(9))")) {
            EquationLabel.setForeground(Color.RED.darker());
            return;
        }
        Matcher divideByZero = Pattern.compile("÷0(?!\\.0*[1-9]+)").matcher(equation);
        if (String.valueOf(equation.charAt(equation.length() - 1)).matches(util.operator.pattern())) {
            EquationLabel.setForeground(Color.RED.darker());
        } else if (divideByZero.find(0)) {
            EquationLabel.setForeground(Color.RED.darker());
        } else /*if (EquationLabel.getText().matches("(\\d+(\\.\\d+)*)([+-×÷](\\d+(\\.\\d+)*))+"))*/ {
            EquationLabel.setForeground(Color.GREEN.darker());
//            System.out.println("Calculation Requested.");
            util.logger.info("Calculation Requested.");
            String input = EquationLabel.getText();
            String output = util.solve(util.convertToPostfix(input));

            ResultLabel.setText(output);
        }
    }

    private void exit(ActionEvent e) {
        this.dispose();
    }

    private void Clear(ActionEvent e) {
        EquationLabel.setText("");
        ResultLabel.setText("0");
    }

    private void Delete(ActionEvent e) {
        EquationLabel.setText(EquationLabel.getText().substring(0, EquationLabel.getText().length() - 1));
    }

    private void opConcat(ActionEvent e) {
        String equation = EquationLabel.getText();
        if (String.valueOf(equation.charAt(0)).matches(util.operator.pattern())) {
            System.out.println("No start with operator >:(");
            return;
        } else if (String.valueOf(equation.charAt(equation.length() - 1)).matches(util.operator.pattern())) {
            EquationLabel.setText(equation.substring(0, equation.length() - 1));
        }
        faceTextEquationConcat(e);
        // test whether is first character, if so don't add - test imp
        // test if length -1 character is an op, if so substring -1 and concat - test imp
        Matcher matcherPre = Pattern.compile("(?<!\\d)\\.").matcher(EquationLabel.getText());
        Matcher matcherPost = Pattern.compile("\\.(?=[\\u002B\\u2212\\u00D7\\u00F7])").matcher(EquationLabel.getText());
        while (matcherPre.find()) {
            EquationLabel.setText(EquationLabel.getText().replaceAll("(?<!\\d)\\.", "0."));
        }
        while (matcherPost.find()) {
            EquationLabel.setText(EquationLabel.getText().replaceAll("\\.(?=[\\u002B\\u2212\\u00D7\\u00F7])", ".0"));
        }
    }


    private void faceTextEquationConcat(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        equationConcat(button.getText());
    }

    private void equationConcat(String s) {
        EquationLabel.setText(EquationLabel.getText().concat(s));
    }

    private void parenthetical(ActionEvent e) {
        String equation = EquationLabel.getText();
        if (EquationLabel.getText().isEmpty()) {
            equationConcat("(");
            return;
        }
        String last = String.valueOf(equation.charAt(equation.length() - 1));
        if (isEqualParenthesis(equation) || last.equals("(") || last.matches(util.operator.pattern())) {
            equationConcat("(");
        } else {
            equationConcat(")");
        }
    }

    private boolean isEqualParenthesis(String equation) {
        int open = 0, close = 0;
        for (char ch : equation.toCharArray()) {
            if (ch == '(') {
                open++;
            }
            if (ch == ')') {
                close++;
            }
        }
        return open == close;
    }

    private void SquareRoot(ActionEvent e) {
        equationConcat("√(");
    }

    private void PowerY(ActionEvent e) {
        equationConcat("^(");
    }

    private void PowerTwo(ActionEvent e) {
        equationConcat("^(2)");
    }

    private void negate(ActionEvent e) {
        String expression = EquationLabel.getText();
        if (EquationLabel.getText().matches("|\\d")) {
            EquationLabel.setText("(-".concat(EquationLabel.getText()));
            return;
        }
        if (expression.substring(0, 2).matches("\\(-")) {
            EquationLabel.setText(EquationLabel.getText().substring(2));
        } else {
            EquationLabel.setText("(-".concat(EquationLabel.getText()));
        }
    }

    @SuppressWarnings("ALL")
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Educational license - James Taylour
        MainForm = new JPanel();
        menuBar = new JMenuBar();
        File = new JMenu();
        exit = new JMenuItem();
        centrePanel = new JPanel();
        displayPanel = new JPanel();
        ResultLabel = new JLabel();
        EquationLabel = new JLabel();
        buttonPanel = new JPanel();
        Parentheses = new JButton();
        ClearEntry = new JButton();
        Clear = new JButton();
        Delete = new JButton();
        PowerTwo = new JButton();
        PowerY = new JButton();
        SquareRoot = new JButton();
        Divide = new JButton();
        Seven = new JButton();
        Eight = new JButton();
        Nine = new JButton();
        Multiply = new JButton();
        Four = new JButton();
        Five = new JButton();
        Six = new JButton();
        Subtract = new JButton();
        One = new JButton();
        Two = new JButton();
        Three = new JButton();
        Add = new JButton();
        PlusMinus = new JButton();
        Zero = new JButton();
        Dot = new JButton();
        Equals = new JButton();
        hSpacer2 = new JPanel(null);
        hSpacer3 = new JPanel(null);

        //======== MainForm ========
        {
            MainForm.setBorder(null);
            MainForm.setName("MainForm"); //NON-NLS
            MainForm.setLayout(new BorderLayout());

            //======== menuBar ========
            {
                menuBar.setName("menuBar"); //NON-NLS

                //======== File ========
                {
                    File.setText("File"); //NON-NLS
                    File.setName("File"); //NON-NLS

                    //---- exit ----
                    exit.setText("Exit"); //NON-NLS
                    exit.setName("exit"); //NON-NLS
                    exit.addActionListener(e -> exit(e));
                    File.add(exit);
                }
                menuBar.add(File);
            }
            MainForm.add(menuBar, BorderLayout.NORTH);

            //======== centrePanel ========
            {
                centrePanel.setName("centrePanel"); //NON-NLS
                centrePanel.setLayout(new GridBagLayout());
                ((GridBagLayout)centrePanel.getLayout()).columnWeights = new double[] {1.0};
                ((GridBagLayout)centrePanel.getLayout()).rowWeights = new double[] {1.0, 1.0};

                //======== displayPanel ========
                {
                    displayPanel.setName("displayPanel"); //NON-NLS
                    displayPanel.setLayout(new GridLayout(2, 0));

                    //---- ResultLabel ----
                    ResultLabel.setFont(new Font("Segoe UI", Font.BOLD, 48)); //NON-NLS
                    ResultLabel.setHorizontalAlignment(SwingConstants.TRAILING);
                    ResultLabel.setText("0"); //NON-NLS
                    ResultLabel.setName("ResultLabel"); //NON-NLS
                    displayPanel.add(ResultLabel);

                    //---- EquationLabel ----
                    EquationLabel.setHorizontalAlignment(SwingConstants.TRAILING);
                    EquationLabel.setFont(new Font("Tahoma", Font.PLAIN, 16)); //NON-NLS
                    EquationLabel.setName("EquationLabel"); //NON-NLS
                    displayPanel.add(EquationLabel);
                }
                centrePanel.add(displayPanel, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

                //======== buttonPanel ========
                {
                    buttonPanel.setName("buttonPanel"); //NON-NLS
                    buttonPanel.setLayout(new GridLayout(6, 4));

                    //---- Parentheses ----
                    Parentheses.setText("( )"); //NON-NLS
                    Parentheses.setPreferredSize(new Dimension(45, 45));
                    Parentheses.setName("Parentheses"); //NON-NLS
                    Parentheses.addActionListener(e -> parenthetical(e));
                    buttonPanel.add(Parentheses);

                    //---- ClearEntry ----
                    ClearEntry.setText("CE"); //NON-NLS
                    ClearEntry.setPreferredSize(new Dimension(45, 45));
                    ClearEntry.setName("ClearEntry"); //NON-NLS
                    ClearEntry.addActionListener(e -> Clear(e));
                    buttonPanel.add(ClearEntry);

                    //---- Clear ----
                    Clear.setText("C"); //NON-NLS
                    Clear.setPreferredSize(new Dimension(45, 45));
                    Clear.setName("Clear"); //NON-NLS
                    Clear.addActionListener(e -> Clear(e));
                    buttonPanel.add(Clear);

                    //---- Delete ----
                    Delete.setText("Del"); //NON-NLS
                    Delete.setPreferredSize(new Dimension(45, 45));
                    Delete.setName("Delete"); //NON-NLS
                    Delete.addActionListener(e -> Delete(e));
                    buttonPanel.add(Delete);

                    //---- PowerTwo ----
                    PowerTwo.setText("x\u00b2"); //NON-NLS
                    PowerTwo.setPreferredSize(new Dimension(45, 45));
                    PowerTwo.setName("PowerTwo"); //NON-NLS
                    PowerTwo.addActionListener(e -> PowerTwo(e));
                    buttonPanel.add(PowerTwo);

                    //---- PowerY ----
                    PowerY.setText("x\u02b8"); //NON-NLS
                    PowerY.setPreferredSize(new Dimension(45, 45));
                    PowerY.setName("PowerY"); //NON-NLS
                    PowerY.addActionListener(e -> PowerY(e));
                    buttonPanel.add(PowerY);

                    //---- SquareRoot ----
                    SquareRoot.setText("\u221a"); //NON-NLS
                    SquareRoot.setPreferredSize(new Dimension(45, 45));
                    SquareRoot.setName("SquareRoot"); //NON-NLS
                    SquareRoot.addActionListener(e -> SquareRoot(e));
                    buttonPanel.add(SquareRoot);

                    //---- Divide ----
                    Divide.setText("\u00f7"); //NON-NLS
                    Divide.setPreferredSize(new Dimension(45, 45));
                    Divide.setName("Divide"); //NON-NLS
                    Divide.addActionListener(e -> opConcat(e));
                    buttonPanel.add(Divide);

                    //---- Seven ----
                    Seven.setText("7"); //NON-NLS
                    Seven.setPreferredSize(new Dimension(45, 45));
                    Seven.setName("Seven"); //NON-NLS
                    Seven.addActionListener(e -> faceTextEquationConcat(e));
                    buttonPanel.add(Seven);

                    //---- Eight ----
                    Eight.setText("8"); //NON-NLS
                    Eight.setPreferredSize(new Dimension(45, 45));
                    Eight.setName("Eight"); //NON-NLS
                    Eight.addActionListener(e -> faceTextEquationConcat(e));
                    buttonPanel.add(Eight);

                    //---- Nine ----
                    Nine.setText("9"); //NON-NLS
                    Nine.setPreferredSize(new Dimension(45, 45));
                    Nine.setName("Nine"); //NON-NLS
                    Nine.addActionListener(e -> faceTextEquationConcat(e));
                    buttonPanel.add(Nine);

                    //---- Multiply ----
                    Multiply.setText("\u00d7"); //NON-NLS
                    Multiply.setPreferredSize(new Dimension(45, 45));
                    Multiply.setName("Multiply"); //NON-NLS
                    Multiply.addActionListener(e -> opConcat(e));
                    buttonPanel.add(Multiply);

                    //---- Four ----
                    Four.setText("4"); //NON-NLS
                    Four.setPreferredSize(new Dimension(45, 45));
                    Four.setName("Four"); //NON-NLS
                    Four.addActionListener(e -> faceTextEquationConcat(e));
                    buttonPanel.add(Four);

                    //---- Five ----
                    Five.setText("5"); //NON-NLS
                    Five.setPreferredSize(new Dimension(45, 45));
                    Five.setName("Five"); //NON-NLS
                    Five.addActionListener(e -> faceTextEquationConcat(e));
                    buttonPanel.add(Five);

                    //---- Six ----
                    Six.setText("6"); //NON-NLS
                    Six.setPreferredSize(new Dimension(45, 45));
                    Six.setName("Six"); //NON-NLS
                    Six.addActionListener(e -> faceTextEquationConcat(e));
                    buttonPanel.add(Six);

                    //---- Subtract ----
                    Subtract.setText("-"); //NON-NLS
                    Subtract.setPreferredSize(new Dimension(45, 45));
                    Subtract.setName("Subtract"); //NON-NLS
                    Subtract.addActionListener(e -> opConcat(e));
                    buttonPanel.add(Subtract);

                    //---- One ----
                    One.setText("1"); //NON-NLS
                    One.setPreferredSize(new Dimension(45, 45));
                    One.setName("One"); //NON-NLS
                    One.addActionListener(e -> faceTextEquationConcat(e));
                    buttonPanel.add(One);

                    //---- Two ----
                    Two.setText("2"); //NON-NLS
                    Two.setPreferredSize(new Dimension(45, 45));
                    Two.setName("Two"); //NON-NLS
                    Two.addActionListener(e -> faceTextEquationConcat(e));
                    buttonPanel.add(Two);

                    //---- Three ----
                    Three.setText("3"); //NON-NLS
                    Three.setPreferredSize(new Dimension(45, 45));
                    Three.setSelectedIcon(null);
                    Three.setName("Three"); //NON-NLS
                    Three.addActionListener(e -> faceTextEquationConcat(e));
                    buttonPanel.add(Three);

                    //---- Add ----
                    Add.setText("+"); //NON-NLS
                    Add.setPreferredSize(new Dimension(45, 45));
                    Add.setName("Add"); //NON-NLS
                    Add.addActionListener(e -> opConcat(e));
                    buttonPanel.add(Add);

                    //---- PlusMinus ----
                    PlusMinus.setText("\u00b1"); //NON-NLS
                    PlusMinus.setPreferredSize(new Dimension(45, 45));
                    PlusMinus.setName("PlusMinus"); //NON-NLS
                    PlusMinus.addActionListener(e -> negate(e));
                    buttonPanel.add(PlusMinus);

                    //---- Zero ----
                    Zero.setText("0"); //NON-NLS
                    Zero.setPreferredSize(new Dimension(45, 45));
                    Zero.setName("Zero"); //NON-NLS
                    Zero.addActionListener(e -> faceTextEquationConcat(e));
                    buttonPanel.add(Zero);

                    //---- Dot ----
                    Dot.setText("."); //NON-NLS
                    Dot.setPreferredSize(new Dimension(45, 45));
                    Dot.setName("Dot"); //NON-NLS
                    Dot.addActionListener(e -> faceTextEquationConcat(e));
                    buttonPanel.add(Dot);

                    //---- Equals ----
                    Equals.setText("="); //NON-NLS
                    Equals.setPreferredSize(new Dimension(45, 45));
                    Equals.setName("Equals"); //NON-NLS
                    Equals.addActionListener(e -> Equals(e));
                    buttonPanel.add(Equals);
                }
                centrePanel.add(buttonPanel, new GridBagConstraints(0, 1, 1, 1, 0.0, 2.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            MainForm.add(centrePanel, BorderLayout.CENTER);

            //---- hSpacer2 ----
            hSpacer2.setName("hSpacer2"); //NON-NLS
            MainForm.add(hSpacer2, BorderLayout.EAST);

            //---- hSpacer3 ----
            hSpacer3.setName("hSpacer3"); //NON-NLS
            MainForm.add(hSpacer3, BorderLayout.WEST);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    @SuppressWarnings("GrazieInspection")
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Educational license - James Taylour
    private JPanel MainForm;
    private JMenuBar menuBar;
    private JMenu File;
    private JMenuItem exit;
    private JPanel centrePanel;
    private JPanel displayPanel;
    private JLabel ResultLabel;
    private JLabel EquationLabel;
    private JPanel buttonPanel;
    private JButton Parentheses;
    private JButton ClearEntry;
    private JButton Clear;
    private JButton Delete;
    private JButton PowerTwo;
    private JButton PowerY;
    private JButton SquareRoot;
    private JButton Divide;
    private JButton Seven;
    private JButton Eight;
    private JButton Nine;
    private JButton Multiply;
    private JButton Four;
    private JButton Five;
    private JButton Six;
    private JButton Subtract;
    private JButton One;
    private JButton Two;
    private JButton Three;
    private JButton Add;
    private JButton PlusMinus;
    private JButton Zero;
    private JButton Dot;
    private JButton Equals;
    private JPanel hSpacer2;
    private JPanel hSpacer3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
