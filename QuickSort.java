import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class QuickSort extends JPanel {
    private static final long serialVersionUID = 1L;
    private final int WIDTH = 1000, HEIGHT = WIDTH * 9 / 16;
    private int SIZE = 200; // Updated: made non-final
    private float BAR_WIDTH = (float) WIDTH / SIZE; // Updated: made non-final
    private float[] bar_height = new float[SIZE];
    private SwingWorker<Void, Void> sorter;
    private int[] indices;
    private int sortingDelay = 10; // Default sorting delay in milliseconds
    private JSlider sizeSlider; // Added: slider for bar size

    private QuickSort() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        initBarHeight();
    
        JButton shuffleButton = new JButton("Shuffle");
        shuffleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shuffleBars();
            }
        });
    
        JButton sortButton = new JButton("Sort");
        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortBars();
            }
        });
    
        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 10000, 5000); // Increase the maximum value
        speedSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int value = source.getValue();
                    if (value == 10000) {
                        sortingDelay = 1; // Set minimum delay for maximum speed
                    } else {
                        sortingDelay = 10000 / (value + 1); // Adjust the calculation of sortingDelay
                    }
                }
            }
        });
    
        sizeSlider = new JSlider(JSlider.HORIZONTAL, 10, 1000, SIZE); // Added: slider for bar size
        sizeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int value = source.getValue();
                    updateBarSize(value);
                    shuffleBars(); // Shuffle the bars after changing the size
                }
            }
        });
    
        setLayout(null);
    
        shuffleButton.setBounds(10, 10, 80, 30);
        sortButton.setBounds(100, 10, 80, 30);
        speedSlider.setBounds(200, 10, 200, 30);
        sizeSlider.setBounds(410, 10, 200, 30); // Added: position of the sizeSlider
    
        add(shuffleButton);
        add(sortButton);
        add(speedSlider);
        add(sizeSlider); // Added: sizeSlider added to the layout
    }
    
    @Override
    // Filling bars
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.CYAN);
        Rectangle2D.Float bar;
        for (int i = 0; i < SIZE; i++) {
            float barY = HEIGHT - bar_height[i]; // Adjusting the bar's Y coordinate to flip it upside down
            bar = new Rectangle2D.Float(i * BAR_WIDTH, barY, BAR_WIDTH, bar_height[i]);
            g2d.fill(bar);
        }
    
        g2d.setColor(Color.RED);
        for (int i = 0; i < SIZE; i++) {
            if (i == indices[0] || i == indices[1]) {
                float currentBarY = HEIGHT - bar_height[i]; // Adjusting the current bar's Y coordinate
                bar = new Rectangle2D.Float(i * BAR_WIDTH, currentBarY, BAR_WIDTH, bar_height[i]);
                g2d.fill(bar);
            }
        }
    
        // Draw time and space complexity information
        String complexityInfo = "Worst Time Complexity: O(N^2)\n";
        complexityInfo += "Average Time Complexity: O(N log N)\n";
        complexityInfo += "Best Time Complexity: O(N log N)\n";
        complexityInfo += "Space Complexity: O(log N)";
    
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.setColor(Color.WHITE);
    
        // Calculate the height of a single line of text
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int stringHeight = fontMetrics.getHeight();
    
        // Calculate the position to draw the complexity information
        Insets insets = getInsets();
        int x = getWidth() - insets.right - 10;
        int y = insets.top + stringHeight + 10;
    
        // Draw each line of the complexity information
        String[] lines = complexityInfo.split("\n");
        for (String line : lines) {
            int stringWidth = fontMetrics.stringWidth(line);
            int stringX = x - stringWidth;
            g2d.drawString(line, stringX, y);
            y += stringHeight;
        }
    
    }
    
    // Creating intervals
    private void initBarHeight() {
        float maxHeight = HEIGHT * 0.8f; // Set the maximum bar height to be 80% of the screen height
        float interval = maxHeight / SIZE;
        for (int i = 0; i < SIZE; i++) {
            bar_height[i] = i * interval;
        }
        indices = new int[] { -1, -1 };
    }
    
    // Swap two bars
    private void swap(int indexA, int indexB) {
        float temp = bar_height[indexA];
        bar_height[indexA] = bar_height[indexB];
        bar_height[indexB] = temp;
    }
    
    // Shuffle bars
    private void shuffleBars() {
        SwingWorker<Void, Void> shuffler = new SwingWorker<>() {
            @Override
            public Void doInBackground() throws InterruptedException {
                Random random = new Random();
                for (int i = SIZE - 1; i > 0; i--) {
                    int randomIndex = random.nextInt(i + 1);
                    swap(i, randomIndex);
    
                    Thread.sleep(15);
                    repaint();
                }
                return null;
            }
    
            @Override
            public void done() {
                super.done();
                sorter = createSorter(); // Create a new sorter instance after shuffling
            }
        };
        shuffler.execute();
    }
    
    // Sort bars
    private void sortBars() {
        sorter.execute();
    }
    
    // Sorting through Quick Sort
    private SwingWorker<Void, Void> createSorter() {
        return new SwingWorker<>() {
            @Override
            public Void doInBackground() throws InterruptedException {
                quickSort(0, SIZE - 1);
                return null;
            }
    
            private void quickSort(int low, int high) throws InterruptedException {
                if (low < high) {
                    int pivotIndex = partition(low, high);
                    quickSort(low, pivotIndex - 1);
                    quickSort(pivotIndex + 1, high);
                }
            }
    
            private int partition(int low, int high) throws InterruptedException {
                float pivot = bar_height[high];
                indices[0] = low;
                indices[1] = high;
                repaint();
                Thread.sleep(sortingDelay); // Add delay for visualization
    
                int i = low - 1;
                for (int j = low; j <= high - 1; j++) {
                    if (bar_height[j] < pivot) {
                        i++;
                        swap(i, j);
                        indices[0] = i;
                        indices[1] = j;
                        repaint();
                        Thread.sleep(sortingDelay); // Add delay for visualization
                    }
                }
                swap(i + 1, high);
                indices[0] = i + 1;
                indices[1] = high;
                repaint();
                Thread.sleep(sortingDelay); // Add delay for visualization
    
                return i + 1;
            }
    
            @Override
            public void done() {
                super.done();
                indices[0] = -1;
                indices[1] = -1;
                repaint();
            }
        };
    }
    
    // Update the size of the bars and recalculate the width
    private void updateBarSize(int newSize) {
        SIZE = newSize;
        BAR_WIDTH = (float) WIDTH / SIZE;
        bar_height = new float[SIZE];
        initBarHeight();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Quick Sort Visualization");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new QuickSort());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}    
