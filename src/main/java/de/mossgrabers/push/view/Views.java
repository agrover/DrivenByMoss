// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.push.view;

import java.util.HashSet;
import java.util.Set;


/**
 * Static view IDs.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class Views
{
    /** View for playing notes. */
    public static final Integer       VIEW_PLAY       = Integer.valueOf (0);
    /** View for a session grid with clips. */
    public static final Integer       VIEW_SESSION    = Integer.valueOf (1);
    /** View for a sequencer. */
    public static final Integer       VIEW_SEQUENCER  = Integer.valueOf (2);
    /** View for playing drums and sequencing. */
    public static final Integer       VIEW_DRUM       = Integer.valueOf (3);
    /** View for raindrops sequencer. */
    public static final Integer       VIEW_RAINDROPS  = Integer.valueOf (4);
    /** View for playing in piano keyboard style. */
    public static final Integer       VIEW_PIANO      = Integer.valueOf (5);
    /** View sending program changes. */
    public static final Integer       VIEW_PRG_CHANGE = Integer.valueOf (6);
    /** View for editing the clip length. */
    public static final Integer       VIEW_CLIP       = Integer.valueOf (7);
    /** View for drum sequencing with 4 sounds. */
    public static final Integer       VIEW_DRUM4      = Integer.valueOf (8);
    /** View for drum sequencing with 8 sounds. */
    public static final Integer       VIEW_DRUM8      = Integer.valueOf (9);
    /** View for drum playing with 64 pads. */
    public static final Integer       VIEW_DRUM64     = Integer.valueOf (10);
    /** View for selecting a color. */
    public static final Integer       VIEW_COLOR      = Integer.valueOf (11);

    private static final Set<Integer> SEQUENCER_VIEWS = new HashSet<> ();

    static
    {
        SEQUENCER_VIEWS.add (VIEW_SEQUENCER);
        SEQUENCER_VIEWS.add (VIEW_RAINDROPS);
        SEQUENCER_VIEWS.add (VIEW_DRUM);
        SEQUENCER_VIEWS.add (VIEW_DRUM4);
        SEQUENCER_VIEWS.add (VIEW_DRUM8);
    }


    /**
     * Private due to utility class.
     */
    private Views ()
    {
        // Intentionally empty
    }


    /**
     * Returns true if the given view ID is one of the sequencer views.
     *
     * @param viewId The view ID to test
     * @return True if it is a sequencer view
     */
    public static boolean isSequencerView (final Integer viewId)
    {
        return SEQUENCER_VIEWS.contains (viewId);
    }
}