package de.retest.web;

import java.awt.image.BufferedImage;

import de.retest.recheck.ui.descriptors.Element;
import de.retest.recheck.ui.descriptors.IdentifyingAttributes;
import de.retest.recheck.ui.descriptors.MutableAttributes;
import de.retest.recheck.ui.descriptors.RootElement;
import de.retest.recheck.ui.image.ImageUtils;
import de.retest.recheck.ui.image.Screenshot;

public class RootElementPeer extends WebElementPeer {

	private final String title;
	private final BufferedImage screenshot;

	public RootElementPeer( final WebData webData, final String path, final String title,
			final BufferedImage screenshot ) {
		super( webData, path );
		this.screenshot = screenshot;
		this.title = title;
	}

	@Override
	public RootElement toElement( final Element parent ) {
		final IdentifyingAttributes identifyingAttributes = retrieveIdentifyingAttributes();
		final MutableAttributes stateAttributes = retrieveStateAttributes();
		final String retestId = RecheckSeleniumAdapter.idProvider.getRetestId( identifyingAttributes );
		final Screenshot ss = ImageUtils.image2Screenshot( retestId, screenshot );
		final RootElement rootElement =
				new RootElement( retestId, identifyingAttributes, stateAttributes.immutable(), ss, title, 1, title );
		rootElement.addChildren( convertChildren( rootElement ) );
		return rootElement;
	}

}
