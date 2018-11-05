/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class NuGraph extends JFrame {

    public NuGraph(ArrayList<Integer> newMaxFit, ArrayList<Double> newAverageFit) {

        initUI(newMaxFit,newAverageFit);
    }

    private void initUI(ArrayList<Integer> maxFit, ArrayList<Double> averageFit) {

        XYDataset dataset = createDataset(maxFit, averageFit);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Line chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private XYDataset createDataset(ArrayList<Integer> maxFit, ArrayList<Double> averageFit) {

        XYSeries max = new XYSeries("Max Fitness");
        XYSeries avg = new XYSeries("Average Fitness");
        
        //adds new elements to the graph
        for(int i = 0; i < maxFit.size(); i++){
            max.add(i, maxFit.get(i));
            avg.add(i, averageFit.get(i));
        }
        

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(max);
        dataset.addSeries(avg);

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Chromosome Performance", 
                "Time ", 
                "Chromosome Fitness Value", 
                dataset, 
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false 
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Fitness Coorelation",
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;

    }
}
