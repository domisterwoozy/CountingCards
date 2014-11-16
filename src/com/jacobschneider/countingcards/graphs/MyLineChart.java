package com.jacobschneider.countingcards.graphs;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;


public class MyLineChart extends JFrame {

	public MyLineChart(XYDataset data) {
		super("Black Jack Strategies");
		
		JPanel chartPanel = createChartPanel(data);
		add(chartPanel, BorderLayout.CENTER);
		
		setSize(640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	private JPanel createChartPanel(XYDataset data) {
		String chartTitle = "Black Jack Strategies - 6 Decks - BJ pays 3:2";
	    String xAxisLabel = "Total Hands";
	    String yAxisLabel = "Player Edge (%)";
	 
	    JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
	            xAxisLabel, yAxisLabel, data);
	    
	    XYPlot plot = ((XYPlot)chart.getPlot());
	    plot.getRangeAxis().setRange(-3,3);
	    
	    Paint paint = Color.BLACK;
	    Stroke stroke = new BasicStroke();
	    Marker marker = new ValueMarker(0, paint, stroke);
	    plot.addRangeMarker(marker);
	 
	    return new ChartPanel(chart);
	}
	
	

}
