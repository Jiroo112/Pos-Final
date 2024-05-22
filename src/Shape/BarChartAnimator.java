package Shape;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartAnimator {

    private DefaultCategoryDataset dataset;
    private double targetValue;
    private double currentValue;
    private int seriesIndex;
    private int categoryIndex;
    private Animator animator;

    public BarChartAnimator(DefaultCategoryDataset dataset, int seriesIndex, int categoryIndex, double targetValue) {
        this.dataset = dataset;
        this.seriesIndex = seriesIndex;
        this.categoryIndex = categoryIndex;
        this.targetValue = targetValue;
        this.currentValue = dataset.getValue(seriesIndex, categoryIndex).doubleValue();
    }

    public void animate() {
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double value = currentValue + (targetValue - currentValue) * fraction;
                dataset.setValue(value, dataset.getRowKey(seriesIndex), dataset.getColumnKey(categoryIndex));
            }
        };
        animator = new Animator(800, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
    }
}
