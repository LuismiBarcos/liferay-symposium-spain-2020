package com.liferay.travel.internal.search;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;
import com.liferay.travel.model.Trip;
import com.liferay.travel.service.TripLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luis Miguel Barcos
 */

@Component(
        immediate = true,
        property = "indexer.class.name=com.liferay.travel.model.Trip",
        service = ModelIndexerWriterContributor.class
)
public class TripModelIndexerWriterContributor implements ModelIndexerWriterContributor<Trip> {

    @Reference
    protected DynamicQueryBatchIndexingActionableFactory dynamicQueryBatchIndexingActionableFactory;

    @Reference
    protected TripLocalService tripLocalService;

    @Override
    public void customize(BatchIndexingActionable batchIndexingActionable, ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {
        batchIndexingActionable.setPerformActionMethod((Trip trip) -> {
            Document document = modelIndexerWriterDocumentHelper.getDocument(trip);
            batchIndexingActionable.addDocuments(document);
        });
    }

    @Override
    public BatchIndexingActionable getBatchIndexingActionable() {
        return dynamicQueryBatchIndexingActionableFactory
                .getBatchIndexingActionable(tripLocalService.getIndexableActionableDynamicQuery());
    }

    @Override
    public long getCompanyId(Trip trip) {
        return trip.getCompanyId();
    }
}
