package com.liferay.travel.internal.search.spi.model.index.contributor;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import com.liferay.travel.model.Trip;
import org.osgi.service.component.annotations.Component;

/**
 * @author Luis Miguel Barcos
 */
@Component(
        immediate = true,
        property = "indexer.class.name=com.liferay.travel.model.Trip",
        service = ModelDocumentContributor.class
)
public class TripModelDocumentContributor implements ModelDocumentContributor<Trip> {
    @Override
    public void contribute(Document document, Trip trip) {
        document.addText(Field.NAME, trip.getName());
        document.addText(Field.DESCRIPTION, trip.getDescription());
    }
}
