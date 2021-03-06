// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.push.mode;

import de.mossgrabers.framework.ButtonEvent;
import de.mossgrabers.framework.Model;
import de.mossgrabers.framework.controller.color.ColorManager;
import de.mossgrabers.framework.controller.display.Display;
import de.mossgrabers.framework.daw.data.TrackData;
import de.mossgrabers.framework.mode.AbstractMode;
import de.mossgrabers.framework.view.ViewManager;
import de.mossgrabers.push.controller.DisplayMessage;
import de.mossgrabers.push.controller.PushControlSurface;
import de.mossgrabers.push.controller.PushDisplay;
import de.mossgrabers.push.view.Views;


/**
 * Mode to select a view.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class NoteViewSelectMode extends BaseMode
{
    /** The views to choose from. */
    private static final Integer [] VIEWS     =
    {
        Views.VIEW_PLAY,
        Views.VIEW_PIANO,
        Views.VIEW_SEQUENCER,
        Views.VIEW_RAINDROPS,
        Views.VIEW_DRUM,
        Views.VIEW_DRUM4,
        Views.VIEW_DRUM8,
        Views.VIEW_DRUM64
    };

    /** More views to choose from. */
    private static final Integer [] VIEWS_TOP =
    {
        null,
        null,
        null,
        null,
        null,
        null,
        Views.VIEW_CLIP,
        Views.VIEW_PRG_CHANGE
    };


    /**
     * Constructor.
     *
     * @param surface The control surface
     * @param model The model
     */
    public NoteViewSelectMode (final PushControlSurface surface, final Model model)
    {
        super (surface, model);
    }


    /** {@inheritDoc} */
    @Override
    public void onFirstRow (final int index, final ButtonEvent event)
    {
        if (event == ButtonEvent.UP)
            this.activateView (VIEWS[index]);
    }


    /** {@inheritDoc} */
    @Override
    public void onSecondRow (final int index, final ButtonEvent event)
    {
        if (event == ButtonEvent.UP)
            this.activateView (VIEWS_TOP[index]);
    }


    /** {@inheritDoc} */
    @Override
    public void updateDisplay1 ()
    {
        final Display d = this.surface.getDisplay ();
        final ViewManager viewManager = this.surface.getViewManager ();
        d.clear ().setBlock (1, 0, "Note view:");
        for (int i = 0; i < VIEWS.length; i++)
        {
            if (VIEWS[i] != null)
                d.setCell (3, i, (viewManager.isActiveView (VIEWS[i]) ? PushDisplay.RIGHT_ARROW : "") + viewManager.getView (VIEWS[i]).getName ());
            if (VIEWS_TOP[i] != null)
                d.setCell (0, i, (viewManager.isActiveView (VIEWS_TOP[i]) ? PushDisplay.RIGHT_ARROW : "") + viewManager.getView (VIEWS_TOP[i]).getName ());
        }
        d.allDone ();
    }


    /** {@inheritDoc} */
    @Override
    public void updateDisplay2 ()
    {
        final ViewManager viewManager = this.surface.getViewManager ();
        final DisplayMessage message = ((PushDisplay) this.surface.getDisplay ()).createMessage ();
        for (int i = 0; i < VIEWS.length; i++)
        {
            final String menuBottomName = VIEWS[i] == null ? "" : viewManager.getView (VIEWS[i]).getName ();
            final String menuTopName = VIEWS_TOP[i] == null ? "" : viewManager.getView (VIEWS_TOP[i]).getName ();
            final boolean isMenuBottomSelected = VIEWS[i] != null && viewManager.isActiveView (VIEWS[i]);
            final boolean isMenuTopSelected = VIEWS_TOP[i] != null && viewManager.isActiveView (VIEWS_TOP[i]);
            message.addOptionElement ("", menuTopName, isMenuTopSelected, i == 0 ? "Note view" : "", menuBottomName, isMenuBottomSelected, false);
        }
        message.send ();
    }


    /** {@inheritDoc} */
    @Override
    public void updateFirstRow ()
    {
        final ColorManager colorManager = this.model.getColorManager ();
        final ViewManager viewManager = this.surface.getViewManager ();
        for (int i = 0; i < 8; i++)
            this.surface.updateButton (20 + i, colorManager.getColor (VIEWS[i] == null ? AbstractMode.BUTTON_COLOR_OFF : viewManager.isActiveView (VIEWS[i]) ? AbstractMode.BUTTON_COLOR_HI : AbstractMode.BUTTON_COLOR_ON));
    }


    /** {@inheritDoc} */
    @Override
    public void updateSecondRow ()
    {
        final ColorManager colorManager = this.model.getColorManager ();
        final ViewManager viewManager = this.surface.getViewManager ();
        for (int i = 0; i < 8; i++)
            this.surface.updateButton (102 + i, colorManager.getColor (VIEWS_TOP[i] == null ? AbstractMode.BUTTON_COLOR_OFF : viewManager.isActiveView (VIEWS_TOP[i]) ? AbstractMode.BUTTON_COLOR_HI : AbstractMode.BUTTON_COLOR_ON));
    }


    private void activateView (final Integer viewID)
    {
        if (viewID == null)
            return;

        final ViewManager viewManager = this.surface.getViewManager ();
        viewManager.setActiveView (viewID);

        final TrackData selectedTrack = this.model.getCurrentTrackBank ().getSelectedTrack ();
        if (selectedTrack != null)
            viewManager.setPreferredView (selectedTrack.getPosition (), viewID);
        this.surface.getModeManager ().restoreMode ();
    }
}