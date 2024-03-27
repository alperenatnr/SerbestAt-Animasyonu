/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serbestatis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import javax.swing.JFrame;
import javax.swing.*;

/**
 *
 * @author alper
 */
public class SerbestAtisAnimasyonu extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int GROUND_HEIGHT = 50;
    private static final int BALL_RADIUS = 10;
    private static final double GRAVITY = 0.5;

    private double ballX;
    private double ballY;
    double speedX;
    double speedY;

    public SerbestAtisAnimasyonu() {
        ballX = 50;
        ballY = HEIGHT - GROUND_HEIGHT - BALL_RADIUS;

        // Timer kullanarak animasyonu güncelle
        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });
        timer.start();

        // Kullanıcıdan açı ve hız bilgilerini al
        double angle = Double.parseDouble(javax.swing.JOptionPane.showInputDialog("Lütfen açıyı (derece cinsinden) girin: "));
        double speed = Double.parseDouble(javax.swing.JOptionPane.showInputDialog("Lütfen hızı girin: "));

        // Açıyı radian cinsine çevir
        double angleRad = Math.toRadians(angle);

        // Başlangıç hızları
        speedX = speed * Math.cos(angleRad);
        speedY = -speed * Math.sin(angleRad);
    }

    private void update() {
        // Hareketi güncelle
        ballX += speedX;
        ballY += speedY;
        speedY += GRAVITY;

        // Yerde durma kontrolü
        if (ballY >= HEIGHT - GROUND_HEIGHT - BALL_RADIUS) {
            ballY = HEIGHT - GROUND_HEIGHT - BALL_RADIUS;
            speedY *= -0.8; // Zeminde yavaşlatma (sürtünme) efekti
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Zemini çiz
        g.setColor(Color.WHITE);
        g.fillRect(0, HEIGHT - GROUND_HEIGHT, WIDTH, GROUND_HEIGHT);

        // Topu çiz
        g.setColor(Color.RED);
        g.fillOval((int) ballX, (int) ballY, BALL_RADIUS * 2, BALL_RADIUS * 2);
    }

}
