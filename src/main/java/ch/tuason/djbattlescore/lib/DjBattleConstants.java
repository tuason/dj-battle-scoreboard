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
 * a collection of constants / finalized variables to handle some app settings,
 * collors, look & feel and project pathes...
 * 
 * @author maesi
 */
public interface DjBattleConstants {
    
    // PREFIXES FOR ABSOLUTE FILE PATH FOR DJ PICTURES...
    public static final String ABSOLUTE_IMAGE_FILEPATH_PREFIX = "file:";
    
    
    // APPLICATION VARIABLES...
    public static final String APPLICATION_TITLE = "...::: DJ Roulette Scoreboard - Kurhaus Lenzerheide :::...";
    public static final String HEADER_TITLE = ". . . : : :   DJ Roulette   : : : . . .";
    
    public static final Integer APP_MIN_SCENE_WIDTH = 1024;
    public static final Integer APP_MIN_SCENE_HEIGHT = 850;
    
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
    public static final String IMAGE_RESOURCE_LOGO_PARTY_SPONSOR = "images/ho_logo.png";
    public static final String IMAGE_RESOURCE_TURNTABLE_LOGO_32 = "/images/turntables32.jpg";
    public static final String IMAGE_RESOURCE_TURNTABLE_LOGO_48 = "/images/turntables48.jpg";
    
    
    // IMAGE CONSTANTS FOR DJ'S...
    public static final String IMAGE_RESOURCE_BASE_FOR_DJ_PICS = "/images/dj/";
    
    // IMAGE CONSTANTS FOR STANDARD DJ'S...
    public static final String IMAGE_RESOURCE_PLAYING_NOW_STANDARD = "/images/dj/standard_image.jpg";    
}
