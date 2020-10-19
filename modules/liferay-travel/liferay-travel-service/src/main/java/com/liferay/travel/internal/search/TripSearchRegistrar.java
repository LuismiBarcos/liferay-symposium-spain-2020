package com.liferay.travel.internal.search;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchRegistrarHelper;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;
import com.liferay.travel.model.Trip;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Luis Miguel Barcos
 */
@Component(immediate = true)
public class TripSearchRegistrar {
    @Reference(target = "(indexer.class.name=com.liferay.travel.model.Trip)")
    protected ModelIndexerWriterContributor<Trip> modelIndexerWriterContributor;
    @Reference
    protected ModelSearchRegistrarHelper modelSearchRegistrarHelper;
    @Reference(target = "(indexer.class.name=com.liferay.travel.model.Trip)")
    protected ModelSummaryContributor modelSummaryContributor;
    private ServiceRegistration<?> serviceRegistration;

    @Activate
    protected void activate(BundleContext bundleContext) {
        serviceRegistration = modelSearchRegistrarHelper.register(
            Trip.class, bundleContext, modelSearchDefinition -> {
                modelSearchDefinition.setDefaultSelectedFieldNames(
                    Field.ASSET_TAG_NAMES, Field.COMPANY_ID, Field.CONTENT,
                    Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
                    Field.GROUP_ID, Field.MODIFIED_DATE, Field.SCOPE_GROUP_ID,
                    Field.TITLE, Field.UID, Field.CATEGORY_ID, Field.COMPANY_ID,
                    Field.USER_ID, Field.ASSET_CATEGORY_IDS, Field.ASSET_CATEGORY_TITLE,
                    Field.ASSET_VOCABULARY_IDS, Field.ROLE_ID, Field.GROUP_ROLE_ID);

            modelSearchDefinition.setModelIndexWriteContributor(modelIndexerWriterContributor);
            modelSearchDefinition.setModelSummaryContributor(modelSummaryContributor);
        });
    }

    @Deactivate
    protected void deactivate() {
        serviceRegistration.unregister();
    }
}
