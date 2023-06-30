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

public class InsertionSort extends JPanel {
    private static final long serialVersionUID = 1L;
    private final int WIDTH = 1000, HEIGHT = WIDTH * 9 / 16;
    private final int SIZE = 200;
    private final float BAR_WIDTH = (float) WIDTH / SIZE;
    private float[] bar_height = new float[SIZE];
    private SwingWorker<Void, Void> sorter;
    private int currentIndex, traversingIndex;
    private int sortingDelay = 10; // Default sorting delay in milliseconds

    private InsertionSort() {
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

        setLayout(null);

        shuffleButton.setBounds(10, 10, 80, 30);
        sortButton.setBounds(100, 10, 80, 30);
        speedSlider.setBounds(200, 10, 200, 30);

        add(shuffleButton);
        add(sortButton);
        add(speedSlider);
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
        float currentBarY = HEIGHT - bar_height[currentIndex]; // Adjusting the current bar's Y coordinate
        bar = new Rectangle2D.Float(currentIndex * BAR_WIDTH, currentBarY, BAR_WIDTH, bar_height[currentIndex]);
        g2d.fill(bar);

        g2d.setColor(Color.GREEN);
        float traversingBarY = HEIGHT - bar_height[traversingIndex]; // Adjusting the traversing bar's Y coordinate
        bar = new Rectangle2D.Float(traversingIndex * BAR_WIDTH, traversingBarY, BAR_WIDTH, bar_height[traversingIndex]);
        g2d.fill(bar);

        // Draw time and space complexity information
        String complexityInfo = "Worst Time Complexity: O(N^2)\n";
        complexityInfo += "Average Time Complexity: O(N^2)\n";
        complexityInfo += "Best Time Complexity: O(N)\n";
        complexityInfo += "Space Complexity: O(1)";
        
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
                int middle = SIZE / 2;
                for (int i = 0, j = middle; i < middle; i++, j++) {
                    int randomIndex = new Random().nextInt(SIZE);
                    swap(i, randomIndex);

                    randomIndex = new Random().nextInt(SIZE);
                    swap(j, randomIndex);

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

    // Sorting through Insertion Sort
    private SwingWorker<Void, Void> createSorter() {
        return new SwingWorker<>() {
            @Override
            public Void doInBackground() throws InterruptedException {
                for (currentIndex = 1; currentIndex < SIZE; currentIndex++) {
                    traversingIndex = currentIndex;
                    while (traversingIndex > 0 && bar_height[traversingIndex] < bar_height[traversingIndex - 1]) {
                        swap(traversingIndex, traversingIndex - 1);
                        traversingIndex--;

                        Thread.sleep(sortingDelay);
                        repaint();
                    }
                }

                currentIndex = 0;
                traversingIndex = 0;

                return null;
            }
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Insertion Sort Visualizer");
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new InsertionSort());
            frame.validate();
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
