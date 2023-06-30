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

public class MergeSort extends JPanel {
    private static final long serialVersionUID = 1L;
    private final int WIDTH = 1000, HEIGHT = WIDTH * 9 / 16;
    private int SIZE = 200;
    private float BAR_WIDTH = (float) WIDTH / SIZE;
    private float[] bar_height = new float[SIZE];
    private SwingWorker<Void, Void> sorter;
    private int currentIndex, traversingIndex;
    private int sortingDelay = 10;
    private Color currentColor = Color.RED;
    private Color traversingColor = Color.GREEN;

    private JSlider sizeSlider;

    private MergeSort() {
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

        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 10000, 5000);
        speedSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int value = source.getValue();
                    if (value == 10000) {
                        sortingDelay = 1;
                    } else {
                        sortingDelay = 10000 / (value + 1);
                    }
                }
            }
        });

        sizeSlider = new JSlider(JSlider.HORIZONTAL, 10, 1000, SIZE);
        sizeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int value = source.getValue();
                    updateBarSize(value);
                    shuffleBars();
                }
            }
        });

        setLayout(null);

        shuffleButton.setBounds(10, 10, 80, 30);
        sortButton.setBounds(100, 10, 80, 30);
        speedSlider.setBounds(200, 10, 200, 30);
        sizeSlider.setBounds(410, 10, 200, 30);

        add(shuffleButton);
        add(sortButton);
        add(speedSlider);
        add(sizeSlider);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.CYAN);
        Rectangle2D.Float bar;
        for (int i = 0; i < SIZE; i++) {
            float barY = HEIGHT - bar_height[i];
            bar = new Rectangle2D.Float(i * BAR_WIDTH, barY, BAR_WIDTH, bar_height[i]);
            g2d.fill(bar);
        }

        g2d.setColor(currentColor);
        float currentBarY = HEIGHT - bar_height[currentIndex];
        bar = new Rectangle2D.Float(currentIndex * BAR_WIDTH, currentBarY, BAR_WIDTH, bar_height[currentIndex]);
        g2d.fill(bar);

        g2d.setColor(traversingColor);
        float traversingBarY = HEIGHT - bar_height[traversingIndex];
        bar = new Rectangle2D.Float(traversingIndex * BAR_WIDTH, traversingBarY, BAR_WIDTH, bar_height[traversingIndex]);
        g2d.fill(bar);

        String complexityInfo = "Time Complexity: O(N log N)\n";
        complexityInfo += "Space Complexity: O(N)";

        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.setColor(Color.WHITE);

        FontMetrics fontMetrics = g2d.getFontMetrics();
        int stringHeight = fontMetrics.getHeight();

        Insets insets = getInsets();
        int x = getWidth() - insets.right - 10;
        int y = insets.top + stringHeight + 10;

        String[] lines = complexityInfo.split("\n");
        for (String line : lines) {
            int stringWidth = fontMetrics.stringWidth(line);
            int stringX = x - stringWidth;
            g2d.drawString(line, stringX, y);
            y += stringHeight;
        }
    }

    private void initBarHeight() {
        float maxHeight = HEIGHT * 0.8f;
        float interval = maxHeight / SIZE;
        for (int i = 0; i < SIZE; i++) {
            bar_height[i] = i * interval;
        }
    }

    private void swap(int indexA, int indexB) {
        float temp = bar_height[indexA];
        bar_height[indexA] = bar_height[indexB];
        bar_height[indexB] = temp;
    }

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
                sorter = createSorter();
            }
        };
        shuffler.execute();
    }

    private void sortBars() {
        sorter.execute();
    }

    private SwingWorker<Void, Void> createSorter() {
        return new SwingWorker<>() {
            @Override
            public Void doInBackground() throws InterruptedException {
                mergeSort(0, SIZE - 1);
                currentIndex = 0;
                traversingIndex = 0;
                currentColor = Color.RED;
                traversingColor = Color.GREEN;
                repaint();
                return null;
            }
        };
    }

    private void mergeSort(int low, int high) throws InterruptedException {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(low, mid);
            mergeSort(mid + 1, high);
            merge(low, mid, high);
        }
    }

    private void merge(int low, int mid, int high) throws InterruptedException {
        int leftSize = mid - low + 1;
        int rightSize = high - mid;

        float[] leftArray = new float[leftSize];
        float[] rightArray = new float[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = bar_height[low + i];
        }

        for (int j = 0; j < rightSize; j++) {
            rightArray[j] = bar_height[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = low;
        while (i < leftSize && j < rightSize) {
            if (leftArray[i] <= rightArray[j]) {
                bar_height[k] = leftArray[i];
                i++;
            } else {
                bar_height[k] = rightArray[j];
                j++;
            }
            currentIndex = k;
            traversingIndex = j;
            currentColor = Color.RED;
            traversingColor = Color.GREEN;
            repaint();
            Thread.sleep(sortingDelay);
            k++;
        }

        while (i < leftSize) {
            bar_height[k] = leftArray[i];
            currentIndex = k;
            currentColor = Color.RED;
            traversingIndex = j;
            traversingColor = Color.GREEN;
            repaint();
            Thread.sleep(sortingDelay);
            i++;
            k++;
        }

        while (j < rightSize) {
            bar_height[k] = rightArray[j];
            currentIndex = k;
            currentColor = Color.RED;
            traversingIndex = j;
            traversingColor = Color.GREEN;
            repaint();
            Thread.sleep(sortingDelay);
            j++;
            k++;
        }
    }

    private void updateBarSize(int newSize) {
        SIZE = newSize;
        BAR_WIDTH = (float) WIDTH / SIZE;
        bar_height = new float[SIZE];
        initBarHeight();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Merge Sort Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MergeSort());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
