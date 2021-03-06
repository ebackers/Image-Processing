/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.processing;

import GUI.Gui;
import csvIO.CSVIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultFormatter;
import layer.Layer;

/**
 *
 * @author Arces
 */
public class ImageView extends javax.swing.JFrame {

    private BufferedImage displayImage;
    private Layer layer;
    private JFileChooser jfc;
    private JFormattedTextField xTextField, yTextField, widthTextField, heightTextField, kTextField, mTextField;
    private JComponent xComp, yComp, widthComp, heightComp, kComp, mComp;
    private ArrayList<BufferedImage> displayImages, originalImages;
    private int currentIndex;
    private boolean hasTemplate = false, hasImages = false;
    private final int size = 720;
    private CSVIO csvReader;

    private ViewPanel inputPanel;
    private ViewPanel outputPanel;

    public ImageView() {
        layer = new Layer(size, size);
        //layer = new Layer(200, 200);
        displayImages = new ArrayList<>();
        originalImages = new ArrayList<>();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImageView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        initComponents();

        this.setLocationRelativeTo(null);
        this.add(imagePanel);
        this.setVisible(true);
        jfc = new JFileChooser();
        FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter("JPG file", "jpg");
        FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("CSV file", "csv");
        jfc.addChoosableFileFilter(jpgFilter);
        jfc.addChoosableFileFilter(csvFilter);

        xComp = cropXSpinner.getEditor();
        xTextField = (JFormattedTextField) xComp.getComponent(0);
        DefaultFormatter xFormatter = (DefaultFormatter) xTextField.getFormatter();
        xFormatter.setCommitsOnValidEdit(true);

        yComp = cropYSpinner.getEditor();
        yTextField = (JFormattedTextField) yComp.getComponent(0);
        DefaultFormatter yFormatter = (DefaultFormatter) yTextField.getFormatter();
        yFormatter.setCommitsOnValidEdit(true);

        widthComp = cropWidthSpinner.getEditor();
        widthTextField = (JFormattedTextField) widthComp.getComponent(0);
        DefaultFormatter widthFormatter = (DefaultFormatter) widthTextField.getFormatter();
        widthFormatter.setCommitsOnValidEdit(true);

        heightComp = cropHeightSpinner.getEditor();
        heightTextField = (JFormattedTextField) heightComp.getComponent(0);
        DefaultFormatter heightFormatter = (DefaultFormatter) heightTextField.getFormatter();
        heightFormatter.setCommitsOnValidEdit(true);

        mComp = convolveMSpinner.getEditor();
        mTextField = (JFormattedTextField) mComp.getComponent(0);
        DefaultFormatter mFormatter = (DefaultFormatter) mTextField.getFormatter();
        mFormatter.setCommitsOnValidEdit(true);

        kComp = convolveKSpinner.getEditor();
        kTextField = (JFormattedTextField) kComp.getComponent(0);
        DefaultFormatter kFormatter = (DefaultFormatter) kTextField.getFormatter();
        kFormatter.setCommitsOnValidEdit(true);

        inputPanel = new ViewPanel("Input Images");
        outputPanel = new ViewPanel("Output Images");

        inputPanel.setBounds(0, 0, inputPanel.getPreferredSize().width, inputPanel.getPreferredSize().height);
        outputPanel.setBounds(this.getBounds().x + this.getPreferredSize().width, 0, inputPanel.getPreferredSize().width, inputPanel.getPreferredSize().height);

        inputPanel.setObserver(this);
        outputPanel.setObserver(this);

        inputPanel.setVisible(true);
        outputPanel.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imagePanel = new javax.swing.JScrollPane();
        imageLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        actionPanel = new javax.swing.JPanel();
        loadImageBtn = new javax.swing.JButton();
        rotateCounterClockwiseBtn = new javax.swing.JButton();
        rotateClockwiseBtn = new javax.swing.JButton();
        rotateLabel = new javax.swing.JLabel();
        cropLabel = new javax.swing.JLabel();
        cropXSpinner = new javax.swing.JSpinner();
        cropYSpinner = new javax.swing.JSpinner();
        cropXLabel = new javax.swing.JLabel();
        cropYLabel = new javax.swing.JLabel();
        cropHeightSpinner = new javax.swing.JSpinner();
        cropWidthSpinner = new javax.swing.JSpinner();
        cropWidthLabel = new javax.swing.JLabel();
        cropHeightLabel = new javax.swing.JLabel();
        cropImageBtn = new javax.swing.JButton();
        zoomCombo = new javax.swing.JComboBox();
        zoomLabel = new javax.swing.JLabel();
        alterLabel = new javax.swing.JLabel();
        reverseBtn = new javax.swing.JButton();
        noiseSlider = new javax.swing.JSlider();
        noiseBtn = new javax.swing.JButton();
        brightenSlider = new javax.swing.JSlider();
        brightenBtn = new javax.swing.JButton();
        darkenSlider = new javax.swing.JSlider();
        darkenBtn = new javax.swing.JButton();
        zoomBtn = new javax.swing.JButton();
        peelBtn = new javax.swing.JButton();
        peelLabel = new javax.swing.JLabel();
        convolveKSpinner = new javax.swing.JSpinner();
        convolveKLabel = new javax.swing.JLabel();
        convolveMSpinner = new javax.swing.JSpinner();
        convolveMLabel = new javax.swing.JLabel();
        templateBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        saveImageBtn = new javax.swing.JButton();
        nextBtn = new javax.swing.JButton();
        previousBtn = new javax.swing.JButton();
        templateLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Image Manipulation App");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        imagePanel.setBackground(new java.awt.Color(255, 255, 255));
        imagePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Image Display", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 2, 12))); // NOI18N
        imagePanel.setPreferredSize(new java.awt.Dimension(720, 720));

        imageLabel.setBackground(new java.awt.Color(204, 204, 204));
        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        imageLabel.setPreferredSize(new java.awt.Dimension(722, 720));
        imagePanel.setViewportView(imageLabel);

        jScrollPane1.setBorder(null);

        actionPanel.setBackground(new java.awt.Color(255, 255, 255));
        actionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Actions", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 2, 12))); // NOI18N

        loadImageBtn.setBackground(new java.awt.Color(236, 236, 245));
        loadImageBtn.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        loadImageBtn.setText("Load Image/s");
        loadImageBtn.setFocusable(false);
        loadImageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadImageBtnActionPerformed(evt);
            }
        });

        rotateCounterClockwiseBtn.setBackground(new java.awt.Color(236, 236, 245));
        rotateCounterClockwiseBtn.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        rotateCounterClockwiseBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/counter_clockwise.png"))); // NOI18N
        rotateCounterClockwiseBtn.setToolTipText("rotate counter clockwise");
        rotateCounterClockwiseBtn.setEnabled(false);
        rotateCounterClockwiseBtn.setFocusable(false);
        rotateCounterClockwiseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotateCounterClockwiseBtnActionPerformed(evt);
            }
        });

        rotateClockwiseBtn.setBackground(new java.awt.Color(236, 236, 245));
        rotateClockwiseBtn.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        rotateClockwiseBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/clockwise.png"))); // NOI18N
        rotateClockwiseBtn.setToolTipText("rotate clockwise");
        rotateClockwiseBtn.setEnabled(false);
        rotateClockwiseBtn.setFocusable(false);
        rotateClockwiseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotateClockwiseBtnActionPerformed(evt);
            }
        });

        rotateLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        rotateLabel.setText("Rotate Image");

        cropLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cropLabel.setText("Crop Image");

        cropXSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        cropXSpinner.setEnabled(false);

        cropYSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        cropYSpinner.setEnabled(false);

        cropXLabel.setFont(new java.awt.Font("Century Gothic", 2, 10)); // NOI18N
        cropXLabel.setText("X");
        cropXLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cropYLabel.setFont(new java.awt.Font("Century Gothic", 2, 10)); // NOI18N
        cropYLabel.setText("Y");
        cropYLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cropHeightSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        cropHeightSpinner.setEnabled(false);

        cropWidthSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        cropWidthSpinner.setEnabled(false);

        cropWidthLabel.setFont(new java.awt.Font("Century Gothic", 2, 10)); // NOI18N
        cropWidthLabel.setText("width");
        cropWidthLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cropHeightLabel.setFont(new java.awt.Font("Century Gothic", 2, 10)); // NOI18N
        cropHeightLabel.setText("height");
        cropHeightLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cropImageBtn.setBackground(new java.awt.Color(236, 236, 245));
        cropImageBtn.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        cropImageBtn.setText("Crop Image");
        cropImageBtn.setEnabled(false);
        cropImageBtn.setFocusable(false);
        cropImageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cropImageBtnActionPerformed(evt);
            }
        });

        zoomCombo.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        zoomCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "8" }));
        zoomCombo.setEnabled(false);
        zoomCombo.setFocusable(false);

        zoomLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        zoomLabel.setText("Zoom Image");

        alterLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        alterLabel.setText("Alter Image");
        alterLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        reverseBtn.setBackground(new java.awt.Color(236, 236, 245));
        reverseBtn.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        reverseBtn.setText("Reverse B&W");
        reverseBtn.setEnabled(false);
        reverseBtn.setFocusable(false);
        reverseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reverseBtnActionPerformed(evt);
            }
        });

        noiseSlider.setBackground(new java.awt.Color(255, 255, 255));
        noiseSlider.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        noiseSlider.setMajorTickSpacing(50);
        noiseSlider.setMinorTickSpacing(10);
        noiseSlider.setPaintLabels(true);
        noiseSlider.setPaintTicks(true);
        noiseSlider.setValue(0);
        noiseSlider.setEnabled(false);

        noiseBtn.setBackground(new java.awt.Color(236, 236, 245));
        noiseBtn.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        noiseBtn.setText("Add Noise");
        noiseBtn.setEnabled(false);
        noiseBtn.setFocusable(false);
        noiseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noiseBtnActionPerformed(evt);
            }
        });

        brightenSlider.setBackground(new java.awt.Color(255, 255, 255));
        brightenSlider.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        brightenSlider.setMajorTickSpacing(50);
        brightenSlider.setMinorTickSpacing(10);
        brightenSlider.setPaintLabels(true);
        brightenSlider.setPaintTicks(true);
        brightenSlider.setValue(0);
        brightenSlider.setEnabled(false);

        brightenBtn.setBackground(new java.awt.Color(236, 236, 245));
        brightenBtn.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        brightenBtn.setText("Brighten Image");
        brightenBtn.setEnabled(false);
        brightenBtn.setFocusable(false);
        brightenBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brightenBtnActionPerformed(evt);
            }
        });

        darkenSlider.setBackground(new java.awt.Color(255, 255, 255));
        darkenSlider.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        darkenSlider.setMajorTickSpacing(50);
        darkenSlider.setMinorTickSpacing(10);
        darkenSlider.setPaintLabels(true);
        darkenSlider.setPaintTicks(true);
        darkenSlider.setValue(0);
        darkenSlider.setEnabled(false);

        darkenBtn.setBackground(new java.awt.Color(236, 236, 245));
        darkenBtn.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        darkenBtn.setText("Darken Image");
        darkenBtn.setEnabled(false);
        darkenBtn.setFocusable(false);
        darkenBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                darkenBtnActionPerformed(evt);
            }
        });

        zoomBtn.setBackground(new java.awt.Color(236, 236, 245));
        zoomBtn.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        zoomBtn.setText("Zoom");
        zoomBtn.setEnabled(false);
        zoomBtn.setFocusable(false);
        zoomBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomBtnActionPerformed(evt);
            }
        });

        peelBtn.setBackground(new java.awt.Color(236, 236, 245));
        peelBtn.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        peelBtn.setText("Peel Image");
        peelBtn.setEnabled(false);
        peelBtn.setFocusable(false);
        peelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                peelBtnActionPerformed(evt);
            }
        });

        peelLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        peelLabel.setText("Convolve Images");
        peelLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        convolveKSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        convolveKSpinner.setEnabled(false);

        convolveKLabel.setFont(new java.awt.Font("Century Gothic", 2, 10)); // NOI18N
        convolveKLabel.setText("k");
        convolveKLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        convolveMSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        convolveMSpinner.setEnabled(false);

        convolveMLabel.setFont(new java.awt.Font("Century Gothic", 2, 10)); // NOI18N
        convolveMLabel.setText("m");
        convolveMLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        templateBtn.setBackground(new java.awt.Color(236, 236, 245));
        templateBtn.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        templateBtn.setText("Insert Template");
        templateBtn.setFocusable(false);
        templateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                templateBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout actionPanelLayout = new javax.swing.GroupLayout(actionPanel);
        actionPanel.setLayout(actionPanelLayout);
        actionPanelLayout.setHorizontalGroup(
            actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(actionPanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(zoomLabel)
                            .addComponent(alterLabel))
                        .addGap(59, 59, 59))
                    .addGroup(actionPanelLayout.createSequentialGroup()
                        .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(peelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cropImageBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(loadImageBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(reverseBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(noiseSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(noiseBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(brightenSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(brightenBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(darkenSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(darkenBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, actionPanelLayout.createSequentialGroup()
                                .addComponent(zoomBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(zoomCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(actionPanelLayout.createSequentialGroup()
                                .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(actionPanelLayout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(cropXLabel)
                                        .addGap(52, 52, 52)
                                        .addComponent(cropYLabel))
                                    .addGroup(actionPanelLayout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(rotateLabel))
                                    .addGroup(actionPanelLayout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(cropLabel))
                                    .addGroup(actionPanelLayout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(actionPanelLayout.createSequentialGroup()
                                                .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cropWidthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(actionPanelLayout.createSequentialGroup()
                                                        .addGap(10, 10, 10)
                                                        .addComponent(cropWidthLabel)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cropHeightSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(actionPanelLayout.createSequentialGroup()
                                                        .addGap(10, 10, 10)
                                                        .addComponent(cropHeightLabel))))
                                            .addGroup(actionPanelLayout.createSequentialGroup()
                                                .addComponent(cropXSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cropYSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(actionPanelLayout.createSequentialGroup()
                                .addGap(0, 8, Short.MAX_VALUE)
                                .addComponent(rotateCounterClockwiseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rotateClockwiseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)))
                        .addContainerGap())))
            .addGroup(actionPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(peelLabel)
                    .addGroup(actionPanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(convolveKLabel)
                        .addGap(50, 50, 50)
                        .addComponent(convolveMLabel))
                    .addGroup(actionPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(convolveKSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(convolveMSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(actionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(templateBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        actionPanelLayout.setVerticalGroup(
            actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionPanelLayout.createSequentialGroup()
                .addComponent(loadImageBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(templateBtn)
                .addGap(21, 21, 21)
                .addComponent(rotateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rotateClockwiseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rotateCounterClockwiseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cropLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cropXSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cropYSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cropXLabel)
                    .addComponent(cropYLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cropWidthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cropHeightSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cropWidthLabel)
                    .addComponent(cropHeightLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cropImageBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(zoomLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zoomCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zoomBtn))
                .addGap(18, 18, 18)
                .addComponent(alterLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reverseBtn)
                .addGap(18, 18, 18)
                .addComponent(noiseSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(noiseBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brightenSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(brightenBtn)
                .addGap(7, 7, 7)
                .addComponent(darkenSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(darkenBtn)
                .addGap(18, 18, 18)
                .addComponent(peelLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(convolveKSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(convolveMSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(actionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(convolveKLabel)
                    .addComponent(convolveMLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(peelBtn)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        rotateCounterClockwiseBtn.getAccessibleContext().setAccessibleName("Rotate Clockwise");

        jScrollPane1.setViewportView(actionPanel);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        saveImageBtn.setBackground(new java.awt.Color(255, 255, 255));
        saveImageBtn.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        saveImageBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.png"))); // NOI18N
        saveImageBtn.setText("Save Image");
        saveImageBtn.setEnabled(false);
        saveImageBtn.setFocusable(false);
        saveImageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveImageBtnActionPerformed(evt);
            }
        });

        nextBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/right.png"))); // NOI18N
        nextBtn.setToolTipText("Next Image");
        nextBtn.setEnabled(false);
        nextBtn.setFocusable(false);
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });

        previousBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/left.png"))); // NOI18N
        previousBtn.setToolTipText("Previous Image");
        previousBtn.setEnabled(false);
        previousBtn.setFocusable(false);
        previousBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousBtnActionPerformed(evt);
            }
        });

        templateLabel.setBackground(new java.awt.Color(244, 244, 244));
        templateLabel.setOpaque(true);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setText("Loaded Template:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(previousBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123)
                .addComponent(saveImageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(templateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(nextBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(saveImageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(nextBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(templateLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(previousBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadImageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadImageBtnActionPerformed
        boolean hasLoaded = loadImage();
        if (hasLoaded) {
            currentIndex = 0;
            displayImage = displayImages.get(0);

            imageLabel.setIcon(new ImageIcon(displayImage.getScaledInstance(size, size, BufferedImage.SCALE_SMOOTH)));

            hasImages = true;
            if (hasImages && hasTemplate) {
                enableGUI();
            }
        }
    }//GEN-LAST:event_loadImageBtnActionPerformed

    private void rotateClockwiseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rotateClockwiseBtnActionPerformed
        displayImage = rotateClockwise();
        imageLabel.setIcon(new ImageIcon(displayImage.getScaledInstance(size, size, BufferedImage.SCALE_SMOOTH)));
    }//GEN-LAST:event_rotateClockwiseBtnActionPerformed

    private void rotateCounterClockwiseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rotateCounterClockwiseBtnActionPerformed
        displayImage = rotateCounterClockwise();
        imageLabel.setIcon(new ImageIcon(displayImage.getScaledInstance(size, size, BufferedImage.SCALE_SMOOTH)));
    }//GEN-LAST:event_rotateCounterClockwiseBtnActionPerformed

    private void cropImageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cropImageBtnActionPerformed
        int cropWidth = (Integer) cropWidthSpinner.getValue();
        int cropHeight = (Integer) cropHeightSpinner.getValue();

        if (cropWidth != 0 && cropHeight != 0) {
            displayImage = cropImage(cropWidth, cropHeight);
            imageLabel.setIcon(new ImageIcon(displayImage.getScaledInstance(size, size, BufferedImage.SCALE_SMOOTH)));
        }
    }//GEN-LAST:event_cropImageBtnActionPerformed

    private void reverseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reverseBtnActionPerformed
        displayImage = reverseColor();
        imageLabel.setIcon(new ImageIcon(displayImage.getScaledInstance(size, size, BufferedImage.SCALE_SMOOTH)));
    }//GEN-LAST:event_reverseBtnActionPerformed

    private void noiseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noiseBtnActionPerformed
        Random rand = new Random();
        Color noiseColor = null;

        //calculate noise
        float noisePercent = (float) noiseSlider.getValue() / 100;
        //System.out.println(noisePercent);
        float noiseCount = (float) displayImage.getHeight() * (float) displayImage.getWidth() * noisePercent;
        //System.out.println(noiseCount);

        //apply noise
        for (int i = 0; i < noiseCount; i++) {
            //generate random point
            int x = rand.nextInt(displayImage.getWidth());
            int y = rand.nextInt(displayImage.getHeight());

            //generate black or white color
            int c = rand.nextInt(2);
            switch (c) {
                case 0:
                    noiseColor = new Color(255, 255, 255);
                    break;
                case 1:
                    noiseColor = new Color(0, 0, 0);
                    break;
            }
            displayImage.setRGB(x, y, noiseColor.getRGB());
        }
        imageLabel.setIcon(new ImageIcon(displayImage.getScaledInstance(size, size, BufferedImage.SCALE_SMOOTH)));
    }//GEN-LAST:event_noiseBtnActionPerformed

    private void brightenBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brightenBtnActionPerformed
        displayImage = brightenImage();
        imageLabel.setIcon(new ImageIcon(displayImage.getScaledInstance(size, size, BufferedImage.SCALE_SMOOTH)));
    }//GEN-LAST:event_brightenBtnActionPerformed

    private void darkenBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_darkenBtnActionPerformed
        displayImage = darkenImage();
        imageLabel.setIcon(new ImageIcon(displayImage.getScaledInstance(size, size, BufferedImage.SCALE_SMOOTH)));
    }//GEN-LAST:event_darkenBtnActionPerformed

    private void zoomBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomBtnActionPerformed
        displayImage = zoomImage(displayImage, "zoom", currentIndex);
        imageLabel.setIcon(new ImageIcon(new ImageIcon(displayImage).getImage().getScaledInstance(720, 720, Image.SCALE_SMOOTH)));
    }//GEN-LAST:event_zoomBtnActionPerformed

    private void saveImageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveImageBtnActionPerformed
        int val = jfc.showSaveDialog(this);
        if (val == JFileChooser.APPROVE_OPTION) {
            try {
                String file = jfc.getSelectedFile().toString();
                String extension = file.substring(file.indexOf(".") + 1);

                System.out.println("Saving " + extension + " file. . .");
                switch (extension) {
                    case "jpg":
                        ImageIO.write(displayImage, extension, jfc.getSelectedFile());
                        break;
                    case "csv":
                        createCSVFile(displayImage, file.substring(file.lastIndexOf("/") + 1));
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(ImageView.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Saving successful. . .");
        }

    }//GEN-LAST:event_saveImageBtnActionPerformed

    private void peelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_peelBtnActionPerformed
        int k = (Integer) convolveKSpinner.getValue();
        int m = (Integer) convolveMSpinner.getValue();
        int ctr = 1;
        outputPanel.emptyImages();
        displayImages.clear();
        //restorePile();
        
        if (k != 0 && m != 0) {
            displayImages = layer.extract(k, m);
            currentIndex = 0;
            for (int i = 0; i < displayImages.size(); i++) {
                displayImages.set(i, zoomImage(displayImages.get(i), "convolve", i));
            }

            displayImage = displayImages.get(0);
            imageLabel.setIcon(new ImageIcon(displayImage.getScaledInstance(size, size, BufferedImage.SCALE_SMOOTH)));

            try {
                for (BufferedImage img : displayImages) {
                    outputPanel.addImage(img);
                    createCSVFile(img, "[CSV] Convolved Image " + ctr + ".csv");
                    ImageIO.write(img, "jpg", new File("[JPG] Convolved Image " + ctr + ".jpg"));

                    ctr++;
                }
                
                restorePile();
            } catch (IOException ex) {
                Logger.getLogger(ImageView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_peelBtnActionPerformed

    private void previousBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousBtnActionPerformed
        if (currentIndex > 0) {
            displayImage = displayImages.get(currentIndex - 1);
            currentIndex--;
            imageLabel.setIcon(new ImageIcon(displayImage.getScaledInstance(size, size, BufferedImage.SCALE_SMOOTH)));
        }
    }//GEN-LAST:event_previousBtnActionPerformed

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
        if (currentIndex < displayImages.size() - 1) {
            displayImage = displayImages.get(currentIndex + 1);
            currentIndex++;
            imageLabel.setIcon(new ImageIcon(displayImage.getScaledInstance(size, size, BufferedImage.SCALE_SMOOTH)));
        }
    }//GEN-LAST:event_nextBtnActionPerformed

    private void templateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_templateBtnActionPerformed
        BufferedImage template = null;
        int[][] matrix;

        jfc.setMultiSelectionEnabled(false);
        int val = jfc.showOpenDialog(this);
        if (val == JFileChooser.APPROVE_OPTION) {
            if (jfc.getSelectedFile().getPath().substring(jfc.getSelectedFile().getPath().indexOf("."), jfc.getSelectedFile().getPath().length() - 1).equals("csv")) {
                ArrayList<String> pixels = csvReader.read(jfc.getSelectedFile().getPath());
                int size = pixels.size();
                matrix = new int[size][size];
                int i = 0;
                for (String s : pixels) {
                    String[] toPix = s.split(",");
                    for (int j = 0; j < matrix.length; j++) {
                        matrix[i][j] = Integer.parseInt(toPix[j]);
                    }
                    i++;
                }

                template = toImage(matrix);

                layer.addTemplate(template);

                templateLabel.setIcon(new ImageIcon(template.getScaledInstance(templateLabel.getWidth(), templateLabel.getHeight(), BufferedImage.SCALE_SMOOTH)));
                hasTemplate = true;
                if (hasTemplate && hasImages) {
                    enableGUI();
                }
            } else {
                File templateFile = jfc.getSelectedFile();

                if (templateFile != null) {
                    try {
                        template = ImageIO.read(templateFile);
                    } catch (IOException ex) {
                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    layer.addTemplate(template);

                    templateLabel.setIcon(new ImageIcon(template.getScaledInstance(templateLabel.getWidth(), templateLabel.getHeight(), BufferedImage.SCALE_SMOOTH)));
                    hasTemplate = true;
                    if (hasTemplate && hasImages) {
                        enableGUI();
                    }
                }
            }
        }
    }//GEN-LAST:event_templateBtnActionPerformed

    public boolean loadImage() {
        BufferedImage image;
        boolean hasLoaded = false;
        jfc.setMultiSelectionEnabled(true);
        int val = jfc.showOpenDialog(this);
        if (val == JFileChooser.APPROVE_OPTION) {
            File[] imgFiles = jfc.getSelectedFiles();

            for (File imgFile : imgFiles) {
                if (imgFile != null) {
                    System.out.println("Selected file: " + imgFile.getAbsolutePath());

                    try {
                        image = ImageIO.read(imgFile);
                        layer.addImage(image);
                        inputPanel.addImage(image);
                        displayImages.add(image);
                        originalImages.add(image);
                    } catch (IOException ex) {
                        Logger.getLogger(ImageView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
            hasLoaded = true;
        }

        return hasLoaded;
    }

    public BufferedImage rotateClockwise() {
        BufferedImage cwImage = new BufferedImage(displayImage.getHeight(), displayImage.getWidth(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < displayImage.getHeight(); i++) {
            for (int j = 0; j < displayImage.getWidth(); j++) {
                cwImage.setRGB(i, j, displayImage.getRGB(j, displayImage.getHeight() - 1 - i));
            }
        }
        displayImages.set(currentIndex, cwImage);
        return cwImage;
    }

    public BufferedImage rotateCounterClockwise() {
        BufferedImage ccwImage = new BufferedImage(displayImage.getHeight(), displayImage.getWidth(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < displayImage.getHeight(); i++) {
            for (int j = 0; j < displayImage.getWidth(); j++) {
                ccwImage.setRGB(i, j, displayImage.getRGB(displayImage.getWidth() - 1 - j, i));
            }
        }

        displayImages.set(currentIndex, ccwImage);
        return ccwImage;
    }

    public BufferedImage reverseColor() {
        Color rgb, reverseColor;
        BufferedImage reverseImage = new BufferedImage(displayImage.getWidth(), displayImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < displayImage.getWidth(); i++) {
            for (int j = 0; j < displayImage.getHeight(); j++) {
                rgb = new Color(displayImage.getRGB(i, j));
                reverseColor = new Color(255 - rgb.getRed(), 255 - rgb.getGreen(), 255 - rgb.getBlue());
                reverseImage.setRGB(i, j, reverseColor.getRGB());
            }
        }

        displayImages.set(currentIndex, reverseImage);
        return reverseImage;
    }

    public BufferedImage zoomImage(BufferedImage image, String caller, int index) {
        int zoomTimes = 1;
        switch (caller) {
            case ("zoom"):
                zoomTimes = Integer.parseInt(zoomCombo.getSelectedItem().toString());
                break;
            case ("convolve"):
                zoomTimes = (Integer) convolveMSpinner.getValue();
                break;
        }
        ArrayList<Color> colorList = new ArrayList<>();
        BufferedImage zoomedImage = new BufferedImage((int) Math.floor(image.getWidth() / zoomTimes), (int) Math.floor(image.getHeight() / zoomTimes), BufferedImage.TYPE_INT_RGB);
        System.out.println("size! " + zoomedImage.getWidth() + " x " + zoomedImage.getHeight());
        Color zoomedColor;

        int zoomX = 0;
        int zoomY = 0;

        for (int i = 0; i < image.getWidth(); i += zoomTimes) {
            for (int j = 0; i < image.getWidth() && j < image.getHeight(); j++) {
                colorList.add(new Color(image.getRGB(i, j)));
                if ((j + 1) % zoomTimes == 0) {
                    i++;
                    j -= zoomTimes;
                }
                if (colorList.size() == zoomTimes * zoomTimes) {
                    //System.out.println("number of colors = " + colorList.size());

                    //average colors
                    int aveRed = 0, aveGreen = 0, aveBlue = 0;
                    for (Color c : colorList) {
                        aveRed += c.getRed();
                        aveGreen += c.getGreen();
                        aveBlue += c.getBlue();
                    }
                    aveRed /= colorList.size();
                    aveGreen /= colorList.size();
                    aveBlue /= colorList.size();
                    colorList.clear();

                    //put in zoomed image
                    zoomedColor = new Color(aveRed, aveGreen, aveBlue);
                    // System.out.println("vs " + zoomX + " x " + zoomY);
                    if (zoomX < zoomedImage.getWidth() && zoomY < zoomedImage.getHeight()) {
                        zoomedImage.setRGB(zoomY, zoomX, zoomedColor.getRGB());
                    }
                    zoomX++;
                    if (zoomX > zoomedImage.getWidth() - 1) {
                        zoomX = 0;
                        zoomY++;
                    }

                    i -= zoomTimes;
                    j += zoomTimes;
                }
            }
        }
        displayImages.set(index, zoomedImage);
        return zoomedImage;
    }

    public BufferedImage brightenImage() {
        float brightValue = (float) (1 + (float) brightenSlider.getValue() / 100);

        RescaleOp ro = new RescaleOp(brightValue, 0, null);
        BufferedImage brightImage = ro.filter(displayImage, null);
        displayImages.set(currentIndex, brightImage);
        return brightImage;
    }

    public BufferedImage darkenImage() {
        float darkValue = (float) (1 - (float) darkenSlider.getValue() / 100);

        RescaleOp ro = new RescaleOp(darkValue, 0, null);
        BufferedImage darkImage = ro.filter(displayImage, null);
        displayImages.set(currentIndex, darkImage);
        return darkImage;
    }

    public BufferedImage cropImage(int cropWidth, int cropHeight) {
        int cropX = (Integer) cropXSpinner.getValue();
        int cropY = (Integer) cropYSpinner.getValue();

        //System.out.println("x = " + cropX + " y = " + cropY + " w = " + cropWidth + " h = " + cropHeight);
        BufferedImage croppedImage = new BufferedImage(cropWidth, cropHeight, BufferedImage.TYPE_INT_RGB);

        for (int i = cropX; i < displayImage.getWidth() && i - cropX < cropWidth - 1; i++) {
            for (int j = cropY; j < displayImage.getHeight() && j - cropY < cropHeight - 1; j++) {
                //        System.out.println("i = " + (i - cropX) + " j = " + (j - cropY));
                croppedImage.setRGB(i - cropX, j - cropY, displayImage.getRGB(i, j));
            }
        }

        return croppedImage;
    }

    public void createCSVFile(BufferedImage image, String csvName) {
        //GRAY SCALE ONLY
        Color pixel;
        try {
            FileWriter fw = new FileWriter(csvName);

            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    pixel = new Color(image.getRGB(i, j));
                    fw.write("" + ((pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3));
                    fw.write(",");
                }
                fw.write("\n");
            }

            fw.flush();
            fw.close();

        } catch (IOException ex) {
            Logger.getLogger(ImageView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCurrentImage(BufferedImage image, int index) {
        displayImage = image;
        currentIndex = index;
        imageLabel.setIcon(new ImageIcon(displayImage.getScaledInstance(size, size, BufferedImage.SCALE_SMOOTH)));
    }

    public void enableGUI() {
        saveImageBtn.setEnabled(true);
        rotateClockwiseBtn.setEnabled(true);
        rotateCounterClockwiseBtn.setEnabled(true);
        cropXSpinner.setEnabled(true);
        cropYSpinner.setEnabled(true);
        cropWidthSpinner.setEnabled(true);
        cropHeightSpinner.setEnabled(true);
        cropImageBtn.setEnabled(true);
        zoomBtn.setEnabled(true);
        zoomCombo.setEnabled(true);
        reverseBtn.setEnabled(true);
        noiseSlider.setEnabled(true);
        noiseBtn.setEnabled(true);
        brightenSlider.setEnabled(true);
        brightenBtn.setEnabled(true);
        darkenSlider.setEnabled(true);
        darkenBtn.setEnabled(true);
        convolveKSpinner.setEnabled(true);
        convolveMSpinner.setEnabled(true);
        peelBtn.setEnabled(true);
        previousBtn.setEnabled(true);
        nextBtn.setEnabled(true);

        SpinnerNumberModel xCrop = (SpinnerNumberModel) cropXSpinner.getModel();
        xCrop.setMaximum(displayImage.getWidth());

        SpinnerNumberModel yCrop = (SpinnerNumberModel) cropYSpinner.getModel();
        yCrop.setMaximum(displayImage.getHeight());

        SpinnerNumberModel widthCrop = (SpinnerNumberModel) cropWidthSpinner.getModel();
        widthCrop.setMaximum(displayImage.getWidth());

        SpinnerNumberModel heightCrop = (SpinnerNumberModel) cropHeightSpinner.getModel();
        heightCrop.setMaximum(displayImage.getHeight());

        SpinnerNumberModel kModel = (SpinnerNumberModel) convolveKSpinner.getModel();
        kModel.setMaximum(layer.getPileSize());

        SpinnerNumberModel mModel = (SpinnerNumberModel) convolveMSpinner.getModel();
        mModel.setMaximum(displayImage.getHeight());
    }

    public BufferedImage toImage(int matrix[][]) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int nPixelCount = rows * cols;
        BufferedImage image = new BufferedImage(cols, rows, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < nPixelCount; i++) {
            int row = i / cols;
            int col = i % cols;

            Color c = new Color(matrix[row][col], matrix[row][col], matrix[row][col]);

            image.setRGB(col, row, c.getRGB());
        }

        return image;
    }
    
    public void restorePile(){
        layer.restart();
        for(BufferedImage img: originalImages){
       //     displayImages.add(img);
            layer.addImage(img);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionPanel;
    private javax.swing.JLabel alterLabel;
    private javax.swing.JButton brightenBtn;
    private javax.swing.JSlider brightenSlider;
    private javax.swing.JLabel convolveKLabel;
    private javax.swing.JSpinner convolveKSpinner;
    private javax.swing.JLabel convolveMLabel;
    private javax.swing.JSpinner convolveMSpinner;
    private javax.swing.JLabel cropHeightLabel;
    private javax.swing.JSpinner cropHeightSpinner;
    private javax.swing.JButton cropImageBtn;
    private javax.swing.JLabel cropLabel;
    private javax.swing.JLabel cropWidthLabel;
    private javax.swing.JSpinner cropWidthSpinner;
    private javax.swing.JLabel cropXLabel;
    private javax.swing.JSpinner cropXSpinner;
    private javax.swing.JLabel cropYLabel;
    private javax.swing.JSpinner cropYSpinner;
    private javax.swing.JButton darkenBtn;
    private javax.swing.JSlider darkenSlider;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JScrollPane imagePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadImageBtn;
    private javax.swing.JButton nextBtn;
    private javax.swing.JButton noiseBtn;
    private javax.swing.JSlider noiseSlider;
    private javax.swing.JButton peelBtn;
    private javax.swing.JLabel peelLabel;
    private javax.swing.JButton previousBtn;
    private javax.swing.JButton reverseBtn;
    private javax.swing.JButton rotateClockwiseBtn;
    private javax.swing.JButton rotateCounterClockwiseBtn;
    private javax.swing.JLabel rotateLabel;
    private javax.swing.JButton saveImageBtn;
    private javax.swing.JButton templateBtn;
    private javax.swing.JLabel templateLabel;
    private javax.swing.JButton zoomBtn;
    private javax.swing.JComboBox zoomCombo;
    private javax.swing.JLabel zoomLabel;
    // End of variables declaration//GEN-END:variables
}
