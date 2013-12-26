/*
 * Copyright (C) 2013 Tuason Software Inc.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package ch.tuason.djbattlescore.lib.components;

import ch.tuason.djbattlescore.lib.MainController;
import ch.tuason.djbattlescore.lib.data.entities.DjEntity;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author maesi
 */
public class BattleBarChart extends StackedBarChart {
   
    private static NumberAxis yAxis;
    private static CategoryAxis xAxis;

    private final MainController mController;
    
    // the glow for the mouse behaviour of the bars...
    private final Glow glow = new Glow(.8);
    
    
    /**
     * constructor
     * @param controller a MainController instance.
     */
    public BattleBarChart(MainController controller) {
        super(getXAxisObject(), getYAxisObject());
        this.mController = controller;
        
        //getXAxisObject().setLabel("DJ");
        getYAxisObject().setLabel("Votes");
        
        this.setTitle("..:: DJ Battle Scoreboard - Kurhaus Lenzerheide ::..");
        
        this.setLegendVisible(false);
        
        // let us set all dj names with sound styles to the x axis...
        getXAxisObject().setCategories(FXCollections.<String>observableArrayList(
                controller.getDataHandler().getDJNamesWithSoundStyle())
        );
        
        updateChartWithCurrentData();
        // the following code is already in the method updateChartWithCurrentData...
        // XYChart.Series dataSerie = new XYChart.Series();
        // getData().addAll(addDataToSerie(dataSerie));
        
        //dataSerie.getData().add(this) 
    }
    
    
    /**
     * updates the chart with the current data...
     */
    public final void updateChartWithCurrentData() {
        getData().clear();
        XYChart.Series dataSerie = new XYChart.Series();
        dataSerie = addDataToSerie(dataSerie);
        getData().addAll(dataSerie);
        setupBarMouseEventHandler(dataSerie);
    }
    
    
    
    private XYChart.Series addDataToSerie(XYChart.Series serie) {
        serie.getData().clear();
        for (DjEntity dj : getController().getDataHandler().getDJList()) {
            serie.getData().add(
                new XYChart.Data(
                    dj.getDjNameWithSoundStyle(), 
                    dj.getVotes()
                )
            );
        }
        return serie;
    }
    
    
    /**
     * method to set up all event listeners for the several bars...
     * 
     * @param series 
     */
    private void setupBarMouseEventHandler(XYChart.Series<String, Number> series) {
        for (final XYChart.Data<String, Number> seriesData : series.getData()) {
            final Node node = seriesData.getNode();

            node.setEffect(null);
            
            // mouse entered - change the glow...
            node.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    node.setEffect(glow);
                }
            });
           
            // mouse exited - clear the glow...
            node.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    node.setEffect(null);
                }
            });
            
            // click on a bar...
            node.setOnMouseClicked(new EventHandler<MouseEvent>() {
                
                @Override
                public void handle(MouseEvent e) {
                    // System.out.println("openDetailsScreen(<selected Bar>)");
                    System.out.println(seriesData.getXValue() + " : " + seriesData.getYValue());
                    
                    // TODO update the dj data object for the clicked bar/dj!
                    // 
                    DjEntity dj = getController().getDataHandler().getDjEntityWithName(seriesData.getXValue());
                    if (dj != null) {
                        dj.setVotes(dj.getVotes() + 1);
                    }
                    
                    int numberOfVotes = seriesData.getYValue().intValue();
                    
                    // check the scale of the y-axis... maybe we need to increase it...
                    if ((numberOfVotes + 1) >= getYAxisObject().getUpperBound()) {
                        increaseUpperBoundForYAxis();
                    }
                    
                    seriesData.setYValue(numberOfVotes + 1);
                }
            });
        }
    }
    
    
    private void increaseUpperBoundForYAxis() {
        getYAxisObject().setUpperBound(getYAxisObject().getUpperBound() * 2);
        getYAxisObject().setTickUnit(getYAxisObject().getUpperBound() / 10);
    }
    
    
    protected static NumberAxis getYAxisObject() {
        if (yAxis == null) {
            yAxis = new NumberAxis();
            yAxis.setAnimated(true);
            yAxis.setAutoRanging(false);
            yAxis.setForceZeroInRange(true);
            yAxis.setTickUnit(10);
            yAxis.setUpperBound(100);
        }
        return yAxis;
    }
    
    
    protected static CategoryAxis getXAxisObject() {
        if (xAxis == null) {
            xAxis = new CategoryAxis();
        }
        return xAxis;
    }
    
    
    private MainController getController() {
        return this.mController;
    }
}
