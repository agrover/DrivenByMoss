// Written by Jürgen Moßgraber - mossgrabers.de
// (c) 2017
// Licensed under LGPLv3 - http://www.gnu.org/licenses/lgpl-3.0.txt

package de.mossgrabers.sl;

import com.bitwig.extension.api.PlatformType;
import com.bitwig.extension.controller.AutoDetectionMidiPortNamesList;
import com.bitwig.extension.controller.ControllerExtension;
import com.bitwig.extension.controller.api.ControllerHost;

import java.util.UUID;


/**
 * Definition class for the Novation SLmkI controller extension.
 *
 * @author J&uuml;rgen Mo&szlig;graber
 */
public class SLMkIControllerExtensionDefinition extends SLControllerExtensionDefinition
{
    private static final UUID EXTENSION_ID = UUID.fromString ("A9041F50-0407-11E5-B939-0800200C9A66");


    /** {@inheritDoc} */
    @Override
    public String getHardwareModel ()
    {
        return "SL MkI";
    }


    /** {@inheritDoc} */
    @Override
    public UUID getId ()
    {
        return EXTENSION_ID;
    }


    /** {@inheritDoc} */
    @Override
    public void listAutoDetectionMidiPortNames (final AutoDetectionMidiPortNamesList list, final PlatformType platformType)
    {
        list.add (new String []
        {
            "ReMOTE SL Port 2",
            "ReMOTE SL Port 1"
        }, new String []
        {
            "ReMOTE SL Port 2"
        });
    }


    /** {@inheritDoc} */
    @Override
    public ControllerExtension createInstance (final ControllerHost host)
    {
        return new SLControllerExtension (this, host, false);
    }
}
