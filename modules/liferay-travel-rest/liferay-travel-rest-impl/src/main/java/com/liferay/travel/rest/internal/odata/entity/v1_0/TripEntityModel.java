package com.liferay.travel.rest.internal.odata.entity.v1_0;

import com.liferay.headless.common.spi.odata.entity.EntityFieldsMapFactory;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.odata.entity.StringEntityField;

import java.util.Map;

/**
 * @author Luis Miguel Barcos
 */
public class TripEntityModel implements EntityModel {
    private final Map<String, EntityField> entityFieldsMap;

    public TripEntityModel() {
        entityFieldsMap = EntityFieldsMapFactory.create(
                new StringEntityField(Field.NAME, locale -> Field.NAME),
                new StringEntityField(Field.DESCRIPTION, locale -> Field.DESCRIPTION)
        );
    }

    @Override
    public Map<String, EntityField> getEntityFieldsMap() {
        return entityFieldsMap;
    }
}
