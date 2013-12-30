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

package ch.tuason.djbattlescore.lib;

/**
 *
 * @author maesi
 */
public interface DjBattleConstants {
    
    // APPLICATION VARIABLES...
    public static final String APPLICATION_TITLE = "...::: DJ Battle Scoreboard - Kurhaus Lenzerheide :::...";
    
    public static final Integer APP_MIN_SCENE_WIDTH = 1024;
    public static final Integer APP_MIN_SCENE_HEIGHT = 850;
    
    // private final String cCSS_FILE_RESOURCE = "/styles/Styles.css";
    // private final String cFXML_FILE_RESOURCE = "/fxml/Scene.fxml";

    public static final Integer START_NUMBER_FOR_VOTES = 10;
    
    public static final Integer CHART_STANDARD_TICK_UNIT_STEPS = 10;
    public static final Integer CHART_STANDARD_UPPER_BOUND_LIMIT = 100;
    
    public static final Integer DJ_AVATAR_SIZE_BIG_WIDTH = 250;
    public static final Integer DJ_AVATAR_SIZE_SMALL_WIDTH = 32;
    
    
    // COLOR CONSTANTS...
    public static final String COLOR_RESULT_TITLE_TEXT = "#0076a3";
    public static final String FOOTER_AND_HEADER_BACKGROUND_STYLE = "-fx-background-color: #336699;";
    
    
    
    
    // RESOURCES CONSTANTS...
    
    // DATA FILE (djs.csv)
    public static final String FILE_RESOURCE_DJS_CSV = "/data/djs.csv";
    public static final String FILE_CVS_SPLIT_BY = ",";
    
    // IMAGE CONSTANTS...
    public static final String IMAGE_RESOURCE_LOGO_KURHAUS = "/images/kurhaus_logo.png";
    public static final String IMAGE_RESOURCE_TURNTABLE_LOGO_32 = "/images/turntables32.jpg";
    public static final String IMAGE_RESOURCE_TURNTABLE_LOGO_48 = "/images/turntables48.jpg";
    
    
    // IMAGE CONSTANTS FOR DJ'S...
    public static final String IMAGE_RESOURCE_BASE_FOR_DJ_PICS = "/images/dj/";
    
    // IMAGE CONSTANTS FOR STANDARD DJ'S...
    public static final String IMAGE_RESOURCE_PLAYING_NOW_STANDARD = "/images/dj/standard_image.jpg";
    
//    public static final String IMAGE_RESOURCE_DJ_AVATAR_1_32 = "/images/dj/dj_avatar_1_32.jpg";
//    public static final String IMAGE_RESOURCE_DJ_AVATAR_2_32 = "/images/dj/dj_avatar_2_32.jpg";
//    public static final String IMAGE_RESOURCE_DJ_AVATAR_3_32 = "/images/dj/dj_avatar_3_32.jpg";
//    public static final String IMAGE_RESOURCE_DJ_AVATAR_4_32 = "/images/dj/dj_avatar_4_32.jpg";
//    public static final String IMAGE_RESOURCE_DJ_AVATAR_5_32 = "/images/dj/dj_avatar_5_32.jpg";
//    
//    public static final String IMAGE_RESOURCE_DJ_AVATAR_1 = "/images/dj/dj_avatar_1.jpg";
//    public static final String IMAGE_RESOURCE_DJ_AVATAR_2 = "/images/dj/dj_avatar_2.jpg";
//    public static final String IMAGE_RESOURCE_DJ_AVATAR_3 = "/images/dj/dj_avatar_3.jpg";
//    public static final String IMAGE_RESOURCE_DJ_AVATAR_4 = "/images/dj/dj_avatar_4.jpg";
//    public static final String IMAGE_RESOURCE_DJ_AVATAR_5 = "/images/dj/dj_avatar_5.jpg";
}
