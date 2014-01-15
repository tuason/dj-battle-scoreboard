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

package ch.tuason.djbattlescore.lib.components.comps;

import ch.tuason.djbattlescore.lib.DjBattleConstants;
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
import javafx.scene.text.Font;

/**
 * the main bar chart ui component...
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
        
        getYAxisObject().setLabel("Votes");
        
        this.setLegendVisible(false);
        
        // let us set all dj names with sound styles to the x axis...
        getXAxisObject().setCategories(FXCollections.<String>observableArrayList(
                controller.getDataHandler().getDJNamesWithSoundStyle())
        );
        
        updateChartWithCurrentData();
        // the following code is already in the method updateChartWithCurrentData...
        // XYChart.Series dataSerie = new XYChart.Series();
        // getData().addAll(addDataToSerie(dataSerie));
        
        // update the ranking list...
        getController().getComponentHandler().updateDjRanking();
//        for (TickMark mark : getXAxisObject().getTickMarks()) {
//            System.out.println("tick mark: -->" + mark.getLabel());
//        }
    }
    
    /**
     * resets all chart datas and updates the ranking afterwards...
     */
    public final void resetChartAndAllData() {
        getController().getDataHandler().clearAllVotesBackToZero();
        resetUpperBoundForYAxis();
        updateChartWithCurrentData();
        getController().getComponentHandler().updateDjRanking();
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
    
    
    /**
     * adds a certain serie to th chart...
     * 
     * @param serie Series object
     * @return XYChart.Series
     */
    private XYChart.Series addDataToSerie(XYChart.Series serie) {
        serie.getData().clear();
        for (DjEntity dj : getController().getDataHandler().getDJList()) {
            serie.getData().add(
                new XYChart.Data(
                    dj.getName(), // dj.getDjNameWithSoundStyle()
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
                    
                    // we update the dj data object for the clicked bar/dj!
                    DjEntity justChangedEntity = getController().getDataHandler().getDjEntityWithName(seriesData.getXValue());  //getDjEntityWithNameAndStyle                  
                    getController().getDataHandler().increaseVotesForDj(justChangedEntity);
                   
                    int numberOfVotes = seriesData.getYValue().intValue();                    
                    // check the scale of the y-axis... maybe we need to increase it...
                    if ((numberOfVotes + 1) >= getYAxisObject().getUpperBound()) {
                        increaseUpperBoundForYAxis();
                    }
                    
                    seriesData.setYValue(numberOfVotes + 1);
                    
                    // at the end we might have to update the dj ranking...
                    getController().getComponentHandler().checkIfNeededAndUpdateDjRanking();
                }
            });
        }
    }
    
    
    private void resetUpperBoundForYAxis() {
        getYAxisObject().setTickUnit(DjBattleConstants.CHART_STANDARD_TICK_UNIT_STEPS);
        getYAxisObject().setUpperBound(DjBattleConstants.CHART_STANDARD_UPPER_BOUND_LIMIT);
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
            yAxis.setTickUnit(DjBattleConstants.CHART_STANDARD_TICK_UNIT_STEPS);
            yAxis.setUpperBound(DjBattleConstants.CHART_STANDARD_UPPER_BOUND_LIMIT);
        }
        return yAxis;
    }
    
    
    protected static CategoryAxis getXAxisObject() {
        if (xAxis == null) {
            xAxis = new CategoryAxis();
            
            xAxis.setTickLabelFont(new Font("Arial", 20));
            
            // xAxis.setTickLabelGap(1);
            
            // check also http://docs.oracle.com/javafx/2/charts/css-styles.htm
            //xAxis.setStyle("-fx-font-size: 2.0em; -fx-font-family: Arial; -fx-wrap-text: true; -fx-tick-label-fill: #000000;");
                    
//            xAxis.getTickMarks().addListener(new ListChangeListener() {
//
//                @Override
//                public void onChanged(ListChangeListener.Change change) {
//                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//                    if (change.wasAdded()) {
//                        //System.out.println("changed a tick on the x-axis: " + change.getList().get(change.getList().size()-1));
//                        System.out.println("tick label added to the x-axis.");
//                    }
//                }
//
//            });
            
//            for (Node label : xAxis.lookupAll(".axis-label")) {  
//                label.setStyle("-fx-wrap-text: true;"); 
//                System.out.println(label.getId());
//            }  
        }
        return xAxis;
    }
    
    /**
     * returns the main controller instance...
     * 
     * @return 
     */
    private MainController getController() {
        return this.mController;
    }
}
