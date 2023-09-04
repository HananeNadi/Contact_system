package com.ensah.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Acceuil extends JPanel {

    JPanel panel1;
    private JFrame frame;

    private BufferedImage background;
    private final JButton contactButton,GroupButton;



    public Acceuil() {
        loadBackgroundImage();
        setLayout(null);
        panel1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, null);
            }
        };

        panel1.setLayout(null);
        panel1.setPreferredSize(new Dimension(background.getWidth(), background.getHeight()));
        
        JLabel label=new JLabel();
        label.setText("Contact System");
        label.setFont(new Font("Cheap Pine", Font.PLAIN, 36));
        label.setForeground(Color.decode("#3979bc"));
        label.setBounds(610,100,600,50);
        panel1.add(label);



        // Create  buttons with images
        contactButton = createButton("Contact", "/conIco.png", 650, 200, 200, 50);
        GroupButton = createButton("Groupe", "/grouIco.png", 650, 270, 200, 50);


        // Add buttons to the panel
        panel1.add(contactButton);
        panel1.add(GroupButton);



        frame = new JFrame("Contact manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private void loadBackgroundImage() {
        try {
            background = ImageIO.read(Objects.requireNonNull(Acceuil.class.getResourceAsStream("/Contact.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JButton createButton(String name,String imagePath, int x, int y, int width, int height) {
        JButton button = new JButton(name);
        button.setIcon(new ImageIcon(Objects.requireNonNull(Acceuil.class.getResource(imagePath))));
        button.setBounds(x, y, width, height);
        button.setBackground(Color.decode("#3979bc"));
        button.setBorder(new LineBorder(Color.decode("#3979bc"), 0));
        button.setFocusPainted(false);
        button.setFont(new Font("Cheap Pine", Font.PLAIN, 24));
        button.setForeground(Color.white);

        button.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e){
                if (button == contactButton) {

                    frame.getContentPane().removeAll();
                    try {
                        frame.getContentPane().add(new Contact_test().contactPanel);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.setVisible(true);



                }

                if (button == GroupButton) {
                    frame.getContentPane().removeAll();
                    try {
                        frame.getContentPane().add(new Group_test().groupPanel);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.setVisible(true);

                }

            }

        });
        return button;
    }


}
